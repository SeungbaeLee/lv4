package com.hh99.lv4.domain.member.dto;

import com.hh99.lv4.domain.member.entity.Member;
import com.hh99.lv4.domain.member.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class MemberPostDto {

    @Email
    @NonNull
    private String email;
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "비밀번호는 알파벳 대소문자, 숫자, 특수문자를 모두 포함해야 합니다."
    )
    @NonNull
    private String password;
    @NonNull
    private String gender;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String address;
    @NonNull
    private Role role;

    @Builder
    public MemberPostDto(String email, String password, String gender, String phoneNumber, String address, Role role) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .gender(this.gender)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .role(this.role)
                .build();
    }
}
