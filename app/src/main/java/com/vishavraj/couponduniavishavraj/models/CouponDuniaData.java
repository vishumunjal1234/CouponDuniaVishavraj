package com.vishavraj.couponduniavishavraj.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class CouponDuniaData {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("authenticated")
    @Expose
    private Boolean authenticated;
    @SerializedName("response")
    @Expose
    private CouponDuniaResponse response;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public CouponDuniaResponse getResponse() {
        return response;
    }

    public void setResponse(CouponDuniaResponse response) {
        this.response = response;
    }

}