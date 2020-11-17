package cn.edu.bupt.goods.sevice;

import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.model.CategorySecond;

import java.util.List;

/**
 * ClassName: CategorySecondService
 * Description:
 * Create by xiongyu
 * Date: 2020/11/15 下午5:21
 */
public interface CategorySecondService {
    List<CategorySecond> queryByCategoryId(int categoryId) throws SystemException;
}
