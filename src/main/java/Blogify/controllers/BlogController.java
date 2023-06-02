package Blogify.controllers;

import Blogify.entities.BlogPost;

import Blogify.entities.User;
import Blogify.services.BlogPostService;
import Blogify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/posts")
public class BlogController {
    @Autowired
    private BlogPostService blogPostService;
@Autowired
    private UserService userService;



    @GetMapping
    public String allPosts(Model model) {
        model.addAttribute("posts", blogPostService.findAll());
        return "post/posts";
    }
    @GetMapping("/new_post")
    public String newPost(BlogPost post){
        return "post/create_post";
    }

    @PostMapping("/new_post")
    public String addPost(BlogPost post, Model model, @AuthenticationPrincipal User user){

        System.out.println("User: "+user.getUsername());
blogPostService.addPost(post, user);
        return "redirect:/posts";
    }

    @GetMapping("/{post}")
    public String showPost(@PathVariable("post") BlogPost post, Model model){
        model.addAttribute("post", post);
        return "post/post";
    }

    @PostMapping("/{id}/delete")
    public String deletePostById(@PathVariable("id") Long id) {
        blogPostService.remove(blogPostService.findById(id));
        return "redirect:/posts";
    }

}
