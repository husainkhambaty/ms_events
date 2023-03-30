package io.thebuildingblocks.services.eventing.controller;

import io.thebuildingblocks.services.eventing.entity.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/events", produces = { APPLICATION_JSON_VALUE })
@Slf4j
public class EventController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "NewTopic";


    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok("Events is OK");
    }

    @PostMapping(value="/publish", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> publishMessage(@RequestBody Event event) {
        if (event.getMessage() != null) {
            // Sending the message
            kafkaTemplate.send(TOPIC, event.getMessage());
            log.info("message: " + event.getMessage());
            return ResponseEntity.ok("Published Successfully");
        } else {
            log.error("message is null");
            return new ResponseEntity<>("Message is empty", HttpStatus.BAD_REQUEST);
        }


    }

}
