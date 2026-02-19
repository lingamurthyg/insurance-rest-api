package com.scale.global.insurance.app.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateAttributeConverterTest {

    private LocalDateAttributeConverter converter;

    @BeforeEach
    void setUp() {
        converter = new LocalDateAttributeConverter();
    }

    @Test
    void testConvertToDatabaseColumn() {
        LocalDate localDate = LocalDate.of(2023, 6, 15);
        Date result = converter.convertToDatabaseColumn(localDate);
        assertNotNull(result);
        assertEquals(Date.valueOf(localDate), result);
    }

    @Test
    void testConvertToDatabaseColumnWithNull() {
        Date result = converter.convertToDatabaseColumn(null);
        assertNull(result);
    }

    @Test
    void testConvertToDatabaseColumnWithCurrentDate() {
        LocalDate today = LocalDate.now();
        Date result = converter.convertToDatabaseColumn(today);
        assertNotNull(result);
        assertEquals(Date.valueOf(today), result);
    }

    @Test
    void testConvertToDatabaseColumnWithPastDate() {
        LocalDate pastDate = LocalDate.of(1990, 1, 1);
        Date result = converter.convertToDatabaseColumn(pastDate);
        assertNotNull(result);
        assertEquals(Date.valueOf(pastDate), result);
    }

    @Test
    void testConvertToEntityAttribute() {
        Date sqlDate = Date.valueOf("2023-06-15");
        LocalDate result = converter.convertToEntityAttribute(sqlDate);
        assertNotNull(result);
        assertEquals(LocalDate.of(2023, 6, 15), result);
    }

    @Test
    void testConvertToEntityAttributeWithNull() {
        LocalDate result = converter.convertToEntityAttribute(null);
        assertNull(result);
    }

    @Test
    void testConvertToEntityAttributeWithCurrentDate() {
        Date today = Date.valueOf(LocalDate.now());
        LocalDate result = converter.convertToEntityAttribute(today);
        assertNotNull(result);
        assertEquals(today.toLocalDate(), result);
    }

    @Test
    void testRoundTripConversion() {
        LocalDate original = LocalDate.of(2020, 12, 25);
        Date sqlDate = converter.convertToDatabaseColumn(original);
        LocalDate converted = converter.convertToEntityAttribute(sqlDate);
        assertEquals(original, converted);
    }

    @Test
    void testRoundTripConversionWithCurrentDate() {
        LocalDate original = LocalDate.now();
        Date sqlDate = converter.convertToDatabaseColumn(original);
        LocalDate converted = converter.convertToEntityAttribute(sqlDate);
        assertEquals(original, converted);
    }

    @Test
    void testConvertToEntityAttributeWithFutureDate() {
        Date futureDate = Date.valueOf(LocalDate.of(2050, 1, 1));
        LocalDate result = converter.convertToEntityAttribute(futureDate);
        assertNotNull(result);
        assertEquals(LocalDate.of(2050, 1, 1), result);
    }
}
