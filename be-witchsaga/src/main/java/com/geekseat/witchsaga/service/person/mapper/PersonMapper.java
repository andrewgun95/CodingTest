package com.geekseat.witchsaga.service.person.mapper;

import com.geekseat.witchsaga.entity.Person;
import com.geekseat.witchsaga.service.common.GenericMapper;
import com.geekseat.witchsaga.service.person.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface PersonMapper extends GenericMapper<PersonDTO, Person> {

    @Mapping(target = "name", ignore = true)
    void updateEntity(PersonDTO dto, @MappingTarget Person person);

}
