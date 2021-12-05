package aggregates;

import org.axonframework.commandhandling.CommandHandler;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import command.ApplyAccountOwnerCommand;
import command.ApplyMandateOwnerCommand;

import event.AccountCreatedEvent;
import event.AddMandateEvent;
import event.CreateAccountEvent;
import event.CreateCustomerProjectionEvent;
import event.CreateMandateEvent;
import event.CustomerCreatedEvent;
import event.MandateCreatedEvent;
import components.CustomerProjection;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.createNew;

@Aggregate
public class Customer {

	@AggregateIdentifier
	private String customerId;

	public Customer() {

	}

	public Customer(String customerId) {

	}

//1. Strang: Account und Mandate erstellen:
	@CommandHandler
	public Customer(ApplyAccountOwnerCommand command) { // Command kommt vom Gateway

		apply(new CreateCustomerProjectionEvent(command.getId()));

		apply(new CreateAccountEvent(command.getId(), command.getLastName(), command.getFirstName(),
				command.getAddress(), command.getCity(), command.getPostalCode(), command.getPhone(),
				command.getEmail()));

	}

	@CommandHandler
	public Customer(ApplyMandateOwnerCommand command) { // kommt von Gateway

		apply(new CreateCustomerProjectionEvent(command.getId()));
		
		apply(new CreateMandateEvent(command.getId(), command.getLastName(), command.getFirstName(),
				command.getAddress(), command.getCity(), command.getPostalCode(), command.getPhone(),
				command.getEmail()));
		
		
	}

	@EventSourcingHandler
	private void handle(CreateCustomerProjectionEvent event) {

		try {
			createNew(CustomerProjection.class, () -> new CustomerProjection(event.getId()));// CustomerProjection wird
																								// erstellt
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	// log customerprojectionevent

	@EventSourcingHandler
	private void handle(CreateAccountEvent event) {

		try {
			createNew(Account.class, () -> new Account(event.getCustomerId())); // Account wird mit Id erstellt
		} catch (Exception e) {

			e.printStackTrace();
		}
		// log accountCreatedEvent

		apply(new AccountCreatedEvent(event.getCustomerId())); // geht an Account

		apply(new CustomerCreatedEvent(event.getCustomerId(), event.getLastName(), event.getFirstName(),
				event.getAddress(), event.getCity(), event.getPostalCode(), event.getPhone(), event.getEmail())); // geht
																													// an
																													// CustomerProjection

	}

	@EventSourcingHandler
	private void handle(CreateMandateEvent event) {

		try {
			createNew(Mandate.class, () -> new Mandate(event.getCustomerId())); // Mandate wird erstellt
		} catch (Exception e) {

			e.printStackTrace();
		}

		apply(new CustomerCreatedEvent(event.getCustomerId(), event.getLastName(), event.getFirstName(),
				event.getAddress(), event.getCity(), event.getPostalCode(), event.getPhone(), event.getEmail())); // geht
																													// an
																													// Customerprojection

		apply(new MandateCreatedEvent(event.getCustomerId())); // geht an Mandate
		
		apply (new AddMandateEvent(event.getCustomerId())); //sendet Mandate-id an account

	}

	@EventSourcingHandler // wer instanziiert das Customerobjekt?
	private void on(CustomerCreatedEvent event) {

		// log customerCreatedEvent
		this.customerId = event.getCustomerId();
	}

}
