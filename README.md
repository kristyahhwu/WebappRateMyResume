# CSC648-Fall22-04-team04 Repository

## Application URL: http://34.94.190.11:3000


## Team member

| Student Name | Student Email | GitHub Username | Student's Role |
|    :---:     |     :---:     |     :---:       |     :---:       |
| Leiyi Gao    | lgao4@mail.sfsu.edu             |   gaolarry1987             |  Team Leader |
| Justin Mao   | jmao@mail.sfsu.edu             |   jmao12                   |  Backend Leader |
| Yinyin Wu    | ywu21@mail.sfsu.edu            |   kristyahhwu              |  Frontend Lead |
| Michael Han    | mhan2@mail.sfsu.edu           |   michaelhan38              |  Git Master |
| Nicholas Hamada | nhamada@sfsu.edu       |  nhamada2                   | Scrum Master |

## NO code needs to be stored in the root of your repository. You may rename the application folder if you like to your team's application name. But all source code related to your team's application should be stored inside the application folder.

## Running app in the GCE
### Initial setup for VM instances in Google Cloud Platform  
- Install docker: `sudo apt-get install docker.io`
- Configure docker auth: `gcloud auth configure-docker`

### Pulling and running frontend image
- docker pull gcr.io/project-648-1/ratemyresume
- docker run -p 3000:3000 -d gcr.io/project-648-1/ratemyresume

### Running backend
- pull the latest app version from github
- cd to `application/server` directory
- run the following commands:
  ```
  mvn package
  java -jar target/Team4-1.0-SNAPSHOT.jar &
  ```  

## Running app locally

### Run mongodb

From `/applications` directory, run the following command: `docker compose up`  
NOTE: You need might to create an account in [docker hub](https://hub.docker.com/) to pull container images from the registry. Refer To [this article](https://docs.docker.com/engine/reference/commandline/login/) if you run into credential issues.  

You can interact with the database using mongoExpress by entering this address in your browser URL:  
`localhost:8081`

### Run backend
- cd to `application/server` directory
- run the following commands:
  ```
  mvn package
  java -jar target/Team4-1.0-SNAPSHOT.jar &
  ```  

### Run frontend
- cd to `application/client` directory
- run
  ```
  npm config set legacy-peer-deps true
  npm i
  npm start
  ```
