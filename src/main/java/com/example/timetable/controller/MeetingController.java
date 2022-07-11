package com.example.timetable.controller;

import com.example.timetable.dto.FreeTimeDto;
import com.example.timetable.dto.MeetingDto;
import com.example.timetable.exception.CustomMeetingException;
import com.example.timetable.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Api("/timetable")
@RequestMapping("/timetable")
public class MeetingController {
    private MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping("/meeting/")
    public ResponseEntity<MeetingDto> addMeeting(@ApiParam(
            value = "Новая встреча",
            required = true,
            type = "com.example.timetable.dto.meetingDto"
    ) @Valid @RequestBody MeetingDto meetingDto) throws CustomMeetingException {
        return new ResponseEntity(meetingService.addMeeting(meetingDto), HttpStatus.OK);
    }

    @GetMapping("/meeting/getFreeTime")
    public ResponseEntity<List<FreeTimeDto>> getFreeTime(
            @ApiParam(
                    value = "Начальная временная граница",
                    required = true,
                    example = "2022-07-08 00:00",
                    type = "java.time"
            ) @RequestParam(
                    value = "startBorderTime",

                    required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                    LocalDateTime startBorderTime,

            @ApiParam(
                    value = "Конечная временная граница",
                    required = true,
                    example = "2022-07-08 23:00",
                    type = "java.time"
            ) @RequestParam(
                    value = "stopBorderTime",
                    required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")

                    LocalDateTime stopBorderTime) {
        return new ResponseEntity(meetingService.getFreeTime(startBorderTime, stopBorderTime), HttpStatus.OK);
    }


}
