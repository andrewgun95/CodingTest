package com.geekseat.witchsaga.service.village.impl;

import com.geekseat.witchsaga.repository.VillageInfoRepository;
import com.geekseat.witchsaga.service.village.VillageInfoService;
import com.geekseat.witchsaga.service.village.dto.VillageInfoDTO;
import com.geekseat.witchsaga.service.village.mapper.VillageInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VillageInfoServiceImpl implements VillageInfoService {

    private final VillageInfoRepository villageInfoRepository;
    private final VillageInfoMapper villageInfoMapper;

    public VillageInfoServiceImpl(VillageInfoRepository villageInfoRepository,
                                  VillageInfoMapper villageInfoMapper) {
        this.villageInfoRepository = villageInfoRepository;
        this.villageInfoMapper = villageInfoMapper;
    }

    @Transactional
    @Override
    public List<VillageInfoDTO> findByAll() {
        return villageInfoMapper.mapEntitiesToDTOs(villageInfoRepository.findAll());
    }

}
