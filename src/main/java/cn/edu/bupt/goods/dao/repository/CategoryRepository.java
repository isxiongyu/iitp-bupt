package cn.edu.bupt.goods.dao.repository;

import cn.edu.bupt.goods.dao.CategoryDao;
import cn.edu.bupt.goods.dao.CategoryDo;
import cn.edu.bupt.goods.helper.CategoryHelper;
import cn.edu.bupt.goods.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: CategoryRepository
 * Description:
 * Create by xiongyu
 * Date: 2020/11/16 上午12:03
 */
@Repository
public class CategoryRepository {
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    CategoryHelper categoryHelper;

    public List<Category> selectAll() {
        return categoryDao.selectAll().stream().
                map(categoryHelper::convert2Model).collect(Collectors.toList());
    }
}
