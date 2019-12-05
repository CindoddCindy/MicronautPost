package cindy.test.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cindy.test.model.Post;

public interface PostRepositoryInterface {

    Long size();
    List<Post> findAll (int page, int limit);
    Post findById (@NotNull Long id);
    boolean save(@NotNull Post post);
    boolean update(@NotNull Long id, @NotBlank String name,@NotBlank String email, @NotBlank String password, @NotBlank String data); // @NotNull int grade);
    boolean destroy(@NotNull Long id);
}