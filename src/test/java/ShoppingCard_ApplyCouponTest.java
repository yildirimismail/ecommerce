import entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DiscountType;
import util.DiscountTypeView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCard_ApplyCouponTest {
    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void addCouponDiscountCalculation() {
        Integer quantity = 3;
        Category category = new Category("fruit", null);
        Product product = new Product("apple", 30.0, category);
        shoppingCart.addItem(product, quantity);
        Assert.assertEquals(quantity, shoppingCart.getProductList().get(product));
        Coupon coupon = new Coupon(DiscountType.RATE, 70, 10);
        shoppingCart.setTotalAmountAfterDiscounts(product.getPrice() * quantity);
        shoppingCart.applyCoupon(coupon);
        Double expectedValue = (product.getPrice() * quantity) * coupon.getDiscountRate() / 100;
        Assert.assertEquals(expectedValue, shoppingCart.getTotalAmountAfterDiscounts());
    }

    @Test
    public void addCouponDiscountCalculationIfApplicable() {
        Integer quantity = 3;
        Category category = new Category("fruit", null);
        Product product = new Product("apple", 30.0, category);
        shoppingCart.addItem(product, quantity);
        Assert.assertEquals(quantity, shoppingCart.getProductList().get(product));
        Coupon coupon = new Coupon(DiscountType.RATE, 70, 10);
        shoppingCart.setTotalAmountAfterDiscounts(product.getPrice() * quantity);
        ;
        Assert.assertTrue(shoppingCart.applyCoupon(coupon));
    }

    @Test
    public void addCouponDiscountCalculationIfNotApplicable() {
        Integer quantity = 3;
        Category category = new Category("fruit", null);
        Product product = new Product("apple", 30.0, category);
        shoppingCart.addItem(product, quantity);
        Assert.assertEquals(quantity, shoppingCart.getProductList().get(product));
        Coupon coupon = new Coupon(DiscountType.RATE, 120, 10);
        shoppingCart.setTotalAmountAfterDiscounts(product.getPrice() * quantity);
        ;
        Assert.assertFalse(shoppingCart.applyCoupon(coupon));
    }

    @Test
    public void testtes() {
        Integer quantity = 3;
        Category category = new Category("fruit", null);
        Category categoryTest = new Category("test", null);
        Product product = new Product("apple", 30.0, category);
        Product product2 = new Product("pine", 30.0, categoryTest);
        shoppingCart.addItem(product, quantity);
        shoppingCart.addItem(product2, quantity);
        Assert.assertEquals(quantity, shoppingCart.getProductList().get(product));
        Campaign campaign = new Campaign(category, DiscountTypeView.AMOUNT, 10.0, 5);
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign);
        shoppingCart.appylyDiscounts(campaigns);
    }
}
