package aggregates;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;

import org.axonframework.spring.stereotype.Aggregate;


import command.SendTransactionCommand;
import event.AccountCreatedEvent;
import event.BalanceChangedEvent;

import event.IncreaseBalanceEvent;
import event.NewBalanceEvent;
import event.TransactionCreatedEvent;


import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.math.BigDecimal;

	
	@Aggregate
	public class Account {

	    @AggregateIdentifier
	    private String accountId;
	    
	    private BigDecimal balance;
	  
	    public Account() { //werden hier account id und balance angelegt und müsste balance dann anfangs auf null gesetzt werden?
	     
	    }

	    //1. Strang: Account erstellen

	    @EventSourcingHandler
	    public void on (AccountCreatedEvent event) {
	    	
	    	//log acccountCreatedEvent
	    	
	    	this.accountId = event.getAccountId();
	    	this.balance = event.getStartBalance();
	    }
	   
	    
	    //2. Strang: Transaction
	    
	    //a: Überweisung tätigen
	    @CommandHandler
	    public void on (SendTransactionCommand command) { //Command mit Überweisungsbetrag kommt von UI
	    	    
	//hier checken, ob Überweisung erlaub ist, wenn ja:
	    	
	    	apply (new BalanceChangedEvent(command.getSenderId(),command.getAmount()));
	    	
	    	apply (new IncreaseBalanceEvent(command.getRecieverId(), command.getAmount())); //->sendet Geld an den anderen Account id muss id des Empfängers sein, also andere ID
	    	
	    	apply (new TransactionCreatedEvent(command.getTransactionId(), command.getSenderId(), command.getRecieverId(), command.getAmount(), command.getType())); //geht derzeit in TRansactionProjection, noch in Accountprojektion integrieren?
	    	
	    } //hier jetzt noch eine Saga draufbauen!
	    
	           
	
	
	    
	    @EventSourcingHandler //absichern, dass nur das IBE mit passender Id ankommt
	    public void on (BalanceChangedEvent event) { // ->anderer Account, der das Geld erhält ->wichtig: unterschiedliche id, damit das Event nicht im selben Account wieder ankommt	    	
		    
	       	        	
	    	
	    	balance = balance.subtract(event.getAmount()); //Kontostand wird reduziert woher ist klar, wie hoch balance ist?? gehört das nicht eher in Accountprojection??
	    	
	    	//log balancechangedevent
	    	
	    	apply (new NewBalanceEvent(event.getId(), balance)); //neuer Kontostand wird in AccountProjection gespeichert
	    	
	    }
	    
	    
	    //b: Geld erhalten:
	    
	    @EventSourcingHandler //absichern, dass nur das IBE mit passender Id ankommt
	    public void on (IncreaseBalanceEvent event) { // ->anderer Account, der das Geld erhält ->wichtig: unterschiedliche id, damit das Event nicht im selben Account wieder ankommt	    	
	    	balance = balance.add(event.getAmount()); //Kontostand wird erhöht
	    	//log balance   
	    	apply (new NewBalanceEvent(event.getId(), balance)); //neuer Kontostand wird in AccountProjection gespeichert
	    	
	    	
	    }
	    
	    
	    
	
	    
	    
	    
	    
}
