package org.example.services.StorageStrategy;

import lombok.NoArgsConstructor;
import org.example.Exception.StorageException;
import org.example.strategy.StorageStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalStorageStrategy implements StorageStrategy {
    private final Path rootLocation;

    public LocalStorageStrategy(@Value("${storage.root-path}") String rootPath ){
        rootLocation = Paths.get(rootPath);
    }

  @Override
  public void store(MultipartFile file , String storageKey){
      try {
          Path destination = rootLocation.resolve(storageKey);

          Files.createDirectories(destination.getParent()); // if directory is present well and good else it will create root i.e. uploads/video
          System.out.println("Storing file at: " + destination.toAbsolutePath());
          Files.copy(file.getInputStream(),destination);
      } catch(IOException ex){
          throw new StorageException("Failed to store file");
      }
  }

  @Override
    public Resource load(String storageKey){
        try {
            Path filePath = rootLocation.resolve(storageKey);
            Resource resource = new FileSystemResource(filePath);
            return resource;
        }catch(Exception ex){
            throw new StorageException("File Not Found");
        }
  }

  @Override
    public void delete(String storageKey){
        try {
            Path filePath = rootLocation.resolve(storageKey);
            boolean deleted = Files.deleteIfExists(filePath);
        } catch(IOException ex){
            throw new StorageException("File doesn't  Exists");
        }


  }
}
