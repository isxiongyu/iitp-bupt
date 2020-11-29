package cn.edu.bupt.goods.sevice;

import cn.edu.bupt.common.model.MainResponse;
import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.model.Goods;

import java.util.List;

/**
 * ClassName: GoodsService
 * Description:
 * Create by xiongyu
 * Date: 2020/11/17 下午2:49
 */
public interface GoodsService {
    void addGoods(Goods goods) throws SystemException;
    List<Goods> queryAllGoods() throws SystemException;
    List<Goods> queryGoodsByCategoryId(int categoryId) throws SystemException;
}
