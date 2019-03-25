package com.example.rentalHome.Exceptions;

public class MyResourceNotFoundException extends Exception
{
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public MyResourceNotFoundException(String errorMessage)
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public MyResourceNotFoundException() {
        super();
    }
}
