package dao;

import models.Team;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import org.sql2o.Connection;
import java.util.List;

//implementing our interface
public class Sql2oTeamDao implements TeamDao {
    private final Sql2o sql2o;
    public Sql2oTeamDao(Sql2o sql2o){
        this.sql2o = sql2o;//making the sql2o object available everywhere so we can call methods in it

    }

    @Override
    public void add(Team team) {
        String sql = "INSERT INTO teams (teamName, description, memberid) VALUES (:teamName, :description, :memberid)"; //raw sql
        try(Connection con = sql2o.open()){//try to open a connection
            int id = (int) con.createQuery(sql)//make a new variable
                    .addParameter("teamName", team.getTeamName())
                    .addParameter("description", team.getDescription())
                    .addParameter("memberid", team.getId())
                    .addColumnMapping("TEAMNAME", "teamname")
                    .addColumnMapping("DESCRIPTION", "description")
                    .addColumnMapping("memberid", "memberid")
                    .executeUpdate()//run it all
                    .getKey();//int id is now the row number (row “key”) of db
            team.setId(id);//update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public List<Team> getAllTeams() {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teams")
                    .executeAndFetch(Team.class);//fetch a list
        }
    }
    @Override
    public Team findByTeamId(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teams WHERE id = :id")
                    .addParameter("id",id)//key/value pair, key must match above
                    .executeAndFetchFirst(Team.class); //fetch an individual item
        }
    }
    @Override
    public void update(int id, String newDescription, int memberId) {
        String sql = "UPDATE teams SET description = :description WHERE id=:id";
        try (Connection con = sql2o.open())

        {
            con.createQuery(sql)
                    .addParameter("description", newDescription)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void deleteByTeamId(int id) {
        String sql ="DELETE from teams WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void clearAllTeams() {
        String sql = "DELETE from teams";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

} //implementing our interface


