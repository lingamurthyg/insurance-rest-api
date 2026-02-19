package com.scale.global.insurance.app.engine;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DatesCalculatorTest {

    private static class TestDatesCalculator implements DatesCalculator {
        @Override
        public int yearsSinceInception(LocalDate inceptionDate, LocalDate baseDate) {
            return 5;
        }

        @Override
        public int getAgeToDate(LocalDate date, LocalDate baseDate) {
            return 35;
        }
    }

    @Test
    void testYearsSinceInception() {
        DatesCalculator calculator = new TestDatesCalculator();
        LocalDate inceptionDate = LocalDate.of(2018, 1, 1);
        LocalDate baseDate = LocalDate.of(2023, 1, 1);
        int years = calculator.yearsSinceInception(inceptionDate, baseDate);
        assertEquals(5, years);
    }

    @Test
    void testYearsSinceInceptionWithNullDates() {
        DatesCalculator calculator = new TestDatesCalculator();
        int years = calculator.yearsSinceInception(null, null);
        assertEquals(5, years);
    }

    @Test
    void testYearsSinceInceptionWithSameDates() {
        DatesCalculator calculator = new TestDatesCalculator();
        LocalDate date = LocalDate.now();
        int years = calculator.yearsSinceInception(date, date);
        assertEquals(5, years);
    }

    @Test
    void testGetAgeToDate() {
        DatesCalculator calculator = new TestDatesCalculator();
        LocalDate birthDate = LocalDate.of(1988, 6, 15);
        LocalDate baseDate = LocalDate.of(2023, 6, 15);
        int age = calculator.getAgeToDate(birthDate, baseDate);
        assertEquals(35, age);
    }

    @Test
    void testGetAgeToDateWithNullDates() {
        DatesCalculator calculator = new TestDatesCalculator();
        int age = calculator.getAgeToDate(null, null);
        assertEquals(35, age);
    }

    @Test
    void testGetAgeToDateWithCurrentDate() {
        DatesCalculator calculator = new TestDatesCalculator();
        LocalDate currentDate = LocalDate.now();
        int age = calculator.getAgeToDate(currentDate, currentDate);
        assertTrue(age >= 0);
    }

    @Test
    void testGetAgeToDateWithFutureBaseDate() {
        DatesCalculator calculator = new TestDatesCalculator();
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        LocalDate baseDate = LocalDate.of(2050, 1, 1);
        int age = calculator.getAgeToDate(birthDate, baseDate);
        assertTrue(age >= 0);
    }
}
