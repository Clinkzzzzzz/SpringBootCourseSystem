package com.ray.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexController
 *
 * @author ray
 *
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

}
