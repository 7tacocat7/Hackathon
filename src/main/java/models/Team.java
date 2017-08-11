package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String description;
    private String member;
    private  List<String> members = new ArrayList<String>();
    public static List<Team>instances = new ArrayList<Team>();

    public Team(String teamName, String description, String member){
        this.teamName = teamName;
        this.description = description;
        this.member = member;
        this.members.add(member);
        instances.add(this);

    }
    public List<String> getMembers() {
        return members;
    }
    public void setMembers(List<String> members) {
        this.members = members;
    }
    public void setMember(String member) {
        this.member = member;
    }




    public static List<Team> getAllTeams() {
        return instances;
    }

}

