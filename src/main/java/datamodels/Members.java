package datamodels;

public class Members {
    private String memberName;
    private String memberId;

//getters
    public String getMemberName() {
        return memberName;
    }

    public String getMemberId() {
        return memberId;
    }
//setters

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Members members = (Members) o;

        if (!memberName.equals(members.memberName)) return false;
        return memberId != null ? memberId.equals(members.memberId) : members.memberId == null;
    }

    @Override
    public int hashCode() {
        int result = memberName.hashCode();
        result = 31 * result + (memberId != null ? memberId.hashCode() : 0);
        return result;
    }
}
