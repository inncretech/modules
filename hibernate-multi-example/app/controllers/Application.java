package controllers;

import static play.data.Form.form;
import models.User;

import org.springframework.beans.factory.annotation.Autowired;

import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.UserDao;
import views.html.index;

@org.springframework.stereotype.Controller
public class Application extends Controller {

  @Autowired
  private UserDao userDao;

  public Result index() {
    DynamicForm pageForm = form().bindFromRequest();
    User user = userDao.loadUser(Long.parseLong(pageForm.get("user")));
    return ok(index.render(user));
  }

}
