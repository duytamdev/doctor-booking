package com.funix.lab.prj321x_asm3_tamndfx27974.service.imp;


import com.funix.lab.prj321x_asm3_tamndfx27974.dto.GetInfoUserResponse;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.User;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.AppointmentScheduleRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.UserRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    final
    UserRepository userRepository;

    final AppointmentScheduleRepository appointmentScheduleRepository;
//    final AuthenticationService authenticationService;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                return userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
            }
        };
    }

    @Override
    public GetInfoUserResponse getInfoUser() {
        User user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            user = (User) authentication.getPrincipal();
        } else {
            throw new RuntimeException("User not found");
        }

        var appointmentSchedules = appointmentScheduleRepository.findByUserId(user.getId());
        var response = new GetInfoUserResponse();
        response.setUserInfo(user);
        response.setAppointmentSchedules(appointmentSchedules);
        return response;
    }
}
