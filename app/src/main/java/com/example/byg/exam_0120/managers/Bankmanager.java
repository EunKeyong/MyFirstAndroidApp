package com.example.byg.exam_0120.managers;

import com.example.byg.exam_0120.models.AccountInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byg on 2017-02-08.
 */

public class Bankmanager {

    private static Bankmanager sInstance = new Bankmanager();

    // 관리자
    private final static String ADMIN_ID = "admin";
    private final static String ADMIN_PASSWORD = "admin";

    // 고객 계좌 정보
    private List<AccountInfo> mAccountList;

    // 싱글턴 패턴(인스턴스 하나만 갖게 함)
    public static Bankmanager newInstance() {
        return sInstance;

    }

    private Bankmanager() {
        mAccountList = new ArrayList<>();
    }

    /**
     * @param accountinfo 개설 할 계좌 정보
     */
    public void open(AccountInfo accountinfo) {
        mAccountList.add(accountinfo);

    }

    /**
     * 로그인
     *
     * @param id       아이디
     * @param password 패스워드
     * @return 없으면 null, 있으면 해당 계좌
     */
    public AccountInfo login(String id, String password) {
        for (AccountInfo accountInfo : mAccountList) {
            if (accountInfo.getId().equals(id) &&
                    accountInfo.getPassword().equals(password)) {
                return accountInfo;
            }
        }
        return null;
    }

    /**
     * 관리자 권한 확인
     *
     * @param id       아이디
     * @param password 패스워드
     * @return 관리자면 true, 아니면 false
     */
    public boolean isAdmin(String id, String password) {
        return ADMIN_ID.equals(id) && ADMIN_PASSWORD.equals(password);
    }

    /**
     * 전체 계좌 정보 얻음
     *
     * @return 전체 계좌 정보
     */
    public List<AccountInfo> getAccountList() {
        return mAccountList;
    }
}
