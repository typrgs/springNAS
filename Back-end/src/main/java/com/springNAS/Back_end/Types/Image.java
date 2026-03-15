package com.springNAS.Back_end.Types;

import java.security.InvalidParameterException;

public record Image(String path, int width, int height) {
  public Image(String path, int width, int height)
  {
    this.path = path;
    this.width = width;
    this.height = height;

    checkInvariants();
  }

  private void checkInvariants()
  {
    if(this.path == null) throw new InvalidParameterException("Image path must be non-null.");
    if(this.width <= 0) throw new InvalidParameterException("Image width must be positive.");
    if(this.height <= 0) throw new InvalidParameterException("Image height must be positive.");
  }
}
