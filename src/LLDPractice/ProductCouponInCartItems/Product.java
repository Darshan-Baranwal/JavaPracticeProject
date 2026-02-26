package LLDPractice.ProductCouponInCartItems;

public class Product {
  String name;
  int price;
  ProductType productType;

  public Product(String name, int price, ProductType productType) {
    this.name = name;
    this.price = price;
    this.productType = productType;
  }

  int getPrice(){
    return price;
  }
  public ProductType getProductType(){
    return productType;
  }
}
