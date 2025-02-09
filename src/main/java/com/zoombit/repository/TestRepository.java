package com.zoombit.repository;

import com.zoombit.domain.TestDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestDomain, String> {

}
