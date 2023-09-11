package com.kb04.starroad.Dto.policy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyRequestDto {

    private String keyword;
    private String location;
    private String tag1;
    private String tag2;
    private String tag3;
    private String tag4;
}
