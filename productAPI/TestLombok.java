import org.moviecrudspring.productapi.Models.Product;
import java.math.BigDecimal;

public class TestLombok {
    public static void main(String[] args) {
        // Test @Builder annotation
        Product product = Product.builder()
            .name("Test Product")
            .description("Test Description")
            .price(new BigDecimal("29.99"))
            .build();
        
        // Test @Data annotation (getter methods)
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Description: " + product.getDescription());
        System.out.println("Product Price: " + product.getPrice());
        
        // Test @Data annotation (setter methods)
        product.setName("Updated Product");
        System.out.println("Updated Name: " + product.getName());
        
        // Test @AllArgsConstructor
        Product product2 = new Product("1", "Product 2", "Description 2", new BigDecimal("39.99"));
        System.out.println("Product 2 Name: " + product2.getName());
        
        // Test @NoArgsConstructor
        Product product3 = new Product();
        product3.setName("Product 3");
        System.out.println("Product 3 Name: " + product3.getName());
    }
}
