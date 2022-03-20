package com.geekseat.witchsaga.repository;

import com.geekseat.witchsaga.entity.VillageInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties = {
        "spring.datasource.url = jdbc:h2:mem:test",
        "spring.jpa.database-platform = org.hibernate.dialect.H2Dialect"
})
public class VillageInfoRepositoryTest {

    @Autowired
    private VillageInfoRepository villageInfoRepository;

    @BeforeEach
    public void init() {
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(villageInfoRepository).isNotNull();
    }

    @Test
    public void givenEntity_whenSave_thenGotStored() {
        VillageInfo villageInfo = new VillageInfo();
        villageInfo.setMessage("The witch starting killing the villagers");
        villageInfo.setDate(new Date());

        villageInfoRepository.save(villageInfo);

        List<VillageInfo> result = villageInfoRepository.findAll();
        Assertions.assertTrue(result.size() > 0);
    }

}
