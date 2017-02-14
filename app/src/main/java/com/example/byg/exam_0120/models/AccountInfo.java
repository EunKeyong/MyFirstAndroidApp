package com.example.byg.exam_0120.models;

import java.io.Serializable;

/**
 * Created by byg on 2017-02-08.
 */

public class AccountInfo implements Serializable {

    private String id;
    private String password;
    private int balance;

    public AccountInfo(String id, String password, int balance) {
        this.id = id;
        this.password = password;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("조회한 ID : %s\n조회한 계좌의 잔액 : %d", id, balance);
    }
}
