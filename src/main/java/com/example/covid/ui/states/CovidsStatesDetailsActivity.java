package com.example.covid.ui.states;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.covid.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CovidsStatesDetailsActivity extends AppCompatActivity {
    TextView active,death,cases,recoverd,time,state;
    private InterstitialAd mInterstitialAd;
    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_covids_states_details );

        state = findViewById( R.id.tvStateName);
cases = findViewById( R.id.tvTotalCases );
time = findViewById( R.id.tvLastUpdated );
active = findViewById( R.id.tvTotalActive );
death = findViewById( R.id.tvTotalDeaths );
recoverd = findViewById( R.id.tvTotalRecovered );



        MobileAds.initialize( this,"ca-app-pub-9778081856603284~7149946991" );
        mAdView = findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd( adRequest );

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //call volley
        CovidState covidState = getIntent().getParcelableExtra( "EXTRA_COVID" );

        //getmState() is deaths
        //recoverd is Total cases
        //confirmed is date
        //actvie is active
        //getmdeaths is recoverd

        state.setText( covidState.getmLastUpDatedTime() );
        cases.setText( covidState.getmRecovered() );
        time.setText( covidState.getmConfirmed() );
        active.setText( covidState.getmActive() );
        recoverd.setText( covidState.getmDeaths() );
        death.setText( covidState.getmState() );





        prepareAD();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate( new Runnable() {
            @Override
            public void run() {
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        if (mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();
                        }
                        else {
                            Log.d("TAG","AD not loaded");
                        }
                        prepareAD();
                    }
                } );
            }
        },50,50, TimeUnit.SECONDS );


    }
    public void  prepareAD(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9778081856603284/5571580947");
        mInterstitialAd.loadAd( new AdRequest.Builder().build() );

    }

}
