package com.geekseat.witchsaga.repository;

import com.geekseat.witchsaga.entity.VillageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageInfoRepository extends JpaRepository<VillageInfo, Long> {
}
