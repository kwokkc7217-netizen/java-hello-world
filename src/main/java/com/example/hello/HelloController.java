package com.example.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST API controller — 呢個就係 job ad 入面講嘅 "Web Service API"。
 *
 * 試下：
 *   curl http://localhost:8080/api/hello
 *   curl "http://localhost:8080/api/hello?name=tom"
 */
@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public Map<String, String> hello(@RequestParam(defaultValue = "World") String name) {
        // Spring Boot 會自動將呢個 Map 轉成 JSON 回傳
        return Map.of("message", "Hello, " + name + "! [v2]");
    }
}
