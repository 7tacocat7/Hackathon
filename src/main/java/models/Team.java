package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String description;
    private  ArrayList<String> members = new ArrayList<String>();
    public static ArrayList<Team>instances = new ArrayList<Team>();
    private int teamId;
//    private String teamMember;

    public Team(String teamName, String description){
        this.teamName = teamName;
        this.description = description;
        this.members =  members;
        instances.add(this);
        this.teamId = instances.size();

    }
//    getters
    public List<String> getMembers() {
        return members;
    }





    public static ArrayList<Team> getAllTeams() {
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

}

