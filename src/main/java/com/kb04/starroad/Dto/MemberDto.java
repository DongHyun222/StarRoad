package com.kb04.starroad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="member")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    @SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
    @Column(name= "no", nullable = false)
    private int no;

    @Column(name= "name",nullable = false, length = 15)
    private String name;

    @Column(name="id", nullable = false, length = 15, unique = true)
    private String id;

    @Column(name="password", nullable = false, length = 15)
    private String password;

    @Column(name="birthday" ,nullable = false)
    private Date birthday;

    @Column(name="phone" ,nullable = false, length = 15)
    private String phone;

    @Column(name= "email",nullable = false, length = 15, unique = true)
    private String email;

    @Column(name="address",nullable = false, length = 300)
    private String address;

    @Column(name="job",nullable = false, length = 30)
    private String job;

    @Column(name="purpose",nullable = false, length = 50)
    private String purpose;

    @Column(name="source",nullable = false, length = 50)
    private String source;

    @Column(name="goal",nullable = false)
    private int goal;

    @Column(name="status",nullable = false)
    private boolean status;

    @Column(name="salary",nullable = false)
    private int salary;

    @Column(name="agreement",nullable = false)
    private boolean agreement;

    @Column(name="point",nullable = false)
    private int point;

    @Column(name="investment",nullable = false)
    private int investment;

}
