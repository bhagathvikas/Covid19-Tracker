package com.example.covid.ui.districts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid.R;
import com.example.covid.ui.states.CovidState;
import com.example.covid.ui.states.CovidStateAdapter;

import java.util.ArrayList;

public class CovidDistAdapter extends RecyclerView.Adapter<CovidDistAdapter.ViewHolder> {

    ArrayList<CovidDist> covidDists;
    Context context;

    public CovidDistAdapter(ArrayList<CovidDist> covidDists, Context context){
        this.covidDists = covidDists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.item_list_covid_dist, parent, false);
        return new CovidDistAdapter.ViewHolder(view  );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CovidDist covidDist = covidDists.get( position );
        holder.tvTotal.setText(covidDist.getmComfirmed());
        holder.tvDist.setText(covidDist.getmDistrict());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTotal,tvDist;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            tvDist = itemView.findViewById( R.id.textDist );
            tvTotal = itemView.findViewById( R.id.textDTotal );
        }
    }
}
