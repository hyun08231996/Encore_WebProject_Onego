package com.encore.backend.storage;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GcsUploader implements StorageUploader {
    private final Storage storage;

    @Value("${cloud.gcp.storage.bucket}")
    private String bucket;

    @Override
    public String upload(MultipartFile multipartFile, String dirName, String fileName) throws IOException {
        String objectName = dirName + "/" + fileName;
        String contentType = multipartFile.getContentType() != null ? multipartFile.getContentType()
                : "application/octet-stream";

        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucket, objectName)).setContentType(contentType).build();
        storage.create(blobInfo, multipartFile.getBytes());

        return "https://storage.googleapis.com/" + bucket + "/"
                + URLEncoder.encode(objectName, StandardCharsets.UTF_8.toString()).replace("+", "%20");
    }

    @Override
    public boolean remove(String fileName) {
        return storage.delete(BlobId.of(bucket, "profileImages/" + fileName));
    }
}
