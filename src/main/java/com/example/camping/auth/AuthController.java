package com.example.camping.auth;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        return userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .map(u -> {
                    session.setAttribute("LOGIN_USER_ID", u.getId());
                    session.setAttribute("LOGIN_USERNAME", u.getUsername());
                    return "redirect:/specials";
                })
                .orElseGet(() -> {
                    model.addAttribute("err", "아이디 또는 비밀번호가 올바르지 않습니다.");
                    return "login";
                });
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("err", "이미 존재하는 아이디입니다.");
            return "register";
        }
        User newUser = new User(username, password);
        userRepository.save(newUser);
        model.addAttribute("msg", "회원가입 성공! 로그인 해주세요.");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
