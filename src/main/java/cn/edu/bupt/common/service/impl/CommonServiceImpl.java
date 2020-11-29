package cn.edu.bupt.common.service.impl;

import cn.edu.bupt.common.model.MainResponse;
import cn.edu.bupt.common.service.CommonService;
import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.dao.repository.CategoryRepository;
import cn.edu.bupt.goods.dao.repository.CategorySecondRepository;
import cn.edu.bupt.goods.dao.repository.GoodsRepository;
import cn.edu.bupt.goods.model.Category;
import cn.edu.bupt.goods.model.CategorySecond;
import cn.edu.bupt.goods.model.CategoryVo;
import cn.edu.bupt.goods.model.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: CommonService
 * Description:
 * Create by xiongyu
 * Date: 2020/11/17 下午3:16
 */

@Service
public class CommonServiceImpl implements CommonService {

    Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategorySecondRepository categorySecondRepository;
    @Override
    public MainResponse queryMain() throws SystemException {
        MainResponse mainResponse = new MainResponse();
        try {
            List<Goods> goodsList = goodsRepository.queryAllGoods();
            List<Category> categories = categoryRepository.selectAll();
            List<CategoryVo> categoryVos = categories.stream().map(category -> {
                CategoryVo categoryVo = new CategoryVo();
                categoryVo.setId(category.getId());
                categoryVo.setName(category.getName());
                categoryVo.setCategorySeconds(categorySecondRepository.selectByCategoryId(category.getId()));
                return categoryVo;
            }).collect(Collectors.toList());
            mainResponse.setGoodsList(goodsList);
            mainResponse.setCategoryVos(categoryVos);
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
        return mainResponse;
    }
}
