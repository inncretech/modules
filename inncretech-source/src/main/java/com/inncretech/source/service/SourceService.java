package com.inncretech.source.service;

import com.inncretech.source.model.Source;

public interface SourceService {
  
  Source create(Source source);
  
  //update only title and description
  void update(Source source);
  
  void delete(Long sourceId);
  
  Source get(Long sourceId);

}
