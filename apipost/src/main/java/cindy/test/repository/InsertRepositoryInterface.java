package cindy.test.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cindy.test.model.Insert;

public interface InsertRepositoryInterface {

    Long size();
    List<Insert> findAll (int page, int limit);
    Insert findById (@NotNull Long id);
    boolean save(@NotNull Insert insert);
    boolean update(@NotNull Long id, @NotBlank String name,@NotBlank String email, @NotBlank String password, @NotBlank String data); // @NotNull int grade);
    boolean destroy(@NotNull Long id);
}