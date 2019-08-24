package com.example.demo.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/")
    public String login(){
        return "Login";
    }

    @GetMapping(value = "/join")
    public String join(){
       return "join";
    }

    @GetMapping(value="/board")
    public String board(){
        return "board";
    }
}
