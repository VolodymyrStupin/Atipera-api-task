package com.stupin.service.impl;

import com.stupin.model.dto.RepositoryDto;
import com.stupin.service.BranchService;
import com.stupin.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.stupin.config.Constants.URL.API_GITHUB_COM_URL;

@Service
@ComponentScan
public class RepositoryServiceImpl implements RepositoryService {

	private static final Logger LOG = LoggerFactory.getLogger(RepositoryServiceImpl.class);

	private final RestTemplate restTemplate;

	private final BranchService branchService;

	@Autowired
	public RepositoryServiceImpl(RestTemplate restTemplate, final BranchService branchService) {
		this.restTemplate = restTemplate;
		this.branchService = branchService;
	}

	@Override
	public List<RepositoryDto> getRepositoriesWithBranches(final String login) {
		List<RepositoryDto> repositories = getNonForkRepositories(login);
		setBranchesToRepositories(repositories);
		LOG.debug("In getRepositoriesWithBranches - Successfully get repository for user with login:{}", login);
		return repositories;
	}

	private List<RepositoryDto> getAllPublicGitHubRepository(String login) {
		String url = API_GITHUB_COM_URL + "/users/" + login + "/" + "repos";

		ResponseEntity<List<RepositoryDto>> response =
			restTemplate.exchange
				(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
				});
		return response.getBody();
	}

	private void setBranchesToRepositories(final List<RepositoryDto> repositories) {
		for (RepositoryDto repository : repositories) {
			repository.setBranches(branchService.getBranches(repository));
		}
	}

	private List<RepositoryDto> getNonForkRepositories(final String login) {
		List<RepositoryDto> repositories = getAllPublicGitHubRepository(login);
		return repositories.stream()
			.filter(RepositoryDto::isNotFork)
			.toList();
	}
}
