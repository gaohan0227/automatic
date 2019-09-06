package priv.bigant.aotomatic.autoCode.bean;

import priv.bigant.aotomatic.common.BaseEntity;

public class SysUserRole extends BaseEntity {
    private String userId;
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
