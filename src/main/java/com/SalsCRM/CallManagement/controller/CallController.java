package com.SalsCRM.CallManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SalsCRM.CallManagement.service.CallService;

@RestController
@RequestMapping("/api/call")
public class CallController {

    private final CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @PostMapping("/initiate")
    public ResponseEntity<String> initiateCall(@RequestParam String to) {
        String callSid = callService.initiateCall(to);
        return ResponseEntity.ok("Call initiated. Call SID: " + callSid);
    }
}