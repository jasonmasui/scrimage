/*
   Copyright 2013 Stephen K Samuel

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.sksamuel.scrimage.composite

import java.awt.Graphics2D

import com.sksamuel.scrimage.{AwtImage, Composite}
import thirdparty.romainguy.{BlendComposite, BlendingMode}

/** @author Stephen Samuel */
class BlenderComposite(mode: BlendingMode, alpha: Double) extends Composite {
  def apply(src: AwtImage, applicative: AwtImage) {
    val g2 = src.awt.getGraphics.asInstanceOf[Graphics2D]
    g2.setComposite(BlendComposite.getInstance(mode, alpha.toFloat))
    g2.drawImage(applicative.awt, 0, 0, null)
    g2.dispose()
  }
}
class AverageComposite(alpha: Double) extends BlenderComposite(BlendingMode.AVERAGE, alpha.toFloat)
class BlueComposite(alpha: Double) extends BlenderComposite(BlendingMode.BLUE, alpha.toFloat)
class ColorComposite(alpha: Double) extends BlenderComposite(BlendingMode.COLOR, alpha.toFloat)
class ColorBurnComposite(alpha: Double) extends BlenderComposite(BlendingMode.COLOR_BURN, alpha.toFloat)
class ColorDodgeComposite(alpha: Double) extends BlenderComposite(BlendingMode.COLOR_DODGE, alpha.toFloat)
class DifferenceComposite(alpha: Double) extends BlenderComposite(BlendingMode.DIFFERENCE, alpha.toFloat)
class GreenComposite(alpha: Double) extends BlenderComposite(BlendingMode.GREEN, alpha.toFloat)
class GlowComposite(alpha: Double) extends BlenderComposite(BlendingMode.GLOW, alpha.toFloat)
class HueComposite(alpha: Double) extends BlenderComposite(BlendingMode.HUE, alpha.toFloat)
class HardLightComposite(alpha: Double) extends BlenderComposite(BlendingMode.HARD_LIGHT, alpha.toFloat)
class HeatComposite(alpha: Double) extends BlenderComposite(BlendingMode.HEAT, alpha.toFloat)
class LightenComposite(alpha: Double) extends BlenderComposite(BlendingMode.LIGHTEN, alpha.toFloat)
class LuminosityComposite(alpha: Double) extends BlenderComposite(BlendingMode.LUMINOSITY, alpha.toFloat)
class MultiplyComposite(alpha: Double) extends BlenderComposite(BlendingMode.MULTIPLY, alpha.toFloat)
class NegationComposite(alpha: Double) extends BlenderComposite(BlendingMode.NEGATION, alpha.toFloat)
class OverlayComposite(alpha: Double) extends BlenderComposite(BlendingMode.OVERLAY, alpha.toFloat)
class RedComposite(alpha: Double) extends BlenderComposite(BlendingMode.RED, alpha.toFloat)
class ReflectComposite(alpha: Double) extends BlenderComposite(BlendingMode.REFLECT, alpha.toFloat)
class SaturationComposite(alpha: Double) extends BlenderComposite(BlendingMode.SATURATION, alpha.toFloat)
class ScreenComposite(alpha: Double) extends BlenderComposite(BlendingMode.SCREEN, alpha.toFloat)
class SubtractComposite(alpha: Double) extends BlenderComposite(BlendingMode.SUBTRACT, alpha.toFloat)

