package query;

import org.axonframework.eventsourcing.EventSourcingHandler;

import org.springframework.stereotype.Component;


import event.MandateCreatedEvent;


import java.util.ArrayList;



@Component
public class MandateProjection { //hier werden die Customerdaten gespeichert

    private final ArrayList <String> storage = new ArrayList<>(); //hier sind die Customer nun gespeichert

    @EventSourcingHandler
    public void on(MandateCreatedEvent event) {
        storage.add(event.getId());
    } //Daten können abgefragt werden und Daten können geändert werden von der UI



}
