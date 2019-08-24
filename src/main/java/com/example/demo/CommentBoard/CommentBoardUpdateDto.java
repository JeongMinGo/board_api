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
public class CommentBoardUpdateDto {
    @Column(name = "boardContent")
    private String boardContent;

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardContent() {
        return boardContent;
    }
}
