package org.example.strategy;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageStrategy {
   public void store(MultipartFile file , String storageKey);
   public Resource load(String storageKey);
   public void delete(String storageKey);
}
