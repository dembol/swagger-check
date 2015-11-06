package de.leanovate.swaggercheck.model

import com.fasterxml.jackson.core.JsonGenerator

/**
  * Json number.
  *
  * @param min optional minimum for shrinking
  * @param value the number value
  */
case class JsNumber(
                     min: Option[BigDecimal],
                     value: BigDecimal
                   ) extends JsValue {
  override def generate(json: JsonGenerator): Unit = json.writeNumber(value.underlying())
}

object JsNumber {
  def fixed(value: BigDecimal) = JsNumber(Some(value),value)
}