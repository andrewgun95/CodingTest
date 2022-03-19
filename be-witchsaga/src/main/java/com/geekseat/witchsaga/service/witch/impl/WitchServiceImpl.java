package com.geekseat.witchsaga.service.witch.impl;

import com.geekseat.witchsaga.entity.VillageInfo;
import com.geekseat.witchsaga.entity.Witch;
import com.geekseat.witchsaga.repository.VillageInfoRepository;
import com.geekseat.witchsaga.repository.WitchRepository;
import com.geekseat.witchsaga.service.common.exceptions.DataNotFoundException;
import com.geekseat.witchsaga.service.witch.WitchService;
import com.geekseat.witchsaga.service.witch.dto.KillingDTO;
import com.geekseat.witchsaga.service.witch.dto.WitchDTO;
import com.geekseat.witchsaga.service.witch.mapper.WitchMapper;
import com.geekseat.witchsaga.utils.WitchUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class WitchServiceImpl implements WitchService {

    private static final String DEFAULT_KILLING_CYCLE = "0 0/1 * * * ?";

    private final TaskScheduler scheduler;
    private final VillageInfoRepository villageInfoRepository;
    private final WitchRepository witchRepository;
    private final WitchMapper witchMapper;

    private final AtomicInteger currentYear = new AtomicInteger(0);

    private ScheduledFuture<?> future;
    private Integer initialYear;

    public WitchServiceImpl(TaskScheduler scheduler,
                            VillageInfoRepository villageInfoRepository,
                            WitchRepository witchRepository,
                            WitchMapper witchMapper) {
        this.scheduler = scheduler;
        this.villageInfoRepository = villageInfoRepository;
        this.witchRepository = witchRepository;
        this.witchMapper = witchMapper;
    }

    @Transactional
    @Override
    public WitchDTO createOrUpdate(WitchDTO dto) {
        Optional<Witch> optional = witchRepository.findByName(dto.getName());
        Witch witch;
        if (optional.isPresent()) {
            witch = optional.get();
            witchMapper.updateEntity(dto, witch);
        } else {
            witch = witchRepository.save(witchMapper.mapDTOtoEntity(dto));
        }
        return witchMapper.mapEntityToDTO(witch);
    }

    @Transactional(readOnly = true)
    @Override
    public WitchDTO findByOne(Long id) throws DataNotFoundException {
        return witchMapper.mapEntityToDTO(
                witchRepository.findById(id)
                        .orElseThrow(() -> new DataNotFoundException("User is not found"))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<WitchDTO> findByAll() {
        return witchMapper.mapEntitiesToDTOs(witchRepository.findAll());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        witchRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void startKilling(KillingDTO dto) throws DataNotFoundException {
        Witch witch = witchRepository.findById(dto.getWitchId())
                .orElseThrow(() -> new DataNotFoundException("Witch is not found"));

        VillageInfo introInfo = new VillageInfo();
        introInfo.setMessage(witch.getIntro());
        introInfo.setDate(new Date());

        log.info("Start Killing : {}", introInfo.getMessage());

        villageInfoRepository.save(introInfo);

        initialYear = dto.getInitialYear();
        currentYear.set(dto.getInitialYear());

        future = scheduler.schedule(() -> {
            synchronized (this) {
                VillageInfo killingInfo = new VillageInfo();

                Integer year = currentYear.getAndIncrement();

                killingInfo.setMessage(constructKillingMessage(year));
                killingInfo.setDate(new Date());

                log.info("Killing Info : {}", killingInfo.getMessage());

                villageInfoRepository.save(killingInfo);
            }
        }, new CronTrigger(
                StringUtils.hasText(dto.getKillingCycle()) ? dto.getKillingCycle() : DEFAULT_KILLING_CYCLE
        ));
    }

    private String constructKillingMessage(Integer year) {
        return String.format("On the %s%s year she kills ", year, getSuffix(year)) + WitchUtils.countKill(year);
    }

    private String getSuffix(Integer number) {
        if (number < 1) {
            return "";
        } else {
            int result = number % 10;
            if (result == 1)
                return "st";
            if (result == 2)
                return "nd";
            if (result == 3)
                return "rd";
            else
                return "th";
        }
    }

    @Transactional
    @Override
    public synchronized void stopKilling(Long id) throws DataNotFoundException {
        if (future != null) {
            future.cancel(true);

            Witch witch = witchRepository.findById(id)
                    .orElseThrow(() -> new DataNotFoundException("Witch is not found"));
            witch.setLeave(true);
            VillageInfo deathInfo = new VillageInfo();
            deathInfo.setMessage(witch.getDeathSentence());
            villageInfoRepository.save(deathInfo);
        }
    }

    @Override
    public Integer getInitialYear() {
        return initialYear;
    }

}
