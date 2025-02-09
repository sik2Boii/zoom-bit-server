package com.zoombit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "test_table")
@Getter
@Setter
public class TestDomain {

    @Id
    private String id;

    private String name;

    private String createdAt;
}
