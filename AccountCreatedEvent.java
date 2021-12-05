package event;

import java.math.BigDecimal;

public class AccountCreatedEvent {// +

	private String accountId;
	private BigDecimal startBalance;

	public AccountCreatedEvent(String id) {
		this.startBalance = new BigDecimal(0);
		this.accountId = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}
}
