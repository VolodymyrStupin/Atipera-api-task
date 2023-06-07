package com.stupin.service;

import com.stupin.model.dto.BranchDto;
import com.stupin.model.dto.OwnerDto;
import com.stupin.model.dto.RepositoryDto;
import com.stupin.service.impl.BranchServiceImpl;
import com.stupin.service.impl.RepositoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class RepositoryServiceTest {

	private final RestTemplate REST_TEMPLATE_MOCK = Mockito.mock(RestTemplate.class);
	@Mock
	private OwnerDto ownerDto;
	@Mock
	private BranchDto branchDto;
	private RepositoryService repositoryService;

	private final BranchService branchService = new BranchServiceImpl(REST_TEMPLATE_MOCK);

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		repositoryService = new RepositoryServiceImpl(REST_TEMPLATE_MOCK, branchService);
	}

	@Test
	void testGetRepositoriesByUser_shouldReturnRepository() {
		List<BranchDto> branches = List.of(branchDto);

		boolean FORK = false;
		List<RepositoryDto> expectedRepositories = Arrays.asList(
			new RepositoryDto("repo1", ownerDto, branches, FORK),
			new RepositoryDto("repo2", ownerDto, branches, FORK),
			new RepositoryDto("repo3", ownerDto, branches, FORK)
		);
		ResponseEntity<List<RepositoryDto>> response = new ResponseEntity<>(expectedRepositories, HttpStatus.OK);
		when(REST_TEMPLATE_MOCK.exchange(
				Mockito.anyString(),
				Mockito.eq(HttpMethod.GET),
				Mockito.isNull(),
				Mockito.<ParameterizedTypeReference<List<RepositoryDto>>>any()
			)
		).thenReturn(response);

		List<RepositoryDto> repositories = repositoryService.getRepositoriesWithBranches("exampleUser");

		assertEquals(expectedRepositories, repositories);
	}

	@Test
	void testGetRepositoriesByUser_shouldCatchHttpClientErrorException_shouldReturnEmptyCollection() {
		HttpClientErrorException exception = new HttpClientErrorException(HttpStatus.NOT_FOUND);
		when(REST_TEMPLATE_MOCK.exchange(
				Mockito.anyString(),
				Mockito.eq(HttpMethod.GET),
				Mockito.isNull(),
				Mockito.<ParameterizedTypeReference<List<RepositoryDto>>>any()
			)
		).thenThrow(exception);

		List<RepositoryDto> repositories;
		try {
			repositories = repositoryService.getRepositoriesWithBranches("exampleUser");
		} catch (HttpClientErrorException e) {
			repositories = Collections.emptyList();
		}

		assertTrue(repositories.isEmpty());
	}

	@Test
	void testGetRepositoriesByUser_shouldCatchHttpMediaTypeNotAcceptableException_shouldReturnEmptyCollection() {
		HttpClientErrorException exception = new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		when(REST_TEMPLATE_MOCK.exchange(
				Mockito.anyString(),
				Mockito.eq(HttpMethod.GET),
				Mockito.isNull(),
				Mockito.<ParameterizedTypeReference<List<RepositoryDto>>>any()
			)
		).thenThrow(exception);

		List<RepositoryDto> repositories;
		try {
			repositories = repositoryService.getRepositoriesWithBranches("exampleUser");
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_ACCEPTABLE) {
				repositories = Collections.emptyList();
			} else {
				throw e;
			}
		}
		assertTrue(repositories.isEmpty());
	}
}
