package models;

public class Member {
    private String memberName;
    private int id;
    private int teamId;

//constructor


    public Member(String memberName) {
        this.memberName = memberName;
    }

    //getters
    public String getMemberName() {
        return memberName;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getId() {
        return id;
    }
//setters

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != member.id) return false;
        if (teamId != member.teamId) return false;
        return memberName.equals(member.memberName);
    }

    @Override
    public int hashCode() {
        int result = memberName.hashCode();
        result = 31 * result + id;
        result = 31 * result + teamId;
        return result;
    }
}
