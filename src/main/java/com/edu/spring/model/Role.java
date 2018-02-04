package com.edu.spring.model;

public enum Role {
	ADMIN("ADMIN") {

		@Override
		public User instantiateUser() {
			return new Admin();
		}

		@Override
		public Class<? extends User> getUserClass() {
			return Admin.class;
		}
	};

	private String role;

	Role(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public abstract User instantiateUser();

	public abstract Class<? extends User> getUserClass();
}
