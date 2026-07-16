package org.example.exceptions;

public class ServiceNotAvailable extends RuntimeException {
    public ServiceNotAvailable(String message) {
        super(message);
    }
}
