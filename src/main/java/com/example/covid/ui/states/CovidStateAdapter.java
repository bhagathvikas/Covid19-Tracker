package com.example.covid.ui.states;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.covid.R;
import com.example.covid.ui.country.CovidCountry;

import java.util.ArrayList;

public class CovidStateAdapter extends RecyclerView.Adapter<CovidStateAdapter.ViewHolder> {

    ArrayList<CovidState> covidStates;
    private Context context;

    public CovidStateAdapter(ArrayList<CovidState> covidStates, Context context){
        this.covidStates = covidStates;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.item_list_covid_states, parent, false);
        return new ViewHolder(view  );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CovidState covidState = covidStates.get( position );
        holder.tvTotalCases.setText(covidState.getmConfirmed());
        holder.tvDistName.setText(covidState.getmState());




    }

    @Override
    public int getItemCount() {
        return covidStates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalCases, tvDistName;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            tvTotalCases = itemView.findViewById( R.id.textTotal );
            tvDistName = itemView.findViewById( R.id.textState );



        }
    }
}
