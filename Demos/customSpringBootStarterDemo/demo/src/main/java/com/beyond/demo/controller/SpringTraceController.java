package com.beyond.demo.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("articleId")
public class SpringTraceController implements EnvironmentAware {

    private Environment environment;

    private String[] sensitiveStrs = new String[] {"a","b"};

    @ModelAttribute("comment")
    public String replaceSensitiveStr(String comment){
        if (comment == null){
            return null;
        }
        for (String sensitiveStr : sensitiveStrs) {
            comment = comment.replace(sensitiveStr,"");
        }
        return comment;
    }

    @RequestMapping("/article/{articleId}/comment")
    public String comment(@PathVariable String articleId,RedirectAttributes redirectAttributes, Model model){
        redirectAttributes.addFlashAttribute("comment", model.asMap().get("comment"));
        redirectAttributes.addFlashAttribute("articleId",articleId);
        model.addAttribute("articleId",articleId);
        return "redirect:/showArticle";
    }

    @RequestMapping("/showArticle")
    public String showArticle(Model model){
        Object articleId = model.asMap().get("articleId");
        model.addAttribute("articleTitle","articleTitle"+articleId);
        model.addAttribute("article","articleContent"+articleId);
        return "demo";
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
