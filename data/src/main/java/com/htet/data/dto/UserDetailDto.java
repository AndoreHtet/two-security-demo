package com.htet.data.dto;

import com.htet.data.entities.User;
import com.htet.data.utils.ActiveStatus;
import lombok.Data;

@Data
public class UserDetailDto {

    private String name;

    private String phoneNo;

    private String roleName;

    private String password;

    private boolean locked;

    private boolean activated;

    public UserDetailDto(User user){
        this.name = user.getName();
        this.phoneNo = user.getPhoneNo();
        this.roleName = user.getRole().getName();
        this.password = user.getPassword();
        this.activated = user.getStatus() == ActiveStatus.ACTIVE.getCode();
        this.locked = user.isLocked();
    }
}
