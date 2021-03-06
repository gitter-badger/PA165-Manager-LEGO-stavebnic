package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

import java.util.List;

/**
 * brick actionbean
 *
 * @author Jiri Krepl
 */
@UrlBinding("/bricks/{$event}")
public class BrickActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    private BrickService brickService;
    private List<BrickDto> bricks;
    @SpringBean
    private BuildingKitService buildingKitService;

    @ValidateNestedProperties(
            value = {
                @Validate(on = {"createBrick"}, field = "name", required = true, maxlength = 50)
            }
    )
    //one brick used for some operations (deletition, edit)
    private BrickDto brick;

    /**
     * return all books for table in some jsp
     *
     * @return
     */
    public List<BrickDto> getBricks() {
        return bricks;
    }

    public BrickDto getBrick() {
        return brick;
    }

    /**
     * without setter, posting brick forms would not work
     *
     * @param brick
     */
    public void setBrick(BrickDto brick) {
        this.brick = brick;
    }

    /**
     * downloads all bricks and redirect to brick/list.jsp list.jsp uses this
     * action bean = uses data and its methods
     *
     * @return resolution Redirects to list
     */
    @DefaultHandler
    public Resolution list() {
        bricks = brickService.findAll();
        return new ForwardResolution("/brick/brickList.jsp");
    }

    /**
     * method which deletes the brick
     *
     * @return resolution Redirects to list
     */
    public Resolution delete() {
        brick = brickService.findById(brick.getId());
        List<BuildingKitDto> buildingKitDtoList = buildingKitService.findByBrick(brick);

        // list is empty, brick is not contained in any building kit => delete brick
        if (buildingKitDtoList.isEmpty()) {
            brickService.delete(brick.getId());

        } else { // list is not empty == brick is used by some building kit
            StringBuilder sb = new StringBuilder();
            sb.append("<ul>");
            for (BuildingKitDto kit : buildingKitDtoList) {
                sb.append("<li>");
                sb.append(kit.getName());
                sb.append("</li>");                
            }
            sb.append("</ul>");
            
            getContext().getMessages().add(new LocalizableMessage("brick.delete.contains", sb.toString()));
        }

        // returns back to list (in fact call lis() method from this class)
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     * creates brick
     *
     * @return
     */
    public Resolution createBrick() {
        brickService.create(brick);
        return new ForwardResolution(this.getClass(), "list");
    }

    /**
     * redirects to brick edit page
     *
     * @return
     */
    public Resolution openEditPage() {
        return new ForwardResolution("/brick/brickEdit.jsp");
    }

    /**
     * updated edited values of brick; after submitting edit form correct values
     * of brick are setted automatically from form
     *
     * @return
     */
    public Resolution updateBrick() {
        brickService.update(brick);
        brick = null;
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        bricks = brickService.findAll();
        return null;
    }
}
