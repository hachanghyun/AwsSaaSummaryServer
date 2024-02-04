package com.hotong.awsSaaSummaryServer.notice.controller;

import com.hotong.awsSaaSummaryServer.notice.entity.Post;
import com.hotong.awsSaaSummaryServer.notice.repository.PostRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시판 API", description = "게시물 API")
@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Operation(summary = "게시물 조회", description = "게시물 조회")
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> posts = postRepository.findAll();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            if (postRepository == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            // 예외가 발생한 경우
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "게시물 등록", description = "게시물 등록")
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            Post savedPost = postRepository.save(post);
            return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
