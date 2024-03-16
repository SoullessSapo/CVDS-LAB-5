package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

@Controller
public class GreetingController {
    private int targetNumber;
    private int balance = 100000;
    private final Random random = new Random();

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    public GreetingController() {
        resetGame();
    }

    @GetMapping("/guess")
    public String guessForm(Model model) {
        model.addAttribute("balance", balance);
        return "guess";
    }

    @PostMapping("/guess")
    public String guess(@RequestParam(name="number", required=true) int number, Model model) {
        if (number == targetNumber) {
            model.addAttribute("result", "You guessed it!");
            resetGame();
        } else {
            balance -= 10000;
            model.addAttribute("result", "Try again!");
        }
        model.addAttribute("balance", balance);
        return "guess";
    }

    @PostMapping("/reset")
    public String reset(Model model) {
        resetGame();
        model.addAttribute("balance", balance);
        return "guess";
    }

    private void resetGame() {
        targetNumber = random.nextInt(10) + 1;
        balance = 100000;
    }


    }
