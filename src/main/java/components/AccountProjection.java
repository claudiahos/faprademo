package components;

import org.axonframework.eventhandling.EventHandler;

import org.springframework.stereotype.Component;

import command.AccountEntity;
import command.TransactionEntity;

import event.NewBalanceEvent;
import event.TransactionCreatedEvent;

import java.util.*;

@Component
public class AccountProjection {

	private final Map<String, AccountEntity> accountStorage = new HashMap<>();
	private final Map<String, List<TransactionEntity>> storage = new HashMap<>();

	public AccountProjection() {
	}

	public AccountProjection(String id) {

	}

	@EventHandler
	public void on(NewBalanceEvent event) { // neuer Kontostand wird gespeichert
		AccountEntity account = accountStorage.get(event.getId());

		account.setBalance(event.getAmount());

	}

	@EventHandler
	public void on(TransactionCreatedEvent event) { // speichert Transactions
		TransactionEntity transaction = new TransactionEntity(event.getTransactionId(), event.getFrom(), event.getTo(),
				event.getValue(), event.getType());
		saveTransaction(transaction.getFrom(), transaction);
		saveTransaction(transaction.getTo(), transaction);
	}

	public void saveTransaction(String accountId, TransactionEntity transaction) {
		if (storage.containsKey(accountId)) {
			storage.get(accountId).add(transaction);
		} else {
			storage.put(accountId, new ArrayList<>(List.of(transaction)));
		}

	}

}
