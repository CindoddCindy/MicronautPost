package cindy.test.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import cindy.test.model.Insert;

@Singleton
public class InsertRepository implements InsertRepositoryInterface {

    @PersistenceContext
    private EntityManager manager;

    public InsertRepository(@CurrentSession EntityManager manager){
        this.manager = manager;
    }

    @Override
    @Transactional(readOnly = true)
    public Long size() {
        Long count = manager.createQuery("select count(*) from Insert where deleted_at IS NULL", Long.class).getSingleResult();
        return count;
    }

    @Override
    @Transactional
    public List<Insert> findAll(int page, int limit) {
        TypedQuery<Insert> query = manager
                .createQuery("from Insert where deleted_at IS NULL", Insert.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0)
                .setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Insert findById(@NotNull Long id) {
        Insert query = manager.find(Insert.class, id);
        return query;
    }

    @Override
    @Transactional
    public boolean save(@NotNull Insert insert) {
        try {
            manager.persist(insert);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean update(@NotNull Long id, String name,String email, String password, String data) {
        try {

            Insert c = manager.find(Insert.class, id);
            if (name.equals("-")==false) c.setName(name);
            if (email.equals("-")==false) c.setEmail(email);
            if (password.equals("-")==false) c.setPassword(password);
            if (data.equals("-")==false) c.setData(data);
            
          //  if (grade != 0) c.setGrade(grade);
            c.setUpdated_At(new Date());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean destroy(@NotNull Long id) {
        try {
            Insert c = manager.find(Insert.class, id);
            c.setDeleted_At(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}