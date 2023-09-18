package com.kb04.starroad.Entity;

import com.kb04.starroad.Dto.MemberDto;
import lombok.*;


import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    @SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
    @Column(name = "no", nullable = false)
    private int no;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "id", nullable = false, length = 15, unique = true)
    private String id;

    @Column(name = "password", nullable = false, length = 3000)
    private String password;

    @Column(name = "birthday", nullable = false)
    private String birthday;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "address", nullable = false, length = 300)
    private String address;

    @Column(name = "job", nullable = false, length = 30)
    private String job;

    @Column(name = "purpose", nullable = false, length = 50)
    private String purpose;

    @Column(name = "source", nullable = false, length = 50)
    private String source;

    @Column(name = "goal", nullable = false)
    private int goal;


    @Column(name = "status", nullable = false)
    private Character status = 'Y';

    @Column(name = "salary", nullable = false)
    private int salary;

    @Column(name = "agreement", nullable = false)
    private Character agreement = 'Y';

    @Column(name = "point", nullable = false)
    private int point = 0;

    @Column(name = "investment", nullable = false)
    private int investment = 0;

    public MemberDto toMemberDto() {
        return MemberDto.builder()
                .no(no)
                .name(name)
                .id(id)
                .password(password)
                .birthday(birthday)
                .phone(phone)
                .email(email)
                .address(address)
                .job(job)
                .purpose(purpose)
                .source(source)
                .goal(goal)
                .status(status)
                .salary(salary)
                .agreement(agreement)
                .point(point)
                .investment(investment)
                .build();
    }

    public void savePoint(int rewardPoint) {
        this.point += rewardPoint;
    }
}