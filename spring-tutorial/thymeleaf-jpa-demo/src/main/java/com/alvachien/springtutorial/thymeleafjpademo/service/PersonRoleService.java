package com.alvachien.springtutorial.thymeleafjpademo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alvachien.springtutorial.thymeleafjpademo.exception.*;
import com.alvachien.springtutorial.thymeleafjpademo.model.PersonRole;
import com.alvachien.springtutorial.thymeleafjpademo.repository.PersonRoleRepository;

@Service
public class PersonRoleService {
    @Autowired
    private PersonRoleRepository personRoleRepository;

    private boolean existsById(Long id) {
        return personRoleRepository.existsById(id);
    }

    public PersonRole findById(Long id) throws ResourceNotFoundException {
        PersonRole contact = personRoleRepository.findById(id).orElse(null);
        if (contact == null) {
            throw new ResourceNotFoundException("Cannot find Person Role with id: " + id);
        } else
            return contact;
    }

    public List<PersonRole> findAll(int pageNumber, int rowPerPage) {
        List<PersonRole> roles = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        personRoleRepository.findAll(sortedByIdAsc).forEach(roles::add);
        return roles;
    }

    public PersonRole save(PersonRole role) throws BadResourceException, ResourceAlreadyExistsException {
        if (!ObjectUtils.isEmpty(role.getRoleName())) {
            if (role.getId() != null && existsById(role.getId())) {
                throw new ResourceAlreadyExistsException("Role with id: " + role.getId() + " already exists");
            }
            return personRoleRepository.save(role);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save person role");
            exc.addErrorMessage("Person role is null or empty");
            throw exc;
        }
    }

    public void update(PersonRole role) throws BadResourceException, ResourceNotFoundException {
        if (!ObjectUtils.isEmpty(role.getRoleName())) {
            if (!existsById(role.getId())) {
                throw new ResourceNotFoundException("Cannot find Person Role with id: " + role.getId());
            }
            personRoleRepository.save(role);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save role");
            exc.addErrorMessage("Person role is null or empty");
            throw exc;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find role with id: " + id);
        } else {
            personRoleRepository.deleteById(id);
        }
    }

    public Long count() {
        return personRoleRepository.count();
    }
}
