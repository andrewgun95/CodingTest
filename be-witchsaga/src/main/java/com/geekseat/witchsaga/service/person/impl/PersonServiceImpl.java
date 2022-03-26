package com.geekseat.witchsaga.service.person.impl;

import com.geekseat.witchsaga.entity.Person;
import com.geekseat.witchsaga.repository.PersonRepository;
import com.geekseat.witchsaga.repository.WitchRepository;
import com.geekseat.witchsaga.service.common.exceptions.DataNotFoundException;
import com.geekseat.witchsaga.service.person.PersonService;
import com.geekseat.witchsaga.service.person.dto.PersonDTO;
import com.geekseat.witchsaga.service.person.mapper.PersonMapper;
import com.geekseat.witchsaga.service.witch.WitchService;
import com.geekseat.witchsaga.utils.WitchUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final WitchService witchService;
    private final WitchRepository witchRepository;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper, WitchService witchService, WitchRepository witchRepository) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.witchService = witchService;
        this.witchRepository = witchRepository;
    }

    @Transactional
    @Override
    public PersonDTO createOrUpdate(PersonDTO dto) {
        Optional<Person> optional = personRepository.findByName(dto.getName());
        Person person;
        if (optional.isPresent()) {
            person = optional.get();
            personMapper.updateEntity(dto, person);
        } else {
            person = personRepository.save(personMapper.mapDTOtoEntity(dto));
        }
        return personMapper.mapEntityToDTO(person);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDTO findByOne(Long id) throws DataNotFoundException {
        return personMapper.mapEntityToDTO(
                personRepository.findById(id)
                        .orElseThrow(() -> new DataNotFoundException("Person is not found"))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonDTO> findByAll() {
        return personMapper.mapEntitiesToDTOs(personRepository.findAll());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Double findAverage() {
        List<Person> personList = personRepository.findAll((root, query, criteria) -> criteria.and(
                criteria.greaterThan(root.get("ageOfDeath"), 0),
                criteria.greaterThan(root.get("yearOfDeath"), 0)
        ));

        double result = 0.0;

        Integer initialYear = witchService.getInitialYear();
        for (Person person : personList) {
            BigDecimal bornOfYear = person.getYearOfDeath().subtract(person.getAgeOfDeath());
            if (bornOfYear.intValue() < initialYear) {
                return -1.0;
            }

            result += WitchUtils.countKill(bornOfYear.longValue()).doubleValue() / personList.size();
        }

        if (result > 0.0) {
            witchRepository.findFirstByLeaveIsFalse().ifPresent(witch -> {
                try {
                    witchService.stopKilling(witch.getId());
                } catch (DataNotFoundException e) {
                    log.error(e.getMessage(), e.getCause());
                }
            });
        }

        return result;
    }

}
