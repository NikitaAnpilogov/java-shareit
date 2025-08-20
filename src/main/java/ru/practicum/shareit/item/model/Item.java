package ru.practicum.shareit.item.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.practicum.shareit.request.model.ItemRequest;

/**
 * TODO Sprint add-controllers.
 */
@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название не может быть пустым")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Описание не может быть пустым")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Статус доступности должен быть указан")
    @Column(nullable = false)
    private Boolean available;

    @Column(name = "owner_id")
    private Long ownerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    @ToString.Exclude
    private ItemRequest request;
}
