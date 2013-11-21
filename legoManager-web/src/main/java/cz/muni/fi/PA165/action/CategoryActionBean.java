package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.service.CategoryService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 11/21/13
 */
@UrlBinding("/categories/{$event}")
public class CategoryActionBean extends BaseActionBean {

    @SpringBean
    private CategoryService service;
    List<CategoryDto> categories;

    public List<CategoryDto> getCategories() {
        categories = service.findAll();
        return categories;
    }

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/category/list.jsp");
    }

    public Resolution add() {
        return new ForwardResolution("/category/list.jsp");
    }
}