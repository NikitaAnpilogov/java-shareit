package ru.practicum.shareit.comment;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.comment.mapper.CommentMapper;
import ru.practicum.shareit.comment.model.Comment;
import ru.practicum.shareit.comment.model.CommentRequest;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentMapperTest {
    @Test
    void mapCommentToDtoTest() {
        Comment comment = new Comment(1L, "Text", new Item(), new User(), LocalDateTime.now());
        CommentDto commentDto = CommentMapper.mapCommentToDto(comment);
        assertThat(commentDto.getId()).isEqualTo(1L);
    }

    @Test
    void mapCommentToDtoListTest() {
        Comment comment = new Comment(1L, "Text", new Item(), new User(), LocalDateTime.now());
        List<CommentDto> commentDto = CommentMapper.mapCommentToDtoList(List.of(comment));
        assertThat(commentDto).isNotEmpty();
    }

    @Test
    void mapToCommentTest() {
        CommentRequest comment = new CommentRequest();
        comment.setText("Text");
        Comment result = CommentMapper.mapToComment(comment, new User(), new Item());
        assertThat(result.getText()).isEqualTo("Text");
    }
}
