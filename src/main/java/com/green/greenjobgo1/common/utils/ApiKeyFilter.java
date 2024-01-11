package com.green.greenjobgo1.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ApiKeyFilter {

    private Map<String, LocalDateTime> apiKeys = new HashMap<>();

    public String generateApiKey() {
        String apiKey = UUID.randomUUID().toString();
        LocalDateTime expirationDate = LocalDateTime.now().plus(7, ChronoUnit.DAYS); // 7일 후 만료
        apiKeys.put(apiKey, expirationDate);
        return apiKey;
    }

    public boolean isValidApiKey(String apiKey) {
        if (apiKeys.containsKey(apiKey) && LocalDateTime.now().isBefore(apiKeys.get(apiKey))) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        ApiKeyFilter apiKeyManager = new ApiKeyFilter();

        // API 키 생성
        String apiKey = apiKeyManager.generateApiKey();
        System.out.println("Generated API Key: " + apiKey);

        // API 키의 유효성 확인
        if (apiKeyManager.isValidApiKey(apiKey)) {
            System.out.println("API를 사용할 수 있습니다");
        } else {
            System.out.println("API를 사용할 수 없습니다.");
        }
    }
}