package com.homeService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long parentId;
    @Transient
    private ArrayList<Category> doterCategories;
    @Transient
    private ArrayList<String> path;

    private String name;
    private String nameImage;
    private boolean isForProduct;


    /*====================================*/
    public ArrayList<Category> getDoterCategories() {
        if (doterCategories == null) doterCategories = new ArrayList<>();
        return doterCategories;
    }
    public void addDoterCategories(ArrayList<Category> doterCategories) {
        getDoterCategories().addAll(doterCategories);
    }
    public void addDoterCategory(Category doterCategory) {
        getDoterCategories().add(doterCategory);
    }
}
