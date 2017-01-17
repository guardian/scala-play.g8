import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.routing.Router
import controllers._
import router.Routes
import scala.concurrent.duration._
import play.api.libs.ws.ahc.AhcWSComponents
import com.gu.googleauth.GoogleAuthConfig
import play.api.libs.concurrent.Execution.Implicits._

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context)
      with AhcWSComponents {

  def mandatoryConfig(key: String): String = configuration.getString(key).getOrElse(sys.error(s"Missing config key: $key"))

  val googleAuthConfig = GoogleAuthConfig(
    clientId = mandatoryConfig("google.clientId"),
    clientSecret = mandatoryConfig("google.clientSecret"),
    redirectUrl = mandatoryConfig("google.redirectUrl"),
    domain = "guardian.co.uk")

  val applicationController = new Application
  val healthcheckController = new Healthcheck
  val loginController = new Login(wsClient, googleAuthConfig)
  val assets = new Assets(httpErrorHandler)
  val router: Router = new Routes(httpErrorHandler, applicationController, healthcheckController, loginController, assets)
}