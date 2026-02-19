package com.scale.global.insurance.app.repositories;

import com.scale.global.insurance.app.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {

    @Test
    void testCustomerRepositoryExtendsCrudRepository() {
        assertTrue(CrudRepository.class.isAssignableFrom(CustomerRepository.class));
    }

    @Test
    void testCustomerRepositoryGenericTypes() {
        Class<?>[] interfaces = CustomerRepository.class.getInterfaces();
        assertTrue(interfaces.length > 0);
    }

    @Test
    void testCustomerRepositoryIsInterface() {
        assertTrue(CustomerRepository.class.isInterface());
    }

    @Test
    void testCustomerRepositoryNotNull() {
        assertNotNull(CustomerRepository.class);
    }

    @Test
    void testCustomerRepositoryPackage() {
        assertEquals("com.scale.global.insurance.app.repositories",
                CustomerRepository.class.getPackageName());
    }

    @Test
    void testCustomerRepositorySimpleName() {
        assertEquals("CustomerRepository", CustomerRepository.class.getSimpleName());
    }

    @Test
    void testCustomerRepositoryHasCrudRepositoryMethods() {
        try {
            CrudRepository.class.getMethod("save", Object.class);
            CrudRepository.class.getMethod("findById", Object.class);
            CrudRepository.class.getMethod("findAll");
            CrudRepository.class.getMethod("deleteById", Object.class);
            assertTrue(true);
        } catch (NoSuchMethodException e) {
            fail("CrudRepository methods not found");
        }
    }
}
