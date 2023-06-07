package com.stupin.service;

import com.stupin.model.dto.RepositoryDto;

import java.util.List;

public interface RepositoryService {
	List<RepositoryDto> getRepositoriesWithBranches(String login);

}
