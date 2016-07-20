package com.jszczygiel.sortedlistexample.ui.view.viewmodels;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.CallSuper;

public abstract class BaseViewModel implements Parcelable {
    private final int type;
    private final String id;

    public BaseViewModel(int type, String id) {
        this.type = type;
        this.id = id;
    }

    public BaseViewModel(Parcel in) {
        this.id = in.readString();
        this.type = in.readInt();
    }

    public int getModelType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    @CallSuper
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.id);
        out.writeInt(this.type);
    }

    public boolean equals(Object o) {
        return o instanceof BaseViewModel && this.id.equalsIgnoreCase(((BaseViewModel) o).id) && this.type == ((BaseViewModel) o).type;
    }

    public int hashCode() {
        byte result = 42;
        int result1 = 37 * result + this.id.hashCode();
        result1 = 37 * result1 + this.type;
        return result1;
    }

    public int describeContents() {
        return 0;
    }

    public static class Types {
        public static final int PERSON = 1;
        public static final int EMAIL = 2;
        public static final int PHONE = 3;
    }
}
