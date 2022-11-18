package internal.service;
import com.google.gson.Gson;
import internal.dataAccess.DTO.UserDTO;
import internal.dataAccess.DTO.UserLoginDTO;
import internal.dataAccess.DTO.UserLoginResponseDTO;
import internal.service.Utilities.*;


import static org.junit.jupiter.api.Assertions.*;
class LoginTest{
    Gson gson = new Gson();
    @org.junit.jupiter.api.Test
    void LoginTest(){
        UserDTO newUser = new UserDTO();
        newUser.userId = "1";
        newUser.username = "john123";
        newUser.password = "password123";
        newUser.email = "email@email.email";
        String user = Utilities.createUser(gson.toJson(newUser));
//        assertEquals("User created", user);
        UserLoginDTO newLogin = new UserLoginDTO();
        newLogin.username = "john123";
        newLogin.password = "password123";
        String loginResponse = Utilities.login(gson.toJson(newLogin));
        UserLoginResponseDTO response = gson.fromJson(loginResponse, UserLoginResponseDTO.class);
        assertNotEquals(gson.toJson(new UserLoginResponseDTO(false, "Invalid password")), response);
        assertNotEquals(gson.toJson(new UserLoginResponseDTO(false, "User not found")), response);
        assertEquals(gson.toJson(new UserLoginResponseDTO(true, null)), loginResponse);
        Utilities.deleteUser(user);
        assertEquals(gson.toJson(new UserLoginResponseDTO(false, "User not found")), Utilities.login(gson.toJson(newLogin)));
    }
}