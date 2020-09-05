package com.trendyol.discount.campaign;

public class CampaignRateDiscount implements CampaignDiscount {
    @Override
    public double apply(double totalPrice, double discountAmount) {
        return totalPrice * discountAmount / 100;
    }
}
