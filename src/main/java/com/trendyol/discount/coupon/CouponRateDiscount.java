package com.trendyol.discount.coupon;

public class CouponRateDiscount implements CouponDiscount {
    @Override
    public double apply(double totalPrice, double discountAmount) {
        return totalPrice * discountAmount / 100;
    }
}
