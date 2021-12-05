package query;



import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;


import command.AccountEntity;

import event.BalanceChangedEvent;
import event.NewBalanceEvent;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AccountProjection {

    private final Map<String, AccountEntity> accountStorage = new HashMap<>();

  
    private final QueryUpdateEmitter queryUpdateEmitter;

    public AccountProjection(QueryUpdateEmitter queryUpdateEmitter) {
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

 
    @EventSourcingHandler
    public void on(NewBalanceEvent event) {
        AccountEntity account = accountStorage.get(event.getId());
        
        
        account.setBalance(event.getAmount()); //neuer Kontostand wird gespeichert ->hier könnte etwas falsch sein!
     

     /*   queryUpdateEmitter.emit(AccountQuery.class, query -> event.getAccountId().equals(query.getAccountId()),
                new AccountResponse(account.getAccountId(), account.getBalance(), account.getStatus()));

        emitSummaryUpdate();  */
    }

 /*   @EventSourcingHandler
    public void on(AccountClosedEvent event) { //woher kommt das?
        AccountEntity account = accountStorage.get(event.getAccountId());
        account.setStatus(AccountStatus.CLOSED);

        queryUpdateEmitter.emit(AccountQuery.class, query -> event.getAccountId().equals(query.getAccountId()),
                new AccountResponse(account.getAccountId(), account.getBalance(), account.getStatus()));

        emitSummaryUpdate();
    }
*/

    @QueryHandler
    public AccountResponse handle(AccountQuery query) { //holt AccountID, Balance und status
       AccountEntity account = accountStorage.get(query.getAccountId());
        return new AccountResponse(account.getId(), account.getBalance(), account.getStatus());
    }
    

    @QueryHandler
    public List<AccountResponse> handle(AccountSummaryQuery query) {
        return getAccountSummary(); //gibt eine Liste mit AccountResponses zurück
    }

    private List<AccountResponse> getAccountSummary() {
        return accountStorage.values().stream()
                .map(account -> new AccountResponse(account.getId(), account.getBalance(), account.getStatus()))
                .collect(Collectors.toList());
    }

    private void emitSummaryUpdate() {
        queryUpdateEmitter.emit(AccountSummaryQuery.class, Objects::nonNull, getAccountSummary());
    }
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
