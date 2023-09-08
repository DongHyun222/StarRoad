package com.kb04.starroad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {


    private int no;
    private String name;
    private String id;
    private String password;
    private Date birthday;
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

}