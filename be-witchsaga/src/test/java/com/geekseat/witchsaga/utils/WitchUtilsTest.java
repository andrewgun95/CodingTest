package com.geekseat.witchsaga.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WitchUtilsTest {

    @Test
    public void givenNumber_whenCountKill_thenGotValidResult() {
        Assertions.assertEquals(0, WitchUtils.countKill(0));
        Assertions.assertEquals(1, WitchUtils.countKill(1));
        // Scenario 1 : 0 + 1 + 1 = 2
        Assertions.assertEquals(2, WitchUtils.countKill(2));
        // Scenario 2 : 1 + 2 + 1 = 4
        Assertions.assertEquals(4, WitchUtils.countKill(3));
        // Scenario 3 : 2 + 4 + 1 = 7
        Assertions.assertEquals(7, WitchUtils.countKill(4));
        // Scenario 4 : 4 + 7 + 1 = 12
        Assertions.assertEquals(12, WitchUtils.countKill(5));
        // Scenario 5 : 7 + 12 + 1 = 20
        Assertions.assertEquals(20, WitchUtils.countKill(6));
        // Scenario 6 : 20 + 12 + 1 = 30
        Assertions.assertEquals(33, WitchUtils.countKill(7));
    }


}
