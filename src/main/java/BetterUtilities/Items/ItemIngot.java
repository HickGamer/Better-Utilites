package BetterUtilities.Items;

import BetterUtilities.Library.BULibrary;
import BetterUtilities.Library.EnumLibrary.Ingot;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemIngot extends Item {
	
	public ItemIngot(String name){
		setHasSubtypes(true);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(BULibrary.id, name));
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items){
		for(int i = 0;i <Ingot.values().length; i++){
			items.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for(int i = 0; i < Ingot.values().length; i++) {
			if(stack.getItemDamage() == i) {
				return this.getUnlocalizedName() + "." + Ingot.values()[i].getName();
			}
			else {
				continue;
			}
		}
		return this.getUnlocalizedName() + "." + Ingot.copper.getName();
	}

}
