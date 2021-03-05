package kz.asaheyt.inc.autopartsstore.persistence.model

case class AutoPartDTO(ts: Long,
                       name: String,
                       autoPartId: Long,
                       address: String,
                       price: Double,
                       quantity: Int,
                       customerId: Long,
                       totalPrice: Double)
