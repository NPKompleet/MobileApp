package com.ipnx.ipnxmobile.models.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.ipnx.ipnxmobile.models.responses.login.InternetService;

import java.util.List;

public class TestClass1 implements Parcelable {
    private int boy;
    private List<InternetService> girls;

    TestClass1(int boy, List<InternetService> girls){
        this.boy= boy;
        this.girls = girls;
    }


    protected TestClass1(Parcel in) {
        boy = in.readInt();
        girls = in.createTypedArrayList(InternetService.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(boy);
        dest.writeTypedList(girls);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TestClass1> CREATOR = new Creator<TestClass1>() {
        @Override
        public TestClass1 createFromParcel(Parcel in) {
            return new TestClass1(in);
        }

        @Override
        public TestClass1[] newArray(int size) {
            return new TestClass1[size];
        }
    };
}
