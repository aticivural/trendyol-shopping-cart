package com.trendyol.model;

public class Category{

    private String title;

    public Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return title.equals(category.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
