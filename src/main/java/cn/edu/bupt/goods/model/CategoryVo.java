package cn.edu.bupt.goods.model;

import java.util.List;

/**
 * ClassName: CategoryVo
 * Description:
 * Create by xiongyu
 * Date: 2020/11/18 下午2:51
 */
public class CategoryVo {
    private int id;
    private String name;
    private List<CategorySecond> categorySeconds;

    public CategoryVo() {
    }

    public CategoryVo(int id, String name, List<CategorySecond> categorySeconds) {
        this.id = id;
        this.name = name;
        this.categorySeconds = categorySeconds;
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

    public List<CategorySecond> getCategorySeconds() {
        return categorySeconds;
    }

    public void setCategorySeconds(List<CategorySecond> categorySeconds) {
        this.categorySeconds = categorySeconds;
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categorySeconds=" + categorySeconds +
                '}';
    }
}
