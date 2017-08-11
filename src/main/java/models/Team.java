package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String description;
    //    private String member;
    private  List<String> members = new ArrayList<String>();
    public static List<Team>instances = new ArrayList<Team>();

    public Team(String teamName, String description){
        this.teamName = teamName;
        this.description = description;
        members = members;
        instances.add(this);

    }
//    getters
    public List<String> getMembers() {
        return members;
    }





    public static List<Team> getAllTeams() {
        return null;
    }

    public static void clearAllTeams(){
        instances.clear();
    }

}

