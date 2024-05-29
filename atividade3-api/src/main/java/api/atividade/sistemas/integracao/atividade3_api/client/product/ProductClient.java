package api.atividade.sistemas.integracao.atividade3_api.client.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {
    private static ProductClient instance;
    private final String url;
    private String token;
    private final RestTemplate restTemplate;

    private ProductClient() {
        this.url = "http://localhost:3005/";
        this.restTemplate = new RestTemplate();
    }

    public static synchronized ProductClient getInstance() {
        if (instance == null) {
            instance = new ProductClient();
        }
        return instance;
    }

    public void authenticationProcess(String email, String password) {
        String authUrl = String.format("%sauth/login?email=%s&password=%s", url, email, password);
        ResponseEntity<Map> response = restTemplate.getForEntity(authUrl, Map.class);
        this.token = (String) response.getBody().get("token");
    }

    public void destroyer(int id) {
        String destroyUrl = String.format("%sproducts?id=%d", url, id);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(destroyUrl, HttpMethod.DELETE, entity, String.class);
    }

    public ResponseEntity<List> products() {
        String productsUrl = url + "products";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(productsUrl, HttpMethod.GET, entity, List.class);
    }

    public ResponseEntity<String> registerProducts(String email, String password, List<Map<String, Object>> products) {
        String registerUrl = url + "register-products";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", this.token);

        Map<String, Object> payload = new HashMap<>();
        payload.put("user", email);
        payload.put("password", password);
        payload.put("products", products);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
        return restTemplate.exchange(registerUrl, HttpMethod.POST, entity, String.class);
    }
}