package uk.co.hexeption.sboutline.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Page;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.PageLocation;

public class SBOutlineConfig extends Config {


   // Hub ===========================

   // Crazy Witch
   // Crypt Ghoul
   // Gaia Construct
   // Golden Ghoul
   // Headless Horseman
   // Horseman Bat
   // Horseman Zombie
   // Minos Champion
   // Minos Hunter
   // Minos Inquisitor
   // Minotaur
   // Old Wolf
   // Phantom Spirit
   // Rat
   // Scary Jerry
   // Siamese Lynx
   // Trick Or Treater
   // Wither Gourd
   // Wolf
   // Wraith
   // Zombie Villager

   // General
   @Page(
           name = "Crypt Ghoul",
           location = PageLocation.TOP,
           description = "Crypt Ghoul settings",
           category = "Hub",
           subcategory = "General"
   )
   public static SBOutlineMobPage CryptGhoul = new SBOutlineMobPage();

   @Page(
           name = "Golden Ghoul",
           location = PageLocation.TOP,
           description = "Golden Ghoul settings",
           category = "Hub",
           subcategory = "General"
   )
   public static SBOutlineMobPage GoldenGhoul = new SBOutlineMobPage();

   @Page(
           name = "Zombie",
           location = PageLocation.TOP,
           description = "Zombie settings",
           category = "Hub",
           subcategory = "General"
   )
   public static SBOutlineMobPage Zombie = new SBOutlineMobPage();

   @Page(
           name = "Old Wolf",
           location = PageLocation.TOP,
           description = "Old Wolf settings",
           category = "Hub",
           subcategory = "General"
   )
   public static SBOutlineMobPage OldWolf = new SBOutlineMobPage();

   // Spooky Festival
   @Page(
           name = "Crazy Witch",
           location = PageLocation.TOP,
           description = "Crazy Witch settings",
           category = "Hub",
           subcategory = "Spooky Festival"
   )
   public static SBOutlineMobPage CrazyWitch = new SBOutlineMobPage();

   @Page(
           name = "Headless Horseman",
           location = PageLocation.TOP,
           description = "Headless Horseman settings",
           category = "Hub",
           subcategory = "Spooky Festival"
   )
   public static SBOutlineMobPage HeadlessHorseman = new SBOutlineMobPage();

   @Page(
           name = "Horseman Bat",
           location = PageLocation.TOP,
           description = "Horseman Bat settings",
           category = "Hub",
           subcategory = "Spooky Festival"
   )
   public static SBOutlineMobPage HorsemanBat = new SBOutlineMobPage();

   @Page(
           name = "Horseman Zombie",
           location = PageLocation.TOP,
           description = "Horseman Zombie settings",
           category = "Hub",
           subcategory = "Spooky Festival"
   )
   public static SBOutlineMobPage HorsemanZombie = new SBOutlineMobPage();

   // Mythological
   @Page(
           name = "Gaia Construct",
           location = PageLocation.TOP,
           description = "Gaia Construct settings",
           category = "Hub",
           subcategory = "Mythological"
   )
   public static SBOutlineMobPage GaiaConstruct = new SBOutlineMobPage();

   @Page(
           name = "Minos Champion",
           location = PageLocation.TOP,
           description = "Minos Champion settings",
           category = "Hub",
           subcategory = "Mythological"
   )
   public static SBOutlineMobPage MinosChampion = new SBOutlineMobPage();

   @Page(
           name = "Minos Hunter",
           location = PageLocation.TOP,
           description = "Minos Hunter settings",
           category = "Hub",
           subcategory = "Mythological"
   )
   public static SBOutlineMobPage MinosHunter = new SBOutlineMobPage();

   @Page(
           name = "Minos Inquisitor",
           location = PageLocation.TOP,
           description = "Minos Inquisitor settings",
           category = "Hub",
           subcategory = "Mythological"
   )
   public static SBOutlineMobPage MinosInquisitor = new SBOutlineMobPage();

   @Page(
           name = "Minotaur",
           location = PageLocation.TOP,
           description = "Minotaur settings",
           category = "Hub",
           subcategory = "Mythological"
   )
   public static SBOutlineMobPage Minotaur = new SBOutlineMobPage();

   // Spider's Den
   @Page(
           name = "Rain Slime",
           location = PageLocation.TOP,
           description = "Rain Slime settings",
           category = "Spider's Den"
   )
   public static SBOutlineMobPage RainSpider = new SBOutlineMobPage();

   @Page(
           name = "[Lv 300] Arachne",
           location = PageLocation.TOP,
           description = "[Lv 300] Arachne settings",
           category = "Spider's Den",
           subcategory = "Boss"
   )
   public static SBOutlineMobPage Lv300_Arachne = new SBOutlineMobPage();

   @Page(
           name = "[Lv 500] Arachne",
           location = PageLocation.TOP,
           description = "[Lv 500] Arachne settings",
           category = "Spider's Den",
           subcategory = "Boss"
   )
   public static SBOutlineMobPage Lv500_Arachne = new SBOutlineMobPage();

   public SBOutlineConfig() {
      super(new Mod("SBOutline", ModType.SKYBLOCK), "sboutline.json");
      initialize();
   }

}
