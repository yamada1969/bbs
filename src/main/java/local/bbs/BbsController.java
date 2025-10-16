package local.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class BbsController {
    private final BbsDao dao;

    @Autowired
    public BbsController(BbsDao dao){
        this.dao = dao;
    }

    @RequestMapping(value="/")
    public String show(Model model){
        model.addAttribute("article", dao.getAll());
        return "bbs";
    }

    @RequestMapping(value="/posting")
    public String posting(
        @RequestParam("name") String name,
        @RequestParam("article") String article
    ){
        String id = UUID.randomUUID().toString().substring(0,8);
        String posttime = LocalDateTime.now().toString();
        if(name.equals("")){
            name = "名無し";
        }
        if(article.equals("")){
            article = "記事なし";
        }

        BbsItem item = new BbsItem(id, posttime, name, article);
        dao.insert(item);
        return "redirect:/";
    }
}
