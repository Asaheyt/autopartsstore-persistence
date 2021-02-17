package kz.asaheyt.inc.autopartsstore.persistence.entity

import akka.actor.typed.Behavior
import akka.cluster.sharding.typed.scaladsl.EntityTypeKey
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.{Effect, EventSourcedBehavior}
import kz.asaheyt.inc.autopartsstore.persistence.command.{AddToCartAutoPartCommand, AutoPartCommand, CheckoutAutoPartCommand, CreateAutoPartCommand, DeliverAutoPartCommand, FinishAutoPartCommand, RemoveFromCartAutoPartCommand, ReturnAutoPartCommand}
import kz.asaheyt.inc.autopartsstore.persistence.event.{AddToCartAutoPartEvent, AutoPartEvent, CheckoutAutoPartEvent, CreateAutoPartEvent, DeliverAutoPartEvent, FinishAutoPartEvent, RemoveFromCartAutoPartEvent, ReturnAutoPartEvent}

object AutoPartEntity {

  case class AutoPart(ts: Option[Long] = None,
                      name: Option[String] = None,
                      autoPartId: Option[Long] = None,
                      address: Option[String] = None)


  object AutoPart {
    def empty = new AutoPart()
  }


  trait State

  trait AutoPartEntityState

  object AutoPartEntityState {

    case object INIT extends AutoPartEntityState

    case object ADDTOCART extends AutoPartEntityState

    case object REMOVEFROMCART extends AutoPartEntityState

    case object CHECKOUT extends AutoPartEntityState

    case object DELIVER extends AutoPartEntityState

    case object FINISH extends AutoPartEntityState

    case object RETURN extends AutoPartEntityState

    case object CLOSE extends AutoPartEntityState

  }

  case class StateHolder(content: AutoPart, state: AutoPartEntityState) {

    def update(event: AutoPartEvent): StateHolder = event match {
      case evt: CreateAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            name = Some(evt.name),
            autoPartId = Some(evt.autoPartId)
          ),
          state = AutoPartEntityState.ADDTOCART
        )
      }

      case evt: AddToCartAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            autoPartId = Some(evt.autoPartId)
          ),
          state = AutoPartEntityState.CHECKOUT
        )
      }

      case evt: RemoveFromCartAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            autoPartId = Some(evt.autoPartId)
          ),
          state = AutoPartEntityState.INIT
        )
      }

      case evt: CheckoutAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            autoPartId = Some(evt.autoPartId)
          ),
          state = AutoPartEntityState.DELIVER
        )
      }

      case evt: DeliverAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            autoPartId = Some(evt.autoPartId),
            address = Some(evt.address)
          ),
          state = AutoPartEntityState.FINISH
        )
      }

      case evt: FinishAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            autoPartId = Some(evt.autoPartId)
          ),
          state = AutoPartEntityState.CLOSE
        )
      }

      case evt: ReturnAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            autoPartId = Some(evt.autoPartId)
          ),
          state = AutoPartEntityState.INIT
        )
      }
    }
  }

  object StateHolder {
    def empty: StateHolder = StateHolder(content = AutoPart.empty, state = AutoPartEntityState.INIT)
  }


  val EntityKey: EntityTypeKey[AutoPartCommand] = EntityTypeKey[AutoPartCommand]("AutoPart")


  def apply(AutoPartId: String): Behavior[AutoPartCommand] = {
    EventSourcedBehavior[AutoPartCommand, AutoPartEvent, StateHolder](
      persistenceId = PersistenceId(EntityKey.name, AutoPartId),
      StateHolder.empty,
      (state, command) => commandHandler(AutoPartId, state, command),
      (state, event) => handleEvent(state, event)
    )
  }


  def commandHandler(AutoPartId: String, state: StateHolder, command: AutoPartCommand): Effect[AutoPartEvent, StateHolder] = {
    command match {
      case cmd: CreateAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.INIT => {

            val evt = CreateAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId,
              name = cmd.name
            )

            Effect.persist(evt)
          }

          case _ => throw new RuntimeException("Error")
        }
      }

      case cmd: AddToCartAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.ADDTOCART => {

            val evt = AddToCartAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId
            )

            Effect.persist(evt)
          }

          case _ => throw new RuntimeException("Error")
        }
      }

      case cmd: RemoveFromCartAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.CHECKOUT => {

            val evt = RemoveFromCartAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId
            )

            Effect.persist(evt)
          }

          case _ => throw new RuntimeException("Error")
        }
      }

      case cmd: CheckoutAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.CHECKOUT => {

            val evt = CheckoutAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId
            )

            Effect.persist(evt)
          }

          case _ => throw new RuntimeException("Error")
        }
      }

      case cmd: DeliverAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.DELIVER => {

            val evt = DeliverAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId,
              address = cmd.address
            )

            Effect.persist(evt)
          }

          case _ => throw new RuntimeException("Error")
        }
      }

      case cmd: FinishAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.FINISH => {

            val evt = FinishAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId
            )

            Effect.persist(evt)
          }

          case _ => throw new RuntimeException("Error")
        }
      }

      case cmd: ReturnAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.CLOSE => {

            val evt = ReturnAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId
            )

            Effect.persist(evt)
          }

          case _ => throw new RuntimeException("Error")
        }
      }
    }
  }


  def handleEvent(state: StateHolder, event: AutoPartEvent): StateHolder = {
    state.update(event)
  }
}