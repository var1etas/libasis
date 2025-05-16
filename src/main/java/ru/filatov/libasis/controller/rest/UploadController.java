package ru.filatov.libasis.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.filatov.libasis.model.service.UploadService;

import java.io.IOException;

@RestController
@RequestMapping("/photo")
public class UploadController {
    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public String upload(MultipartFile photo) throws IOException {
        return uploadService.uploadPhoto(photo.getBytes());
    }
}
