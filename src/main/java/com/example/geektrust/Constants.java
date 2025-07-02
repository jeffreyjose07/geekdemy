package com.example.geektrust;

import java.math.BigDecimal;

/**
 * Holds constants that are referenced from more than one component. If at any
 * point all such shared constants are removed, this class should be deleted as
 * well to avoid an unnecessary utility holder.
 */
public final class Constants {
    /** Fee charged for opting into the PRO membership. */
    public static final BigDecimal PRO_MEMBERSHIP_FEE = new BigDecimal("200.00");

    /** Minimum number of programmes required for the B4G1 coupon to apply. */
    public static final int B4G1_MIN_PROGRAMS = 4;

    private Constants() {
        // Prevent instantiation
    }
}
