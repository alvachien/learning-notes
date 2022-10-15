package com.alvachien.springtutorial.thymeleafjpademo.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alvachien.springtutorial.thymeleafjpademo.exception.ResourceNotFoundException;
import com.alvachien.springtutorial.thymeleafjpademo.model.PersonRole;
import com.alvachien.springtutorial.thymeleafjpademo.service.PersonRoleService;

@Controller
public class PersonRoleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    private final int ROW_PER_PAGE = 5;
 
    @Autowired
    private PersonRoleService personRoleService;
 
    @Value("${msg.title}")
    private String title;
 
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) { 
        model.addAttribute("title", title);
        return "index";
    }
 
    @GetMapping(value = "/personroles")
    public String getPersonRoles(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) 
    { 
        List<PersonRole> roles = personRoleService.findAll(pageNumber, ROW_PER_PAGE);
 
        long count = personRoleService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;

        model.addAttribute("roles", roles);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "person-role-list";    
    }
 
    @GetMapping(value = "/personroles/{roleId}")
    public String getPersonRoleById(Model model, @PathVariable long roleId) 
    { 
        PersonRole role = null;
        try {
            role = personRoleService.findById(roleId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Person role not found");
        }
        model.addAttribute("role", role);

        return "person-role-display";
    }
 
    @GetMapping(value = {"/personroles/add"})
    public String showAddPersonRole(Model model) 
    { 
        PersonRole role = new PersonRole();
        model.addAttribute("add", true);
        model.addAttribute("role", role);
     
        return "person-role-edit";
    }
 
    @PostMapping(value = "/personroles/add")
    public String addPersonRole(Model model, @ModelAttribute("role") PersonRole personRole) 
    { 
        try {
            PersonRole newRole = personRoleService.save(personRole);
            return "redirect:/personroles/" + String.valueOf(newRole.getId());
        } catch (Exception ex) {
            // log exception first, 
            // then show error
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
            model.addAttribute("add", true);
            return "person-role-edit";
        }
    }
 
    @GetMapping(value = {"/personroles/{roleId}/edit"})
    public String showEditPersonRole(Model model, @PathVariable long roleId)
    { 
        PersonRole role = null;
        try {
            role = personRoleService.findById(roleId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Person role not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("role", role);
        return "person-role-edit";
    }
    
    @PostMapping(value = {"/personroles/{roleId}/edit"})
    public String updatePersonRole(Model model,
            @PathVariable long roleId,
            @ModelAttribute("personRole") PersonRole personRole) 
    { 
        try {
            personRole.setId(roleId);
            personRoleService.update(personRole);
            return "redirect:/personroles/" + String.valueOf(personRole.getId());
        } catch (Exception ex) {
            // log exception first, 
            // then show error
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
             model.addAttribute("add", false);
            return "person-role-edit";
        }
    }
         
    @GetMapping(value = {"/personroles/{roleId}/delete"})
    public String showDeletePersonRoleById(Model model, @PathVariable long roleId) 
    { 
        //... 
        return "";
    }
    
    @PostMapping(value = {"/personroles/{roleId}/delete"})
    public String deletePersonRoleById(Model model, @PathVariable long roleId) 
    { 
        //... 
        return "";
    }    
}
