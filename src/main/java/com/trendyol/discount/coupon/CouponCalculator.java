package com.trendyol.discount.coupon;

import com.trendyol.discount.DiscountType;

import java.util.HashMap;
import java.util.Map;

public class CouponCalculator {

    private static Map<DiscountType, CouponDiscount> couponDiscountMap = new HashMap<>();

    static {
        couponDiscountMap.put(DiscountType.AMOUNT, new CouponAmountDiscount());
        couponDiscountMap.put(DiscountType.RATE, new CouponRateDiscount());
    }

    public static double calculate(Coupon coupon, double totalPrice) {
        return couponDiscountMap.get(coupon.getDiscountType()).apply(totalPrice, coupon.getDiscountAmount());
    }

}
