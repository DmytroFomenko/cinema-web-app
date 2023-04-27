package fdv.controller;


import fdv.entity.LoginData;
import fdv.entity.Role;
import fdv.repository.LoginDataRepository;
import fdv.repository.RoleRepository;
import fdv.service.LoginDataService;
import fdv.service.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@AllArgsConstructor
@SessionAttributes({"loggedUser"})
public class LoginDataController {

    LoginDataService loginDataService;
    RoleService roleService;

    //LoginDataRepository loginDataRepository;

    //RoleRepository roleRepository;

//    @ModelAttribute("loggedUser")
//    public LoginData loggedUser() {
//        return new LoginData();
//    }

////    @ModelAttribute("error")
////    public String error() {
////        return null;
////    }

    @GetMapping("/login_user_form")
    public String LoginUserForm(Model model, @ModelAttribute("error") String error) {
        //model.addAttribute("error",  model.getAttribute("error"));
        model.addAttribute("error",  error.isEmpty()?null:error);
        model.addAttribute("loginData", new LoginData());
        return "login_user_form";
    }


    @PostMapping("/login_user")
    public String LoginUser(@ModelAttribute("loginData") LoginData enteredLoginData, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        boolean logOk = loginDataService.checkLoginData(enteredLoginData);

        if (logOk) {
            session.setAttribute("loggedUser", loginDataService.findLoginDataByNickname(enteredLoginData.getNickname()));
            model.addAttribute("loggedUser", session.getAttribute("loggedUser"));
            //redirectAttributes.addFlashAttribute("loggedUser", loginDataRepository.findLoginDataByNickname(enteredLoginData.getNickname()));
        } else {
            redirectAttributes.addAttribute("error", "Неправильний нікнейм чи пароль");
            ////model.addAttribute("error", "Неправильний нікнейм чи пароль");
            return "redirect:/login_user_form";
        }
        return "redirect:/";
    }

    @GetMapping("/logout_user")
    public String LogoutUser(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        session.removeAttribute("loggedUser");
        model.addAttribute("loggedUser", null);
        //redirectAttributes.addFlashAttribute("loggedUser", new LoginData());
        return "redirect:/";
    }


    @GetMapping("/signup_client_form")
    public String SignUpClientForm(Model model, @ModelAttribute("error") String error) {
        model.addAttribute("error",  error.isEmpty()?null:error);
        model.addAttribute("clientToSignUpData", new LoginData());
        return "signup_client_form";
    }

    @PostMapping("/signup_client")
    public String SignUpClient(@ModelAttribute("clientToSignUpData") LoginData enteredClientSignUpData, HttpSession session, RedirectAttributes redirectAttributes) {

        // Check if the username already exists in the DB
        if (loginDataService.existsLoginDataByNickname(enteredClientSignUpData.getNickname())) {

            redirectAttributes.addAttribute("error", "Такий нікнейм зайнятий іншим користувачем.\nВведіть будь ласка інший");
            return "redirect:/signup_client_form";
        }

        Role role = roleService.findRoleByRole("client");
        enteredClientSignUpData.setRoleId(role.getId());

        loginDataService.saveLoginData(enteredClientSignUpData);
        //redirectAttributes.addFlashAttribute("loggedUser", loginDataRepository.findLoginDataByNickname(enteredClientSignUpData.getNickname()));
        session.setAttribute("loggedUser", loginDataService.findLoginDataByNickname(enteredClientSignUpData.getNickname()));

        return "redirect:/";
    }


    @GetMapping("/signup_admin_form")
    public String SignUpAdminForm(Model model, @ModelAttribute("error") String error) {
        model.addAttribute("error",  error.isEmpty()?null:error);
        model.addAttribute("adminToSignUpData", new LoginData());
        return "signup_admin_form";
    }

    @PostMapping("/signup_admin")
    public String SignUpAdmin(@ModelAttribute("adminToSignUpData") LoginData enteredAdminSignUpData, @ModelAttribute("adminKey") String enteredAdminKey, HttpSession session, RedirectAttributes redirectAttributes) {

        // TODO: 21.04.2023 It is test admin key!!!
        String testValidAdminKey = "1";

        // Check if entered admin key != the valid admin key
        if (!enteredAdminKey.equals(testValidAdminKey)) {
            redirectAttributes.addAttribute("error", "Неправильний ключ адміністратора!!!");
            return "redirect:/signup_admin_form";
        }

        // Check if the username already exists in the DB
        if (loginDataService.existsLoginDataByNickname(enteredAdminSignUpData.getNickname())) {

            redirectAttributes.addAttribute("error", "Такий нікнейм зайнятий іншим користувачем. Введіть будь ласка інший");
            return "redirect:/signup_admin_form";
        }

        Role role = roleService.findRoleByRole("admin");
        enteredAdminSignUpData.setRoleId(role.getId());

        loginDataService.saveLoginData(enteredAdminSignUpData);
        //redirectAttributes.addFlashAttribute("loggedUser", loginDataRepository.findLoginDataByNickname(enteredClientSignUpData.getNickname()));
        session.setAttribute("loggedUser", loginDataService.findLoginDataByNickname(enteredAdminSignUpData.getNickname()));

        return "redirect:/";
    }




}
