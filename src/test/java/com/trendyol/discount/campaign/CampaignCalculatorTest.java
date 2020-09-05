package com.trendyol.discount.campaign;

import com.trendyol.discount.DiscountType;
import com.trendyol.model.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CampaignCalculatorTest {

    @Test
    public void should_calculate_amount_discount(){
        Category foodCategory = new Category("food");
        Campaign amountCampaign = new Campaign(foodCategory, 10, 1 , DiscountType.AMOUNT);
        double discount = CampaignCalculator.calculate(amountCampaign, 100);
        Assert.assertEquals(10.0, discount,2);

    }

    @Test
    public void should_calculate_rate_discount(){
        Category foodCategory = new Category("food");
        Campaign amountCampaign = new Campaign(foodCategory, 15, 1 , DiscountType.RATE);
        double discount = CampaignCalculator.calculate(amountCampaign, 100);
        Assert.assertEquals(15.0, discount,2);

    }
}