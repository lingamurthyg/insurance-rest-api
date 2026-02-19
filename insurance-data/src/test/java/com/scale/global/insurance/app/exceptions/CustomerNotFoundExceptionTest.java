package com.scale.global.insurance.app.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerNotFoundExceptionTest {

    @Test
    void testExceptionWithId() {
        Integer id = 123;
        CustomerNotFoundException exception = new CustomerNotFoundException(id);

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Customer not found with id = "));
        assertTrue(exception.getMessage().contains("123"));
    }

    @Test
    void testExceptionMessage() {
        Integer id = 456;
        CustomerNotFoundException exception = new CustomerNotFoundException(id);

        String expectedMessage = "Customer not found with id = 456";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testExceptionWithIdAndCause() {
        Integer id = 789;
        Throwable cause = new RuntimeException("Database connection failed");
        CustomerNotFoundException exception = new CustomerNotFoundException(id, cause);

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Customer not found with id = 789"));
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testExceptionWithNullId() {
        CustomerNotFoundException exception = new CustomerNotFoundException(null);

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("null"));
    }

    @Test
    void testExceptionWithZeroId() {
        Integer id = 0;
        CustomerNotFoundException exception = new CustomerNotFoundException(id);

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("0"));
    }

    @Test
    void testExceptionWithNegativeId() {
        Integer id = -1;
        CustomerNotFoundException exception = new CustomerNotFoundException(id);

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("-1"));
    }

    @Test
    void testExceptionIsRuntimeException() {
        CustomerNotFoundException exception = new CustomerNotFoundException(1);
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testExceptionWithCauseMessage() {
        Integer id = 999;
        Throwable cause = new IllegalArgumentException("Invalid argument");
        CustomerNotFoundException exception = new CustomerNotFoundException(id, cause);

        assertNotNull(exception.getCause());
        assertEquals("Invalid argument", exception.getCause().getMessage());
    }

    @Test
    void testExceptionWithNullCause() {
        Integer id = 100;
        CustomerNotFoundException exception = new CustomerNotFoundException(id, null);

        assertNotNull(exception);
        assertNull(exception.getCause());
    }

    @Test
    void testExceptionMessageFormat() {
        Integer id = 555;
        CustomerNotFoundException exception = new CustomerNotFoundException(id);

        String message = exception.getMessage();
        assertTrue(message.startsWith("Customer not found with id = "));
        assertTrue(message.endsWith("555"));
    }
}
