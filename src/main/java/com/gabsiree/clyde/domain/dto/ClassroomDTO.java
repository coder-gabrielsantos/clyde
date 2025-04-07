package com.gabsiree.clyde.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomDTO {
    private Long id;
    private String name;
    private String code;
    private String teacher;
}
