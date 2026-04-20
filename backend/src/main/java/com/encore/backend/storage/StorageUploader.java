package com.encore.backend.storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface StorageUploader {
    String upload(MultipartFile multipartFile, String dirName, String fileName) throws IOException;

    boolean remove(String fileName);
}
