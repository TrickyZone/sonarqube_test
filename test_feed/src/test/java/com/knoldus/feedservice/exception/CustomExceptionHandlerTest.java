package com.knoldus.feedservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.knoldus.feedservice.response.ErrorResponse;

import java.lang.reflect.Constructor;

import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

class CustomExceptionHandlerTest {
    /**
     * Method under test: {@link CustomExceptionHandler#handleAllExceptions(Exception, WebRequest)}
     */
    @Test
    void testHandleAllExceptions() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        Exception ex = new Exception();
        ResponseEntity<Object> actualHandleAllExceptionsResult = customExceptionHandler.handleAllExceptions(ex,
                new ServletWebRequest(new MockHttpServletRequest()));
        assertTrue(actualHandleAllExceptionsResult.hasBody());
        assertTrue(actualHandleAllExceptionsResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleAllExceptionsResult.getStatusCode());
        List<String> details = ((ErrorResponse<Object>) actualHandleAllExceptionsResult.getBody()).getDetails();
        assertEquals(1, details.size());
        assertNull(details.get(0));
        assertEquals("Server Error", ((ErrorResponse<Object>) actualHandleAllExceptionsResult.getBody()).getMessage());
    }


    /**
     * Method under test: {@link CustomExceptionHandler#handleUserNotFoundException(RecordNotFoundException, WebRequest)}
     */
    @Test
    void testHandleUserNotFoundException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        RecordNotFoundException ex = new RecordNotFoundException("Exception");
        ResponseEntity<Object> actualHandleUserNotFoundExceptionResult = customExceptionHandler
                .handleUserNotFoundException(ex, new ServletWebRequest(new MockHttpServletRequest()));
        assertTrue(actualHandleUserNotFoundExceptionResult.hasBody());
        assertTrue(actualHandleUserNotFoundExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleUserNotFoundExceptionResult.getStatusCode());
        List<String> details = ((ErrorResponse<Object>) actualHandleUserNotFoundExceptionResult.getBody()).getDetails();
        assertEquals(1, details.size());
        assertEquals("Exception", details.get(0));
        assertEquals("Record Not Found",
                ((ErrorResponse<Object>) actualHandleUserNotFoundExceptionResult.getBody()).getMessage());
    }


    /**
     * Method under test: {@link CustomExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMethodArgumentNotValid2() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null,
                new BindException("Target", "Object Name"));

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> actualHandleMethodArgumentNotValidResult = customExceptionHandler
                .handleMethodArgumentNotValid(ex, headers, HttpStatus.CONTINUE,
                        new ServletWebRequest(new MockHttpServletRequest()));
        assertTrue(actualHandleMethodArgumentNotValidResult.hasBody());
        assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
        assertTrue(((ErrorResponse<Object>) actualHandleMethodArgumentNotValidResult.getBody()).getDetails().isEmpty());
        assertEquals("Validation Failed",
                ((ErrorResponse<Object>) actualHandleMethodArgumentNotValidResult.getBody()).getMessage());
    }

    
}

