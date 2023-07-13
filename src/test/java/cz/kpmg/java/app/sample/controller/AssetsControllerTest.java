package cz.kpmg.java.app.sample.controller;

import cz.kpmg.java.app.sample.model.Asset;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AssetsControllerTest {

    @Autowired
    private AssetsController assetsController;

    @Test
    @Order(1)
    void testUserListEmpty() {
        ResponseEntity<List<Asset>> response = assetsController.getAssetsList();
        Assert.notNull(response, "Response is null");
        List<Asset> userList = response.getBody();
        Assert.notNull(userList, "User list is null");
//        Assert.isTrue(!userList.isEmpty(), "User list is empty.");
//        Assert.isTrue(Objects.equals(userList.size(), 2), "User list does not contains two users.");
    }

//    @Test
//    @Order(2)
//    void testCreateUser() {
//        User newUser = new User();
//        newUser.setName("Michal");
//        newUser.setSurname("Nevim");
//        Assert.isNull(newUser.getId(), "Id is not null.");
//        ResponseEntity<User> response = assetsController.createUser(newUser);
//        Assert.notNull(response, "Response is null");
//        User storedUser = response.getBody();
//        Assert.notNull(storedUser, "User is null");
//        Assert.notNull(storedUser.getId(), "User id is null");
//        Assert.isTrue(Objects.equals(newUser.getName(), storedUser.getName()), "User name is different.");
//    }
//
//    @Test
//    @Order(3)
//    void testUserList() {
//        ResponseEntity<List<User>> response = assetsController.getUserList();
//        Assert.notNull(response, "Response is null");
//        List<User> userList = response.getBody();
//        Assert.notNull(userList, "User list is null");
//        Assert.notEmpty(userList, "User list is empty.");
//        Assert.isTrue(Objects.equals(userList.size(), 3), "User list does not contains three users.");
//    }
//
//    @Test
//    @Order(4)
//    void testGetUserById() {
//        String id = getFirstUserId(assetsController.getUserList());
//        ResponseEntity<User> response = assetsController.getUserById(id);
//        Assert.notNull(response, "Response is null");
//        User user = response.getBody();
//        Assert.notNull(user, "User list is null");
//        Assert.isTrue(Objects.equals(user.getId(), id), "User ids are not equals.");
//    }
//
//    @Test
//    @Order(5)
//    void testDeleteUser() {
//        String id = getFirstUserId(assetsController.getUserList());
//        assetsController.deleteUser(id);
//        ResponseEntity<List<User>> response = assetsController.getUserList();
//        Assert.notNull(response, "Response is null");
//        List<User> userList = response.getBody();
//        Assert.notNull(userList, "User list is null");
//        Assert.isTrue(!userList.isEmpty(), "User list is empty.");
//        Assert.isTrue(Objects.equals(userList.size(), 2), "User list does not contains two users.");
//    }
//
//    private String getFirstUserId(ResponseEntity<List<User>> response) {
//        List<User> userList = response.getBody();
//        if (CollectionUtils.isNotEmpty(userList)) {
//            return userList.get(0).getId();
//        }
//        return null;
//    }

}
