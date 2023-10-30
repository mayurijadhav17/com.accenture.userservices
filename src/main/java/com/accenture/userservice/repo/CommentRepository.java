package com.accenture.userservice.repo;

import com.accenture.userservice.model.Comment;
import com.accenture.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
