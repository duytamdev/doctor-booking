package com.funix.lab.prj321x_asm3_tamndfx27974.controller;

import com.funix.lab.prj321x_asm3_tamndfx27974.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/public/search")
    public ResponseEntity<?> searchCommon(@RequestParam String keysearch) {
        return ResponseEntity.ok(searchService.searchCommon(keysearch));
    }

    @GetMapping("/public/search/specialization")
    public ResponseEntity<?> searchSpecialization(@RequestParam String keysearch) {
        return ResponseEntity.ok(searchService.searchSpecialization(keysearch));
    }

}
