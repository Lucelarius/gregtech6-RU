/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregtech.tileentity.portals;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MD;
import gregapi.render.BlockTextureCopied;
import gregapi.render.ITexture;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMiniPortalAlfheim extends MultiTileEntityMiniPortal {
	public static List<MultiTileEntityMiniPortal>
	sListAlfheimSide = new ArrayListNoNulls<>(),
	sListWorldSide   = new ArrayListNoNulls<>();
	
	@Override public List<MultiTileEntityMiniPortal> getPortalListA() {return sListWorldSide;}
	@Override public List<MultiTileEntityMiniPortal> getPortalListB() {return sListAlfheimSide;}
	
	static {
		LH.add("gt.tileentity.portal.alfheim.tooltip.1", "Работает только в Альфхейме и Мидгарде");
		LH.add("gt.tileentity.portal.alfheim.tooltip.2", "Предел погрешности для работы: 128 Метров.");
		LH.add("gt.tileentity.portal.alfheim.tooltip.3", "Требуется межпространственное ядро шлюза для активации");
		LH.add("gt.tileentity.portal.alfheim.tooltip.4", "Это не торговый портал! Он работает так же, как и все другие мини-порталы!");
	}
	
	@Override
	public void addToolTips2(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt.tileentity.portal.alfheim.tooltip.1"));
		aList.add(Chat.CYAN     + LH.get("gt.tileentity.portal.alfheim.tooltip.2"));
		aList.add(Chat.ORANGE   + LH.get("gt.tileentity.portal.alfheim.tooltip.3"));
		aList.add(Chat.ORANGE   + LH.get("gt.tileentity.portal.alfheim.tooltip.4"));
	}
	
	@Override
	public void findTargetPortal() {
		mTarget = null;
		if (MD.ALF.mLoaded && worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				long tShortestDistance = 128*128;
				for (MultiTileEntityMiniPortal tTarget : sListAlfheimSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = xCoord-tTarget.xCoord, tZDifference = zCoord-tTarget.zCoord;
					long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
					if (tTempDist < tShortestDistance) {
						tShortestDistance = tTempDist;
						mTarget = tTarget;
					} else if (tTempDist == tShortestDistance && (mTarget == null || Math.abs(tTarget.yCoord-yCoord) < Math.abs(mTarget.yCoord-yCoord))) {
						mTarget = tTarget;
					}
				}
			} else if (WD.dimALF(worldObj)) {
				long tShortestDistance = 128*128;
				for (MultiTileEntityMiniPortal tTarget : sListWorldSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = tTarget.xCoord-xCoord, tZDifference = tTarget.zCoord-zCoord;
					long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
					if (tTempDist < tShortestDistance) {
						tShortestDistance = tTempDist;
						mTarget = tTarget;
					} else if (tTempDist == tShortestDistance && (mTarget == null || Math.abs(tTarget.yCoord-yCoord) < Math.abs(mTarget.yCoord-yCoord))) {
						mTarget = tTarget;
					}
				}
			}
		}
	}
	
	@Override
	public void addThisPortalToLists() {
		if (MD.ALF.mLoaded && worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				if (!sListWorldSide.contains(this)) sListWorldSide.add(this);
				for (MultiTileEntityMiniPortal tPortal : sListAlfheimSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else if (WD.dimALF(worldObj)) {
				if (!sListAlfheimSide.contains(this)) sListAlfheimSide.add(this);
				for (MultiTileEntityMiniPortal tPortal : sListWorldSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else {
				setPortalInactive();
			}
		}
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			ItemStack aStack = aPlayer.inventory.getCurrentItem();
			if (ST.valid(aStack) && aStack.stackSize > 0 && IL.ALF_Gateway_Core.equal(aStack, F, T)) {
				setPortalActive();
				if (mTarget != null) UT.Entities.sendchat(aPlayer, "X: " + mTarget.xCoord + "   Y: " + mTarget.yCoord + "   Z: " + mTarget.zCoord);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
			}
		}
		return T;
	}
	
	@Override public float getBlockHardness() {return Blocks.stone.getBlockHardness(worldObj, xCoord, yCoord, zCoord);}
	@Override public float getExplosionResistance2() {return Blocks.stone.getExplosionResistance(null);}
	
	public ITexture sAlfheimPortal = BlockTextureCopied.get(Blocks.portal, SIDE_ANY, 0, 0x000088ff, F, T, T), sMidgardPortal = BlockTextureCopied.get(Blocks.portal, SIDE_ANY, 0, 0x00ffff00, F, T, T), sAlfheimPortalFrame = BlockTextureCopied.get(ST.block(MD.BOTA, "dreamwood", Blocks.planks));
	@Override public ITexture getPortalTexture() {return WD.dimALF(worldObj) ? sMidgardPortal : sAlfheimPortal;}
	@Override public ITexture getFrameTexture() {return sAlfheimPortalFrame;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.portal.alfheim";}
}
