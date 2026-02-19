package com.scale.global.insurance.app.exceptionHandling;

import com.scale.global.insurance.app.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler exceptionHandler;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        exceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    void testHandleCustomerNotFoundException() {
        Integer customerId = 123;
        CustomerNotFoundException exception = new CustomerNotFoundException(customerId);

        ResponseEntity<Object> response = exceptionHandler.handleThereIsNoSuchUserException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Map);

        @SuppressWarnings("unchecked")
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertTrue(body.containsKey("Error"));
        assertTrue(body.get("Error").contains("Customer not found with id = 123"));
    }

    @Test
    void testHandleCustomerNotFoundExceptionWithDifferentId() {
        Integer customerId = 999;
        CustomerNotFoundException exception = new CustomerNotFoundException(customerId);

        ResponseEntity<Object> response = exceptionHandler.handleThereIsNoSuchUserException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        @SuppressWarnings("unchecked")
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertTrue(body.get("Error").contains("999"));
    }

    @Test
    void testHandleMethodArgumentNotValid() {
        FieldError fieldError = new FieldError("customerDTO", "firstName", "Min size is 2 and max size is 100");
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<Object> response = exceptionHandler.handleMethodArgumentNotValid(
                methodArgumentNotValidException, new HttpHeaders(), HttpStatusCode.valueOf(400), webRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Map);

        @SuppressWarnings("unchecked")
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertTrue(body.containsKey("firstName"));
        assertEquals("Min size is 2 and max size is 100", body.get("firstName"));
    }

    @Test
    void testHandleMethodArgumentNotValidWithMultipleErrors() {
        FieldError fieldError1 = new FieldError("customerDTO", "firstName", "First name is required");
        FieldError fieldError2 = new FieldError("customerDTO", "lastName", "Last name is required");
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError1, fieldError2));

        ResponseEntity<Object> response = exceptionHandler.handleMethodArgumentNotValid(
                methodArgumentNotValidException, new HttpHeaders(), HttpStatusCode.valueOf(400), webRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        @SuppressWarnings("unchecked")
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertEquals(2, body.size());
        assertTrue(body.containsKey("firstName"));
        assertTrue(body.containsKey("lastName"));
    }

    @Test
    void testExceptionHandlerIsControllerAdvice() {
        assertNotNull(ControllerExceptionHandler.class.getAnnotation(
                org.springframework.web.bind.annotation.ControllerAdvice.class));
    }

    @Test
    void testExceptionHandlerExtendsResponseEntityExceptionHandler() {
        assertTrue(org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler.class
                .isAssignableFrom(ControllerExceptionHandler.class));
    }

    @Test
    void testHandleCustomerNotFoundExceptionWithNullId() {
        CustomerNotFoundException exception = new CustomerNotFoundException(null);

        ResponseEntity<Object> response = exceptionHandler.handleThereIsNoSuchUserException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        @SuppressWarnings("unchecked")
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertNotNull(body.get("Error"));
    }

    @Test
    void testHandleMethodArgumentNotValidReturnsMap() {
        FieldError fieldError = new FieldError("customerDTO", "dateOfBirth", "Date should be in past or to be present date");
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<Object> response = exceptionHandler.handleMethodArgumentNotValid(
                methodArgumentNotValidException, new HttpHeaders(), HttpStatusCode.valueOf(400), webRequest);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Map);
    }
}
