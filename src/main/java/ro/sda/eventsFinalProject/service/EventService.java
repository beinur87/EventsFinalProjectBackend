package ro.sda.eventsFinalProject.service;

import org.springframework.stereotype.Service;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

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

    public Event readEvent(Integer id) {
        if (id==null){
            throw new IllegalArgumentException("Event ID must not be null!");
        }
        Event event=eventRepository.findById(id).orElse(null);
        if (event==null) {
            throw new IllegalArgumentException("There is no event with id: " + id);
        }
        return event;
        }

        public List<Event> readAllEvents(){

        List<Event> events = new ArrayList<>();
        return eventRepository.findAll();

    }

    public Event updateEvent(Event updatedEvent){

        // Aici verificam ca exista un event cu id-ul dat!
        Event eventToUpdate = readEvent(updatedEvent.getId());
        //Aici salvam(cu toate verificarile de rigoare) evenimentul din baza de date
        return eventRepository.save(updatedEvent);
    }

    public void deleteEvent(Integer eventId){

        Event eventToDelete = readEvent(eventId);
        eventRepository.delete(eventToDelete);
    }

}
