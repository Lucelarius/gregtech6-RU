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
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.RM;
import gregapi.old.Textures;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

import static gregapi.data.CS.*;

public class BlockFlowersA extends BlockBaseFlower implements Runnable {
	public BlockFlowersA(String aUnlocalised) {
		super(null, aUnlocalised, 9, Textures.BlockIcons.FLOWERS_A);
		LH.add(getUnlocalizedName()+ ".0", "Измененная андезитовая гречиха"); // Gold, Silver
		LH.add(getUnlocalizedName()+ ".1", "Гречиха Кросби"); // Gold, Silver, Sulfur
		LH.add(getUnlocalizedName()+ ".2", "Альпийская ловля"); // Copper
		LH.add(getUnlocalizedName()+ ".3", "Виола каламинария"); // Zinc and heavy Metals
		LH.add(getUnlocalizedName()+ ".4", "Тласпи Лерешианум"); // Nickel and Zinc
		LH.add(getUnlocalizedName()+ ".5", "Энотера дернистая"); // Uranium
		LH.add(getUnlocalizedName()+ ".6", "Нарцисс Шелдония"); // Cooperite
		LH.add(getUnlocalizedName()+ ".7", "Орхидея"); // Any Random Ore that doesn't have a specific Flower
		LH.add(getUnlocalizedName()+ ".8", "Гексолилия"); // Hexorium
		
		GT.mBeforePostInit.add(this);
		BlocksGT.FLOWERS.add(this);
		
		OM.data(ST.make(this, 1, 0), MT.Wheat, U);
		OM.data(ST.make(this, 1, 1), MT.Wheat, U);
		
		for (int i = 0; i < maxMeta(); i++) OM.reg(ST.make(this, 1, i), OD.flower);
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		switch(aMeta) {
		case  0: aList.add("Указывает на наличие поблизости месторождения золота"          ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  1: aList.add("Указывает на наличие поблизости месторождения серебра"         ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  2: aList.add("Указывает на наличие поблизости месторождения меди"            ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  3: aList.add("Указывает на наличие поблизости месторождения цинка"           ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  4: aList.add("Указывает на наличие поблизости месторождения никеля"          ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  5: aList.add("Указывает на наличие поблизости месторождения урана"           ); aList.add(LH.Chat.DGRAY + "* существует в реальной жизни"); break;
		case  6: aList.add("Указывает на наличие поблизости месторождения платины"         ); break;
		case  7: aList.add("Указывает на наличие поблизости месторождения упомянутой руды" ); break;
		case  8: aList.add("Указывает на наличие поблизости месторождения гексория"        ); break;
		case  9: aList.add("Указывает на наличие поблизости месторождения другой руды"     ); break;
		case 10: aList.add("Указывает на наличие поблизости месторождения другой руды"     ); break;
		case 11: aList.add("Указывает на наличие поблизости месторождения другой руды"     ); break;
		case 12: aList.add("Указывает на наличие поблизости месторождения другой руды"     ); break;
		case 13: aList.add("Указывает на наличие поблизости месторождения другой руды"     ); break;
		case 14: aList.add("Указывает на наличие поблизости месторождения другой руды"     ); break;
		case 15: aList.add("Указывает на наличие поблизости месторождения другой руды"     ); break;
		}
	}
	
	@Override
	public void run() {
		RM.biomass(ST.make(this, 8, W));
		
		RM.mortarize(ST.make(this, 1, 0), OM.dust(MT.Wheat));
		RM.mortarize(ST.make(this, 1, 1), OM.dust(MT.Wheat));
		
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow   ], OM.dust(MT.Wheat));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow   ], OM.dust(MT.Wheat));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta  ], OM.dust(MT.Magenta));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow   ], OM.dust(MT.Yellow));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink     ], OM.dust(MT.Pink));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White    ], OM.dust(MT.White));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], OM.dust(MT.LightBlue));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Brown    ], OM.dust(MT.Brown));
		
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow   ], OM.dust(MT.Wheat));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow   ], OM.dust(MT.Wheat));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta  ], OM.dust(MT.Magenta));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow   ], OM.dust(MT.Yellow));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink     ], OM.dust(MT.Pink));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White    ], OM.dust(MT.White));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], OM.dust(MT.LightBlue));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Brown    ], OM.dust(MT.Brown));
		
		CR.shapeless(OM.dust(MT.Yellow   ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 0)});
		CR.shapeless(OM.dust(MT.Yellow   ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 1)});
		CR.shapeless(OM.dust(MT.Magenta  ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 2)});
		CR.shapeless(OM.dust(MT.Yellow   ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 3)});
		CR.shapeless(OM.dust(MT.Pink     ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 4)});
		CR.shapeless(OM.dust(MT.White    ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 5)});
		CR.shapeless(OM.dust(MT.LightBlue), CR.DEF_NCC, new Object[] {ST.make(this, 1, 6)});
		CR.shapeless(OM.dust(MT.Brown    ), CR.DEF_NCC, new Object[] {ST.make(this, 1, 7)});
		
		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(this, 1, 0), OM.dust(MT.Yellow   ));
		RM.ic2_extractor(ST.make(this, 1, 1), OM.dust(MT.Yellow   ));
		RM.ic2_extractor(ST.make(this, 1, 2), OM.dust(MT.Magenta  , U * 2));
		RM.ic2_extractor(ST.make(this, 1, 3), OM.dust(MT.Yellow   , U * 2));
		RM.ic2_extractor(ST.make(this, 1, 4), OM.dust(MT.Pink     , U * 2));
		RM.ic2_extractor(ST.make(this, 1, 5), OM.dust(MT.White    , U * 2));
		RM.ic2_extractor(ST.make(this, 1, 6), OM.dust(MT.LightBlue, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 7), OM.dust(MT.Brown    , U * 2));
		}
	}
}
