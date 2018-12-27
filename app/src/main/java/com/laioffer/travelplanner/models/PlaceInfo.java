package com.laioffer.travelplanner.models;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

public class PlaceInfo {

    private String id;
    private String place_id;

    private String name;

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("formatted_address")
    private String address;

    private float rating;
    private String phoneNumber;

    @SerializedName("website")
    private Uri websiteUri;

    @SerializedName("opening_hours")
    private OpeningHours openingHours;

    private LatLng latlng;
    private String attributions;

    public PlaceInfo(String name, String address, String phoneNumber, String id, Uri websiteUri,
                     LatLng latlng, float rating, String attributions) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.websiteUri = websiteUri;
        this.latlng = latlng;
        this.rating = rating;
        this.attributions = attributions;
    }

    public PlaceInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(Uri websiteUri) {
        this.websiteUri = websiteUri;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getAttributions() {
        return attributions;
    }

    public void setAttributions(String attributions) {
        this.attributions = attributions;
    }

    @Override
    public String toString() {
        return "PlaceInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id='" + id + '\'' +
                ", websiteUri=" + websiteUri +
                ", latlng=" + latlng +
                ", rating=" + rating +
                ", attributions='" + attributions + '\'' +
                '}';
    }

    public String getPlace_id() {
        return place_id;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }
}
