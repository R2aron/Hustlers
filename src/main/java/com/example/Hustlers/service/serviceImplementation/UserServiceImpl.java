package com.example.Hustlers.service.serviceImplementation;

import com.example.Hustlers.dto.AccountDto;
import com.example.Hustlers.dto.EmailDetails;
import com.example.Hustlers.dto.HustlerProfileDto;
import com.example.Hustlers.dto.UserDto;
import com.example.Hustlers.model.HustlerProfile;
import com.example.Hustlers.model.User;
import com.example.Hustlers.repository.HustlerRepository;
import com.example.Hustlers.repository.UserRepository;
import com.example.Hustlers.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;
    private final HustlerRepository hustlerRepository;
    private final EmailServiceImpl emailService;


    @Override
    public AccountDto getCurrentAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        switch (user.getRole()) {
            case USER:
                return new UserDto(user);
            case HUSTLER:
                HustlerProfile hustlerProfile = hustlerRepository.findByUser(user);
                return new HustlerProfileDto(hustlerProfile);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user role");
        }
    }

    @Override
// Nu cred ca am nevoie de userID pentru ca doar userul logat poate sa isi updateze contul, deci pot sa iau userul din context
    public UserDto updateUser(UserDto userDto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

//        if(user.getId() != userId) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update your own account");
//        }
        if (userDto.getFirstname() != null) user.setFirstname(userDto.getFirstname());
        if (userDto.getLastname() != null) user.setLastname(userDto.getLastname());
        if (userDto.getEmail() != null) user.setEmail(userDto.getEmail());

        userRepository.save(user);
        return new UserDto(user);

    }

    @Override
    public void deleteUser() {

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(SecurityContextHolder.getContext().getAuthentication().getName());
        emailDetails.setSubject("Account Deletion Confirmation");
        emailDetails.setMsgBody("Dear User,\n\nYour account has been successfully deleted. We're sorry to see you go. If you have any feedback or if there's anything we can do to improve our service, please don't hesitate to reach out.\n\nBest regards,\nThe Hustlers Team");
        emailService.sendSimpleMail(emailDetails);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userRepository.delete(user);
    }
}
