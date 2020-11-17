package cn.edu.bupt.goods.sevice.impl;

import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.dao.repository.CategorySecondRepository;
import cn.edu.bupt.goods.model.CategorySecond;
import cn.edu.bupt.goods.sevice.CategorySecondService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: CategorySecondService
 * Description:
 * Create by xiongyu
 * Date: 2020/11/15 下午5:17
 */

@Service
public class CategorySecondServiceImpl implements CategorySecondService {

    Logger logger = LoggerFactory.getLogger(CategorySecondServiceImpl.class);
    @Autowired
    CategorySecondRepository categorySecondRepository;
    @Override
    public List<CategorySecond> queryByCategoryId(int categoryId) throws SystemException {
        try {
            return categorySecondRepository.selectByCategoryId(categoryId);
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }
}
