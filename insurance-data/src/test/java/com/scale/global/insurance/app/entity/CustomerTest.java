package com.scale.global.insurance.app.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testCustomerBuilderWithAllFields() {
        Customer customer = Customer.builder()
                .insuranceNumber(1)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2020, 1, 1))
                .build();

        assertNotNull(customer);
        assertEquals(1, customer.getInsuranceNumber());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), customer.getDateOfBirth());
        assertEquals(LocalDate.of(2020, 1, 1), customer.getInceptionOfThePolicy());
    }

    @Test
    void testCustomerNoArgsConstructor() {
        Customer customer = new Customer();
        assertNotNull(customer);
        assertNull(customer.getInsuranceNumber());
        assertNull(customer.getFirstName());
        assertNull(customer.getLastName());
    }

    @Test
    void testCustomerAllArgsConstructor() {
        LocalDate birthDate = LocalDate.of(1985, 5, 15);
        LocalDate inceptionDate = LocalDate.of(2015, 3, 10);
        Customer customer = new Customer(2, "Jane", "Smith", birthDate, inceptionDate);

        assertNotNull(customer);
        assertEquals(2, customer.getInsuranceNumber());
        assertEquals("Jane", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals(birthDate, customer.getDateOfBirth());
        assertEquals(inceptionDate, customer.getInceptionOfThePolicy());
    }

    @Test
    void testCustomerSetters() {
        Customer customer = new Customer();
        customer.setInsuranceNumber(3);
        customer.setFirstName("Alice");
        customer.setLastName("Johnson");
        customer.setDateOfBirth(LocalDate.of(1995, 12, 25));
        customer.setInceptionOfThePolicy(LocalDate.of(2018, 6, 1));

        assertEquals(3, customer.getInsuranceNumber());
        assertEquals("Alice", customer.getFirstName());
        assertEquals("Johnson", customer.getLastName());
        assertEquals(LocalDate.of(1995, 12, 25), customer.getDateOfBirth());
        assertEquals(LocalDate.of(2018, 6, 1), customer.getInceptionOfThePolicy());
    }

    @Test
    void testCustomerGetters() {
        Customer customer = Customer.builder()
                .insuranceNumber(4)
                .firstName("Bob")
                .lastName("Brown")
                .dateOfBirth(LocalDate.of(1980, 3, 20))
                .inceptionOfThePolicy(LocalDate.of(2010, 9, 15))
                .build();

        assertEquals(4, customer.getInsuranceNumber());
        assertEquals("Bob", customer.getFirstName());
        assertEquals("Brown", customer.getLastName());
        assertEquals(LocalDate.of(1980, 3, 20), customer.getDateOfBirth());
        assertEquals(LocalDate.of(2010, 9, 15), customer.getInceptionOfThePolicy());
    }

    @Test
    void testCustomerWithNullValues() {
        Customer customer = Customer.builder()
                .insuranceNumber(null)
                .firstName(null)
                .lastName(null)
                .dateOfBirth(null)
                .inceptionOfThePolicy(null)
                .build();

        assertNotNull(customer);
        assertNull(customer.getInsuranceNumber());
        assertNull(customer.getFirstName());
        assertNull(customer.getLastName());
        assertNull(customer.getDateOfBirth());
        assertNull(customer.getInceptionOfThePolicy());
    }

    @Test
    void testCustomerWithEmptyStrings() {
        Customer customer = Customer.builder()
                .insuranceNumber(5)
                .firstName("")
                .lastName("")
                .dateOfBirth(LocalDate.now())
                .inceptionOfThePolicy(LocalDate.now())
                .build();

        assertNotNull(customer);
        assertEquals("", customer.getFirstName());
        assertEquals("", customer.getLastName());
    }

    @Test
    void testCustomerWithFutureDates() {
        LocalDate futureDate = LocalDate.of(2050, 1, 1);
        Customer customer = Customer.builder()
                .insuranceNumber(6)
                .firstName("Future")
                .lastName("Person")
                .dateOfBirth(futureDate)
                .inceptionOfThePolicy(futureDate)
                .build();

        assertNotNull(customer);
        assertEquals(futureDate, customer.getDateOfBirth());
        assertEquals(futureDate, customer.getInceptionOfThePolicy());
    }

    @Test
    void testCustomerWithNegativeInsuranceNumber() {
        Customer customer = Customer.builder()
                .insuranceNumber(-1)
                .firstName("Test")
                .lastName("User")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .inceptionOfThePolicy(LocalDate.of(2020, 1, 1))
                .build();

        assertNotNull(customer);
        assertEquals(-1, customer.getInsuranceNumber());
    }
}
