package api.atividade.sistemas.integracao.atividade3_api.controller;

import api.atividade.sistemas.integracao.atividade3_api.client.UserClient;
import api.atividade.sistemas.integracao.atividade3_api.client.product.ProductClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AppController {
    private final ProductClient productClient;
    private final UserClient userClient;

    public AppController() {
        this.productClient = ProductClient.getInstance();
        this.userClient = UserClient.getInstance();
    }

    @GetMapping("/access_products")
    public List<Map<String, Object>> accessProduct(@RequestParam String name) {
        Map<String, String> userInfo = getUserInformation(name);

        productClient.authenticationProcess(userInfo.get("email"), userInfo.get("password"));

        return (List<Map<String, Object>>) productClient.products().getBody();
    }

    @DeleteMapping("/delete_product_price200")
    public List<Map<String, Object>> deleteProduct(@RequestParam String name) {
        Map<String, String> userInfo = getUserInformation(name);

        productClient.authenticationProcess(userInfo.get("email"), userInfo.get("password"));

        List<Map<String, Object>> products = (List<Map<String, Object>>) productClient.products().getBody();

        List<Map<String, Object>> productsFiltered = products.stream()
                .filter(e -> "200.00".equals(e.get("price").toString()))
                .collect(Collectors.toList());

        return productsFiltered.stream()
                .map(product -> {
                    productClient.destroyer((Integer) product.get("id"));
                    return product;
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/products")
    public ResponseEntity<String> registerAndGetProducts(@RequestParam String name) {
        Map<String, String> userInfo = getUserInformation(name);

        productClient.authenticationProcess(userInfo.get("email"), userInfo.get("password"));

        // Example products to be registered
        List<Map<String, Object>> products = List.of(
                Map.of("name", "Produto 7", "description", "Description 7", "company", "Company 7", "price", 10.00, "amount", 4),
                Map.of("name", "Produto 8", "description", "Description 8", "company", "Company 8", "price", 10.00, "amount", 4),
                Map.of("name", "Produto 9", "description", "Description 9", "company", "Company 9", "price", 10.00, "amount", 4)
        );

        ResponseEntity<String> response = productClient.registerProducts(userInfo.get("email"), userInfo.get("password"), products);

        return response;
    }

    @GetMapping("/products")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    private Map<String, String> getUserInformation(String name) {
        Map<String, Object> resultOfUserClient = userClient.find(name);

        if (!resultOfUserClient.containsKey("users") || ((List) resultOfUserClient.get("users")).isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Map<String, Object> user = (Map<String, Object>) ((List) resultOfUserClient.get("users")).get(0);
        return Map.of(
                "email", user.get("email").toString(),
                "password", user.get("password").toString()
        );
    }
}