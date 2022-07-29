package com.example.timetable.controller;

import com.example.timetable.dto.MeetingDto;
import com.example.timetable.exception.CustomMeetingException;
import com.example.timetable.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
