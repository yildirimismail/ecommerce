package entity;

import java.util.ArrayList;
import java.util.List;


public class CostCalculator {

    private Double costPerDelivery;
    private Double costPerProduct;
    private Double fixedCost;

    public CostCalculator(Double costPerDelivery, Double costPerProduct, Double fixedCost) {
        this.setCostPerDelivery(costPerDelivery);
        this.setCostPerProduct(costPerProduct);
        this.setFixedCost(fixedCost);
    }

    public double calculate(ShoppingCart cart) {
        List<Product> products = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        try {
            for (Product product : cart.getProductList().keySet()) {
                if (!products.contains(product)) {
                    products.add(product);
                    if (product.getCategory().getParentCategory() != null) {
                        if (!categories.contains(product.getCategory().getParentCategory())) {
                            categories.add(product.getCategory().getParentCategory());
                        }
                    } else {
                        if (!categories.contains(product.getCategory())) {
                            categories.add(product.getCategory());
                        }
                    }
                }
            }
            return (costPerDelivery * categories.size()) + (costPerProduct * products.size()) + fixedCost;
        }catch (Exception e){
            throw new NullPointerException();
        }
    }

    public Double getCostPerDelivery() {
        return costPerDelivery;
    }

    public void setCostPerDelivery(Double costPerDelivery) {
        this.costPerDelivery = costPerDelivery;
    }

    public Double getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(Double costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public Double getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(Double fixedCost) {
        this.fixedCost = fixedCost;
    }
}
