package com.example.covid.ui.states;

import android.os.Parcel;
import android.os.Parcelable;

public class CovidState implements Parcelable {



    String mConfirmed,mDeaths,mRecovered,mState,mLastUpDatedTime,mActive;





    public CovidState(String active, String state, String lastupdatedtime, String recovered, String confirmed, String deaths) {
        this.mConfirmed =confirmed ;
        this.mDeaths = deaths;
        this.mRecovered = recovered;
        this.mActive = active;
        this.mLastUpDatedTime = lastupdatedtime;
        this.mState = state;
    }

    public String getmConfirmed() {
        return mConfirmed;
    }

    public void setmConfirmed(String mConfirmed) {
        this.mConfirmed = mConfirmed;
    }

    public String getmDeaths() {
        return mDeaths;
    }

    public void setmDeaths(String mDeaths) {
        this.mDeaths = mDeaths;
    }

    public String getmRecovered() {
        return mRecovered;
    }

    public void setmRecovered(String mRecovered) {
        this.mRecovered = mRecovered;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmLastUpDatedTime() {
        return mLastUpDatedTime;
    }

    public void setmLastUpDatedTime(String mLastUpDatedTime) {
        this.mLastUpDatedTime = mLastUpDatedTime;
    }
    public String getmActive() {
        return mActive;
    }

    public void setmActive(String mActive) {
        this.mActive = mActive;
    }









    public static final Creator<CovidState> CREATOR = new Creator<CovidState>() {
        @Override
        public CovidState createFromParcel(Parcel in) {
            return new CovidState( in );
        }

        @Override
        public CovidState[] newArray(int size) {
            return new CovidState[size];
        }
    };
    public static Creator<CovidState> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mConfirmed);
        dest.writeString(this.mDeaths);
        dest.writeString(this.mLastUpDatedTime);
        dest.writeString(this.mRecovered);
        dest.writeString( this.mState );
        dest.writeString( this.mActive );

    }

    protected CovidState(Parcel in) {

        this.mRecovered = in.readString();
        this.mState = in.readString();
        this.mConfirmed = in.readString();
        this.mDeaths = in.readString();
        this.mLastUpDatedTime = in.readString();
        this.mActive = in.readString();
    }
}
