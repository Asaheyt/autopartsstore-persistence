package kz.asaheyt.inc.autopartsstore.persistence.route

import akka.actor.typed.ActorSystem
import akka.cluster.sharding.typed.scaladsl.ClusterSharding
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import kz.asaheyt.inc.autopartsstore.persistence.util.Codec
import akka.http.scaladsl.server.Directives._
import io.circe.parser.parse
import kz.asaheyt.inc.autopartsstore.persistence.command.CreateAutoPartCommand
import kz.asaheyt.inc.autopartsstore.persistence.entity.AutoPartEntity
import kz.asaheyt.inc.autopartsstore.persistence.model.{AutoPartDTO, AutoPartSummary}

import io.circe.{Json, parser}
import io.circe.generic.auto._
import io.circe.syntax._

import scala.util.{ Failure, Success }

import scala.concurrent.{ExecutionContext, Future}

class AutoPartRoutes(implicit system: ActorSystem[_], implicit val executionContext: ExecutionContext) extends Codec with FailFastCirceSupport {

  implicit private val timeout: Timeout = Timeout.create(system.settings.config.getDuration("askTimeout"))

  private val sharding = ClusterSharding(system)

  val routes: Route = {
    createAutoPartRoute
  }


  def createAutoPartRoute: Route = {
    pathPrefix("autopart" / "init") {
      post {
        entity(as[AutoPartDTO]) { entity =>

          val entityRef = sharding.entityRefFor(AutoPartEntity.EntityKey, entity.autoPartId.toString)

          val reply: Future[AutoPartSummary] = entityRef.ask(
            CreateAutoPartCommand(
              ts = entity.ts,
              autoPartId = entity.autoPartId,
              name = entity.name,
              quantity = entity.quantity,
              _
            )
          )

          onSuccess(reply) { summary =>

            val message = HttpRequest(
              method = HttpMethods.POST,
              uri = "http://localhost:8080/home/test/autopart/add",
              entity = HttpEntity(ContentTypes.`application/json`, summary.asJson.noSpaces)
            )

            val responseFuture: Future[HttpResponse] = Http().singleRequest(message)

            responseFuture
              .onComplete {
                case Success(res) => println(res)
                case Failure(_)   => sys.error("something wrong")
              }


            complete("OK")
          }
        }
      }
    }
  }


}