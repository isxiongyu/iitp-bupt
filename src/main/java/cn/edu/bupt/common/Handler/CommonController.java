package cn.edu.bupt.common.Handler;

import cn.edu.bupt.common.model.MainResponse;
import cn.edu.bupt.common.service.CommonService;
import cn.edu.bupt.exception.user.SystemException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: CommonController
 * Description:
 * Create by xiongyu
 * Date: 2020/11/17 下午2:38
 */
@RequestMapping("/common")
@Controller
public class CommonController {

    @Autowired
    CommonService commonService;

    @ResponseBody
    @RequestMapping("/main.do")
    public String main() throws SystemException {
        MainResponse mainResponse = commonService.queryMain();
        return JSON.toJSONString(mainResponse);
    }
}
