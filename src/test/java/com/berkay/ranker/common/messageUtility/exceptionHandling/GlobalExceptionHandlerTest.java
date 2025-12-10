package com.berkay.ranker.common.messageUtility.exceptionHandling;

import com.berkay.ranker.common.exceptionHandling.GlobalExceptionHandler;
import com.berkay.ranker.common.exceptionHandling.customExceptions.DuplicateResourceException;
import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private GlobalExceptionHandler handler;

    @Test
    void handleNotFound_ShouldReturn404AndLocalizedMessage() {
        // given
        ResourceNotFoundException ex =
                new ResourceNotFoundException("user.not.found", 1L);

        when(messageSource.getMessage(eq("user.not.found"), eq(new Object[]{1L}), any(Locale.class)))
                .thenReturn("User not found");

        // when
        ResponseEntity<?> response = handler.handleNotFound(ex);

        // then
        assertThat(response.getStatusCode().value()).isEqualTo(404);

        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();

        assertThat(body).isNotNull();
        assertThat(body.get("status")).isEqualTo(404);
        assertThat(body.get("error")).isEqualTo("User not found");
        assertThat(body.get("timestamp")).isNotNull();
    }

    @Test
    void handleDuplicate_ShouldReturn409AndLocalizedMessage() {
        // given
        DuplicateResourceException ex =
                new DuplicateResourceException("user.exists", "berkay");

        when(messageSource.getMessage(eq("user.exists"), eq(new Object[]{"berkay"}), any(Locale.class)))
                .thenReturn("User already exists");

        // when
        ResponseEntity<?> response = handler.handleDuplicate(ex);

        // then
        assertThat(response.getStatusCode().value()).isEqualTo(409);

        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();

        assertThat(body).isNotNull();
        assertThat(body.get("status")).isEqualTo(409);
        assertThat(body.get("error")).isEqualTo("User already exists");
        assertThat(body.get("timestamp")).isNotNull();
    }
}
