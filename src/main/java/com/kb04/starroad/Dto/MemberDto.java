package com.kb04.starroad.Dto;

import com.kb04.starroad.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private int no;
    private String name;
    private String id;
    private String password;
    private String birthday;
    private String phone;
    private String email;
    private String address;
    private String job;
    private String purpose;
    private String source;
    private int goal;
    private Character status;
    private int salary;
    private Character agreement;
    private int point;
    private int investment;

    public Member toMemberEntity() { // Dto를 Entity로 변환
        return Member.builder()
                .no(no)
                .name(this.name)
                .id(this.id)
                .password(this.password)
                .birthday(this.birthday)
                .phone(this.phone)
                .email(this.email)
                .address(this.address)
                .job(this.job)
                .purpose(this.purpose)
                .source(this.source)
                .goal(this.goal)
                .status(this.status)
                .salary(this.salary)
                .agreement(this.agreement)
                .point(this.point)
                .investment(this.investment)
                .build();
    }

}