package com.example.travelmate;

import android.os.Parcel;
import android.os.Parcelable;

public class AgencyRVModel implements Parcelable {
    private String agencyId;
    private String agencyName;
    private String contactNumber;
    private String locationLink;
    private String logoLink;
    private String description;


    public AgencyRVModel() {

    }

    public AgencyRVModel(String agencyId, String agencyName, String contactNumber, String locationLink, String logoLink, String description) {
        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.contactNumber = contactNumber;
        this.locationLink = locationLink;
        this.logoLink = logoLink;
        this.description = description;
    }

    protected AgencyRVModel(Parcel in) {
        agencyId = in.readString();
        agencyName = in.readString();
        contactNumber = in.readString();
        locationLink = in.readString();
        logoLink = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(agencyId);
        dest.writeString(agencyName);
        dest.writeString(contactNumber);
        dest.writeString(locationLink);
        dest.writeString(logoLink);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AgencyRVModel> CREATOR = new Creator<AgencyRVModel>() {
        @Override
        public AgencyRVModel createFromParcel(Parcel in) {
            return new AgencyRVModel(in);
        }

        @Override
        public AgencyRVModel[] newArray(int size) {
            return new AgencyRVModel[size];
        }
    };

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLocationLink() {
        return locationLink;
    }

    public void setLocationLink(String locationLink) {
        this.locationLink = locationLink;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
