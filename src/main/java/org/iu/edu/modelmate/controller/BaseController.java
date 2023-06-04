package org.iu.edu.modelmate.controller;

import org.iu.edu.modelmate.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    @GetMapping("/")
    public String index(){
        return "index.html";
    }
}
