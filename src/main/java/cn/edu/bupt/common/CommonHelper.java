package cn.edu.bupt.common;

import cn.edu.bupt.exception.user.SystemException;
import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: CommonHelper
 * Description:
 * Create by xiongyu
 * Date: 2020/9/10 1:08 上午
 */

@Component
public class CommonHelper {

    Logger logger = LoggerFactory.getLogger(CommonHelper.class);

    public String getUUID() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    /**
     * 用于将参数map转换成对应的bean
     * @param map 参数map
     * @param clazz 参数类型class对象
     * @param <T> 参数类型
     * @return
     * @throws SystemException
     */
    public <T> T toBean(Map<String, ?> map, Class<T> clazz) throws SystemException {
        try {
            T bean = clazz.newInstance();
            ConvertUtils.register(new DateLocaleConverter(), Date.class);
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            logger.error("注册属性填充错误: " + JSON.toJSONString(map));
            throw new SystemException("属性填充错误");
        }
    }
}
