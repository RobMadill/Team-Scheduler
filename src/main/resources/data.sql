INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('rob@rob.ca', '$2a$10$MKqoVFxoTjyyoH8MCycPROaPsFdQmxDv8h8qLvoFFdtff2CVScnzK', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('bob@builder.ca', '$2a$10$Cfqlb8GMXKwpUNp3F5beheCrFPtzbiKQCWPviCcpb6pPrlnzFuqdy', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('hadeel@hadeel.ca', '$2a$10$PD7FhKeAxy/3BlK641YoNe1lJXPOEJXbE0iVHmz4MGa0PBus.Y6OC', 1);

INSERT INTO meeting(email, meetingDate1, meetingTime1, meetingDate2, meetingTime2) VALUES
('rob@rob.ca', 'Monday','Morning', 'Tuesday', 'Afternoon'),
('bob@builder.ca','Thursday', 'Lunchtime', 'Wednesday', 'Morning'),
('hadeel@hadeel.ca', 'Friday', 'Afternoon', 'Friday', 'Morning');

 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');
 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');
 

INSERT INTO user_role (userId, roleId)
VALUES (1, 1);
 
INSERT INTO user_role (userId, roleId)
VALUES (2, 1);

INSERT INTO user_role (userId, roleId)
VALUES (3, 1);