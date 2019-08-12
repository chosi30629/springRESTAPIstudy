package me.chosi.restapi.events;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

//public class EventReource extends ResourceSupport {
public class EventReource extends Resource<Event> {
    public EventReource(Event event, Link... links) {
        super(event, links);
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }
    // 둘다 동일함
    /*
    @JsonUnwrapped
    private Event event;

    public EventReource(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
    */

}
