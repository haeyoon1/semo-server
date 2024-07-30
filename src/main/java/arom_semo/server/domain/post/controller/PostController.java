package arom_semo.server.domain.post.controller;

import arom_semo.server.domain.post.dto.PostCreateRequestDto;
import arom_semo.server.domain.post.dto.PostModifyRequestDto;
import arom_semo.server.domain.post.dto.PostResponseDto;
import arom_semo.server.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @PostMapping("posts")
    public ResponseEntity<String> createPost(@RequestParam String userName,
                                                      @RequestParam String groupName,
                                                      @RequestBody PostCreateRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.createPost(userName, groupName, dto));
    }

    @GetMapping("posts/{postId}")
    public ResponseEntity<PostResponseDto> fetchPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @GetMapping("posts")
    public ResponseEntity<List<PostResponseDto>> fetchAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PatchMapping("posts/{postId}")
    public ResponseEntity<Void> modifyPost(@RequestParam String userName,
                                           @RequestBody PostModifyRequestDto dto) {
        postService.modifyPost(userName, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("posts/{postId}")
    public ResponseEntity<Void> deletePost(@RequestParam String userName,
                                           @PathVariable Long postId) {
        postService.deletePost(userName, postId);
        return ResponseEntity.ok().build();
    }
}