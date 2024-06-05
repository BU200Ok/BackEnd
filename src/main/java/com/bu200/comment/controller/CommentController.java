package com.bu200.comment.controller;
import com.bu200.comment.dto.CommentCreateDTO;
import com.bu200.comment.dto.CommentDTO;
import com.bu200.comment.service.CommentService;
import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("forum/comment")
@Controller
public class CommentController {
    private final CommentService commentService;
    private final Tool tool;

    public CommentController(CommentService commentService, Tool tool) {
        this.commentService = commentService;
        this.tool = tool;
    }

    //작성
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createComment(@RequestBody CommentCreateDTO  commentCreateDTO) {
        try {
            CommentDTO savedComment = commentService.createComment(commentCreateDTO);
            return tool.res(HttpStatus.OK, "댓글 내용입니다.", savedComment);
        } catch (IllegalArgumentException e) {
            return tool.res(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    //댓글 목록
        @GetMapping("/list/{forumCode}")
        public ResponseEntity<ResponseDTO> getCommentList(@PathVariable Long forumCode) {
            List<CommentDTO> comments = commentService.getCommentsByForumCode(forumCode);
            return tool.res(HttpStatus.OK, "해당 게시글의 댓글", comments);
        }

    //삭제
    @DeleteMapping("/delete/{commentCode}")
    public ResponseEntity<ResponseDTO> deleteComment(@PathVariable Long commentCode){
        try {
            commentService.deleteComment(commentCode);
            return tool.res(HttpStatus.OK, "댓글이 성공적으로 삭제되었습니다.", null);
        } catch (IllegalArgumentException e) {
            return tool.res(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
