package cn.edu.bupt.goods.dao.repository;

import cn.edu.bupt.goods.dao.GoodsDao;
import cn.edu.bupt.goods.helper.GoodsHelper;
import cn.edu.bupt.goods.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ClassName: GoodsRepository
 * Description:
 * Create by xiongyu
 * Date: 2020/11/16 下午6:08
 */
@Repository
public class GoodsRepository {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    GoodsHelper goodsHelper;
    public void insertGoods(Goods goods) {
        goodsDao.insert(goodsHelper.convert2Do(goods));
    }
}
