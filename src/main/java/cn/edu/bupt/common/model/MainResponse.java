package cn.edu.bupt.common.model;

import cn.edu.bupt.goods.model.CategoryVo;
import cn.edu.bupt.goods.model.Goods;

import java.util.List;

/**
 * ClassName: Main
 * Description:
 * Create by xiongyu
 * Date: 2020/11/17 下午3:07
 */
public class MainResponse {
    private List<Goods> goodsList;
    private List<CategoryVo> categoryVos;

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public List<CategoryVo> getCategoryVos() {
        return categoryVos;
    }

    public void setCategoryVos(List<CategoryVo> categoryVos) {
        this.categoryVos = categoryVos;
    }

    @Override
    public String toString() {
        return "MainResponse{" +
                "goodsList=" + goodsList +
                ", categoryVos=" + categoryVos +
                '}';
    }
}
