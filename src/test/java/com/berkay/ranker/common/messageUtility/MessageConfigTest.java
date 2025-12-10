package com.berkay.ranker.common.messageUtility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(MessageConfig.class)
class MessageConfigTest {

    @Autowired
    @SuppressWarnings("FieldCanBeLocal")
    private MessageSource messageSource;

    @Test
    void messageSource_ShouldReturnMessage() {
        String result = messageSource.getMessage(
                "user.not.found",
                new Object[]{1L},
                Locale.getDefault()
        );
        assertEquals("User not found with id: 1", result);
    }
}
