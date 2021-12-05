package aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import command.ApplyAccountOwnerCommand;
import command.ApplyMandateOwnerCommand;
import command.CustomerEntity;
import event.AccountCreatedEvent;
import event.ApplicationApprovedEvent;
import event.ApplicationRejectedEvent;
import event.CustomerCreatedEvent;
import event.MandateCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.math.BigDecimal;

@Aggregate
public class Customer { 

	@AggregateIdentifier
	private String customerId;
	
	
 
    public Customer() {
      
    }
//1. Strang: Account und Mandate erstellen:
    @CommandHandler 
    public Customer(ApplyAccountOwnerCommand command) { //kommt von Gateway
    	
    	
        apply(new CustomerCreatedEvent(command.getId(),  command.getLastName(), command.getFirstName(),
                command.getAddress(), command.getCity(), command.getPostalCode(), command.getPhone(), command.getEmail())); //geht an Accountowner projection
        
       apply (new AccountCreatedEvent(command.getId())); //geht an Account, warum? entscheiden: wo wird es gespeichert? Eventstore oder Peristenz??
       
       
    }
 
  
    
 
    @CommandHandler //hier wurde der Customer mit Daten im Eventstore gespeichert
    public Customer(ApplyMandateOwnerCommand command) { //kommtvon Gatewy
        apply(new CustomerCreatedEvent(command.getId(), command.getLastName(),command.getFirstName(), 
                command.getAddress(), command.getCity(), command.getPostalCode(), command.getPhone(), command.getEmail())); //geht an Customerprojection
        
       apply (new MandateCreatedEvent(command.getId())); //geht an Mandate
       
       
    }

    
    @EventSourcingHandler
    private void on(CustomerCreatedEvent event) {
    	
    	//log customerCreatedEvent
    	this.customerId = event.getCustomerId();
    }
    
    
  
    
 /*
    
    
    //ab hier Artem:
    @CommandHandler
    public void handle(ApproveApplicationCommand command) { //kommt von der UI = ApplicationView
        if (status == CustomerApplicationStatus.PENDING) {
            apply(new ApplicationApprovedEvent(command.getApplicationFormId()));
        /*    commandGateway.send(new OpenAccountCommand(idFactory.getId(), idFactory.getId(), firstName, lastName,
                    address, city, postalCode, phone, email, BigDecimal.ZERO));
                    */
/*        }
    }

    @CommandHandler
    public void handle(RejectApplicationCommand command) { //kommt von der UI = ApplicationView
        if (status == ApplicationFormStatus.PENDING) {
            apply(new ApplicationRejectedEvent(command.getApplicationFormId()));
            // TODO: send rejection notification to customer
        }
    }

    @EventSourcingHandler
    public void on(ApplicationReceivedEvent event) {
        applicationFormId = event.getApplicationFormId();
        firstName = event.getFirstName();
        lastName = event.getLastName();
        address = event.getAddress();
        city = event.getCity();
        postalCode = event.getPostalCode();
        phone = event.getPhone();
        email = event.getEmail();
        status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(ApplicationApprovedEvent event) {
        status = ApplicationFormStatus.APPROVED;
    }

    @EventSourcingHandler
    public void on(ApplicationRejectedEvent event) {
        status = ApplicationFormStatus.REJECTED;
    }
*/
    
    
    
    
    
    
   /* 
    //ab hier Artem:
    @CommandHandler
    public void handle(ApproveApplicationCommand command) { //kommt von der UI = ApplicationView
        if (status == CustomerApplicationStatus.PENDING) {
            apply(new ApplicationApprovedEvent(command.getApplicationFormId()));
        /*    commandGateway.send(new OpenAccountCommand(idFactory.getId(), idFactory.getId(), firstName, lastName,
                    address, city, postalCode, phone, email, BigDecimal.ZERO));
                    */
/*        }
    }

    @CommandHandler
    public void handle(RejectApplicationCommand command) { //kommt von der UI = ApplicationView
        if (status == ApplicationFormStatus.PENDING) {
            apply(new ApplicationRejectedEvent(command.getApplicationFormId()));
            // TODO: send rejection notification to customer
        }
    }

    @EventSourcingHandler
    public void on(ApplicationReceivedEvent event) {
        applicationFormId = event.getApplicationFormId();
        firstName = event.getFirstName();
        lastName = event.getLastName();
        address = event.getAddress();
        city = event.getCity();
        postalCode = event.getPostalCode();
        phone = event.getPhone();
        email = event.getEmail();
        status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(ApplicationApprovedEvent event) {
        status = ApplicationFormStatus.APPROVED;
    }

    @EventSourcingHandler
    public void on(ApplicationRejectedEvent event) {
        status = ApplicationFormStatus.REJECTED;
    }
*/
    
    //Ende Artem
    
 
    
    
    
    
    
    

}
