package com.example.demo.User;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDto {
    @NotEmpty @Length(min = 3,max = 10)
    private String userName;
    @Email @Length(min = 1, max = 50)
    private String userEmail;
    @NotEmpty @Length(min = 10, max = 30)
    private String userPassword;
}
