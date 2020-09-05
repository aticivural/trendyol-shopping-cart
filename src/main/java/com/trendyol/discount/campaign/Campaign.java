package com.trendyol.discount.campaign;

import com.trendyol.model.Category;
import com.trendyol.discount.DiscountType;

public class Campaign{

    private Category category;
    private double discountAmount;
    private int minItemCount;
    private DiscountType discountType;

    public Campaign(Category category, double discountAmount, int minItemCount, DiscountType discountType) {
        this.category = category;
        this.discountAmount = discountAmount;
        this.minItemCount = minItemCount;
        this.discountType = discountType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getMinItemCount() {
        return minItemCount;
    }

    public void setMinItemCount(int minItemCount) {
        this.minItemCount = minItemCount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public double calculate(double totalPrice) {
        return CampaignCalculator.calculate(this, totalPrice);
    }

}
