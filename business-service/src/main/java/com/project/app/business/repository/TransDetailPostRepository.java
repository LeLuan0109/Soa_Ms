package com.project.app.business.repository;

import com.project.app.business.domain.TransDetailPost;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransDetailPostRepository extends JpaRepository<TransDetailPost, Integer> {
  Optional<TransDetailPost> getByTransDetailPostsId(Integer id);
}
