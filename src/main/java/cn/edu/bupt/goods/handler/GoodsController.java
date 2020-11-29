package cn.edu.bupt.goods.handler;

import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.model.Goods;
import cn.edu.bupt.goods.sevice.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * ClassName: GoodsController
 * Description:
 * Create by xiongyu
 * Date: 2020/11/15 下午3:38
 */
@RequestMapping("/goods")
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/getGoodsByCategoryId.do")
    public ModelAndView getGoodsByCategoryId(int categoryId) throws SystemException {
        ModelAndView mv = new ModelAndView();
        List<Goods> goodsList = goodsService.queryGoodsByCategoryId(categoryId);
        mv.addObject("goodsList", goodsList);
        mv.setViewName("main");
        return mv;
    }
    @RequestMapping("/getGoodsByCategorySecondId.do")
    public ModelAndView getGoodsByCategorySecondId(int categorySecondId) throws SystemException {
        ModelAndView mv = new ModelAndView();
        List<Goods> goodsList = goodsService.queryGoodsByCategoryId(categorySecondId);
        mv.addObject("goodsList", goodsList);
        mv.setViewName("main");
        return mv;
    }
}
