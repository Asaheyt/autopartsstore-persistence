package kz.asaheyt.inc.autopartsstore.persistence.serializable.proto

import akka.serialization.SerializerWithStringManifest
import kz.asaheyt.inc.autopartsstore.persistence.event.proto.{CreateAutoPartEventV1, CheckoutAutoPartEventV1,
  PayAutoPartEventV1, DeliverAutoPartEventV1, ReturnAutoPartEventV1, ExamineAutoPartEventV1, ShowcaseAutoPartEventV1}

class AutoPartEventSerializer extends SerializerWithStringManifest {

  final val CreateAutoPartEventManifestV1: String = classOf[CreateAutoPartEventV1].getName
  final val CheckoutAutoPartEventManifestV1: String = classOf[CheckoutAutoPartEventV1].getName
  final val PayAutoPartEventManifestV1: String = classOf[PayAutoPartEventV1].getName
  final val DeliverAutoPartEventManifestV1: String = classOf[DeliverAutoPartEventV1].getName
  final val ReturnAutoPartEventManifestV1: String = classOf[ReturnAutoPartEventV1].getName
  final val ExamineAutoPartEventManifestV1: String = classOf[ExamineAutoPartEventV1].getName
  final val ShowcaseAutoPartEventManifestV1: String = classOf[ShowcaseAutoPartEventV1].getName

  override def identifier: Int = 1000

  override def manifest(o: AnyRef): String = o.getClass.getName

  override def toBinary(o: AnyRef): Array[Byte] = o match {
    case evt: CreateAutoPartEventV1 => evt.toByteArray
    case evt: CheckoutAutoPartEventV1 => evt.toByteArray
    case evt: PayAutoPartEventV1 => evt.toByteArray
    case evt: DeliverAutoPartEventV1 => evt.toByteArray
    case evt: ReturnAutoPartEventV1 => evt.toByteArray
    case evt: ExamineAutoPartEventV1 => evt.toByteArray
    case evt: CreateAutoPartEventV1 => evt.toByteArray
  }

  override def fromBinary(bytes: Array[Byte], manifest: String): AnyRef = manifest match {
    case CreateAutoPartEventManifestV1 => CreateAutoPartEventV1.parseFrom(bytes)
    case CheckoutAutoPartEventManifestV1 => CheckoutAutoPartEventV1.parseFrom(bytes)
    case PayAutoPartEventManifestV1 => PayAutoPartEventV1.parseFrom(bytes)
    case DeliverAutoPartEventManifestV1 => DeliverAutoPartEventV1.parseFrom(bytes)
    case ReturnAutoPartEventManifestV1 => ReturnAutoPartEventV1.parseFrom(bytes)
    case ExamineAutoPartEventManifestV1 => ExamineAutoPartEventV1.parseFrom(bytes)
    case ShowcaseAutoPartEventManifestV1 => ShowcaseAutoPartEventV1.parseFrom(bytes)
  }
}
