package com.example.demo.CommentBoard;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="commentBoard")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "commentId")
public class CommentBoard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(name = "CommentBoardContent")
    private String commentBoardContent;
    @Column(name = "CommentBoardWriter")
    private String CommentBoardWriter;
    @Column(name = "boardId")
    private Long boardId;
    //isDeleted or Deleted 둘중에서 오류 하나 날수 도 있음  Swagger에서 결과값 가져올때 오류있을시 수정 요망
    @Column(name="isDeleted")//몽고 디비로 사용했을 때 isDeleted가 이미 존재해서 오류남
    private Boolean isDeleted;//commentBoard_flag-->바로 삭제가 아닌 Repository에 저장을 하고 true로 변경하여 판단
    @CreationTimestamp
    private Timestamp createdDateTime;
    @UpdateTimestamp
    private Timestamp updatedDateTime;

    public void setCommentBoardContent(String commentBoardContent) {
        this.commentBoardContent = commentBoardContent;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setCommentBoardWriter(String commentBoardWriter) {
        CommentBoardWriter = commentBoardWriter;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setUpdatedDateTime(Timestamp updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getCommentBoardContent() {
        return commentBoardContent;
    }

    public String getCommentBoardWriter() {
        return CommentBoardWriter;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public Timestamp getUpdatedDateTime() {
        return updatedDateTime;
    }
}
