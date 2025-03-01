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

package gregtech.blocks;

import gregapi.block.BlockBaseMeta;
import gregapi.block.IBlockOnWalkOver;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.*;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static gregapi.data.CS.*;

public class BlockPath extends BlockBaseMeta implements IBlockOnWalkOver, IRenderedBlock {
	public BlockPath(String aUnlocalised) {
		super(null, aUnlocalised, Material.grass, soundTypeGrass, 12, Textures.BlockIcons.DIRTS);
		setCreativeTab(CreativeTabs.tabTransport);
		LH.add(getUnlocalizedName()+ ".0", "Тропинка");
		LH.add(getUnlocalizedName()+ ".1", "Тропинка (Aether)");
		LH.add(getUnlocalizedName()+ ".2", "Тропинка (Loamy)");
		LH.add(getUnlocalizedName()+ ".3", "Тропинка (Sandy)");
		LH.add(getUnlocalizedName()+ ".4", "Тропинка (Silty)");
		LH.add(getUnlocalizedName()+ ".5", "Тропинка (Alfisol)");
		LH.add(getUnlocalizedName()+ ".6", "Тропинка (Andisol)");
		LH.add(getUnlocalizedName()+ ".7", "Тропинка (Gelisol)");
		LH.add(getUnlocalizedName()+ ".8", "Тропинка (Histosol)");
		LH.add(getUnlocalizedName()+ ".9", "Тропинка (Inceptisol)");
		LH.add(getUnlocalizedName()+".10", "Тропинка (Mollisol)");
		LH.add(getUnlocalizedName()+".11", "Тропинка (Oxisol)");
		LH.add(getUnlocalizedName()+".12", "Тропинка");
		LH.add(getUnlocalizedName()+".13", "Тропинка");
		LH.add(getUnlocalizedName()+".14", "Тропинка");
		LH.add(getUnlocalizedName()+".15", "Тропинка");
		setBlockBounds(0, 0, 0, 1, PIXELS_NEG[1], 1);
		
		if (COMPAT_FR  != null) COMPAT_FR.addToBackpacks("digger", ST.make(this, 1, W));
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		switch(aMeta) {
		case  1: return ST.arraylist(IL.AETHER_Dirt.get(1));
		case  2: return ST.arraylist(IL.BoP_Dirt_Loamy.get(1));
		case  3: return ST.arraylist(IL.BoP_Dirt_Sandy.get(1));
		case  4: return ST.arraylist(IL.BoP_Dirt_Silty.get(1));
		case  5: return ST.arraylist(IL.EB_Dirt_Alfisol.get(1));
		case  6: return ST.arraylist(IL.EB_Dirt_Andisol.get(1));
		case  7: return ST.arraylist(IL.EB_Dirt_Gelisol.get(1));
		case  8: return ST.arraylist(IL.EB_Dirt_Histosol.get(1));
		case  9: return ST.arraylist(IL.EB_Dirt_Inceptisol.get(1));
		case 10: return ST.arraylist(IL.EB_Dirt_Mollisol.get(1));
		case 11: return ST.arraylist(IL.EB_Dirt_Oxisol.get(1));
		default: return ST.arraylist(ST.make(Blocks.dirt, 1, 0));
		}
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		if (SIDES_TOP[aSide]) return T;
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		return tBlock != Blocks.farmland && !WD.visOpq(tBlock);
	}
	
	@Override public int getRenderType() {return RendererBlockTextured.INSTANCE==null?0:RendererBlockTextured.INSTANCE.mRenderID;}
	
	@Override
	public ITexture getTexture(int aRenderPass, byte aSide, ItemStack aStack) {
		if (SIDES_TOP[aSide]) return BlockTextureDefault.get(Textures.BlockIcons.PATH_TOP);
		ITexture tDirt = BlockTextureDefault.get(mIcons[ST.meta_(aStack) % 16]);
		return SIDES_BOTTOM[aSide]?tDirt:BlockTextureMulti.get(tDirt, BlockTextureDefault.get(Textures.BlockIcons.PATH_SIDE));
	}
	
	@Override
	public ITexture getTexture(int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered, IBlockAccess aWorld, int aX, int aY, int aZ) {
		if (SIDES_BOTTOM[aSide]) return BlockTextureDefault.get(mIcons[WD.meta(aWorld, aX, aY, aZ) % 16]);
		if (SIDES_TOP   [aSide]) return BlockTextureDefault.get(Textures.BlockIcons.PATH_TOP);
		return BlockTextureMulti.get(   BlockTextureDefault.get(mIcons[WD.meta(aWorld, aX, aY, aZ) % 16]), BlockTextureDefault.get(isHalfBlock(aWorld, aX, aY, aZ) ? Textures.BlockIcons.PATH_SLAB : Textures.BlockIcons.PATH_SIDE));
	}
	
