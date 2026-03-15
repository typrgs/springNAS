package com.springNAS.Back_end.Services.Interfaces;

import java.util.List;

import com.springNAS.Back_end.Types.Image;

public interface ImageService {
  List<Image> getImages(int page);
}
