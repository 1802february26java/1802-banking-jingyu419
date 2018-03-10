package com.revature.model;

public class Account {

	    private Long accountId;
	    private Long userId;
	    private String accountName;
	    private Double balance;
		
	    public Account(){}

		public Account(Long accountId, Long userId, String accountName, Double balance) {
			super();
			this.accountId = accountId;
			this.userId = userId;
			this.accountName = accountName;
			this.balance = balance;
		}

		public Long getAccountId() {
			return accountId;
		}

		public void setAccountId(Long accountId) {
			this.accountId = accountId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getAccountName() {
			return accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public Double getBalance() {
			return balance;
		}

		public void setBalance(Double balance) {
			this.balance = balance;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
			result = prime * result + ((balance == null) ? 0 : balance.hashCode());
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
			Account other = (Account) obj;
			if (accountName == null) {
				if (other.accountName != null)
					return false;
			} else if (!accountName.equals(other.accountName))
				return false;
			if (balance == null) {
				if (other.balance != null)
					return false;
			} else if (!balance.equals(other.balance))
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
			return "Account [accountId=" + accountId + ", userId=" + userId + ", accountName=" + accountName
					+ ", balance=" + balance + "]";
		}
	    
	    
}
