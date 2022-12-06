# CSC648-Fall22-04-team04 Repository

## Application URL: http://ratemyresume.ninja


## Team member

| Student Name | Student Email | GitHub Username | Student's Role |
|    :---:     |     :---:     |     :---:       |     :---:       |
| Leiyi Gao    | lgao4@mail.sfsu.edu             |   gaolarry1987             |  Team Leader |
| Justin Mao   | jmao@mail.sfsu.edu             |   jmao12                   |  Backend Leader |
| Yinyin Wu    | ywu21@mail.sfsu.edu            |   kristyahhwu              |  Scrum Master, Frontend Lead |
| Michael Han    | mhan2@mail.sfsu.edu           |   michaelhan38              |  Git Master |
| Nicholas Hamada | nhamada@sfsu.edu       |  nhamada2                   | Team member |

## Running app in GCP
### Initial setup for VM instances in Google Cloud Platform  
- Install docker: `sudo apt-get install docker.io`
- Configure docker auth: `gcloud auth configure-docker`

### Pulling and running frontend image
```
docker pull gcr.io/project-648-1/ratemyresume
docker run -p 80:3000 -d gcr.io/project-648-1/ratemyresume
```

### Build and run backend
- pull the latest app version from github
- cd to `application/server` directory
- run the following commands:
  ```
  rm pom.xml
  mv gcp-pom.xml pom.xml
  rm -r target/
  mvn package
  java -jar target/Team4-1.0-SNAPSHOT.jar &
  ```  

## Running app locally

### Run mongodb

From `/applications` directory, run the following command: `docker compose up`  
NOTE: You need might to create an account in [docker hub](https://hub.docker.com/) to pull container images from the registry. Refer To [this article](https://docs.docker.com/engine/reference/commandline/login/) if you run into credential issues.  

You can interact with the database using mongoExpress by entering this address in your browser URL:  
`localhost:8081`

### Running backend server
- cd to `application/server` directory
- run the following commands:
  ```
  rm -r target/
  mvn package
  java -jar target/Team4-1.0-SNAPSHOT.jar &
  ```  

### Running frontend app
- cd to `application/client` directory
- run
  ```
  npm config set legacy-peer-deps true
  npm i
  npm start
  ```

## Build and push Docker image for frontend app
- cd to `application/client` directory
- run
  ```
  docker build . -t ratemyresume
  docker tag ratemyresume gcr.io/project-648-1/ratemyresume
  docker push gcr.io/project-648-1/ratemyresume
  ```