package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.service.BrickService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 * brick actionbean
 * 
 * @author Jiri Krepl
 */

// tohle url by melo byt jine nez url slozky z jsp, jinak to dela bordel
// predtim tam bylo "/brick/{event}" -- nefungovalo volani jine metody nez list s @defaultHandlerem
// ted je tu: "brick/{event}" -- funguje i pro delete ^_^
@UrlBinding("/bricks/{$event}")
public class BrickActionBean extends BaseActionBean {
    
    @SpringBean
    private BrickService brickService;
    private List<BrickDto> bricks;
    private BrickDto brick; //one brick used for some operations (deletition, edit)
    
    /**
     * return all books for table in some jsp
     * @return 
     */
    public List<BrickDto> getBricks() {
        return bricks;
    }

    
    public BrickDto getBrick() {
        return brick;
    }

    /**
     * without setter, posting brick forms wouldnt work 
     * @param brick 
     */
    public void setBrick(BrickDto brick) {
        this.brick = brick;
    }

    /**
     * downloads all bricks and redirect to brick/list.jsp
     * list.jsp uses this action bean = uses data and its methods
     * @return resolution Redirects to list 
     */
    @DefaultHandler
    public Resolution list() {
        bricks = brickService.findAll();
        return new ForwardResolution("/brick/brickList.jsp");
    }
    
    /**
     * method which deletes the brick
     * @return resolution Redirects to list
     */
    public Resolution delete() {
        // id se ziskalo z formulare name=brick.id, a bylo do objektu brick
        // setnuto pres setBrick
        brickService.delete(brick.getId());
        // getContext().getMessages().add(new LocalizableMessage("book.delete.message",escapeHTML(book.getTitle()),escapeHTML(book.getAuthor())));
        
        // returns back to list (in fact call lis() method from this class)
        return new RedirectResolution(this.getClass(), "list");
    }
    
    /**
     * creates brick
     * @return 
     */
    public Resolution createBrick() {
        brickService.create(brick);
        return new ForwardResolution(this.getClass(), "list");
    }
    
    /**
     * redirects to brick edit page
     * @return 
     */
    public Resolution openEditPage() {
        return new ForwardResolution("/brick/brickEdit.jsp");
    }
}
