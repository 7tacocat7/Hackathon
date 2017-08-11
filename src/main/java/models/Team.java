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
        members.add(member);
        instances.add(this);

    }
    public List<String> getMembers() {
        return members;
    }


    public static List<Team> getAllTeams() {
        return instances;
    }

}

