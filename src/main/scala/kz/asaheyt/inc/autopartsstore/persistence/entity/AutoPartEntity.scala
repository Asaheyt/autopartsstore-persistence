package kz.asaheyt.inc.autopartsstore.persistence.entity

import akka.actor.typed.Behavior
import akka.cluster.sharding.typed.scaladsl.EntityTypeKey
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.{Effect, EventSourcedBehavior, RetentionCriteria}
import kz.asaheyt.inc.autopartsstore.persistence.adapter.AutoPartEventAdapter
import kz.asaheyt.inc.autopartsstore.persistence.command.{AutoPartCommand, CheckoutAutoPartCommand, CreateAutoPartCommand, DeliverAutoPartCommand, ExamineAutoPartCommand, PayAutoPartCommand, ReturnAutoPartCommand, ShowcaseAutoPartCommand}
import kz.asaheyt.inc.autopartsstore.persistence.event.{AutoPartEvent, CheckoutAutoPartEvent, CreateAutoPartEvent, DeliverAutoPartEvent, ExamineAutoPartEvent, PayAutoPartEvent, ReturnAutoPartEvent, ShowcaseAutoPartEvent}

object AutoPartEntity {

  case class AutoPart(ts: Option[Long] = None,
                      name: Option[String] = None,
                      autoPartId: Option[Long] = None,
                      address: Option[String] = None,
                      price: Option[Double] = None,
                      quantity: Option[Int] = None,
                      customerId: Option[Long] = None,
                      totalPrice: Option[Double] = None)


  object AutoPart {
    def empty = new AutoPart()
  }


  trait State

  trait AutoPartEntityState

  object AutoPartEntityState {

    case object INIT extends AutoPartEntityState

    case object CHECKOUT extends AutoPartEntityState

    case object PAYMENT extends AutoPartEntityState

    case object DELIVER extends AutoPartEntityState

    case object CLOSE extends AutoPartEntityState

    case object RETURN extends AutoPartEntityState

    case object EXAMINE extends AutoPartEntityState

    case object SHOWCASE extends AutoPartEntityState

    case object FORSALE extends AutoPartEntityState

  }

  case class StateHolder(content: AutoPart, state: AutoPartEntityState) {

    def update(event: AutoPartEvent): StateHolder = event match {
      case evt: CreateAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            name = Some(evt.name),
            autoPartId = Some(evt.autoPartId),
            quantity = Some(evt.quantity)
          ),
          state = AutoPartEntityState.CHECKOUT
        )
      }

      case evt: CheckoutAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            name = Some(evt.name),
            autoPartId = Some(evt.autoPartId),
            quantity = Some(evt.quantity),
            customerId = Some(evt.customerId)
          ),
          state = AutoPartEntityState.PAYMENT
        )
      }

      case evt: PayAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            name = Some(evt.name),
            autoPartId = Some(evt.autoPartId),
            quantity = Some(evt.quantity),
            customerId = Some(evt.customerId),
            price = Some(evt.price),
            totalPrice = Some(evt.totalPrice)
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
          state = AutoPartEntityState.CLOSE
        )
      }

      case evt: ReturnAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            autoPartId = Some(evt.autoPartId)
          ),
          state = AutoPartEntityState.EXAMINE
        )
      }

      case evt: ExamineAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            autoPartId = Some(evt.autoPartId)
          ),
          state = AutoPartEntityState.SHOWCASE
        )
      }

      case evt: ShowcaseAutoPartEvent => {
        copy(
          content = content.copy(
            ts = Some(evt.ts),
            autoPartId = Some(evt.autoPartId)
          ),
          state = AutoPartEntityState.FORSALE
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
      ).withTagger(_ => eventProcessorTag)
        .withRetention(RetentionCriteria.snapshotEvery(numberOfEvents = 10, keepNSnapshots = 2))
        .eventAdapter(new AutoPartEventAdapter)

  }


  def commandHandler(AutoPartId: String, state: StateHolder, command: AutoPartCommand): Effect[AutoPartEvent, StateHolder] = {
    command match {
      case cmd: CreateAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.INIT => {

            val evt = CreateAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId,
              name = cmd.name,
              quantity = cmd.quantity
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
              autoPartId = cmd.autoPartId,
              name = cmd.name,
              quantity = cmd.quantity,
              customerId = cmd.customerId
            )

            Effect.persist(evt)
          }

          case AutoPartEntityState.FORSALE => {

            val evt = CheckoutAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId,
              name = cmd.name,
              quantity = cmd.quantity,
              customerId = cmd.customerId
            )

            Effect.persist(evt)
          }

          case _ => throw new RuntimeException("Error")
        }
      }

      case cmd: PayAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.PAYMENT => {

            val evt = PayAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId,
              name = cmd.name,
              quantity = cmd.quantity,
              customerId = cmd.customerId,
              price = cmd.price,
              totalPrice = cmd.totalPrice
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

      case cmd: ExamineAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.EXAMINE => {

            val evt = ExamineAutoPartEvent(
              ts = cmd.ts,
              autoPartId = cmd.autoPartId
            )

            Effect.persist(evt)
          }

          case _ => throw new RuntimeException("Error")
        }
      }

      case cmd: ShowcaseAutoPartCommand => {
        state.state match {
          case AutoPartEntityState.SHOWCASE => {

            val evt = ShowcaseAutoPartEvent(
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