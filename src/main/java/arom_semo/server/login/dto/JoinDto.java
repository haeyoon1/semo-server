package arom_semo.server.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {

    private String username;
    private String password;
    private String nickname;
    private int age;
    private String gender;
}