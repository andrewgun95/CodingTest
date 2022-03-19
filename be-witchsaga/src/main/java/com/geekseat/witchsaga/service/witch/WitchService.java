package com.geekseat.witchsaga.service.witch;

import com.geekseat.witchsaga.service.common.CrudService;
import com.geekseat.witchsaga.service.common.exceptions.DataNotFoundException;
import com.geekseat.witchsaga.service.witch.dto.KillingDTO;
import com.geekseat.witchsaga.service.witch.dto.WitchDTO;

public interface WitchService extends CrudService<WitchDTO> {

    void startKilling(KillingDTO dto) throws DataNotFoundException;

    void stopKilling(Long id) throws DataNotFoundException;

    Integer getInitialYear();

}
