package uk.co.hexeption.sboutline;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import uk.co.hexeption.sboutline.config.SBOutlineConfig;
import uk.co.hexeption.sboutline.config.SBOutlineMobPage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Mod(modid = SBOutline.MOD_ID, version = SBOutline.VERSION, name = "SBOutline", clientSideOnly = true)
public class SBOutline {

   public static final String MOD_ID = "sboutline";
   public static final String VERSION = "1.0.0";

   public static final Logger LOGGER = LogManager.getLogger(SBOutline.class);

   public static SBOutlineConfig config;

   @Mod.EventHandler
   public void onFMLInitialization(FMLInitializationEvent event) {
      MinecraftForge.EVENT_BUS.register(this);
      config = new SBOutlineConfig();
   }

   @Mod.EventHandler
   public void onFMLPreInitialization(FMLPreInitializationEvent event) {
      LOGGER.info("SBOutline loading...");
   }

   enum Mobs {
      CRYPT_GHOUL("Crypt Ghoul", ".*Lv30.* .*Crypt.* Ghoul.*", SBOutlineConfig.CryptGhoul),
      GOLDEN_GHOUL("Golden Ghoul", ".*Lv60.* .*Golden.* Ghoul.*", SBOutlineConfig.GoldenGhoul),
      OLD_WOLF("Old Wolf", "", SBOutlineConfig.OldWolf),
      ZOMBIE("Zombie", ".*Lv1.* .*Zombie.*", SBOutlineConfig.Zombie),
      ARACHNE_lvl_300("Lvl 300 Arachne", ".*Lv300.* .*Arachne.*", SBOutlineConfig.Lv300_Arachne),
      ARACHNE_lvl_500("Lvl 500 Arachne", ".*Lv500.* .*Arachne.*", SBOutlineConfig.Lv500_Arachne),
      ;

      private String name;
      private String regexName;
      private SBOutlineMobPage options;

      Mobs(String name, String regexName, SBOutlineMobPage options) {
         this.name = name;
         this.regexName = regexName;
         this.options = options;
      }

      public String getName() {
         return name;
      }

      public String getRegexName() {
         return regexName;
      }

      public SBOutlineMobPage getOptions() {
         return options;
      }
   }

   private Map<Integer, Mobs> entities = new HashMap<>();

   @SubscribeEvent
   public void onRenderWorldLast(RenderWorldLastEvent event) {

      Minecraft.getMinecraft().thePlayer.worldObj.loadedEntityList.forEach(entity -> {
         if (entity != Minecraft.getMinecraft().thePlayer) {
            if (entity.getEntityBoundingBox() != null) {
               if (Minecraft.getMinecraft().thePlayer.canEntityBeSeen(entity)) {
                  for (Mobs mob : Mobs.values()) {
                     if (entity.getName().matches(mob.getRegexName())) {
                        if (mob.getOptions().enable) {
                           entities.put(entity.getEntityId() - 1, mob);
                        }
                     }
                  }
               }
            }
         }
      });

      Minecraft.getMinecraft().thePlayer.worldObj.loadedEntityList.forEach(entity -> {
         if (entities.containsKey(entity.getEntityId())) {
            AxisAlignedBB bb = entity.getEntityBoundingBox();

            Mobs mob = entities.get(entity.getEntityId());

            if (entities.get(entity.getEntityId()) == mob) {
               renderBox(bb, mob.getOptions().boxColor.toJavaColor(), mob.getOptions().outlineBox, mob.getOptions().outlineColor.toJavaColor(), mob.getOptions().outlineWidth, event.partialTicks);
            }

            entities.remove(entity.getEntityId());
         }
      });
   }

   public static void renderBox(AxisAlignedBB axisAlignedBB, Color color, boolean outline, Color outlineColor, float outlineWidth, float ticks) {

      Entity viewEntity = Minecraft.getMinecraft().getRenderViewEntity();

      double x_view = viewEntity.lastTickPosX + (viewEntity.posX - viewEntity.lastTickPosX) * ticks;
      double y_view = viewEntity.lastTickPosY + (viewEntity.posY - viewEntity.lastTickPosY) * ticks;
      double z_view = viewEntity.lastTickPosZ + (viewEntity.posZ - viewEntity.lastTickPosZ) * ticks;

      GlStateManager.pushMatrix();

      GlStateManager.translate(-x_view, -y_view, -z_view);

      GlStateManager.disableLighting();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.disableTexture2D();

      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);


