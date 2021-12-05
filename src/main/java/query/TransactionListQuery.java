package query;

public class TransactionListQuery {

    private final String accountId;

    public TransactionListQuery(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

}
