package com.scale.global.insurance.app.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {

    @Test
    void testCustomerDTOBuilderWithAllFields() {
        CustomerDTO dto = CustomerDTO.builder()
                .insuranceNumber(1)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2020, 1, 1))
                .rate(new BigDecimal("150.50"))
                .build();

        assertNotNull(dto);
        assertEquals(1, dto.getInsuranceNumber());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), dto.getDateOfBirth());
        assertEquals(LocalDate.of(2020, 1, 1), dto.getInceptionOfThePolicy());
        assertEquals(new BigDecimal("150.50"), dto.getRate());
    }

    @Test
    void testCustomerDTONoArgsConstructor() {
        CustomerDTO dto = new CustomerDTO();
        assertNotNull(dto);
        assertNull(dto.getInsuranceNumber());
        assertNull(dto.getFirstName());
        assertNull(dto.getLastName());
        assertNull(dto.getRate());
    }

    @Test
    void testCustomerDTOAllArgsConstructor() {
        LocalDate birthDate = LocalDate.of(1985, 5, 15);
        LocalDate inceptionDate = LocalDate.of(2015, 3, 10);
        BigDecimal rate = new BigDecimal("200.75");
        CustomerDTO dto = new CustomerDTO(2, "Jane", "Smith", birthDate, inceptionDate, rate);

        assertNotNull(dto);
        assertEquals(2, dto.getInsuranceNumber());
        assertEquals("Jane", dto.getFirstName());
        assertEquals("Smith", dto.getLastName());
        assertEquals(birthDate, dto.getDateOfBirth());
        assertEquals(inceptionDate, dto.getInceptionOfThePolicy());
        assertEquals(rate, dto.getRate());
    }

    @Test
    void testCustomerDTOSetters() {
        CustomerDTO dto = new CustomerDTO();
        dto.setInsuranceNumber(3);
        dto.setFirstName("Alice");
        dto.setLastName("Johnson");
        dto.setDateOfBirth(LocalDate.of(1995, 12, 25));
        dto.setInceptionOfThePolicy(LocalDate.of(2018, 6, 1));
        dto.setRate(new BigDecimal("175.25"));

        assertEquals(3, dto.getInsuranceNumber());
        assertEquals("Alice", dto.getFirstName());
        assertEquals("Johnson", dto.getLastName());
        assertEquals(LocalDate.of(1995, 12, 25), dto.getDateOfBirth());
        assertEquals(LocalDate.of(2018, 6, 1), dto.getInceptionOfThePolicy());
        assertEquals(new BigDecimal("175.25"), dto.getRate());
    }

    @Test
    void testCustomerDTOGetters() {
        CustomerDTO dto = CustomerDTO.builder()
                .insuranceNumber(4)
                .firstName("Bob")
                .lastName("Brown")
                .dateOfBirth(LocalDate.of(1980, 3, 20))
                .inceptionOfThePolicy(LocalDate.of(2010, 9, 15))
                .rate(new BigDecimal("125.00"))
                .build();

        assertEquals(4, dto.getInsuranceNumber());
        assertEquals("Bob", dto.getFirstName());
        assertEquals("Brown", dto.getLastName());
        assertEquals(LocalDate.of(1980, 3, 20), dto.getDateOfBirth());
        assertEquals(LocalDate.of(2010, 9, 15), dto.getInceptionOfThePolicy());
        assertEquals(new BigDecimal("125.00"), dto.getRate());
    }

    @Test
    void testCustomerDTOWithNullValues() {
        CustomerDTO dto = CustomerDTO.builder()
                .insuranceNumber(null)
                .firstName(null)
                .lastName(null)
                .dateOfBirth(null)
                .inceptionOfThePolicy(null)
                .rate(null)
                .build();

        assertNotNull(dto);
        assertNull(dto.getInsuranceNumber());
        assertNull(dto.getFirstName());
        assertNull(dto.getLastName());
        assertNull(dto.getDateOfBirth());
        assertNull(dto.getInceptionOfThePolicy());
        assertNull(dto.getRate());
    }

    @Test
    void testCustomerDTOWithZeroRate() {
        CustomerDTO dto = CustomerDTO.builder()
                .insuranceNumber(5)
                .firstName("Test")
                .lastName("User")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2020, 1, 1))
                .rate(BigDecimal.ZERO)
                .build();

        assertNotNull(dto);
        assertEquals(BigDecimal.ZERO, dto.getRate());
    }

    @Test
    void testCustomerDTOWithNegativeRate() {
        CustomerDTO dto = CustomerDTO.builder()
                .insuranceNumber(6)
                .firstName("Negative")
                .lastName("Rate")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2020, 1, 1))
                .rate(new BigDecimal("-50.00"))
                .build();

        assertNotNull(dto);
        assertEquals(new BigDecimal("-50.00"), dto.getRate());
    }

    @Test
    void testCustomerDTOWithEmptyStrings() {
        CustomerDTO dto = CustomerDTO.builder()
                .insuranceNumber(7)
                .firstName("")
                .lastName("")
                .dateOfBirth(LocalDate.now())
                .inceptionOfThePolicy(LocalDate.now())
                .build();

        assertNotNull(dto);
        assertEquals("", dto.getFirstName());
        assertEquals("", dto.getLastName());
    }

    @Test
    void testCustomerDTOWithLongNames() {
        String longName = "A".repeat(100);
        CustomerDTO dto = CustomerDTO.builder()
                .insuranceNumber(8)
                .firstName(longName)
                .lastName(longName)
                .dateOfBirth(LocalDate.of(1988, 12, 31))
                .inceptionOfThePolicy(LocalDate.of(2010, 6, 15))
                .build();

        assertNotNull(dto);
        assertEquals(longName, dto.getFirstName());
        assertEquals(longName, dto.getLastName());
    }
}
