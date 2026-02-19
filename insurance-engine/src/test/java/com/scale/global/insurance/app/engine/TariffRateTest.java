package com.scale.global.insurance.app.engine;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TariffRateTest {

    private static class TestTariffRate implements TariffRate {
        @Override
        public BigDecimal getRate(Integer age) {
            return new BigDecimal("1.5");
        }

        @Override
        public BigDecimal getProgramPrice() {
            return new BigDecimal("100.0");
        }
    }

    @Test
    void testGetRate() {
        TariffRate tariffRate = new TestTariffRate();
        BigDecimal rate = tariffRate.getRate(25);
        assertNotNull(rate);
        assertEquals(new BigDecimal("1.5"), rate);
    }

    @Test
    void testGetRateWithNullAge() {
        TariffRate tariffRate = new TestTariffRate();
        BigDecimal rate = tariffRate.getRate(null);
        assertNotNull(rate);
    }

    @Test
    void testGetRateWithZeroAge() {
        TariffRate tariffRate = new TestTariffRate();
        BigDecimal rate = tariffRate.getRate(0);
        assertNotNull(rate);
        assertEquals(new BigDecimal("1.5"), rate);
    }

    @Test
    void testGetRateWithNegativeAge() {
        TariffRate tariffRate = new TestTariffRate();
        BigDecimal rate = tariffRate.getRate(-5);
        assertNotNull(rate);
    }

    @Test
    void testGetProgramPrice() {
        TariffRate tariffRate = new TestTariffRate();
        BigDecimal programPrice = tariffRate.getProgramPrice();
        assertNotNull(programPrice);
        assertEquals(new BigDecimal("100.0"), programPrice);
    }

    @Test
    void testGetProgramPriceNotNull() {
        TariffRate tariffRate = new TestTariffRate();
        BigDecimal programPrice = tariffRate.getProgramPrice();
        assertNotNull(programPrice);
        assertTrue(programPrice.compareTo(BigDecimal.ZERO) >= 0);
    }
}
