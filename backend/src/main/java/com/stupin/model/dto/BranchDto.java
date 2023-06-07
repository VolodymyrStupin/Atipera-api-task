package com.stupin.model.dto;

public class BranchDto {

	private String name;

	private CommitDto commit;

	public BranchDto() {
	}

	public BranchDto(final String name, final CommitDto commit) {
		this.name = name;
		this.commit = commit;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public CommitDto getCommit() {
		return commit;
	}

	public void setCommit(final CommitDto commit) {
		this.commit = commit;
	}
}
