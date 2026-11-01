/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.Valid
 *  org.springframework.http.ResponseEntity
 *  org.springframework.mail.javamail.JavaMailSender
 *  org.springframework.security.authentication.AuthenticationManager
 *  org.springframework.security.authentication.UsernamePasswordAuthenticationToken
 *  org.springframework.security.core.Authentication
 *  org.springframework.security.core.context.SecurityContextHolder
 *  org.springframework.security.crypto.password.PasswordEncoder
 *  org.springframework.stereotype.Controller
 *  org.springframework.ui.Model
 *  org.springframework.validation.BindingResult
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.ModelAttribute
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.ResponseBody
 *  org.springframework.web.servlet.mvc.support.RedirectAttributes
 */
package com.nec.controller;

import com.nec.dto.LoginRequest;
import com.nec.dto.RegisterRequest;
import com.nec.model.User;
import com.nec.service.UserService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value={"/auth"})
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender mailSender;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JavaMailSender mailSender) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.mailSender = mailSender;
    }

    @GetMapping(value={"/login"})
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", (Object)new LoginRequest());
        return "auth/login";
    }

    @PostMapping(value={"/login"})
    public String login(@Valid @ModelAttribute(value="loginRequest") LoginRequest loginRequest, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "auth/login";
        }
        try {
            Authentication authentication = this.authenticationManager.authenticate((Authentication)new UsernamePasswordAuthenticationToken((Object)loginRequest.getEmail(), (Object)loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/dashboard";
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", (Object)"Invalid email or password");
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value={"/register"})
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", (Object)new RegisterRequest());
        return "auth/register";
    }

    @PostMapping(value={"/register"})
    public String register(@Valid @ModelAttribute(value="registerRequest") RegisterRequest registerRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "auth/register";
        }
        if (this.userService.existsByEmail(registerRequest.getEmail())) {
            result.rejectValue("email", "error.email", "Email already exists");
            return "auth/register";
        }
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
            return "auth/register";
        }
        User user = User.builder().name(registerRequest.getName()).email(registerRequest.getEmail()).password(this.passwordEncoder.encode((CharSequence)registerRequest.getPassword())).role(User.Role.USER).enabled(true).build();
        this.userService.save(user);
        return "redirect:/auth/login?registered=true";
    }

    @PostMapping(value={"/logout"})
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/auth/login";
    }

    @PostMapping(value={"/api/login"})
    @ResponseBody
    public ResponseEntity<?> apiLogin(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = this.authenticationManager.authenticate((Authentication)new UsernamePasswordAuthenticationToken((Object)loginRequest.getEmail(), (Object)loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok().body(Map.of("message", "Login successful"));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
        }
    }
}

