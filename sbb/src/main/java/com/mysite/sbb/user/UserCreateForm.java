package com.mysite.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserCreateForm {
    @Size(min =3,max =25)
    @NotEmpty(message = "사용자ID는필수항목입니다.")
    private String username;
    @NotEmpty(message = "비밀번호는필수항목입니다.")
    private String password1;
    @NotEmpty(message = "비밀번호확인은필수항목입니다.")
    private String password2;
    @NotEmpty(message = "이메일은필수항목입니다.")
    @Email
    private String email;
}