	public boolean isHalfBlock(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return aWorld.getBlock(aX+1, aY-1, aZ) == this || aWorld.getBlock(aX, aY-1, aZ+1) == this || aWorld.getBlock(aX-1, aY-1, aZ) == this || aWorld.getBlock(aX, aY-1, aZ-1) == this;
	}
	
	@Override
	public void onWalkOver(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {
		if ((aEntity.motionX != 0 || aEntity.motionZ != 0) && !aEntity.isInWater() && !aEntity.isSneaking()) {
			double tSpeed = (aWorld.getBlock(aX, aY-1, aZ).slipperiness >= 0.8 && isHalfBlock(aWorld, aX, aY, aZ) ? 1.05 : 1.1);
			aEntity.motionX *= tSpeed; aEntity.motionZ *= tSpeed;
		}
		// Convert Et Futurum Grass Paths to this when adjacent.
		if (IL.EtFu_Path.exists()) for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
			if (IL.EtFu_Path.equal(aWorld.getBlock(aX+i, aY+j, aZ+k))) {
				aWorld.setBlock(aX+i, aY+j, aZ+k, this, 0, 2);
			}
		}
	}
	
	@Override public boolean usesRenderPass(int aRenderPass, ItemStack aStack                                                                     ) {return T;}
	@Override public boolean usesRenderPass(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered         ) {return T;}
	@Override public boolean setBlockBounds(int aRenderPass, ItemStack aStack                                                                     ) {setBlockBounds(0, 0, 0, 1,                                          PX_N[1] , 1); return T;}
	@Override public boolean setBlockBounds(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered         ) {setBlockBounds(0, 0, 0, 1, (isHalfBlock(aWorld, aX, aY, aZ)?PX_N[9]:PX_N[1]), 1); return T;}
	@Override public int getRenderPasses(ItemStack aStack                                                                                         ) {return 1;}
	@Override public int getRenderPasses(IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered                             ) {return 1;}
	@Override public IRenderedBlockObject passRenderingToObject(ItemStack aStack                                                                  ) {return null;}
	@Override public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ                                       ) {return null;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return (SIDES_TOP[aSide]?Textures.BlockIcons.PATH_TOP:Textures.BlockIcons.DIRTS[aMeta % 16]).getIcon(0);}
	
	
	@Override @SuppressWarnings({"unchecked"})
	public void addCollisionBoxesToList(World aWorld, int aX, int aY, int aZ, AxisAlignedBB aAABB, List aList, Entity aEntity) {
		AxisAlignedBB
		tAABB = AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+0.5  , aZ+1); if (tAABB.intersectsWith(aAABB)) aList.add(tAABB);
		if (isHalfBlock(aWorld, aX, aY, aZ)) return;
		tAABB = AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+0.875, aZ+1); if (tAABB.intersectsWith(aAABB)) aList.add(tAABB);
		tAABB = AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+1    , aZ+1); if (tAABB.intersectsWith(aAABB)) aList.add(tAABB);
	}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+(isHalfBlock(aWorld, aX, aY, aZ)?0.5:1), aZ+1);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool (World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+(isHalfBlock(aWorld, aX, aY, aZ)?0.5:1), aZ+1);}
	@Override public void setBlockBoundsBasedOnState(IBlockAccess aWorld, int aX, int aY, int aZ) {setBlockBounds(0, 0, 0, 1, (isHalfBlock(aWorld, aX, aY, aZ)?0.5F:1), 1);}
	@Override public boolean doesWalkSpeed(byte aMeta) {return T;}
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
	@Override public boolean canCreatureSpawn(byte aMeta) {return F;}
	@Override public boolean canSilkHarvest() {return F;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_shovel;}
	@Override public int getHarvestLevel(int aMeta) {return 0;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.grass.getBlockHardness(aWorld, aX, aY, aZ) * 2;}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.grass.getExplosionResistance(null) * 1.5F;}
	@Override public boolean isSideSolid(int aMeta, byte aSide) {return SIDES_BOTTOM_HORIZONTAL[aSide];}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return F;}
	@Override public boolean isNormalCube() {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
}
