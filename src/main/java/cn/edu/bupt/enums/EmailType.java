package cn.edu.bupt.enums;

import com.alibaba.druid.util.StringUtils;

/**
 * ClassName: EmailType
 * Description:
 * Create by xiongyu
 * Date: 2020/9/15 12:00 上午
 */
public enum EmailType {
    REGISTER("用户注册激活码", "register"),
    FORGET_PASSWORD("忘记密码，用于发送验证码", "forgetPassword");
    private String name;
    private String code;

    EmailType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static EmailType getByCode(String code) {
        EmailType[] values = EmailType.values();
        for (EmailType emailType : values) {
            if (StringUtils.equals(emailType.getCode(), code)) {
                return emailType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "EmailType{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
