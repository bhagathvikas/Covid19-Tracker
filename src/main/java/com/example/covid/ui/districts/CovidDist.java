package com.example.covid.ui.districts;

import android.os.Parcel;
import android.os.Parcelable;

public class CovidDist implements Parcelable {


    String mDistrict,mComfirmed;

    public String getmDistrict() {
        return mDistrict;
    }

    public void setmDistrict(String mDistrict) {
        this.mDistrict = mDistrict;
    }

    public String getmComfirmed() {
        return mComfirmed;
    }

    public void setmComfirmed(String mComfirmed) {
        this.mComfirmed = mComfirmed;
    }

    public static Creator<CovidDist> getCREATOR() {
        return CREATOR;
    }

    public CovidDist(String mDistrict, String mComfirmed){
        this.mComfirmed  = mComfirmed;
        this.mDistrict = mDistrict;
    }



    protected CovidDist(Parcel in) {

        this.mDistrict = in.readString();
        this.mComfirmed = in.readString();
    }

    public static final Creator<CovidDist> CREATOR = new Creator<CovidDist>() {
        @Override
        public CovidDist createFromParcel(Parcel in) {
            return new CovidDist( in );
        }

        @Override
        public CovidDist[] newArray(int size) {
            return new CovidDist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString( this.mComfirmed );
        dest.writeString( this.mDistrict );
    }
}
