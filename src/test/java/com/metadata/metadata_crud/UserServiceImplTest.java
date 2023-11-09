package com.metadata.metadata_crud;

import com.metadata.metadata_crud.MetadataCrudApplication;
import com.metadata.metadata_crud.entity.sourcesys.sourcesystem;
import com.metadata.metadata_crud.entity.user.User;
import com.metadata.metadata_crud.repository.sourcesys.Sourcesystemrepo;
import com.metadata.metadata_crud.repository.user.UserRepository;
import com.metadata.metadata_crud.service.impl.sysource.Syssource;
import com.metadata.metadata_crud.service.impl.user.UserService;
import com.metadata.metadata_crud.service.impl.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MetadataCrudApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceImplTest {


    private final UserService userService;

    private final UserRepository userRepository;
    private final Syssource syssource;
    private  final Sourcesystemrepo sourcesystemrepo;


    @Autowired

    public UserServiceImplTest(UserService userService, UserRepository userRepository , Syssource syssource , Sourcesystemrepo sourcesystemrepo) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.syssource = syssource;
        this.sourcesystemrepo = sourcesystemrepo;
    }
    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        sourcesystemrepo.deleteAll();
    }

    @Test
    public void testCreateKeyWithNonNullUser() {
        // Create a sample user
        User user = new User();
        user.setKeyy("F");
        user.setValue("Female");
        user.setCategory("gender");
        // Call the createKey method
        User createdUser = userService.createKey(user);

        // Verify that the createdUser is not null and has the same values as the input user
        assertNotNull(createdUser);
        assertEquals(user.getKeyy(), createdUser.getKeyy());
        assertEquals(user.getValue(), createdUser.getValue());
        assertEquals(user.getCategory(), createdUser.getCategory());
    }

    @Test
    public void testGetUserByCategory() {
        // Create sample users with different categories
        User user1 = createUser("M", "gender","Male");
        User user2 = createUser("TN", "state","Tamil Nadu");
        User user3 = createUser("GJ", "state","Gujarat");

        // Save the users to the database
        userService.createKey(user1);
        userService.createKey(user2);
        userService.createKey(user3);

        // Retrieve users by category
        List<User> usersWithCategory1 = userService.getUserByCateg("gender");
        List<User> usersWithCategory2 = userService.getUserByCateg("state");

        // Verify that the correct number of users are retrieved
        assertEquals(1, usersWithCategory1.size());
        assertEquals(2, usersWithCategory2.size());

        // Verify that the retrieved users have the expected categories
        assertTrue(usersWithCategory1.stream().allMatch(user -> "gender".equals(user.getCategory())));
        assertTrue(usersWithCategory2.stream().allMatch(user -> "state".equals(user.getCategory())));
    }
    private User createUser(String keyy, String category,String value) {
        User user = new User();
        user.setKeyy(keyy);
        user.setCategory(category);
        user.setValue(value);
        return user;
    }
    @Test
    public void testGetAllUsers() {
        // Create sample users
        User user1 = createUser("M", "gender","Male");
        User user2 = createUser("TN", "state","Tamil Nadu");
        User user3 = createUser("GJ", "state","Gujarat");

        // Save the users to the database
        userService.createKey(user1);
        userService.createKey(user2);
        userService.createKey(user3);

        // Retrieve all users
        List<User> allUsers = userService.getAllUsers();

        // Verify that all users are retrieved
        assertEquals(3, allUsers.size());
    }
    @Test
    public void testUpdateId() {
        // Create a sample user
        User user = createUser("M", "gender","male");
        userService.createKey(user);

        // Update the user
        User updatedUser = new User();
        updatedUser.setKeyy("M");
        updatedUser.setValue("Male");
        updatedUser.setCategory("Gender");

        User result = userService.updateId("M", updatedUser);

        // Check if the user was updated
        assertEquals("M", result.getKeyy());
        assertEquals("Male", result.getValue());
        assertEquals("Gender", result.getCategory());
    }

    @Test
    public void testUpdateIdNonExistentUser() {
        // Try to update a user that doesn't exist
        User updatedUser = new User();
        updatedUser.setKeyy("Jk");
        updatedUser.setValue("Jammu  and Kashmir");
        updatedUser.setCategory("state");

        User result = userService.updateId("JK", updatedUser);

        // Check that the result is null since the user doesn't exist
        assertNull(result);
    }

    @Test
    public void testDeleteUser() {
        // Create a sample user and save it
        User user = new User();
        user.setKeyy("F");
        user.setValue("Female");
        user.setCategory("Gender");
        userRepository.save(user);

        // Delete the user using the deleteUser method
        userService.deleteUser("F");

        // Verify that the user is deleted
        Optional<User> deletedUser = userRepository.findByKeyy("F");
        assertFalse(deletedUser.isPresent());
    }
    @Test
    public void testGetAllCrossbyusername() {


        sourcesystem source1 = new sourcesystem();
        source1.setKeyy("F");
        source1.setValue("Female");
        source1.setCategory("gender");
        source1.setUsername("abc");
        sourcesystem source2 = new sourcesystem();
        source2.setKeyy("M");
        source2.setValue("Male");
        source2.setCategory("gender");
        source2.setUsername("bcd");

        sourcesystem source3 = new sourcesystem();
        source3.setKeyy("TN");
        source3.setValue("TamilNadu");
        source3.setCategory("state");
        source3.setUsername("cde");
        sourcesystemrepo.save(source1);
        sourcesystemrepo.save(source2);
        sourcesystemrepo.save(source3);

        List<sourcesystem> resultList = syssource.getAllCrossByUserAndCategory("abc", "gender");

        // Add your assertions here to validate the resultList
        assertEquals(1, resultList.size());
    }
    @Test
    public void testUpdateSource() {
        sourcesystem source1 = new sourcesystem();
        source1.setUsername("user1");
        source1.setCategory("category1");
        source1.setKeyy("key1");
        source1.setValue("value1");

        sourcesystem source2 = new sourcesystem();
        source2.setUsername("user2");
        source2.setCategory("category2");
        source2.setKeyy("key2");
        source2.setValue("value2");

        sourcesystemrepo.save(source1);
        sourcesystemrepo.save(source2);
        // Create an updated sourcesystem
        sourcesystem updatedSource = new sourcesystem();
        updatedSource.setKeyy("newKey");
        updatedSource.setValue("newValue");
        updatedSource.setCategory("newCategory");

        // Call the updateSource method
        sourcesystem updatedUser = syssource.updateSource("user1", "category1", updatedSource);

        // Verify that the updatedUser is not null and has the updated values
        assertNotNull(updatedUser);
        assertEquals("newKey", updatedUser.getKeyy());
        assertEquals("newValue", updatedUser.getValue());
        assertEquals("newCategory", updatedUser.getCategory());
    }

    @Test
    public void testUpdateSourceNonExistentUser() {
        sourcesystem source1 = new sourcesystem();
        source1.setUsername("user1");
        source1.setCategory("category1");
        source1.setKeyy("key1");
        source1.setValue("value1");

        sourcesystem source2 = new sourcesystem();
        source2.setUsername("user2");
        source2.setCategory("category2");
        source2.setKeyy("key2");
        source2.setValue("value2");

        sourcesystemrepo.save(source1);
        sourcesystemrepo.save(source2);
        // Try to update a user that doesn't exist
        sourcesystem updatedSource = new sourcesystem();
        updatedSource.setKeyy("newKey");
        updatedSource.setValue("newValue");
        updatedSource.setCategory("newCategory");

        sourcesystem result = syssource.updateSource("nonExistentUser", "category1", updatedSource);

        // Check that the result is null since the user doesn't exist
        assertNull(result);
    }
    @Test
    public void testGetAllCrossbyonlyusername() {

        sourcesystem source1 = new sourcesystem();
        source1.setUsername("user1");
        source1.setCategory("category1");

        sourcesystem source2 = new sourcesystem();
        source2.setUsername("user1");
        source2.setCategory("category2");

        sourcesystem source3 = new sourcesystem();
        source3.setUsername("user2");
        source3.setCategory("category1");

        sourcesystemrepo.save(source1);
        sourcesystemrepo.save(source2);
        sourcesystemrepo.save(source3);
        List<sourcesystem> resultList = syssource.getAllCrossbyusername("user1");

        // Add your assertions here to validate the resultList
        assertEquals(2, resultList.size()); // Example assertion
        // You can add more specific assertions based on your test data
    }
    @Test
    public void testCreateKeyyCross() {
        // Create a sourcesystem
        sourcesystem newUser = new sourcesystem();
        newUser.setUsername("user1");
        newUser.setCategory("category1");
        newUser.setKeyy("key1");
        newUser.setValue("value1");

        // Call the createKeyyCross method
        sourcesystem createdUser = syssource.createKeyyCross(newUser);

        // Verify that the createdUser is not null and has the same values as the input user
        assertNotNull(createdUser);
        assertEquals("user1", createdUser.getUsername());
        assertEquals("category1", createdUser.getCategory());
        assertEquals("key1", createdUser.getKeyy());
        assertEquals("value1", createdUser.getValue());
    }
    @Test
    public void testDeleteSource() {

        sourcesystem source1 = new sourcesystem();
        source1.setUsername("user1");
        source1.setCategory("category1");

        sourcesystem source2 = new sourcesystem();
        source2.setUsername("user2");
        source2.setCategory("category1");

        sourcesystemrepo.save(source1);
        sourcesystemrepo.save(source2);
        // Call the deleteSource method
        syssource.deleteSource("user1");

        // Verify that the user with "user1" is deleted
        assertNull(syssource.findByUsername("user1"));
    }
}
