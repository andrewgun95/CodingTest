package com.geekseat.witchsaga.service.person;

import com.geekseat.witchsaga.entity.Person;
import com.geekseat.witchsaga.entity.PersonType;
import com.geekseat.witchsaga.repository.PersonRepository;
import com.geekseat.witchsaga.repository.WitchRepository;
import com.geekseat.witchsaga.service.person.impl.PersonServiceImpl;
import com.geekseat.witchsaga.service.person.mapper.PersonMapper;
import com.geekseat.witchsaga.service.witch.WitchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private WitchService witchService;

    @MockBean
    private WitchRepository witchRepository;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);
        personService = new PersonServiceImpl(personRepository, personMapper, witchService, witchRepository);
    }

    @Test
    public void givenPerson_whenFindAverage_thenGotValidResult() {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person();
        person1.setName("Jacob");
        person1.setGender(PersonType.MALE);
        person1.setAgeOfDeath(BigDecimal.valueOf(10));
        person1.setYearOfDeath(BigDecimal.valueOf(12));
        personList.add(person1);
        Person person2 = new Person();
        person2.setName("Smith");
        person2.setGender(PersonType.MALE);
        person2.setAgeOfDeath(BigDecimal.valueOf(13));
        person2.setYearOfDeath(BigDecimal.valueOf(17));
        personList.add(person2);

        Mockito.when(personRepository.findAll(Mockito.any(Specification.class))).thenReturn(personList);
        Mockito.when(witchService.getInitialYear()).thenReturn(1);

        Double result = personService.findAverage();
        Assertions.assertEquals(4.5, result);
    }

}
