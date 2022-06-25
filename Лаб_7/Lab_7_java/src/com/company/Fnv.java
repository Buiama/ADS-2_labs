package com.company;

import java.math.BigInteger;

public class Fnv {
    private static final BigInteger INIT32  = new BigInteger("811c9dc5",16);
    private static final BigInteger PRIME32 = new BigInteger("01000193",16);
    private static final BigInteger MOD32   = new BigInteger("2").pow(32);

    public BigInteger fnv1a_32(byte[] data) {
        BigInteger hash = INIT32;

        for (byte b : data) {
            hash = hash.xor(BigInteger.valueOf((int) b & 0xff));
            hash = hash.multiply(PRIME32).mod(MOD32);
        }

        return hash;
    }
}
