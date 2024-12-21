package me.stanleyazi.hellospringworld;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingsController {
  @GetMapping("/greeting")
  public String greeting(@RequestParam(value = "name", defaultValue = "Spring World") String  name, Model model) {
   model.addAttribute("name", name);
    return "greeting";
  }
}
