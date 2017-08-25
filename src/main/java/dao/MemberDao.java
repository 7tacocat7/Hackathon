package dao;

import models.Member;
import models.Team;

import java.util.List;

public interface MemberDao {
    //create
    void add(Member member);

    //read
    List<Member> getAll();

    List<Team> getAllTeamsByMember(int memberId);

    Member findById(int id);

    //update
    void update(int id, String memberName);

    //delete
    void deleteById(int id);

    void clearAllMembers();
}

