package com.example.dodgema.service;

import org.springframework.stereotype.Service;

@Service
public class SNSDataService {
    private String nickName;
    private String email;
    private int loginKind;

    public void makeSNSData(String nickName, String email, int loginKind) {
        this.nickName = nickName;
        this.email = email;
        this.loginKind = loginKind;
    }

    public String getSNSNickname() {
        return this.nickName;
    }
    public String getSNSEmail() {
        return this.email;
    }
    public int getSNSLoginKind() {
        return this.loginKind;
    }
}