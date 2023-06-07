package com.stupin.service;

import com.stupin.model.dto.BranchDto;
import com.stupin.model.dto.RepositoryDto;

import java.util.List;

public interface BranchService {

	List<BranchDto> getBranches(RepositoryDto  repositories);
}
