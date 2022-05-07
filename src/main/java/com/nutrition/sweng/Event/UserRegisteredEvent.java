package com.nutrition.sweng.Event;

import com.nutrition.sweng.Model.User;

import java.util.Objects;

public class UserRegisteredEvent {

    private Integer userId;
    private String name;
    private String email;

    public UserRegisteredEvent() {
    }

    public UserRegisteredEvent(Integer id, String name, String email, String inviteCode) {
        this.userId = id;
        this.name = name;
        this.email = email;
    }

    public UserRegisteredEvent(User registeredUser, String inviteCode) {
        this.userId = registeredUser.getId();
        this.name = registeredUser.getName();
        this.email = registeredUser.getEmail();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserRegisteredEvent{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegisteredEvent that = (UserRegisteredEvent) o;
        return Objects.equals(userId, that.userId) && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email);
    }
}


