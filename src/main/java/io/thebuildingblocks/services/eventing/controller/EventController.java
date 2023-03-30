package io.thebuildingblocks.services.eventing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/events", produces = { MediaType.APPLICATION_JSON_VALUE })
@Slf4j
public class EventController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "NewTopic1";


    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok("Events is OK");
    }

    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable("message") final String message) {

        // Sending the message
        kafkaTemplate.send(TOPIC, message);
        return "Published Successfully";
    }

}
