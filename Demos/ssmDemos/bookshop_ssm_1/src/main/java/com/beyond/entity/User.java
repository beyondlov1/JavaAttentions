package com.beyond.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.beyond.validation.group.UserValidationGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"password", "handler"})
public class User implements Serializable {

    private String id;

    @NotBlank(message = "{user.username.notNull}", groups = UserValidationGroup.class)
    @Pattern(regexp = "[a-zA-z][a-zA-z0-9]+", message = "{user.username.regex}", groups = UserValidationGroup.class)
    private String username;

    @NotBlank(message = "{user.password.notNull}", groups = UserValidationGroup.class)
    @Size(min = 6, max = 20, message = "{user.password.size}", groups = UserValidationGroup.class)
    private String password;

    private List<Book> ownBooks = new ArrayList<>();
    private List<Book> borrowBooks = new ArrayList<>();
    private List<Order> sendOrders = new ArrayList<>();
    private List<Order> reciveOrders = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getOwnBooks() {
        return ownBooks;
    }

    public void setOwnBooks(List<Book> ownBooks) {
        this.ownBooks = ownBooks;
    }

    public List<Book> getBorrowBooks() {
        return borrowBooks;
    }

    public void setBorrowBooks(List<Book> borrowBooks) {
        this.borrowBooks = borrowBooks;
    }

    public List<Order> getSendOrders() {
        return sendOrders;
    }

    public void setSendOrders(List<Order> sendOrders) {
        this.sendOrders = sendOrders;
    }

    public List<Order> getReciveOrders() {
        return reciveOrders;
    }

    public void setReciveOrders(List<Order> reciveOrders) {
        this.reciveOrders = reciveOrders;
    }

}
