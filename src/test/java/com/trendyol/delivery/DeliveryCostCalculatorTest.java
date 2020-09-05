package com.trendyol.delivery;

import com.trendyol.model.Category;
import com.trendyol.model.Product;
import com.trendyol.shoppingcart.ShoppingCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeliveryCostCalculatorTest {

    ShoppingCart cart;

    @Before
    public void setUp() throws Exception {
        Category foodCategory = new Category("food");
        Product apple = new Product("Apple", 100.0, foodCategory);

        cart = new ShoppingCart();
        cart.addItem(apple, 3);

    }

    @Test
    public void should_calculate_delivery_cost_with_default_fixed_cost() {
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorImpl(3.0, 4.0);
        double deliveryCost = deliveryCostCalculator.calculateFor(cart);

        Assert.assertEquals(9.99, deliveryCost, 2);
    }


    @Test
    public void should_calculate_delivery_cost_with_multiple_products_and_multiple_categories() {

        Product tv = new Product("tv", 900, new Category("technology"));
        cart.addItem(tv, 1);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorImpl(3.0, 4.0);
        double deliveryCost = deliveryCostCalculator.calculateFor(cart);

        Assert.assertEquals(16.99, deliveryCost, 2);
    }
}