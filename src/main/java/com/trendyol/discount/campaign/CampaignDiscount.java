package com.trendyol.discount.campaign;

public interface CampaignDiscount {

    double apply(double totalPrice, double discountAmount);
}
