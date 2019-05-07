package com.oop.onlinemusicstore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.LinkedHashMap;

@Controller
public class UIcontroller {
    @GetMapping({"/"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "home page";
    }

    @GetMapping({"/main"})
    public String getmain(Model model, Authentication principal) {
        System.out.println(principal.getPrincipal());

//        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>)principal.getPrincipal();
//        System.out.println(properties.get("name"));
        model.addAttribute("user_name", principal.getPrincipal());
        return "main";
    }
}