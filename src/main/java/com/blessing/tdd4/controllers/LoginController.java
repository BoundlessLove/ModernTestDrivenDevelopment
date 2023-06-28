package com.blessing.tdd4.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blessing.tdd4.model.Credential;
import com.blessing.tdd4.model.Person;
import com.blessing.tdd4.LogicLayer.Login;
import java.util.Arrays;


@Controller
public class LoginController {
    
    @GetMapping
    String login(Model model){
        Credential crendential = new Credential();
        model.addAttribute("credential", crendential);
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticateCredentials(@ModelAttribute("credential") Credential credential, 
                                                    RedirectAttributes redirectAttributes){
        System.out.println(credential);
        boolean isValidUser = Login.authenticateCredentials(credential.getUsername(), credential.getPassword());
        if(isValidUser){
            //Person p1 = new Person("John", 20)    
            //Person[] persons = new Person[2];
            //persons[0] = new Person("John", 25);
            //persons[1] = new Person("Jack", 35);
            List<Person> persons = Arrays.asList(new Person("John", 25), new Person("Jack", 35));
            //Person p1 = new Person("Jack", 35);
            //redirectAttributes.addFlashAttribute("persons", persons);
            redirectAttributes.addFlashAttribute("persons", persons);
            redirectAttributes.addFlashAttribute("creds", credential);
            return "redirect:/authenticated";
            //return "redirect:/people";
        }else{
            System.out.println("invalid user");
            return "people";    
        }
    }
}
