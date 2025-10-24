package com.example.camping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reserve")
    public String reserveForm(Model model) {
        model.addAttribute("campgrounds", List.of(
                new Campground(1L, "지리산 캠핑장"),
                new Campground(2L, "설악산 캠핑장"),
                new Campground(3L, "한라산 캠핑장")
        ));
        return "redirect:/reservation/success";
    }

    @GetMapping("/success")
    public String reservationSuccess() {
        return "success";
    }

    public static class Campground {
        private Long id;
        private String name;
        public Campground(Long id, String name) { this.id = id; this.name = name; }
        public Long getId() { return id; }
        public String getName() { return name; }
    }
}
