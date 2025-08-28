package ru.practicum.shareit.item.mapper;

import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemShortDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.List;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        if (item == null) {
            return null;
        }

        ItemDto result = new ItemDto();
        result.setId(item.getId());
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setAvailable(item.getAvailable());
        return result;
    }

    public static ItemDto mapToItemDtoWithBookings(Item item, List<CommentDto> comments) {
        if (item == null) {
            return null;
        }
        ItemDto result = new ItemDto();
        result.setId(item.getId());
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setAvailable(item.getAvailable());
        result.setComments(comments != null ? comments : List.of());
        return result;
    }

    public static Collection<ItemDto> mapToItemDtoList(Collection<Item> search) {
        if (search == null) {
            return null;
        }
        return search.stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }

    public static ItemShortDto mapToShortItemDto(Item item) {
        if (item == null) {
            return null;
        }
        return new ItemShortDto(item.getId(), item.getName(), item.getOwnerId());
    }

    public static List<ItemShortDto> mapToShortItemDtoList(Collection<Item> search) {
        if (search == null) {
            return null;
        }
        return search.stream().map(ItemMapper::mapToShortItemDto).toList();
    }
}
