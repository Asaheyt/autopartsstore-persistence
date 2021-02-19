package kz.asaheyt.inc.autopartsstore.persistence.event


trait AutoPartEvent

case class CreateAutoPartEvent(ts: Long,
                               autoPartId: Long,
                               name: String,
                               quantity: Int) extends AutoPartEvent

case class CheckoutAutoPartEvent(ts: Long,
                                 autoPartId: Long,
                                 name: String,
                                 quantity: Int,
                                 customerId: Long) extends AutoPartEvent

case class PayAutoPartEvent(ts: Long,
                            autoPartId: Long,
                            name: String,
                            quantity: Int,
                            customerId: Long,
                            price: Double,
                            totalPrice: Double) extends AutoPartEvent

case class DeliverAutoPartEvent(ts: Long,
                                autoPartId: Long,
                                address: String) extends AutoPartEvent

case class ReturnAutoPartEvent(ts: Long,
                               autoPartId: Long) extends AutoPartEvent

case class ExamineAutoPartEvent(ts: Long,
                                autoPartId: Long) extends AutoPartEvent

case class ShowcaseAutoPartEvent(ts: Long,
                                 autoPartId: Long) extends AutoPartEvent