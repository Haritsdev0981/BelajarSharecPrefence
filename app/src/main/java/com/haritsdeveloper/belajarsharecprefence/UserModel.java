package com.haritsdeveloper.belajarsharecprefence;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    private String nama;
    private String emai;
    private String password;
    private String Kelamin;
    private String Aggre;
    private boolean statusLogin;

    public boolean isStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(boolean statusLogin) {
        this.statusLogin = statusLogin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKelamin() {
        return Kelamin;
    }

    public void setKelamin(String kelamin) {
        Kelamin = kelamin;
    }

    public String getAggre() {
        return Aggre;
    }

    public void setAggre(String aggre) {
        Aggre = aggre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.emai);
        dest.writeString(this.password);
        dest.writeString(this.Kelamin);
        dest.writeString(this.Aggre);
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.nama = in.readString();
        this.emai = in.readString();
        this.password = in.readString();
        this.Kelamin = in.readString();
        this.Aggre = in.readString();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
