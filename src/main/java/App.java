import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);

//        before("/teams/:id/update", (req, res) -> {
//            if(req.params(":id") == null){
//                res.redirect("/");
//                halt();
//            }
//                });

        //get: show new team form
        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new team form
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("name");
            String description = request.queryParams("description");
            Team newTeam = new Team(teamName, description, 1); //ignore the hardcoded membersId
            teamDao.add(newTeam);
            model.put("team", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req, res) -> {
//            Team winners = new Team("fish People","the gilliest");
//            teamDao.add(winners);
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAllTeams();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all teams
        get("/teams/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Member> allMembers = memberDao.getAll();
            model.put("members", allMembers);
            teamDao.clearAllTeams();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());



        //get: show a form to create a new member
        //  /members/new
        get("/members/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Member> members = memberDao.getAll(); //refresh list of links for navbar.
            model.put("members", members);
            return new ModelAndView(model, "member-form.hbs"); //new
        }, new HandlebarsTemplateEngine());


        //post: process a form to create a new member
        //  /members
        post("/members", (request, response) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            Member newMember = new Member(name);
            memberDao.add(newMember);
            List<Member> members = memberDao.getAll(); //refresh list of links for navbar.
            model.put("members", members);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a member
        //  /members/update
        get("/members/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("editMember", true);

            List<Member> allMembers = memberDao.getAll();
            model.put("members", allMembers);

            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete a member and its team
        //  /members/:member_id/delete

        //get: delete all members and all teams
        get("/members/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            teamDao.clearAllTeams();
            memberDao.clearAllMembers();

            List<Member> allMembers = memberDao.getAll();
            model.put("members", allMembers);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all teams and all members

        get("/teams", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAllTeams();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a team
        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Member> allMembers = memberDao.();
            model.put("members", allMembers);
            int newteamId = Integer.parseInt(req.params("id"));
            List<Team> allTeams = teamDao.getAllTeams();
            Team editTeam = teamDao.findByTeamId(newteamId);
            model.put("teams", allTeams);//puts all teams from the model to display
            model.put("editTeam", editTeam);//puts editteam as true for the if statment
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual team that is nested in a member
        get("/members/:members_id/teams/:team_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("team_id"));
            Team foundTeam = teamDao.findByTeamId(idOfTeamToFind);
            model.put("team", foundTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a team
        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Member> allMembers = memberDao.getAll();
            model.put("members", allMembers);
            int newteamId = Integer.parseInt(req.params("id"));
            List<Team> allTeams = teamDao.getAllTeams();
            Team editTeam = teamDao.findByTeamId(newteamId);
            model.put("teams", allTeams);//puts all teams from the model to display
            model.put("editTeam", editTeam);//puts editteam as true for the if statment
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a team
        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> allTeams = teamDao.getAllTeams();
            model.put("teams", allTeams);
            String newDescription = req.queryParams("description");
            int newteamId = Integer.parseInt(req.params("id"));//500 error. not sure how to grab this value. numberformat exception thrown. value is not parsed
            int teamToEditId = Integer.parseInt(req.queryParams("teamToEditId"));
            Team editTeam = teamDao.findByTeamId(teamToEditId);
            model.put("team", newteamId);//puts the id of the TeamId to edit?
            model.put("editTeam", editTeam);//why do i need this for post?
            teamDao.update(teamToEditId, newDescription);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //gets a specific member (and its team )
        //  /members/:member_id

        get("/members/:memId", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfMemberToFind = Integer.parseInt(req.params("memId")); //new
            List<Member> members = memberDao.getAll(); //refresh list of links for navbar.
            model.put("members", members);
            Member foundMember = memberDao.findById(idOfMemberToFind);
            model.put("member", foundMember);
            List<Team> allTeamsByMember = memberDao.getAllTeamsByMember(idOfMemberToFind);
            model.put("teams", allTeamsByMember);
            return new ModelAndView(model, "member-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());
        //get: show a specific team and its specific member
        //  /members/:member_id/teams/:team_id



        //get: delete an individual team
        get("/members/:members_id/teams/:team_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToDelete = Integer.parseInt(req.params("team_id"));
            Team deleteTeam = teamDao.findByTeamId(idOfTeamToDelete);
            teamDao.deleteByTeamId(idOfTeamToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());




    }

}
