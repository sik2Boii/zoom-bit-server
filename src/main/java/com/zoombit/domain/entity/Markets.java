package com.zoombit.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "markets")
@Getter
@Setter
public class Markets {

    @Id
    private String market;

    private String koreanName;

    private String englishName;

}
