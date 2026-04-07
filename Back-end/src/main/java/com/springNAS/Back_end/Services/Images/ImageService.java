package com.springNAS.Back_end.Services.Images;

import java.util.List;

import com.springNAS.Back_end.Types.Image;

public interface ImageService {
  List<Image> getImages(int page);
}
