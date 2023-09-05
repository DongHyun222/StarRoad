package com.kb04.starroad.Dto;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "policy_seq", sequenceName = "policy_seq", allocationSize = 1)
public class PolicyDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_seq")
    private int no;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, length = 2000)
    private String explain;

    @Column(nullable = false, length = 50)
    private String tag;

    @Column(nullable = false, length = 5000)
    private String link;
}
