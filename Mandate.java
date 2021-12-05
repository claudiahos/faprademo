package aggregates;

import static org.axonframework.modelling.command.AggregateLifecycle.createNew;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;

import org.axonframework.spring.stereotype.Aggregate;

import components.MandateProjection;

import event.MandateCreatedEvent;

@Aggregate
public class Mandate {

	@AggregateIdentifier
	private String mandateId;

	public Mandate() {

	}

	public Mandate(String mandateId) {

	}

	@EventSourcingHandler
	public void on(MandateCreatedEvent event) {

		// log MandateCreatedEvent
		this.mandateId = event.getId();
		
		
		//Event, das MandateId an accountId sendet


		try {
			createNew(MandateProjection.class, () -> new MandateProjection(mandateId)); // MandateProjection wird
																						// erstellt
		} catch (Exception e) {

			e.printStackTrace();
		}

		// log AccountProjection erstellt

	}


}
