package com.ipnx.ipnxmobile.models.responses.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceAddress implements Parcelable
{

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("state")
    @Expose
    private String state;
    public final static Parcelable.Creator<ServiceAddress> CREATOR = new Creator<ServiceAddress>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceAddress createFromParcel(Parcel in) {
            return new ServiceAddress(in);
        }

        public ServiceAddress[] newArray(int size) {
            return (new ServiceAddress[size]);
        }

    }
            ;

    protected ServiceAddress(Parcel in) {
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.area = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ServiceAddress() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(city);
        dest.writeValue(area);
        dest.writeValue(address);
        dest.writeValue(state);
    }

    public int describeContents() {
        return 0;
    }

}