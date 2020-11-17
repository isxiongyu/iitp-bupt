package cn.edu.bupt.order.model;

import cn.edu.bupt.goods.model.GoodsVo;
import cn.edu.bupt.user.model.User;

import java.util.Date;

/**
 * ClassName: OrderItem
 * Description: 订单条目（存库）
 * Create by xiongyu
 * Date: 2020/9/6 11:36 上午
 */
public class OrderItem {
    private String id;
    /**
     * 订单条目拥有者（下单用户）
     */
    private User owner;
    /**
     * 下单时间
     */
    private Date orderTime;
    /**
     * 商家用户
     */
    private User seller;

    /**
     * 商品
     */
    private GoodsVo goods;
}
