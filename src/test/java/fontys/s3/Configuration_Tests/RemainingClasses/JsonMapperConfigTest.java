//package fontys.s3.Configuration_Tests.RemainingClasses;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import fontys.s3.Configuration.serialization.JsonMapperConfig;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class JsonMapperConfigTest {
//
//    @Test
//    void testGetObjectMapper() {
//        JsonMapperConfig config = new JsonMapperConfig();
//        ObjectMapper mapper = config.getObjectMapper();
//
//        assertNotNull(mapper);
//        assertFalse(mapper.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)); // This should be false as per configuration
//        assertTrue(mapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)); // Ensure FAIL_ON_UNKNOWN_PROPERTIES is disabled
//        assertTrue(mapper.getRegisteredModuleIds().contains(JavaTimeModule.class.getName())); // Ensure JavaTimeModule is registered
//    }
//}
