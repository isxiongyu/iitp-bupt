package cn.edu.bupt.goods.model;

/**
 * ClassName: CategorySecond
 * Description: 二级目录
 * Create by xiongyu
 * Date: 2020/9/5 6:23 下午
 */
public class CategorySecond {
    private int id;
    private String name;
    /**
     * 所属一级目录
     */
    private int categoryId;

    public CategorySecond() {
    }

    public CategorySecond(int id, String name, int categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "CategorySecond{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
