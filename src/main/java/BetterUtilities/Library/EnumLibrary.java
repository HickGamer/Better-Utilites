package BetterUtilities.Library;

import net.minecraft.util.IStringSerializable;

public class EnumLibrary {
	
	public static enum MachineType implements IStringSerializable{
		basic("basic", 0),
		advanced("advanced", 1),
		elite("elite", 2),
		ultimate("ultimate", 3),
		creative("creative", 4);
		
		private int ID;
		private String name;
		
		private MachineType(String name, int ID){
			this.ID = ID;
			this.name = name;
		}
		
		@Override
		public String getName(){
			return this.name;
		}
		
		public int getID(){
			return ID;
		}
		
		@Override
		public String toString(){
			return getName();
		}
	}
	
	public static enum Ingot implements IStringSerializable{
		copper("basic", 0),
		tin("advanced", 1),
		cadmium("elite", 2),
		bronze("ultimate", 3),
		iridium("iridium", 4);
		
		private int ID;
		private String name;
		
		private Ingot(String name, int ID){
			this.ID = ID;
			this.name = name;
		}
		
		@Override
		public String getName(){
			return this.name;
		}
		
		public int getID(){
			return ID;
		}
		
		@Override
		public String toString(){
			return getName();
		}
	}
	
	public static enum Ore implements IStringSerializable{
		copper("basic", 0),
		tin("advanced", 1),
		cadmium("elite", 2),
		iridium("ultimate", 3);
		
		private int ID;
		private String name;
		
		private Ore(String name, int ID){
			this.ID = ID;
			this.name = name;
		}
		
		@Override
		public String getName(){
			return this.name;
		}
		
		public int getID(){
			return ID;
		}
		
		@Override
		public String toString(){
			return getName();
		}
	}

}
