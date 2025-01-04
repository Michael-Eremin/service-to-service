package com.report.infrastructure.repositories;

import com.report.domain.entities.PlanDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanDataRepository extends JpaRepository<PlanDataEntity, Long> {

    @Query(nativeQuery = true, value = """
            select * from report.luk_plan_data limit 10
                """)
    List<PlanDataEntity> getPartPlanData();
}
