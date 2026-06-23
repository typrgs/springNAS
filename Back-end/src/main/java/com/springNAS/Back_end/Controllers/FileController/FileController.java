package com.springNAS.Back_end.Controllers.FileController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springNAS.Back_end.Controllers.FileController.Adapters.*;
import com.springNAS.Back_end.Controllers.FileController.DTOs.*;

@RestController
@RequestMapping("/api/file")
public class FileController {

  @Autowired
  Environment env;
  
  @GetMapping("/showRoot")
  public ResponseEntity<Map<String, String>> showRoot()
  {
    Map<String, String> body = new HashMap<>();
    body.put("root", env.getProperty("default.root"));
    
    return ResponseEntity.ok(body);
  }

  @GetMapping("/dir")
  public ResponseEntity<List<UserFileDTO>> getDirectory(@RequestParam("root") String path) throws IOException
  {
    List<UserFileDTO> dir;
    
    try(Stream<Path> paths = Files.list(Paths.get(path)))
    {
      dir = paths.map((Path p) -> {
        try
        {
          return (UserFileDTO)new PathAdapter(p);
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
        return null;
      })
              .toList();
    }
    
    return ResponseEntity.ok(dir);
  }
}
