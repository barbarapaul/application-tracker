# Application Tracker

## Building and Running Tests

    mvn clean package
    
## Running Application in Development Mode

    mvn spring-boot:run -Dspring.profiles.active=test
    
## Examples
### Submit Qualified Application
```
curl -X POST \
  http://localhost:8080/api/v1/applications \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
  "name": "Application Name",
  "answers": [
    {
      "id": "id01",
      "answer": "answer 1"
    },
    {
      "id": "id02",
      "answer": "answer 2"
    }
  ]
}'
```
### Submit not Qualified Application
```
curl -X POST \
  http://localhost:8080/api/v1/applications \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
  "name": "Application Name",
  "answers": [
    {
      "id": "id01",
      "answer": "answer 1"
    },
    {
      "id": "id02",
      "answer": "wrong answer"
    }
  ]
}'
```
### Get Submitted Applications
```
curl -X GET \
  http://localhost:8080/api/v1/applications \
  -H 'cache-control: no-cache'
```