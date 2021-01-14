package minwoo.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello") // hello path로 요청시 동작
    public String hello(Model model){
        model.addAttribute("data","정민우");
        return "hello";
    }


}
