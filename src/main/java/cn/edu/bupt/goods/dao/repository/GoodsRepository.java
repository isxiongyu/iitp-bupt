package cn.edu.bupt.goods.dao.repository;

import cn.edu.bupt.goods.dao.GoodsDao;
import cn.edu.bupt.goods.helper.GoodsHelper;
import cn.edu.bupt.goods.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Goods> queryAllGoods() {
        return goodsDao.selectAll().stream().map(goodsHelper::convert2Model).collect(Collectors.toList());
    }
    public List<Goods> queryGoodsByCategoryIdPage(int categoryId, int pageSize, int page) {
        Map<String, Integer> params = new HashMap<>();
        int first = pageSize * (page - 1);
        int second = pageSize * page;
        params.put("categoryId", categoryId);
        params.put("first", first);
        params.put("second", second);
        return goodsDao.selectByCategoryIdPage(params).stream().map(goodsHelper::convert2Model).collect(Collectors.toList());
    }
    public List<Goods> queryGoodsBySecondCategoryId(int categorySecondId, int pageSize, int page) {
        Map<String, Integer> params = new HashMap<>();
        int first = pageSize * (page - 1);
        int second = pageSize * page;
        params.put("categorySecondId", categorySecondId);
        params.put("first", first);
        params.put("second", second);
        return goodsDao.selectByCategorySecondIdPage(params).stream().map(goodsHelper::convert2Model).collect(Collectors.toList());
    }
}
