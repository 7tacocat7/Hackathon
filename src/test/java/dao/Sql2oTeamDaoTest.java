
package dao;

import models.Member;
import models.Team;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.assertTrue;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oTeamDaoTest {
    private   Sql2oTeamDao teamDao; //ignore me for now. We'll create this soon.
    private Connection conn;//must be sql2o class conn



    @Before
        public  void setup() throws Exception {
            String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
            Sql2o sql2o = new Sql2o (connectionString, "", ""); //ignore me for now
            teamDao = new Sql2oTeamDao(sql2o); //ignore me for now
        //keep connection open through entire test so it does not get erased.
             conn = sql2o.open();


    }
    @After
        public void tearDown() throws Exception {
        conn.close();
}



    @Test
    public void addingTeamSetsId() throws Exception {
    Team team = setupNewTeam();
    int originalTeamId = team.getId();
    teamDao.add(team);
    assertNotEquals(originalTeamId, team);
    }

    @Test
    public void existingTeamsCanBeFoundByTeamId() throws Exception {
    Team team = setupNewTeam();
    teamDao.add(team);//add to dao (takes care of saving)
    Team foundTeam = teamDao.findByTeamId(team.getId());// retrieve
    assertEquals(team, foundTeam);
    }
    @Test
    public void allTeamsAreReturnedFromGetAll() throws  Exception{
    Team team = setupNewTeam();
    teamDao.add(team);
    assertEquals(1,teamDao.getAllTeams().size());
    }
    @Test
    public void noTeamsReturnsEmptyList() throws Exception {

        assertEquals(0, teamDao.getAllTeams().size());
    }

    @Test
    public void updateChangesTeamDescription() throws Exception {
        Team team = setupNewTeam();
        Member member = new Member ("carson ");
        String initialDescription ="the best";
        teamDao.add(team);
        teamDao.update(team.getId(),"we are Living Fire!!!");
        Team updatedTeam = teamDao.findByTeamId(team.getId());
        assertNotEquals(initialDescription,updatedTeam.getDescription());
    }
    @Test public void deleteByTeamIdDeletesCorrectTeam(){
        Team team = setupNewTeam();
        teamDao.add(team);
        teamDao.deleteByTeamId(team.getId());
        assertEquals(0,teamDao.getAllTeams().size());

    }
    @Test public void clearAllTeamsClearsAllTeams() {
        Team team = setupNewTeam();
        Team team1 = setupNewTeam();
        teamDao.add(team);
        teamDao.add(team1);
        int daoSize = teamDao.getAllTeams().size();
        teamDao.clearAllTeams();
        assertEquals(2,daoSize);
    }
    @Test
    public void categoryIdIsReturnedCorrectly() throws Exception {
        Team team = setupNewTeam();
        int originalCatId = team.getId();
        teamDao.add(team);
        assertEquals(originalCatId, teamDao.findByTeamId(team.getId()).getMemberId());
    }


    public Team setupNewTeam(){
        return new Team ("the best","we are awesome", 1);
    }


//    }
}