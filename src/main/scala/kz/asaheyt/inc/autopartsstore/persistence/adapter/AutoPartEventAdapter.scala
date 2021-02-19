package kz.asaheyt.inc.autopartsstore.persistence.adapter

import akka.persistence.typed.{EventAdapter, EventSeq}
import kz.asaheyt.inc.autopartsstore.persistence.event.{AutoPartEvent, CheckoutAutoPartEvent, CreateAutoPartEvent, DeliverAutoPartEvent, ExamineAutoPartEvent, PayAutoPartEvent, ReturnAutoPartEvent, ShowcaseAutoPartEvent}

class AutoPartEventAdapter() extends EventAdapter[AutoPartEvent, AutoPartWrapper] {

  override def toJournal(e: AutoPartEvent): AutoPartWrapper = {
    val protoEvent = e match {

      case evt: CreateAutoPartEvent => {
        CreateAutoPartEventV1(
          ts = evt.ts.toString,
          name = evt.name,
          autoPartId = evt.autoPartId.toString,
          quantity = evt.quantity.toString
        )
      }

      case evt: CheckoutAutoPartEvent => {
        CheckoutAutoPartEventV1(
          ts = evt.ts.toString,
          name = evt.name,
          autoPartId = evt.autoPartId.toString,
          quantity = evt.quantity.toString,
          customerId = evt.customerId.toString
        )
      }

      case evt: PayAutoPartEvent => {
        PayAutoPartEventV1(
          ts = evt.ts.toString,
          name = evt.name,
          autoPartId = evt.autoPartId.toString,
          quantity = evt.quantity.toString,
          customerId = evt.customerId.toString,
          price = evt.price.toString,
          totalPrice = evt.totalPrice.toString

        )
      }

      case evt: DeliverAutoPartEvent => {
        DeliverAutoPartEventV1(
          ts = evt.ts.toString,
          autoPartId = evt.autoPartId.toString,
          address = evt.address
        )
      }

      case evt: ReturnAutoPartEvent => {
        ReturnAutoPartEventV1(
          ts = evt.ts.toString,
          autoPartId = evt.autoPartId.toString
        )
      }

      case evt: ExamineAutoPartEvent => {
        ExamineAutoPartEventV1(
          ts = evt.ts.toString,
          autoPartId = evt.autoPartId.toString
        )
      }

      case evt: ShowcaseAutoPartEvent => {
        ShowcaseAutoPartEventV1(
          ts = evt.ts.toString,
          autoPartId = evt.autoPartId.toString
        )
      }

    }

    AutoPartWrapper(protoEvent)
  }

  override def manifest(event: AutoPartEvent): String = ""

  override def fromJournal(p: AutoPartWrapper, manifest: String): EventSeq[AutoPartEvent] = {
    p.event match {
      case evt: CreateAutoPartEventV1 => {
        EventSeq.single(
          CreatePostEvent(
            ts = evt.ts.toString.toLong,
            name = evt.name,
            autoPartId = evt.autoPartId.toString.toLong,
            quantity = evt.quantity.toString.toLong
          )
        )
      }

      case evt: CheckoutAutoPartEventV1 => {
        EventSeq.single(
          CheckoutAutoPartEvent(
            ts = evt.ts.toString.toLong,
            name = evt.name,
            autoPartId = evt.autoPartId,
            quantity = evt.quantity.toString.toLong,
            customerId = evt.customerId.toString.toLong
          )
        )
      }

      case evt: PayAutoPartEventV1 => {
        EventSeq.single(
          PayAutoPartEvent(
            ts = evt.ts.toString.toLong,
            name = evt.name,
            autoPartId = evt.autoPartId,
            quantity = evt.quantity.toString.toLong,
            customerId = evt.customerId.toString.toLong,
            price = evt.price.toString.toLong,
            totalPrice = evt.totalPrice.toString.toLong
          )
        )
      }

      case evt: DeliverAutoPartEventV1 => {
        EventSeq.single(
          DeliverAutoPartEvent(
            ts = evt.ts.toString.toLong,
            autoPartId = evt.autoPartId.toString.toLong,
            address = evt.address
          )
        )
      }

      case evt: ReturnAutoPartEventV1 => {
        EventSeq.single(
          ReturnAutoPartEvent(
            ts = evt.ts.toString.toLong,
            autoPartId = evt.autoPartId.toString.toLong
          )
        )
      }

      case evt: ExamineAutoPartEventV1 => {
        EventSeq.single(
          ExamineAutoPartEvent(
            ts = evt.ts.toString.toLong,
            autoPartId = evt.autoPartId.toString.toLong
          )
        )
      }

      case evt: ShowcaseAutoPartEventV1 => {
        EventSeq.single(
          ShowcaseAutoPartEvent(
            ts = evt.ts.toString.toLong,
            autoPartId = evt.autoPartId.toString.toLong
          )
        )
      }
    }
  }
}
