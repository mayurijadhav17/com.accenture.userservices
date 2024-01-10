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

@Service
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
            throw new ServiceRuntimeException("user with email already exists--" + user.getEmail(), ErrorCodeEnum.EMAIL_EXISTS);
        }
        user.setStatus(UserStatusEnum.INACTIVE);
        String domain = getDomain(user.getEmail());
        if (!organisationRepository.existsByDomain(domain)) {
            throw new ServiceRuntimeException("User email is invalid" + user.getEmail(), ErrorCodeEnum.EMAIL_INVALID);
        }
        Organisation organisation = organisationRepository.findByDomain(domain)
                .orElseThrow(() -> new ServiceRuntimeException("Organisation not found for domain --" + domain, ErrorCodeEnum.ORGANISATION_NOT_FOUND));
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
        return userRepository.findById(id).
                orElseThrow(() -> new ServiceRuntimeException("User not found for id--" + id, ErrorCodeEnum.USER_NOT_FOUND));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ServiceRuntimeException("User not found for id--" + id, ErrorCodeEnum.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    public User updateUserDetails(User userRes, Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ServiceRuntimeException("User not found for id--" + id, ErrorCodeEnum.USER_NOT_FOUND));
        if (userRepository.existsUserByEmail(userRes.getEmail())) {
            throw new ServiceRuntimeException("User with email already exists--" + userRes.getEmail(), ErrorCodeEnum.EMAIL_EXISTS);
        }
        user.setName(userRes.getName());
        user.setEmail(user.getEmail());
        String domain = getDomain(userRes.getEmail());
        if (!organisationRepository.existsByDomain(domain)) {
            throw new Exception("EMAIL_INVALID" + domain);
        }
        Organisation organisation = organisationRepository.findByDomain(domain)
                .orElseThrow(() -> new ServiceRuntimeException("organisation not found for domain--" + domain, ErrorCodeEnum.ORGANISATION_NOT_FOUND));
        user.setOrganisation(organisation);
        return userRepository.save(user);
    }

    public EmailVerificationDto emailVerificationToken(String email, int requestToken) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ServiceRuntimeException("User not found for email--" + email, ErrorCodeEnum.USER_NOT_FOUND));

        return emailVerificationService.checkEmailVerification(user.getId(), requestToken);
    }

    private String getDomain(String email) {
        return StringUtils.substringAfter(email, "@");
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow();

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UserDetailsImpl(user);
    }

}
