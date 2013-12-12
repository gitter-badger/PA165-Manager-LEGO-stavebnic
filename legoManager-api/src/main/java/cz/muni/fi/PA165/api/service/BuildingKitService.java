package cz.muni.fi.PA165.api.service;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;

import java.util.List;

/**
 * Interface for BuildingKit service
 * 
 * @author Tomas Kopecky
 */
public interface BuildingKitService {
    
    /**
     * creates given building kit
     *
     * @param buildingKit instance of BuildingKit DTO class
     */
    public void create(BuildingKitDto buildingKit);

    /**
     * deletes given building kit
     *
     * @param id id of the instance of the building kit to remove
     */
    public void delete(Long id);

    /**
     * updates given building kit
     *
     * @param buildingKit instance of BuildingKit DTO class
     */
    public void update(BuildingKitDto buildingKit);
    
    /**
     * retrieves all building kits
     *
     */
    public List<BuildingKitDto> findAll();

    /**
     * retrieves building kit with given id
     *
     * @param id the given id
     */
    public BuildingKitDto findById(Long id);

    /**
     * retrieves building kits with relation to given category
     *
     * @param categoryDto the given category
     */
    public List<BuildingKitDto> findByCategory(CategoryDto categoryDto);

    /**
     * retrieves building kits with relation to given theme set
     *
     * @param themeSetDto the given theme set
     */
    public List<BuildingKitDto> findByThemeSet(ThemeSetDto themeSetDto);
    
    /**
     * find buildingKits by brick
     * @param brickDto give brick dto
     * @return List of buildinkKits dto
     */
    public List<BuildingKitDto> findByBrick(BrickDto brickDto);

}
