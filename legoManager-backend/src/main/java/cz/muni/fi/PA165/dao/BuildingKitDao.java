package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.BuildingKit;
import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 * Dao Class for BuildingKit
 * 
 * @author Tomas Kopecky
 */
public interface BuildingKitDao {
    
    /**
     * persist given building kit
     *
     * @param buildingKit instance of BuildingKit entity class
     */
    public void create(BuildingKit buildingKit);

    /**
     * deletes given building kit
     *
     * @param id id of the instance of the building kit to remove
     */
    public void delete(Long id);

    /**
     * updates given building kit
     *
     * @param buildingKit instance of BuildingKit entity class
     */
    public void update(BuildingKit buildingKit);

    /**
     * sets the entity manager
     *
     * @param entityManager instance of EntityManager class
     */
    public void setEntityManager(EntityManager entityManager);

    /**
     * retrieves all building kits from the database
     *
     */
    public List<BuildingKit> findAll();

    /**
     * retrieves all building kits with given price from the database
     *
     * @param price the given price
     */
    public List<BuildingKit> findByPrice(BigDecimal price);

    /**
     * retrieves all building kits with given year or higher year from the database
     *
     * @param yearFrom the given year
     */
    public List<BuildingKit> findByYearFrom(int yearFrom);

    /**
     * retrieves building kit with given id from the database
     *
     * @param id the given id
     */
    public BuildingKit findById(Long id);

    /**
     * retrieves building kit with relation to given brick from the database
     *
     * @param brick the given brick
     */
    public List<BuildingKit> findByBrick(Brick brick);

    /**
     * retrieves building kit with relation to given category from the database
     *
     * @param category the given category
     */
    public List<BuildingKit> findByCategory(Category category);

    /**
     * retrieves building kit with relation to given theme set from the database
     *
     * @param themeSet the given theme set
     */
    public List<BuildingKit> findByThemeSet(ThemeSet themeSet);
}
