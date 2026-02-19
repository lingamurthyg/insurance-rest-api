package com.scale.global.insurance.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

class InsuranceRestApiApplicationTest {

    @Test
    void testMainMethodExists() {
        try {
            InsuranceRestApiApplication.class.getDeclaredMethod("main", String[].class);
            assertTrue(true);
        } catch (NoSuchMethodException e) {
            fail("main method not found");
        }
    }

    @Test
    void testApplicationClassNotNull() {
        assertNotNull(InsuranceRestApiApplication.class);
    }

    @Test
    void testApplicationClassIsPublic() {
        assertTrue(java.lang.reflect.Modifier.isPublic(InsuranceRestApiApplication.class.getModifiers()));
    }

    @Test
    void testApplicationHasSpringBootApplicationAnnotation() {
        assertNotNull(InsuranceRestApiApplication.class.getAnnotation(
                org.springframework.boot.autoconfigure.SpringBootApplication.class));
    }

    @Test
    void testApplicationPackage() {
        assertEquals("com.scale.global.insurance.app",
                InsuranceRestApiApplication.class.getPackageName());
    }

    @Test
    void testApplicationSimpleName() {
        assertEquals("InsuranceRestApiApplication",
                InsuranceRestApiApplication.class.getSimpleName());
    }

    @Test
    void testApplicationConstructorExists() {
        try {
            InsuranceRestApiApplication.class.getDeclaredConstructor();
            assertTrue(true);
        } catch (NoSuchMethodException e) {
            fail("Default constructor not found");
        }
    }

    @Test
    void testApplicationCanBeInstantiated() {
        assertDoesNotThrow(() -> new InsuranceRestApiApplication());
    }
}
