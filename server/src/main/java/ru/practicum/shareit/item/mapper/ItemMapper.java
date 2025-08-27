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

        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }

    public static Item toItem(ItemDto itemDto) {
        if (itemDto == null) {
            return null;
        }

        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(itemDto.getAvailable())
                .build();
    }

    public static ItemDto mapToItemDtoWithBookings(Item item, List<CommentDto> comments) {
        if (item == null) {
            return null;
        }
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .comments(comments != null ? comments : List.of())
                .build();
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
