package de.leanovate.swaggercheck

import de.leanovate.swaggercheck.fixtures.model._
import de.leanovate.swaggercheck.simple.SimpleRequestCreator
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Properties}
import play.api.libs.json.Json

object ThingApiSpecification extends Properties("Thing API") {
  val swaggerChecks = SwaggerChecks(getClass.getClassLoader.getResourceAsStream("thing_api.yaml"))

  property("Service document is correctly written") = {
    val verifier = swaggerChecks.jsonVerifier("ServiceDocument")

    forAll(Arbitrary.arbitrary[ServiceDocument]) {
      serviceDocument: ServiceDocument =>
        val json = Json.stringify(Json.toJson(serviceDocument))

        verifier.verify(json)
    }
  }

  property("Thing is correctly written") = {
    val verifier = swaggerChecks.jsonVerifier("Thing")

    forAll(Arbitrary.arbitrary[Thing]) {
      thing: Thing =>
        val json = Json.stringify(Json.toJson(thing))

        verifier.verify(json)
    }
  }

  property("Thing can be correctly parsed") = forAll(swaggerChecks.jsonGenerator("Thing")) {
    json =>
      Json.parse(json).validate[Thing].isSuccess
  }

  property("Thing list is correctly written") = {
    val verifier = swaggerChecks.jsonVerifier("ThingList")

    forAll(Arbitrary.arbitrary[ThingList]) {
      thingList: ThingList =>
        val json = Json.stringify(Json.toJson(thingList))

        verifier.verify(json)
    }
  }

  property("Thing list can be correctly parsed") = forAll(swaggerChecks.jsonGenerator("ThingList")) {
    json =>
      Json.parse(json).validate[ThingList].isSuccess
  }

  property("SubBase can be written") = {
    val verifier = swaggerChecks.jsonVerifier("SubBase")

    forAll(Arbitrary.arbitrary[SubBase]) {
      subBase: SubBase =>
        val json = Json.stringify(Json.toJson(subBase))

        verifier.verify(json)
    }
  }

  property("SubBase can be parsed") = forAll(swaggerChecks.jsonGenerator("SubBase")) {
    json =>
      Json.parse(json).validate[SubBase].isSuccess
  }

  property("OtherBase can be parsed") = forAll(swaggerChecks.jsonGenerator("OtherBase")) {
    json =>
      Json.parse(json).validate[OtherBase].isSuccess
  }

  property("ThingNode can be parsed") = forAll(swaggerChecks.jsonGenerator("ThingNode")) {
    json =>
      Json.parse(json).validate[ThingNode].isSuccess
  }
}
