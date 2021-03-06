package cjminecraft.industrialtech.items;

import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.handlers.EnumHandler;
import cjminecraft.industrialtech.util.IndustrialTechItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemChip extends IndustrialTechItem {
	
	public ItemChip(String unlocalizedName) {
		super(unlocalizedName);
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for(int i = 0; i < EnumHandler.ChipTypes.values().length; i++) {
			if(stack.getItemDamage() == i) {
				return super.getUnlocalizedName() + "." + EnumHandler.ChipTypes.values()[i].getName();
			}
			else {
				continue;
			}
		}
		return super.getUnlocalizedName() + ".basic";
	}
	
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		for(int i = 0; i < EnumHandler.ChipTypes.values().length; i++) {
			subItems.add(new ItemStack(itemIn, 1, i));
		}
	}

}
