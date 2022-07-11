package com.example.timetable.repositorie;

import com.example.timetable.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {


    @Query(nativeQuery = true, value = "select exists (select 1 from meeting " +
            "where ((:meetingTimeStart) > meeting.meeting_time_start and (:meetingTimeStart)  < meeting_time_stop) " +
            "or ((:meetingTimeStop)  > meeting.meeting_time_start and (:meetingTimeStop) < meeting_time_stop) " +
            "or ((:meetingTimeStart)  < meeting.meeting_time_start and (:meetingTimeStop) > meeting_time_stop) " +
            "and meeting.userid = (:userid))")
    Boolean checkCollisionTime(@Param("userid") Integer userid, @Param("meetingTimeStart") LocalDateTime meetingTimeStart,
                               @Param("meetingTimeStop") LocalDateTime meetingTimeStop);

    @Query(nativeQuery = true, value = "select * from meeting where ((:meetingTimeStart) < meeting.meeting_time_start and (:meetingTimeStop) > meeting.meeting_time_stop)")
    List<Meeting> findAllByMeetingTimeStartIsBetween(@Param("meetingTimeStart") LocalDateTime meetingTimeStart,
                                                     @Param("meetingTimeStop") LocalDateTime meetingTimeStop);
}
