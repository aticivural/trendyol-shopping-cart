package com.trendyol.discount.campaign;

import com.trendyol.discount.DiscountType;

import java.util.HashMap;
import java.util.Map;

public class CampaignCalculator {

    private static Map<DiscountType, CampaignDiscount> campaignDiscountMap = new HashMap<>();

    static {
        campaignDiscountMap.put(DiscountType.AMOUNT, new CampaignAmountDiscount());
        campaignDiscountMap.put(DiscountType.RATE, new CampaignRateDiscount());
    }

    public static double calculate(Campaign campaign, double totalPrice) {
        return campaignDiscountMap.get(campaign.getDiscountType()).apply(totalPrice, campaign.getDiscountAmount());
    }

}
