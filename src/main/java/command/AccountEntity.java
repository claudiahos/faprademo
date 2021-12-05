package command;

import java.math.BigDecimal;

import aggregates.AccountStatus;

public class AccountEntity {

	private String id;
	private BigDecimal balance;
	private AccountStatus status;

	public AccountEntity(String id, BigDecimal balance) {

		this.id = id;
		this.balance = balance;

	}

	public String getId() {
		return id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setBalance(BigDecimal balance) {

		this.balance = balance;

	}
}
