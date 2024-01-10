//package com.accenture.userService.service;
//
//import com.accenture.userservice.model.Organisation;
//import com.accenture.userservice.model.User;
//import com.accenture.userservice.model.UserRoleEnum;
//import com.accenture.userservice.repo.OrganisationRepository;
//import com.accenture.userservice.repo.UserRepository;
//import com.accenture.userservice.service.EmailVerificationService;
//import com.accenture.userservice.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//  private UserService service;
//
//  @Mock
//  private UserRepository userRepository;
//  @Mock
//  private RoleRepository roleRepository;
//  @Mock
//  private OrganisationRepository organisationRepository;
//  @Mock
//  private EmailVerificationService emailVerificationService;
//  @Mock
//  private PasswordEncoder passwordEncoder;
//  private User dummyUser;
//  private Optional<Organisation> dummyOrganisation;
//  private Optional<Role> role;
//
//  @BeforeEach
//  void setUp() {
//    service = new UserService(userRepository, roleRepository, organisationRepository, emailVerificationService, passwordEncoder);
//    dummyUser = User.builder().id(1L).name("dummyuser").email("dummy@dummy.com").password("password").build();
//    dummyOrganisation = Optional.of(Organisation.builder().name("dummyOrganisation").domain("dummy.com").build());
//    role = Optional.of(Role.builder()
//            .name(UserRoleEnum.ROLE_ADMIN)
//            .build());
//  }
//
//  @Test
//  void testAddUser() throws Exception {
//    // Setup
//    //Optional<User> testUser=Optional.of(User.builder().build());
//    User expectedResult = User.builder().build();
//    //test
//    when(userRepository.existsUserByEmail(anyString())).thenReturn(false);
//    when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(dummyUser));
//    when(organisationRepository.existsByDomain(anyString())).thenReturn(true);
//    when(organisationRepository.findByDomain(anyString())).thenReturn(dummyOrganisation);
//
//    User result = service.createUser(dummyUser);
//    assertEquals(expectedResult, result);
//  }
//
//  @Test
//  void testGetUserById() throws Exception {
//    // Setup
//    User expectedResult = User.builder().build();
//    //test
//    when(service.getUserById(1L)).thenReturn(expectedResult);
//    User result = service.getUserById(1L);
//    assertEquals(expectedResult, result);
//
//  }
//
//  @Test
//  void testGetOrganisationList() throws Exception {
//    // Setup
//    User user1 = User.builder().build();
//    User user2 = User.builder().build();
//    List<User> list = new ArrayList<>();
//    list.add(user1);
//    list.add(user2);
//    when(service.getUsers()).thenReturn(list);
//    List<User> result = service.getUsers();
//    assertEquals(list, result);
//
//  }
//
//  @Test
//  void testUpdateUser() throws Exception {
//    // Setup
//    User expectedResult = User.builder().build();
//    User user = User.builder().build();
//    //test
//    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//    User result = service.updateUserDetails(user, user.getId());
//    assertEquals(expectedResult, result);
//  }
//}
