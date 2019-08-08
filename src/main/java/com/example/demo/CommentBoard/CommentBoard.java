package com.example.demo.CommentBoard;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="commentBoard")
@Builder
@EqualsAndHashCode(of = "commentId")
public class CommentBoard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(name = "CommentBoardContent")
    private String commentBoardContent;
    @Column(name = "CommentBoardWriter")
    private String CommentBoardWriter;
    @CreatedDate
    @Convert(converter = CommentDateConvert.class)  // <- @Converter를 지정 해줘야 한다.
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdDateTime;
    //isDeleted or Deleted 둘중에서 오류 하나 날수 도 있음  Swagger에서 결과값 가져올때 오류있을시 수정 요망
    @Column(name="isDeleted")//몽고 디비로 사용했을 때 isDeleted가 이미 존재해서 오류남
    private Boolean isDeleted;//commentBoard_flag-->바로 삭제가 아닌 Repository에 저장을 하고 true로 변경하여 판단

}
