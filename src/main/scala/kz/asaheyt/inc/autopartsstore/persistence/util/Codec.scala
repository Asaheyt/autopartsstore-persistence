package kz.asaheyt.inc.autopartsstore.persistence.util

import kz.asaheyt.inc.autopartsstore.persistence.model.{AutoPartDTO, AutoPartSummary}
import io.circe.{Decoder, Encoder}
import cats.syntax.functor._
import io.circe.generic.auto._
import io.circe.syntax._
import org.joda.time.DateTime

trait Codec {

  implicit val autoPartCreateEncodeDecode: EncoderDecoder[AutoPartDTO] = DerivedEncoderDecoder[AutoPartDTO]

  implicit val datetimeED: EncoderDecoder[DateTime] = ContainerEncoderDecoder[DateTime, String](DateTime.parse, _.toString)

  implicit val autoPartSummaryEncodeDecode: EncoderDecoder[AutoPartSummary] = DerivedEncoderDecoder[AutoPartSummary]
}
