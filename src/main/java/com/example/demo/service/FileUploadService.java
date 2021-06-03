package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.UploadWrapper;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FileUploadService {

    String doUpload(HttpServletRequest request, Model model, UploadWrapper uploadWrapper);

    List<Student> getStudentsFromFile(String filename);

}
