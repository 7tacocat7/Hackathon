import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
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


        //gets a specific member (and its team )
        //  /members/:member_id

        //get: show a specific team and its specific member
        //  /members/:member_id/teams/:team_id

        //get: show a form to create a new member
        //  /members/new

        //post: process a form to create a new member
        //  /members

        //get: show a form to update a member
        //  /members/update

        //post: process a form to update a member
        //  /members/update

        //get: delete a member and its team
        //  /members/:member_id/delete

        //get: delete all members and all teams

        //get: show all teams and all members

        get("/teams/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Team.clearAllTeams(); //change
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new Team form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        //post: process new Team form

        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String teamName = request.queryParams("teamName");
            String description = request.queryParams("description");
            String name = request.queryParams("name");
            Team newTeam = new Team(teamName,description);
            model.put("team",newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        // show all Teams
        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Team> teams = Team.getAllTeams();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams", (req, res) -> {
            Map<String , Object> model = new HashMap<>();
            List<Team> team = Team.getAllTeams();
            model.put("team", team);
            return new ModelAndView(model, "allTeams.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show an individual team
        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("id"));
            Team team = Team.findById(idOfTeamToFind);
            model.put("team", team);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());


        post("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind = Integer.parseInt(req.params("id"));
            String member = req.queryParams("name");
            Team team = Team.findById(idOfPostToFind);
            team.setMembers(member);
            model.put("team", team);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post
        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = req.queryParams("teamName");
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            editTeam.update(newTeamName);
            model.put("editTeam", editTeam);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual team
        get("/teams/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToDelete = Integer.parseInt(req.params("id"));
            Team deleteTeam = Team.findById(idOfTeamToDelete); //change
            deleteTeam.deleteTeam(); //change
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());






    }

}
