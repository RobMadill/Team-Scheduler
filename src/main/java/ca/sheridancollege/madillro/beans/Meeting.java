package ca.sheridancollege.madillro.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Meeting {
	private Long id;
	private String email;
	private String meetingDate1;
	private String meetingDate2;	
	private String meetingTime1;
	private String meetingTime2;
	String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
	String[] timeSlot = {"Morning", "Lunchtime", "Afternoon" };
}