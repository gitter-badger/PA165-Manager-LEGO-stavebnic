/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service.impl;

import cz.muni.fi.PA165.dao.ThemeSetDao;
import cz.muni.fi.PA165.daoDtoConversion.ThemeSetConversion;
import cz.muni.fi.PA165.dto.ThemeSetDto;
import cz.muni.fi.PA165.entity.ThemeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pavol Bako
 */
@Service
@Transactional
public class ThemeSetServiceImpl implements ThemeSetService{
    
    @Autowired
    private ThemeSetDao themeSetDao;

    public void setThemeSetDao(ThemeSetDao themeSetDao) {
        this.themeSetDao = themeSetDao;
    }
    
    public void create(ThemeSetDto setDto) {

        if (setDto == null) {
            throw new DataAccessExceptionService("DTO can not be NULL");

        }
        ThemeSet ts = ThemeSetConversion.convertToEntity(setDto);
        themeSetDao.create(ts);
    }

    public List<ThemeSetDto> findAll() {
        List<ThemeSet> themeSetEntities = themeSetDao.findAll();
        List<ThemeSetDto> themeSetDtoList = new ArrayList<ThemeSetDto>();
        for (ThemeSet ts : themeSetEntities) {
            themeSetDtoList.add(ts.createDto());
        }
        return themeSetDtoList;
    }

    public List<ThemeSetDto> findByPrice(BigDecimal price) {
        if (price == null){
            throw new DataAccessExceptionService("Parameter price cannot be NULL") {
            };
        }
        List<ThemeSet> tsList = themeSetDao.findByPrice(price);
        List<ThemeSetDto> themeSetDtoList = new ArrayList<ThemeSetDto>();
        for (ThemeSet ts : tsList) {
            themeSetDtoList.add(ts.createDto());
        }
        return themeSetDtoList;
    }

    public void update(ThemeSetDto setDto) {
        if (setDto == null) {
            throw new DataAccessExceptionService("DTO object cannot be NULL");
        }
        ThemeSet ts = ThemeSetConversion.convertToEntity(setDto);
        themeSetDao.update(ts);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new DataAccessExceptionService("ID cannot be NULL");
        }
        themeSetDao.delete(id);
    }

    public ThemeSetDto findById(Long id) {
        if (id == null) {
            throw new DataAccessExceptionService("ID cannot be NULL");
        }
        ThemeSet entity = themeSetDao.findById(id);
        
        return ThemeSetConversion.convertToDto(entity);
    }
}