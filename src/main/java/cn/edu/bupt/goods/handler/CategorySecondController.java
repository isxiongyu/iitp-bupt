package cn.edu.bupt.goods.handler;

import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.model.CategorySecond;
import cn.edu.bupt.goods.sevice.CategorySecondService;
import cn.edu.bupt.goods.sevice.impl.CategorySecondServiceImpl;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: CategorySecondController
 * Description:
 * Create by xiongyu
 * Date: 2020/11/15 下午4:50
 */
@RequestMapping("/categorySecond")
@Controller
public class CategorySecondController {

    Logger logger = LoggerFactory.getLogger(CategorySecondServiceImpl.class);

    @Autowired
    CategorySecondService categorySecondService;

    @ResponseBody
    @RequestMapping("/getCategorySecondAJAX.do")
    public String getCategorySecondAJAX(int categoryId) throws SystemException {
        List<CategorySecond> categorySeconds;
        try {
            categorySeconds = categorySecondService.queryByCategoryId(categoryId);
        } catch (Exception e) {
            logger.error("系统性异常：{}", e.getMessage());
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
            throw new SystemException();
        }
        return JSON.toJSONString(categorySeconds);
    }
}
