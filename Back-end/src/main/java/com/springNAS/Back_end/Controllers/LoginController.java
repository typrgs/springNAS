package com.springNAS.Back_end.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
  @Value("${USERNAME}")
  private String username;
  
  @Value("${PASS}")
  private String pass;
  
  @PostMapping()
  public boolean login(String username, String password)
  {
    return this.username.equals(username) && this.pass.equalsIgnoreCase(password);    
  }
}
