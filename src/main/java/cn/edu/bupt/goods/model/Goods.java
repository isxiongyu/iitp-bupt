package cn.edu.bupt.goods.model;


import cn.edu.bupt.user.model.User;

/**
 * ClassName: Good
 * Description: 商品类
 * Create by xiongyu
 * Date: 2020/9/5 2:42 下午
 */
public class Goods {
    private String id;
    private String name;
    private String description;
    private double price;
    private String img;
    private int rest;
    /**
     * 所属一级分类
     */
    private Category category;
    /**
     * 所属二级分类
     */
    private CategorySecond categorySecond;
    /**
     * 所属用户
     */
    private User owner;
}
