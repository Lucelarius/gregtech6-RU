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

import gregapi.block.misc.BlockBaseSpike;
import gregapi.damage.DamageSources;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.TFC_DAMAGE_MULTIPLIER;

public class BlockSpikeSuper extends BlockBaseSpike {
	public BlockSpikeSuper(String aNameInternal) {
		super(aNameInternal, MT.TungstenSteel, MT.Ad);
		LH.add(getUnlocalizedName()+ ".0", "Настенный шип (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".1", "Настенный шип (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".2", "Настенный шип (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".3", "Настенный шип (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".4", "Настенный шип (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".5", "Настенный шип (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".6", "Блок шипов (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".7", "Падающий блок шипов (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+ ".8", "Настенный шип (Адамантий)");
		LH.add(getUnlocalizedName()+ ".9", "Настенный шип (Адамантий)");
		LH.add(getUnlocalizedName()+".10", "Настенный шип (Адамантий)");
		LH.add(getUnlocalizedName()+".11", "Настенный шип (Адамантий)");
		LH.add(getUnlocalizedName()+".12", "Настенный шип (Адамантий)");
		LH.add(getUnlocalizedName()+".13", "Настенный шип (Адамантий)");
		LH.add(getUnlocalizedName()+".14", "Блок шипов (Вольфрамовая сталь)");
		LH.add(getUnlocalizedName()+".15", "Падающий блок шипов (Вольфрамовая сталь)");
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		if (aMeta < 8) {
			aList.add(LH.Chat.ORANGE + "Наносит огромный урон всему, что к нему прикасается!");
		} else {
			aList.add(LH.Chat.ORANGE + "Наносит абсурдное количество урона всему, что к нему прикасается!");
		}
		if ((aMeta & 7) >= 6) {
			aList.add(LH.Chat.CYAN + "Работает во всех направлениях, но наносит только половину урона от настенных шипов!");
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World aWorld, int aX, int aY, int aZ, Entity aEntity) {
		int aMeta = WD.meta(aWorld, aX, aY, aZ);
		if (aEntity instanceof EntityLivingBase) {
			if (aMeta < 8) {
				aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), TFC_DAMAGE_MULTIPLIER * ((aMeta & 7) < 6 ? 15.0F :  7.5F));
			} else {
				aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), TFC_DAMAGE_MULTIPLIER * ((aMeta & 7) < 6 ? 50.0F : 25.0F));
			}
		}
	}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess aWorld, int aX, int aY, int aZ, Entity aEntity) {
		return WD.meta(aWorld, aX, aY, aZ) < 8 || !(aEntity instanceof EntityWither || aEntity instanceof EntityDragon);
	}
}
