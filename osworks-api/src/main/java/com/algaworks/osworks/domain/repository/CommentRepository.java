package com.algaworks.osworks.domain.repository;

import com.algaworks.osworks.domain.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}