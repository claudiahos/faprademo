package query;



import java.math.BigDecimal;

import aggregates.AccountStatus;

public class AccountResponse {

    private final String accountId;
    private final BigDecimal balance;
    private final AccountStatus status;

    public AccountResponse(String accountId, BigDecimal balance, AccountStatus status) {
        this.accountId = accountId;
        this.balance = balance;
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() { return status; }
}
