package org.richard.keycloak.spi;

import org.keycloak.Config;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.OperationType;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 7/26/17.
 */
public class SysoutEventListenerProviderFactory implements EventListenerProviderFactory {
    private SysoutEventListenerProvider sysoutEventListenerProvider;

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return this.sysoutEventListenerProvider;
    }

    @Override
    public void init(Config.Scope config) {
        System.out.println("SysoutEventListenerProviderFactory::init");
        /*String[] excludes = config.getArray("excludes");
        /*if (excludes != null) {
            excludedEvents = new HashSet<>();
            for (String e : excludes) {
                excludedEvents.add(EventType.valueOf(e));
            }
        }*//*
        System.out.println("Excluded Events.....");
        if (excludes != null) {
            Arrays.stream(excludes)
                    .forEach(System.out::println);
        }

        String[] excludesOperations = config.getArray("excludesOperations");
        /*if (excludesOperations != null) {
            excludedAdminOperations = new HashSet<>();
            for (String e : excludesOperations) {
                excludedAdminOperations.add(OperationType.valueOf(e));
            }
        }*//*
        System.out.println("Excluded Operations.....");
        if (excludesOperations != null) {
            Arrays.stream(excludesOperations)
                    .forEach(System.out::println);
        }*/
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(EventListenerConfiguration.class);
        ctx.refresh();

        /*MyService myService = ctx.getBean(MyService.class);
        myService.doStuff();*/
        //return new SysoutEventListenerProvider(excludedEvents, excludedAdminOperations);
        sysoutEventListenerProvider = ctx.getBean(SysoutEventListenerProvider.class);
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        System.out.println("SysoutEventListenerProviderFactory::postInit");
    }

    @Override
    public void close() {
        System.out.println("SysoutEventListenerProviderFactory::close");
    }

    @Override
    public String getId() {
        return "sysout";
    }
}
