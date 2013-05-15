import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {

  private ApplicationContext ctx;

  @Override
  public void onStart(Application app) {
      ctx = new ClassPathXmlApplicationContext("spring-app.xml");
  }

  @Override
  public <A> A getControllerInstance(Class<A> clazz) {
      return ctx.getBean(clazz);
  }

  @Override
  public void onStop(Application application) {
  }
}
