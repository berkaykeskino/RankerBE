package com.berkay.ranker.common.exceptionHandling.customExceptions;

public class DuplicateResourceException extends RuntimeException{

    private final String messageKey;
    private final Object[] args;

    public DuplicateResourceException(String messageKey, Object... args) {
        super(messageKey);
        this.messageKey = messageKey;
        this.args = args;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Object[] getArgs() {
        return args;
    }
}
