package cn.edu.bupt.goods.sevice.impl;

import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.dao.repository.GoodsRepository;
import cn.edu.bupt.goods.model.Goods;
import cn.edu.bupt.goods.sevice.GoodsService;
import cn.edu.bupt.user.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: GoodsServiceImpl
 * Description:
 * Create by xiongyu
 * Date: 2020/11/17 下午2:49
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    GoodsRepository goodsRepository;
    @Override
    public void addGoods(Goods goods) throws SystemException {
        if (goods == null) return;
        try {
            goodsRepository.insertGoods(goods);
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }

    @Override
    public List<Goods> queryAllGoods() throws SystemException {
        try {
            return goodsRepository.queryAllGoods();
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }

    @Override
    public List<Goods> queryGoodsByCategoryIdPage(int categoryId, int pageSize, int page) throws SystemException {
        try {
            return goodsRepository.queryGoodsByCategoryIdPage(categoryId, pageSize, page);
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }

    @Override
    public List<Goods> queryGoodsByCategorySecondIdPage(int categorySecondId, int pageSize, int page) throws SystemException {
        try {
            return goodsRepository.queryGoodsBySecondCategoryId(categorySecondId, pageSize, page);
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }
}
