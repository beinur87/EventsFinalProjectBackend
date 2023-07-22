package ro.sda.eventsFinalProject.service;

import org.springframework.stereotype.Service;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.repository.EventRepository;

@Service

public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event saveEvent(Event event) {
        if (event.getName() == null) {
            // Avoids the generation of NullPointerException for null event names !
            throw new IllegalArgumentException("An event must have a name!");
        }
        if (event.getStartDate() == null || event.getEndDate()==null || event.getStartDate().isAfter(event.getEndDate())) {
            // Avoids the generation of NullPointerException for null dates !
            throw new IllegalArgumentException("Event start date is after its end date. Please be careful!");
        }
        Event savedEvent = eventRepository.save(event);
        return savedEvent;
    }
}
