package com.example.timetable.service.impl;

import com.example.timetable.configuration.OrikaMapperConfig;
import com.example.timetable.dto.MeetingDto;
import com.example.timetable.entity.Meeting;
import com.example.timetable.exception.CustomMeetingException;
import com.example.timetable.repositorie.MeetingRepository;
import com.example.timetable.service.MeetingService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingServiceImpl implements MeetingService {

    private MapperFacade mapperFacade = new OrikaMapperConfig();

    private MeetingRepository meetingRepository;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Override
    public MeetingDto addMeeting(MeetingDto meetingDto) throws CustomMeetingException {


        Boolean isCollision = meetingRepository.checkCollisionTime(meetingDto.getUserid(), meetingDto.getMeetingTimeStart(), meetingDto.getMeetingTimeStop());

        try {

            Meeting meeting = Meeting.builder()
                    .meetingName(meetingDto.getMeetingName())
                    .meetingTimeStart(meetingDto.getMeetingTimeStart())
                    .meetingTimeStop(meetingDto.getMeetingTimeStop())
                    .userid(meetingDto.getUserid())
                    .build();

            if (!isCollision && (meetingDto.getMeetingTimeStop().compareTo(meetingDto.getMeetingTimeStart()) >= 0)) {

                meetingRepository.save(meeting);
                return mapperFacade.map(meeting, MeetingDto.class);
            } else {
                throw new CustomMeetingException("Не удалось сохранить встречу " + meetingDto.getMeetingName());
            }

        } catch (Exception exception) {
            throw new CustomMeetingException("Не удалось сохранить встречу " + meetingDto.getMeetingName());
        }
    }

}
