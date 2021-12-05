package event;


import java.math.BigDecimal;

import command.TransactionType;

public class TransactionCreatedEvent {

    private final String transactionId;
    private final String from;
    private final String to;
    private final BigDecimal value;
    private final TransactionType type;

    public TransactionCreatedEvent(String transactionId, String from, String to, BigDecimal value, TransactionType type) {
        this.transactionId = transactionId;
        this.from = from;
        this.to = to;
        this.value = value;
        this.type = type;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getValue() {
        return value;
    }

    public TransactionType getType() {
        return type;
    }

}
