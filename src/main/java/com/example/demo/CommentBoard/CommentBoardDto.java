package com.example.demo.CommentBoard;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CommentBoardDto {
    @Column(name = "boardId")
    private Long boardId;
    @Column(name = "boardContent")
    private String boardContent;

    public Long getBoardId() {
        return boardId;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }
}
