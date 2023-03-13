package com.knoldus.feedservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ApiExceptionTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ApiException#ApiException(String, HttpStatus, int, ZonedDateTime)}
     *   <li>{@link ApiException#getCode()}
     *   <li>{@link ApiException#getMessage()}
     *   <li>{@link ApiException#getStatus()}
     *   <li>{@link ApiException#getZoneDateTime()}
     * </ul>
     */
    @Test
    void testConstructor() {
        ApiException actualApiException = new ApiException("An error occurred", HttpStatus.CONTINUE, 1, null);

        assertEquals(1, actualApiException.getCode());
        assertEquals("An error occurred", actualApiException.getMessage());
        assertEquals(HttpStatus.CONTINUE, actualApiException.getStatus());
        assertNull(actualApiException.getZoneDateTime());
    }
}

