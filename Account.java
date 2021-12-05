package aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;

import org.axonframework.spring.stereotype.Aggregate;

import command.SendTransactionCommand;
import event.AccountCreatedEvent;
import event.AddMandateEvent;
import event.BalanceChangedEvent;

import event.IncreaseBalanceEvent;

import event.NewBalanceEvent;
import event.TransactionCreatedEvent;
import components.AccountProjection;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.createNew;

import java.math.BigDecimal;
import java.util.ArrayList;

@Aggregate
public class Account {

	@AggregateIdentifier
	private String accountId;

	private BigDecimal balance;
	
	private ArrayList<String> mandates;
	
	
	public Account() {
	};

	public Account(String accountId) {

	}

	// 1. Strang: Account erstellen

	@EventSourcingHandler
	public void on(AccountCreatedEvent event) {

		// log acccountCreatedEvent

		this.accountId = event.getAccountId();
		this.balance = event.getStartBalance();

		try {
			createNew(AccountProjection.class, () -> new AccountProjection(accountId)); // AccountProjection wird
																						// erstellt
		} catch (Exception e) {

			e.printStackTrace();
		}

		// log AccountProjection erstellt

	}
	
	

	@EventSourcingHandler //todo: absichern, dass der richtige Account addressiert wird
	public void on(AddMandateEvent event) {
		mandates.add(event.getId());
		
	}

	// 2. Strang: Transaction

	// a: Überweisung tätigen
	@CommandHandler
	public void on(SendTransactionCommand command) { // Command mit Überweisungsbetrag kommt von UI

		// hier checken, ob Überweisung erlaub ist, wenn ja:

		apply(new BalanceChangedEvent(command.getSenderId(), command.getAmount()));

		apply(new IncreaseBalanceEvent(command.getRecieverId(), command.getAmount())); // ->sendet Geld an den anderen
																						// Account id muss id des
																						// Empfängers sein, also andere
																						// ID

		apply(new TransactionCreatedEvent(command.getTransactionId(), command.getSenderId(), command.getRecieverId(),
				command.getAmount(), command.getType())); // geht in Accountprojection

	} // hier eine Saga!

	@EventSourcingHandler
	public void on(BalanceChangedEvent event) { // ->anderer Account, der das Geld erhält ->wichtig: unterschiedliche
												// id, damit das Event nicht im selben Account wieder ankommt

		balance = balance.subtract(event.getAmount()); // Kontostand wird reduziert
		// log balancechangedevent

		apply(new NewBalanceEvent(event.getId(), balance)); // neuer Kontostand wird in AccountProjection gespeichert

	}

	// b: Geld erhalten:

	@EventSourcingHandler // todo: absichern, dass nur das IBE mit passender Id ankommt
	public void on(IncreaseBalanceEvent event) { // ->anderer Account, der das Geld erhält ->wichtig: unterschiedliche
													// id, damit das Event nicht im selben Account wieder ankommt
		balance = balance.add(event.getAmount()); // Kontostand wird erhöht
		// log balance
		apply(new NewBalanceEvent(event.getId(), balance)); // neuer Kontostand wird in AccountProjection gespeichert

	}

}
