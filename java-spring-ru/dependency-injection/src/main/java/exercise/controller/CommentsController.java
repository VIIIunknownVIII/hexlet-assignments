package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.Optional;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController()
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Comment> getComment(@PathVariable Long id) {
        var comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        return Optional.of(comment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createPost(@RequestBody Comment comment) {
        var newComment = commentRepository.save(comment);
        return newComment;
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment commentData) {
        var currentComment = commentRepository.findById(id).get();
        currentComment.setBody(commentData.getBody());
        var newComment = commentRepository.save(currentComment);
        return newComment;
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }
}
// END