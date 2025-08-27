package ru.practicum.shareit.request.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemRequestMapper {
    public static ItemRequestDto mapToItemRequestDto(ItemRequest itemRequest) {
        if (itemRequest == null) {
            return null;
        }
        return ItemRequestDto.builder()
                .id(itemRequest.getId())
                .description(itemRequest.getDescription())
                .requesterId(itemRequest.getId())
                .created(itemRequest.getCreated())
                .build();
    }

    public static List<ItemRequestDto> mapToItemRequestDtoList(Collection<ItemRequest> requests) {
        if (requests == null) {
            return null;
        }
        return requests.stream()
                .map(ItemRequestMapper::mapToItemRequestDto)
                .toList();
    }
}