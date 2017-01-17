package auth

import com.gu.googleauth._
import play.api.Configuration
import controllers.routes

trait AuthActions extends Actions {

  // your app's routing
  override val loginTarget = routes.Login.loginAction
  override val defaultRedirectTarget = routes.Application.index
  override val failureRedirectTarget = routes.Login.login
}