package cn.edu.bupt.common;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * ClassName: CommonHelper
 * Description:
 * Create by xiongyu
 * Date: 2020/9/10 1:08 上午
 */

@Component
public class CommonHelper {

    public String getUUID() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }
}
