package com.kb04.starroad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberConditionDto {
    private int no;
    private ConditionDto conditionNo;
    private MemberDto memberNo;
}
