package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface SortingService {

    List<Student> studentSort(String sortingType, List<Student> students);

}
