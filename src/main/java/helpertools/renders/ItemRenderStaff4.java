package helpertools.renders;

import helpertools.models.Modelv5Staff5;
import helpertools.tools.ItemStaffofExpansion;

import java.lang.ref.Reference;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeHooks;



//@SideOnly(Side.CLIENT)
public class ItemRenderStaff4 implements IItemRenderer {

	private Modelv5Staff5 Modelv5Staff5;
		
	public ItemRenderStaff4()
	{
		
		Modelv5Staff5 = new Modelv5Staff5();
	}
	
	////////////////////////////////////////
	//Expansion staff model
	/////////////////////////////////////////
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		switch(type)
		{
		case EQUIPPED: return true;	
		//
		case EQUIPPED_FIRST_PERSON: return true;
		//	
		default: return false;
		}

	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {

		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		//ItemStaffofExpansion  entitywolf = (ItemStaffofExpansion)item.getItem();
		switch(type){
		
		case EQUIPPED:{
				GL11.glPushMatrix();
				//				
				GL11.glScalef(.1F,.1F,.1F);
				GL11.glRotatef(200, 0F,0F,20F);
				//GL11.glRotatef(90, -3F,10F,10F);
				GL11.glTranslatef(-9F,0F,0F);
				GL11.glTranslatef(0F,-6.5F,0F);
				//GL11.glTranslatef(3F,+3.5F,-6F);
				//				
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("helpertools", "textures/models/Staff5.png")); 
				//
				//Modelv5Staff5.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
				//
				Modelv5Staff5.renderModel(item, 0.625F);
				//
				GL11.glPopMatrix();
				break;
		}
		case EQUIPPED_FIRST_PERSON:{
			
			GL11.glPushMatrix();
			GL11.glScalef(.1F,.1F,.1F);
			GL11.glRotatef(200, 0F,0F,20F);
			GL11.glTranslatef(-9F,0F,0F);
			GL11.glTranslatef(0F,-6.5F,0F);
			//
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("helpertools", "textures/models/Staff5.png")); 
			//
			//Modelv5Staff5.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
			//
			Modelv5Staff5.renderModel(item, 0.625F);
			//
			GL11.glPopMatrix();
			
			break;
			
		}
		default:
			break;
		}

	}

}
