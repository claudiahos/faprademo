package command;

import java.math.BigDecimal;

public class SendTransactionCommand {
	
	private String transactionId;
	private String senderId;
	private String recieverId;
	private BigDecimal amount;
	private TransactionType type;
	
	
	public SendTransactionCommand(String transactionId, String senderId, String recieverId, BigDecimal amount, TransactionType type) {
		this.transactionId = transactionId;
		this.senderId = senderId;
		this.recieverId = recieverId;
		this.amount = amount;
		this.type = type;
	}
	
	
	public String getTransactionId() {
		return transactionId;
	}

	public String getSenderId() {
		return senderId;
	}
	
	public String getRecieverId() {
		return recieverId;
	}
	
	public BigDecimal getAmount() {
		
		return amount;
	}
	
	public TransactionType getType() {
		return type;
	}
}
