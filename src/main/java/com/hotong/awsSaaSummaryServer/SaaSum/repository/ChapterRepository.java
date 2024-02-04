package com.hotong.awsSaaSummaryServer.SaaSum.repository;

import com.hotong.awsSaaSummaryServer.SaaSum.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 레포지토리
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    // 기본적인 CRUD 메소드는 JpaRepository에 이미 정의되어 있음
}