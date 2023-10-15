package com.jpmorgan.gbce.stockmarket.core.utils;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Class holds the constants
 */
public class Constants {

    /**
     * Precision and Rounding used for the calculations
     */
    public static final MathContext MATH_CONTEXT = new MathContext(6, RoundingMode.HALF_EVEN);
}
