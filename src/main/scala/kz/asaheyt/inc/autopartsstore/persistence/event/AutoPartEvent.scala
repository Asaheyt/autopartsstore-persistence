package kz.asaheyt.inc.autopartsstore.persistence.event


trait AutoPartEvent

case class CreateAutoPartEvent(ts: Long,
                                 autoPartId: Long,
                                 name: String) extends AutoPartEvent

case class AddToCartAutoPartEvent(ts: Long,
                                    autoPartId: Long) extends AutoPartEvent

case class RemoveFromCartAutoPartEvent(ts: Long,
                                         autoPartId: Long) extends AutoPartEvent

case class CheckoutAutoPartEvent(ts: Long,
                                   autoPartId: Long) extends AutoPartEvent

case class DeliverAutoPartEvent(ts: Long,
                                  autoPartId: Long,
                                  address: String) extends AutoPartEvent

case class FinishAutoPartEvent(ts: Long,
                                 autoPartId: Long) extends AutoPartEvent

case class ReturnAutoPartEvent(ts: Long,
                                 autoPartId: Long) extends AutoPartEvent