package org.hbrs.se2.project.hellocar.services.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    @Value("${kiconnect.api.key}")
    private String apiKey;

    @Value("${kiconnect.api.url}")
    private String apiUrl;

    private RestTemplate restTemplate = new RestTemplate();

    public String sendMessageAndGetResponse(List<Map<String, String>> chatHistory) {
        try {
            HttpHeaders header = buildHeader();

            String payload = buildPayload(chatHistory);

            HttpEntity<String> request = new HttpEntity<>(payload, header);

            return extractContent(restTemplate.postForObject(apiUrl, request, String.class));

        } catch(Exception e) {
            throw new RuntimeException("Fehler beim Request", e);
        }
    }

    private HttpHeaders buildHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        return headers;
    }


    private String buildPayload(List<Map<String, String>> chatHistory) {
        try {
            Map<String, Object> payload = Map.of(
                    "model", "LLAMA 3.1 8B",
                    "messages", chatHistory
            );

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(payload);

        } catch (Exception e) {
            throw new RuntimeException("Payload konnte nicht gebaut werden: " + e.getMessage());
        }
    }

    private String extractContent(String response){

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            return root.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e){
            throw new RuntimeException("Response konnte nicht gebaut werden: " + e.getMessage());
        }
    }
}
