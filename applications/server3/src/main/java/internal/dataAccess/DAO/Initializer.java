package internal.dataAccess.DAO;

import java.util.ArrayList;

public class Initializer {
    ArrayList<TeamMember> teamMembers;

    public Initializer() {
        teamMembers = new ArrayList<>();
        TeamMember leiyi = new TeamMember("Leiyi Gao", "Team Leader", "leiyi.jpeg", "lgao4@mail.sfsu.edu");
        TeamMember michael = new TeamMember("Michael Han", "Git Master", "leiyi.jpeg", "mhan2@mail.sfsu.edu");
        TeamMember yinyin = new TeamMember("Yinyin Wu", "Scrum Master", "yinyin.png", "ywu21@mail.sfsu.edu");
        TeamMember justin = new TeamMember("Justin Mao", "Backend Leader", "justin.png", "jmao@mail.sfsu.edu");
        TeamMember nicholas = new TeamMember("Nicholas Hamada", "Frontend Leader", "nicholas.png", "nhamada@mail.sfsu.edu");
        teamMembers.add(leiyi);
        teamMembers.add(michael);
        teamMembers.add(yinyin);
        teamMembers.add(justin);
        teamMembers.add(nicholas);
    }

    public ArrayList<TeamMember> getTeamMembers() {
        return teamMembers;
    }

}