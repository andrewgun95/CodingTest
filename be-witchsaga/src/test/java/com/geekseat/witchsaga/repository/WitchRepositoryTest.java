package com.geekseat.witchsaga.repository;

import com.geekseat.witchsaga.entity.Witch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties = {
        "spring.datasource.url = jdbc:h2:mem:test",
        "spring.jpa.database-platform = org.hibernate.dialect.H2Dialect"
})
public class WitchRepositoryTest {

    @Autowired
    private WitchRepository witchRepository;

    @BeforeEach
    public void init() {
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(witchRepository).isNotNull();
    }

    @Test
    public void givenEntity_whenSave_thenGotStored() {
        Witch witch = new Witch();
        witch.setName("Jacob");
        witch.setIntro("Hello, I'm jacob. I'm witch and I will starting killing the villagers");
        witch.setDeathSentence("Goodbye. I'm leaving now");

        witchRepository.save(witch);

        Assertions.assertTrue(witchRepository.findByName("Jacob").isPresent());
        Assertions.assertTrue(witchRepository.findFirstByLeaveIsFalse().isPresent());
    }

}
