package com.geekseat.witchsaga.repository;

import com.geekseat.witchsaga.entity.Person;
import com.geekseat.witchsaga.entity.PersonType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties = {
        "spring.datasource.url = jdbc:h2:mem:test",
        "spring.jpa.database-platform = org.hibernate.dialect.H2Dialect"
})
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void init() {
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(personRepository).isNotNull();
    }

    @Test
    public void givenEntity_whenSave_thenGotStored() {
        Person person1 = new Person();
        person1.setName("Jacob");
        person1.setGender(PersonType.MALE);
        person1.setAgeOfDeath(BigDecimal.valueOf(10));
        person1.setYearOfDeath(BigDecimal.valueOf(12));

        Person result = personRepository.save(person1);

        Assertions.assertTrue(personRepository.findById(result.getId()).isPresent());

        Person person2 = new Person();
        person2.setName("Smith");
        person2.setGender(PersonType.MALE);
        person2.setAgeOfDeath(BigDecimal.valueOf(13));
        person2.setYearOfDeath(BigDecimal.valueOf(17));

        personRepository.save(person2);

        Assertions.assertTrue(personRepository.findByName("Smith").isPresent());
    }


}
