package com.springNAS.Back_end.Controllers.FileController.Adapters;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;

import com.springNAS.Back_end.Controllers.FileController.DTOs.UserFileDTO;

public class PathAdapter extends UserFileDTO {
  public PathAdapter(Path path) throws IOException {
    super(path.getFileName().toString(), FilenameUtils.getExtension(path.getFileName().toString()).toUpperCase());
  }  
}
