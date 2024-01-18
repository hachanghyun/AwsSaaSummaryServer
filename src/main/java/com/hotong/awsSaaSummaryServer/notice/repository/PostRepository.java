package com.hotong.awsSaaSummaryServer.notice.repository;

import com.hotong.awsSaaSummaryServer.notice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
