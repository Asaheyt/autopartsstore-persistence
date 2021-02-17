package kz.asaheyt.inc.autopartsstore.persistence.command

trait AutoPartCommand {
  def ts: Long
  def autoPartId: Long
}

case class CreateAutoPartCommand(ts: Long,
                                 autoPartId: Long,
                                 name: String) extends AutoPartCommand

case class AddToCartAutoPartCommand(ts: Long,
                                    autoPartId: Long) extends AutoPartCommand

case class RemoveFromCartAutoPartCommand(ts: Long,
                                         autoPartId: Long) extends AutoPartCommand

case class CheckoutAutoPartCommand(ts: Long,
                                   autoPartId: Long) extends AutoPartCommand

case class DeliverAutoPartCommand(ts: Long,
                                  autoPartId: Long,
                                  address: String) extends AutoPartCommand

case class FinishAutoPartCommand(ts: Long,
                                 autoPartId: Long) extends AutoPartCommand

case class ReturnAutoPartCommand(ts: Long,
                                 autoPartId: Long) extends AutoPartCommand