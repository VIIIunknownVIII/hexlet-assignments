package exercise.controller.users;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private final List<Post> posts = Data.getPosts();
    @GetMapping("/users/{userId}/posts")
    public List<Post> index(@PathVariable String userId) {
        return posts.stream()
                .filter(p -> p.getUserId() == Integer.parseInt(userId))
                .toList();
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Post> create(@PathVariable String userId, @RequestBody Post post) {
        post.setUserId(Integer.parseInt(userId));
        posts.add(post);
        URI location = URI.create("/users/" + userId + "/posts");
        return ResponseEntity.created(location).body(post);
    }
}
// END