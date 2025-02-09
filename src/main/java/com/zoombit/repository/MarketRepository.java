package com.zoombit.repository;

import com.zoombit.domain.entity.Markets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarketRepository extends JpaRepository<Markets, String> {

    @Query("SELECT m.market FROM Markets m")
    List<String> findAllMarketIds();

}
