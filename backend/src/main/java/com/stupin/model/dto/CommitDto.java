package com.stupin.model.dto;

public class CommitDto {

	private String sha;

	public CommitDto() {
	}

	public CommitDto(final String sha) {
		this.sha = sha;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(final String sha) {
		this.sha = sha;
	}

	@Override
	public String toString() {
		return "CommitDto{" +
			", commitSha=" + sha +
			'}';
	}
}
