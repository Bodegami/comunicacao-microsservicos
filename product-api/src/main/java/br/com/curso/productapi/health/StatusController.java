package br.com.curso.productapi.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StatusController {

    @Value("${spring.application.name}")
    private String name;


    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getApiStatus() {
        Map<String, Object> response = new HashMap<>();

        response.put("service", name);
        response.put("status", "up");
        response.put("httpStatus", HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }


}
