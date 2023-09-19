package com.kb04.starroad.Dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MypageResponseDto {
        private String name;    // 이름
        private int point;      // 포인트리
        private int investment; // 투자금
        private int savings;    // 적금
        private int deposit;    // 예금
}
