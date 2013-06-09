package com.inncretech.source.model;

public class Source {

  private Long id;

  // <source_type>:<magzine_name>/image_title, product:<mag>
  private String sourceUri;

  private int sourceType = 0;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSourceUri() {
    return sourceUri;
  }

  public void setSourceUri(String sourceUri) {
    this.sourceUri = sourceUri;
  }

  public int getSourceType() {
    return sourceType;
  }

  public void setSourceType(int sourceType) {
    this.sourceType = sourceType;
  }
}
