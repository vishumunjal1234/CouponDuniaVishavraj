package com.vishavraj.couponduniavishavraj.networking;


import com.vishavraj.couponduniavishavraj.models.CouponDuniaData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkService {

    @GET("task?")
    Observable<CouponDuniaData> getCityList(@Query("page") String pageNo);

}
