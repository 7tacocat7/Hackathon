package dao;

import datamodels.Member;
import datamodels.Team;

import java.util.List;

public interface MemberDao {
    //create
    void add(Member member);

    //read
    List<Member> getAllMembers();

    List<Team> getAllTeamsByMember(int memberId);

    Member findByMemberId(int memberId);

    //update
    void updateMember(int memberId, String memberName);

    //delete
    void deleteByMemberId(int memberId);

    void clearAllMembers();
}

