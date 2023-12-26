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
import gregapi.data.ANY;
import gregapi.data.LH;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBarsSteel extends BlockBaseBars {
	public BlockBarsSteel(String aNameInternal) {
		super(aNameInternal, ANY.Steel, Material.iron, Block.soundTypeMetal);
		LH.add(getUnlocalizedName()+ ".0", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+ ".1", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+ ".2", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+ ".3", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+ ".4", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+ ".5", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+ ".6", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+ ".7", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+ ".8", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+ ".9", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+".10", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+".11", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+".12", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+".13", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+".14", "Решетка (Сталь)");
		LH.add(getUnlocalizedName()+".15", "Решетка (Сталь)");
	}
	
	@Override public float getExplosionResistance(byte aMeta) {return 8;}
}
