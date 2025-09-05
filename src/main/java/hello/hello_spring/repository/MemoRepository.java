package hello.hello_spring.repository;

import hello.hello_spring.domain.Memo;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class MemoRepository {

    private final EntityManager em;

    public MemoRepository(EntityManager em) {
        this.em = em;
    }

    public Memo save(Memo memo) {
        em.persist(memo);
        return memo;
    }

    public Optional<Memo> findById(long id) {
        Memo memo = em.find(Memo.class, id);
        return Optional.ofNullable(memo);
    }

    public Optional<Memo> findByTitle(String title) {
        List<Memo> result = em.createQuery("select m from Memo m  where m.title = :title", Memo.class)
                .setParameter("title", title)
                .getResultList();
        return result.stream().findAny();
    }

    public List<Memo> findAll() {
        return em.createQuery("select m from Memo m", Memo.class).getResultList();
    }

    public void deleteById(long id) {
        Memo memo = em.find(Memo.class, id);
        em.remove(memo);
    }
}
