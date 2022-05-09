package com.example.accommodations_management;

public class AccModel {
    String name, location, description, venue_type,contact,surl;

    AccModel(){

    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public AccModel(String name, String location, String description, String venue_type, String contact, String surl) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.venue_type = venue_type;
        this.contact = contact;
        this.surl= surl;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue_type() {
        return venue_type;
    }

    public void setVenue_type(String venue_type) {
        this.venue_type = venue_type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
