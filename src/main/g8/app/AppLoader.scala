import play.api.libs.logback.LogbackLoggerConfigurator
import play.api.{ Application, ApplicationLoader }
import play.api.ApplicationLoader.Context

class AppLoader extends ApplicationLoader {
  override def load(context: Context): Application = {
    new LogbackLoggerConfigurator().configure(context.environment)
    new AppComponents(context).application
  }
}


