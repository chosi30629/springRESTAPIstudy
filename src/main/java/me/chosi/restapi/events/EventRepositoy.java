package me.chosi.restapi.events;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepositoy extends JpaRepository<Event, Integer> {
}
