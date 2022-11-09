package internal.dataAccess.DTO;

public class UserLoginResponseDTO {
    Boolean isLoggedIn;
    String error;

    public UserLoginResponseDTO(){

    }

    public UserLoginResponseDTO(Boolean isLoggedIn, String error) {
        this.isLoggedIn = isLoggedIn;
        this.error = error;
    }
}
