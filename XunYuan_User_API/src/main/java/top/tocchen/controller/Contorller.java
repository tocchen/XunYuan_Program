package top.tocchen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tocchen
 * @date 2023/2/24 12:26
 * @since jdk 1.8
 **/
@RestController
public class Contorller {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

}
