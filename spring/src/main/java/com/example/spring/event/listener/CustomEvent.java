package com.example.spring.event.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class CustomEvent extends ApplicationEvent {
    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
