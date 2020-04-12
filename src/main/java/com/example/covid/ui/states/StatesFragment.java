package com.example.covid.ui.states;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid.R;
import com.example.covid.ui.country.CountryFragment;
import com.example.covid.ui.country.CovidCountry;
import com.example.covid.ui.country.CovidCountryAdapter;
import com.example.covid.ui.country.CovidCountryDetails;
import com.example.covid.ui.country.ItemClickSupport;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class StatesFragment extends Fragment {

    RecyclerView rvCovidCountry;
    ProgressBar progressBar;
    TextView tvTotalCountry;
    RequestQueue mQue;
    AdView mAdView;

    private static final String TAG = CountryFragment.class.getSimpleName();
    ArrayList<CovidState> covidStates;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.fragment_states, container, false );

        rvCovidCountry = root.findViewById(R.id.rvCovidStates);
        progressBar = root.findViewById( R.id.progress_circular_country);
        tvTotalCountry = root.findViewById(R.id.tvTotalCountries);
        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));
        mQue = Volley.newRequestQueue( getActivity() );

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCovidCountry.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        rvCovidCountry.addItemDecoration(dividerItemDecoration);

        MobileAds.initialize( getActivity(),"ca-app-pub-9778081856603284~7149946991" );
        mAdView = root.findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd( adRequest );

        // call Volley method
        getDataFromServer();

        return root;
    }

    private void showRecyclerView() {
        CovidStateAdapter covidStateAdapter = new CovidStateAdapter(covidStates, getActivity());
        rvCovidCountry.setAdapter(covidStateAdapter);

        ItemClickSupport.addTo(rvCovidCountry).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedCovidState(covidStates.get(position));
            }
        });
    }

    private void showSelectedCovidState(CovidState covidState){
        Intent covidCovidState = new Intent(getActivity(), CovidsStatesDetailsActivity.class);
        covidCovidState.putExtra("EXTRA_COVID", covidState);
        startActivity(covidCovidState);
    }

    private void getDataFromServer() {
        String url = "https://api.covid19india.org/data.json";

        covidStates = new ArrayList<>();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray( "statewise" );
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject regional = jsonArray.getJSONObject(i);



                            covidStates.add(new CovidState(
                                        regional.getString( "active" ),
                            regional.getString( "state" ),
                            regional.getString( "lastupdatedtime" ),
                            regional.getString( "recovered" ),
                                    regional.getString( "confirmed" ),
                                    regional.getString( "deaths" )

                                ));
                            }

                            showRecyclerView();
                        } catch (JSONException e) {
                        Toast.makeText( getContext(),"Error",Toast.LENGTH_SHORT ).show();
                            e.printStackTrace();
                        }
                    }
                }
            },
                    new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText( getContext(),"error",Toast.LENGTH_SHORT ).show();
                    Log.e(TAG, "onResponse: " + error);
                }
            });
        Volley.newRequestQueue(getActivity()).add(stringRequest);







        }
}




