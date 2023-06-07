package com.stupin.controller;

import com.stupin.model.dto.RepositoryDto;
import com.stupin.service.RepositoryService;
import com.stupin.service.impl.RepositoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {
	private static final Logger LOG = LoggerFactory.getLogger(RepositoryController.class);
	private final RepositoryService repositoryService;

	@Autowired
	public RepositoryController(RepositoryServiceImpl gitHubService) {
		this.repositoryService = gitHubService;
	}

	@GetMapping("/{username}")
	public List<RepositoryDto> getBranches(@PathVariable String username) {
		LOG.debug("Received GET request to get all gitHub public branch for user: {}", username);
		return repositoryService.getRepositoriesWithBranches(username);
	}
}
