package com.sidhu.cloud.api.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/authFallback")
    public String authFallback() {
        return "Authentication service is down. Try again later...";
    }

    @GetMapping("/usersFallback")
    public String usersFallback() {
        return "Users service is down. Try again later...";
    }

    @GetMapping("/accountsFallback")
    public String accountsFallback() {
        return "Accounts service is down. Try again later...";
    }

    @GetMapping("/transactionsFallback")
    public String transactionsFallback() {
        return "Transactions  service is down. Try again later...";
    }
}
