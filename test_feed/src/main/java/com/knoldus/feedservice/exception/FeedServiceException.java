package com.knoldus.feedservice.exception;

public class FeedServiceException extends Exception{
     private String  errorCode;
     private String errorMessage;

     public FeedServiceException() {
     }

     public FeedServiceException(String errorCode, String errorMessage) {
          super(errorCode);
          this.errorCode = errorCode;
          this.errorMessage = errorMessage;
     }

     public String getErrorCode() {
          return errorCode;
     }

     public void setErrorCode(String errorCode) {
          this.errorCode = errorCode;
     }

     public String getErrorMessage() {
          return errorMessage;
     }

     public void setErrorMessage(String errorMessage) {
          this.errorMessage = errorMessage;
     }
}
