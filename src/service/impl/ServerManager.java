package service.impl;

import service.LoginManager;

/**
 * 用以维护一些需要单例的对象
 */
public enum ServerManager {

    LOGIN_STATUS;

    private LoginManager loginManager;

    ServerManager() {
        loginManager = new LoginManagerImpl();
    }

    public LoginManager getLoginManager() {
        return this.loginManager;
    }

}
