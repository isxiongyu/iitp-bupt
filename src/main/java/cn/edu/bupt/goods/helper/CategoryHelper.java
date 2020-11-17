package cn.edu.bupt.goods.helper;

import cn.edu.bupt.goods.dao.CategoryDo;
import cn.edu.bupt.goods.model.Category;
import org.springframework.stereotype.Component;

/**
 * ClassName: CategoryHelper
 * Description:
 * Create by xiongyu
 * Date: 2020/11/16 上午12:09
 */
@Component
public class CategoryHelper {
    public Category convert2Model(CategoryDo categoryDo) {
        Category category = new Category();
        if (categoryDo == null) return category;
        category.setId(categoryDo.getId());
        category.setName(categoryDo.getName());
        return category;
    }
}
