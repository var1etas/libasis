package ru.filatov.libasis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.filatov.libasis.config.ObjectStorageConfig;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectAclRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Request;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ExecutorService;

@Service
public class UploadService {
    ObjectStorageConfig objectStorageConfig;

    @Autowired
    public UploadService(ObjectStorageConfig objectStorageConfig) {
        this.objectStorageConfig = objectStorageConfig;
    }

    public String uploadPhoto(byte[] photos) {


        final String bucketName = objectStorageConfig.getBucketName();
        final String accessKeyId = objectStorageConfig.getAccessKeyId();
        final String secretAccessKey = objectStorageConfig.getSecretAccessKey();
        AwsCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        String regionName = "ru-central1";
        S3Client s3Client = S3Client
                .builder()
                .region(Region.of(regionName))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(URI.create("https://storage.yandexcloud.net"))
                .build();


        //Map<String, String> metadata = new HashMap<>();
//        metadata.put("company", "Baeldung");
//        metadata.put("environment", "development");
        UUID uuid = UUID.randomUUID();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(photos);

        PutObjectResponse response = s3Client.putObject(request -> request
                .bucket(bucketName).key(uuid.toString()),
                RequestBody.fromInputStream(inputStream, inputStream.available()));

        return s3Client.utilities().getUrl(request -> request
                .bucket(bucketName).key(uuid.toString())).toString();
    }

    private String generateUniqueName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
