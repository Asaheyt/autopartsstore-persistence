// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package kz.asaheyt.inc.autopartsstore.persistence.event.proto

@SerialVersionUID(0L)
final case class CreateAutoPartEventV1(
    ts: _root_.scala.Predef.String = "",
    name: _root_.scala.Predef.String = "",
    autoPartId: _root_.scala.Predef.String = "",
    quantity: _root_.scala.Predef.String = "",
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[CreateAutoPartEventV1] with kz.asaheyt.inc.autopartsstore.persistence.model.protobuf.AutoPartProtoEvent {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = ts
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(1, __value)
        }
      };
      
      {
        val __value = name
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(2, __value)
        }
      };
      
      {
        val __value = autoPartId
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(3, __value)
        }
      };
      
      {
        val __value = quantity
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(4, __value)
        }
      };
      __size += unknownFields.serializedSize
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var read = __serializedSizeCachedValue
      if (read == 0) {
        read = __computeSerializedValue()
        __serializedSizeCachedValue = read
      }
      read
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      {
        val __v = ts
        if (!__v.isEmpty) {
          _output__.writeString(1, __v)
        }
      };
      {
        val __v = name
        if (!__v.isEmpty) {
          _output__.writeString(2, __v)
        }
      };
      {
        val __v = autoPartId
        if (!__v.isEmpty) {
          _output__.writeString(3, __v)
        }
      };
      {
        val __v = quantity
        if (!__v.isEmpty) {
          _output__.writeString(4, __v)
        }
      };
      unknownFields.writeTo(_output__)
    }
    def withTs(__v: _root_.scala.Predef.String): CreateAutoPartEventV1 = copy(ts = __v)
    def withName(__v: _root_.scala.Predef.String): CreateAutoPartEventV1 = copy(name = __v)
    def withAutoPartId(__v: _root_.scala.Predef.String): CreateAutoPartEventV1 = copy(autoPartId = __v)
    def withQuantity(__v: _root_.scala.Predef.String): CreateAutoPartEventV1 = copy(quantity = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = ts
          if (__t != "") __t else null
        }
        case 2 => {
          val __t = name
          if (__t != "") __t else null
        }
        case 3 => {
          val __t = autoPartId
          if (__t != "") __t else null
        }
        case 4 => {
          val __t = quantity
          if (__t != "") __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PString(ts)
        case 2 => _root_.scalapb.descriptors.PString(name)
        case 3 => _root_.scalapb.descriptors.PString(autoPartId)
        case 4 => _root_.scalapb.descriptors.PString(quantity)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1
}

object CreateAutoPartEventV1 extends scalapb.GeneratedMessageCompanion[kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1] = this
  def merge(`_message__`: kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1, `_input__`: _root_.com.google.protobuf.CodedInputStream): kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1 = {
    var __ts = `_message__`.ts
    var __name = `_message__`.name
    var __autoPartId = `_message__`.autoPartId
    var __quantity = `_message__`.quantity
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __ts = _input__.readStringRequireUtf8()
        case 18 =>
          __name = _input__.readStringRequireUtf8()
        case 26 =>
          __autoPartId = _input__.readStringRequireUtf8()
        case 34 =>
          __quantity = _input__.readStringRequireUtf8()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder(_message__.unknownFields)
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1(
        ts = __ts,
        name = __name,
        autoPartId = __autoPartId,
        quantity = __quantity,
        unknownFields = if (_unknownFields__ == null) _message__.unknownFields else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage == scalaDescriptor), "FieldDescriptor does not match message type.")
      kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1(
        ts = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        name = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        autoPartId = __fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        quantity = __fieldsMap.get(scalaDescriptor.findFieldByNumber(4).get).map(_.as[_root_.scala.Predef.String]).getOrElse("")
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = AutoPartEventProto.javaDescriptor.getMessageTypes.get(0)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = AutoPartEventProto.scalaDescriptor.messages(0)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1(
    ts = "",
    name = "",
    autoPartId = "",
    quantity = ""
  )
  implicit class CreateAutoPartEventV1Lens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1](_l) {
    def ts: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.ts)((c_, f_) => c_.copy(ts = f_))
    def name: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.name)((c_, f_) => c_.copy(name = f_))
    def autoPartId: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.autoPartId)((c_, f_) => c_.copy(autoPartId = f_))
    def quantity: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.quantity)((c_, f_) => c_.copy(quantity = f_))
  }
  final val TS_FIELD_NUMBER = 1
  final val NAME_FIELD_NUMBER = 2
  final val AUTOPARTID_FIELD_NUMBER = 3
  final val QUANTITY_FIELD_NUMBER = 4
  def of(
    ts: _root_.scala.Predef.String,
    name: _root_.scala.Predef.String,
    autoPartId: _root_.scala.Predef.String,
    quantity: _root_.scala.Predef.String
  ): _root_.kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1 = _root_.kz.asaheyt.inc.autopartsstore.persistence.event.proto.CreateAutoPartEventV1(
    ts,
    name,
    autoPartId,
    quantity
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[CreateAutoPartEventV1])
}
