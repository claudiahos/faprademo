package query;


import org.axonframework.eventhandling.EventHandler;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import aggregates.CustomerApplicationStatus;
import command.CustomerEntity;
import event.ApplicationApprovedEvent;
import event.ApplicationRejectedEvent;
import event.CustomerCreatedEvent;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomerProjection {

    private final Map<String, CustomerEntity> storage = new LinkedHashMap<>();

    @EventHandler//1. Strang: Kundendaten anlegen
    public void on(CustomerCreatedEvent event) { //speichert die Kundendaten in Persistenzdatenbank
        storage.put(event.getCustomerId(), new CustomerEntity(event.getCustomerId(),
                event.getFirstName(), event.getLastName(), event.getAddress(), event.getCity(), event.getPostalCode(),
                event.getPhone(), event.getEmail(), event.getStatus(), event.getCreatedAt()));
    }
     
    
    @EventHandler //Kundendaten werden geprüft ->Status wird geändert ->
    public void on(ApplicationApprovedEvent event) {
        CustomerEntity app = storage.get(event.getApplicationFormId());
        app.setStatus(CustomerApplicationStatus.APPROVED); //muss das nicht im Aggreagt stattfinden??
        app.setUpdatedAt(event.getUpdatedAt());
    }

    @EventHandler
    public void on(ApplicationRejectedEvent event) {
        CustomerEntity app = storage.get(event.getApplicationFormId());
        app.setStatus(CustomerApplicationStatus.REJECTED);
        app.setUpdatedAt(event.getUpdatedAt());
    }
 

    @QueryHandler //Kundendaten werden abgefragt, zu welchem Zweck??
    public ApplicationResponse handle(ApplicationQuery query) {
        CustomerEntity app = storage.get(query.getApplicationFormId());
        return new ApplicationResponse(app.getCustomerId(), app.getFirstName(), app.getLastName(),
                app.getAddress(), app.getCity(), app.getPostalCode(), app.getPhone(), app.getEmail(), app.getStatus());
    }

    @QueryHandler //was macht das??
    public List<ApplicationPreviewResponse> handle(ApplicationListQuery query) {
        return storage.values().stream()
                .map(a -> new ApplicationPreviewResponse(a.getCustomerId(), a.getFirstName(), a.getLastName(),
                        a.getStatus(), a.getCreatedAt(), a.getUpdatedAt()))
                .collect(Collectors.toList());
    }

}
