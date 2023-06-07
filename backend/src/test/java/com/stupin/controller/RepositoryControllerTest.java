package com.stupin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stupin.model.dto.BranchDto;
import com.stupin.model.dto.CommitDto;
import com.stupin.model.dto.OwnerDto;
import com.stupin.model.dto.RepositoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RepositoryController.class)
public class RepositoryControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private RepositoryController repositoryControllerMock;

	@Test
	void getBranches_ShouldReturnStatusOk() throws Exception {
		final String jsonBody = mapper.writeValueAsString(createListRepositories());

		when(repositoryControllerMock.getBranches("userName")).thenReturn(createListRepositories());

		mockMvc.perform(get("/repositories/{userName}", "userName")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBody))
			.andExpect(status().isOk());
	}

	@Test
	void getBranches_ShouldReturnStatusNotAcceptable() throws Exception {
		when(repositoryControllerMock.getBranches("userName")).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/repositories/{userName}", "userName")
				.accept(MediaType.APPLICATION_XML_VALUE))
			.andExpect(status().isNotAcceptable());

		verify(repositoryControllerMock, times(1)).getBranches("userName");
	}

	private List<RepositoryDto> createListRepositories() {

		return List.of(
			createRepository(
				"name",
				createOwner("userName"),
				List.of(createBranch(
					"nameBranch",
					new CommitDto("sha1")
				)),
				false
			),
			createRepository(
				"name1",
				createOwner("userName"),
				List.of(createBranch(
					"nameBranch1",
					new CommitDto("sha2")
				)),
				false
			)
		);
	}

	private RepositoryDto createRepository(String name, OwnerDto ownerDto, List<BranchDto> branches, boolean fork) {
		return new RepositoryDto(name, ownerDto, branches, fork);
	}

	private OwnerDto createOwner(String login) {
		return new OwnerDto(login);
	}

	private BranchDto createBranch(String name, CommitDto commit) {
		return new BranchDto(name, commit);
	}
}