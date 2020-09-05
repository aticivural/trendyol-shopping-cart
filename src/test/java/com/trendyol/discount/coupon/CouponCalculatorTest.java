package com.trendyol.discount.coupon;

import com.trendyol.discount.DiscountType;
import com.trendyol.discount.campaign.Campaign;
import com.trendyol.discount.campaign.CampaignCalculator;
import com.trendyol.model.Category;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CouponCalculatorTest {

    @Test
    public void should_calculate_amount_discount() {
        Coupon rateCoupon = new Coupon(100, 10, DiscountType.AMOUNT);
        double discount = CouponCalculator.calculate(rateCoupon, 150);
        Assert.assertEquals(10.0, discount, 2);
    }

    @Test
    public void should_calculate_rate_discount() {
        Coupon rateCoupon = new Coupon(100, 10, DiscountType.RATE);
        double discount = CouponCalculator.calculate(rateCoupon, 150);
        Assert.assertEquals(15.0, discount, 2);
    }

}