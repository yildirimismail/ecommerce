import entity.Category;
import entity.CostCalculator;
import entity.Product;
import entity.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

public class CostCalculator_CalculateTest {

    private CostCalculator costCalculator;
    private ShoppingCart shoppingCart;
    Double costPerDelivery;
    Double costPerProduct;
    Double fixedCost;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void calculateWithParentCategoryTest() {
        costPerDelivery = 1.1;
        costPerProduct = 1.3;
        fixedCost = 2.99;
        costCalculator = new CostCalculator(costPerDelivery, costPerProduct, fixedCost);
        addItemToCartWithoutParentCategory();
        double deliveryCost = costCalculator.calculate(shoppingCart);
        System.out.println(deliveryCost);
    }

    @Test
    public void calculateWithOutParentCategoryTest() {
        costPerDelivery = 1.3;
        costPerProduct = 2.5;
        fixedCost = 5.99;
        costCalculator = new CostCalculator(costPerDelivery, costPerProduct, fixedCost);
        addItemToCartWithParentCategory();
        double deliveryCost = costCalculator.calculate(shoppingCart);
        System.out.println(deliveryCost);
    }

    @Test(expected = NullPointerException.class)
    public void calculateExceptionTest() {
        costPerDelivery = 1.3;
        costPerProduct = 2.5;
        fixedCost = null;
        costCalculator = new CostCalculator(costPerDelivery, costPerProduct, fixedCost);
        addItemToCartWithParentCategory();
        costCalculator.calculate(shoppingCart);
    }


    private void addItemToCartWithoutParentCategory() {
        Category categoryFruit = new Category("fruit", null);
        Category categoryEq = new Category("electronic", null);

        int quantityApple = 3;
        int quantityMic = 5;
        Product productApple = new Product("apple", 30.0, categoryFruit);
        Product productMic = new Product("mic", 30.0, categoryEq);

        shoppingCart.addItem(productApple, quantityApple);
        shoppingCart.addItem(productMic, quantityMic);
    }

    private void addItemToCartWithParentCategory() {
        Category categoryFruit = new Category("fruit", null);
        Category parentCategory = new Category("electronic", null);
        Category categoryEq = new Category("pc-equipment", parentCategory);
        Category categoryTech = new Category("pc-equipment", parentCategory);

        int quantityApple = 3;
        int quantityMic = 5;
        Product productApple = new Product("apple", 30.0, categoryFruit);
        Product productMic = new Product("mic", 30.0, categoryEq);
        Product productBoard = new Product("board", 30.0, categoryTech);

        shoppingCart.addItem(productApple, quantityApple);
        shoppingCart.addItem(productMic, quantityMic);
        shoppingCart.addItem(productBoard, quantityMic);
    }
}
