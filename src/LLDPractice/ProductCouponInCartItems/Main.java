package LLDPractice.ProductCouponInCartItems;

public class Main {
  public static void main(String[] args) {
    Product item1 = new Product("FAN", 1000, ProductType.ELECTRONICS);
    Product item2 = new Product("Dalia", 1000, ProductType.FOOD);
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addProduct(item1);
    shoppingCart.addProduct(item2);

    int finalPrice = shoppingCart.getDiscountedPrice();

  }
}
