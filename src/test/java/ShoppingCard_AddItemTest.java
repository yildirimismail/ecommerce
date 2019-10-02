import entity.Category;
import entity.Product;
import entity.ShoppingCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCard_AddItemTest {

    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void addProductCount() {
        Integer quantity = 3;
        Category category = new Category("fruit", null);
        Product product = new Product("apple", 30.0, category);
        shoppingCart.addItem(product, quantity);
        Assert.assertEquals(1, shoppingCart.getProductList().size());
        Integer productQuantity = shoppingCart.getProductList().get(product);
        Assert.assertEquals(quantity, productQuantity);
    }

    @Test
    public void addProductQuantity() {
        Integer quantity = 3;
        Category category = new Category("fruit", null);
        Product product = new Product("apple", 30.0, category);
        shoppingCart.addItem(product, quantity);
        Assert.assertEquals(quantity, shoppingCart.getProductList().get(product));
    }
}
