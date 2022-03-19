package com.geekseat.witchsaga.service.village.mapper;

import com.geekseat.witchsaga.entity.VillageInfo;
import com.geekseat.witchsaga.service.common.GenericMapper;
import com.geekseat.witchsaga.service.village.dto.VillageInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface VillageInfoMapper extends GenericMapper<VillageInfoDTO, VillageInfo> {
}
