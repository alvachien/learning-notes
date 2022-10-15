package com.alvachien.springtutorial.thymeleafjpademo.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.alvachien.springtutorial.thymeleafjpademo.model.PersonRole;

public interface PersonRoleRepository extends PagingAndSortingRepository<PersonRole, Long>, JpaSpecificationExecutor<PersonRole> 
{    
}

