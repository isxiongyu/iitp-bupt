package cn.edu.bupt.goods.handler;

import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.model.Category;
import cn.edu.bupt.goods.sevice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * ClassName: CategoryController
 * Description:
 * Create by xiongyu
 * Date: 2020/11/15 下午4:48
 */
@RequestMapping("/category")
@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/getAllCategory.do")
    public ModelAndView getAllCategory() throws SystemException {
        ModelAndView mv = new ModelAndView();
        List<Category> categories = categoryService.selectAll();
        mv.addObject("categories", categories);
        mv.setViewName("");
        return mv;
    }
}
