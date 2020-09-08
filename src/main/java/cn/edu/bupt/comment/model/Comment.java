package cn.edu.bupt.comment.model;

import cn.edu.bupt.goods.model.Goods;
import cn.edu.bupt.user.model.User;

import java.util.Date;

/**
 * ClassName: Comment
 * Description: 评论
 * Create by xiongyu
 * Date: 2020/9/6 3:04 下午
 */
public class Comment {
    private String id;
    private String content;
    private Date time;
    /**
     * 评论用户
     */
    private User user;
    /**
     * 评论商品
     */
    private Goods good;
}
