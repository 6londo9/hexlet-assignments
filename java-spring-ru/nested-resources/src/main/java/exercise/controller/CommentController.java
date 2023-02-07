package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getAllCommentsForPost(@PathVariable long postId) {
        return commentRepository.findByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getCommentByPostIdAndCommentId(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
    ) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId);
        if (comment == null) {
            throw new ResourceNotFoundException("The comment with such id for given post is not found");
        }
        return comment;
    }

    @PostMapping(path = "/{postId}/comments")
    public Comment postCommentForGivenPost(
            @PathVariable("postId") long postId,
            @RequestBody Comment comment
    ) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("No post with such id found"));
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment patchCommentByPostIdAndCommentId(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId,
            @RequestBody Comment comment
    ) {
        Comment updatedComment = commentRepository.findByIdAndPostId(commentId, postId);
        if (updatedComment == null) {
            throw new ResourceNotFoundException("The comment with such id is not found");
        }
        updatedComment.setContent(comment.getContent());
        return commentRepository.save(updatedComment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteCommentByPostIdAndCommentId(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
    ) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId);
        if (comment == null) {
            throw new ResourceNotFoundException("The comment with such id is not found");
        }
        commentRepository.deleteById(comment.getId());
    }
    // END
}
