package com.berkay.ranker.common.messageUtility.exceptionHandling.customExceptions;

import com.berkay.ranker.common.exceptionHandling.customExceptions.DuplicateResourceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DuplicateResourceExceptionTest {

    @Test
    void testExceptionStoresMessageKeyAndArgs() {
        // given
        String messageKey = "user.username.already.exists";
        Object[] args = new Object[] { "berkay@example.com" };

        // when
        DuplicateResourceException ex = new DuplicateResourceException(messageKey, args);

        // then
        assertEquals(messageKey, ex.getMessageKey(), "Message key should match");
        assertArrayEquals(args, ex.getArgs(), "Arguments should match");
    }

    @Test
    void testSuperclassMessageIsMessageKey() {
        // given
        String messageKey = "user.username.already.exists";

        // when
        DuplicateResourceException ex = new DuplicateResourceException(messageKey, "berkay");

        // then
        assertEquals(messageKey, ex.getMessage(),
                "getMessage() should return the messageKey because of super(messageKey)");
    }
}
