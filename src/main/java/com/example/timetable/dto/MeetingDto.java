package com.example.timetable.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel(description = "Встреча")
@Data
public class MeetingDto {
    @ApiModelProperty(
            notes = "Идентификатор пользователя",
            name = "userid",
            dataType = "java.lang",
            example = "1"
    )
    @NotNull
    private Integer userid;

    @ApiModelProperty(
            notes = "Время начала встречи",
            name = "meetingTimeStart",
            dataType = "java.time",
            example = "2022-07-08 15:00"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private LocalDateTime meetingTimeStart;

    @ApiModelProperty(
            notes = "Время окончания встречи",
            name = "meetingTimeStop",
            dataType = "java.time",
            example = "2022-07-08 17:00"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private LocalDateTime meetingTimeStop;

    @ApiModelProperty(
            notes = "Наименование встречи",
            name = "meetingTimeEnd",
            dataType = "java.lang",
            example = "Встреча друзей"
    )
    @NotBlank
    private String meetingName;
}