      int rgb = color.getRGB();
      GlStateManager.color((rgb >> 16 & 255) / 255.0F, (rgb >> 8 & 255) / 255.0F, (rgb & 255) / 255.0F, (rgb >> 24 & 255) / 255.0F);

      GlStateManager.translate(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);

      double x = axisAlignedBB.maxX - axisAlignedBB.minX;
      double y = axisAlignedBB.maxY - axisAlignedBB.minY;
      double z = axisAlignedBB.maxZ - axisAlignedBB.minZ;

      GL11.glBegin(GL11.GL_QUADS);
      GL11.glVertex3d(0, 0, 0);
      GL11.glVertex3d(0, 0, z);
      GL11.glVertex3d(0, y, z);
      GL11.glVertex3d(0, y, 0);

      GL11.glVertex3d(x, 0, z);
      GL11.glVertex3d(x, 0, 0);
      GL11.glVertex3d(x, y, 0);
      GL11.glVertex3d(x, y, z);

      GL11.glVertex3d(0, y, z);
      GL11.glVertex3d(0, 0, z);
      GL11.glVertex3d(x, 0, z);
      GL11.glVertex3d(x, y, z);

      GL11.glVertex3d(0, 0, 0);
      GL11.glVertex3d(0, y, 0);
      GL11.glVertex3d(x, y, 0);
      GL11.glVertex3d(x, 0, 0);

      GL11.glVertex3d(0, y, 0);
      GL11.glVertex3d(0, y, z);
      GL11.glVertex3d(x, y, z);
      GL11.glVertex3d(x, y, 0);

      GL11.glVertex3d(0, 0, z);
      GL11.glVertex3d(0, 0, 0);
      GL11.glVertex3d(x, 0, 0);
      GL11.glVertex3d(x, 0, z);
      GL11.glEnd();

      if (outline) {
         // Create a black outline around the box
         int outline_rgb = outlineColor.getRGB();
         GlStateManager.color((outline_rgb >> 16 & 255) / 255.0F, (outline_rgb >> 8 & 255) / 255.0F, (outline_rgb & 255) / 255.0F, (outline_rgb >> 24 & 255) / 255.0F);
         GL11.glLineWidth(outlineWidth);
         GL11.glBegin(GL11.GL_LINE_LOOP);
         GL11.glVertex3d(0, 0, 0);
         GL11.glVertex3d(0, 0, z);
         GL11.glVertex3d(0, y, z);
         GL11.glVertex3d(0, y, 0);
         GL11.glEnd();

         GL11.glBegin(GL11.GL_LINE_LOOP);
         GL11.glVertex3d(x, 0, z);
         GL11.glVertex3d(x, 0, 0);
         GL11.glVertex3d(x, y, 0);
         GL11.glVertex3d(x, y, z);
         GL11.glEnd();

         GL11.glBegin(GL11.GL_LINE_LOOP);
         GL11.glVertex3d(0, y, z);
         GL11.glVertex3d(0, 0, z);
         GL11.glVertex3d(x, 0, z);
         GL11.glVertex3d(x, y, z);
         GL11.glEnd();

         GL11.glBegin(GL11.GL_LINE_LOOP);
         GL11.glVertex3d(0, 0, 0);
         GL11.glVertex3d(0, y, 0);
         GL11.glVertex3d(x, y, 0);
         GL11.glVertex3d(x, 0, 0);
         GL11.glEnd();

         GL11.glBegin(GL11.GL_LINE_LOOP);
         GL11.glVertex3d(0, y, 0);
         GL11.glVertex3d(0, y, z);
         GL11.glVertex3d(x, y, z);
         GL11.glVertex3d(x, y, 0);
         GL11.glEnd();

         GL11.glBegin(GL11.GL_LINE_LOOP);
         GL11.glVertex3d(0, 0, z);
         GL11.glVertex3d(0, 0, 0);
         GL11.glVertex3d(x, 0, 0);
         GL11.glVertex3d(x, 0, z);
         GL11.glEnd();
      }

      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GlStateManager.enableTexture2D();
      GlStateManager.enableLighting();
      GlStateManager.popMatrix();
   }
}
