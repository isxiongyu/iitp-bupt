package cn.edu.bupt.goods.sevice;

import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.model.Category;

import java.util.List;

/**
 * ClassName: CategoryService
 * Description:
 * Create by xiongyu
 * Date: 2020/11/16 上午12:14
 */
public interface CategoryService {
    List<Category> selectAll() throws SystemException;
}
