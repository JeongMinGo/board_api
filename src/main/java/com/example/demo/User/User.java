package com.example.demo.User;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="User")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "userId")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "userPassword")
    private String userPassword;
    @Column(name="isDeleted")//몽고 디비로 사용했을 때 isDeleted가 이미 존재해서 오류남
    private Boolean isDeleted;

}
