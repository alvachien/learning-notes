package com.alvachien.springbootfundamentals.repository;

import com.alvachien.springbootfundamentals.entity.Release;
import org.springframework.data.repository.CrudRepository;

public interface ReleaseRepository extends CrudRepository<Release, Long> {
}
