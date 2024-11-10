package net.coder.the.util;

import java.util.NoSuchElementException;

public class PinIsTakenException extends NoSuchElementException {
    public PinIsTakenException(String message) {
        super(message);
    }
}
