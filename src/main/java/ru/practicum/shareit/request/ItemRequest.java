package ru.practicum.shareit.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    private Long id;
    @NotBlank(message = "Описание запроса не может быть пустым")
    private String description;
    private User requestor;
    private LocalDateTime created;
}
