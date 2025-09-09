package hello.hello_spring.service;

import hello.hello_spring.domain.Memo;
import hello.hello_spring.repository.MemoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class MemoService {
    private MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // 작성
    @Transactional
    public Long saveMemo(Memo memo) {
        memoRepository.save(memo);
        return memo.getId();
    }

    // 전체 조회
    public List<Memo> findMemos(){
        return memoRepository.findAll();
    }

    // 제목으로 조회
    public Optional<Memo> findMemoByTitle(String title){
        return memoRepository.findByTitle(title);
    }

    // ID로 조회
    public Memo findMemoById(Long id){
        return memoRepository.findById(id);
    }

    // 삭제
    @Transactional
    public Long deleteMemo(Long id){
        memoRepository.deleteById(id);
        return id;
    }
}
