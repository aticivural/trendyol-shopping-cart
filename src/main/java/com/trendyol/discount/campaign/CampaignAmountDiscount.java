package com.trendyol.discount.campaign;

public class CampaignAmountDiscount implements CampaignDiscount {
    @Override
    public double apply(double totalPrice, double discountAmount) {
        return discountAmount;
    }
}
