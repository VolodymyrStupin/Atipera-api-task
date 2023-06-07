package com.stupin.model.dto;

import java.util.List;

public class RepositoryDto {

	private String name;

	private OwnerDto owner;

	private List<BranchDto> branches;

	private boolean fork;

	public RepositoryDto() {
	}

	public RepositoryDto(final String name, final OwnerDto owner, final List<BranchDto> branches, final boolean fork) {
		this.name = name;
		this.owner = owner;
		this.branches = branches;
		this.fork = fork;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public OwnerDto getOwner() {
		return owner;
	}

	public void setOwner(final OwnerDto owner) {
		this.owner = owner;
	}

	public List<BranchDto> getBranches() {
		return branches;
	}

	public void setBranches(final List<BranchDto> branches) {
		this.branches = branches;
	}

	public boolean isNotFork() {
		return !fork;
	}

	public void setFork(final boolean fork) {
		this.fork = fork;
	}

	@Override
	public String toString() {
		return "RepositoryDto{" +
			"name='" + name + '\'' +
			", owner=" + owner +
			", branches=" + branches +
			", fork=" + fork +
			'}';
	}
}