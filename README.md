This project Notification microservice with clean architecture that uses InfoBip API for SMS  and Email using smtp.gmail .
It sends OTP for bursary auth service.
The project leverages RabbitMQ message broker to listen to UserCreated events 
and consume those events by triggering SMsService and EmailService to send OTP for
bursary applicants identity verification.
