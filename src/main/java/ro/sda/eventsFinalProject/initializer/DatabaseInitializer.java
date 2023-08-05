package ro.sda.eventsFinalProject.initializer;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.service.EventService;

import java.time.LocalDateTime;

@Component
public class DatabaseInitializer {

    private final EventService eventService;

    public DatabaseInitializer(EventService eventService) {
        this.eventService = eventService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDataBase(){

        Event e1 = new Event (null,
                "UNTOLD",
                LocalDateTime.of(2023, 8, 11, 20 ,0,0),
                LocalDateTime.of(2023,8, 14, 23, 59, 00),
                "Festival de muzica",
                "Cluj-Napoca",
                "https://s.inyourpocket.com/img/text/romania/cluj-napoca/top-10-events-cluj-napoca.jpg"
               );

        Event e2 = new Event (null,
                "Electric Castle",
                LocalDateTime.of(2023, 9, 11, 20 ,0,0),
                LocalDateTime.of(2023,9, 14, 23, 59, 00),
                "Festival de muzica",
                "Cluj-Napoca",
                "https://cdn.pixabay.com/photo/2019/08/13/08/48/bucuresti-4402855_1280.jpg"
        );


        eventService.saveEvent(e1);
        eventService.saveEvent(e2);

    }

}
