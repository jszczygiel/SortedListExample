package com.jszczygiel.sortedlistexample.ui.view.viewmodels;

import android.os.Parcel;
import android.os.Parcelable;

public class EmailViewModel extends BaseViewModel implements Parcelable{
    final String email;

    public EmailViewModel(String id, String email) {
        super(Types.EMAIL, id);
        this.email=email;
    }

    public EmailViewModel(Parcel in) {
        super(in);
        email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(email);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EmailViewModel> CREATOR = new Creator<EmailViewModel>() {
        @Override
        public EmailViewModel createFromParcel(Parcel in) {
            return new EmailViewModel(in);
        }

        @Override
        public EmailViewModel[] newArray(int size) {
            return new EmailViewModel[size];
        }
    };

    public String getEmail() {
        return email;
    }
}
