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

package gregtech.blocks.wood;

import gregapi.block.metatype.BlockBasePlanksFlammable;
import gregapi.block.metatype.BlockMetaType;
import gregapi.block.metatype.ItemBlockMetaType;
import gregapi.data.*;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;

import static gregapi.data.CS.ALL_SIDES_VALID;

public class BlockTreePlanks extends BlockBasePlanksFlammable {
	public BlockTreePlanks(String aName) {
		super(ItemBlockMetaType.class, Material.wood, soundTypeWood, aName, "", ANY.Wood, 1, 1, 0, 16, Textures.BlockIcons.PLANKS);
		LH.add(getUnlocalizedName()+ ".0", "Доски из каучукового дерева");
		LH.add(getUnlocalizedName()+ ".1", "Доски из клена");
		LH.add(getUnlocalizedName()+ ".2", "Доски из ивы");
		LH.add(getUnlocalizedName()+ ".3", "Доски из синего махо");
		LH.add(getUnlocalizedName()+ ".4", "Доски из орешника");
		LH.add(getUnlocalizedName()+ ".5", "Доски из дерева корицы");
		LH.add(getUnlocalizedName()+ ".6", "Доски из кокосового дерева");
		LH.add(getUnlocalizedName()+ ".7", "Доски из радужного дерева");
		LH.add(getUnlocalizedName()+ ".8", "Доски из сжатого дерева");
		LH.add(getUnlocalizedName()+ ".9", "Доски из дерева");
		LH.add(getUnlocalizedName()+".10", "Обработанные доски");
		LH.add(getUnlocalizedName()+".11", "Ящик");
		LH.add(getUnlocalizedName()+".12", "Мертвые доски");
		LH.add(getUnlocalizedName()+".13", "Гнилые доски");
		LH.add(getUnlocalizedName()+".14", "Замшелые доски");
		LH.add(getUnlocalizedName()+".15", "Замороженные доски");
		
		OM.reg(ST.make(this, 1, 7), OP.plank.dat(MT.WOODS.Rainbowood));
		
		for (int i = 0; i < maxMeta(); i++) {
			if (i != 10) OM.reg(ST.make(this, 1, i), OD.plankWood);
			for (byte tSide : ALL_SIDES_VALID) OM.reg(ST.make(mSlabs[tSide], 1, i), OD.slabWood);
		}
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockTreePlanks(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockTreePlanks(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		LH.add(getUnlocalizedName()+ ".0", "Плита из каучука");
		LH.add(getUnlocalizedName()+ ".1", "Плита из клена");
		LH.add(getUnlocalizedName()+ ".2", "Плита из ивы");
		LH.add(getUnlocalizedName()+ ".3", "Плита из синего махо");
		LH.add(getUnlocalizedName()+ ".4", "Плита из орешника");
		LH.add(getUnlocalizedName()+ ".5", "Плита из дерева корицы");
		LH.add(getUnlocalizedName()+ ".6", "Плита из кокосового дерева");
		LH.add(getUnlocalizedName()+ ".7", "Плита из радужного дерева");
		LH.add(getUnlocalizedName()+ ".8", "Плита из сжатого дерева");
		LH.add(getUnlocalizedName()+ ".9", "Плита из дерева");
		LH.add(getUnlocalizedName()+".10", "Плита из обработанного дерева");
		LH.add(getUnlocalizedName()+".11", "Плита из ящика");
		LH.add(getUnlocalizedName()+".12", "Плита из мертвого дерева");
		LH.add(getUnlocalizedName()+".13", "Плита из гнилого дерева");
		LH.add(getUnlocalizedName()+".14", "Плита из замшелого дерева");
		LH.add(getUnlocalizedName()+".15", "Плита из замороженного дерева");
	}
	
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return (aWorld.getBlockMetadata(aX, aY, aZ) < 12 ? 1.0F : 0.5F) * super.getBlockHardness(aWorld, aX, aY, aZ);}
}
