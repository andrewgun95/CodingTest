package com.geekseat.witchsaga.service.witch.mapper;


import com.geekseat.witchsaga.entity.Witch;
import com.geekseat.witchsaga.service.common.GenericMapper;
import com.geekseat.witchsaga.service.witch.dto.WitchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface WitchMapper extends GenericMapper<WitchDTO, Witch> {

    @Mapping(target = "name", ignore = true)
    void updateEntity(WitchDTO dto, @MappingTarget Witch witch);

}
