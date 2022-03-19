package com.geekseat.witchsaga.utils;

public class WitchUtils {

    /**
     * Count Kill, base on this formula :
     * Xn = (Xn-1 + Xn-2) + 1
     *
     * @param year year
     * @return kill count (number of villagers)
     */
    public static Integer countKill(Integer year) {
        if (year < 0) {
            return -1;
        } else if (year < 2) {
            return year;
        } else {
            return countKill(year - 1) + countKill(year - 2) + 1;
        }
    }

}
