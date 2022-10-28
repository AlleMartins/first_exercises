package it.unibo.exceptions.fakenetwork.impl;

import java.io.IOException;
import java.util.Objects;

public class NetworkException extends IOException {

    public NetworkException() {
        super("Network error: no response");
    }

    public NetworkException(String message) {
        super("Network error while sending message: " + message);
        Objects.requireNonNull(message);
    }
}