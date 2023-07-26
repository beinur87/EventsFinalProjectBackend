package ro.sda.eventsFinalProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.service.EventService;

import java.util.List;


@RestController
@Service

public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
                this.eventService = eventService;
    }

    @PostMapping("/events")
    public ResponseEntity createEvent(@RequestBody Event event) {
        if (event.getId()!=null) {
            return new ResponseEntity("Event id must be empty!", HttpStatus.BAD_REQUEST);
        }
        try {
            Event savedEvent = eventService.saveEvent(event);
            return new ResponseEntity(savedEvent, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/events/{id}")
    public ResponseEntity readEvent(@PathVariable(name = "id") Integer eventId) {
        try {
            Event readEvent = eventService.readEvent(eventId);
            return new ResponseEntity(readEvent, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/events")
    public ResponseEntity readAllEvents() {
        List<Event> events = eventService.readAllEvents();
        return new ResponseEntity(events, HttpStatus.OK);

    }

    @PutMapping("events/{id}")
    public ResponseEntity updateEvent(@PathVariable(name = "id") Integer pathId, @RequestBody Event eventToUpdate) {
        if (!pathId.equals(eventToUpdate.getId())) {
            return new ResponseEntity<>("Inconsistent id's!!!", HttpStatus.BAD_REQUEST);
        }
        try {
            Event updatedEvent = eventService.updateEvent(eventToUpdate);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            eventService.deleteEvent(id);
            return new ResponseEntity("Event deleted sucessfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

