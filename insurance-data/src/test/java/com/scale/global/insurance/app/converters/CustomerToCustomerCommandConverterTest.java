package com.scale.global.insurance.app.converters;

import com.scale.global.insurance.app.engine.PriceCalculator;
import com.scale.global.insurance.app.entity.Customer;
import com.scale.global.insurance.app.model.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerToCustomerCommandConverterTest {

    @Mock
    private PriceCalculator priceCalculator;

    private CustomerToCustomerCommandConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CustomerToCustomerCommandConverter(priceCalculator);
    }

    @Test
    void testConvertWithValidCustomer() {
        Customer customer = Customer.builder()
                .insuranceNumber(1)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2020, 1, 1))
                .build();

        when(priceCalculator.calculateRate(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(new BigDecimal("150.50"));

        CustomerDTO result = converter.convert(customer);

        assertNotNull(result);
        assertEquals(1, result.getInsuranceNumber());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), result.getDateOfBirth());
        assertEquals(LocalDate.of(2020, 1, 1), result.getInceptionOfThePolicy());
        assertEquals(new BigDecimal("150.50"), result.getRate());
    }

    @Test
    void testConvertWithNullCustomer() {
        CustomerDTO result = converter.convert(null);
        assertNull(result);
    }

    @Test
    void testConvertWithNullFields() {
        Customer customer = Customer.builder()
                .insuranceNumber(null)
                .firstName(null)
                .lastName(null)
                .dateOfBirth(null)
                .inceptionOfThePolicy(null)
                .build();

        when(priceCalculator.calculateRate(any(), any())).thenReturn(BigDecimal.ZERO);

        CustomerDTO result = converter.convert(customer);

        assertNotNull(result);
        assertNull(result.getInsuranceNumber());
        assertNull(result.getFirstName());
        assertNull(result.getLastName());
    }

    @Test
    void testConvertCalculatesRate() {
        Customer customer = Customer.builder()
                .insuranceNumber(2)
                .firstName("Jane")
                .lastName("Smith")
                .dateOfBirth(LocalDate.of(1985, 5, 15))
                .inceptionOfThePolicy(LocalDate.of(2015, 3, 10))
                .build();

        BigDecimal expectedRate = new BigDecimal("200.75");
        when(priceCalculator.calculateRate(LocalDate.of(1985, 5, 15), LocalDate.of(2015, 3, 10)))
                .thenReturn(expectedRate);

        CustomerDTO result = converter.convert(customer);

        assertNotNull(result);
        assertEquals(expectedRate, result.getRate());
    }

    @Test
    void testConvertWithZeroRate() {
        Customer customer = Customer.builder()
                .insuranceNumber(3)
                .firstName("Test")
                .lastName("User")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2020, 1, 1))
                .build();

        when(priceCalculator.calculateRate(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(BigDecimal.ZERO);

        CustomerDTO result = converter.convert(customer);

        assertNotNull(result);
        assertEquals(BigDecimal.ZERO, result.getRate());
    }

    @Test
    void testConvertWithNegativeInsuranceNumber() {
        Customer customer = Customer.builder()
                .insuranceNumber(-1)
                .firstName("Test")
                .lastName("User")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2020, 1, 1))
                .build();

        when(priceCalculator.calculateRate(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(new BigDecimal("100.00"));

        CustomerDTO result = converter.convert(customer);

        assertNotNull(result);
        assertEquals(-1, result.getInsuranceNumber());
    }
}
