package com.example.demo.Board;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "boardId")
public class Board implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    @Column(name = "boardTitle")
    private String boardTitle;
    @Column(name = "boardContent")
    private String boardContent;
    @Column(name = "boardWriter")
    private String boardWriter;
//    @Convert(converter = DateConvert.class)  // <- @Converter를 지정 해줘야 한다.
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdDateTime;
    @Column(name="hit")
    @ColumnDefault("0")
    private Long hit;
    //isDeleted or Deleted 둘중에서 오류 하나 날수 도 있음  Swagger에서 결과값 가져올때 오류있을시 수정 요망
    @Column(name="isDeleted")//몽고 디비로 사용했을 때 isDeleted가 이미 존재해서 오류남
    private Boolean isDeleted;

    public Long getBoardId() {
        return boardId;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public Long getHit() {
        return hit;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public void setBoardWriter(String boardWriter) {
        this.boardWriter = boardWriter;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public void setHit(Long hit) {
        this.hit = hit;
    }
}
