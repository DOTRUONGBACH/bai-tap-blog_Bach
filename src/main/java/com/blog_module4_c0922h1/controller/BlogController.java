package com.blog_module4_c0922h1.controller;

import com.blog_module4_c0922h1.model.Blog;
import com.blog_module4_c0922h1.model.Category;
import com.blog_module4_c0922h1.service.BlogService;
import com.blog_module4_c0922h1.service.CategoryService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.getAll();}

    @GetMapping("/blog")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("showBlog");
        modelAndView.addObject("blogs", blogService.getAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView creatBlog() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("categories", categoryService.getAll());
        return modelAndView;
    }

    @PostMapping("create")
    public String creat(@ModelAttribute Blog blog, @RequestParam MultipartFile fileImg) {

        String nameFile = fileImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(fileImg.getBytes(), new File("C:\\Users\\dell\\IdeaProjects\\demo jpa_blog\\demo_blog_c0922h1\\src\\main\\webapp\\WEB-INF\\img\\" + nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        blog.setImg("/img/" + nameFile);
        blogService.save(blog);
        return "redirect:/blog";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Blog blog,@RequestParam MultipartFile fileImg){
        if (!fileImg.isEmpty()){
            try {
                FileCopyUtils.copy(fileImg.getBytes(),
                        new File("C:\\Users\\dell\\IdeaProjects\\demo jpa_blog\\demo_blog_c0922h1\\src\\main\\webapp\\WEB-INF" + blog.getImg()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        blogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        blogService.delete(id);
        return "redirect:/blog";
    }
    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable int id){

        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }
}
