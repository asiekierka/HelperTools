package helpertools.Common.Tools;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class Adv_Tool extends ItemSpade{

	//public short aShort;



	public Adv_Tool(ToolMaterial material) {
		super(material);
		// TODO Auto-generated constructor stub
	}

	/**
	 public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	    {
	    nbt.setShort("Damage", this.aShort);
		 
		 return nbt;
	    }
	**/


    /////////////////////////////////////////////////////////////////////
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {

    	if(entity.worldObj.isRemote){
    		return;  	}
    	//Item item = entity.inventory.currentItem;
    	if (!(entity instanceof EntityPlayer)){
    		return;
    	}
    	if(((EntityPlayer) entity).getCurrentEquippedItem() == null){
    		return;
    	}

    	ItemStack item = ((EntityPlayer) entity).getCurrentEquippedItem();
    	Item item2 = item.getItem();
    	if (!(item2 == this)){
    		return;
    	}



    	return;

    }
    public String whatModeString(ItemStack stack){	  
    	String modestring = "null";

    	if (getMode(stack) == 2){
    		modestring = "Pillar";
    	}
    	else if(getMode(stack) == 4){
    		modestring = "Wall";
    	}
    	else if(getMode(stack) == 6){
    		modestring = "Matching";
    	}
    	else{
    		modestring = "null";
    	}  
    	return modestring;
    };

    public String whatBlockString(ItemStack stack){  		
    	String modestring = "null";

    	if (getTBlock(stack) == 0){
    		modestring = "null";
    	}  		
    	if (getTBlock(stack) != 0){
    		modestring = returnTBlock(stack).getLocalizedName() + " ";  			
    		return modestring;
    	} 
    	return modestring;

    };

		
	

    /////////////////////////////////////////////////////////////
  	public int getMode(ItemStack itemStack) {
		if (!itemStack.hasTagCompound()) {
			return 2;
		}
		if (itemStack.getTagCompound().getInteger("mode") == 0){
			setMode(itemStack, 2); 
		}

		return itemStack.getTagCompound().getInteger("mode");

	}
      
      public boolean isMetadataSpecific(ItemStack itemStack)
   	{
   		return false;
   	}
      
      ///////////////////////////////////////////
      public void setMode(ItemStack itemStack, int Value)
   	{
   		if(!itemStack.hasTagCompound())
   		{
   			itemStack.setTagCompound(new NBTTagCompound());
   		}

   		itemStack.getTagCompound().setInteger("mode",  Value );
   		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
   	} 
   	///////////////////
      
    public int getTBlock(ItemStack itemStack)
	{
		if(!itemStack.hasTagCompound())
		{
			//setTBlock(itemStack, 2);
			//return 2;
			return 0;
		}

		return itemStack.getTagCompound().getInteger("TBlock");
		
	}
	public void setTBlock(ItemStack itemStack, int Value)
	{
		if(!itemStack.hasTagCompound())
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.getTagCompound().setInteger("TBlock",  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	////////
	public int getTMeta(ItemStack itemStack)
	{
		if(!itemStack.hasTagCompound())
		{
			return 0;
		}

		return itemStack.getTagCompound().getInteger("TMeta");
		
	}
	public void setTMeta(ItemStack itemStack, int Value)
	{
		if(!itemStack.hasTagCompound())
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.getTagCompound().setInteger("TMeta",  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	
	
	//////
	public int returnTMeta(ItemStack thestaff)
    {
		return getTMeta(thestaff);		
    }
	
	public Block returnTBlock(ItemStack thestaff)
	{
		return Block.getBlockById(getTBlock(thestaff));
	}
	
    
	/** Offmode here prevents getblock from double dipping into switch mode code because i suck**/
	// ///////////////////////////////////////////////////////////
		public int getOffMode(ItemStack itemStack) {
			if (!itemStack.hasTagCompound()) {				
				return 0;
			}

			return itemStack.getTagCompound().getInteger("OffMode");

		}		
		public void setOffMode(ItemStack itemStack, int Value) {
			if (!itemStack.hasTagCompound()) {
				itemStack.setTagCompound(new NBTTagCompound());
			}

			itemStack.getTagCompound().setInteger("OffMode", Value);			
		}
    
    //////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////
    
    /** Tool levels **/
		public int getToolLevel(ItemStack itemStack) {
			if (!itemStack.hasTagCompound()) {
				return 0;
			}

			return itemStack.getTagCompound().getInteger("ToolLevel");

		}		
		public void setToolLevel(ItemStack itemStack, int Value) {
			if (!itemStack.hasTagCompound()) {
				itemStack.setTagCompound(new NBTTagCompound());
			}

			itemStack.getTagCompound().setInteger("ToolLevel", Value);			
		}
		
		public void ToolEmpower(ItemStack itemStack, EntityLivingBase entityLiving){
			int Toolmax = EnchantmentHelper.getEnchantmentLevel(32, itemStack);
			int NextLevel = (getToolLevel(itemStack))+1;
			if(NextLevel>Toolmax){
				setToolLevel(itemStack,0);
				entityLiving.worldObj.playSoundAtEntity(entityLiving, "random.fizz", (float)(1), (float)(1.3));
			}
			if(NextLevel<=Toolmax){
				setToolLevel(itemStack,NextLevel);
				entityLiving.worldObj.playSoundAtEntity(entityLiving, "random.orb", (float)(.8), (float)( itemRand.nextFloat()*.75+.2));
			}
			/**
			 if(Helpertoolscore.ToolPowerMesseges == true){	
				 String Messy = ("Rank: "+(getToolLevel(itemStack)));
					ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
					((EntityPlayer) entityLiving).addChatComponentMessage(chatmessy);
				    }
				    **/
			//
			//System.out.println("Empowering!"+"  The level is... "+(getToolLevel(itemStack))); 
		}
		
		
		
		/** returns a rounded number for tool levels**/
		public int getEff2Level(ItemStack itemStack) {
			if (itemStack == null) {
				return 0;
			}
			//int eff = EnchantmentHelper.getEnchantmentLevel(32, itemStack);
			int eff = (getToolLevel(itemStack));
			
			if ((eff%2)!=0){
				//odd
				eff = eff-1;
			}
			eff = eff/2;
			if (eff <= 0){
				eff = 0;
			}
			return eff;

		}	
		

		
}