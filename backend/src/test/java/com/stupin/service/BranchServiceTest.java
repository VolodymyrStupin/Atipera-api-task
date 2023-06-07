package com.stupin.service;

import com.stupin.model.dto.BranchDto;
import com.stupin.model.dto.CommitDto;
import com.stupin.model.dto.OwnerDto;
import com.stupin.model.dto.RepositoryDto;
import com.stupin.service.impl.BranchServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BranchServiceTest {
	private final RestTemplate REST_TEMPLATE_MOCK =  Mockito.mock(RestTemplate.class);

	@Mock
	private CommitDto commitDto;

	@Test
	void testGetBranches_shouldReturnBranches() {
		List<BranchDto> expectedBranches = Arrays.asList(
			new BranchDto("branch1", commitDto),
			new BranchDto("branch2", commitDto),
			new BranchDto("branch3", commitDto)
		);
		ResponseEntity<List<BranchDto>> response = new ResponseEntity<>(expectedBranches, HttpStatus.OK);
		when(REST_TEMPLATE_MOCK.exchange(
				Mockito.anyString(),
				Mockito.eq(HttpMethod.GET),
				Mockito.isNull(),
				Mockito.<ParameterizedTypeReference<List<BranchDto>>>any()
			)
		).thenReturn(response);

		BranchService branchService1 = new BranchServiceImpl(REST_TEMPLATE_MOCK);

		RepositoryDto repository = new RepositoryDto();
		repository.setOwner(new OwnerDto("exampleOwner"));
		repository.setName("exampleRepo");

		List<BranchDto> branches = branchService1.getBranches(repository);

		assertEquals(expectedBranches, branches);
	}
}
