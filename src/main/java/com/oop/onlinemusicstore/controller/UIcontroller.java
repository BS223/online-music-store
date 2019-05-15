package com.oop.onlinemusicstore.controller;

import com.oop.onlinemusicstore.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private SongRepository songRepository;

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
        model.addAttribute("songList",songRepository.findAll());
        System.out.println(songRepository.findAll());

        return "main";
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin";
    }

    @GetMapping("/insert")
    public String getInsertPage(){
        return "insert";
    }

}