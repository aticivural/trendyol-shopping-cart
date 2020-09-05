package com.trendyol.delivery;

import com.trendyol.shoppingcart.ShoppingCart;

public interface DeliveryCostCalculator {
    double calculateFor(ShoppingCart cart);
}
