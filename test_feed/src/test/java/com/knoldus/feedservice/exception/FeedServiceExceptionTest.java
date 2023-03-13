package com.knoldus.feedservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FeedServiceExceptionTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link FeedServiceException#FeedServiceException()}
     *   <li>{@link FeedServiceException#setErrorCode(String)}
     *   <li>{@link FeedServiceException#setErrorMessage(String)}
     *   <li>{@link FeedServiceException#getErrorCode()}
     *   <li>{@link FeedServiceException#getErrorMessage()}
     * </ul>
     */
    @Test
    void testConstructor() {
        FeedServiceException actualFeedServiceException = new FeedServiceException();
        actualFeedServiceException.setErrorCode("An error occurred");
        actualFeedServiceException.setErrorMessage("An error occurred");
        assertEquals("An error occurred", actualFeedServiceException.getErrorCode());
        assertEquals("An error occurred", actualFeedServiceException.getErrorMessage());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link FeedServiceException#FeedServiceException(String, String)}
     *   <li>{@link FeedServiceException#setErrorCode(String)}
     *   <li>{@link FeedServiceException#setErrorMessage(String)}
     *   <li>{@link FeedServiceException#getErrorCode()}
     *   <li>{@link FeedServiceException#getErrorMessage()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        FeedServiceException actualFeedServiceException = new FeedServiceException("An error occurred",
                "An error occurred");
        actualFeedServiceException.setErrorCode("An error occurred");
        actualFeedServiceException.setErrorMessage("An error occurred");
        assertEquals("An error occurred", actualFeedServiceException.getErrorCode());
        assertEquals("An error occurred", actualFeedServiceException.getErrorMessage());
    }
}

