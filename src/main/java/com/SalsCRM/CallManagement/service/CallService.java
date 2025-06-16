package com.SalsCRM.CallManagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

import java.net.URI;

@Service
public class CallService {

    @Value("${twilio.fromNumber}")
    private String fromNumber;

    public String initiateCall(String toNumber) {
        Call call = Call.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(fromNumber),
                URI.create("http://demo.twilio.com/docs/voice.xml") // Simple demo XML
        ).create();

        return call.getSid();
    }
}