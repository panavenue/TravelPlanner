package com.laioffer.travelplanner.retrofit.response;

import com.google.gson.annotations.SerializedName;
import com.laioffer.travelplanner.models.PlaceInfo;

import java.util.List;

public class BaseResponse {

    public String status;

    @SerializedName("results")
    public List<PlaceInfo> results;
}
