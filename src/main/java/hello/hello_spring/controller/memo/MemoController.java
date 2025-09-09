package hello.hello_spring.controller.memo;


import org.springframework.ui.Model;
import hello.hello_spring.domain.Memo;
import hello.hello_spring.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        memoService.saveMemo(memo);
        return "redirect:/memos";
    }

    @DeleteMapping("/memos/{id}/delete")
    public String deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
        return "redirect:/memos";
    }

    // 상세
    @GetMapping("/memos/{id}")
    public String memoDetail(@PathVariable Long id, Model model) {
        Memo memo = memoService.findMemoById(id);
        if (memo == null) {
            return "redirect:/memos";
        }

        model.addAttribute("memo", memo);
        return "memos/detail";
    }

    // 업데이트
    @GetMapping("/memos/update/{id}")
    public String updateMemoForm(@PathVariable Long id, Model model) {
        Memo memo = memoService.findMemoById(id);

        if (memo == null) {
            return "redirect:/memos";
        }

        model.addAttribute("memo", memo);
        return "memos/update";
    }

    @PostMapping("memos/update/{id}")
    public String updateMemo(MemoForm memoForm, @PathVariable Long id) {
        Memo memo = memoService.findMemoById(id);
        if (memo == null) {
            return "redirect:/memos";
        }

        memo.setTitle(memoForm.getTitle());
        memo.setContent(memoForm.getContent());
        memoService.saveMemo(memo);
        return "redirect:/memos";
    }

}
