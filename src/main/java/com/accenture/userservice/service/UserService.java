package com.accenture.userservice.service;

import com.accenture.userservice.dto.EmailVerificationDto;
import com.accenture.userservice.exception.ServiceRuntimeException;
import com.accenture.userservice.model.*;
import com.accenture.userservice.repo.OrganisationRepository;
import com.accenture.userservice.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "UserServiceBean")
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final OrganisationRepository organisationRepository;
    private final EmailVerificationService emailVerificationService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user) throws Exception {

        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new ServiceRuntimeException(ErrorCodeEnum.EMAIL_EXISTS, user.getEmail());
        }
        user.setStatus(UserStatusEnum.INACTIVE);
        String domain = getDomain(user.getEmail());
        if (!organisationRepository.existsByDomain(domain)) {
            throw new ServiceRuntimeException(ErrorCodeEnum.EMAIL_INVALID, domain);
        }
        Organisation organisation = organisationRepository.findByDomain(domain).orElseThrow(() -> new ServiceRuntimeException(ErrorCodeEnum.ORGANISATION_NOT_FOUND, domain));
        user.setOrganisation(organisation);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());

        //saving email verification data
        userRepository.save(user);
        //send code email
        emailVerificationService.sendEmailVerificationCode(user);
        return user;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ServiceRuntimeException(ErrorCodeEnum.USER_NOT_FOUND, id));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ServiceRuntimeException(ErrorCodeEnum.USER_NOT_FOUND, id);
        }
        userRepository.deleteById(id);
    }

    public User updateUserDetails(User userRes, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceRuntimeException(ErrorCodeEnum.USER_NOT_FOUND, ErrorCodeEnum.USER_NOT_FOUND.getTemplate(), id.toString()));
        if (userRepository.existsUserByEmail(userRes.getEmail())) {
            throw new ServiceRuntimeException(ErrorCodeEnum.EMAIL_EXISTS, ErrorCodeEnum.EMAIL_EXISTS.getTemplate(), user.getEmail());
        }
        user.setName(userRes.getName());
        user.setEmail(user.getEmail());
        String domain = getDomain(userRes.getEmail());
        Organisation organisation = organisationRepository.findByDomain(domain).orElseThrow(() -> new ServiceRuntimeException(ErrorCodeEnum.ORGANISATION_NOT_FOUND, domain));
        user.setOrganisation(organisation);
        return userRepository.save(user);
    }

    public EmailVerificationDto emailVerificationToken(String email, int requestToken) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ServiceRuntimeException(ErrorCodeEnum.USER_NOT_FOUND, email));

        return emailVerificationService.checkEmailVerification(user.getId(), requestToken);
    }

    public String getDomain(String email) {
        return StringUtils.substringAfter(email, "@");
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new ServiceRuntimeException(ErrorCodeEnum.USER_NOT_FOUND, username));

        return new UserDetailsImpl(user);
    }

}
