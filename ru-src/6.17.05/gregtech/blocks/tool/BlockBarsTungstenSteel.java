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

package gregtech.blocks.tool;

import gregapi.block.misc.BlockBaseBars;
import gregapi.data.LH;
import gregapi.data.MT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.world.IBlockAccess;

public class BlockBarsTungstenSteel extends BlockBaseBars {
	public BlockBarsTungstenSteel(String aNameInternal) {
		super(aNameInternal, MT.TungstenSteel, Material.iron, Block.soundTypeMetal);
		LH.add(getUnlocalizedName()+ ".0", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".1", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".2", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".3", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".4", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".5", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".6", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".7", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".8", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".9", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+".10", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+".11", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+".12", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+".13", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+".14", "Решетка (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+".15", "Решетка (Вольфрамовая сталь)");
	}
	
	@Override public float getExplosionResistance(byte aMeta) {return 16;}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess aWorld, int aX, int aY, int aZ, Entity aEntity) {
		return !(aEntity instanceof EntityWither || aEntity instanceof EntityDragon);
	}
}
