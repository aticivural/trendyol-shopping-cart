package com.trendyol.delivery;

import com.trendyol.shoppingcart.ShoppingCart;

public class DeliveryCostCalculatorImpl implements DeliveryCostCalculator{
    private double costPerDelivery;
    private double costPerProduct;
    private static final double FIXED_COST = 2.99;

    public DeliveryCostCalculatorImpl(double costPerDelivery, double costPerProduct) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
    }


    public double getCostPerDelivery() {
        return costPerDelivery;
    }

    public void setCostPerDelivery(double costPerDelivery) {
        this.costPerDelivery = costPerDelivery;
    }

    public double getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(double costPerProduct) {
        this.costPerProduct = costPerProduct;
    }


    public double calculateFor(ShoppingCart cart){
        // (CostPerDelivery * NumberOfDeliveries) + (CostPerProduct * NumberOfProducts) + FixedCost
        // NumberOfDeliveries = number of distinct categories in the cart
        // NumberOfProducts   = number of distinct products   in the cart

        int numberOfDeliveries = cart.getDistinctCategories().size();
        int numberOfProducts = cart.getDistinctProducts().size();
        return (costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + FIXED_COST;
    }
}
