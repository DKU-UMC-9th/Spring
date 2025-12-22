package com.example.umc9th.domain.mission.dto.res;


import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.ReviewPreviewDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record MissionPreviewListDTO(
            List<MissionResDTO.MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}


    @Builder
    public record MissionPreviewDTO(
            long missionId,
            String restaurantName,
            String content,
            int point,
            LocalDateTime expiredAt
    ){}

}
