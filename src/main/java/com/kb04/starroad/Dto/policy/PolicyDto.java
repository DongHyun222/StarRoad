package com.kb04.starroad.Dto.policy;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDto {

    private String name;
    private String location;
    private String explain;
    private String tag;
    private String link;
}
