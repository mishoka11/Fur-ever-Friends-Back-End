package fontys.s3.Configuration_Tests.Authentication;

import fontys.s3.Configuration.logging.RequestLoggingFilterConfig;
import org.junit.jupiter.api.Test;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestLoggingFilterConfigTest {

    @Test
    void logFilter_shouldReturnConfiguredFilter() throws Exception {
        RequestLoggingFilterConfig config = new RequestLoggingFilterConfig();
        CommonsRequestLoggingFilter filter = config.logFilter();

        Method isIncludeQueryStringMethod = CommonsRequestLoggingFilter.class.getSuperclass().getDeclaredMethod("isIncludeQueryString");
        Method isIncludePayloadMethod = CommonsRequestLoggingFilter.class.getSuperclass().getDeclaredMethod("isIncludePayload");
        Method isIncludeHeadersMethod = CommonsRequestLoggingFilter.class.getSuperclass().getDeclaredMethod("isIncludeHeaders");

        isIncludeQueryStringMethod.setAccessible(true);
        isIncludePayloadMethod.setAccessible(true);
        isIncludeHeadersMethod.setAccessible(true);

        boolean includeQueryString = (boolean) isIncludeQueryStringMethod.invoke(filter);
        boolean includePayload = (boolean) isIncludePayloadMethod.invoke(filter);
        boolean includeHeaders = (boolean) isIncludeHeadersMethod.invoke(filter);

        assertTrue(includeQueryString);
        assertFalse(includePayload);
        assertFalse(includeHeaders);
    }
}
