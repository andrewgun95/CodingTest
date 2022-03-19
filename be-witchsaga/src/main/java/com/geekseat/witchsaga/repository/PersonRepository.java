package com.geekseat.witchsaga.repository;

import com.geekseat.witchsaga.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    Optional<Person> findByName(String name);

}
