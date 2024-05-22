package ua.com.backend.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.backend.service.S3Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class DefaultS3Service implements S3Service {

    private static final String S3_BUCKET_NAME = "aws-upload-image-app-bucket";
    private static final String FOLDER_PATH = "images/";
    private final AmazonS3 s3Client;

    public DefaultS3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String save(MultipartFile file) throws IOException {
        String s3Key = FOLDER_PATH + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        s3Client.putObject(S3_BUCKET_NAME, s3Key, file.getInputStream(), metadata);
        return s3Key;
    }

    public String getURL(String s3Key) {
        return String.valueOf(s3Client.getUrl(S3_BUCKET_NAME, s3Key));
    }

    public byte[] getFileByURL(String fileURL) throws IOException {
        URL url = new URL(fileURL);
        String bucketName = url.getHost().split("\\.")[0];
        String key = url.getPath().substring(1);
        S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, key));
        try (InputStream inputStream = s3Object.getObjectContent();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        }
    }

}