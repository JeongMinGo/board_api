package com.example.demo.Board;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
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
    @CreatedDate
//    @Convert(converter = DateConvert.class)  // <- @Converter를 지정 해줘야 한다.
    @Column(name = "created_at", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDateTime;
    @Column(name="hit")
    @ColumnDefault("0")
    private Long hit;
    //isDeleted or Deleted 둘중에서 오류 하나 날수 도 있음  Swagger에서 결과값 가져올때 오류있을시 수정 요망
    @Column(name="isDeleted")//몽고 디비로 사용했을 때 isDeleted가 이미 존재해서 오류남
    private Boolean isDeleted;
}
