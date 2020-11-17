package cn.edu.bupt.goods.model;

/**
 * ClassName: CategorySecond
 * Description: 二级目录
 * Create by xiongyu
 * Date: 2020/9/5 6:23 下午
 */
public class CategorySecondVo {
    private int id;
    private String name;
    /**
     * 所属一级目录
     */
    private Category category;

    public CategorySecondVo() {
    }

    public CategorySecondVo(int id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategorySecond{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }
}
