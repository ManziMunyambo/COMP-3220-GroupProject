# Registration of Patients/Receptionists Brainstorming

cuz if I don't write this down, Ima forget

Registering patients should be done by a Receptionist, so the process could go like this...
* Receptionist logs in
* Asks patient for their info
* Adds patient as a record in the database

Receptionists can add new Receptonists into the database too

"But what if there's no Receptionists?"
- could prompt the software to make a receptionist right away

Patient info can consist of...
* fullName
* address
* dateOfBirth
* sex
* age
* bloodType
* healthCardNum
* disability 
(prob some more stuff to include, but I think this is good enough)

* List <Appointments> ???

Receptionists should be able to included into the database as well. They can enter their...
* fullName
* address
* employeeID

Patient and Receptionist have some overlap in info. I could do some abstraction here...

User:
* fullName
* address
* email
* dateOfBirth
* username
* password

Patient extends User
* healthCardNumber
* medicalHistory <- HashMap that includes all the important medical info

Receptionist extends User
* employeeID

Now...what methods should each file have?

PatientRegistration.java 
* Patient registerPatient(Scanner) - creates a new Patient with the info given by Receptionist
* void savePatient(Patient) - adds a brand new Patient record into the database
* bool isDuplicate(healthCardNumber) - checks if a patient is a duplicate
* bool validatePatient(healthCardNumber) - ensures all fields are filled in and format properly

ReceptionistRegistration.java
* Receptionist registerReceptionist(Scanner) - creates a new Receptionist with the info given
* void saveReceptionist(Receptionist) - adds a new Receptionist record into the database
* bool validateReceptionist(employeeID) - ensures all fields are filled in and formatted properly
* String generatedEmployeeID() - assigns an employee ID to a Receptionist
