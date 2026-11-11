Clinic Database Schema

Patient
- Full_Name (Text)
- PID (Primary Key, Text)
- Password (Text)
- Date_Of_Birth (Text)
- Phone_Number (Text)
- Is_Shared_Household_Line (Text)
- Allow_Medical_Voicemail (Text)
- Street_Address (Text)
- City (Text)
- Postal_Code (Text)
- Email (Text)
- Family_Doctor (Text)
- Health_Insurance (Text)

Receptionist
- Full_Name (Text)
- Rid (Primary Key, Integer)
- Password (Text)
- Date_Of_Birth (Text)
- Phone_Number (Text)
- System_Username (Text)
- Desk_ID (INTEGER)

MedicalRecords
- Pid (Foreign Key, Integer)
- Record_ID (Primary Key, Integer)
- Note (Text)

Nurse
- Full_Name (Text)
- Nid (Integer, Primary Key)
- Date_Of_Birth (Text)
- Phone_Number (Text)
- Password (Text)
- System_Username (Text)

Doctor
- DID (Primary Key, Integer)
- Full_Name (Text)
- Password (Text)
- Specialty (Text)
- Days_Available (Text)
- Medical_License (Integer)
- Phone_Number (Integer)
- Date_Of_Birth (Text)
- System_Username (Text)

Appointment
- Start_Time (Text, Primary Key) - Formatted as YYYY-MM-DD HH:MM:SS
- End_Time (Text, Primary Key)- Formatted as YYYY-MM-DD HH:MM:SS
- Did (Foreign Key references Doctor.DID, Integer, Primary Key)
- Pid (Foreign Key references Patient.PID, Integer)
- Status (Text)

RoomAssignment
- Room_Number (Integer, Primary Key)
- Start_Time (Text, Primary Key) - Formatted as YYYY-MM-DD HH:MM:SS
- End_Time (Text, Primary Key) - Formatted as YYYY-MM-DD HH:MM:SS
- DID (Text, Primary Key)
