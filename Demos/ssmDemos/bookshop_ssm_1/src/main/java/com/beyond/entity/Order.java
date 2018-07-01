package com.beyond.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.beyond.f.F;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"})
public class Order implements Serializable {

    private String id;
    private Book book;
    private User owner;
    private Book exchangeBook;
    private User exchanger;
    private Timestamp createTime;
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Book getExchangeBook() {
        return exchangeBook;
    }

    public void setExchangeBook(Book exchangeBook) {
        this.exchangeBook = exchangeBook;
    }

    public User getExchanger() {
        return exchanger;
    }

    public void setExchanger(User exchanger) {
        this.exchanger = exchanger;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void accept() {
        if (status == null) {
            status = 0;
        }
        status++;
    }

    public void confirm() {
        if (owner != null) {
            status += F.ORDER_OWNER_CONFIRM_WEIGHT;
        }
        if (exchanger != null) {
            status += F.ORDER_EXCHANGER_CONFIRM_WEIGHT;
        }
    }
}
