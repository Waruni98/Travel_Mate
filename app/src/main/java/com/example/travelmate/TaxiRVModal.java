package com.example.travelmate;

import android.os.Parcel;
import android.os.Parcelable;

public class TaxiRVModal implements Parcelable {

    private String taxiName;
    private String taxiType;
    private String taxiLocation;
    private String taxiContact;
    private String taxiNumber;
    private String taxiImage;
    private String taxiDescription;
    private String taxiID;

    public TaxiRVModal(){

    }

    public TaxiRVModal(String taxiName, String taxiType, String taxiLocation, String taxiContact, String taxiNumber, String taxiImage, String taxiDescription, String taxiID) {
        this.taxiName = taxiName;
        this.taxiType = taxiType;
        this.taxiLocation = taxiLocation;
        this.taxiContact = taxiContact;
        this.taxiNumber = taxiNumber;
        this.taxiImage = taxiImage;
        this.taxiDescription = taxiDescription;
        this.taxiID = taxiID;
    }

    protected TaxiRVModal(Parcel in) {
        taxiName = in.readString();
        taxiType = in.readString();
        taxiLocation = in.readString();
        taxiContact = in.readString();
        taxiNumber = in.readString();
        taxiImage = in.readString();
        taxiDescription = in.readString();
        taxiID = in.readString();
    }

    public static final Creator<TaxiRVModal> CREATOR = new Creator<TaxiRVModal>() {
        @Override
        public TaxiRVModal createFromParcel(Parcel in) {
            return new TaxiRVModal(in);
        }

        @Override
        public TaxiRVModal[] newArray(int size) {
            return new TaxiRVModal[size];
        }
    };

    public String getTaxiName() {
        return taxiName;
    }

    public void setTaxiName(String taxiName) {
        this.taxiName = taxiName;
    }

    public String getTaxiType() {
        return taxiType;
    }

    public void setTaxiType(String taxiType) {
        this.taxiType = taxiType;
    }

    public String getTaxiLocation() {
        return taxiLocation;
    }

    public void setTaxiLocation(String taxiLocation) {
        this.taxiLocation = taxiLocation;
    }

    public String getTaxiContact() {
        return taxiContact;
    }

    public void setTaxiContact(String taxiContact) {
        this.taxiContact = taxiContact;
    }

    public String getTaxiNumber() {
        return taxiNumber;
    }

    public void setTaxiNumber(String taxiNumber) {
        this.taxiNumber = taxiNumber;
    }

    public String getTaxiImage() {
        return taxiImage;
    }

    public void setTaxiImage(String taxiImage) {
        this.taxiImage = taxiImage;
    }

    public String getTaxiDescription() {
        return taxiDescription;
    }

    public void setTaxiDescription(String taxiDescription) {
        this.taxiDescription = taxiDescription;
    }

    public String getTaxiID() {
        return taxiID;
    }

    public void setTaxiID(String taxiID) {
        this.taxiID = taxiID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(taxiName);
        parcel.writeString(taxiType);
        parcel.writeString(taxiLocation);
        parcel.writeString(taxiContact);
        parcel.writeString(taxiNumber);
        parcel.writeString(taxiImage);
        parcel.writeString(taxiDescription);
        parcel.writeString(taxiID);
    }
}
