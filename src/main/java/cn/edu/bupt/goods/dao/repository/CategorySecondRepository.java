package cn.edu.bupt.goods.dao.repository;

import cn.edu.bupt.goods.dao.CategorySecondDao;
import cn.edu.bupt.goods.helper.CategorySecondHelper;
import cn.edu.bupt.goods.model.CategorySecond;
import cn.edu.bupt.goods.model.CategorySecondVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: CategorySecondRepository
 * Description:
 * Create by xiongyu
 * Date: 2020/11/15 下午5:06
 */
@Repository
public class CategorySecondRepository {

    @Autowired
    CategorySecondDao categorySecondDao;

    @Autowired
    CategorySecondHelper categorySecondHelper;

    public List<CategorySecond> selectByCategoryId(int categoryId) {
        return categorySecondDao.selectByCategoryId(categoryId).stream().
                map(categorySecondHelper::convert2Model).collect(Collectors.toList());
    }
}
