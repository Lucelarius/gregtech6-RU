/**
 * Copyright (c) 2023 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.blocks.stone;

import gregapi.block.BlockBaseMeta;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.ST;
import mods.railcraft.common.carts.EntityTunnelBore;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;

import static gregapi.data.CS.*;

public class BlockCrystalOres extends BlockBaseMeta {
	public static OreDictMaterial[] ORE_MATERIALS = {MT.OREMATS.Arsenopyrite, MT.OREMATS.Chalcopyrite, MT.OREMATS.Cinnabar, MT.OREMATS.Cobaltite, MT.OREMATS.Galena, MT.OREMATS.Kesterite, MT.OREMATS.Molybdenite, MT.Pyrite, MT.OREMATS.Sphalerite, MT.OREMATS.Stannite, MT.OREMATS.Stibnite, MT.OREMATS.Tetrahedrite};
	
	public BlockCrystalOres(String aUnlocalised) {
		super(null, aUnlocalised, Material.glass, soundTypeGlass, ORE_MATERIALS.length, Textures.BlockIcons.CRYSTAL_ORES);
		LH.add(getUnlocalizedName()+ ".0", "Кристалл арсенопирита");
		LH.add(getUnlocalizedName()+ ".1", "Кристалл халькопирита");
		LH.add(getUnlocalizedName()+ ".2", "Кристалл киновари");
		LH.add(getUnlocalizedName()+ ".3", "Кристалл кобальтина");
		LH.add(getUnlocalizedName()+ ".4", "Кристалл галенита");
		LH.add(getUnlocalizedName()+ ".5", "Кристалл кестерита");
		LH.add(getUnlocalizedName()+ ".6", "Кристалл молибденит");
		LH.add(getUnlocalizedName()+ ".7", "Кристалл пирита");
		LH.add(getUnlocalizedName()+ ".8", "Кристалл сфалерита");
		LH.add(getUnlocalizedName()+ ".9", "Кристалл станнина");
		LH.add(getUnlocalizedName()+".10", "Кристалл антимонита");
		LH.add(getUnlocalizedName()+".11", "Кристалл тэтраэдрита");
		
		for (int i = 0; i < maxMeta(); i++) {
			OM.reg(ST.make(this, 1, i), OP.oreDense.dat(ORE_MATERIALS[i]));
			if (COMPAT_IC2 != null) COMPAT_IC2.valuable(this, i, 2);
		}
		
		if (MD.RC.mLoaded) try {EntityTunnelBore.addMineableBlock(this);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (COMPAT_FR  != null) COMPAT_FR.addToBackpacks("miner", ST.make(this, 1, W));
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		return ST.arraylist(OP.oreRaw.mat(ORE_MATERIALS[aMeta], aFortune>0?2+RNGSUS.nextInt(aFortune*2+2):2));
	}
	
	@Override
	public int getExpDrop(IBlockAccess aWorld, int aMeta, int aFortune) {
		return 3+RNGSUS.nextInt(4);
	}
	
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
	@Override public boolean canCreatureSpawn(byte aMeta) {return T;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_pickaxe;}
	@Override public int getHarvestLevel(int aMeta) {return 0;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.glowstone.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.glowstone.getExplosionResistance(null);}
}
