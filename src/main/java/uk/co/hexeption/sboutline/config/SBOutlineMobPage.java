package uk.co.hexeption.sboutline.config;

import cc.polyfrost.oneconfig.config.annotations.Color;
import cc.polyfrost.oneconfig.config.annotations.Number;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.core.OneColor;

public class SBOutlineMobPage {

   @Switch(
           name = "Enable"
   )
   public boolean enable = false;

   @Color(
           name = "Box Color"
   )
   public OneColor boxColor = new OneColor(26, 35, 143, 120);


   @Switch(
           name = "Outline Box",
           category = "Outline"
   )
   public boolean outlineBox = false;

   @Color(
           name = "Outline Color",
           category = "Outline"
   )
   public OneColor outlineColor = new OneColor("D9D627");

   @Number(
           name = "Outline Width",
           category = "Outline",
           min = 1,
           max = 10
   )
   public float outlineWidth = 1;


}