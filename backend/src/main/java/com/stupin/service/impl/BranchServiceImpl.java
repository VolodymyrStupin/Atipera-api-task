package com.stupin.service.impl;

import com.stupin.model.dto.BranchDto;
import com.stupin.model.dto.RepositoryDto;
import com.stupin.service.BranchService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.stupin.config.Constants.URL.API_GITHUB_COM_URL;

@Service
public class BranchServiceImpl implements BranchService {

	private final RestTemplate restTemplate;

	public BranchServiceImpl(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<BranchDto> getBranches(final RepositoryDto repository) {
		final String username = repository.getOwner().getLogin();
		String repositoryName = repository.getName();

		String url = API_GITHUB_COM_URL + "/repos/" + username + "/" + repositoryName + "/" + "branches";

		ResponseEntity<List<BranchDto>> response =
			restTemplate.exchange
				(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
				});
		return response.getBody();
	}
}
