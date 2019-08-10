/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.cover.CoverData;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverLogisticsFluidStorage extends AbstractCoverAttachmentLogistics {
	public static final CoverLogisticsFluidStorage INSTANCE = new CoverLogisticsFluidStorage();
	
	public CoverLogisticsFluidStorage() {}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		FluidStack tFluid = FL.load(aStack.getTagCompound(), "gt.filter.fluid");
		if (tFluid != null && tFluid.getFluid() != null) aList.add(LH.Chat.CYAN + FL.name(tFluid, T));
		aList.add(LH.Chat.ORANGE + "Not NBT sensitive!");
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
	}
	
	@Override
	public long onToolClick(byte aCoverSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_softhammer)) {
			aData.mNBTs[aCoverSide] = null;
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				if (aData.mNBTs[aCoverSide] == null) {
					aChatReturn.add("Stores Anything! (Priority: " + aData.mValues[aCoverSide] + ")");
					aData.mNBTs[aCoverSide] = null;
				} else {
					FluidStack tFluid = FL.load(aData.mNBTs[aCoverSide], "gt.filter.fluid");
					if (tFluid == null) {
						aChatReturn.add("Stores Anything! (Priority: " + aData.mValues[aCoverSide] + ")");
						aData.mNBTs[aCoverSide] = null;
					} else {
						aChatReturn.add("Stores: " + LH.Chat.CYAN + tFluid.getFluid().getName() + " (Priority: " + aData.mValues[aCoverSide] + ")");
					}
				}
			}
			return 1;
		}
		return super.onToolClick(aCoverSide, aData, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSideClicked, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean onCoverClickedRight(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer instanceof EntityPlayer && aData.mTileEntity.isServerSide()) {
			if (aData.mNBTs[aCoverSide] == null || !aData.mNBTs[aCoverSide].hasKey("gt.filter.fluid")) {
				ItemStack tStack = ((EntityPlayer)aPlayer).getCurrentEquippedItem();
				if (ST.valid(tStack)) {
					FluidStack tFluid = FL.getFluid(tStack, T);
					if (tFluid != null && tFluid.getFluid() != null) {
						aData.mNBTs[aCoverSide] = FL.save(null, "gt.filter.fluid", tFluid);
						UT.Sounds.send(aData.mTileEntity.getWorld(), SFX.MC_CLICK, 1, 1, aData.mTileEntity.getCoords());
						UT.Entities.sendchat(aPlayer, "Stores: " + LH.Chat.CYAN + tFluid.getFluid().getName());
					}
				}
			}
		}
		return T;
	}
	
	@Override public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData) {return sTexture;}
	
	public static final ITexture sTexture = BlockTextureDefault.get("machines/covers/logistics/fluid/storage");
}
