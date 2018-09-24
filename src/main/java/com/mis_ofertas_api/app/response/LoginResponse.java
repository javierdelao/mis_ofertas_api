package com.mis_ofertas_api.app.response;

import com.mis_ofertas_api.app.model.SystemUser;

public class LoginResponse {

    private SystemUser user;

    private String status;

    public LoginResponse() {
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
