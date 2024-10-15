package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.model.Comment;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private CommentDTO createCommentDTO(Comment commentData) {
        var commentDTO = new CommentDTO();
        commentDTO.setId(commentData.getId());
        commentDTO.setBody(commentData.getBody());
        return commentDTO;
    }

    private PostDTO createPostDTO(Post postData) {
        var postDto = new PostDTO();
        postDto.setId(postData.getId());
        postDto.setBody(postData.getBody());
        postDto.setTitle(postData.getTitle());
        var comments = commentRepository.findByPostId(postData.getId());
        var commentsDto = comments.stream().map(this::createCommentDTO).toList();
        postDto.setComments(commentsDto);
        return postDto;
    }

    @GetMapping
    public List<PostDTO> getPosts() {
        var posts = postRepository.findAll();
        var postsDTO = posts.stream().map(this::createPostDTO).toList();
        return postsDTO;
    }

    @GetMapping("/{id}")
    public PostDTO getPost(@PathVariable Long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return createPostDTO(post);
    }

}
// END