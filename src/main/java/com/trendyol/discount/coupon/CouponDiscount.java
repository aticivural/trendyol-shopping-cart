package com.trendyol.discount.coupon;

public interface CouponDiscount {

    double apply(double totalPrice, double discountAmount);
}
