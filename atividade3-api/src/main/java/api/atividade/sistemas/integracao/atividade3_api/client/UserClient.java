package api.atividade.sistemas.integracao.atividade3_api.client;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserClient {
    private static UserClient instance;
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:3004";
    private final int timeout = 5000; // Usado apenas para referência; não utilizado diretamente no RestTemplate

    private UserClient() {
        this.restTemplate = new RestTemplate();
    }

    public static synchronized UserClient getInstance() {
        if (instance == null) {
            instance = new UserClient();
        }
        return instance;
    }

    public Map<String, Object> find(String name) {
        String queryString = (name != null && !name.isEmpty()) ? "?name=" + name : "";
        String url = baseUrl + "/users" + queryString;

        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        List<Map<String, Object>> users = response.getBody();

        Map<String, Object> result = new HashMap<>();
        if (users == null || users.isEmpty()) {
            result.put("msg", "No users found");
        } else {
            result.put("msg", "User found");
            users.forEach(user -> {
                user.remove("createdAt");
                user.remove("updatedAt");
            });
            result.put("users", users);
        }
        return result;
    }
}