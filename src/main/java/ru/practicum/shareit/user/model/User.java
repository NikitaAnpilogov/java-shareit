package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * TODO Sprint add-controllers.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String name;
    @NotBlank(message = "Email не может быть пустым")
    private String email;
}
