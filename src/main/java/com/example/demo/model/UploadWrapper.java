package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

public class UploadWrapper {

    private String sortingType;

    private MultipartFile[] fileData;

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public MultipartFile[] getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile[] fileData) {
        this.fileData = fileData;
    }

}
