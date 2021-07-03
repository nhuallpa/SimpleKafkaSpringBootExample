package com.example.kafka.user.resource;

import com.example.kafka.user.services.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.function.EntityResponse;

@Controller
public class UserResource {

    @Autowired
    private Producer producer;

    @PostMapping(value="/users")
    public ResponseEntity<String> createUser(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

}
