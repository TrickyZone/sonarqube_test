package com.knoldus.feedservice.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ResponseStatusTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ResponseStatus#valueOf(String)}
     *   <li>{@link ResponseStatus#getCode()}
     *   <li>{@link ResponseStatus#getMessage()}
     *   <li>{@link ResponseStatus#getText()}
     * </ul>
     */
    @Test
    void testValueOf() {
        ResponseStatus actualValueOfResult = ResponseStatus.valueOf("NOT_FOUND");
        assertEquals("not.found", actualValueOfResult.getCode());
        assertEquals("User not found", actualValueOfResult.getMessage());
        assertEquals("Not Found", actualValueOfResult.getText());
    }
}

