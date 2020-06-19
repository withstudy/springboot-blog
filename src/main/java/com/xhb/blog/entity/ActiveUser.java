package com.xhb.blog.entity;

import java.util.List;

public class ActiveUser {
    private User user;
    private List<String> functions;

    public ActiveUser(User user, List<String> functions) {
        this.user = user;
        this.functions = functions;
    }

    public ActiveUser() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<String> getFunctions() {
        return functions;
    }

    public void setFunctions(List<String> functions) {
        this.functions = functions;
    }
}
