package com.richard.spring.cloud.backend.controller.rest;

import com.richard.spring.cloud.backend.domain.UserAccount;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created on 7/17/2017.
 */
@RestController
@RequestMapping(value = "/api")
public class HomeController {
  private AtomicLong atomicLong = new AtomicLong(0);

  @GetMapping({"", "/"})
  @CrossOrigin(origins = "*", maxAge = 3600)
  public Map<String, String> index() {
    Map<String, String> map = new HashMap<>();
    map.put(String.valueOf(atomicLong.incrementAndGet()), String.valueOf(System.currentTimeMillis()));
    return map;
  }

  @GetMapping({"/me"})
  public UserAccount me() {
    return UserAccount.builder()
      .firstName("Richard")
      .lastName("Ag")
      .userId("123")
      .password("1234")
      .username("haha")
      .build();
  }
}
