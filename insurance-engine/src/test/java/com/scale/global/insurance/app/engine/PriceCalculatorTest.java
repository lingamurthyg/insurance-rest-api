package com.scale.global.insurance.app.engine;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    private static class TestPriceCalculator implements PriceCalculator {
        @Override
        public BigDecimal calculateRate(LocalDate birthDate, LocalDate inceptionDate) {
            return new BigDecimal("150.50");
        }
    }

    @Test
    void testCalculateRate() {
        PriceCalculator calculator = new TestPriceCalculator();
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        LocalDate inceptionDate = LocalDate.of(2020, 1, 1);
        BigDecimal rate = calculator.calculateRate(birthDate, inceptionDate);
        assertNotNull(rate);
        assertEquals(new BigDecimal("150.50"), rate);
    }

    @Test
    void testCalculateRateWithNullBirthDate() {
        PriceCalculator calculator = new TestPriceCalculator();
        LocalDate inceptionDate = LocalDate.of(2020, 1, 1);
        BigDecimal rate = calculator.calculateRate(null, inceptionDate);
        assertNotNull(rate);
    }

    @Test
    void testCalculateRateWithNullInceptionDate() {
        PriceCalculator calculator = new TestPriceCalculator();
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        BigDecimal rate = calculator.calculateRate(birthDate, null);
        assertNotNull(rate);
    }

    @Test
    void testCalculateRateWithBothDatesNull() {
        PriceCalculator calculator = new TestPriceCalculator();
        BigDecimal rate = calculator.calculateRate(null, null);
        assertNotNull(rate);
    }

    @Test
    void testCalculateRateWithCurrentDates() {
        PriceCalculator calculator = new TestPriceCalculator();
        LocalDate currentDate = LocalDate.now();
        BigDecimal rate = calculator.calculateRate(currentDate, currentDate);
        assertNotNull(rate);
        assertTrue(rate.compareTo(BigDecimal.ZERO) >= 0);
    }

    @Test
    void testCalculateRateWithPastDates() {
        PriceCalculator calculator = new TestPriceCalculator();
        LocalDate birthDate = LocalDate.of(1980, 1, 1);
        LocalDate inceptionDate = LocalDate.of(2010, 6, 1);
        BigDecimal rate = calculator.calculateRate(birthDate, inceptionDate);
        assertNotNull(rate);
    }
}
