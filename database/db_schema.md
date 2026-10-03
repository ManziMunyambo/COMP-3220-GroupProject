Clinic Database Schema

Patient
- Health_Card (Primary Key, Text)
- Full_Name (Text)
- Address (Text)
- Email (Text)
- Date_Of_Birth (Text)
- Username (Text)
- Password (Text)
- Sex (Text)
- Blood_Type (Text)
- Disability (Text)

Receptionist
- RID (Primary Key, Integer)
- Full_Name (Text)
- Address (Text)
- Email (Text)
- Date_Of_Birth (Text)
- Username (Text)
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