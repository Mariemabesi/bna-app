package com.example.bna.repository;

import com.example.bna.model.MonthlyStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyStatRepository extends JpaRepository<MonthlyStat, Long> {

    // Derived query method (if MonthlyStat has a field 'user' with 'id')
    List<MonthlyStat> findByUserId(Long userId);

    // Alternative JPQL query method (uncomment if you prefer this)
    /*
    @Query("SELECT m FROM MonthlyStat m WHERE m.user.id = :userId")
    List<MonthlyStat> findByUserId(@Param("userId") Long userId);
    */
}
