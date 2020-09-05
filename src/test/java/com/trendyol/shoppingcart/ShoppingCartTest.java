package com.trendyol.shoppingcart;

import com.trendyol.discount.DiscountType;
import com.trendyol.discount.campaign.Campaign;
import com.trendyol.discount.coupon.Coupon;
import com.trendyol.model.Category;
import com.trendyol.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ShoppingCartTest {

    ShoppingCart cart;
    Category foodCategory;
    Category bookCategory;
    public void setup() {

        foodCategory = new Category("food");
        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        bookCategory = new Category("book");
        Product javaBook = new Product("Java8 in Action", 50, bookCategory);

        cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);
        cart.addItem(javaBook,1);

        Campaign campaign1 = new Campaign(foodCategory, 20.0, 2, DiscountType.RATE);
        Campaign campaign2 = new Campaign(foodCategory, 50.0, 3, DiscountType.RATE);
        Campaign campaign3 = new Campaign(foodCategory, 5.0, 5, DiscountType.AMOUNT);
        Campaign campaign4 = new Campaign(bookCategory, 5.0, 1, DiscountType.AMOUNT);

        cart.applyDiscounts(Arrays.asList(campaign1, campaign2, campaign3,campaign4));

        Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
        cart.applyCoupon(coupon);

    }

    @Test
    public void should_calculate_distinct_categories(){
        setup();
        List<Category> distinctCategories = cart.getDistinctCategories();
        Assert.assertEquals(2, distinctCategories.size());
    }

    @Test
    public void should_calculate_distinct_products(){
        setup();
        List<Product> distinctProducts = cart.getDistinctProducts();
        Assert.assertEquals(3, distinctProducts.size());
    }

    @Test
    public void should_calculate_total_price_in_category(){
        setup();
        double categoryPrice = cart.getTotalPriceInCategory(foodCategory);
        Assert.assertEquals(450.0, categoryPrice,2);
    }

    @Test
    public void should_calculate_product_count_in_category(){
        setup();
        int count = cart.getProductCountInCategory(foodCategory);
        Assert.assertEquals(4, count);
    }


    @Test
    public void should_calculate_products_gropu_by_category(){
        setup();
        Map<Category, List<ShoppingCartItem>> productsGroupByCategory = cart.getProductsGroupByCategory();
        List<ShoppingCartItem> foods = productsGroupByCategory.get(foodCategory);
        Assert.assertEquals(2, foods.size() );
    }

    @Test
    public void should_return_zero_value_when_not_exist_available_campaign_in_category(){
        setup();
        double discount = cart.findMaxDiscountInCategory(new Category("technology"));
        Assert.assertEquals(0.0,discount,2);
    }

    @Test
    public void should_return_max_discount_when_exist_available_campaigns_in_category(){
        setup();
        double discount = cart.findMaxDiscountInCategory(foodCategory);
        Assert.assertEquals(225.0,discount,2);
    }


    @Test
    public void should_apply_max_discount_when_applied_multiple_campaigns_in_category(){
        Category foodCategory = new Category("food");
        Product apple = new Product("Apple", 100.0, foodCategory);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);

        Campaign campaign1 = new Campaign(foodCategory, 20.0, 2, DiscountType.RATE);
        Campaign campaign2 = new Campaign(foodCategory, 50.0, 3, DiscountType.AMOUNT);

        cart.applyDiscounts(Arrays.asList(campaign1, campaign2));
        Assert.assertEquals(60.0, cart.getCampaignDiscount(), 2);

    }

    @Test
    public void should_apply_first_campaign_discounts_then_apply_available_coupon(){
        Category foodCategory = new Category("food");
        Product apple = new Product("Apple", 100.0, foodCategory);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);

        Campaign campaign1 = new Campaign(foodCategory, 20.0, 2, DiscountType.RATE);
        Campaign campaign2 = new Campaign(foodCategory, 50.0, 3, DiscountType.AMOUNT);

        cart.applyDiscounts(Arrays.asList(campaign1, campaign2));
        double totalAmountAfterCampaignDiscounts = cart.getTotalAmountAfterDiscounts();
        cart.applyCoupon(new Coupon(150,50,DiscountType.AMOUNT));
        double totalAmountAfterDiscounts = cart.getTotalAmountAfterDiscounts();

        Assert.assertEquals(60.0, cart.getCampaignDiscount(), 2);
        Assert.assertEquals(240.0, totalAmountAfterCampaignDiscounts,2);
        Assert.assertEquals(190.0, totalAmountAfterDiscounts,2);
    }

    @Test
    public void should_apply_first_campaign_discounts_then_apply_unavailable_coupon(){
        Category foodCategory = new Category("food");
        Product apple = new Product("Apple", 100.0, foodCategory);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);

        Campaign campaign1 = new Campaign(foodCategory, 20.0, 2, DiscountType.RATE);
        Campaign campaign2 = new Campaign(foodCategory, 50.0, 3, DiscountType.AMOUNT);

        cart.applyDiscounts(Arrays.asList(campaign1, campaign2));
        double totalAmountAfterCampaignDiscounts = cart.getTotalAmountAfterDiscounts();
        cart.applyCoupon(new Coupon(250,50,DiscountType.AMOUNT));
        double totalAmountAfterDiscounts = cart.getTotalAmountAfterDiscounts();

        Assert.assertEquals(60.0, cart.getCampaignDiscount(), 2);
        Assert.assertEquals(240.0, totalAmountAfterCampaignDiscounts,2);
        Assert.assertEquals(240.0, totalAmountAfterDiscounts,2);
    }

    @Test
    public void should_apply_only_coupon_when_not_exist_available_campaign(){
        Category foodCategory = new Category("food");
        Category bookCategory = new Category("book");
        Product apple = new Product("Apple", 100.0, foodCategory);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);

        Campaign campaign1 = new Campaign(bookCategory, 20.0, 2, DiscountType.RATE);
        Campaign campaign2 = new Campaign(bookCategory, 50.0, 3, DiscountType.AMOUNT);

        cart.applyDiscounts(Arrays.asList(campaign1, campaign2));
        double totalAmountAfterCampaignDiscounts = cart.getTotalAmountAfterDiscounts();
        cart.applyCoupon(new Coupon(250,50,DiscountType.AMOUNT));
        double totalAmountAfterDiscounts = cart.getTotalAmountAfterDiscounts();

        Assert.assertEquals(0.0, cart.getCampaignDiscount(), 2);
        Assert.assertEquals(300.0, totalAmountAfterCampaignDiscounts,2);
        Assert.assertEquals(250.0, totalAmountAfterDiscounts,2);
    }

    @Test
    public void should_apply_all_discounts_successfully(){
        setup();
        Assert.assertEquals(230.0, cart.getCampaignDiscount(),2);
        Assert.assertEquals(27.0, cart.getCouponDiscount(),2);
        Assert.assertEquals(243.0, cart.getTotalAmountAfterDiscounts(),2);
    }

}