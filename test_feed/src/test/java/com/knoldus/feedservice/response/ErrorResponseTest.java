package com.knoldus.feedservice.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ErrorResponseTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorResponse#ErrorResponse()}
     *   <li>{@link ErrorResponse#setDetails(List)}
     *   <li>{@link ErrorResponse#setMessage(String)}
     *   <li>{@link ErrorResponse#getDetails()}
     *   <li>{@link ErrorResponse#getMessage()}
     * </ul>
     */
    @Test
    void testConstructor() {
        ErrorResponse<Object> actualErrorResponse = new ErrorResponse<>();
        ArrayList<String> stringList = new ArrayList<>();
        actualErrorResponse.setDetails(stringList);
        actualErrorResponse.setMessage("Not all who wander are lost");
        assertSame(stringList, actualErrorResponse.getDetails());
        assertEquals("Not all who wander are lost", actualErrorResponse.getMessage());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorResponse#ErrorResponse(String, List)}
     *   <li>{@link ErrorResponse#setDetails(List)}
     *   <li>{@link ErrorResponse#setMessage(String)}
     *   <li>{@link ErrorResponse#getDetails()}
     *   <li>{@link ErrorResponse#getMessage()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ArrayList<String> stringList = new ArrayList<>();
        ErrorResponse<Object> actualErrorResponse = new ErrorResponse<>("Not all who wander are lost", stringList);
        ArrayList<String> stringList1 = new ArrayList<>();
        actualErrorResponse.setDetails(stringList1);
        actualErrorResponse.setMessage("Not all who wander are lost");
        List<String> details = actualErrorResponse.getDetails();
        assertSame(stringList1, details);
        assertEquals(stringList, details);
        assertEquals("Not all who wander are lost", actualErrorResponse.getMessage());
    }
}

