package com.sksamuel.scrimage

import org.scalatest.{BeforeAndAfter, FunSuite}
import java.awt.image.BufferedImage

/** @author Stephen Samuel */
class ImageTest extends FunSuite with BeforeAndAfter {

    val in = getClass.getResourceAsStream("/bird.jpg")
    val image = Image(in)

    test("ratio happy path") {
        val awt1 = new BufferedImage(200, 400, BufferedImage.TYPE_INT_ARGB)
        assert(0.5 === Image(awt1).ratio)

        val awt2 = new BufferedImage(400, 200, BufferedImage.TYPE_INT_ARGB)
        assert(2 === Image(awt2).ratio)

        val awt3 = new BufferedImage(333, 333, BufferedImage.TYPE_INT_ARGB)
        assert(1 === Image(awt3).ratio)

        val awt4 = new BufferedImage(333, 111, BufferedImage.TYPE_INT_ARGB)
        assert(3.0 === Image(awt4).ratio)

        val awt5 = new BufferedImage(111, 333, BufferedImage.TYPE_INT_ARGB)
        assert(1 / 3d === Image(awt5).ratio)
    }

    test("when scaling by pixels then the output image has the given dimensions") {
        val scaled = image.scale(40, 50)
        assert(40 === scaled.width)
        assert(50 === scaled.height)
    }

    test("when scaling by scale factor then the output image has the scaled dimensions") {
        val scaled = image.scale(0.5)
        assert(972 === scaled.width)
        assert(648 === scaled.height)
    }

    test("when resizing by pixels then the output image has the given dimensions") {
        val scaled = image.resize(440, 505)
        assert(440 === scaled.width)
        assert(505 === scaled.height)
    }

    test("when resizing by scale factor then the output image has the scaled dimensions") {
        val scaled = image.resize(0.5)
        assert(972 === scaled.width)
        assert(648 === scaled.height)
    }

    test("dimensions happy path") {
        assert(1944 === image.width)
        assert(1296 === image.height)
    }

    test("pixel happy path") {
        assert(-1 === image.pixel(0, 0))
        assert(-1 === image.pixel(100, 100))
    }

    test("when creating a blank filled image the dimensions are as specified") {
        val image = Image.filled(595, 911, Color.Black)
        assert(595 === image.width)
        assert(911 === image.height)
    }

    test("when creating a filled copy then the dimensions are as specified") {
        val image = Image.empty(80, 90)
        assert(80 === image.width)
        assert(90 === image.height)
    }

    test("when padding to a width smaller than the image width then the width is not reduced") {
        val image = Image.empty(85, 56)
        val padded = image.pad(55, 162)
        assert(85 === padded.width)
    }

    test("when padding to a height smaller than the image height then the height is not reduced") {
        val image = Image.empty(85, 56)
        val padded = image.pad(90, 15)
        assert(56 === padded.height)
    }

    test("when padding to a width larger than the image width then the width is increased") {
        val image = Image.empty(85, 56)
        val padded = image.pad(151, 162)
        assert(151 === padded.width)
    }

    test("when padding to a height larger than the image height then the height is increased") {
        val image = Image.empty(85, 56)
        val padded = image.pad(90, 77)
        assert(77 === padded.height)
    }

    test("when padding to a size larger than the image then the image canvas is increased") {
        val image = Image.empty(85, 56)
        val padded = image.pad(515, 643)
        assert(515 === padded.width)
        assert(643 === padded.height)
    }

    test("when padding with a border size then the width and height are increased by the right amount") {
        val image = Image.empty(85, 56)
        val padded = image.pad(4)
        assert(93 === padded.width)
        assert(64 === padded.height)
    }
}
