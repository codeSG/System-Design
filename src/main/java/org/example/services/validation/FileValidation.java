package org.example.services.validation;

import org.example.Exception.FileValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileValidation {
    @Value("${video.max-file-size}")
    private DataSize maxFileSize;
    public boolean validate(MultipartFile file){
        if(file==null){
            throw new FileValidationException(HttpStatus.BAD_REQUEST , "File is Required");
        }else if(file.isEmpty()){
            throw new FileValidationException(HttpStatus.BAD_REQUEST , "Uploaded file is empty");
        }else if(file.getSize() > maxFileSize.toBytes()){
            throw new FileValidationException(HttpStatus.BAD_REQUEST , "Video Size cannot Exceed " + maxFileSize + " MB");
        }
        return true;
    }
}
