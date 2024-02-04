package com.hotong.awsSaaSummaryServer.SaaSum.repository;

import com.hotong.awsSaaSummaryServer.SaaSum.entity.ChapterDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterDetailRepository extends JpaRepository<ChapterDetail, Long> {
    List<ChapterDetail> findAllByChapterId(Long chapterId);
    // 추가로 필요한 쿼리 메소드를 여기에 정의할 수 있습니다.
}

