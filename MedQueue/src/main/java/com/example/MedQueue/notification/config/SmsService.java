package com.example.MedQueue.notification.config;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @Value("${twilio.phone.from}")
    private String FROM_NUMBER;

    @PostConstruct
    public void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendQueueAlert(String toPhone, int position) {
        String body = "You're number " + position + " in the queue. Get ready!";
        Message.creator(new PhoneNumber(toPhone), new PhoneNumber(FROM_NUMBER), body).create();
    }
}
