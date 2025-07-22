package app.api.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/content")
@CrossOrigin(origins = "*")
public class HealthController
{

    @GetMapping("/HealthStatus")
    public ResponseEntity<Map<String, String>> getHealth() {
        return ResponseEntity.ok().body(Map.of("status", "UP"));
    }
}
