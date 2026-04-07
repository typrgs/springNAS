package com.springNAS.Back_end.Services.Images;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springNAS.Back_end.Types.Image;

@Service
public class ImageServiceStub implements ImageService {
  private List<Image> images;

  public ImageServiceStub()
  {
    images = new ArrayList<>();
    this.images.add(new Image("https://images.pexels.com/photos/36183149/pexels-photo-36183149.jpeg", 5464, 8192));
    this.images.add(new Image("https://images.pexels.com/photos/1624600/pexels-photo-1624600.jpeg", 3310, 2481));
    this.images.add(new Image("https://images.pexels.com/photos/417074/pexels-photo-417074.jpeg", 4226, 2847));
    this.images.add(new Image("https://images.pexels.com/photos/1379636/pexels-photo-1379636.jpeg", 6000, 4000));
    this.images.add(new Image("https://images.pexels.com/photos/992734/pexels-photo-992734.jpeg", 4608, 3072));
    this.images.add(new Image("https://images.pexels.com/photos/807598/pexels-photo-807598.jpeg", 4128, 3096));
    this.images.add(new Image("https://images.pexels.com/photos/2649403/pexels-photo-2649403.jpeg", 7782, 5191));
    this.images.add(new Image("https://images.pexels.com/photos/753550/pexels-photo-753550.jpeg", 3548, 2182));
    this.images.add(new Image("https://images.pexels.com/photos/156934/pexels-photo-156934.jpeg", 4096, 2736));
    this.images.add(new Image("https://images.pexels.com/photos/36704/pexels-photo.jpg", 4896, 3264));
    this.images.add(new Image("https://images.pexels.com/photos/240288/pexels-photo-240288.jpeg", 5247, 3935));
    this.images.add(new Image("https://images.pexels.com/photos/842711/pexels-photo-842711.jpeg", 5472, 3648));
    this.images.add(new Image("https://images.pexels.com/photos/414612/pexels-photo-414612.jpeg", 5184, 3456));
    this.images.add(new Image("https://images.pexels.com/photos/210186/pexels-photo-210186.jpeg", 5184, 3456));
    this.images.add(new Image("https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg", 2976, 3968));
    this.images.add(new Image("https://images.pexels.com/photos/462118/pexels-photo-462118.jpeg", 6000, 4000));
    this.images.add(new Image("https://images.pexels.com/photos/104827/cat-pet-animal-domestic-104827.jpeg", 5760, 3840));
    this.images.add(new Image("https://images.pexels.com/photos/196645/pexels-photo-196645.jpeg", 5616, 3744));
  }

  public List<Image> getImages(int page)
  {
    List<Image> imagePage = new ArrayList<>();

    if(page == 0)
    {
      imagePage = new ArrayList<>(this.images);
    }

    return imagePage;
  }
}
