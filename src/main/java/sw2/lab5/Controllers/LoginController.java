package sw2.lab5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import sw2.lab5.entity.User;
import sw2.lab5.repository.UserRespository;

import javax.servlet.http.HttpSession;

public class LoginController {

    @Autowired
    UserRespository userRespository;


    @GetMapping("/loginForm")
    public String loginForm(){
        return "login/form";
    }

    @GetMapping(value="/redirectByRole")
    public String redirectByRole(Authentication auth, HttpSession session) {
        String rol = "";
        for (GrantedAuthority role : auth.getAuthorities()) {
            rol = role.getAuthority();
            break;
        }
        String username = auth.getName();
        User usuario = userRespository.findByEmail(username);

        session.setAttribute("usuario",usuario);


            return "redirect:/posts/";
    }


}
