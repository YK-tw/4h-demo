package com.example.demo.service;

import com.example.demo.model.Entity;
import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SortingServiceImpl implements SortingService {

    @Override
    public List<Student> studentSort(String sortingType, List<Student> students) {
        switch (sortingType) {
            case "heap":
                return studentHeapSort(students);
            case "merge":
                return studentMergeSort(students);
            case "bubble":
                return studentBubbleSort(students);
            default:
                return students;
        }
    }

    private List<Student> studentHeapSort(List<Student> students) {
        return entitiesToStudents(heapSortImpl(studentsToEntities(students)));
    }

    private List<Student> studentMergeSort(List<Student> students) {
        return entitiesToStudents(mergeSortImpl(studentsToEntities(students), students.size()));
    }

    private List<Student> studentBubbleSort(List<Student> students) {
        return entitiesToStudents(bubbleSortImpl(studentsToEntities(students)));
    }

    private Entity[] studentsToEntities(List<Student> students) {
        return students.toArray(new Entity[students.size()]);
    }

    private List<Student> entitiesToStudents(Entity[] entities) {
        List<Student> result = new ArrayList<>();
        for (int i = entities.length - 1; i >= 0; i--) {
            result.add((Student) entities[i]);
        }
        return result;
    }

    private Entity[] heapSortImpl(Entity[] entities) {
        int n = entities.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(entities, n, i);

        for (int i = n - 1; i > 0; i--) {
            Entity temp = entities[0];
            entities[0] = entities[i];
            entities[i] = temp;

            heapify(entities, i, 0);
        }
        return entities;
    }

    private void heapify(Entity[] entities, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && entities[l].getMark() > entities[largest].getMark())
            largest = l;

        if (r < n && entities[r].getMark() > entities[largest].getMark())
            largest = r;

        if (largest != i) {
            Entity swap = entities[i];
            entities[i] = entities[largest];
            entities[largest] = swap;

            heapify(entities, n, largest);
        }
    }

    private Entity[] mergeSortImpl(Entity[] entities, int n) {
        if (n < 2) {
            return entities;
        }
        int mid = n / 2;
        Entity[] l = new Student[mid];
        Entity[] r = new Student[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = entities[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = entities[i];
        }
        mergeSortImpl(l, mid);
        mergeSortImpl(r, n - mid);

        merge(entities, l, r, mid, n - mid);
        return entities;
    }

    private void merge(Entity[] entities, Entity[] l, Entity[] r, int left, int right) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left && j < right) {
            if (l[i].getMark() <= r[j].getMark()) {
                entities[k++] = l[i++];
            } else {
                entities[k++] = r[j++];
            }
        }
        while (i < left) {
            entities[k++] = l[i++];
        }
        while (j < right) {
            entities[k++] = r[j++];
        }
    }

    private Entity[] bubbleSortImpl(Entity[] entities) {
        int n = entities.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (entities[j].getMark() > entities[j + 1].getMark()) {
                    Entity temp = entities[j];
                    entities[j] = entities[j + 1];
                    entities[j + 1] = temp;
                }
            }
        }
        return entities;
    }
}
