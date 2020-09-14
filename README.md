# JPService

JP Service helps to decide of sending Christmas presents to children depending on them behavior.

### Present request API
Person can send GET-request to the system:
e.g: http://localhost:8080/api/request

with his name and present type.
The information should be provided in JSON:
e.g
{
    "firstName": "Dmitry",
    "lastName": "Petrov",
    "presentType": "Car"
}

The system tries to find person by "firstName" and "lastName" and present type by "presentType" . In case of person or present type were not found system returns 404 NOT_FOUND code.
If person found system checks his behavior. 
If behavior is bad system returns message:
e.g. "Dmitry Petrov cannot receive Car because of bad behavior".

If behavior is good system returns message:
e.g. "Dmitry Petrov received Car because of good behavior"

### Supply API
External system can add some number of specified presents.
 - System calls supply API with POST request:
 e.g.: http://localhost:8080/api/supply
 
 and sends JSON:
 e.g.:
 
 {
     "presentType": "Car",
     "quantity": 5
 }
 
 where "presentType" - type of presents which exists in JPService system.
 "quantity" - integer positive value.
 
 In case of success system will return 200 OK code with "Presents have been added" message.
 
 ### The future improvements:
  - current quantity of specified present type tracking and requests to factory to produce addition ones;
  - present factory which can send notifications to JPService to increase presents quantity.
  - Code should be covered by unit tests.