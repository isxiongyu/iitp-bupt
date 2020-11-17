package cn.edu.bupt.goods.model;

import cn.edu.bupt.user.model.User;
import com.alibaba.druid.util.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ClassName: Goods
 * Description:
 * Create by xiongyu
 * Date: 2020/11/15 上午5:18
 */
public class Goods {
    private String id;
    @NotNull(message="物品名不能为空")
    @Size(min=1, max=10, message="物品名长度必须在{min}-{max}内")
    private String name;
    @Size(max=100, message="物品描述字数长度必须在{min}-{max}内")
    private String description;
    private double price;
    private String img;
    private int rest;
    /**
     * 所属一级分类
     */
    @NotNull(message="请选择物品所属一级分类")
    private Integer goodCategoryId;
    /**
     * 所属二级分类
     */
    @NotNull(message="请选择物品所属二级分类")
    private Integer categorySecondId;
    /**
     * 所属用户
     */
    private String ownerId;

    public Goods() {
    }

    public Goods(String id, String name, String description, double price, String img, int rest, Integer goodCategoryId, Integer categorySecondId, String ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.rest = rest;
        this.goodCategoryId = goodCategoryId;
        this.categorySecondId = categorySecondId;
        this.ownerId = ownerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public Integer getGoodCategoryId() {
        return goodCategoryId;
    }

    public void setGoodCategoryId(Integer goodCategoryId) {
        this.goodCategoryId = goodCategoryId;
    }

    public Integer getCategorySecondId() {
        return categorySecondId;
    }

    public void setCategorySecondId(Integer categorySecondId) {
        this.categorySecondId = categorySecondId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
