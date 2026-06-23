package com.springNAS.Back_end.Controllers.FileController.DTOs;

public class UserFileDTO
{
  private String fileName;
  private String fileType;
  
  public UserFileDTO(String fn, String ft)
  {
    this.fileName = fn;
    this.fileType = ft;
  }

  public String getFileName() { return fileName; }
  public String getFileType() { return fileType; }
}
