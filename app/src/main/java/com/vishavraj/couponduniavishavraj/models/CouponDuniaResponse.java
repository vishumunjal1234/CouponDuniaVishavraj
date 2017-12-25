package com.vishavraj.couponduniavishavraj.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class CouponDuniaResponse {

    @SerializedName("list")
    @Expose
    private java.util.List<CouponDuniaList> list = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_items")
    @Expose
    private Integer totalItems;

    public java.util.List<CouponDuniaList> getList() {
        return list;
    }

    public void setList(java.util.List<CouponDuniaList> list) {
        this.list = list;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}