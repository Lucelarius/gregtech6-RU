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

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.misc.BlockBaseSpike;
import gregapi.damage.DamageSources;
import gregapi.data.ANY;
import gregapi.data.LH;
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSpikeSteel extends BlockBaseSpike {
	public BlockSpikeSteel(String aNameInternal) {
		super(aNameInternal, ANY.BlueSteel, ANY.RedSteel); // If you were looking for the regular Steel Spike, that one is with the Sharp Spikes.
		LH.add(getUnlocalizedName()+ ".0", "Настенный шип (Синяя сталь)");
		LH.add(getUnlocalizedName()+ ".1", "Настенный шип (Синяя сталь)");
		LH.add(getUnlocalizedName()+ ".2", "Настенный шип (Синяя сталь)");
		LH.add(getUnlocalizedName()+ ".3", "Настенный шип (Синяя сталь)");
		LH.add(getUnlocalizedName()+ ".4", "Настенный шип (Синяя сталь)");
		LH.add(getUnlocalizedName()+ ".5", "Настенный шип (Синяя сталь)");
		LH.add(getUnlocalizedName()+ ".6", "Блок шипов (Синяя сталь)");
		LH.add(getUnlocalizedName()+ ".7", "Падающий блок шипов (Синяя сталь)");
		LH.add(getUnlocalizedName()+ ".8", "Настенный шип (Красная сталь)");
		LH.add(getUnlocalizedName()+ ".9", "Настенный шип (Красная сталь)");
		LH.add(getUnlocalizedName()+".10", "Настенный шип (Красная сталь)");
		LH.add(getUnlocalizedName()+".11", "Настенный шип (Красная сталь)");
		LH.add(getUnlocalizedName()+".12", "Настенный шип (Красная сталь)");
		LH.add(getUnlocalizedName()+".13", "Настенный шип (Красная сталь)");
		LH.add(getUnlocalizedName()+".14", "Блок шипов (Красная сталь)");
		LH.add(getUnlocalizedName()+".15", "Падающий блок шипов (Красная сталь)");
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		aList.add(LH.Chat.ORANGE + "Наносит урон выше среднего всему, что к нему прикасается!");
		aList.add(LH.Chat.ORANGE + "Не действует на железных големов.");
		
		if ((aMeta & 7) >= 6) {
			aList.add(LH.Chat.CYAN + "Работает во всех направлениях, но наносит только половину урона от настенных шипов!");
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World aWorld, int aX, int aY, int aZ, Entity aEntity) {
		int aMeta = WD.meta(aWorld, aX, aY, aZ);
		if (aEntity instanceof EntityLivingBase && !(aEntity instanceof EntityIronGolem)) {
			aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), TFC_DAMAGE_MULTIPLIER * ((aMeta & 7) < 6 ?  8.0F :  4.0F));
		}
	}
}
