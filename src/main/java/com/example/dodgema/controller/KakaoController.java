package com.example.dodgema.controller;


import com.example.dodgema.model.User;
import com.example.dodgema.service.JwtService;
import com.example.dodgema.service.SNSAccessTokenService;
import com.example.dodgema.service.SNSDataService;
import com.example.dodgema.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/kakao")
public class KakaoController {

    @Value("${wikin.key}")
    private String wikinKey;

    @Value("${url.add}")
    private String urladd;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    SNSDataService SDService;

    @Autowired
    SNSAccessTokenService SService;
    @Autowired
    UserService userService;

    static Logger logger = LoggerFactory.getLogger(KakaoController.class);

    HttpConnection conn = HttpConnection.getInstance();


    @GetMapping(value = "/login")
    public String kakao(HttpServletResponse res) throws IOException {

        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://kauth.kakao.com/oauth/authorize?client_id=");
        loginUrl.append("80f815c1c8e70bed6e21bce82a93aa5e"); //????????? ?????? ?????? REST KEY
        loginUrl.append("&redirect_uri=");
        loginUrl.append(urladd+"kakao/callback"); //????????? ?????? ????????? redirect URL
        loginUrl.append("&response_type=code");
        System.out.println("??????"+loginUrl.toString());
        return "redirect:"+loginUrl.toString();

    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String redirect(@RequestParam String code, HttpServletResponse res,HttpSession session) throws IOException, ParseException {
        //code
        //???????????? ?????? ????????? error ??????????????? ??????
        // ?????? ????????? ??????????????????
        Map<String, String> map = new HashMap<String, String>();
        map.put("grant_type", "=authorization_code");
        map.put("client_id", "=80f815c1c8e70bed6e21bce82a93aa5e"); //????????? ?????? ?????? REST KEY
        map.put("redirect_uri", "="+urladd+"kakao/callback"); //????????? ?????? ????????? redirect URL
        map.put("code", "="+code);

        System.out.println("????????????");
        String out = conn.HttpPostConnection("https://kauth.kakao.com/oauth/token", map).toString();
        ObjectMapper mapper = new ObjectMapper();
        String access_token = mapper.readTree(out).findValue("access_token").toString();
        System.out.println("access_token : " + access_token);
        map.clear();
        map.put("Authorization", "Bearer "+ access_token);
        out = conn.HttpPostConnection("https://kapi.kakao.com/v2/user/me", map).toString();
        String email = mapper.readTree(out).findValue("kakao_account").findValue("email").toString();
        String nickname = mapper.readTree(out).findValue("properties").findValue("nickname").toString();
        System.out.println("email : "+  email);
        System.out.println("nickname : "+  nickname);

        access_token = access_token.substring(1, access_token.length()-1);
        email = email.substring(1, email.length()-1);
        nickname = nickname.substring(1, nickname.length()-1);
        System.out.println(access_token);
        ///////////////???????????? ????????? ????????????
        //return "redirect:http://localhost:80";


        User user = userService.getUserByEmail(email);

        System.out.println("???????" + user);
        //if(user != null && (user.getLoginKind() & 2)> 1) { // ????????? ??????????????? ???????????? ?????????
        if(user != null) {
            String token = jwtService.create(user, 1, access_token);
            SService.makeSNSData(token, email);


            System.out.println(user+"????");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), wikinKey));
            SecurityContextHolder.getContext().setAuthentication(authentication);



            session.setAttribute("access_token", access_token);
            System.out.println("??????"+session.getAttribute("access_token"));
            System.out.println("?????????"+authentication);
            System.out.println("?????????????????? ???????????????");
            System.out.println(SService.getToken());
            return "redirect:/";
        }
        else { // ??????????????? ????????????
            SDService.makeSNSData(nickname, email, 2);
            return "redirect:"+urladd+"kakao_register";
        }

//
        /*        if(user != null) { // ?????? ?????? ??????????????? ????????????
            SDService.makeSNSData(nickname, email, 2);
            return "redirect:http://"+fronturl+"/user/snsCombine";
        }
         */
    }


    @GetMapping("/logout")
    public String access(HttpSession session) throws IOException {


        String access_token = (String)session.getAttribute("access_token");


        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://kauth.kakao.com");
        loginUrl.append("/oauth/logout?client_id="); //????????? ?????? ?????? REST KEY
        loginUrl.append("80f815c1c8e70bed6e21bce82a93aa5e");
        loginUrl.append("&logout_redirect_uri=");
        loginUrl.append(urladd+"logout"); //????????? ?????? ????????? redirect URL
        System.out.println("???????????? ???????????????"+loginUrl.toString());
        return "redirect:"+loginUrl.toString();




  /*      Map<String, String> map = new HashMap<String, String>();
        map.put("Authorization", "Bearer "+ access_token);

        String result = conn.HttpPostConnection("https://kapi.kakao.com/v1/user/logout", map).toString();


        System.out.println("????????????"+result);*/

      //  return "redirect:/logout";


    }








}