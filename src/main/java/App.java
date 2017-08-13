import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //get: show new Team form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());


        //post: process new Team form

        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String teamName = request.queryParams("teamName");
            String description = request.queryParams("description");
            Team newTeam = new Team(teamName,description);
            model.put("team",newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        // show all Teams
        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("teams", Team.getAllTeams());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show an individual team
        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind = Integer.parseInt(req.params("id"));
            Team team = Team.findById(idOfPostToFind);
            model.put("team", team);
            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/teams", (req, res) -> {
            Map<String , Object> model = new HashMap<>();
            List<Team> team = Team.getAllTeams();
            model.put("team", team);
            return new ModelAndView(model, "allTeams.hbs");
        }, new HandlebarsTemplateEngine());




    }

}
