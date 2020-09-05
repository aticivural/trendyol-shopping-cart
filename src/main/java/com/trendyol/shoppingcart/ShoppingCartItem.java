package com.trendyol.shoppingcart;

import com.trendyol.model.Category;
import com.trendyol.model.Product;

public class ShoppingCartItem {

    private Product product;
    private int quantity;

    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return product.getCategory();
    }

    public double getPrice() {
        return product.getPrice();
    }
}
