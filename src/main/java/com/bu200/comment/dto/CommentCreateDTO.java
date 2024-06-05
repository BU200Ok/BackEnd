package com.bu200.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentCreateDTO {
    private String commentContent;
    private Long forumCode;
    private Long accountCode;
}
