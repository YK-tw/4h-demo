package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.UploadWrapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public String doUpload(HttpServletRequest request,
                           Model model,
                           UploadWrapper uploadWrapper) {

        String sorting = uploadWrapper.getSortingType();
        System.out.println("Sorting: " + sorting);

        String uploadRootPath = request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        MultipartFile[] fileDatas = uploadWrapper.getFileData();
        List<File> uploadedFiles = new ArrayList<>();
        String name = "";

        for (MultipartFile fileData : fileDatas) {

            name = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + name);

            if (name != null && name.length() > 0) {
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                    stream.write(fileData.getBytes());
                    uploadedFiles.add(serverFile);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        model.addAttribute("sorting", sorting);
        model.addAttribute("uploadedFiles", uploadedFiles);
        HttpSession session = request.getSession();
        session.setAttribute("filepath", uploadRootPath + File.separator + name);
        session.setAttribute("sorting", sorting);
        return "show-upload";
    }

    @Override
    public List<Student> getStudentsFromFile(String filename) {
        File file = new File(filename);
        List<Student> students = new ArrayList<>();
        String regex = "[,]+";

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String dataLine = sc.nextLine();
                String[] data = dataLine.split(regex);
                students.add(new Student(data[0], Double.parseDouble(data[1])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }
}
