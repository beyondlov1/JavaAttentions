package com.beyond.join;

import java.math.BigDecimal;

public class BookExt {
    private String bookId;
    private BigDecimal price;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookExt{" +
                "bookId='" + bookId + '\'' +
                ", price=" + price +
                '}';
    }
}
