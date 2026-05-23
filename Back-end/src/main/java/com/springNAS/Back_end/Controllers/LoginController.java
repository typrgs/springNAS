package com.springNAS.Back_end.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
  @Autowired
  Environment env;
  
  @PostMapping()
  public boolean login(String username, String password)
  {
    return this.env.getProperty("default.username").equals(username) && this.env.getProperty("default.pass").equalsIgnoreCase(password);
  }
}
