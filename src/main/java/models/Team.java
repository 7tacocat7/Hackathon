package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String description;

    //    private String name;
    private  List<String> members = new ArrayList<String>();
    public static List<Team>instances = new ArrayList<Team>();
    private int teamId;
//    private String teamMember;

    public Team(String teamName, String description,String name){
        this.teamName = teamName;
        this.description = description;
        this.members.add(name);
        instances.add(this);
        this.teamId = instances.size();

    }
//    getters

    public List<String> getMembers() {
        return members;
    }

    public String getTeamName() {
        return teamName;
    }
    public String getDescription() {
        return description;
    }




    public static List<Team> getAllTeams() {
        return instances;
    }

    public static void clearAllTeams(){
        instances.clear();
    }

    public  int getTeamId() {
        return teamId;
    }

    public static Team findById(int teamId) {
        return instances.get(teamId - 1);
    }


    public void setMembers(String name) {
        this.members.add(name);
    }

    public void updateTeamName(String teamName) {
        this.teamName = teamName;
    }




}

