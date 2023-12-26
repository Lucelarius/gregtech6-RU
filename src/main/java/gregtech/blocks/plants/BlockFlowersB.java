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

package gregtech.blocks.plants;

import gregapi.block.misc.BlockBaseFlower;
import gregapi.data.*;
import gregapi.old.Textures;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

import static gregapi.data.CS.*;

public class BlockFlowersB extends BlockBaseFlower implements Runnable {
	public BlockFlowersB(String aUnlocalised) {
		super(null, aUnlocalised, 8, Textures.BlockIcons.FLOWERS_B);
		LH.add(getUnlocalizedName()+ ".0", "Полынь"); // Gold, Antimony, Arsenic
		LH.add(getUnlocalizedName()+ ".1", "Лебеда сереющая"); // Gold, Antimony, Arsenic
		LH.add(getUnlocalizedName()+ ".2", "Труба пустыни"); // Gold, color might vary depending on other Minerals close by
		LH.add(getUnlocalizedName()+ ".3", "Медный цветок"); // Copper, Nickel
		LH.add(getUnlocalizedName()+ ".4", "Пустынный князь"); // Selenium
		LH.add(getUnlocalizedName()+ ".5", "Шалфей Томпсона"); // Uranium
		LH.add(getUnlocalizedName()+ ".6", "Канделябр Панданус"); // Diamond
		LH.add(getUnlocalizedName()+ ".7", "Вольфрамит"); // Tungsten
		
		GT.mBeforePostInit.add(this);
		BlocksGT.FLOWERS.add(this);
		
		OM.data(ST.make(this, 1, 0), MT.WOODS.Acacia, U);
		OM.data(ST.make(this, 1, 1), MT.WOODS.Acacia, U);
		OM.data(ST.make(this, 1, 6), MT.WOODS.Palm  , U);
		
		for (int i = 2; i < maxMeta(); i++) if (i != 6) OM.reg(ST.make(this, 1, i), OD.flower);
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		switch(aMeta) {
		case  0: aList.add("Указывает на наличие поблизости месторождения мышьяка"       ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  1: aList.add("Указывает на наличие поблизости месторождения сурьмы"        ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  2: aList.add("Указывает на наличие поблизости месторождения золота"        ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  3: aList.add("Указывает на наличие поблизости месторождения меди"          ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  4: aList.add("Указывает на наличие поблизости месторождения красной пыли"  ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  5: aList.add("Указывает на наличие поблизости месторождения урана"         ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  6: aList.add("Указывает на наличие поблизости месторождения алмазов"       ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  7: aList.add("Указывает на наличие поблизости месторождения вольфрама"     ); break;
		case  8: aList.add("Указывает на наличие поблизости месторождения другой руды"   ); break;
		case  9: aList.add("Указывает на наличие поблизости месторождения другой руды"   ); break;
		case 10: aList.add("Указывает на наличие поблизости месторождения другой руды"   ); break;
		case 11: aList.add("Указывает на наличие поблизости месторождения другой руды"   ); break;
		case 12: aList.add("Указывает на наличие поблизости месторождения другой руды"   ); break;
		case 13: aList.add("Указывает на наличие поблизости месторождения другой руды"   ); break;
		case 14: aList.add("Указывает на наличие поблизости месторождения другой руды"   ); break;
		case 15: aList.add("Указывает на наличие поблизости месторождения другой руды"   ); break;
		}
	}
	
	@Override
	public void run() {
		RM.biomass(ST.make(this, 8, W));
		
		RM.mortarize(ST.make(this, 1, 0), OM.dust(MT.WOODS.Acacia));
		RM.mortarize(ST.make(this, 1, 1), OM.dust(MT.WOODS.Acacia));
		RM.mortarize(ST.make(this, 1, 6), OM.dust(MT.WOODS.Palm  ));
		
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Yellow));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink  ], OM.dust(MT.Pink  ));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Yellow));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], OM.dust(MT.Purple));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 7), NF, FL.Juice_Cactus.make(100), IL.Dye_Cactus.get(2));
		
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Yellow));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink  ], OM.dust(MT.Pink  ));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Yellow));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], OM.dust(MT.Purple));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 7), NF, FL.Juice_Cactus.make( 75), IL.Dye_Cactus.get(2));
		
		CR.shaped   (OP.stick.mat(MT.WOODS.Acacia, 2, IL.Stick.get(2)), CR.DEF_NCC, "s", "X", 'X', ST.make(this, 1, 0));
		CR.shaped   (OP.stick.mat(MT.WOODS.Acacia, 2, IL.Stick.get(2)), CR.DEF_NCC, "k", "X", 'X', ST.make(this, 1, 0));
		CR.shaped   (OP.stick.mat(MT.WOODS.Acacia, 2, IL.Stick.get(2)), CR.DEF_NCC, "s", "X", 'X', ST.make(this, 1, 1));
		CR.shaped   (OP.stick.mat(MT.WOODS.Acacia, 2, IL.Stick.get(2)), CR.DEF_NCC, "k", "X", 'X', ST.make(this, 1, 1));
		CR.shaped   (OP.stick.mat(MT.WOODS.Palm  , 2, IL.Stick.get(2)), CR.DEF_NCC, "s", "X", 'X', ST.make(this, 1, 6));
		CR.shaped   (OP.stick.mat(MT.WOODS.Palm  , 2, IL.Stick.get(2)), CR.DEF_NCC, "k", "X", 'X', ST.make(this, 1, 6));
		
		CR.shapeless(OP.stick.mat(MT.WOODS.Acacia, 1, IL.Stick.get(1)), CR.DEF_NCC, new Object[] {ST.make(this, 1, 0)});
		CR.shapeless(OP.stick.mat(MT.WOODS.Acacia, 1, IL.Stick.get(1)), CR.DEF_NCC, new Object[] {ST.make(this, 1, 1)});
		CR.shapeless(OM.dust(MT.Yellow                               ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 2)});
		CR.shapeless(OM.dust(MT.Pink                                 ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 3)});
		CR.shapeless(OM.dust(MT.Yellow                               ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 4)});
		CR.shapeless(OM.dust(MT.Purple                               ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 5)});
		CR.shapeless(OP.stick.mat(MT.WOODS.Palm  , 1, IL.Stick.get(1)), CR.DEF_NCC, new Object[] {ST.make(this, 1, 6)});
		
		RM.add_smelting(ST.make(this, 1, 7), IL.Dye_Cactus.get(1), F, T, F);
		
		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(this, 1, 0), OP.stick.mat(MT.WOODS.Acacia, 2, IL.Stick.get(2)));
		RM.ic2_extractor(ST.make(this, 1, 1), OP.stick.mat(MT.WOODS.Acacia, 2, IL.Stick.get(2)));
		RM.ic2_extractor(ST.make(this, 1, 2), OM.dust(MT.Yellow, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 3), OM.dust(MT.Pink  , U * 2));
		RM.ic2_extractor(ST.make(this, 1, 4), OM.dust(MT.Yellow, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 5), OM.dust(MT.Purple, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 6), OP.stick.mat(MT.WOODS.Palm  , 2, IL.Stick.get(2)));
		RM.ic2_extractor(ST.make(this, 1, 7), IL.Dye_Cactus.get(2));
		}
	}
	
	@Override
	public boolean canBlockStay(World aWorld, int aX, int aY, int aZ) {
		return WD.oxygen(aWorld, aX, aY, aZ) && aWorld.getBlock(aX, aY - 1, aZ).canSustainPlant(aWorld, aX, aY - 1, aZ, ForgeDirection.UP, (IPlantable)Blocks.cactus);
	}
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return EnumPlantType.Desert;
	}
}
