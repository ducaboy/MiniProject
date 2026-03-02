package com.smartclinic.webapp.controller;

import com.smartclinic.webapp.model.Appointment;
import com.smartclinic.webapp.model.User;
import com.smartclinic.webapp.repository.AppointmentRepository;
import com.smartclinic.webapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.smartclinic.webapp.model.Role;

@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    public HomeController(UserRepository userRepository , AppointmentRepository appointmentRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {

        User user = new User(username, password);
        user.setRole(Role.USER);
        userRepository.save(user);


        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session){

        var optionalUser = userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username)
                        && u.getPassword().equals(password))
                .findFirst();

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();  // ✅ Extract real User

            session.setAttribute("loggedUser", user);
            session.setAttribute("userRole", user.getRole());

            return "redirect:/appointments";
        }

        return "redirect:/";
    }

    @PostMapping("/book")
    public String bookAppointment(@RequestParam String patientName,
                                  @RequestParam String date,
                                  jakarta.servlet.http.HttpSession session){


        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }

        Appointment appointment = new Appointment(
                patientName,
                java.time.LocalDate.parse(date),
                "PENDING"
        );

        appointmentRepository.save(appointment);

        return "redirect:/";
    }

    @GetMapping("/users")
    public String listUsers(org.springframework.ui.Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "users";
    }

    @GetMapping("/appointments")
    public String listAppointments(org.springframework.ui.Model model,
                                   jakarta.servlet.http.HttpSession session){


        Object loggedUser = session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/";
        }

        model.addAttribute("appointments" , appointmentRepository.findAll());

        return "appointments";
    }
}