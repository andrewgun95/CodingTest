package com.geekseat.witchsaga.utils;

import java.math.BigInteger;

public class WitchUtils {

    /**
     * Count Kill, base on this formula :
     * Xn = (Xn-1 + Xn-2) + 1
     *
     * @param year year
     * @return kill count (number of villagers)
     */
    public static BigInteger countKill(long year) {
        BigInteger a = BigInteger.valueOf(1L);
        if (a.compareTo(BigInteger.valueOf(year)) == 0) {
            return a;
        }
        BigInteger b = BigInteger.valueOf(2L);
        if (b.compareTo(BigInteger.valueOf(year)) == 0) {
            return b;
        }

        BigInteger c = BigInteger.ZERO;

        for (long i = 2; i < year; i++) {
            c = a.add(b).add(BigInteger.ONE);
            // swap variable
            a = b;
            b = c;
        }

        return c;
    }

}
