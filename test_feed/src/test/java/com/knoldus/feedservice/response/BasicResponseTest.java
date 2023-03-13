package com.knoldus.feedservice.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BasicResponseTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link BasicResponse}
     *   <li>{@link BasicResponse#setStatusCode(String)}
     *   <li>{@link BasicResponse#setStatusText(String)}
     *   <li>{@link BasicResponse#getStatusCode()}
     *   <li>{@link BasicResponse#getStatusText()}
     * </ul>
     */
    @Test
    void testConstructor() {
        BasicResponse actualBasicResponse = new BasicResponse();
        actualBasicResponse.setStatusCode("Status Code");
        actualBasicResponse.setStatusText("Status Text");
        assertEquals("Status Code", actualBasicResponse.getStatusCode());
        assertEquals("Status Text", actualBasicResponse.getStatusText());
    }

    /**
     * Method under test: default or parameterless constructor of {@link BasicResponse}
     */
    @Test
    void testConstructor2() {
        BasicResponse actualBasicResponse = new BasicResponse();
        assertEquals("ok", actualBasicResponse.getStatusCode());
        assertEquals("ok", actualBasicResponse.getStatusText());
    }
}

