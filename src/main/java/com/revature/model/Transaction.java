package com.revature.model;

import java.util.Date;

public class Transaction {

	    private Long transactionId;
	    private Long userId;
	    private Date transactionTime;
	    private String accountName;
	    private String action;
	    private Double amount;   
	    
	    public Transaction(){}

		public Transaction(Long transactionId, Long userId, Date transactionTime, String accountName,
				String action, Double amount) {
			super();
			this.transactionId = transactionId;
			this.userId = userId;
			this.transactionTime = transactionTime;
			this.accountName = accountName;
			this.action = action;
			this.amount = amount;
		}

		public Long getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(Long transactionId) {
			this.transactionId = transactionId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Date getTransactionTime() {
			return transactionTime;
		}

		public void setTransactionTime(Date transactionTime) {
			this.transactionTime = transactionTime;
		}

		public String getAccountName() {
			return accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
			result = prime * result + ((action == null) ? 0 : action.hashCode());
			result = prime * result + ((amount == null) ? 0 : amount.hashCode());
			result = prime * result + ((transactionTime == null) ? 0 : transactionTime.hashCode());
			result = prime * result + ((userId == null) ? 0 : userId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Transaction other = (Transaction) obj;
			if (accountName == null) {
				if (other.accountName != null)
					return false;
			} else if (!accountName.equals(other.accountName))
				return false;
			if (action == null) {
				if (other.action != null)
					return false;
			} else if (!action.equals(other.action))
				return false;
			if (amount == null) {
				if (other.amount != null)
					return false;
			} else if (!amount.equals(other.amount))
				return false;
			if (transactionTime == null) {
				if (other.transactionTime != null)
					return false;
			} else if (!transactionTime.equals(other.transactionTime))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Transaction [transactionId=" + transactionId + ", userId=" + userId + ", transactionTime="
					+ transactionTime + ", accountName=" + accountName + ", action=" + action + ", amount=" + amount
					+ "]";
		}
	    
	    
	
}
