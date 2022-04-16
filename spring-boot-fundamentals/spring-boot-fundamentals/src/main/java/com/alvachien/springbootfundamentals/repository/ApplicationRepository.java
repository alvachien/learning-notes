package com.alvachien.springbootfundamentals.repository;

import com.alvachien.springbootfundamentals.entity.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
}
