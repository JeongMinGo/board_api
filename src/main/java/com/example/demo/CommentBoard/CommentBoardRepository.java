package com.example.demo.CommentBoard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentBoardRepository extends JpaRepository<CommentBoard,Long> {
}
