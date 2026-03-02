package com.smartclinic.webapp.controller;

import com.smartclinic.webapp.model.Appointment;
import com.smartclinic.webapp.model.User;
import com.smartclinic.webapp.repository.AppointmentRepository;
import com.smartclinic.webapp.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        userRepository.save(user);

        return "redirect:/";
    }

    @PostMapping("/book")
    public String bookAppointment(@RequestParam String patientName,
                                  @RequestParam String date){

        Appointment appointment = new Appointment(
                patientName,
                java.time.LocalDate.parse(date),
                "PENDING"
        );

        appointmentRepository.save(appointment);

        return "redirect:/appointments.html";
    }

    @GetMapping("/users")
    public String listUsers(org.springframework.ui.Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "users";
    }

    @GetMapping("/appointments")
    public String listAppointments(org.springframework.ui.Model model){

        model.addAttribute("appointments" , appointmentRepository.findAll());

        return "appointments";
    }
}