package com.example.demo.Board;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BoardDto {
    @NotEmpty @Length(min=1,max=100)
    private String boardTitle;
    @NotEmpty @Length(min=1,max=100)
    private String boardContent;
    private String boardWriter="짱구";
}
