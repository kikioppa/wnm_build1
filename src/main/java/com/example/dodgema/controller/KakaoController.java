package com.example.dodgema.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/kakao")
public class KakaoController {

    static Logger logger = LoggerFactory.getLogger(KakaoController.class);

    HttpConnection conn = HttpConnection.getInstance();


    @GetMapping(value = "/login")
    public String kakao(HttpServletResponse res) throws IOException {

        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://kauth.kakao.com/oauth/authorize?client_id=");
        loginUrl.append("80f815c1c8e70bed6e21bce82a93aa5e"); //카카오 앱에 있는 REST KEY
        loginUrl.append("&redirect_uri=");
        loginUrl.append("http://localhost:80/kakao/callback"); //카카오 앱에 등록한 redirect URL
        loginUrl.append("&response_type=code");
        System.out.println(loginUrl.toString());
        return "redirect:"+loginUrl.toString();
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String redirect(@RequestParam String code, HttpServletResponse res) throws IOException, ParseException {
        //code
        //사용자가 취소 누르면 error 파라메터를 받음
        // 그때 여기서 구분해야할듯
        Map<String, String> map = new HashMap<String, String>();
        map.put("grant_type", "=authorization_code");
        map.put("client_id", "=80f815c1c8e70bed6e21bce82a93aa5e"); //카카오 앱에 있는 REST KEY
        map.put("redirect_uri", "=http://localhost:80/kakao/callback"); //카카오 앱에 등록한 redirect URL
        map.put("code", "="+code);

        System.out.println("탄다탄다");
        String out = conn.HttpPostConnection("https://kauth.kakao.com/oauth/token", map).toString();
        ObjectMapper mapper = new ObjectMapper();
        String access_token = mapper.readTree(out).findValue("access_token").toString();
        System.out.println("access_token : " + access_token);
        map.clear();
        map.put("Authorization", "Bearer "+ access_token);
        out = conn.HttpPostConnection("https://kapi.kakao.com/v2/user/me", map).toString();
        String email = mapper.readTree(out).findValue("kakao_account").findValue("email").toString();
        //String nickname = mapper.readTree(out).findValue("properties").findValue("nickname").toString();
        System.out.println("email : "+  email);
       // System.out.println("nickname : "+  nickname);

     /*   access_token = access_token.substring(1, access_token.length()-1);
        email = email.substring(1, email.length()-1);
        nickname = nickname.substring(1, nickname.length()-1);*/
        System.out.println(access_token);



     return "redirect:http://localhost:80";


        /*User user = userAccountService.getUserByEmail(email);
        if(user != null && (user.getLoginkind() & 2)> 1) { // 카카오 가입정보가 있을경우 로그인
            String token = jwtService.create(user, 1, access_token);
            SService.makeSNSData(token, email);
            return "redirect:http://"+fronturl+"/user/snsLogin";
        }
        if(user != null) { // 다른 종류 가입정보가 있을경우
            SDService.makeSNSData(nickname, email, 2);
            return "redirect:http://"+fronturl+"/user/snsCombine";
        }
        else { // 가입정보가 없는경우
            SDService.makeSNSData(nickname, email, 2);
            return "redirect:http://"+fronturl+"/user/snsRegist";
        }*/
//
    }







}