package dao;
import models.Team;
import java.util.List;

public interface TeamDao {

    //create
    void add (Team team);
//    //read
    List<Team> getAllTeams();

    Team findByTeamId(int id);
//
//    //update
    void update (int id, String description);
    //delete
   void deleteByTeamId(int id); //me too

    void clearAllTeams();

}
