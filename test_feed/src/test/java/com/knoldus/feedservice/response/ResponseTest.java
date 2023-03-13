package com.knoldus.feedservice.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ResponseTest {
    /**
     * Method under test: {@link Response#setStatusText(String)}
     */
    @Test
    void testSetStatusText() {
        Response<Object> response = new Response<>();
        Response<Object> actualSetStatusTextResult = response.setStatusText("Status Text");
        assertSame(response, actualSetStatusTextResult);
        assertEquals("Status Text", actualSetStatusTextResult.getStatusText());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Response}
     *   <li>{@link Response#setData(Object)}
     *   <li>{@link Response#getData()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Response<Object> actualResponse = new Response<>();
        Response<Object> actualSetDataResult = actualResponse.setData("Data");
        assertEquals("Data", actualResponse.getData());
        assertEquals("ok", actualResponse.getStatusText());
        assertEquals("ok", actualResponse.getStatusCode());
        assertSame(actualResponse, actualSetDataResult);
    }

    /**
     * Method under test: {@link Response#setStatusCode(String)}
     */
    @Test
    void testSetStatusCode() {
        Response<Object> response = new Response<>();
        Response<Object> actualSetStatusCodeResult = response.setStatusCode("Status Code");
        assertSame(response, actualSetStatusCodeResult);
        assertEquals("Status Code", actualSetStatusCodeResult.getStatusCode());
    }
}

