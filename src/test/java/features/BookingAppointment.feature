Feature: Booking Appointment
      I want to book an appointment date.
   Scenario: Booking Appointment
     Given the user in the appointment portal and click on the booking appointment
     When  I add all required data
     And Adding OTP
     Then The appointment will be created
