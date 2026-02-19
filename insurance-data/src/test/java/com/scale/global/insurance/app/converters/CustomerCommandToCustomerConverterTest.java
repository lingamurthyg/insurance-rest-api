package com.scale.global.insurance.app.converters;

import com.scale.global.insurance.app.entity.Customer;
import com.scale.global.insurance.app.model.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCommandToCustomerConverterTest {

    private CustomerCommandToCustomerConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CustomerCommandToCustomerConverter();
    }

    @Test
    void testConvertWithValidCustomerDTO() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .insuranceNumber(1)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2020, 1, 1))
                .rate(new BigDecimal("150.50"))
                .build();

        Customer result = converter.convert(customerDTO);

        assertNotNull(result);
        assertEquals(1, result.getInsuranceNumber());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), result.getDateOfBirth());
        assertEquals(LocalDate.of(2020, 1, 1), result.getInceptionOfThePolicy());
    }

    @Test
    void testConvertWithNullCustomerDTO() {
        Customer result = converter.convert(null);
        assertNull(result);
    }

    @Test
    void testConvertWithNullFields() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .insuranceNumber(null)
                .firstName(null)
                .lastName(null)
                .dateOfBirth(null)
                .inceptionOfThePolicy(null)
                .rate(null)
                .build();

        Customer result = converter.convert(customerDTO);

        assertNotNull(result);
        assertNull(result.getInsuranceNumber());
        assertNull(result.getFirstName());
        assertNull(result.getLastName());
        assertNull(result.getDateOfBirth());
        assertNull(result.getInceptionOfThePolicy());
    }

    @Test
    void testConvertDoesNotCopyRate() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .insuranceNumber(2)
                .firstName("Jane")
                .lastName("Smith")
                .dateOfBirth(LocalDate.of(1985, 5, 15))
                .inceptionOfThePolicy(LocalDate.of(2015, 3, 10))
                .rate(new BigDecimal("200.75"))
                .build();

        Customer result = converter.convert(customerDTO);

        assertNotNull(result);
        assertEquals(2, result.getInsuranceNumber());
        assertEquals("Jane", result.getFirstName());
    }

    @Test
    void testConvertWithEmptyStrings() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .insuranceNumber(3)
                .firstName("")
                .lastName("")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2020, 1, 1))
                .build();

        Customer result = converter.convert(customerDTO);

        assertNotNull(result);
        assertEquals("", result.getFirstName());
        assertEquals("", result.getLastName());
    }

    @Test
    void testConvertWithZeroInsuranceNumber() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .insuranceNumber(0)
                .firstName("Test")
                .lastName("User")
                .dateOfBirth(LocalDate.of(1995, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2018, 1, 1))
                .build();

        Customer result = converter.convert(customerDTO);

        assertNotNull(result);
        assertEquals(0, result.getInsuranceNumber());
    }

    @Test
    void testConvertWithLongNames() {
        String longName = "A".repeat(100);
        CustomerDTO customerDTO = CustomerDTO.builder()
                .insuranceNumber(4)
                .firstName(longName)
                .lastName(longName)
                .dateOfBirth(LocalDate.of(1988, 12, 31))
                .inceptionOfThePolicy(LocalDate.of(2010, 6, 15))
                .build();

        Customer result = converter.convert(customerDTO);

        assertNotNull(result);
        assertEquals(longName, result.getFirstName());
        assertEquals(longName, result.getLastName());
    }
}
