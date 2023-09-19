package com.kb04.starroad.Dto.product;

import com.kb04.starroad.Dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberConditionDto {
    private int no;
    private ConditionDto condition;
    private MemberDto member;
}
