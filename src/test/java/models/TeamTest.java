package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Team.clearAllTeams();
    }

    @Test
    public void newTeam_instantiatesCorrectly() throws Exception {
        Team testTeam = new Team ("1","the best","o");
        assertEquals(true, testTeam instanceof Team);
    }
    @Test
    public void AllTeamsAreCorrectlyReturned_true() {
        Team team = new Team("team","the best","l");
        Team otherTeam = new Team ("team2","we the coolest","l");
        assertEquals(2, Team.getAllTeams().size());
    }
    @Test
    public void all_returnsAllInstancesOfTeam_true() throws Exception{
        Team firstTeam = new Team("tigers","we are the best","p");
        Team secondTeam = new Team("lions","the lions team is focused on java","i");
        assertTrue(Team.getAllTeams().contains(firstTeam));
        assertTrue(Team.getAllTeams().contains(secondTeam));
    }
    @Test
    public void all_returnsAllInstancesOfMembers_true() throws Exception {
        Team testTeam = new Team ("bears","the best team on the planet","y");
        Team testTeam2 = new Team ("cubs","the best","tim");
        testTeam.getMembers().add("collin");
        testTeam2.getMembers().add("ryan");
        assertTrue(testTeam.getMembers().contains("collin"));
        assertTrue(testTeam2.getMembers().contains("ryan"));
    }
    @Test
    public void getTeamId_teamInstantiateWithAnID_1() throws Exception{
        Team.clearAllTeams();
        Team myTeam = new Team("the cool guys","just a bunch of cool guys","gary");
        assertEquals(1, myTeam.getTeamId());
    }
    @Test
    public void findReturnsCorrectTeam() throws Exception {
        Team team = setupNewTeam();
        assertEquals(1, Team.findById(team.getTeamId()).getTeamId());
    }

    public Team setupNewTeam(){
        return new Team ("team1","the best ever","tim");
    }
    @Test
    public void findReturnsCorrectTeamWhenMoreThanOneTeamExists() throws Exception {
        Team team = setupNewTeam();
        Team otherteam = new Team("the beans","cool beans","l");
        assertEquals(2, Team.findById(otherteam.getTeamId()).getTeamId());
    }

    @Test
    public void updateChangesTeamContent() throws Exception {
        Team team = setupNewTeam();
        String formerTeamname = team.getTeamName();
        String formerDescription = team.getDescription();
        int formerId = team.getTeamId();
        team.updateTeamName("the bull dogs");
        assertEquals(formerId, team.getTeamId());
        assertEquals(formerDescription, team.getDescription());
        assertNotEquals(formerTeamname, team.getTeamName());

    }
    @Test
    public void addTeamMemberAddsNewNameToList() throws Exception{
        Team team = setupNewTeam();
        team.setMembers("john");
        assertTrue(team.getMembers().contains("john"));

    }


}