package org.seancorbett.FieldDay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.directory.SearchResult;
import java.util.List;

@RestController
@RequestMapping("/")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/results")
    public ResponseEntity<List<SearchResult>> search(@RequestParam("query") String query) {
        List<SearchResult> results = searchService.search(query);
        return ResponseEntity.ok(results);
    }
}
