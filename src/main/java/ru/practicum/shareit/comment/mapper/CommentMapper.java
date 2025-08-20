package ru.practicum.shareit.comment.mapper;

import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.comment.model.Comment;
import ru.practicum.shareit.comment.model.CommentRequest;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class CommentMapper {
    public static CommentDto mapCommentToDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getAuthor().getName(),
                comment.getCreated()
        );
    }


    public static List<CommentDto> mapCommentToDtoList(Collection<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::mapCommentToDto)
                .toList();
    }

    public static Comment mapToComment(CommentRequest comment, User user, Item item) {
        Comment newComment = new Comment();
        newComment.setText(comment.getText());
        newComment.setAuthor(user);
        newComment.setItem(item);
        newComment.setCreated(LocalDateTime.now());
        return newComment;
    }
}
