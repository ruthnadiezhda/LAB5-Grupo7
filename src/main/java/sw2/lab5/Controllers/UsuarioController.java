package sw2.lab5.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sw2.lab5.entity.User;
import sw2.lab5.repository.UserRespository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    UserRespository userRespository;

    @GetMapping(value = {"list",""})
    public String listarUsuarios(Model model, HttpSession session){

        Integer idrol = session.usuario.getId_role();

        if(idrol==1){
            List<User> listaUs = userRespository.findAll();

            model.addAttribute("listaDeUsuarios",listaUs);
        }
        if(idrol==1){
            Integer iduser =session.usuario.getId_user();

            List<User> listaUs = userRespository.findAllById(iduser);


            model.addAttribute("listaDeUsuarios",listaUs);
        }



        return "usuario/list";
    }

    @GetMapping("/edit")
    public String editarRegionForm(@RequestParam("id") int id,
                                   Model model){
        Optional<User> opt = userRespository.findById(id);

        if (opt.isPresent()){
            User user = opt.get();
            model.addAttribute("user",user);
            return "usuario/editForm";
        }else{
            return "redirect:/user/list";
        }

    }

    @PostMapping("/guardar")
    public String guardarShipper(User user){

        userRespository.save(user);

        return "redirect:/user/list";

    }
    @GetMapping("/delete")
    public String borrarShipperForm(@RequestParam("id") int id,
                                    Model model){
        Optional<User> opt = userRespository.findById(id);
        if (opt.isPresent()){
            userRespository.deleteById(id);
        }
        return "redirect:/user/list";
    }


}
