package com.geekseat.witchsaga.service.person;

import com.geekseat.witchsaga.service.common.CrudService;
import com.geekseat.witchsaga.service.person.dto.PersonDTO;

public interface PersonService extends CrudService<PersonDTO> {

    Double findAverage();

}
