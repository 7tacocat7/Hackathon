import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Team> teams = Team.getAllTeams();
            model.put("posts", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String teamName = request.queryParams("teamName");
            String description = request.queryParams("description");
            Team newTeam = new Team(teamName,description);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


    }

}
