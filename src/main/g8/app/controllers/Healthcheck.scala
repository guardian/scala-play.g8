package controllers

import play.api.mvc.{ Action, Controller }

class Healthcheck extends Controller {

  def healthcheck = Action {
    Ok("ok")
  }

}
