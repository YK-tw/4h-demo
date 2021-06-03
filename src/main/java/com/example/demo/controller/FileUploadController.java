package com.example.demo.controller;

import com.example.demo.model.UploadWrapper;
import com.example.demo.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping(value = "/")
    public String showUploadForm(Model model) {
        UploadWrapper uploadWrapper = new UploadWrapper();
        model.addAttribute("uploadWrapper", uploadWrapper);
        return "index";
    }

    @PostMapping(value = "/")
    public String uploadFile(HttpServletRequest request,
                             Model model,
                             @ModelAttribute("uploadWrapper") UploadWrapper uploadWrapper) {
        return fileUploadService.doUpload(request, model, uploadWrapper);
    }

}
