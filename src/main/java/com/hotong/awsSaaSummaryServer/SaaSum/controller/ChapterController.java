package com.hotong.awsSaaSummaryServer.SaaSum.controller;

import com.hotong.awsSaaSummaryServer.Quiz.dto.UserQuizResult;
import com.hotong.awsSaaSummaryServer.SaaSum.entity.Chapter;
import com.hotong.awsSaaSummaryServer.SaaSum.entity.ChapterDetail;
import com.hotong.awsSaaSummaryServer.SaaSum.repository.ChapterDetailRepository;
import com.hotong.awsSaaSummaryServer.SaaSum.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/chapters")
public class ChapterController {
    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ChapterDetailRepository chapterDetailRepository;

    // 모든 목차를 불러오는 API 엔드포인트
    @GetMapping
    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    @GetMapping("/{chapterId}/details")
    public ResponseEntity<List<ChapterDetail>> getChapterDetails(@PathVariable("chapterId") Long chapterId) {
        List<ChapterDetail> chapterDetails = chapterDetailRepository.findAllByChapterId(chapterId);
        return ResponseEntity.ok(chapterDetails);
    }

    // 특정 챕터 세부 내용의 상세 정보를 불러오는 API 엔드포인트
    @GetMapping("/details/{detailId}")
    public ResponseEntity<ChapterDetail> getChapterDetailById(@PathVariable("detailId") Long detailId) {
        return chapterDetailRepository.findById(detailId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

