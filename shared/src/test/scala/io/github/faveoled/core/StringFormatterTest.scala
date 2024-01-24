package io.github.faveoled.core

import org.scalatest.{BeforeAndAfter}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers._

class StringFormatterTest extends AnyFunSpec with BeforeAndAfter {

  describe("Clean string") {
    it("should replace ',' in string with space") {
      val string = "magni,dolores,eos,qui,ratione,voluptatem"
      val expected = "magni dolores eos qui ratione voluptatem"
      val cleanedSTring = StringFormatter.cleanString(string)
      cleanedSTring should be (expected)
    }
  }

  describe("Clean subtitle time") {
    it("should remove separator and convert time into start and end time") {
      val time = "00:00:33,599 --> 00:00:35,270"
      val cleanSubTime = StringFormatter.cleanSubTime(time)
      val expected = Array("00:00:33.599", "00:00:35.270")
      cleanSubTime should be (expected)
    }
  }

  describe("File header list") {
    it("should convert list containing user-defined headers to String") {
      val xs = List("a", "b", "c", "d")
      assert(xs.size === 4)
      val expected = "a,b,c,d"
      val header = StringFormatter.fileHeader(xs)
      header should be(expected)
    }
  }


}
