package dao;
import datamodels.Team;
import java.util.List;

public interface TeamDao {

    //create
    void add (Team team);
//    //read
    List<Team> getAllTeams();
    Team findByTeamId(int teamId);
//
//    //update
    void update (int teamId, String description);
    //delete
   void deleteByTeamId(int teamId); //me too
//    void deleteTeam();
//    void clearAllTeams();

}
