package Blogify.repositories;

import Blogify.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepo extends JpaRepository<BlogPost, Long> {
public List<BlogPost> findAll();
}
