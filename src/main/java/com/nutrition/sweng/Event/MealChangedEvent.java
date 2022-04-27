/*
package com.nutrition.sweng.Event;

import java.util.Objects;

public class MealChangedEvent {

    private Integer userId;
    private String name;
    private String email;
    private String inviteCode;

    public MealChangedEvent(){
    }

    public MealChangedEvent(Integer id, String name, String email, String inviteCode) {
        this.userId = id;
        this.name = name;
        this.email = email;
        this.inviteCode = inviteCode;
    }

    public mealChangedEvent(User registeredUser, String inviteCode) {
        this.userId = registeredUser.getId();
        this.name = registeredUser.getName();
        this.email = registeredUser.getEmail();
        this.inviteCode = inviteCode;
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

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    @Override
    public String toString() {
        return "UserRegisteredEvent{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegisteredEvent that = (UserRegisteredEvent) o;
        return Objects.equals(userId, that.userId) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(inviteCode, that.inviteCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, inviteCode);
    }

}
*/
