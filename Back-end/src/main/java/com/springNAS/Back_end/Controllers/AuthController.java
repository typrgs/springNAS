package com.springNAS.Back_end.Controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springNAS.Back_end.Controllers.Types.Credentials;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  Environment env;
  
  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody Credentials creds)
  {
    if(this.env.getProperty("default.username").equalsIgnoreCase(creds.username()) && this.env.getProperty("default.pass").equalsIgnoreCase(creds.password()))
    {
      ResponseCookie cookie = ResponseCookie.from("nasAuth", creds.username())
                                .httpOnly(true)
                                .secure(true)
                                .path("/")
                                .maxAge(Duration.ofHours(24))
                                .sameSite("None")
                                .build();

      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  @GetMapping("/status")
  public boolean checkAuth(@CookieValue(name = "nasAuth", required = false) String nasAuth)
  {
    return nasAuth != null;
  }

  @DeleteMapping("/reset")
  public ResponseEntity<Object> resetAuth(@CookieValue(name = "nasAuth", required = false) String nasAuth)
  {
    ResponseCookie cookie = ResponseCookie.from("nasAuth", nasAuth)
                          .httpOnly(true)
                          .secure(true)
                          .path("/")
                          .maxAge(Duration.ofSeconds(0))
                          .sameSite("None")
                          .build();

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
  }
}
