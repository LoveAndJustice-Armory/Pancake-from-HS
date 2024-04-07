package tacos.domain;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class Registration {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String province;
    private String postCode;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password),fullname,street,city,province,postCode,phone);
    }
}
