package sw2.lab5.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.lab5.entity.Post;
import sw2.lab5.repository.PostRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@RequestMapping("/post")
@Controller
public class PostController{

    @Autowired
    PostRepository postRepository;

    @GetMapping(value = {"list"})
    public String listarPost(Model model) {
        model.addAttribute("ListaPost",postRepository.findAll());
        return "post/list";
    }

    @GetMapping("/new")
    public String nuevoPost(Model model) {
        return "post/new";
    }


    @PostMapping("/save")
    public String guardarPost(Post post) {
        postRepository.save(post);
        return "redirect:/post";
    }

    @GetMapping("/edit")
    public String editarPost(Model model, @RequestParam("id") int id) {
        Optional<Post> optional = postRepository.findById(id);

        if (optional.isPresent()) {
            model.addAttribute("post", optional.get());
            return "post/edit";
        } else {
            return "redirect:/post";
        }

    }

    @GetMapping("/delete")
    public String borrarEmpleado(@RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Post> optional = postRepository.findById(id);

        if (optional.isPresent()) {
            postRepository.deleteById(id);
        }
        attr.addFlashAttribute("msg","Post borrado exitosamente");
        return "redirect:/employee";
    }



}
