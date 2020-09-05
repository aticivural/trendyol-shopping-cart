package com.trendyol.shoppingcart;

import com.trendyol.delivery.DeliveryCostCalculator;
import com.trendyol.delivery.DeliveryCostCalculatorImpl;
import com.trendyol.model.Category;
import com.trendyol.model.Product;
import com.trendyol.discount.campaign.Campaign;
import com.trendyol.discount.coupon.Coupon;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class ShoppingCart {

    private List<ShoppingCartItem> items = new ArrayList<>();
    private Map<Category, List<Campaign>> campaignMap = new HashMap<>();
    private double totalPrice = 0.0;
    private double totalCampaignDiscounts = 0.0;
    private double couponDiscount = 0.0;
    private DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorImpl(3.0, 5.0);

    public void addItem(Product product, int quantity) {

        totalPrice += product.getPrice() * quantity;
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity);
        items.add(shoppingCartItem);
    }


    public void applyDiscounts(List<Campaign> campaigns) {

        campaignMap = campaigns.stream()
                .filter(campaign -> getProductCountInCategory(campaign.getCategory()) >= campaign.getMinItemCount())
                .collect(groupingBy(Campaign::getCategory));

        List<Category> distinctCategories = getDistinctCategories();

        totalCampaignDiscounts = distinctCategories.stream()
                .map(this::findMaxDiscountInCategory)
                .reduce(0.0, Double::sum);

    }

    public List<Category> getDistinctCategories() {
        return items.stream()
                .map(ShoppingCartItem::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Product> getDistinctProducts() {
        return items.stream()
                .map(ShoppingCartItem::getProduct)
                .distinct()
                .collect(Collectors.toList());
    }

    public double findMaxDiscountInCategory(Category category) {
        double totalPriceInCategory = getTotalPriceInCategory(category);

        List<Campaign> campaigns = campaignMap.get(category);
        if (campaigns == null) {
            return 0.0;
        }

        return campaigns.stream()
                .map(campaign -> campaign.calculate(totalPriceInCategory))
                .max(Comparator.comparingDouble(Double::valueOf))
                .get();
    }

    public double getTotalPriceInCategory(Category category) {
        return items.stream()
                .filter(item -> item.getCategory() == category)
                .map(item -> item.getQuantity() * item.getPrice())
                .reduce(0.0, Double::sum);
    }

    public int getProductCountInCategory(Category category) {
        return items.stream()
                .filter(item -> item.getCategory() == category)
                .map(ShoppingCartItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    public Map<Category, List<ShoppingCartItem>> getProductsGroupByCategory() {
        return items.stream()
                .collect(groupingBy(ShoppingCartItem::getCategory));
    }

    public void applyCoupon(Coupon coupon) {
        couponDiscount = coupon.calculate(totalPrice - totalCampaignDiscounts);
    }

    public double getCampaignDiscount() {
        return totalCampaignDiscounts;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public double getTotalAmountAfterDiscounts() {
        return totalPrice - totalCampaignDiscounts - couponDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCostCalculator.calculateFor(this);
    }


    // group products by category and print the CategoryName, ProductName, Quantity, UnitPrice, TotalPrice, TotalDiscount(coupon, campaign) applied
    public void print() {
        StringBuilder sb = new StringBuilder();
        Map<Category, List<ShoppingCartItem>> productsByGroup = getProductsGroupByCategory();
        for (Map.Entry<Category, List<ShoppingCartItem>> entry : productsByGroup.entrySet()) {
            for (ShoppingCartItem item : entry.getValue()) {
                String record = String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\n", entry.getKey().getTitle(), item.getProduct().getTitle(), item.getQuantity(), item.getPrice(), (item.getQuantity() * item.getPrice()));
                sb.append(record);
            }
        }
        sb.append("\n");
        sb.append("TotalPrice: " + totalPrice);
        sb.append("\n");
        sb.append("TotalDiscounts: " + (getCouponDiscount() + getCampaignDiscount()));
        sb.append("\n");
        sb.append("TotalAmount: " + getTotalAmountAfterDiscounts());
        sb.append("\n");
        sb.append("DeliveryCost: " + getDeliveryCost());
        System.out.println(sb.toString());
    }

}
