package com.example.timetable.service;

import com.example.timetable.dto.MeetingDto;
import com.example.timetable.exception.CustomMeetingException;

public interface MeetingService {
    MeetingDto addMeeting(MeetingDto meetingDto) throws CustomMeetingException;
}
