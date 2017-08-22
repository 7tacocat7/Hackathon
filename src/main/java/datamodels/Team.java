package datamodels;
public class Team {
    private int teamId;
    private String teamName;
    private String description;


//constructor
    public Team(String teamName, String description){
        this.teamName = teamName;
        this.description = description;
    }
//setters
    public void setDescription(String description) {this.description = description;}
    public void setTeamId(int teamId) {this.teamId = teamId;}

    //override methods


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (teamId != team.teamId) return false;
        if (!teamName.equals(team.teamName)) return false;
        return description.equals(team.description);
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + teamName.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    //getters
    public String getDescription() {
        return this.description;
    }
    public String getTeamName() {
        return this.teamName;
    }
    public int getTeamId() { return this.teamId;}










}

