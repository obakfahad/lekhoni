package com.ronniegnr.app.repository;

import com.ronniegnr.app.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    //Comment findById(int id);
}
