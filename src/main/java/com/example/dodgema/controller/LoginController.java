package com.example.dodgema.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import com.example.dodgema.model.ConfirmationToken;
import com.example.dodgema.model.User;
import com.example.dodgema.repository.ConfirmationTokenRepository;
import com.example.dodgema.repository.UserRepository;
import com.example.dodgema.service.EmailSenderService;
import com.example.dodgema.service.SNSAccessTokenService;
import com.example.dodgema.service.SNSDataService;
import com.example.dodgema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class LoginController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private  SNSDataService SDService;

    @Autowired
    private SNSAccessTokenService SService;
    @Autowired
    private EmailSenderService emailSenderService;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(User user,HttpSession session,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User writer = UserService.findByEmail(auth.getName());
        modelAndView.setViewName("login");
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value="/kakao_signup", method = RequestMethod.GET)
    public ModelAndView kakao_signup(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("kakao_signup");
        return modelAndView;
    }

    @RequestMapping(value="/kakao_register", method = RequestMethod.GET)
    public ModelAndView kakao_register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();

        String nickName = SDService.getSNSNickname();
        String email = SDService.getSNSEmail();
        modelAndView.addObject("nickName", nickName);
        modelAndView.addObject("email", email);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("kakao_register");
        System.out.println("닉넴"+nickName);
        System.out.println("메일"+email);
        return modelAndView;
    }

    @PostMapping("/kakao_completed")
    public String kakao_completed(@ModelAttribute(value = "user") User user){

        user.setEmail(SDService.getSNSEmail());
        user.setNickName(SDService.getSNSNickname());

        userService.saveKakaoUser(user);

        return "index";
    }

    @PostMapping("/kakao_login")
    public String kakao_login(@ModelAttribute(value = "user") User user){

        System.out.println(SService.getToken()+"야양");
        
        
        return "redirect:/";
    }






    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = UserService.findByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {

            userService.saveUser(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);


            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("rafdivision@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            modelAndView.addObject("successMessage", "User has been registered successfully please check your mail and verify you account");
            modelAndView.addObject("email",user.getEmail());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }


    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
    // getters and setters

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = UserService.findByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }




    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ConfirmationTokenRepository getConfirmationTokenRepository() {
        return confirmationTokenRepository;
    }

    public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public EmailSenderService getEmailSenderService() {
        return emailSenderService;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

}