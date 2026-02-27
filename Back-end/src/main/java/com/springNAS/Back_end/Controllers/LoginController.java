package com.springNAS.Back_end.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
  
  @PostMapping()
  public boolean login()
  {
    return true;
  }
}
