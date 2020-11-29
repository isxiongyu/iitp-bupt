package cn.edu.bupt.common.service;

import cn.edu.bupt.common.model.MainResponse;
import cn.edu.bupt.exception.user.SystemException;

/**
 * ClassName: CommonService
 * Description:
 * Create by xiongyu
 * Date: 2020/11/17 下午3:17
 */
public interface CommonService {
    MainResponse queryMain() throws SystemException;
}
