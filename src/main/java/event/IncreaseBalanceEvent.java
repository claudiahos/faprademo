package event;

import java.math.BigDecimal;

public class IncreaseBalanceEvent {

	private String id;
	private BigDecimal amount;

	public IncreaseBalanceEvent(String id, BigDecimal amount) {

		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
