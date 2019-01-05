package com.laioffer.travelplanner.retrofit;

import com.laioffer.travelplanner.retrofit.response.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlacesRequestApi {

    @GET("nearbysearch/json?key=AIzaSyD-qkmojsCbUX5W1f-jnIphjS4L_fAVxa4")
    Observable<BaseResponse> getNearbyPlaces(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("name") String name
    );
}
