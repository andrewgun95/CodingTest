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
        int a = 1;
        if (year == a) {
            return a;
        }
        int b = 2;
        if (year == b) {
            return b;
        }

        int c = 0;

        for (int i = 2; i < year; i++) {
            c = (a + b) + 1;
            // swap variable
            a = b;
            b = c;
        }

        return c;
    }

}
