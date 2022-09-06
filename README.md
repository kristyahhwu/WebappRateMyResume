# CSC648-spring22-04-team04 Repository

## Application URL: 


## Please do the following steps before completing Milestone 0.
1. Change the name of the repository of csc648-spring22-SectionName-teamNN. 
 - SectionName should be one of 01, 02, 03 or 04. 
 - teamNN should be your team number. Team numbers whose value is less than 10, please pad with a 0. Ex team 1 is Team01 team 11 is Team11. Please make sure to also remove the username from the repository as well. Teams with incorrectly name repository will have points deducted from their milestone 0 grades.
 - Please follow the above naming convention.
 - Example) csc648-spring22-04-Team01,   csc648-spring22-01-Team05

1. PLEASE REMOVE THE USERNAME FROM THE REPOSITORY NAME!!!

2. Add ALL members of your team to this repository. For it to count, they must ACCEPT the invite.

3. Fill out the table below


| Student Name | Student Email | GitHub Username | Stundet's role |
|    :---:     |     :---:     |     :---:       |     :---:       |
| Leiyi Gao    | lgao4@mail.sfsu.edu             |   gaolarry1987             |  Team Leader |
| Justin Mao   | jmao@mail.sfsu.edu             |   jmao12                   |  Backend Leader |
| Yinyin Wu    | ywu21@mail.sfsu.edu            |   kristyahhwu              |  Scrum Master |
| Michael Han    | mhan2@mail.sfsu.edu           |   michaelhan38              |  Git Master |
| Nicholas Hamada | nhamada@mail.sfsu.edu       |  nhamada2                   | Frontend Leader |

## NO code needs to be stored in the root of your repository. You may rename the application folder if you like to your team's application name. But all source code related to your team's application should be stored inside the application folder.

## Developer Notes  
### Initial setup for VM instances in Google Cloud Platform  
Run the following command to install docker:  
`sudo apt-get install docker.io`  

### Running mongodb locally  
NOTE: You need to create an account in [docker hub](https://hub.docker.com/) to pull container images from the registry. Refer To [this article](https://docs.docker.com/engine/reference/commandline/login/) if you run into credential issues. 
From `applications` directory, run the following command:  
`docker compose up`
