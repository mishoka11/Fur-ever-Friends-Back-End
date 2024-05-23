package fontys.s3.Configuration_Tests.RemainingClasses;

import fontys.s3.Configuration.swagger.SwaggerConfiguration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigurationTest {

    @Test
    void testCustomizeOpenAPI() {
        SwaggerConfiguration config = new SwaggerConfiguration();
        OpenAPI openAPI = config.customizeOpenAPI();

        assertNotNull(openAPI);
        Components components = openAPI.getComponents();
        assertNotNull(components);
        SecurityScheme securityScheme = components.getSecuritySchemes().get("bearerAuth");
        assertNotNull(securityScheme);
        assertEquals(SecurityScheme.Type.HTTP, securityScheme.getType());
        assertEquals("bearer", securityScheme.getScheme());
    }
}