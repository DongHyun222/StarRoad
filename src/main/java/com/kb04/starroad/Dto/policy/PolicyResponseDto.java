package com.kb04.starroad.Dto.policy;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyResponseDto {

    private int no;
    private String name;
    private String location;
    private String explain;
    private String tag;
    private String link;
}
