package io.github.faveoled.core

import org.scalatest.{BeforeAndAfter}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers._

import java.nio.file.{Files, Paths}
import org.scalatest.PrivateMethodTester._

class SRTReaderTest extends AnyFunSpec with BeforeAndAfter {

  var reader: SRTReader = _
  val filePath = "resources/data.srt"
  val characterLen = 4

  before {
    reader = new SRTReader
  }

  describe("file reader") {

    /*it("should accept valid file path") {
      val path = Paths.get(getClass.getClassLoader.getResource(filePath).getFile)
      val file = Files.exists(path)
      file should be (true)
      pending
    }

    it("should return non-empty list") {
      val line = PrivateMethod[List[String]](Symbol("lineReader"))
      val r = reader invokePrivate line(getClass.getClassLoader.getResource(filePath).getFile)
      r.length should be > 0
      pending
    }*/

    it("should convert list of strings to typed list") {
      val list = List("1***00:00:33.599 --> 00:00:35.270***Soy Amelia Folch.")
      val expected = List(SRT(1, "00:00:33.599", "00:00:35.270", List("Soy Amelia Folch.")))
      val typedSubtitle = reader.convert2Type(list)
      typedSubtitle should equal(expected)
    }

  }

  describe("hash delimiter") {
    it("should generate string with specified length") {
      val expected = "####"
      reader.setHashDelim(characterLen) should be (expected)
    }
  }

  describe("star delimiter") {
    it("should generate string with specified length") {
      val expected = "****"
      reader.setStarDelim(characterLen) should be (expected)
    }
  }

}
