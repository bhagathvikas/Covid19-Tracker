package com.example.covid.ui.districts;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid.R;
import com.example.covid.ui.country.CountryFragment;
import com.example.covid.ui.states.CovidState;
import com.example.covid.ui.states.CovidStateAdapter;
import com.example.covid.ui.states.CovidsStatesDetailsActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DistFragment extends Fragment {
    RecyclerView rvCovidCountry;
    ProgressBar progressBar;
    AdView adView;
    private AdView mAdView;

    private static final String TAG = DistFragment.class.getSimpleName();
    ArrayList<CovidDist> covidDists;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.fragment_states, container, false );

        rvCovidCountry = root.findViewById(R.id.rvCovidStates);
        progressBar = root.findViewById( R.id.progress_circular_country);

        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCovidCountry.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable( ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        rvCovidCountry.addItemDecoration(dividerItemDecoration);

        mAdView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        adView = new AdView(getActivity());

        adView.setAdSize( AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        // TODO: Add adView to your view hierarchy.


        // call Volley method
        getDataFromServer();

        return root;
    }

    private void showRecyclerView() {
        CovidDistAdapter covidDistAdapter = new CovidDistAdapter( covidDists, getActivity() );
        rvCovidCountry.setAdapter( covidDistAdapter );

        ItemClickSupport.addTo( rvCovidCountry ).setOnItemClickListener( new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                shoseleceteditem(covidDists.get( position ));
            }
        } );

    }

    private void shoseleceteditem(CovidDist covidDist) {

        Intent coviddits = new Intent( getActivity(),CovidDistDetails.class );
        coviddits.putExtra( "Covid",covidDist );
        startActivity( coviddits );
    }

    private void getDataFromServer() {
        final String url = "https://api.covid19india.org/v2/state_district_wise.json:{\"state\":\"Kerala\"";

        covidDists = new ArrayList<>();


        StringRequest stringRequest = new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                       JSONArray jsonArray = jsonObject.getJSONArray( "districtData" );
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject regional = jsonArray.getJSONObject(i);

                            covidDists.add( new CovidDist(
                                    regional.getString( "district" ),
                                    regional.getString( "confirmed" )

                            ) );





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






