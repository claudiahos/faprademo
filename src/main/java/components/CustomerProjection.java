package components;

import org.axonframework.eventhandling.EventHandler;

import org.springframework.stereotype.Component;

import command.CustomerEntity;

import event.CustomerCreatedEvent;

import java.util.LinkedHashMap;

import java.util.Map;

@Component
public class CustomerProjection {// +

	private final Map<String, CustomerEntity> storage = new LinkedHashMap<>();
	private String id;

	public CustomerProjection() {

	};

	public CustomerProjection(String id) {

	}

	public String getId() {
		return id;
	};

	@EventHandler // 1. Strang: Kundendaten anlegen
	public void on(CustomerCreatedEvent event) { // speichert die Kundendaten in Persistenzdatenbank
		storage.put(event.getCustomerId(),
				new CustomerEntity(event.getCustomerId(), event.getFirstName(), event.getLastName(), event.getAddress(),
						event.getCity(), event.getPostalCode(), event.getPhone(), event.getEmail(), event.getStatus(),
						event.getCreatedAt()));
	}

}
