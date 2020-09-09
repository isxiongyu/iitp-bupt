package cn.edu.bupt.enums;

/**
 * ClassName: UserStatus
 * Description:
 * Create by xiongyu
 * Date: 2020/9/7 6:09 下午
 */
public enum UserStatus {
    UN_ACTIVE("未激活", "unActive"),
    ACTIVE("已激活", "active"),
    BLACK("黑名单", "black");
    private String name;

    private String code;

    UserStatus(String name, String code) {
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

    public static UserStatus getByCode(String code) {
        UserStatus[] values = UserStatus.values();
        for (UserStatus userStatus : values) {
            if (userStatus.getCode().equals(code)) {
                return userStatus;
            }
        }
        return null;
    }
}
