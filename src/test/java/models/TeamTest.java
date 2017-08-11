package models;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {
    @Test
    public void newTeam_instantiatesCorrectly() throws Exception {
        Team testTeam = new Team ("1","the best","Sue");
        assertEquals(true, testTeam instanceof Team);
    }
    @Test
    public void all_returnsAllInstancesOfTeam_true() throws Exception{
        Team firstTeam = new Team("tigers","we are the best","john");
        Team secondTeam = new Team("lions","the lions team is focused on java","Phill");
        assertTrue(Team.getAllTeams().contains(firstTeam));
        assertTrue(Team.getAllTeams().contains(secondTeam));
    }
    @Test
    public void all_returnsAllInstancesOfMembers_true() throws Exception {
        Team testTeam = new Team ("bears","the best team on the planet","collin");
        Team testTeam2 = new Team ("cubs","the best","ryan");
        assertTrue(testTeam.getMembers().contains("collin"));
        assertTrue(testTeam2.getMembers().contains("ryan"));
    }


}