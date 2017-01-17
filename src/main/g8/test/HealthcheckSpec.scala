package controllers

import controllers.Healthcheck
import org.scalatest.{ FlatSpec, Matchers }
import play.api.test.FakeRequest
import play.api.test.Helpers._

class HealthcheckSpec extends FlatSpec with Matchers {

  val healthcheck = new Healthcheck

  it should "return a 200 if application is up and running when hitting healthcheck endpoint" in {
    val resp = healthcheck.healthcheck.apply(FakeRequest())
    status(resp) should be(OK)
    contentAsString(resp) should be("ok")
  }

}