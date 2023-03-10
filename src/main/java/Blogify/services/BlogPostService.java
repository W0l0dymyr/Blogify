package Blogify.services;

import Blogify.entities.BlogPost;
import Blogify.entities.User;
import Blogify.repositories.BlogPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepo blogPostRepo;

    public List<BlogPost> findAll() {
        return blogPostRepo.findAll();
    }

    public void addPost(BlogPost post, User user) {
        post.setAuthor(user);
        post.setDate(LocalDate.now());
    blogPostRepo.save(post);
    }

    public void remove(BlogPost post) {
    blogPostRepo.delete(post);
    }

    public BlogPost findById(Long id) {
        return blogPostRepo.findById(id).get();
    }
}
