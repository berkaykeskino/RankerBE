package com.berkay.ranker.common.messageUtility.exceptionHandling.customExceptions;

import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceNotFoundExceptionTest {

    @Test
    void constructor_ShouldSetMessageKeyAndArgs() {
        // given
        String key = "user.not.found";
        Object[] args = new Object[]{1L};

        // when
        ResourceNotFoundException ex = new ResourceNotFoundException(key, args);

        // then
        assertThat(ex.getMessageKey()).isEqualTo(key);
        assertThat(ex.getArgs()).containsExactly(1L);

        // RuntimeException.message should equal messageKey
        assertThat(ex.getMessage()).isEqualTo(key);
    }

    @Test
    void constructor_ShouldHandleMultipleArgs() {
        // given
        String key = "resource.missing";
        Object[] args = new Object[]{"abc", 22, true};

        // when
        ResourceNotFoundException ex = new ResourceNotFoundException(key, args);

        // then
        assertThat(ex.getMessageKey()).isEqualTo("resource.missing");
        assertThat(ex.getArgs()).containsExactly("abc", 22, true);
    }
}

