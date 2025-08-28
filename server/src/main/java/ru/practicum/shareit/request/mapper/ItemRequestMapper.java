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
        ItemRequestDto result = new ItemRequestDto();
        result.setId(itemRequest.getId());
        result.setDescription(itemRequest.getDescription());
        result.setRequesterId(itemRequest.getId());
        result.setCreated(itemRequest.getCreated());
        return result;
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