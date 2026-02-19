package com.scale.global.insurance.app.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {

    private SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        swaggerConfig = new SwaggerConfig();
    }

    @Test
    void testCustomOpenAPINotNull() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        assertNotNull(openAPI);
    }

    @Test
    void testCustomOpenAPIHasInfo() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        assertNotNull(openAPI.getInfo());
    }

    @Test
    void testCustomOpenAPITitle() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();
        assertEquals("Customer Insurance Service", info.getTitle());
    }

    @Test
    void testCustomOpenAPIDescription() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();
        assertEquals("Customer Insurance Service description", info.getDescription());
    }

    @Test
    void testCustomOpenAPIVersion() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();
        assertEquals("1.0", info.getVersion());
    }

    @Test
    void testCustomOpenAPIContact() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();
        Contact contact = info.getContact();
        assertNotNull(contact);
        assertEquals("FirstName LastName", contact.getName());
        assertEquals("http://www.global-scale.com/", contact.getUrl());
        assertEquals("info@global-scale.com", contact.getEmail());
    }

    @Test
    void testCustomOpenAPILicense() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();
        License license = info.getLicense();
        assertNotNull(license);
        assertEquals("Apache License Version 2.0", license.getName());
        assertEquals("https://www.apache.org/licenses/LICENSE-2.0", license.getUrl());
    }

    @Test
    void testCustomOpenAPITermsOfService() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();
        assertEquals("Commercial register number: HRB 246345", info.getTermsOfService());
    }

    @Test
    void testSwaggerConfigIsConfiguration() {
        assertNotNull(SwaggerConfig.class.getAnnotation(
                org.springframework.context.annotation.Configuration.class));
    }

    @Test
    void testCustomOpenAPIMethodHasBeanAnnotation() throws NoSuchMethodException {
        assertNotNull(SwaggerConfig.class.getMethod("customOpenAPI")
                .getAnnotation(org.springframework.context.annotation.Bean.class));
    }

    @Test
    void testSwaggerConfigConstructor() {
        assertDoesNotThrow(() -> new SwaggerConfig());
    }

    @Test
    void testCustomOpenAPIContactNotNull() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        assertNotNull(openAPI.getInfo().getContact());
    }

    @Test
    void testCustomOpenAPILicenseNotNull() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        assertNotNull(openAPI.getInfo().getLicense());
    }

    @Test
    void testCustomOpenAPIAllFieldsSet() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();
        assertNotNull(info.getTitle());
        assertNotNull(info.getDescription());
        assertNotNull(info.getVersion());
        assertNotNull(info.getContact());
        assertNotNull(info.getLicense());
        assertNotNull(info.getTermsOfService());
    }
}
