package cn.edu.bupt.goods.model;


import cn.edu.bupt.user.model.User;

/**
 * ClassName: Good
 * Description: 商品类
 * Create by xiongyu
 * Date: 2020/9/5 2:42 下午
 */
public class GoodsVo {
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
    private CategorySecondVo categorySecond;
    /**
     * 所属用户
     */
    private User owner;

    public GoodsVo() {
    }

    public GoodsVo(String id, String name, String description, double price, String img, int rest, Category category, CategorySecondVo categorySecond, User owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.rest = rest;
        this.category = category;
        this.categorySecond = categorySecond;
        this.owner = owner;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategorySecondVo getCategorySecond() {
        return categorySecond;
    }

    public void setCategorySecond(CategorySecondVo categorySecond) {
        this.categorySecond = categorySecond;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
