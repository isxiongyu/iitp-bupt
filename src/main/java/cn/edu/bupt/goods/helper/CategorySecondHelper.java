package cn.edu.bupt.goods.helper;

import cn.edu.bupt.goods.dao.CategorySecondDo;
import cn.edu.bupt.goods.model.CategorySecond;
import org.springframework.stereotype.Component;

/**
 * ClassName: CategorySecondHelper
 * Description:
 * Create by xiongyu
 * Date: 2020/11/15 下午5:09
 */
@Component
public class CategorySecondHelper {
    public CategorySecond convert2Model(CategorySecondDo categorySecondDo) {
        CategorySecond categorySecond = new CategorySecond();
        if (categorySecondDo == null) return categorySecond;
        categorySecond.setId(categorySecondDo.getId());
        categorySecond.setName(categorySecondDo.getName());
        categorySecond.setCategoryId(categorySecondDo.getCategoryId());
        return categorySecond;
    }
}