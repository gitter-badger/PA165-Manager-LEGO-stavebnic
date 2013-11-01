package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.CategoryDao;
import cz.muni.fi.PA165.dto.CategoryDto;
import cz.muni.fi.PA165.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/30/13
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        if (categoryDao == null) {
            throw new IllegalArgumentException("CategoryDao cannot be NULL");
        }

        this.categoryDao = categoryDao;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> brickEntitites = categoryDao.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        for (Category category : brickEntitites) {
            categoryDtoList.add(category.createDto());
        }
        return categoryDtoList;
    }

    @Override
    public CategoryDto findByName(String name) {
        if(name == null) {
            throw new DataAccessException("parameter name cannot be NULL") {};
        }
        Category category = categoryDao.findByName(name);

        CategoryDto dto = null;
        try {
            dto = category.createDto();
        } catch (Exception e) {
            return dto;
        }
        return dto;
    }

    @Override
    public void update(CategoryDto categoryDto) {
        if(categoryDto == null) {
            throw new DataAccessException("DTO object cannot be NULL") {};
        }
        Category category = categoryDto.createEntity();
        categoryDao.update(category);
    }

    @Override
    public void delete(Long id) {
        if(id == null) {
            throw new DataAccessException("ID cannot be NULL") {};
        }
        categoryDao.delete(id);
    }

    @Override
    public void create(CategoryDto categoryDto) {
        if(categoryDto == null) {
            throw new DataAccessException("DTO object cannot be NULL") {};
        }
        Category categoryEntity = categoryDto.createEntity();
        categoryDao.create(categoryEntity);
    }

    @Override
    public CategoryDto findById(Long id) {
        if(id == null) {
            throw new DataAccessException("ID cannot be NULL") {};
        }
        Category entity = categoryDao.findById(id);
        
        CategoryDto dto = null;
        try {
            dto = entity.createDto();
        } catch (Exception e) {
            return dto;
        }
        return dto;
    }
}
