package cn.edu.bupt.goods.helper;

import cn.edu.bupt.goods.dao.GoodsDao;
import cn.edu.bupt.goods.dao.GoodsDo;
import cn.edu.bupt.goods.model.Goods;
import cn.edu.bupt.goods.model.GoodsVo;
import org.springframework.stereotype.Component;

/**
 * ClassName: GoodsHelper
 * Description:
 * Create by xiongyu
 * Date: 2020/11/15 下午5:09
 */
@Component
public class GoodsHelper {
    public Goods convert2Model(GoodsDo goodsDo) {
        Goods goods = new Goods();
        if (goodsDo == null) {
            return goods;
        }
        goods.setId(goodsDo.getId());
        goods.setImg(goodsDo.getImg());
        goods.setCategorySecondId(goodsDo.getCategorySecondId());
        goods.setGoodCategoryId(goodsDo.getGoodCategoryId());
        goods.setDescription(goodsDo.getDescription());
        goods.setName(goodsDo.getName());
        goods.setOwnerId(goodsDo.getOwnerId());
        goods.setRest(goodsDo.getRest());
        goods.setPrice(goodsDo.getPrice());
        return goods;
    }
    public GoodsDo convert2Do(Goods goods) {
        GoodsDo goodsDo = new GoodsDo();
        if (goods == null) {
            return goodsDo;
        }
        goodsDo.setId(goods.getId());
        goodsDo.setImg(goods.getImg());
        goodsDo.setCategorySecondId(goods.getCategorySecondId());
        goodsDo.setGoodCategoryId(goods.getGoodCategoryId());
        goodsDo.setDescription(goods.getDescription());
        goodsDo.setName(goods.getName());
        goodsDo.setOwnerId(goods.getOwnerId());
        goodsDo.setRest(goods.getRest());
        goodsDo.setPrice(goods.getPrice());
        return goodsDo;
    }
}
