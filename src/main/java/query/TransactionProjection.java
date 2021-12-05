package query;


import org.axonframework.eventhandling.EventHandler;

import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import command.TransactionEntity;
import event.TransactionCreatedEvent;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TransactionProjection {

    private final Map<String, List<TransactionEntity>> storage = new HashMap<>();
    private final QueryUpdateEmitter queryUpdateEmitter;

    public TransactionProjection(QueryUpdateEmitter queryUpdateEmitter){
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void on(TransactionCreatedEvent event) {
        TransactionEntity transaction = new TransactionEntity(event.getTransactionId(), event.getFrom(), event.getTo(), event.getValue(), event.getType());
        saveTransaction(transaction.getFrom(), transaction);
        saveTransaction(transaction.getTo(), transaction);
    }

    @QueryHandler
    public List<TransactionResponse> handle(TransactionListQuery query) {
        return storage.get(query.getAccountId()).stream()
                .map(t -> new TransactionResponse(t.getTransactionId(), t.getFrom(), t.getTo(), t.getValue(), t.getType()))
                .collect(Collectors.toList());
    }

    public void saveTransaction(String accountId, TransactionEntity transaction) {
        if (storage.containsKey(accountId)) {
            storage.get(accountId).add(transaction);
        } else {
            storage.put(accountId, new ArrayList<>(List.of(transaction)));
        }
        emitSummaryUpdate(accountId);
    }

    private List<TransactionResponse> getTransactionSummary(String accountID) {
        return storage.get(accountID).stream().map(transactionEntity ->
                new TransactionResponse(transactionEntity.getTransactionId(),
                        transactionEntity.getFrom(), transactionEntity. getTo(),
                        transactionEntity.getValue(), transactionEntity.getType())).
                collect(Collectors.toList());
    }

    private void emitSummaryUpdate(String accountID) {
        queryUpdateEmitter.emit(TransactionListQuery.class, Objects::nonNull, getTransactionSummary(accountID));
    }

}
