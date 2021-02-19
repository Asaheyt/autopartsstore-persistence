package kz.asaheyt.inc.autopartsstore.persistence.command

trait AutoPartCommand {
  def ts: Long

  def autoPartId: Long
}

case class CreateAutoPartCommand(ts: Long,
                                 autoPartId: Long,
                                 name: String,
                                 quantity: Int) extends AutoPartCommand

case class CheckoutAutoPartCommand(ts: Long,
                                   autoPartId: Long,
                                   name: String,
                                   quantity: Int,
                                   customerId: Long) extends AutoPartCommand

case class PayAutoPartCommand(ts: Long,
                              autoPartId: Long,
                              name: String,
                              quantity: Int,
                              customerId: Long,
                              price: Double,
                              totalPrice: Double) extends AutoPartCommand

case class DeliverAutoPartCommand(ts: Long,
                                  autoPartId: Long,
                                  address: String) extends AutoPartCommand

case class ReturnAutoPartCommand(ts: Long,
                                 autoPartId: Long) extends AutoPartCommand

case class ExamineAutoPartCommand(ts: Long,
                                  autoPartId: Long) extends AutoPartCommand

case class ShowcaseAutoPartCommand(ts: Long,
                                   autoPartId: Long) extends AutoPartCommand