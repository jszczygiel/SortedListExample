package com.jszczygiel.sortedlistexample.ui.view.viewmodels;

import android.os.Parcel;
import android.os.Parcelable;

public class PhoneViewModel extends BaseViewModel implements Parcelable {
    final String phoneNumber;

    public PhoneViewModel(String id, String phoneNumber) {
        super(Types.PHONE, id);
        this.phoneNumber = phoneNumber;
    }

    protected PhoneViewModel(Parcel in) {
        super(in);
        phoneNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(phoneNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhoneViewModel> CREATOR = new Creator<PhoneViewModel>() {
        @Override
        public PhoneViewModel createFromParcel(Parcel in) {
            return new PhoneViewModel(in);
        }

        @Override
        public PhoneViewModel[] newArray(int size) {
            return new PhoneViewModel[size];
        }
    };

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
