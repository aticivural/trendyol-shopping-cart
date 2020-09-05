package com.trendyol;

import com.trendyol.delivery.DeliveryCostCalculator;
import com.trendyol.delivery.DeliveryCostCalculatorImpl;
import com.trendyol.discount.DiscountType;
import com.trendyol.discount.campaign.Campaign;
import com.trendyol.discount.coupon.Coupon;
import com.trendyol.model.Category;
import com.trendyol.model.Product;
import com.trendyol.shoppingcart.ShoppingCart;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Category foodCategory = new Category("food");
        Category clothingCategory = new Category("clothing");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        Product shoes = new Product("Shoes", 230.0, clothingCategory);
        Product shirt = new Product("Shirt", 170.0, clothingCategory);
        Product hat = new Product("Hat", 50.0, clothingCategory);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);
        cart.addItem(shoes,1);
        cart.addItem(shirt,3);
        cart.addItem(hat,2);

        Campaign campaign1 = new Campaign(foodCategory, 20.0, 2, DiscountType.RATE);
        Campaign campaign2 = new Campaign(foodCategory, 50.0, 3, DiscountType.RATE);
        Campaign campaign3 = new Campaign(foodCategory, 5.0, 5, DiscountType.AMOUNT);

        cart.applyDiscounts(Arrays.asList(campaign1, campaign2, campaign3));

        Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
        cart.applyCoupon(coupon);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorImpl(5.0, 2.5);
        deliveryCostCalculator.calculateFor(cart);

        cart.print();
    }
}
