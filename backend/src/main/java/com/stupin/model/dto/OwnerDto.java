package com.stupin.model.dto;

public class OwnerDto {
	private String login;
	public OwnerDto(){
	}

	public OwnerDto(final String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public String toString() {
		return "OwnerDto{" +
			"login='" + login + '\'' +
			'}';
	}
}
