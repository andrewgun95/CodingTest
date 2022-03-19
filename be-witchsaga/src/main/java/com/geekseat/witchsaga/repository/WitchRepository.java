package com.geekseat.witchsaga.repository;

import com.geekseat.witchsaga.entity.Witch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WitchRepository extends JpaRepository<Witch, Long> {

    Optional<Witch> findByName(String name);

    Optional<Witch> findFirstByLeaveIsFalse();

}
