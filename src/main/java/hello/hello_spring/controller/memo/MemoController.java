package hello.hello_spring.controller.memo;


import org.springframework.ui.Model;
import hello.hello_spring.domain.Memo;
import hello.hello_spring.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemoController {

    private MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/memos")
    public String memoList(Model model) {
        List<Memo> memos = memoService.findMemos();
        model.addAttribute("memos", memos);
        return "memos/index";
    }

    @GetMapping("/memos/create")
    public String createMemoForm() {
        return "memos/create";
    }

    @PostMapping("/memos/create")
    public String createMemo(MemoForm memoForm) {
        Memo memo = new Memo();
        memo.setTitle(memoForm.getTitle());
        memo.setContent(memoForm.getContent());
        memoService.writeMemo(memo);
        return "redirect:/memos";
    }

    @DeleteMapping("/memos/{id}/delete")
    public String deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
        return "redirect:/memos";
    }
}
