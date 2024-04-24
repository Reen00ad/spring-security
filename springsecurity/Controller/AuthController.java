package com.example.springsecurity.Controller;


import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {
        authService.register(user);
        return ResponseEntity.status(200).body("user registered successfully");
    }

    @PutMapping("/update/{username}")
    public ResponseEntity update( @PathVariable String username, @RequestBody @Valid User user){
        authService.update(username, user);
        return ResponseEntity.status(200).body(" updated successfully");
    }
    @DeleteMapping("/delete/{userNameAdmin}/{userName}")
    public ResponseEntity delete(@PathVariable String userNameAdmin,@PathVariable String userNameCustomer){
        authService.delete(userNameAdmin,userNameCustomer);
        return ResponseEntity.status(200).body(" deleted successfully");
    }

    @GetMapping("/getall/{username}")
    public ResponseEntity getAllUser(@PathVariable String username){
        return ResponseEntity.status(200).body(authService.getAllUser(username));
    }
   // public ResponseEntity delete(@PathVariable String userNameAdmin)
}
