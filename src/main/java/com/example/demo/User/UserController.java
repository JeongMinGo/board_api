package com.example.demo.User;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class UserController {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final HttpSession session;
    public UserController(ModelMapper modelMapper, UserRepository userRepository, HttpSession session) {
        this.modelMapper=modelMapper;
        this.userRepository=userRepository;
        this.session=session;
    }
    @PostMapping //회원가입
    public ResponseEntity join(@RequestBody @Valid UserDto userDto, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        User user = modelMapper.map(userDto,User.class);
        User newUser = userRepository.save(user);

        System.out.println(newUser.getUserName());
        System.out.println(newUser.getUserEmail());
        System.out.println(newUser.getUserPassword());

        return ResponseEntity.status(201).body(newUser);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity delete(@PathVariable("userId") long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            return ResponseEntity.status(404).body("User not found");
        }
        User updatedUser = user.get();
        updatedUser.setDeleted(true);
        userRepository.save(updatedUser);
        return ResponseEntity.status(200).body(updatedUser);
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginDto userLoginDto, Errors errors, HttpSession session){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }
        System.out.println(userLoginDto.getUserEmail());
        System.out.println(userLoginDto.getUserPassword());
        User user = userRepository.findUserByUserEmailAndUserPassword(userLoginDto.getUserEmail(),userLoginDto.getUserPassword());
        if(user==null) {
            return ResponseEntity.status(404).body("User not found");
        }
        else {
            session.setAttribute("loginUser",user);
            return ResponseEntity.status(200).body(user);
        }
    }


//    회원정보 list로 뽑아내는 구간
//    @GetMapping("/userlist")
//    public ResponseEntity userlist(){
//        return ResponseEntity.ok().body(userRepository.findAll());
//    }


    @PutMapping("{userId}")
    public ResponseEntity update(@RequestBody @Valid UserUpdateDto userUpdateDto, @PathVariable("userId") long userId, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        User user=userRepository.findById(userId).get();
        user.setUserName(userUpdateDto.getUserName());
        user.setUserEmail(userUpdateDto.getUserName());
        user.setUserPassword(userUpdateDto.getUserPassword());

        userRepository.save(user);

        return ResponseEntity.status(200).body(user);

    }
}

//회원가입
//아이디 비밀번호 찾기
// 로그인
//비밀번호 수정
//Post --> "create" 리소스를 생성합니다
//Get --> Read 해당 URL의 리소스를 조회합니다.
//Put --> 해당 URL 리소스를 수정합니다
//Delete --> 해당 URL의 리로스를 삭제합니다.
