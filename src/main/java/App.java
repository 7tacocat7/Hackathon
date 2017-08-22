//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import models.Team;
//import spark.ModelAndView;
//import spark.template.handlebars.HandlebarsTemplateEngine;import static spark.Spark.*;
//
//public class App {
//    public static void main(String[] args) {
//        staticFileLocation("/public");
//
//        //get: show new Team form
//        get("/teams/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "team-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        //post: process new Team form
//
//        post("/teams/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            String teamName = request.queryParams("teamName");
//            String description = request.queryParams("description");
//            String name = request.queryParams("name");
//            Team newTeam = new Team(teamName,description,name);
//            model.put("team",newTeam);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//        // show all Teams
//        get("/",(request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("team", Team.getAllTeams());
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/teams", (req, res) -> {
//            Map<String , Object> model = new HashMap<>();
//            List<Team> team = Team.getAllTeams();
//            model.put("team", team);
//            return new ModelAndView(model, "allTeams.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        //get: show an individual team
//        get("/teams/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfPostToFind = Integer.parseInt(req.params("id"));
//            Team team = Team.findById(idOfPostToFind);
//            model.put("team", team);
//            return new ModelAndView(model, "team-detail.hbs");
//        }, new HandlebarsTemplateEngine());
//        post("/teams/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfPostToFind = Integer.parseInt(req.params("id"));
//            String member = req.queryParams("name");
//            Team team = Team.findById(idOfPostToFind);
//            team.setMembers(member);
//            model.put("team", team);
//            return new ModelAndView(model, "team-detail.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show a form to update a post
//        get("/teams/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
//            Team editTeam = Team.findById(idOfTeamToEdit);
//            model.put("editTeam", editTeam);
//            return new ModelAndView(model, "team-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        post("/teams/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            String newTeamName = req.queryParams("teamName");
//            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
//            Team editTeam = Team.findById(idOfTeamToEdit);
//            model.put("editTeam", editTeam);
//            editTeam.updateTeamName(newTeamName);
//            return new ModelAndView(model,"success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//
//
//
//    }
//
//}
