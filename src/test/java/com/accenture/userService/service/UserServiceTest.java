package com.accenture.userService.service;

import com.accenture.userservice.model.Organisation;
import com.accenture.userservice.model.User;
import com.accenture.userservice.repo.OrganisationRepository;
import com.accenture.userservice.repo.UserRepository;
import com.accenture.userservice.service.EmailVerificationService;
import com.accenture.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


  private UserService service;

  @Mock
  private UserRepository userRepository;

  @Mock
  private OrganisationRepository organisationRepository;
  @Mock
  private EmailVerificationService emailVerificationService;
  @Mock
  private PasswordEncoder passwordEncoder;

  private Optional<Organisation> dummyOrganisation;


  @BeforeEach
  void setUp() {
    service = new UserService(userRepository, organisationRepository, emailVerificationService, passwordEncoder);
    dummyOrganisation = Optional.of(Organisation.builder().name("dummyOrganisation").domain("dummy.com").build());

  }

@Test
  void testAddUser() throws Exception {
//    // Setup
//
//    User expectedResult = User.builder().build();
//    //test
//   User   NewUser = User.builder().build();//.id(1L).name("dummyuser").email("dummy@dummy.com").password("password").build();
//String domain =service.getDomain(NewUser.getEmail());
//  when(userRepository.existsUserByEmail(NewUser.getEmail())).thenReturn(false);
//
// when(service.getDomain(NewUser.getEmail())).thenReturn(anyString());
//  when(organisationRepository.findByDomain(anyString())).thenReturn(Optional.of(Organisation.builder().build()));
// //  when(organisationRepository.existsByDomain(")).thenReturn(true);
//
//
//    User result = service.createUser(NewUser);
//    assertEquals(expectedResult, result);
  }

  @Test
  void testGetUserById() throws Exception {
    // Setup
    User expectedResult = User.builder().build();
    //test
    when(userRepository.findById(1L)).thenReturn(Optional.of(expectedResult));
    User result = service.getUserById(1L);
    assertEquals(expectedResult, result);

  }



//  @Test
//  void testUpdateUser() throws Exception {
//    // Setup
//    User expectedResult = User.builder().build();
//    User user = User.builder().build();
//    final Optional<Organisation> organisation = Optional.of(Organisation.builder()
//            .name("name")
//            .domain("domain")
//            .build());
//    when(organisationRepository.findById(0L)).thenReturn(organisation);
//    when(organisationRepository.existsByDomain("domain")).thenReturn(false);
//    when(organisationRepository.findByDomain("domain")).thenReturn(organisation);
//    //test
//    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//    User result = service.updateUserDetails(expectedResult, 1L);
//    assertEquals(expectedResult, result);
//  }
}
