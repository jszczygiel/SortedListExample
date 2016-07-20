package com.jszczygiel.sortedlistexample.ui.view.viewmodels;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonViewModel extends BaseViewModel implements Parcelable {
    final String displayName;
    final String imageUrl;

    public PersonViewModel(String id, String displayName, String imageUrl) {
        super(Types.PERSON, id);
        this.displayName = displayName;
        this.imageUrl = imageUrl;
    }

    public PersonViewModel(Parcel in) {
        super(in);
        displayName = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(displayName);
        dest.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonViewModel> CREATOR = new Creator<PersonViewModel>() {
        @Override
        public PersonViewModel createFromParcel(Parcel in) {
            return new PersonViewModel(in);
        }

        @Override
        public PersonViewModel[] newArray(int size) {
            return new PersonViewModel[size];
        }
    };

    public String getDisplayName() {
        return displayName;
    }

    public String getImage() {
        return imageUrl;
    }
}
