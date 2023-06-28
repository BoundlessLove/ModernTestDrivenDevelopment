package com.blessing.tdd4.controllers;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blessing.tdd4.model.Credential;
import com.blessing.tdd4.model.Person;




@Controller
public class PersonController {
    
    @GetMapping("/people")
    String getPeople(Model model){
        model.addAttribute("something", "This is coming from the controller");
        model.addAttribute("people", Arrays.asList(
                new Person("John", 20),
                new Person("Sarah", 22),
                new Person("Simon", 23)
                ));
        return "people";
    }

    @GetMapping("/authenticated")
    String showPeople(Model model, @ModelAttribute("persons") Object persons, @ModelAttribute("creds") Credential credential){
     //   model.addAttribute("something", "This is coming from the controller");
     //   model.addAttribute("people", Arrays.asList(
     //           new Person("John", 20),
     //           new Person("Sarah", 22),
     //           new Person("Simon", 23)
     //           ));
        //Person p1 = new Person("Jack", 35);
        List<Person> PersonList = (List<Person>) persons;
        System.out.println(PersonList.size());
        System.out.println("username: "+ credential.getUsername());
        model.addAttribute("people",PersonList); 
        return "people";
    }
}
