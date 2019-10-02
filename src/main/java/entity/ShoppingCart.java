package entity;

import discount.Discount;
import discount.DiscountFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private Double totalAmountAfterDiscounts;
    private double couponDiscount;
    private double campaignDiscount;
    private double deliveryCost;
    private double totalPrice;
    private Map<Product, Integer> productList;
    private ShoppingCart shoppingCart;


    public void addItem(Product product, Integer quantity) {
        if (productList == null) {
            productList = new HashMap<>();
        }
        productList.put(product, quantity);
    }

    public void appylyDiscounts(List<Campaign> campaignList) {
        Map<Category, Integer> boughtMultiple = new HashMap<>();
        double totalAmount = 0d;
        if (productList != null && productList.size() > 0) {
            for (Product product : productList.keySet()) {
                totalAmount += product.getPrice() * productList.get(product);
                if (product.getCategory().getParentCategory() != null) {
                    if (boughtMultiple.containsKey(product.getCategory().getParentCategory())) {
                        boughtMultiple.put(product.getCategory().getParentCategory(), productList.get(product) + boughtMultiple.get(product.getCategory().getParentCategory()));
                    } else {
                        boughtMultiple.put(product.getCategory().getParentCategory(), productList.get(product));
                    }
                } else {
                    if (boughtMultiple.containsKey(product.getCategory())) {
                        boughtMultiple.put(product.getCategory(), productList.get(product) + boughtMultiple.get(product.getCategory()));
                    } else {
                        boughtMultiple.put(product.getCategory(), productList.get(product));
                    }
                }
            }
            totalAmountAfterDiscounts = 0d;

            totalPrice = totalAmount;

            for (Campaign campaign : campaignList) {
                for (Product product : productList.keySet()) {
                    if (product.getCategory().getTitle().equalsIgnoreCase(campaign.getCategory().getTitle()) || (product.getCategory().getParentCategory() != null && product.getCategory().getParentCategory().getTitle().equalsIgnoreCase(campaign.getCategory().getTitle())))
                        if (boughtMultiple.get(product.getCategory()) > campaign.getQuantity()) {
                            try {
                                Discount discount = DiscountFactory.getDiscountType(campaign.getDiscountType().getText());
                                if (discount != null) {
                                    product.setPrice(discount.makeDiscount(product.getPrice(), campaign.getDiscountRateOrAmount()));
                                } else {
                                    throw new Exception();
                                }
                            } catch (Exception e) {
                                // Return Some Response Discount Type Couldn't Find
                            }
                        }
                }
            }
            for (Product product : productList.keySet()) {
                totalAmountAfterDiscounts += product.getPrice() * productList.get(product);
            }
            campaignDiscount = totalAmount - totalAmountAfterDiscounts;
        }
    }

    public boolean applyCoupon(Coupon coupon) {
        if (totalAmountAfterDiscounts != null && totalAmountAfterDiscounts > 0d) {

            double totalAmount = totalAmountAfterDiscounts;
            if (totalAmountAfterDiscounts > coupon.getMinAmount()) {
                totalAmountAfterDiscounts = totalAmountAfterDiscounts * coupon.getDiscountRate() / 100;
                couponDiscount = totalAmount - totalAmountAfterDiscounts;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void calculateFinalDeliveryCost(double costPerDelivery, double costPerProduct, double fixedCost) {
        CostCalculator costCalculator = new CostCalculator(costPerDelivery, costPerProduct, fixedCost);
        deliveryCost = costCalculator.calculate(shoppingCart);
    }

    public void print() {
        System.out.println("Total Amount Before Discounts : " + totalPrice);
        System.out.println("Total Amount After Discounts : " + totalAmountAfterDiscounts);
        System.out.println("Delivery Cost : " + deliveryCost);
    }

    public Double getTotalAmountAfterDiscounts() {
        return totalAmountAfterDiscounts;
    }

    public void setTotalAmountAfterDiscounts(Double totalAmountAfterDiscounts) {
        this.totalAmountAfterDiscounts = totalAmountAfterDiscounts;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public void setCampaignDiscount(double campaignDiscount) {
        this.campaignDiscount = campaignDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public Map<Product, Integer> getProductList() {
        return productList;
    }

    public void setProductList(Map<Product, Integer> productList) {
        this.productList = productList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
