package ro.sda.eventsFinalProject.service;

import org.springframework.stereotype.Service;
import ro.sda.eventsFinalProject.model.Category;
import ro.sda.eventsFinalProject.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;

@Service

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        if (category == null) {
            // Avoids the generation of NullPointerException for null category names!
            throw new IllegalArgumentException("A category must have a body!");
        }
        if (category.getName() == null) {
            // Avoids the generation of NullPointerException for null category names!
            throw new IllegalArgumentException("A category must have a name!");
        }
        Category savedCategory = categoryRepository.save(category);
        return savedCategory;
    }

    public Category readCategory(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Category ID must not be null!");
        }
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new IllegalArgumentException("There is no category with id:" + id);
        }
        return category;
    }

    public List<Category> readAllCategories() {
        List<Category> categories = new ArrayList<>();
        return categoryRepository.findAll();
    }

    public Category updateCategory(Category updatedCategory) {
        if (updatedCategory == null) {
            throw new IllegalArgumentException("A category must have a body!");
        }
        // Aici verificam ca exista o categorie cu id-ul dat!
        Category categoryToUpdate = readCategory(updatedCategory.getId());

        return categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(Integer categoryId){

        Category categoryToDelete = readCategory(categoryId);
        categoryRepository.delete(categoryToDelete);
    }
}
