package dao;

import models.Member;
import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
public class Sql2oMemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Sql2oTeamDao teamDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);
        teamDao = new Sql2oTeamDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }


    @Test
    public void addingCourseSetsId() throws Exception {
        Member member = setupNewMember();
        int originalMemberId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId, member.getId());
    }

    @Test
    public void existingMembersCanBeFoundById() throws Exception {
        Member member = setupNewMember();
        memberDao.add(member);
        Member foundMember = memberDao.findById(member.getId());
        assertEquals(member, foundMember);
    }

    @Test
    public void addedMembersAreReturnedFromgetAll() throws Exception {
        Member member = setupNewMember();
        memberDao.add(member);
        assertEquals(1, memberDao.getAll().size());
    }

    @Test
    public void noMembersReturnsEmptyList() throws Exception {
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void updateChangesMemberContent() throws Exception {
        String initialname = "bob";
        Member member = new Member (initialname);
        memberDao.add(member);

        memberDao.update(member.getId(),"ted");
        Member updatedMember = memberDao.findById(member.getId());
        assertNotEquals(initialname, updatedMember.getMemberName());
    }

    @Test
    public void deleteByIdDeletesCorrectMember() throws Exception {
        Member member = setupNewMember();
        memberDao.add(member);
        memberDao.deleteById(member.getId());
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Member member = setupNewMember();
        Member otherMember = new Member("bill");
        memberDao.add(member);
        memberDao.add(otherMember);
        int daoSize = memberDao.getAll().size();
        memberDao.clearAllMembers();
        assertTrue(daoSize > 0 && daoSize > memberDao.getAll().size());
    }

//    @Test
//    public void getAllTeamsByMemberReturnsTeamsCorrectly() throws Exception {
//        Member member = setupNewMember();
//        member.setId(1);
//        memberDao.add(member);
//        int memberId = member.getId();
//        Team newTeam = new Team("tigers", "the best",memberId);
//        Team otherTeam = new Team("bears", "the best",memberId);
//        Team thirdTeam = new Team("the beasts", "the best",memberId);
//        teamDao.add(newTeam);
//        teamDao.add(otherTeam); //we are not adding task 3 so we can test things precisely.
//
//
//        assertTrue(memberDao.getAllTeamsByMember(memberId).size() == 2);
//        assertTrue(memberDao.getAllTeamsByMember(memberId).contains(newTeam));
//        assertTrue(memberDao.getAllTeamsByMember(memberId).contains(otherTeam));
//        assertFalse(memberDao.getAllTeamsByMember(memberId).contains(thirdTeam)); //things are accurate!
//    }
    public Member setupNewMember() {
        return new Member("phill");
    }

}
