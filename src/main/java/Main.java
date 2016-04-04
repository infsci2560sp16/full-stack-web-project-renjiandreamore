import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {

  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
	//port(8888);
    staticFileLocation("/public");
    UserDB db = new UserDB();
    db.DefaultDB();
    get("/profile", (req, res) -> {
        Map<String, Object> attributes = new HashMap<>();
        try{
        	attributes.put("latest", true);
        	attributes.put("title", "Latest Recipe");
        	attributes.put("recipes", new String[]{"Orange Chicken", "General Tso's Chicken", "Mapo Dofu"});
        	return new ModelAndView(attributes, "profile.ftl");
        } catch (Exception e) {
            attributes.put("message", "There was an error: " + e);
            return new ModelAndView(attributes, "error.ftl");
        }
    }, new FreeMarkerEngine());
    
    get("/json/:id", (req, res) -> {
        Map<String, Object> attributes = new HashMap<>();
        String id = req.params(":id");
        User usr = db.Find(id);
        try{
        	attributes.put("method", "get");
        	attributes.put("result", usr.JSON());
        	return new ModelAndView(attributes, "json.ftl");
        }catch (Exception e) {
            attributes.put("message", "There was an error: " + e);
            return new ModelAndView(attributes, "error.ftl");
        }
    }, new FreeMarkerEngine());
    
    post("/register", (req, res) -> {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("result", true);
        String name = req.queryParams("name");
        String gender = req.queryParams("gender");
        db.addUser(name, gender);
        User usr = db.FindbyName(name);
        try{
        	attributes.put("method", "post");
        	attributes.put("result", usr.JSON());
        	return new ModelAndView(attributes, "json.ftl");
        }catch (Exception e) {
            attributes.put("message", "There was an error: " + e);
            return new ModelAndView(attributes, "error.ftl");
        }
    }, new FreeMarkerEngine());
    
    
    get("/xml/:id", (req, res) -> {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("result", true);
        String id = req.params(":id");
        User usr = db.Find(id);
        try{
        	attributes.put("result", usr.XML());
        	return new ModelAndView(attributes, "xml.ftl");
        }catch (Exception e) {
            attributes.put("message", "There was an error: " + e);
            return new ModelAndView(attributes, "error.ftl");
        }
    }, new FreeMarkerEngine());  
    
    get("/db", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        return new ModelAndView(attributes, "db.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
    }, new FreeMarkerEngine());

  }

}
