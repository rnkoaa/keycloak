package com.richard.spring.cloud.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created on 7/17/2017.
 */
@Controller
public class IndexController {

  @GetMapping({"", "/"})
  public String string() {
    return "index";
  }

  @GetMapping("/admin")
  public String admin() {
    return "admin";
  }

  @GetMapping("/user")
  public String user() {
    return "user";
  }

  @GetMapping("/about")
  public String about() {
    return "about";
  }

}
