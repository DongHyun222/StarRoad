package com.kb04.starroad.Dto.auth;

import com.kb04.starroad.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    private String id;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .password(password)
                .build();
    }
}
