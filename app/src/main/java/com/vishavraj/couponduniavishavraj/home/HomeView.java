package com.vishavraj.couponduniavishavraj.home;

import com.vishavraj.couponduniavishavraj.models.CouponDuniaData;

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getityListSuccess(CouponDuniaData couponDuniaResponse);

}
