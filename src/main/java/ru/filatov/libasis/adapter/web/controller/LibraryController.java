package ru.filatov.libasis.adapter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.filatov.libasis.dto.LibraryDto;
import ru.filatov.libasis.service.LibraryLotService;

@RestController
public class LibraryController {
    @Autowired
    LibraryLotService libraryLotService;

    @PostMapping("/add")
    public void addLibraryLot(LibraryDto libraryDto) {
        // TODO
    }
}
