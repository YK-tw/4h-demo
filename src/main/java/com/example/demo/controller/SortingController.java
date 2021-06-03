package com.example.demo.controller;

import com.example.demo.service.FileUploadService;
import com.example.demo.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class SortingController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private SortingService sortingService;

    @GetMapping(value = "/sorted")
    public String sort(Model model,
                       @SessionAttribute("filepath") String filepath,
                       @SessionAttribute("sorting") String sortingType) {

        Long start = System.currentTimeMillis();
        model.addAttribute("students", sortingService.studentSort(sortingType,
                fileUploadService.getStudentsFromFile(filepath)));
        model.addAttribute("time", System.currentTimeMillis() - start);
        return "show-sorted";
    }

}
