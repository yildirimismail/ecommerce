import entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DiscountType;
import util.DiscountTypeView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCard_ApplyCampaignTest {
    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void applyCouponIfApplicable() {
        Integer quantity = 3;
        Category category = new Category("fruit", null);
        Category categoryTest = new Category("test", null);
        Product product = new Product("apple", 30.0, category);
        Product product2 = new Product("pine", 30.0, categoryTest);
        shoppingCart.addItem(product, quantity);
        shoppingCart.addItem(product2, quantity);
        Assert.assertEquals(quantity, shoppingCart.getProductList().get(product));
        Campaign campaign = new Campaign(category, DiscountTypeView.AMOUNT, 10.0, 2);
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign);
        shoppingCart.appylyDiscounts(campaigns);
        Double actualValue = shoppingCart.getCampaignDiscount();
        Double exceptedValue = shoppingCart.getProductList().get(product) * campaign.getDiscountRateOrAmount();
        Assert.assertEquals(exceptedValue, actualValue);
    }

    @Test
    public void applyCouponIfNotApplicable() {
        Integer quantity = 3;
        Category category = new Category("fruit", null);
        Category categoryTest = new Category("test", null);
        Product product = new Product("apple", 30.0, category);
        Product product2 = new Product("pine", 30.0, categoryTest);
        shoppingCart.addItem(product, quantity);
        shoppingCart.addItem(product2, quantity);
        Assert.assertEquals(quantity, shoppingCart.getProductList().get(product));
        // min bought count not achieved
        Campaign campaign = new Campaign(category, DiscountTypeView.AMOUNT, 10.0, 5);
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign);
        shoppingCart.appylyDiscounts(campaigns);
        Double actualValue = shoppingCart.getCampaignDiscount();
        Double exceptedValue = shoppingCart.getProductList().get(product) > campaign.getQuantity() ? shoppingCart.getProductList().get(product) * campaign.getDiscountRateOrAmount() : 0d;
        Assert.assertEquals(exceptedValue, actualValue);
    }

    @Test
    public void applyCouponIfThereIsNoCategory() {
        Integer quantity = 3;
        Category category = new Category("fruit", null);
        Category categoryNone = new Category("none", null);
        Category categoryTest = new Category("test", null);
        Product product = new Product("apple", 30.0, category);
        Product product2 = new Product("pine", 30.0, categoryTest);
        shoppingCart.addItem(product, quantity);
        shoppingCart.addItem(product2, quantity);
        Assert.assertEquals(quantity, shoppingCart.getProductList().get(product));
        // min bought count not achieved
        Campaign campaign = new Campaign(categoryNone, DiscountTypeView.AMOUNT, 10.0, 5);
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign);
        shoppingCart.appylyDiscounts(campaigns);
        Double actualValue = shoppingCart.getCampaignDiscount();
        Double exceptedValue = 0d;
        Assert.assertEquals(exceptedValue, actualValue);
    }
}
