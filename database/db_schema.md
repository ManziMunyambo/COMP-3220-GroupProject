Clinic Database Schema

Patient
- PID (Primary Key, Integer)
- First_Name (Text)
- Last_Name (Text)
- Password (Text)

Receptionist
- RID (Primary Key, Integer)
- First_Name (Text)
- Last_Name (Text)
- Password (Text)

Doctor
- DID (Primary Key, Integer)
- First_Name (Text)
- Last_Name (Text)
- Password (Text)
- Specialty (Text)

Appointment
- Start_Time (Text, Primary Key) - Formatted as YYYY-MM-DD HH:MM:SS
- End_Time (Text, Primary Key)- Formatted as YYYY-MM-DD HH:MM:SS
- Doctor_ID (Foreign Key references Doctor.DID, Integer, Primary Key)
- Patient_ID (Foreign Key references Patient.PID, Integer)