package com.springNAS.Back_end.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springNAS.Back_end.Services.Interfaces.ImageService;
import com.springNAS.Back_end.Types.Image;

@RestController
@RequestMapping("/images")
public class ImageController {
  private ImageService imageService;

  @Autowired
  public ImageController(ImageService imageService)
  {
    this.imageService = imageService;
  }

  @GetMapping
  public List<Image> getImages(@RequestParam int page)
  {
    return this.imageService.getImages(page);
  }
}
