package event;

import java.math.BigDecimal;

public class NewBalanceEvent {

	private String id;
	private BigDecimal amount;

	public NewBalanceEvent(String id, BigDecimal amount) {

		this.id = id;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
