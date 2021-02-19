package kz.asaheyt.inc.autopartsstore.persistence.route

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.typesafe.config.Config

class HttpRoutes()(implicit system: ActorSystem[_], config: Config) {

  val routes: Route = pathPrefix("api") {
    pathPrefix("v1") {
      concat(
        pathEndOrSingleSlash {
          get {
            complete("Welcome to akka persistence template!")
          }
        }/*,
        new EmployeeRoutes().routes,
        new SwaggerSite().swaggerSiteRoute,
        new SwaggerRoutes().routes*/
      )
    }
  }

}
