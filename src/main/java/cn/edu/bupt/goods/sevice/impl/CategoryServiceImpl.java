package cn.edu.bupt.goods.sevice.impl;

import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.dao.repository.CategoryRepository;
import cn.edu.bupt.goods.model.Category;
import cn.edu.bupt.goods.sevice.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: CategorySeviceImpl
 * Description:
 * Create by xiongyu
 * Date: 2020/11/16 上午12:15
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> selectAll() throws SystemException {
        try {
            return categoryRepository.selectAll();
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }
}
