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

package gregtech.items;

import gregapi.data.*;
import gregapi.item.CreativeTab;
import gregapi.item.IItemRottable;
import gregapi.item.multiitem.MultiItemRandomWithCompat;
import gregapi.item.multiitem.behaviors.*;
import gregapi.item.multiitem.food.FoodStat;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import static gregapi.data.CS.*;

public class MultiItemFood extends MultiItemRandomWithCompat implements IItemRottable {
	public MultiItemFood(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized);
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Природа и продукты питания", this, (short)12000));
	}

	@Override
	public void addItems() {
		IL.Grass       .set(addItem(12000, "Трава"               , "Соберите 9 штук в тюк, чтобы высушить"        , Behavior_FeedGrass.INSTANCE, OD.itemGrass         , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1)));
		IL.Grass_Dry   .set(addItem(12001, "Сухая трава"         , "Полезно для создания простого воспламенителя" , Behavior_FeedGrass.INSTANCE, OD.itemGrassDry      , TICKS_PER_SMELT / 2, TC.stack(TC.MESSIS, 1)));
		IL.Grass_Moldy .set(addItem(12002, "Заплесневелая трава" , ""                                                                          , OD.itemGrassMoldy    , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		IL.Grass_Rotten.set(addItem(12003, "Гнилая трава"        , ""                                                                          , OD.itemGrassRotten   , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		IL.Crop_Rye    .set(addItem(12004, "Рожь"                , ""                                             , Behavior_FeedGrass.INSTANCE, "cropRye"            , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		IL.Crop_Oats   .set(addItem(12005, "Овес"                , ""                                             , Behavior_FeedGrass.INSTANCE, "cropOats"           , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		IL.Crop_Barley .set(addItem(12006, "Ячмень"              , ""                                             , Behavior_FeedGrass.INSTANCE, "cropBarley"         , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		IL.Crop_Rice   .set(addItem(12007, "Рис"                 , ""                                             , Behavior_FeedGrass.INSTANCE, "cropRice"           , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		RM.replicateOrganic( 1,  2, IL.Grass      .get(1));
		RM.replicateOrganic( 1,  3, IL.Crop_Wheat .get(1));
		RM.replicateOrganic( 1,  4, IL.Crop_Rye   .get(1));
		RM.replicateOrganic( 1,  5, IL.Crop_Oats  .get(1));
		RM.replicateOrganic( 1,  6, IL.Crop_Barley.get(1));
		RM.replicateOrganic( 1,  7, IL.Crop_Rice  .get(1));
		CR.shaped(IL.Bale           .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', OD.itemGrass);
		CR.shaped(IL.Bale_Dry       .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', OD.itemGrassDry);
		CR.shaped(IL.Bale_Moldy     .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', OD.itemGrassMoldy);
		CR.shaped(IL.Bale_Rotten    .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', OD.itemGrassRotten);
		CR.shaped(IL.Bale_Rye       .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', "cropRye");
		CR.shaped(IL.Bale_Oats      .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', "cropOats");
		CR.shaped(IL.Bale_Barley    .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', "cropBarley");
		CR.shaped(IL.Bale_Rice      .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', "cropRice");
		CR.shapeless(IL.Grass       .get(1), CR.DEF_NCC, new Object[] {OD.itemGrassTall});
		CR.shapeless(IL.Grass       .get(9), CR.DEF_NCC, new Object[] {OD.baleGrass});
		CR.shapeless(IL.Grass_Dry   .get(9), CR.DEF_NCC, new Object[] {OD.baleGrassDry});
		CR.shapeless(IL.Grass_Moldy .get(9), CR.DEF_NCC, new Object[] {OD.baleGrassMoldy});
		CR.shapeless(IL.Grass_Rotten.get(9), CR.DEF_NCC, new Object[] {OD.baleGrassRotten});
		CR.shapeless(IL.Crop_Rye    .get(9), CR.DEF_NCC, new Object[] {"baleRye"});
		CR.shapeless(IL.Crop_Oats   .get(9), CR.DEF_NCC, new Object[] {"baleOats"});
		CR.shapeless(IL.Crop_Barley .get(9), CR.DEF_NCC, new Object[] {"baleBarley"});
		CR.shapeless(IL.Crop_Rice   .get(9), CR.DEF_NCC, new Object[] {"baleRice"});
		ItemsGT.addNEIRedirects(IL.Bale       .get(1), IL.Grass       .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Dry   .get(1), IL.Grass_Dry   .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Moldy .get(1), IL.Grass_Moldy .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Rotten.get(1), IL.Grass_Rotten.get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Rye   .get(1), IL.Crop_Rye    .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Oats  .get(1), IL.Crop_Oats   .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Barley.get(1), IL.Crop_Barley .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Rice  .get(1), IL.Crop_Rice   .get(1));
		RM.DidYouKnow.addFakeRecipe(F, ST.array(ST.make(ToolsGT.sMetaTool, 1, ToolsGT.KNIFE, "Просто подрежьте этим высокую траву"), ST.make(ToolsGT.sMetaTool, 1, ToolsGT.SENSE, "Или подрежьте этим высокую траву"), ST.make(Blocks.tallgrass, 1, 1)), ST.array(IL.Grass.get(1), IL.Bale.get(1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		RM.DidYouKnow.addFakeRecipe(F, ST.array(IL.Bale.getWithName(1, "Положите тюк снаружи")), ST.array(IL.Bale_Dry.getWithName(1, "Когда на улице сухо"), IL.Bale_Moldy.getWithName(1, "Когда на улице сыро"), IL.Bale_Rotten.getWithName(1, "Когда на улице сыро и прошло больше времени"), IL.Grass_Dry.getWithName(1, "Когда на улице сухо"), IL.Grass_Moldy.getWithName(1, "Когда на улице сыро"), IL.Grass_Rotten.getWithName(1, "Когда на улице сыро и прошло больше времени")), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		
		
		
		IL.Cerublossom     .set(addItem(12010, "Цветок Церы"      , "Используется в магических целях"        , new Behavior_Turn_Into(IL.ARS_Cerublossom ), IL.ARS_Cerublossom.exists() ? TD.Creative.HIDDEN : "flowerCerublossom", TC.stack(TC.HERBA, 1), TC.stack(TC.PRAECANTIO, 1), TC.stack(TC.LUX, 1)));
		IL.DesertNova      .set(addItem(12011, "Звезда пустыни"   , "Используется в магических целях"        , new Behavior_Turn_Into(IL.ARS_DesertNova  ), IL.ARS_DesertNova .exists() ? TD.Creative.HIDDEN : "flowerDesertNova" , TC.stack(TC.HERBA, 1), TC.stack(TC.PRAECANTIO, 1), TC.stack(TC.LUX, 1)));
		RM.replicateOrganic( 2,  3, IL.ARS_Cerublossom.exists() ? IL.ARS_Cerublossom.get(1) : IL.Cerublossom.get(1));
		RM.replicateOrganic( 2,  4, IL.ARS_DesertNova .exists() ? IL.ARS_DesertNova .get(1) : IL.DesertNova .get(1));
		
		
		IL.Resin           .set(addItem(12050, "Резиновая смола"  , ""                                       , new Behavior_Turn_Into(IL.IC2_Resin       ), IL.IC2_Resin      .exists() ? TD.Creative.HIDDEN : OD.itemResin       , TC.stack(TC.LIMUS, 1), TICKS_PER_SMELT / 2));
		RM.replicateOrganic( 2,  5, IL.IC2_Resin.exists() ? IL.IC2_Resin.get(1) : IL.Resin.get(1));
		
		
		IL.Sticky_Goo      .set(addItem(12098, "Липкая слизь"     , "Совсем не вегеторианское"               , OD.slimeball, OD.slimeballAnimal, TC.stack(TC.LIMUS, 2)));
		IL.Slimeball_Borax .set(addItem(12099, "Слизистый шарик"  , "Боракс, смешанный с клеем"              , OD.slimeball, OD.slimeballBorax , TC.stack(TC.LIMUS, 2)));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.OREMATS.Borax), FL.Glue.make(250), NF, IL.Slimeball_Borax.get(1));
		
		
		IL.Remains_Plant   .set(addItem(12100, "Остатки растений" , ""                                       , OD.itemPlantRemains, TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1)));
		IL.Remains_Fruit   .set(addItem(12101, "Остатки фруктов"  , ""                                       , OD.itemPlantRemains, TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1)));
		IL.Remains_Veggie  .set(addItem(12102, "Остатки овощей"   , ""                                       , OD.itemPlantRemains, TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1)));
		IL.Remains_Nut     .set(addItem(12103, "Остатки орехов"   , ""                                       , OD.itemPlantRemains, TICKS_PER_SMELT / 2, TC.stack(TC.HERBA, 1)));
		
		
		IL.Bark_Dry        .set(addItem(12201, "Сухая кора"       , "Полезно для создания простого воспламенителя", OD.itemBarkDry, TICKS_PER_SMELT / 4, TC.stack(TC.ARBOR, 1), new OreDictItemData(MT.Bark, U2)));
		RM.replicateOrganic( 2,  6, IL.Bark_Dry.get(1));
		
		
		IL.Mud_Ball        .set(addItem(12300, "Грязь"            , ""                                       , OD.itemMud , TC.stack(TC.TERRA, 1)                                         )); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		IL.Clay_Ball_Brown .set(addItem(12310, "Коричневая глина" , "Содержит небольшое количество лития"    , OD.itemClay, TC.stack(TC.TERRA, 1), new OreDictItemData(MT.ClayBrown   , U))); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		IL.Clay_Ball_Red   .set(addItem(12311, "Красная глина"    , "Идеально сбалансированный без эксплойтов!", OD.itemClay, TC.stack(TC.TERRA, 1), new OreDictItemData(MT.ClayRed     , U))); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		IL.Clay_Ball_Yellow.set(addItem(12312, "Желтая глина"     , "Бентонитовая глина"                     , OD.itemClay, TC.stack(TC.TERRA, 1), new OreDictItemData(MT.Bentonite   , U))); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		IL.Clay_Ball_Blue  .set(addItem(12313, "Синяя глина"      , "Палыгорскитовая глина / Сукновальная глина", OD.itemClay, TC.stack(TC.TERRA, 1), new OreDictItemData(MT.Palygorskite, U))); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		IL.Clay_Ball_White .set(addItem(12314, "Белая глина"      , "Каолинитовая глина / Фарфоровая глина"  , OD.itemClay, TC.stack(TC.TERRA, 1), new OreDictItemData(MT.Kaolinite   , U))); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		CR.remove(ST.make(Items.clay_ball, 1, 0), ST.make(Items.clay_ball, 1, 0), NI, ST.make(Items.clay_ball, 1, 0), ST.make(Items.clay_ball, 1, 0));
		RM.generify(IL.Clay_Ball_Brown .get(1), ST.make(Items.clay_ball, 1, 0));
		RM.generify(IL.Clay_Ball_Red   .get(1), ST.make(Items.clay_ball, 1, 0));
		RM.generify(IL.Clay_Ball_Yellow.get(1), ST.make(Items.clay_ball, 1, 0));
		RM.generify(IL.Clay_Ball_Blue  .get(1), ST.make(Items.clay_ball, 1, 0));
		RM.generify(IL.Clay_Ball_White .get(1), ST.make(Items.clay_ball, 1, 0));
		RM.add_smelting(IL.Clay_Ball_Brown .get(1), ST.make(Items.brick, 1, 0), F, F, T);
		RM.add_smelting(IL.Clay_Ball_Red   .get(1), ST.make(Items.brick, 1, 0), F, F, T);
		RM.add_smelting(IL.Clay_Ball_Yellow.get(1), ST.make(Items.brick, 1, 0), F, F, T);
		RM.add_smelting(IL.Clay_Ball_Blue  .get(1), ST.make(Items.brick, 1, 0), F, F, T);
		RM.add_smelting(IL.Clay_Ball_White .get(1), ST.make(Items.brick, 1, 0), F, F, T);
		CR.shaped    (OP.plate.mat(MT.Clay         , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', Items.clay_ball);
		CR.shaped    (OP.plate.mat(MT.ClayBrown    , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', IL.Clay_Ball_Brown);
		CR.shaped    (OP.plate.mat(MT.ClayRed      , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', IL.Clay_Ball_Red);
		CR.shaped    (OP.plate.mat(MT.Bentonite    , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', IL.Clay_Ball_Yellow);
		CR.shaped    (OP.plate.mat(MT.Palygorskite , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', IL.Clay_Ball_Blue);
		CR.shaped    (OP.plate.mat(MT.Kaolinite    , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', IL.Clay_Ball_White);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 0), CR.DEF_NCC, "XX", "XX", 'X', IL.Mud_Ball);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 1), CR.DEF_NCC, "XX", "XX", 'X', IL.Clay_Ball_Brown);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 3), CR.DEF_NCC, "XX", "XX", 'X', IL.Clay_Ball_Red);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 4), CR.DEF_NCC, "XX", "XX", 'X', IL.Clay_Ball_Yellow);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 5), CR.DEF_NCC, "XX", "XX", 'X', IL.Clay_Ball_Blue);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 6), CR.DEF_NCC, "XX", "XX", 'X', IL.Clay_Ball_White);
		CR.shaped    (ST.make(Blocks.clay       , 1, 0), CR.DEF_NCC, "XX", "XX", 'X', Items.clay_ball);
		CR.shapeless (IL.Mud_Ball               .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 0)});
		CR.shapeless (IL.Clay_Ball_Brown        .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 1)});
		CR.shapeless (IL.Clay_Ball_Red          .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 3)});
		CR.shapeless (IL.Clay_Ball_Yellow       .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 4)});
		CR.shapeless (IL.Clay_Ball_Blue         .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 5)});
		CR.shapeless (IL.Clay_Ball_White        .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 6)});
		CR.shapeless (ST.make(Items.clay_ball   , 4, 0), CR.DEF_NCC, new Object[] {Blocks.clay});
		RM.compactunpack(IL.Mud_Ball            .get(4), ST.make(BlocksGT.Diggables, 1, 0));
		RM.compactunpack(IL.Clay_Ball_Brown     .get(4), ST.make(BlocksGT.Diggables, 1, 1));
		RM.compactunpack(IL.Clay_Ball_Red       .get(4), ST.make(BlocksGT.Diggables, 1, 3));
		RM.compactunpack(IL.Clay_Ball_Yellow    .get(4), ST.make(BlocksGT.Diggables, 1, 4));
		RM.compactunpack(IL.Clay_Ball_Blue      .get(4), ST.make(BlocksGT.Diggables, 1, 5));
		RM.compactunpack(IL.Clay_Ball_White     .get(4), ST.make(BlocksGT.Diggables, 1, 6));
		RM.compactunpack(ST.make(Items.clay_ball, 4, 0), ST.make(Blocks.clay       , 1, 0));
		RM.RollingMill.addRecipe1(T, 16, 32, ST.make(Items.clay_ball, 1, 0), OP.plate.mat(MT.Clay        , 1));
		RM.RollingMill.addRecipe1(T, 16, 32, IL.Clay_Ball_Brown     .get(1), OP.plate.mat(MT.ClayBrown   , 1));
		RM.RollingMill.addRecipe1(T, 16, 32, IL.Clay_Ball_Red       .get(1), OP.plate.mat(MT.ClayRed     , 1));
		RM.RollingMill.addRecipe1(T, 16, 32, IL.Clay_Ball_Yellow    .get(1), OP.plate.mat(MT.Bentonite   , 1));
		RM.RollingMill.addRecipe1(T, 16, 32, IL.Clay_Ball_Blue      .get(1), OP.plate.mat(MT.Palygorskite, 1));
		RM.RollingMill.addRecipe1(T, 16, 32, IL.Clay_Ball_White     .get(1), OP.plate.mat(MT.Kaolinite   , 1));
		RM.Compressor .addRecipe1(T, 16, 16, OM.dust(MT.Clay        ), ST.make(Items.clay_ball, 1, 0));
		RM.Compressor .addRecipe1(T, 16, 16, OM.dust(MT.ClayBrown   ), IL.Clay_Ball_Brown     .get(1));
		RM.Compressor .addRecipe1(T, 16, 16, OM.dust(MT.ClayRed     ), IL.Clay_Ball_Red       .get(1));
		RM.Compressor .addRecipe1(T, 16, 16, OM.dust(MT.Bentonite   ), IL.Clay_Ball_Yellow    .get(1));
		RM.Compressor .addRecipe1(T, 16, 16, OM.dust(MT.Palygorskite), IL.Clay_Ball_Blue      .get(1));
		RM.Compressor .addRecipe1(T, 16, 16, OM.dust(MT.Kaolinite   ), IL.Clay_Ball_White     .get(1));
		RM.mortarize(1, ST.make(Items.clay_ball   , 1, W), OM.dust(MT.Clay));
		RM.mortarize(1, IL.Clay_Ball_Brown        .get(1), OM.dust(MT.ClayBrown));
		RM.mortarize(1, IL.Clay_Ball_Red          .get(1), OM.dust(MT.ClayRed));
		RM.mortarize(1, IL.Clay_Ball_Yellow       .get(1), OM.dust(MT.Bentonite));
		RM.mortarize(1, IL.Clay_Ball_Blue         .get(1), OM.dust(MT.Palygorskite));
		RM.mortarize(1, IL.Clay_Ball_White        .get(1), OM.dust(MT.Kaolinite));
		RM.mortarize(4, ST.make(Blocks.clay       , 1, W), OM.dust(MT.Clay, U*4));
		RM.mortarize(4, ST.make(BlocksGT.Diggables, 1, 1), OM.dust(MT.ClayBrown, U*4));
		RM.mortarize(4, ST.make(BlocksGT.Diggables, 1, 3), OM.dust(MT.ClayRed, U*4));
		RM.mortarize(4, ST.make(BlocksGT.Diggables, 1, 4), OM.dust(MT.Bentonite, U*4));
		RM.mortarize(4, ST.make(BlocksGT.Diggables, 1, 5), OM.dust(MT.Palygorskite, U*4));
		RM.mortarize(4, ST.make(BlocksGT.Diggables, 1, 6), OM.dust(MT.Kaolinite, U*4));
		for (FluidStack tWater : FL.waters(125, 100)) {
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.Ceramic      ), FL.mul(tWater, 5), NF, ST.make(Items.clay_ball, 1, 0));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.Clay         ),        tWater    , NF, ST.make(Items.clay_ball, 1, 0));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.ClayRed      ),        tWater    , NF, IL.Clay_Ball_Red.get(1));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.ClayBrown    ),        tWater    , NF, IL.Clay_Ball_Brown.get(1));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.Bentonite    ),        tWater    , NF, IL.Clay_Ball_Yellow.get(1));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.Palygorskite ),        tWater    , NF, IL.Clay_Ball_Blue.get(1));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.Kaolinite    ),        tWater    , NF, IL.Clay_Ball_White.get(1));
		}
		
		IL.Tusk         .set(addItem(12400, "Бивень"           , ""                 , OD.itemTusk              , TC.stack(TC.BESTIA, 2), TC.stack(TC.FAMES, 2)));
		IL.Tusk_Hoglin  .set(addItem(12401, "Хоглинский бивень", "нет золота"       , OD.itemTusk              , TC.stack(TC.BESTIA, 2), TC.stack(TC.FAMES, 2)));
		IL.Tusk_Boar    .set(addItem(12402, "Клык кабана"      , ""                 , OD.itemTusk              , TC.stack(TC.BESTIA, 2), TC.stack(TC.FAMES, 2)));
		IL.Tusk_Elephant.set(addItem(12403, "Слоновий бивень"  , "Слоновая кость"   , OD.itemTusk, OD.itemIvory, TC.stack(TC.BESTIA, 3), TC.stack(TC.FAMES, 3), TC.stack(TC.LUCRUM, 3)));
		
		IL.Hoof         .set(addItem(12500, "Копыто"           , ""                 , OD.itemHoof              , TC.stack(TC.BESTIA, 2), TC.stack(TC.ITER, 2)));
		IL.Hoof_Cow     .set(addItem(12501, "Коровье копыто"   , ""                 , OD.itemHoof              , TC.stack(TC.BESTIA, 2), TC.stack(TC.ITER, 2)));
		IL.Hoof_Horse   .set(addItem(12502, "Лошадиное копыто" , "не достоин гонок" , OD.itemHoof              , TC.stack(TC.BESTIA, 2), TC.stack(TC.ITER, 2)));
		IL.Hoof_Mule    .set(addItem(12503, "Копыто мула"      , ""                 , OD.itemHoof              , TC.stack(TC.BESTIA, 2), TC.stack(TC.ITER, 2)));
		IL.Hoof_Donkey  .set(addItem(12504, "Ослиное копыто"   , ""                 , OD.itemHoof              , TC.stack(TC.BESTIA, 2), TC.stack(TC.ITER, 2)));
		IL.Hoof_Deer    .set(addItem(12505, "Оленье копыто"    , ""                 , OD.itemHoof              , TC.stack(TC.BESTIA, 2), TC.stack(TC.ITER, 2)));
		
		IL.Horn         .set(addItem(12600, "Рог"              , ""                 , OD.itemHorn              , TC.stack(TC.BESTIA, 2), TC.stack(TC.TELUM, 2)));
		IL.Horn_Cow     .set(addItem(12601, "Коровий рог"      , ""                 , OD.itemHorn              , TC.stack(TC.BESTIA, 2), TC.stack(TC.TELUM, 2)));
		IL.Horn_Sheep   .set(addItem(12602, "Бараний рог"      , "От рогатой овцы"  , OD.itemHorn              , TC.stack(TC.BESTIA, 2), TC.stack(TC.TELUM, 2)));
		
		IL.Antler       .set(addItem(12700, "Оленьи рога"      , ""                 , OD.itemAntler            , TC.stack(TC.BESTIA, 2), TC.stack(TC.INSTRUMENTUM, 2)));
		IL.Antler_Deer  .set(addItem(12701, "Красный оленьи рога", "о родной..."    , OD.itemAntler            , TC.stack(TC.BESTIA, 2), TC.stack(TC.INSTRUMENTUM, 2)));
		
		
		
		
		
		
		
		IL.Comb_Honey   .set(addItem(30000, "Медовые соты"    , "", OD.beeComb, OD.materialHoneycomb, "foodFilledhoneycomb", TC.stack(TC.LIMUS, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.SANO, 1), new FoodStat( 1, 0.1F, 20, C+37, 0.50F,  0,  0,  0, 20,  0, EnumAction.eat, null, F, T, F, T))); Sandwiches.INGREDIENTS.put(last(), (byte)22);
		IL.Comb_Water   .set(addItem(30001, "Водные соты"     , "", OD.beeComb, TC.stack(TC.AQUA, 2), new FoodStat( 1, 0.1F, 20, C+37, 0.50F,  0,  0,  0, 20,  0, EnumAction.eat, null, F, T, F, T))); Sandwiches.INGREDIENTS.put(last(), (byte)22);
		IL.Comb_Magic   .set(addItem(30002, "Магические соты" , "", OD.beeComb, TC.stack(TC.PRAECANTIO, 2)));
		IL.Comb_Nether  .set(addItem(30003, "Адские соты"     , "", OD.beeComb, TC.stack(TC.IGNIS, 2)));
		IL.Comb_End     .set(addItem(30004, "Соты Края"       , "", OD.beeComb, TC.stack(TC.ALIENIS, 2)));
		IL.Comb_Rock    .set(addItem(30005, "Каменные соты"   , "", OD.beeComb, TC.stack(TC.TERRA, 2)));
		IL.Comb_Jungle  .set(addItem(30006, "Соты джунглей"   , "", OD.beeComb, TC.stack(TC.HERBA, 2), new FoodStat( 1, 0.1F, 20, C+37, 0.50F,  0,  0,  0, 20,  0, EnumAction.eat, null, F, T, F, T))); Sandwiches.INGREDIENTS.put(last(), (byte)22);
		IL.Comb_Frozen  .set(addItem(30007, "Морозные соты"   , "", OD.beeComb, TC.stack(TC.GELUM, 2)));
		IL.Comb_Shroom  .set(addItem(30008, "Грибные соты"    , "", OD.beeComb, TC.stack(TC.VITIUM, 1), TC.stack(TC.HERBA, 1), new FoodStat( 1, 0.1F, 20, C+37, 0.50F,  0,  0,  0, 20,  0, EnumAction.eat, null, F, T, F, T))); Sandwiches.INGREDIENTS.put(last(), (byte)22);
		IL.Comb_Sandy   .set(addItem(30009, "Песчаные соты"   , "", OD.beeComb, TC.stack(TC.TERRA, 1), TC.stack(TC.IGNIS, 1)));
		
		IL.Comb_Clay    .set(addItem(30100, "Глиняные соты"   , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.TERRA, 1), TC.stack(TC.AQUA, 1)));
		IL.Comb_Sticky  .set(addItem(30101, "Липкие соты"     , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.LIMUS, 2)));
		IL.Comb_Royal   .set(addItem(30102, "Королевские соты", "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.LIMUS, 1), TC.stack(TC.SANO, 1), new FoodStat( 1, 0.1F, 20, C+37, 0.50F,  0,  0,  0, 20,  0, EnumAction.eat, null, F, T, F, T))); Sandwiches.INGREDIENTS.put(last(), (byte)22);
		IL.Comb_Soul    .set(addItem(30103, "Соты души"       , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.SPIRITUS, 2)));
		IL.Comb_Amnesic .set(addItem(30104, "Амнезийные соты" , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.LIMUS, 1), TC.stack(TC.STRONTIO, 1)));
		IL.Comb_Military.set(addItem(30105, "Военные соты"    , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.TELUM, 2)));
		
		IL.Comb_Pyro    .set(addItem(30200, "Огненные соты"   , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.IGNIS, 2)));
		IL.Comb_Cryo    .set(addItem(30201, "Крио соты"       , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.GELUM, 2)));
		IL.Comb_Aero    .set(addItem(30202, "Аэро соты"       , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.AER  , 2)));
		IL.Comb_Tera    .set(addItem(30203, "Земляные соты"   , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.TERRA, 2)));
		
		
		
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Honey   .get(1), NF   , FL.Honey                                .make( 100), OM.dust(MT.WaxBee)                , IL.FR_Propolis.get(1));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Water   .get(1), NF   , FL.Water                                .make(1000), OM.dust(MT.WaxBee)                );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Magic   .get(1), NF   , FL.Ambrosia                             .make( 100), OM.dust(MT.WaxMagic)              );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Nether  .get(1), NF   , FL.Blaze                                .make(   L), OM.dust(MT.WaxRefractory)         );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000,  1000}           , IL.Comb_End     .get(1), NF   , FL.Dragon_Breath                        .make( 125), OM.dust(MT.Endstone)              , IL.FR_Propolis_Pulsating.get(1), IL.EtFu_Chorus_Fruit.get(1));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Rock    .get(1), NF   , FL.Concrete                             .make(   L), OM.dust(MT.Stone)                 );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Jungle  .get(1), NF   , MT.Chocolate                          .liquid(U, T), OM.dust(MT.Cocoa)                 , IL.FR_Propolis_Silky.get(1, ST.make(Items.string, 1, 0)));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Frozen  .get(1), NF   , FL.Ice                                  .make(1000), OM.dust(MT.Ice)                   );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] { 6000,  6000}                  , IL.Comb_Shroom  .get(1), NF   , FL.Soup_Mushroom                        .make(1000), ST.make(Blocks.red_mushroom_block, 1, 0), ST.make(Blocks.brown_mushroom_block, 1, 0));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Sandy   .get(1), NF   , FL.Juice_Cactus                         .make( 100), ST.make(Blocks.sand, 1, 0)        );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] { 2000,2000,2000,2000,2000,2000}, IL.Comb_Clay    .get(1), NF   , FL.Concrete                             .make(   L), OM.dust(MT.Clay)                  , OM.dust(MT.ClayBrown), OM.dust(MT.ClayRed), OM.dust(MT.Bentonite), OM.dust(MT.Palygorskite), OM.dust(MT.Kaolinite));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  3000}                  , IL.Comb_Sticky  .get(1), NF   , FL.Latex                                .make(   L), OM.dust(MT.WaxBee)                , IL.FR_Propolis_Sticky.get(1, IL.IC2_Resin.get(1, IL.Resin.get(1))));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000}                         , IL.Comb_Royal   .get(1), ZL_FS, FL.array(FL.Honey.make(50), FL.RoyalJelly.make(10)), OM.dust(MT.WaxBee)                );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  9000}                  , IL.Comb_Soul    .get(1), NF   , FL.Oil_Soulsand                         .make(  50), OM.dust(MT.WaxSoulful)            , OM.dust(MT.SoulSand));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Amnesic .get(1), NF   , FL                                      .lube(1000), OM.dust(MT.WaxAmnesic)            );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,   500,   500,   250}    , IL.Comb_Military.get(1), NF   , FL.Potion_Harm_1                        .make(  50), OM.dust(MT.Bone)                  , ST.make(Items.bone, 1, 0), ST.make(Items.rotten_flesh, 1, 0), ST.make(Items.spider_eye, 1, 0));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Pyro    .get(1), NF   , FL.Blaze                                .make( L/2), OM.dust(MT.Blaze, U9)             , OP.stick.mat(MT.Blaze, 1));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Cryo    .get(1), NF   , FL.Ice                                  .make( 500), OM.dust(MT.Blizz, U9)             , OP.stick.mat(MT.Blizz, 1));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Aero    .get(1), NF   , FL.Dragon_Breath                        .make(  50), OM.dust(MT.Blitz, U9)             , OP.stick.mat(MT.Blitz, 1));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Tera    .get(1), NF   , FL.Concrete                             .make(   L), OM.dust(MT.Basalz, U9)            , OP.stick.mat(MT.Basalz, 1));
		
		
		IL.Food_Lemon                          .set(addItem(    0, "Лимон"                                    , "Не делайте лимонад"         , "cropLemon"                 , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_CONDUCTIVE, 300, 0, 70), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Lemon_Sliced                   .set(addItem(    1, "Долька лимона"                            , "Идеально, чтобы поставить свой напиток"                   , new FoodStat( 0, 0.150F,   0, C+36,  0.30F,   0,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_CONDUCTIVE, 300, 0, 70), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)28);
		RM.replicateOrganic( 3,  4, IL.Food_Lemon.get(1));
		CR.shaped(IL.Food_Lemon_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropLemon");


		IL.Food_Tomato                         .set(addItem(   10, "Томат"                                    , "То что надо для кетчупа"               , "cropTomato"     , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Tomato_Sliced                  .set(addItem(   11, "Долька томата"                            , "То что надо для кетчупа"                                  , new FoodStat( 0, 0.150F,   0, C+36,  0.30F,   0,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)36);
		RM.replicateOrganic( 4,  5, IL.Food_Tomato.get(1));
		CR.shaped(IL.Food_Tomato_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropTomato");
		
		
		IL.Food_MTomato                        .set(addItem(   20, "Максимальный томат"                       , "Десять сердечек в одном помидоре"    , "cropTomato"       , new FoodStat( 9, 1.000F,  50, C+36,  0.30F,   0,   0,   0,  10,   0, EnumAction.eat, null                                 , F, T, F, T, Potion.regeneration.id, 60, 4, 100), TC.stack(TC.MESSIS, 1), TC.stack(TC.SANO, 3), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 4,  6, IL.Food_MTomato.get(1));
		RM.food_can(IL.Food_MTomato.get(1),10, "Canned Max Tomato", IL.CANS_VEGGIE);
		
		
		IL.Food_Onion                          .set(addItem(   30, "Лук"                                      , "Принимая во внимание весь вкус" , "cropOnion"             , new FoodStat( 1, 1.200F,   0, C+36,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Onion_Sliced                   .set(addItem(   31, "Долька лука"                              , "ЛУК, ОБЪЕДИНЯЙСЯ!"                                        , new FoodStat( 0, 0.300F,   0, C+36,  0.30F,   0,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)31);
		RM.replicateOrganic( 4,  7, IL.Food_Onion.get(1));
		CR.shaped(IL.Food_Onion_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropOnion");
		
		
		IL.Food_Cucumber                       .set(addItem(   40, "Огурец"                                   , "Это сорт арбуза или сорт тыквы?", "cropCucumber"          , new FoodStat( 1, 1.200F,   0, C+36,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Cucumber_Sliced                .set(addItem(   41, "Ломтик огурца"                            , "Это не нарезанный морской огурец!"                        , new FoodStat( 0, 0.300F,   0, C+36,  0.30F,   0,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)23);
		IL.Food_Pickle                         .set(addItem(   42, "Соленый огурец"                           , "Не морской огурец! И не Рик!", "cropPickle", "foodPickles", new FoodStat( 1, 1.200F,   0, C+36,  0.30F,   4,   0,   0,   4,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.VENEMUM, 1)));
		IL.Food_Pickle_Sliced                  .set(addItem(   43, "Ломтик соленого огурца"                   , "Вы, кажется, в рассоле."                                  , new FoodStat( 0, 0.300F,   0, C+36,  0.30F,   1,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)41);
		RM.replicateOrganic( 4,  8, IL.Food_Cucumber.get(1));
		RM.replicateOrganic( 4, 13, IL.Food_Pickle.get(1));
		CR.shaped(IL.Food_Cucumber_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropCucumber");
		CR.shaped(IL.Food_Pickle_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropPickle");
		CR.shaped(IL.Food_Pickle_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "foodPickles");
		
		
		IL.Food_Chili_Pepper                   .set(addItem(   50, "Перец чили"                               , "Это красный и острый"           , "cropChilipepper"        , new FoodStat( 1, 1.200F, -10, C+40,  0.50F,   0,   0,  10,   0,   0, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_FLAMMABLE, 300, 0, 70, Potion.confusion.id, 200, 1, 40), TC.stack(TC.MESSIS, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)20);
		RM.replicateOrganic( 4,  9, IL.Food_Chili_Pepper.get(1));
		
		
		IL.Food_Grapes_Green                   .set(addItem(   60, "Зеленый виноград"                         , "Источник вина"              , "cropGrapeGreen"             , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Raisins_Green                  .set(addItem(   61, "Зеленый изюм"                             , "Сушеный виноград"                , "foodRaisins"           , new FoodStat( 2, 0.600F,   0, C+37,  0.20F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 3,  5, IL.Food_Grapes_Green.get(1));
		
		IL.Food_Grapes_White                   .set(addItem(   63, "Белый виноград"                           , "Источник вина"              , "cropGrapeWhite"             , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Raisins_White                  .set(addItem(   64, "Белый изюм"                               , "Сушеный виноград"                , "foodRaisins"           , new FoodStat( 2, 0.600F,   0, C+37,  0.20F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 3,  6, IL.Food_Grapes_White.get(1));
		
		IL.Food_Grapes_Red                     .set(addItem(   66, "Красный виноград"                         , "Источник вина"              , "cropGrapeRed"               , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Raisins_Red                    .set(addItem(   67, "Красный изюм"                             , "Сушеный виноград"                , "foodRaisins"           , new FoodStat( 2, 0.600F,   0, C+37,  0.20F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 3,  7, IL.Food_Grapes_Red.get(1));
		
		IL.Food_Grapes_Purple                  .set(addItem(   70, "Фиолетовый виноград"                      , "Источник вина"              , "cropGrapePurple"            , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Raisins_Purple                 .set(addItem(   71, "Фиолетовый изюм"                          , "Сушеный виноград"                , "foodRaisins"           , new FoodStat( 2, 0.600F,   0, C+37,  0.20F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 3,  8, IL.Food_Grapes_Purple.get(1));
		
		IL.Food_Raisins_Chocolate              .set(addItem(   72, "Шоколадный изюм"                          , "Сушеный виноград в шоколаде", "foodChocolateraisins"       , new FoodStat( 3, 1.200F,   0, C+37,  0.20F,   0,   0,   0,  40,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.SANO, 1)));
		
		
		IL.Food_Carrot                         .set(ST.make(Items.carrot, 1, 0)); FoodsGT.put(ST.make(Items.carrot, 1, W), 0, 0, 0, 8, 0);//                                , new FoodStat( 5, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1), Behavior_FeedPig.INSTANCE));
		IL.Food_Carrot_Sliced                  .set(addItem(   81, "Ломтик моркови"                           , "Нарезанный Гоку"                                           , new FoodStat( 0, 0.300F,   0, C+37,  0.30F,   0,   0,   0,   2,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)18);
		RM.replicateOrganic( 4,  9, IL.Food_Carrot.get(1));
		CR.shaped(IL.Food_Carrot_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropCarrot");
		
		
		IL.Food_Potato_Poisonous               .set(ST.make(Items.poisonous_potato, 1, 0)); FoodsGT.put(ST.make(Items.poisonous_potato, 1, W), 0, 0, 0, 4, 0);
		RM.replicateOrganic( 4, 11, IL.Food_Potato_Poisonous.get(1));
		
		
		IL.Food_Potato                         .set(ST.make(Items.potato, 1, 0)); FoodsGT.put(ST.make(Items.potato, 1, W), 0, 0, 0, 4, 0);
		IL.Food_Potato_On_Stick                .set(addItem(32700, "Картофель на палочке"        , "Полностью похож на клешню краба", new OreDictItemData(MT.Potato,U,ANY.Wood,U2), new FoodStat( 1, 0.600F,   0, C+37,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, IL.Stick.get(1), F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.ARBOR, 1)));
		RM.replicateOrganic( 4, 10, IL.Food_Potato.get(1));
		CR.shapeless(IL.Food_Potato_On_Stick.get(1), CR.DEF, new Object[] {OP.stick.dat(ANY.Wood), "cropPotato"});
		
		
		IL.Food_Potato_Baked                   .set(ST.make(Items.baked_potato, 1, 0)); FoodsGT.put(ST.make(Items.baked_potato, 1, W), 0, 0, 0, 4, 0);
		IL.Food_Potato_On_Stick_Roasted        .set(addItem(32701, "Жареный картофель на палочке", "По-прежнему выглядит как клешня краба"  , new OreDictItemData(MT.Potato,U,ANY.Wood,U2), new FoodStat( 5, 1.200F,   0, C+38,  0.50F,   0,   0,   0,   4,   0, EnumAction.eat, IL.Stick.get(1), F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.ARBOR, 1), TC.stack(TC.IGNIS, 1)));
		RM.add_smelting(IL.Food_Potato_On_Stick.get(1), IL.Food_Potato_On_Stick_Roasted.get(1), F, T, F);
		CR.shapeless(IL.Food_Potato_On_Stick_Roasted.get(1), CR.DEF, new Object[] {OP.stick.dat(ANY.Wood), IL.Food_Potato_Baked});
		for (OreDictMaterial tMat : ANY.Wood.mToThis) {ItemStack tStick = OP.stick.mat(tMat, 1); if (ST.valid(tStick))
		RM.Boxinator.addRecipe2(T, 16, 16, IL.Food_Potato_Baked.get(1), tStick, IL.Food_Potato_On_Stick_Roasted.get(1));
		}
		
		IL.Food_Fries_Raw                      .set(addItem( 8000, "Картофельные полоски"                     , "Это картофель в полоску"                                               , new FoodStat( 1, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   5,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), new OreDictItemData(MT.Potato, U))); setFluidContainerStats(mLastID, 0, 8);
		IL.Food_Fries                          .set(addItem( 8010, "Фри"                                      , "Не путать с курьером Фрай", "foodFries"      , new FoodStat( 7, 1.200F,   0, C+38,  0.50F,   0,   0,  10,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));             setFluidContainerStats(mLastID, 0, 8);
		IL.Food_Fries_Packaged                 .set(addItem( 8011, "Фри"                                      , "Кетчуп не включен"                                        , new FoodStat( 7, 1.200F,   0, C+38,  0.50F,   0,   0,  10,  10,   0, EnumAction.eat, OP.scrapGt.mat(MT.Paper, 16)         , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));
		RM.Bath.addRecipe1(T,  0,   16, IL.Food_Fries_Raw.get(1), MT.FryingOilHot.liquid(U/100, T), NF, IL.Food_Fries.get(1));
		CR.shaped(IL.Food_Fries_Raw.get(1), CR.DEF_NCC, "k", "X", 'X', "cropPotato");
		CR.shapeless(IL.Food_Fries_Packaged.get(1), CR.DEF_NCC, new Object[] {OP.plateDouble.dat(MT.Paper), IL.Food_Fries});
		RM.Boxinator.addRecipe2(T, 16, 16, IL.Food_Fries.get(1), OP.plateDouble.mat(MT.Paper, 1), IL.Food_Fries_Packaged.get(1));
		RM.Unboxinator.addRecipe1(T, 16, 16, IL.Food_Fries_Packaged.get(1), IL.Food_Fries.get(1), OP.scrapGt.mat(MT.Paper, 16));
		
		
		IL.Food_PotatoChips_Raw                .set(addItem( 9000, "Картофельные чипсы (сырые)"               , "Как картофель"                                          , new FoodStat( 1, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), new OreDictItemData(MT.Potato, U))); setFluidContainerStats(mLastID, 0, 8);
		IL.Food_PotatoChips                    .set(addItem( 9010, "Картофельные чипсы"                       , "Хрустящие"                                                     , new FoodStat( 7, 1.200F,   0, C+37,  0.10F,   0,   0,  10,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));             setFluidContainerStats(mLastID, 0, 8);
		IL.Food_PotatoChips_Packaged           .set(addItem( 9011, "Пакетик картофельных чипсов"              , "Полный восхитительного воздуха"                                       , new FoodStat( 7, 1.200F,   0, C+37,  0.10F,   0,   0,  10,  10,   0, EnumAction.eat, OP.scrapGt.mat(MT.Al, 2)             , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));
		RM.add_smelting(IL.Food_PotatoChips_Raw.get(1), IL.Food_PotatoChips.get(1), F, T, F);
		CR.shaped(IL.Food_PotatoChips_Raw.get(1), CR.DEF_NCC, "kX", 'X', "cropPotato");
		CR.shapeless(IL.Food_PotatoChips_Packaged.get(1), CR.DEF_NCC, new Object[] {OP.foil.dat(MT.Al), IL.Food_PotatoChips});
		RM.Boxinator.addRecipe2(T, 16, 16, IL.Food_PotatoChips.get(1), OP.foil.mat(MT.Al, 1), IL.Food_PotatoChips_Packaged.get(1));
		RM.Unboxinator.addRecipe1(T, 16, 16, IL.Food_PotatoChips_Packaged.get(1), IL.Food_PotatoChips.get(1), OP.scrapGt.mat(MT.Al, 2));
		
		
		IL.Food_ChiliChips                     .set(addItem( 9020, "Чипсы с чили"                             , "Острые"                                                       , new FoodStat( 7, 1.200F, -10, C+40,  0.30F,   0,   0,  30,  10,   0, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_FLAMMABLE, 300, 0, 70), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1))); setFluidContainerStats(mLastID, 0, 8);
		IL.Food_ChiliChips_Packaged            .set(addItem( 9021, "Пакетик чили-чипсов"                      , "Хватит шуметь, Бадж!"                                     , new FoodStat( 7, 1.200F, -10, C+40,  0.30F,   0,   0,  30,  10,   0, EnumAction.eat, OP.scrapGt.mat(MT.Al, 2)             , F, T, F, T, PotionsGT.ID_FLAMMABLE, 300, 0, 70), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));
		CR.shapeless(IL.Food_ChiliChips_Packaged.get(1), CR.DEF_NCC, new Object[] {OP.foil.dat(MT.Al), IL.Food_ChiliChips});
		RM.Boxinator.addRecipe2(T, 16, 16, IL.Food_ChiliChips.get(1), OP.foil.mat(MT.Al, 1), IL.Food_ChiliChips_Packaged.get(1));
		RM.Unboxinator.addRecipe1(T, 16, 16, IL.Food_ChiliChips_Packaged.get(1), IL.Food_ChiliChips.get(1), OP.scrapGt.mat(MT.Al, 2));
		
		
		RM.replicateOrganic( 4, 12, ST.make(Blocks.pumpkin, 1, 0));
		
		
		IL.Food_Banana                         .set(addItem(   90, "Банан"                                    , "Для масштаба"                , "cropBanana"                  , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0,4,EnumAction.eat, null                                , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 1, 70), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Banana_Sliced                  .set(addItem(   91, "Ломтик банана"                            , "Еда для миньонов"                                            , new FoodStat( 0, 0.150F,   0, C+36,  0.30F,   0,   0,   0,   2,   0,1,EnumAction.eat, null                                , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 1, 70), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)17);
		RM.replicateOrganic( 3,  9, IL.Food_Banana.get(1));
		CR.shaped(IL.Food_Banana_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropBanana");
		
		
		IL.Food_Pomegranate                    .set(addItem(  100, "Гранат"                                   , "Яблоко из семечек"           , "cropPomegranate"             , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.BESTIA, 1)));
		IL.Food_Pomeraisins                    .set(addItem(  101, "Гранатовый изюм"                          , "Любимая еда маленьких собак" , "foodRaisins"                 , new FoodStat( 2, 0.600F,   0, C+37,  0.20F,   0,   0,   0,   2,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.BESTIA, 1), Behavior_FeedDog.INSTANCE));
		RM.replicateOrganic( 3, 10, IL.Food_Pomegranate.get(1));
		
		
		IL.Food_Blueberry                      .set(addItem(  110, "Черника"                                  , ""                            , "cropBlueberry"               , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.SENSUS, 1)));
		RM.replicateOrganic( 5,  6, IL.Food_Blueberry.get(1));
		BushesGT.put(IL.Food_Blueberry.get(1), 0x22ff22, 0xffcccc, 0x6666dd, 0x0000ff);
		
		IL.Food_Gooseberry                     .set(addItem(  120, "Крыжовник"                                , ""                            , "cropGooseberry"              , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 5,  7, IL.Food_Gooseberry.get(1));
		// Grows on Trees
		
		IL.Food_Candleberry                    .set(addItem(  130, "Свечная ягода"                            , ""                            , "cropCandleberry"             , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_FLAMMABLE, 300, 0, 30), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.GELUM, 1)));
		RM.replicateOrganic( 5,  8, IL.Food_Candleberry.get(1));
		BushesGT.put(IL.Food_Candleberry.get(1), 0x44ff44, 0xccffcc, 0xaaffaa, 0xccffcc);
		
		IL.Food_Cranberry                      .set(addItem(  140, "Клюква"                                   , ""                            , "cropCranberry"               , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 5,  9, IL.Food_Cranberry.get(1));
		BushesGT.put(IL.Food_Cranberry.get(1), 0x00dd00, 0xffcccc, 0x66ff66, 0xff0000);
		
		IL.Food_Currants_Black                 .set(addItem(  150, "Черная смородина"                         , ""                            , "cropCurrantsBlack"           , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   6,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.TENEBRAE, 1)));
		RM.replicateOrganic( 5, 10, IL.Food_Currants_Black.get(1));
		BushesGT.put(IL.Food_Currants_Black.get(1), 0x33ff33, 0xaaaaaa, 0x66ff66, 0x111111);
		
		IL.Food_Currants_White                 .set(addItem(  160, "Белая смородина"                          , ""                            , "cropCurrantsWhite"           , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.ORDO, 1)));
		RM.replicateOrganic( 5, 11, IL.Food_Currants_White.get(1));
		BushesGT.put(IL.Food_Currants_White.get(1), 0x33ff33, 0xaaaaaa, 0x66ff66, 0xeeeedd);
		
		IL.Food_Currants_Red                   .set(addItem(  170, "Красная смородина"                        , ""                            , "cropCurrantsRed"             , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.SANO, 1)));
		RM.replicateOrganic( 5, 12, IL.Food_Currants_Red.get(1));
		BushesGT.put(IL.Food_Currants_Red.get(1), 0x33ff33, 0xaaaaaa, 0x66ff66, 0xee0000);
		
		IL.Food_Blackberry                     .set(addItem(  180, "Ежевика"                                  , ""                            , "cropBlackberry"              , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.TENEBRAE, 1)));
		RM.replicateOrganic( 5, 13, IL.Food_Blackberry.get(1));
		BushesGT.put(IL.Food_Blackberry.get(1), 0x11ff11, 0xffcccc, 0x663333, 0x331111);
		
		IL.Food_Raspberry                      .set(addItem(  190, "Малина"                                   , ""                            , "cropRaspberry"               , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.SANO, 1)));
		RM.replicateOrganic( 5, 14, IL.Food_Raspberry.get(1));
		BushesGT.put(IL.Food_Raspberry.get(1), 0x11ff11, 0xffcccc, 0x664444, 0xffaaaa);
		
		IL.Food_Strawberry                     .set(addItem(  200, "Клубника"                                 , ""                            , "cropStrawberry"              , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.SANO, 1)));
		RM.replicateOrganic( 5, 15, IL.Food_Strawberry.get(1));
		// Grows on Weeds
		
		
		IL.Food_Apple_Green                    .set(addItem(  210, "Яблоко"           , ""                                                    , "cropAppleGreen"              , new FoodStat( 4, 0.400F,  10, C+36,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, ST.make(this, 1, 212)                , F, T, F, T), TC.stack(TC.MESSIS, 2), TC.stack(TC.FAMES, 1), Behavior_FeedPig.INSTANCE));
		IL.Food_Apple_Green_Sliced             .set(addItem(  211, "Ломтик яблока"    , ""                                                                                    , new FoodStat( 1, 0.400F,  10, C+36,  0.30F,   0,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1)));
		IL.Food_Apple_Green_Core               .set(addItem(  212, "Яблочный огрызок" , "Не путать с модом"                                   , "itemPlantRemains"            , TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1), Behavior_FeedPig.INSTANCE));
		RM.replicateOrganic( 3, 11, IL.Food_Apple_Green.get(1));
		CR.shaped(IL.Food_Apple_Green_Sliced    .get(4), CR.DEF_NCC, "kX", 'X', "cropAppleGreen");
		
		
		IL.Food_Apple_Yellow                   .set(addItem(  220, "Яблоко"           , ""                                                    , "cropAppleYellow"             , new FoodStat( 5, 0.300F,   5, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, ST.make(this, 1, 222)                , F, T, F, T), TC.stack(TC.MESSIS, 2), TC.stack(TC.FAMES, 1), Behavior_FeedPig.INSTANCE));
		IL.Food_Apple_Yellow_Sliced            .set(addItem(  221, "Ломтик яблока"    , ""                                                                                    , new FoodStat( 1, 0.300F,   5, C+36,  0.30F,   0,   0,   0,   2,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1)));
		IL.Food_Apple_Yellow_Core              .set(addItem(  222, "Яблочный огрызок" , "Не путать с модом"                                   , "itemPlantRemains"            , TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1), Behavior_FeedPig.INSTANCE));
		RM.replicateOrganic( 3, 12, IL.Food_Apple_Yellow.get(1));
		CR.shaped(IL.Food_Apple_Yellow_Sliced   .get(4), CR.DEF_NCC, "kX", 'X', "cropAppleYellow");
		
		
		IL.Food_Apple_Red                      .set(ST.make(Items.apple, 1, 0)); FoodsGT.put(ST.make(Items.apple, 1, W), 0, 0, 0, 8, 0);//    , "cropAppleRed"                , new FoodStat( 4, 0.300F,   0, C+37,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, ST.make(this, 1, 232)                , F, T, F, T), TC.stack(TC.MESSIS, 2), TC.stack(TC.FAMES, 1), Behavior_FeedPig.INSTANCE));
		IL.Food_Apple_Red_Sliced               .set(addItem(  231, "Ломтик яблока"    , ""                                                                                    , new FoodStat( 1, 0.300F,   0, C+37,  0.30F,   0,   0,   0,   2,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1)));
		IL.Food_Apple_Red_Core                 .set(addItem(  232, "Яблочный огрызок" , "Не путать с модом"                                   , "itemPlantRemains"            , TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1), Behavior_FeedPig.INSTANCE));
		RM.replicateOrganic( 3, 13, IL.Food_Apple_Red.get(1));
		CR.shaped(IL.Food_Apple_Red_Sliced      .get(4), CR.DEF_NCC, "kX", 'X', "cropAppleRed");
		
		
		IL.Food_Apple_DarkRed                  .set(addItem(  240, "Яблоко"           , ""                                                    , "cropAppleDarkRed"            , new FoodStat( 5, 0.400F,   5, C+37,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, ST.make(this, 1, 242)                , F, T, F, T), TC.stack(TC.MESSIS, 2), TC.stack(TC.FAMES, 1), Behavior_FeedPig.INSTANCE));
		IL.Food_Apple_DarkRed_Sliced           .set(addItem(  241, "Ломтик яблока"    , ""                                                                                    , new FoodStat( 1, 0.400F,   5, C+37,  0.30F,   0,   0,   0,   3,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1)));
		IL.Food_Apple_DarkRed_Core             .set(addItem(  242, "Яблочный огрызок" , "Не путать с модом"                                   , "itemPlantRemains"            , TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1), Behavior_FeedPig.INSTANCE));
		RM.replicateOrganic( 3, 14, IL.Food_Apple_DarkRed.get(1));
		CR.shaped(IL.Food_Apple_DarkRed_Sliced  .get(4), CR.DEF_NCC, "kX", 'X', "cropAppleDarkRed");
		
		
		RM.replicateOrganic( 3, 15, ST.make(Blocks.melon_block, 1, 0));
		
		
		IL.Food_Peanut                         .set(addItem(  250, "Арахис"           , ""                                                    , "cropPeanut"                  , new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   0,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.GRANUM, 1), TC.stack(TC.COGNITIO, 1)));
		addItem(251, "", "", new Behavior_Turn_Into(IL.Food_Coconut), TD.Creative.HIDDEN); // Migrating a GT6U Mistake when assigning IDs. There are 10-ID-wide gaps for a reason.
		RM.replicateOrganic( 6,  7, IL.Food_Peanut.get(1));
		
		
		IL.Food_Hazelnut                       .set(addItem(  260, "Лесной орех"      , ""                                                    , "cropHazelnut"                , new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   0,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.GRANUM, 1), TC.stack(TC.PERFODIO, 1)));
		RM.replicateOrganic( 6,  8, IL.Food_Hazelnut.get(1));
		
		
		IL.Food_Ananas                         .set(addItem(  270, "Ананас"           , "Кто проживает в ананасе на дне океана?"              , "cropAnanas"                  , new FoodStat( 4, 0.300F,  10, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.ARBOR, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ananas_Sliced                  .set(addItem(  271, "Ломтик ананаса"   , "Узнает ли Тед когда нибудь о мистическом ананасе?"                                  , new FoodStat( 1, 0.300F,  10, C+36,  0.30F,   0,   0,   0,   2,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.ARBOR, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)16);
		RM.replicateOrganic( 2,  8, IL.Food_Ananas.get(1));
		CR.shaped(IL.Food_Ananas_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropAnanas");
		
		
		IL.Food_Cinnamon                       .set(addItem(  280, "Кора корицы"      , "Не позволяйте никому бросать вам вызов!"             , "cropCinnamon"                , new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.ARBOR, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 2,  7, IL.Food_Cinnamon.get(1));
		
		// Couldn't resist to put Lyrics from the Donkey Kong Rap there.
		IL.Food_Coconut                        .set(addItem(  290, "Кокос"            , "Его кокосовый пистолет может стрелять сильной струёй. Если он выстрелит в тебя, будет больно!", "cropCoconut", new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   0,   8,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.GRANUM, 1), TC.stack(TC.TUTAMEN, 1)));
		RM.replicateOrganic( 2,  9, IL.Food_Coconut.get(1));
		
		
		IL.Food_Cheese                         .set(addItem( 1000, "Сыр"              , "Нажмите на сыр"                                      , "foodCheese"                  , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   8,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 2)));
		IL.Food_Cheese_Sliced                  .set(addItem( 1001, "Ломтик сыра"      , "ЧУЖИЕ АТАКУЮТ!!! Брось СЫЫЫЫР!!!"                                             , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   2,   0,   2, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), new OreDictItemData(MT.Cheese, U4))); Sandwiches.INGREDIENTS.put(last(), (byte)19);
		CR.shaped(IL.Food_Cheese_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "foodCheese");
		
		
		IL.Food_Brown_Egg                      .set(ST.make(Items.egg, 1, 0));
		IL.Food_White_Egg                      .set(addItem( 1051, "Яйцо"             , "Яйцо появилось раньше курицы!"                    , OD.itemEgg                    , TC.stack(TC.BESTIA, 1), TC.stack(TC.LIMUS, 1), TC.stack(TC.VICTUS, 1)));
		IL.Food_Brown_Egg_Boiled               .set(addItem( 1060, "Вареное яйцо"     , "Вы ожидали, что это будет выглядеть по-другому после кипячения?", "foodBoiledegg"               , new FoodStat( 2, 1.200F,   0, C+37,  0.50F,   0,   0,   0,   4,   8, EnumAction.eat, null                                 , F, T, F, T                                                                    ), TC.stack(TC.FAMES, 2)));
		IL.Food_White_Egg_Boiled               .set(addItem( 1061, "Вареное яйцо"     , "Вы ожидали, что это будет выглядеть по-другому после кипячения?", "foodBoiledegg"               , new FoodStat( 2, 1.200F,   0, C+37,  0.50F,   0,   0,   0,   4,   8, EnumAction.eat, null                                 , F, T, F, T                                                                    ), TC.stack(TC.FAMES, 2)));
		IL.Food_Egg_Fried                      .set(addItem( 1070, "Жаренное яйцо"    , ""                                                    , "foodFriedegg"                , new FoodStat( 2, 1.200F,   0, C+37,  0.50F,   0,   0,   0,   4,   8, EnumAction.eat, null                                 , F, T, F, T                                                                    ), TC.stack(TC.FAMES, 2))); Sandwiches.INGREDIENTS.put(last(), (byte)43);
		IL.Food_Egg_Scrambled                  .set(addItem( 1071, "Яичница"          , ""                                                    , "foodScrambledegg"            , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   4,   8, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 0, 30, PotionsGT.ID_STICKY, 300, 0, 30), TC.stack(TC.FAMES, 2))); Sandwiches.INGREDIENTS.put(last(), (byte)(200+DYE_INDEX_Yellow));
		IL.Food_Egg_Sliced                     .set(addItem( 1072, "Нарезанное яйцо"  , "Яичный!"                                                                         , new FoodStat( 1, 0.600F,   0, C+37,  0.50F,   0,   0,   0,   1,   2, EnumAction.eat, null                                 , F, T, F, T                                                                    ), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)44);
		IL.Food_Egg_Yolk                       .set(addItem( 1073, "Яичный желток"    , "Вот и все, Желтки!"                                                                  , new FoodStat( 1, 1.200F,   0, C+37,  0.50F,   0,   0,   0,   2,   4, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 0, 30                                 ), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)(200+DYE_INDEX_Yellow));
		IL.Food_Egg_White                      .set(addItem( 1074, "Яичный белок"     , ""                                                                                    , new FoodStat( 1, 1.200F,   0, C+37,  0.50F,   0,   0,   0,   2,   4, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_STICKY  , 300, 0, 30                                 ), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)(200+DYE_INDEX_White));
		CR.shaped(IL.Food_Egg_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "foodBoiledegg");
		RM.generify(IL.Food_White_Egg.get(1), ST.make(Items.egg, 1, 0));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Egg_White.get(1), IL.Food_Egg_Yolk.get(1), IL.Food_Egg_Scrambled.get(1));
		for (String tCookingOil : FluidsGT.COOKING_OIL) if (FL.exists(tCookingOil)) {
		for (String tFluid : FluidsGT.VINEGAR) if (FL.exists(tFluid))
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Egg_Yolk.get(1), FL.array(FL.make(tCookingOil, 100), FL.make(tFluid    , 100)), FL.Mayo.make(250), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Egg_Yolk.get(1), FL.array(FL.make(tCookingOil, 100), FL.Juice_Lemon.make(100)), FL.Mayo.make(250), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Egg_Yolk.get(1), FL.array(FL.make(tCookingOil, 100), FL.Juice_Lime .make(100)), FL.Mayo.make(250), ZL_IS);
		}
		
		FoodsGT.put(ST.make(Items.fish           , 1, W), 0, 0, 0, 0,12); Sandwiches.INGREDIENTS.put(ST.make(Items.fish           , 1, W), (byte)24);
		FoodsGT.put(ST.make(Items.cooked_fished  , 1, W), 0, 0, 0, 0,12); Sandwiches.INGREDIENTS.put(ST.make(Items.cooked_fished  , 1, W), (byte)25);
		
		FoodsGT.put(ST.make(Items.beef           , 1, W), 0, 0, 0, 0,16); Sandwiches.INGREDIENTS.put(ST.make(Items.beef           , 1, W), (byte)29);
		FoodsGT.put(ST.make(Items.cooked_beef    , 1, W), 0, 0, 0, 0,16); Sandwiches.INGREDIENTS.put(ST.make(Items.cooked_beef    , 1, W), (byte)30);
		
		FoodsGT.put(ST.make(Items.chicken        , 1, W), 0, 0, 0, 0,12); Sandwiches.INGREDIENTS.put(ST.make(Items.chicken        , 1, W), (byte)29);
		FoodsGT.put(ST.make(Items.cooked_chicken , 1, W), 0, 0, 0, 0,12); Sandwiches.INGREDIENTS.put(ST.make(Items.cooked_chicken , 1, W), (byte)30);
		
		FoodsGT.put(ST.make(Items.porkchop       , 1, W), 0, 0, 0, 0,16); Sandwiches.INGREDIENTS.put(ST.make(Items.porkchop       , 1, W), (byte)29);
		FoodsGT.put(ST.make(Items.cooked_porkchop, 1, W), 0, 0, 0, 0,16); Sandwiches.INGREDIENTS.put(ST.make(Items.cooked_porkchop, 1, W), (byte)30);
		
		FoodsGT.put(ST.make(Items.rotten_flesh   , 1, W),10, 0, 0, 0, 8); Sandwiches.INGREDIENTS.put(ST.make(Items.rotten_flesh   , 1, W), (byte)29);
		
		
		IL.Food_Ham_Raw                        .set(addItem( 1100, "Сырая ветчина"                            , "Падает со свиней и кабанов"  , "foodHamraw"                  , new FoodStat( 3, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, U*2, MT.Bone, U4)));
		IL.Food_Ham_Cooked                     .set(addItem( 1101, "Вареная ветчина"                          , ""                            , "foodHamcooked"               , new FoodStat(10, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U4)));
		RM.add_smelting(IL.Food_Ham_Raw.get(1), IL.Food_Ham_Cooked.get(1), F, T, F);
		
		
		IL.Food_Ham_Slice_Raw                  .set(addItem( 1102, "Ломтик сырой витчины"                     , ""                                                            , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   6, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.CORPUS, 1), new OreDictItemData(MT.MeatRaw, U2))); Sandwiches.INGREDIENTS.put(last(), (byte)26);
		IL.Food_Ham_Slice_Cooked               .set(addItem( 1103, "Ломтик вареной ветчины"                   , ""                                                            , new FoodStat( 3, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,   6, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.CORPUS, 1), new OreDictItemData(MT.MeatCooked, U2))); Sandwiches.INGREDIENTS.put(last(), (byte)27);
		RM.add_smelting(IL.Food_Ham_Slice_Raw.get(1), IL.Food_Ham_Slice_Cooked.get(1), F, T, F);
		CR.shaped(IL.Food_Ham_Slice_Raw.get(4), CR.DEF_NCC, "kX", 'X', "foodHamraw");
		CR.shaped(IL.Food_Ham_Slice_Cooked.get(4), CR.DEF_NCC, "kX", 'X', "foodHamcooked");
		
		
		IL.Food_Bacon_Raw                      .set(addItem( 1112, "Сырой бекон"                              , "Падает со свиней и кабанов"      , "foodBaconraw"            , new FoodStat( 1, 0.900F,   0, C+37,  0.10F,   0,   0,   0,   0,   6, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.CORPUS, 1), new OreDictItemData(MT.MeatRaw, U2))); Sandwiches.INGREDIENTS.put(last(), (byte)37);
		IL.Food_Bacon_Cooked                   .set(addItem( 1113, "Жареный бекон"                            , ""                                , "foodBaconcooked"         , new FoodStat( 3, 1.800F,   0, C+38,  0.50F,   0,   0,   0,   0,   6, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.CORPUS, 1), new OreDictItemData(MT.MeatCooked, U2))); Sandwiches.INGREDIENTS.put(last(), (byte)38);
		RM.add_smelting(IL.Food_Bacon_Raw.get(1), IL.Food_Bacon_Cooked.get(1), F, T, F);
		
		
		IL.Food_Rib_Raw                        .set(addItem( 1200, "Сырые ребра"                              , "Падает с крупных животных"       , "foodRibraw"              , new FoodStat( 3, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, U*2, MT.Bone, U), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Rib_Cooked                     .set(addItem( 1201, "Ребрышки на гриле"                        , ""                                , "foodRibcooked"           , new FoodStat(10, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		IL.Food_Rib_BBQ                        .set(addItem( 1202, "Ребрышки барбекю"                         , "Ребра видеоигр высокого качества", "foodRibbbq"              , new FoodStat(10, 1.600F,   0, C+38,  0.50F,   0,   0,   0,  30,  24, EnumAction.eat, null                                 , F, T, F, T, Potion.heal.id, 1, 0, 10, Potion.moveSpeed.id, 200, 0, 20), TC.stack(TC.FAMES, 2), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_Rib_Raw.get(1), IL.Food_Rib_Cooked.get(1), F, T, F);
		CR.shapeless(IL.Food_Rib_BBQ.get(1), CR.DEF_NCC, new Object[] {"foodRibcooked", "foodBarbecuesauce"});
		
		
		IL.Food_RibEyeSteak_Raw                .set(addItem( 1210, "Сырой стейк"                              , "Падает с больших животных"       , "foodRibeyesteakraw"      , new FoodStat( 3, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 3), new OreDictItemData(MT.MeatRaw, U*3, MT.Bone, U4), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)32);
		IL.Food_RibEyeSteak_Cooked             .set(addItem( 1211, "Стейк на гриле"                           , "Он смотрит на тебя"              , "foodRibeyesteakcooked"   , new FoodStat(10, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 3), new OreDictItemData(MT.MeatCooked, U*3, MT.Bone, U4))); Sandwiches.INGREDIENTS.put(last(), (byte)33);
		RM.add_smelting(IL.Food_RibEyeSteak_Raw.get(1), IL.Food_RibEyeSteak_Cooked.get(1), F, T, F);
		
		
		IL.Food_DogMeat_Raw                    .set(addItem( 1300, "Собачье мясо"                             , "Почему ты их не пощадил?"        , "foodDograw"              , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, U*2, MT.Bone, U9), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_DogMeat_Cooked                 .set(addItem( 1301, "Собачье мясо на гриле"                    , "ТЫ МОНСТР!!!"                    , "foodDogcooked"           , new FoodStat( 8, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U9))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_DogMeat_Raw.get(1), IL.Food_DogMeat_Cooked.get(1), F, T, F);
		
		
		IL.Food_Mutton_Raw                     .set(addItem( 1400, "Баранина"             , "Бее Бее, я овечка, я сказал: Бее Бее этой овечке"    , "foodMuttonraw"           , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 3), new OreDictItemData(MT.MeatRaw, U*2, MT.Bone, U4), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Mutton_Cooked                  .set(addItem( 1401, "Баранина на гриле"                        , ""                                , "foodMuttoncooked"        , new FoodStat( 7, 2.000F,   0, C+38,  0.50F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 3), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U4))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_Mutton_Raw.get(1), IL.Food_Mutton_Cooked.get(1), F, T, F);
		
		
		IL.Food_Horse_Raw                      .set(addItem( 1500, "Конина"                                   , ""                                , "foodHorseraw"            , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, U*2, MT.Bone, U4), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Horse_Cooked                   .set(addItem( 1501, "Конина на гриле"                          , ""                                , "foodHorsecooked"         , new FoodStat( 8, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U4))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_Horse_Raw.get(1), IL.Food_Horse_Cooked.get(1), F, T, F);
		
		IL.Food_Mule_Raw                       .set(addItem( 1510, "Мясо мула"                                , ""                                , "foodMuleraw"             , new FoodStat( 3, 0.800F,   0, C+37,  0.10F,   0,   0,   0,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, 5*U2, MT.Bone, U4), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Mule_Cooked                    .set(addItem( 1511, "Мясо мула на гриле"                       , ""                                , "foodMulecooked"          , new FoodStat(10, 1.800F,   0, C+38,  0.50F,   0,   0,   0,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, 5*U2, MT.Bone, U4))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_DogMeat_Raw.get(1), IL.Food_DogMeat_Cooked.get(1), F, T, F);
		
		IL.Food_Donkey_Raw                     .set(addItem( 1520, "Ослиное мясо"                             , ""                                , "foodDonkeyraw"           , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, 5*U2, MT.Bone, U9), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Donkey_Cooked                  .set(addItem( 1521, "Мясо осла на гриле"                       , ""                                , "foodDonkeycooked"        , new FoodStat( 8, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, 5*U2, MT.Bone, U9))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_Donkey_Raw.get(1), IL.Food_Donkey_Cooked.get(1), F, T, F);
		
		
		IL.Food_Scrap_Meat                     .set(addItem( 1998, "Кусок мяса"                       , "Куски люди обычно не едят"                , "foodScrapmeat"           , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   4,   0,  12, EnumAction.eat, null                                 , F, T, F, T, Potion.hunger.id, 300, 0, 70, Potion.confusion.id, 300, 0, 10), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, U*2), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		
		IL.Food_Dough                          .set(addItem(32000, "Тесто"                            , "Для приготовления хлеба"                  , "foodDough"           , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Sugar                    .set(addItem(32001, "Сладкое тесто"                    , "Не ешьте тесто до того, как оно испечется", "foodSugarDough"      , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Chocolate                .set(addItem(32002, "Шоколадное тесто"                 , "Я сказал, не ешьте тесто!"                , "foodChocolateDough"  , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Egg                      .set(addItem(32003, "Яичное тесто"                     , "Для приготовления пасты"                  , "foodEggDough"        , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Sugar_Raisins            .set(addItem(32004, "Сладкое тесто с изюмом"           , "Не ешьте тесто до того, как оно испечется", "foodSugarDough"      , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,  30,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Sugar_Chocolate_Raisins  .set(addItem(32005, "Сладкое шоколадное тесто с изюмом", "Почти похоже на шоколадные чипсы"         , "foodSugarDough"      , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,  40,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Abyssal                  .set(addItem(32006, "Глубинное тесто"                  , "Для практики неземной пекарни"            , "foodAbyssalDough"    , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		if (IL.NeLi_Bread.exists()) RM.add_smelting(IL.Food_Dough_Abyssal.get(1), IL.NeLi_Bread.get(1), F, T, F);
		
		
		
		IL.Food_Chum                           .set(addItem(10000, "Чум"                                      , "Чум - это Фу!"                    , "foodChum"        , new FoodStat( 5, 1.600F,   0, C+37,  0.10F,   0,   0,  20,   0,   0, EnumAction.eat, null                                 , T, F, T, T, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)39);
		
		
		
		IL.Food_Cookie_Raw                     .set(addItem( 2000, "Тесто в форме печенья"                     , "Для выпечки печенья"                                          , new FoodStat( 1, 0.200F,   0, C+37,  0.10F,   0,   0,   0,   5,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.add_smelting(IL.Food_Cookie_Raw.get(1), ST.make(Items.cookie, 1, 0), F, T, F); FoodsGT.put(ST.make(Items.cookie, 1, W), 0, 0, 0,10, 0);
		RM.food_can(ST.make(Items.cookie, 6, W), 12, "Canned Cookies", IL.CANS_BREAD);
		CR.shaped(IL.Food_Cookie_Raw.get(4), CR.DEF_NCC, "kX", 'X', "foodChocolateDough");
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Dough_Chocolate.get(1), IL.Shape_Slicer_Flat.get(0), IL.Food_Cookie_Raw.get(4));
		
		
		IL.Food_Cookie_Raisins_Raw             .set(addItem( 2002, "Тесто с изюмом в форме печенья"           , "Для выпечки печенья с изюмом"                                   , new FoodStat( 1, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Cookie_Raisins                 .set(addItem( 2003, "Печенье с изюмом"                         , "Тебе это не нравится? Мне все равно! Это вкусно!", "foodRaisincookies", new FoodStat( 2, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.add_smelting(IL.Food_Cookie_Raisins_Raw.get(1), IL.Food_Cookie_Raisins.get(1), F, T, F);
		RM.food_can(IL.Food_Cookie_Raisins.get(6), 12, "Canned Raisin Cookies", IL.CANS_BREAD);
		CR.shaped(IL.Food_Cookie_Raisins_Raw.get(4), CR.DEF_NCC, "kX", 'X', IL.Food_Dough_Sugar_Raisins);
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Dough_Sugar_Raisins.get(1), IL.Shape_Slicer_Flat.get(0), IL.Food_Cookie_Raisins_Raw.get(4));
		
		
		IL.Food_Cookie_Chocolate_Raisins_Raw   .set(addItem( 2004, "Тесто с шоколадным изюмом в форме печенья", "Почти похоже на обычное печенье с шоколадной крошкой >:D"       , new FoodStat( 1, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Cookie_Chocolate_Raisins       .set(addItem( 2005, "Печенье"                                  , ""                                       , "foodRaisincookies", new FoodStat( 2, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  25,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.add_smelting(IL.Food_Cookie_Chocolate_Raisins_Raw.get(1), IL.Food_Cookie_Chocolate_Raisins.get(1), F, T, F);
		RM.food_can(IL.Food_Cookie_Chocolate_Raisins.get(6), 12, "Canned Chocolate Raisin Cookies", IL.CANS_BREAD);
		CR.shaped(IL.Food_Cookie_Chocolate_Raisins_Raw.get(4), CR.DEF_NCC, "kX", 'X', IL.Food_Dough_Sugar_Chocolate_Raisins);
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Dough_Sugar_Chocolate_Raisins.get(1), IL.Shape_Slicer_Flat.get(0), IL.Food_Cookie_Chocolate_Raisins_Raw.get(4));
		
		
		IL.Food_Cookie_Abyssal_Raw             .set(addItem( 2006, "Глубинное тесто в форме печенья"          , "Для выпечки печенья из незерняка"                            , new FoodStat( 1, 0.200F,   0, C+37,  0.10F,   0,   0,   0,   5,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		if (IL.NeLi_Cookie.exists()) {
		RM.add_smelting(IL.Food_Cookie_Abyssal_Raw.get(1), IL.NeLi_Cookie.get(1), F, T, F); FoodsGT.put(IL.NeLi_Cookie.get(1), 0, 0, 0,10, 0);
		RM.food_can(IL.NeLi_Cookie.get(6), 12, "Canned Abyssal Cookies", IL.CANS_BREAD);
		} else {
		RM.add_smelting(IL.Food_Cookie_Abyssal_Raw.get(1), ST.make(Items.cookie, 1, 0), F, T, F);
		}
		CR.shaped(IL.Food_Cookie_Abyssal_Raw.get(4), CR.DEF_NCC, "kX", 'X', "foodAbyssalDough");
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Dough_Abyssal.get(1), IL.Shape_Slicer_Flat.get(0), IL.Food_Cookie_Abyssal_Raw.get(4));
		
		
		IL.Food_CakeBottom_Raw                 .set(addItem( 3000, "Сырая основа торта"                       , "Для приготовления торта"                                             , new FoodStat( 2, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_CakeBottom                     .set(addItem( 3001, "Основа торта"                            , "Я знаю, что обещал тебе настоящий торт, но что ж ..."           , new FoodStat( 3, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.add_smelting(IL.Food_CakeBottom_Raw.get(1), IL.Food_CakeBottom.get(1), F, T, F);
		CR.shapeless(IL.Food_CakeBottom_Raw.get(1), CR.DEF_NCC, new Object[] {"foodSugarDough", "foodSugarDough", "foodSugarDough", "foodSugarDough"});
		CR.shaped(ST.make(Items.cake, 1, 0), CR.DEF_NCC, "C", "Z", 'Z', IL.Food_CakeBottom, 'C', "foodHeavycream");
		CR.delate(ST.make(Items.cake, 1, 0));
		
		
		IL.Food_Dough_Flat                     .set(addItem( 4000, "Раскатанное тесто"                        , "Для приготовления пиццы"                                            , new FoodStat( 1, 0.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Flat_Ketchup             .set(addItem( 4002, "Раскатанное тесто с кетчупом"             , "Для приготовления пиццы"                                            , new FoodStat( 2, 0.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		CR.shapeless(IL.Food_Dough_Flat.get(1), CR.DEF, new Object[] {"foodDough", OreDictToolNames.rollingpin});
		CR.shapeless(IL.Food_Dough_Flat_Ketchup.get(1), CR.DEF, new Object[] {"foodKetchup", IL.Food_Dough_Flat});
		CR.shapeless(IL.Food_Dough_Flat_Ketchup.get(2), CR.DEF, new Object[] {"foodKetchup", IL.Food_Dough_Flat, IL.Food_Dough_Flat});
		CR.shapeless(IL.Food_Dough_Flat_Ketchup.get(3), CR.DEF, new Object[] {"foodKetchup", IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat});
		CR.shapeless(IL.Food_Dough_Flat_Ketchup.get(4), CR.DEF, new Object[] {"foodKetchup", IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat});
		CR.shapeless(IL.Food_Dough_Flat_Ketchup.get(5), CR.DEF, new Object[] {"foodKetchup", IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat});
		
		
		IL.Food_Pizza_Cheese_Raw               .set(addItem( 4010, "Сырая пицца Маргарита"                    , "В духовку с ним!"                                      , new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   8,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 2), TC.stack(TC.IGNIS, 1)));
		IL.Food_Pizza_Cheese                   .set(addItem( 4011, "Пицца Маргарита"                          , "Сырная пицца"                                                , new FoodStat( 6, 1.200F,   0, C+38,  0.50F,   0,   0,   8,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 2), TC.stack(TC.IGNIS, 1)));
		CR.shapeless(IL.Food_Pizza_Cheese_Raw.get(1), CR.DEF, new Object[] {IL.Food_Dough_Flat_Ketchup, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		RM.add_smelting(IL.Food_Pizza_Cheese_Raw.get(1), IL.Food_Pizza_Cheese.get(1), F, T, F);
		
		
		IL.Food_Pizza_Meat_Raw                 .set(addItem( 4012, "Сырая мясная пицца"                       , "В духовку с ним!"                                      , new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   4,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Pizza_Meat                     .set(addItem( 4013, "Мясная пицца"                             , "Эмо Пицца режет сама себя!"                                  , new FoodStat( 7, 1.200F,   0, C+38,  0.50F,   0,   0,   4,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		CR.shapeless(IL.Food_Pizza_Meat_Raw.get(1), CR.DEF, new Object[] {IL.Food_Dough_Flat_Ketchup, OP.dust.dat(MT.MeatCooked)});
		RM.add_smelting(IL.Food_Pizza_Meat_Raw.get(1), IL.Food_Pizza_Meat.get(1), F, T, F);
		
		
		IL.Food_Pizza_Veggie_Raw               .set(addItem( 4014, "Сырая овощная пицца"                      , "В духовку с ним!"                                      , new FoodStat( 1, 0.300F,   5, C+35,  0.30F,   0,   0,   0,  16,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Pizza_Veggie                   .set(addItem( 4015, "Овощная пицца"                            , "Следующее, что они хотят, - это пицца без глютена ..."                 , new FoodStat( 5, 1.200F,   5, C+38,  0.50F,   0,   0,   0,  16,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		CR.shapeless(IL.Food_Pizza_Veggie_Raw.get(1), CR.DEF, new Object[] {IL.Food_Dough_Flat_Ketchup, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		RM.add_smelting(IL.Food_Pizza_Veggie_Raw.get(1), IL.Food_Pizza_Veggie.get(1), F, T, F);
		
		
		IL.Food_Pizza_Ananas_Raw               .set(addItem( 4016, "Сырая Гавайская пицца"                    , "Вы серьезно только что положили ананас в пиццу?"            , new FoodStat( 2, 0.300F,   5, C+37,  0.10F,   0,   0,   4,   8,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.ARBOR, 1)));
		IL.Food_Pizza_Ananas                   .set(addItem( 4017, "Гавайская пицца"                          , "Это мерзость! Кто кладет ананас в пиццу !?"     , new FoodStat( 7, 1.200F,   5, C+38,  0.50F,   0,   0,   4,   8,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.ARBOR, 1)));
		CR.shapeless(IL.Food_Pizza_Ananas_Raw.get(1), CR.DEF, new Object[] {IL.Food_Dough_Flat_Ketchup, IL.Food_Ananas_Sliced, IL.Food_Ananas_Sliced, IL.Food_Ham_Slice_Cooked, IL.Food_Cheese_Sliced});
		RM.add_smelting(IL.Food_Pizza_Ananas_Raw.get(1), IL.Food_Pizza_Ananas.get(1), F, T, F);
		
		
		
		IL.Food_Bun_Raw                        .set(addItem( 5000, "Тесто (булочка)"                          , "В форме булочки"                                                , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Bun                            .set(addItem( 5001, "Булочка"                                  , ""                                                            , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Bun_Sliced                     .set(addItem( 5002, "Нарезанная булка"                         , "Всего половина булочки"                                             , new FoodStat( 1, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Buns_Sliced                    .set(addItem( 5003, "Булочки"                                  , "Предварительно нарезанный"                                                  , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		RM.add_smelting(IL.Food_Bun_Raw.get(1), IL.Food_Bun.get(1), F, T, F);
		RM.food_can(IL.Food_Bun                .get(1), 2, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Bun_Sliced         .get(2), 2, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Buns_Sliced        .get(1), 2, "Canned Bread", IL.CANS_BREAD);
		CR.shaped(IL.Food_Bun_Sliced.get(2), CR.DEF_NCC, "kX", 'X', IL.Food_Bun);
		CR.shapeless(IL.Food_Bun_Raw.get(1), CR.DEF, new Object[] {"foodDough"});
		CR.shapeless(IL.Food_Buns_Sliced.get(1), CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced});
		CR.shapeless(IL.Food_Bun_Sliced.get(2), CR.DEF, new Object[] {IL.Food_Buns_Sliced});
		RM.packunpack(IL.Food_Bun_Sliced.get(2), IL.Food_Buns_Sliced.get(1));
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Bun.get(1), IL.Shape_Slicer_Split.get(0), IL.Food_Bun_Sliced.get(2));
		
		IL.Food_Burger_Veggie                  .set(addItem( 5010, "Вегетарианский бургер"                    , "Как бы вы это ни называли, это НЕ Бургер!"          , new FoodStat( 4, 1.200F,   0, C+35,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1)));
		IL.Food_Burger_Cheese                  .set(addItem( 5011, "Сырный бургер"                            , "СЫЫЫР!"                                                     , new FoodStat( 4, 1.400F,   0, C+38,  0.50F,   0,   0,   8,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 2)));
		IL.Food_Burger_Meat                    .set(addItem( 5012, "Гамбургер"                                , "Это коолевский бургер"                                  , new FoodStat( 6, 1.600F,   0, C+38,  0.50F,   0,   0,   4,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Burger_Chum                    .set(addItem( 5013, "Чумной бургер"                            , "Это чум, Фу!"                                                , new FoodStat( 6, 1.600F,   0, C+37,  0.10F,   0,   0,  20,   0,   0, EnumAction.eat, null                                 , T, F, T, T, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Burger_Tofu                    .set(addItem( 5016, "Бургер с тофу"                            , "Просто белая вещь внутри булочки"                             , new FoodStat( 4, 1.400F,   0, C+37,  0.10F,   0,   0,   4,   4,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1)));
		IL.Food_Burger_Soylent                 .set(addItem( 5017, "Сойлент Бургер"                           , "Не забудьте свой сойлент-салат с сойлент-колой!"          , new FoodStat( 5, 1.400F,   0, C+37,  0.10F,   0,   0,   4,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Burger_Fish                    .set(addItem( 5018, "Рыбный бургер"                            , "Пахнет рыбой"                                                , new FoodStat( 6, 1.600F,   0, C+38,  0.50F,   0,   0,   3,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		CR.shapeless(IL.Food_Burger_Veggie.get(1)   , CR.DEF, new Object[] {IL.Food_Buns_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Burger_Veggie.get(1)   , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Burger_Cheese.get(1)   , CR.DEF, new Object[] {IL.Food_Buns_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Burger_Cheese.get(1)   , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Burger_Meat.get(1)     , CR.DEF, new Object[] {IL.Food_Buns_Sliced, OP.ingot.dat(MT.MeatCooked)});
		CR.shapeless(IL.Food_Burger_Meat.get(1)     , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, OP.ingot.dat(MT.MeatCooked)});
		CR.shapeless(IL.Food_Burger_Tofu.get(1)     , CR.DEF, new Object[] {IL.Food_Buns_Sliced, OP.ingot.dat(MT.Tofu)});
		CR.shapeless(IL.Food_Burger_Tofu.get(1)     , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, OP.ingot.dat(MT.Tofu)});
		CR.shapeless(IL.Food_Burger_Soylent.get(1)  , CR.DEF, new Object[] {IL.Food_Buns_Sliced, OP.ingot.dat(MT.SoylentGreen)});
		CR.shapeless(IL.Food_Burger_Soylent.get(1)  , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, OP.ingot.dat(MT.SoylentGreen)});
		CR.shapeless(IL.Food_Burger_Fish.get(1)     , CR.DEF, new Object[] {IL.Food_Buns_Sliced, OP.ingot.dat(MT.FishCooked)});
		CR.shapeless(IL.Food_Burger_Fish.get(1)     , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, OP.ingot.dat(MT.FishCooked)});
		CR.shapeless(IL.Food_Burger_Chum.get(1)     , CR.DEF, new Object[] {IL.Food_Buns_Sliced, "foodChum"});
		CR.shapeless(IL.Food_Burger_Chum.get(1)     , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, "foodChum"});
		
		
		
		IL.Food_Bread_Raw                      .set(addItem( 6000, "Тесто (хлеб)"                             , "В форме хлеба"                                              , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Bread                          .set(ST.make(Items.bread, 1, 0)); // My OCD told me to do this, it is not my fault! XD                                                   , new FoodStat( 5, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Bread_Sliced                   .set(addItem( 6002, "Нарезанный хлеб"                          , ""                                                            , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Breads_Sliced                  .set(addItem( 6003, "Хлеб"                                     , "Предварительно нарезанный"                                                  , new FoodStat( 5, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		RM.add_smelting(IL.Food_Bread_Raw.get(1), IL.Food_Bread.get(1), F, T, F);
		RM.food_can(IL.Food_Bread              .get(1), 4, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Bread_Sliced       .get(1), 2, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Breads_Sliced      .get(1), 4, "Canned Bread", IL.CANS_BREAD);
		CR.shaped(IL.Food_Bread_Sliced.get(2), CR.DEF_NCC, "kX", 'X', IL.Food_Bread);
		CR.shapeless(IL.Food_Bread_Raw.get(1), CR.DEF, new Object[] {"foodDough", "foodDough"});
		CR.shapeless(IL.Food_Breads_Sliced.get(1), CR.DEF, new Object[] {IL.Food_Bread_Sliced, IL.Food_Bread_Sliced});
		CR.shapeless(IL.Food_Bread_Sliced.get(2), CR.DEF, new Object[] {IL.Food_Breads_Sliced});
		RM.packunpack(IL.Food_Bread_Sliced.get(2), IL.Food_Breads_Sliced.get(1));
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Bread.get(1), IL.Shape_Slicer_Split.get(0), IL.Food_Bread_Sliced.get(2));
		
		IL.Food_Sandwich_Veggie                .set(addItem( 6010, "Вегетарианский сэндвич"                   , "Это правило, ребята! Сезон 4, Морти-веган!"                 , new FoodStat( 7, 1.200F,   0, C+35,  0.30F,   0,   0,   0,  24,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1)));                                                  setFluidContainerStats(mLastID, 0, 32);
		IL.Food_Sandwich_Cheese                .set(addItem( 6011, "Сэндвич с сыром"                          , "Скажи сыр!"                                                 , new FoodStat( 7, 1.400F,   0, C+38,  0.50F,   0,   0,  16,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 2)));                                                  setFluidContainerStats(mLastID, 0, 32);
		IL.Food_Sandwich_Bacon                 .set(addItem( 6014, "Сэндвич с беконом"                        , "Лучший бутерброд на свете!"                                 , new FoodStat(10, 1.800F,   0, C+38,  0.50F,   0,   0,  12,   0,  18, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));                          setFluidContainerStats(mLastID, 0, 32);
		IL.Food_Sandwich_Steak                 .set(addItem( 6015, "Сэндвич со стейком"                       , "Это не сэндвич на пару"                                     , new FoodStat(10, 1.600F,   0, C+38,  0.50F,   0,   0,   8,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));                          setFluidContainerStats(mLastID, 0, 32);
		CR.shapeless(IL.Food_Sandwich_Veggie.get(1) , CR.DEF, new Object[] {IL.Food_Breads_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Sandwich_Veggie.get(1) , CR.DEF, new Object[] {IL.Food_Bread_Sliced, IL.Food_Bread_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Sandwich_Cheese.get(1) , CR.DEF, new Object[] {IL.Food_Breads_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Sandwich_Cheese.get(1) , CR.DEF, new Object[] {IL.Food_Bread_Sliced, IL.Food_Bread_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Sandwich_Bacon.get(1)  , CR.DEF, new Object[] {IL.Food_Breads_Sliced, "foodBaconcooked", "foodBaconcooked", "foodBaconcooked"});
		CR.shapeless(IL.Food_Sandwich_Bacon.get(1)  , CR.DEF, new Object[] {IL.Food_Bread_Sliced, IL.Food_Bread_Sliced, "foodBaconcooked", "foodBaconcooked", "foodBaconcooked"});
		CR.shapeless(IL.Food_Sandwich_Steak.get(1)  , CR.DEF, new Object[] {IL.Food_Breads_Sliced, Items.cooked_beef});
		CR.shapeless(IL.Food_Sandwich_Steak.get(1)  , CR.DEF, new Object[] {IL.Food_Bread_Sliced, IL.Food_Bread_Sliced, Items.cooked_beef});
		
		
		
		IL.Food_Baguette_Raw                   .set(addItem( 7000, "Тесто (Багет)"                            , "В форме багета"                                             , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Baguette                       .set(addItem( 7001, "Багет"                                    , "Ничего не телепортировал, НО ХЛЕБ !!!"                      , new FoodStat( 8, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Baguette_Sliced                .set(addItem( 7002, "Багет нарезанный"                         , "Всего пол багета"                                           , new FoodStat( 4, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Baguettes_Sliced               .set(addItem( 7003, "Багеты"                                   , "Предварительно нарезанный"                                  , new FoodStat( 8, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		RM.add_smelting(IL.Food_Baguette_Raw.get(1), IL.Food_Baguette.get(1), F, T, F);
		RM.food_can(IL.Food_Baguette           .get(1), 8, "Canned Pain", IL.CANS_BREAD);
		RM.food_can(IL.Food_Baguette_Sliced    .get(1), 4, "Canned Pain", IL.CANS_BREAD);
		RM.food_can(IL.Food_Baguettes_Sliced   .get(1), 8, "Canned Pain", IL.CANS_BREAD);
		CR.shaped(IL.Food_Baguette_Sliced.get(2), CR.DEF_NCC, "kX", 'X', IL.Food_Baguette);
		CR.shapeless(IL.Food_Baguette_Raw.get(1), CR.DEF, new Object[] {"foodDough", "foodDough", "foodDough"});
		CR.shapeless(IL.Food_Baguettes_Sliced.get(1), CR.DEF, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced});
		CR.shapeless(IL.Food_Baguette_Sliced.get(2), CR.DEF, new Object[] {IL.Food_Baguettes_Sliced});
		RM.packunpack(IL.Food_Baguette_Sliced.get(2), IL.Food_Baguette_Sliced.get(1));
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Baguette.get(1), IL.Shape_Slicer_Split.get(0), IL.Food_Baguette_Sliced.get(2));
		
		IL.Food_Large_Sandwich_Veggie          .set(addItem( 7010, "Большой овощной бутерброд"                , "Без мяса"                                                    , new FoodStat(15, 2.200F,   0, C+35,  0.30F,   0,   0,   0,  36,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 3), TC.stack(TC.FAMES, 1)));                                                  setFluidContainerStats(mLastID, 0, 16);
		IL.Food_Large_Sandwich_Cheese          .set(addItem( 7011, "Большой бутерброд с сыром"                , "Мне нужна еще одна глупая подсказка для этого"               , new FoodStat(15, 2.400F,   0, C+38,  0.50F,   0,   0,  24,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 3)));                                                  setFluidContainerStats(mLastID, 0, 16);
		IL.Food_Large_Sandwich_Bacon           .set(addItem( 7014, "Большой бутерброд с беконом"              , "Для мужчин! (и мужественных женщин)"                         , new FoodStat(20, 2.800F,   0, C+38,  0.50F,   0,   0,  16,   0,  36, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2)));                          setFluidContainerStats(mLastID, 0, 16);
		IL.Food_Large_Sandwich_Steak           .set(addItem( 7015, "Большой бутерброд со стейком"             , "Да, я как-то случайно назвал его сэндвичем на пару"          , new FoodStat(20, 2.600F,   0, C+38,  0.50F,   0,   0,  12,   0,  32, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2)));                          setFluidContainerStats(mLastID, 0, 16);
		CR.shapeless(IL.Food_Large_Sandwich_Veggie.get(1)   , CR.DEF, new Object[] {IL.Food_Baguettes_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Large_Sandwich_Veggie.get(1)   , CR.DEF, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Large_Sandwich_Cheese.get(1)   , CR.DEF, new Object[] {IL.Food_Baguettes_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Large_Sandwich_Cheese.get(1)   , CR.DEF, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Large_Sandwich_Bacon.get(1)    , CR.DEF, new Object[] {IL.Food_Baguettes_Sliced, "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked"});
		CR.shapeless(IL.Food_Large_Sandwich_Bacon.get(1)    , CR.DEF, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced, "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked"});
		CR.shapeless(IL.Food_Large_Sandwich_Steak.get(1)    , CR.DEF, new Object[] {IL.Food_Baguettes_Sliced, Items.cooked_beef, Items.cooked_beef});
		CR.shapeless(IL.Food_Large_Sandwich_Steak.get(1)    , CR.DEF, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced, Items.cooked_beef, Items.cooked_beef});
		
		
		
		IL.Food_Toast_Raw                       .set(addItem(14000, "Тесто (тостовый хлеб)"                    , "Форма тостового хлеба"                                       , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Toast                           .set(addItem(14001, "Буханка тостов"                           , "Не телепортируйте Хлеб!"                                     , new FoodStat( 8, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Toast_Sliced                    .set(addItem(14002, "Тост"                                     , "Лучшая вещь после нарезанного хлеба, о, подождите..."        , new FoodStat( 1, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)254);
		IL.Food_Toasted_Sliced                  .set(addItem(14003, "Поджаренный тост"                         , ""                                                            , new FoodStat( 1, 1.200F,   0, C+39,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 2))); Sandwiches.INGREDIENTS.put(last(), (byte)253);
		RM.add_smelting(IL.Food_Toast_Raw.get(1), IL.Food_Toast.get(1), F, T, F);
		RM.add_smelting(IL.Food_Toast_Sliced.get(1), IL.Food_Toasted_Sliced.get(1), F, T, F);
		RM.food_can(IL.Food_Toast_Raw           .get(1), 8, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Toast               .get(1), 8, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Toast_Sliced        .get(1), 1, "Canned Bread", IL.CANS_BREAD);
		CR.shaped(IL.Food_Toast_Sliced.get(8), CR.DEF_NCC, "kX", 'X', IL.Food_Toast);
		CR.shapeless(IL.Food_Toast_Raw.get(1), CR.DEF, new Object[] {"foodDough", "foodDough", "foodDough", "foodDough"});
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Toast.get(1), IL.Shape_Slicer_Flat.get(0), IL.Food_Toast_Sliced.get(8));
		
		
		
		
		
		
		
		FoodsGT.put(ST.make(Items.mushroom_stew     , 1, W), 0,10, 0, 5, 0);
		
		
		
		
		IL.Food_Chum_On_Stick                  .set(addItem(10010, "Чум на палочке"                           , "Не забудьте попробовать нашу Чум-балаю"                        , new FoodStat( 5, 1.600F,   0, C+37,  0.10F,   0,   0,  20,   0,   0, EnumAction.eat, IL.Stick.get(1)                      , T, F, T, T, PotionsGT.ID_STICKY, 300, 0, 90, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1), new OreDictItemData(ANY.Wood, U2)));
		CR.shapeless(IL.Food_Chum_On_Stick.get(1), CR.DEF_NCC, new Object[] {OP.stick.dat(ANY.Wood), "foodChum"});
		
		
		
		IL.Food_Dough_Egg_Flat                 .set(addItem(11000, "Тонкое яичное тесто"                      , "Для приготовления пасты"                                       , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   5,   5, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		CR.shapeless(IL.Food_Dough_Egg_Flat.get(1), CR.DEF, new Object[] {IL.Food_Dough_Egg, OreDictToolNames.rollingpin});
		RM.RollingMill.addRecipe1(T, 16,   16, IL.Food_Dough_Egg.get(1), IL.Food_Dough_Egg_Flat.get(1));
		
		
		
		IL.Food_Ice_Cream                      .set(addItem(13000, "Мороженое"                                , "Обычное молочное джелато"                        , "foodIcecream"                    , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Stracciatella        .set(addItem(13001, "Мороженеое с шоколадной крошкой"          , "Джелато с шоколадной крошкой"                    , "foodStracciatellaicecream"       , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Raisin               .set(addItem(13002, "Мороженое с изюмом"                       , "Сухое и виноградное джелато"                     , "foodRaisinicecream"              , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Vanilla              .set(addItem(13003, "Ванильное мороженое"                      , "Стандартный ванильный джелато"                   , "foodVanillaicecream"             , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Chocolate            .set(addItem(13004, "Шоколадное мороженое"                     , "Стандартный шоколадный джелато"                  , "foodChocolateicecream"           , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Mocha                .set(addItem(13005, "Мокка Мороженое"                          , "Джелато на основе кофе"                          , "foodMochaicecream"               , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,  20,   5,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Caramel              .set(addItem(13006, "Карамельное мороженое"                    , "Сладкий Джелато"                                 , "foodCaramelicecream"             , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  30,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Mint                 .set(addItem(13007, "Мятное мороженое"                         , "Джелато со свежим запахом"                       , "foodMinticecream"                , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Mint_Chocolate_Chip  .set(addItem(13008, "Мороженое с мятой и шоколадной крошкой"   , "Джелато с мятно-шоколадной крошкой"              , "foodMintchocolatechipicecream"   , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Strawberry           .set(addItem(13009, "Клубничное мороженое"                     , "Фруктовый красный джелато"                       , "foodStrawberryicecream"          , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Cherry               .set(addItem(13010, "Вишневое мороженое"                       , "Фруктовый красный джелато"                       , "foodCherryicecream"              , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Blueberry            .set(addItem(13011, "Черничное мороженое"                      , "Смурфи Блю Джелато"                              , "foodBlueberryicecream"           , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Currant              .set(addItem(13012, "Мороженое из смородины"                   , "Самое актуальное мороженое"                      , "foodCurranticecream"             , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Blackberry           .set(addItem(13013, "Ежевичное мороженое"                      , "Фруктовый темный джелато"                        , "foodBlackberryicecream"          , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Raspberry            .set(addItem(13014, "Малиновое мороженое"                      , "Фруктовый красный джелато"                       , "foodRaspberryicecream"           , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Cranberry            .set(addItem(13015, "Клюквенное мороженое"                     , "Фруктовый красный джелато"                       , "foodCranberryicecream"           , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Gooseberry           .set(addItem(13016, "Мороженое с крыжовником"                  , "Фруктовый желтый джелато"                        , "foodGooseberryicecream"          , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Nutella              .set(addItem(13017, "Мороженое Nutella"                        , "Джелато с идеальным вкусом"                      , "foodNutellaicecream"             , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  15,  15, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Lemon                .set(addItem(13018, "Лимонное мороженое"                       , "Кислый желтый джелато"                           , "foodLemonicecream"               , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Maple                .set(addItem(13019, "Кленовое мороженое"                       , "Джелато со вкусом клена"                         , "foodMapleicecream"               , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  30,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Pistachio            .set(addItem(13020, "Фисташковое мороженое"                    , "Джелато со вкусом орехов"                        , "foodPistachioicecream"           , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  10,  10, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Bacon                .set(addItem(13021, "Мороженое с беконом"                      , "Мэнли Джелато"                                   , "foodBaconicecream"               , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Ice_Cream_Kiwi                 .set(addItem(13022, "Мороженое с киви"                         , "Симпатичное зеленое джелато"                     , "foodKiwiicecream"                , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Melon                .set(addItem(13023, "Мороженое из арбуза"                      , "Водянистое красное джелато"                      , "foodMelonicecream"               , new FoodStat( 1, 0.600F,  10, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Banana               .set(addItem(13024, "Банановое мороженое"                      , "Миньон Джелато"                                  , "foodBananaicecream"              , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15, 0,2, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Grape                .set(addItem(13025, "Мороженое из винограда"                   , "Водянистое зеленое джелато"                      , "foodGrapeicecream"               , new FoodStat( 1, 0.600F,  10, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Apple                .set(addItem(13026, "Яблочное мороженое"                       , "Фруктовый желтый джелато"                        , "foodAppleicecream"               , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Peanut_Butter        .set(addItem(13027, "Мороженое с арахисовым маслом"            , "Время мороженого с арахисовым маслом!"           , "foodPeanutbuttericecream"        , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  15,  15, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Ananas               .set(addItem(13028, "Ананасовое мороженое"                     , "Ананасовое Джелато"                              , "foodPineappleicecream"           , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.ARBOR, 1)));
		IL.Food_Ice_Cream_Chum                 .set(addItem(13029, "Чум мороженое"                            , "Фу и Фу!"                                        , "foodChumicecream"                , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,  20,  10,   0, EnumAction.eat, null                                 , T, F, T, T, Potion.hunger.id, 250, 4, 100, Potion.confusion.id, 100, 1, 80), TC.stack(TC.GELUM, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Ice_Cream_Honey                .set(addItem(13030, "Мороженое с медом"                        , "Пчела Джелато"                                   , "foodHoneyicecream"               , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  25,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.BESTIA, 1)));
		IL.Food_Ice_Cream_Bear                 .set(addItem(13989, "Медвежье мороженое"                       , "Джелато для медведя!"                            , "foodBearicecream"                , new FoodStat( 2, 1.200F,   0, C+35,  0.50F,   0,   0,   5,  10,   5, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.BESTIA, 1)));
		IL.Food_Ice_Cream_Neapolitan           .set(addItem(13995, "Неаполитанское мороженое"                 , "Джелато микс из клубники, ванили и шоколада"     , "foodNeapolitanicecream"          , new FoodStat( 1, 0.600F,   2, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Spumoni_Chocolate    .set(addItem(13996, "Шоколадное мороженое Spumoni"             , "Микс джелато из вишни, фисташек и шоколада"      , "foodSpumoniicecream"             , new FoodStat( 1, 0.600F,   2, C+35,  0.50F,   0,   0,   5,  18,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Spumoni_Vanilla      .set(addItem(13997, "Ванильное мороженое Spumoni"              , "Микс джелато из вишни, фисташек и ванили"        , "foodSpumoniicecream"             , new FoodStat( 1, 0.600F,   2, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Superman             .set(addItem(13998, "Мороженое Супермэн"                       , "Джелато, спасающее день!"                        , "foodSupermanicecream"            , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Rainbow              .set(addItem(13999, "Радужное мороженое"                       , "Джелато дружбы и волшебства"                     , "foodRainbowicecream"             , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T, Potion.regeneration.id, 10, 4, 100, Potion.digSpeed.id, 200, 2, 100), TC.stack(TC.GELUM, 1), TC.stack(TC.AURAM, 1)));
		
		for (FluidStack tWater : FL.waters(250)) {
		RM.CryoMixer.addRecipe1(T, 16, 16, OM.dust(MT.NaCl, U4), FL.array(tWater           , FL.Cream.make( 250)), ZL_FS, IL.Food_Ice_Cream.get(1));
		RM.CryoMixer.addRecipe1(T, 16, 64, OM.dust(MT.NaCl, U ), FL.array(FL.mul(tWater, 4), FL.Cream.make(1000)), ZL_FS, IL.Food_Ice_Cream.get(4));
		}
		
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Lemon             .make( 50), NF, IL.Food_Ice_Cream_Lemon.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Banana            .make( 50), NF, IL.Food_Ice_Cream_Banana.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Grape_Red         .make( 50), NF, IL.Food_Ice_Cream_Grape.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Grape_White       .make( 50), NF, IL.Food_Ice_Cream_Grape.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Grape_Green       .make( 50), NF, IL.Food_Ice_Cream_Grape.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Grape_Purple      .make( 50), NF, IL.Food_Ice_Cream_Grape.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Apple             .make( 50), NF, IL.Food_Ice_Cream_Apple.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_AppleGrC          .make( 50), NF, IL.Food_Ice_Cream_Apple.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Ananas            .make( 50), NF, IL.Food_Ice_Cream_Ananas.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Cherry            .make( 50), NF, IL.Food_Ice_Cream_Cherry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Cranberry         .make( 50), NF, IL.Food_Ice_Cream_Cranberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Strawberry        .make( 50), NF, IL.Food_Ice_Cream_Strawberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Kiwi              .make( 50), NF, IL.Food_Ice_Cream_Kiwi.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Melon             .make( 50), NF, IL.Food_Ice_Cream_Melon.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Currant           .make( 50), NF, IL.Food_Ice_Cream_Currant.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Raspberry         .make( 50), NF, IL.Food_Ice_Cream_Raspberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Blackberry        .make( 50), NF, IL.Food_Ice_Cream_Blackberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Blueberry         .make( 50), NF, IL.Food_Ice_Cream_Blueberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Gooseberry        .make( 50), NF, IL.Food_Ice_Cream_Gooseberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.RoyalJelly              .make(  5), NF, IL.Food_Ice_Cream_Honey.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Honey                   .make( 50), NF, IL.Food_Ice_Cream_Honey.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.HoneyGrC                .make( 50), NF, IL.Food_Ice_Cream_Honey.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.HoneyBoP                .make( 50), NF, IL.Food_Ice_Cream_Honey.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), MT.Chocolate           .liquid(U4, T), NF, IL.Food_Ice_Cream_Chocolate.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Syrup_Maple             .make( 50), NF, IL.Food_Ice_Cream_Maple.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Cream_Nutella           .make( 50), NF, IL.Food_Ice_Cream_Nutella.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Nutbutter_Peanut        .make( 50), NF, IL.Food_Ice_Cream_Peanut_Butter.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Sap_Rainbow             .make( 50), NF, IL.Food_Ice_Cream_Rainbow.get(1));
		
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.Chocolate   , U4), IL.Food_Ice_Cream_Stracciatella.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.Chocolate   , U ), IL.Food_Ice_Cream_Stracciatella.get(4));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.Vanilla     , U4), IL.Food_Ice_Cream_Vanilla.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.Vanilla     , U ), IL.Food_Ice_Cream_Vanilla.get(4));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.Coffee      , U4), IL.Food_Ice_Cream_Mocha.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.Coffee      , U ), IL.Food_Ice_Cream_Mocha.get(4));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.Mint        , U4), IL.Food_Ice_Cream_Mint.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.Mint        , U ), IL.Food_Ice_Cream_Mint.get(4));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.Pistachio   , U4), IL.Food_Ice_Cream_Pistachio.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.Pistachio   , U ), IL.Food_Ice_Cream_Pistachio.get(4));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.MeatCooked  , U4), IL.Food_Ice_Cream_Bear.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.MeatCooked  , U ), IL.Food_Ice_Cream_Bear.get(4));
		
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream_Mint.get(1), OM.dust(MT.Chocolate  , U4), IL.Food_Ice_Cream_Mint_Chocolate_Chip.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream_Mint.get(4), OM.dust(MT.Chocolate  , U ), IL.Food_Ice_Cream_Mint_Chocolate_Chip.get(4));
		
		RM.Mixer.addRecipeX(T, 16, 48, ST.array(IL.Food_Ice_Cream_Strawberry.get(1), IL.Food_Ice_Cream_Vanilla  .get(1), IL.Food_Ice_Cream_Chocolate.get(1)), IL.Food_Ice_Cream_Neapolitan.get(3));
		RM.Mixer.addRecipeX(T, 16, 48, ST.array(IL.Food_Ice_Cream_Cherry    .get(1), IL.Food_Ice_Cream_Pistachio.get(1), IL.Food_Ice_Cream_Vanilla  .get(1)), IL.Food_Ice_Cream_Spumoni_Vanilla.get(3));
		RM.Mixer.addRecipeX(T, 16, 48, ST.array(IL.Food_Ice_Cream_Cherry    .get(1), IL.Food_Ice_Cream_Pistachio.get(1), IL.Food_Ice_Cream_Chocolate.get(1)), IL.Food_Ice_Cream_Spumoni_Chocolate.get(3));
		RM.Mixer.addRecipeX(T, 16, 48, ST.array(IL.Food_Ice_Cream_Cherry    .get(1), IL.Food_Ice_Cream_Blueberry.get(1), IL.Food_Ice_Cream_Lemon    .get(1)), IL.Food_Ice_Cream_Superman.get(3));
		
		
		
		
		
		
		
		
		
		
		IL.Pill_Empty                          .set(addItem(31000, "Пустая восковая таблетка"                 , "Плацебо"                                                     , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , T, F, F, T                                     ), TC.stack(TC.SANO, 1), TC.stack(TC.VACUOS , 1))); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		IL.Pill_Iodine                         .set(addItem(31001, "Средство от радиации"                     , "Таблетка, снимающая действие радиации"                       , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0, -50, EnumAction.eat, null                                 , T, F, F, T, PotionsGT.ID_RADIATION, -1, -1, 100), TC.stack(TC.SANO, 3)                         )); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		IL.Pill_Mint                           .set(addItem(31002, "Перечная мята"                            , "Сделайте свежий энергичный вдох"                             , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , T, F, F, T, Potion.moveSlowdown.id, -1, -1, 100), TC.stack(TC.SANO, 1), TC.stack(TC.HERBA  , 1))); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		IL.Pill_Blue                           .set(addItem(31003, "Синяя таблетка"                           , "Игнорируйте тошнотворную реальность"                         , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , T, F, F, T, Potion.confusion   .id, -1, -1, 100), TC.stack(TC.SANO, 1), TC.stack(TC.CORPUS , 1))); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		IL.Pill_Red                            .set(addItem(31004, "Красная таблетка"                         , "Открой глаза на правду"                                      , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , T, F, F, T, Potion.blindness   .id, -1, -1, 100), TC.stack(TC.SANO, 1), TC.stack(TC.SENSUS , 1))); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		IL.Pill_Antidote                       .set(addItem(31005, "Противоядие"                              , "Таблетка, излечивающая яд"                                   , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , T, F, F, T, Potion.poison      .id, -1, -1, 100), TC.stack(TC.SANO, 1), TC.stack(TC.VENEMUM, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		RM.Boxinator    .addRecipe2(T, 16,   16, OM.dust(MT.I)                          , IL.Pill_Empty.get(1), IL.Pill_Iodine.get(1));
		RM.Boxinator    .addRecipe2(T, 16,   16, OM.dust(MT.Mint)                       , IL.Pill_Empty.get(1), IL.Pill_Mint.get(1));
		RM.Boxinator    .addRecipe2(T, 16,   16, ST.make(Blocks.brown_mushroom, 1, W)   , IL.Pill_Empty.get(1), IL.Pill_Antidote.get(1));
		
		IL.Pill_Cure_All                       .set(addItem(31999, "Вылечить все"                             , "Лечит все, что вы могли вообразить*"                         , new FoodStat( 0, 0.000F,   0, C+37,  1.00F,-999,-999,-999,-999,-999,-999, EnumAction.eat, null                                 , T, F, F, T, PotionsGT.ID_RADIATION, -1, -1, 100, PotionsGT.ID_HYPOTHERMIA, -1, -1, 100, PotionsGT.ID_HEATSTROKE, -1, -1, 100, PotionsGT.ID_FROSTBITE, -1, -1, 100, PotionsGT.ID_DEHYDRATION, -1, -1, 100, PotionsGT.ID_INSANITY, -1, -1, 100, PotionsGT.ID_FLAMMABLE, -1, -1, 100, PotionsGT.ID_SLIPPERY, -1, -1, 100, PotionsGT.ID_CONDUCTIVE, -1, -1, 100, PotionsGT.ID_STICKY, -1, -1, 100, Potion.digSlowdown.id, -1, -1, 100, Potion.moveSlowdown.id, -1, -1, 100, Potion.hunger.id, -1, -1, 100, Potion.harm.id, -1, -1, 100, Potion.confusion.id, -1, -1, 100, Potion.blindness.id, -1, -1, 100, Potion.weakness.id, -1, -1, 100, Potion.poison.id, -1, -1, 100, Potion.wither.id, -1, -1, 100, Potion.regeneration.id, 100, 100, 100, Potion.field_76443_y.id, 100, 100, 100).setMilk().setExtinguish(), new Behavior_CureZombie(500, F), TC.stack(TC.SANO, 10), TD.Creative.HIDDEN)); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		
		
		
		IL.Food_Tofu                           .set(addItem(32101, "Батончик Тофу"                            , "Альтернатива мясу"                   , OP.ingot.dat(MT.Tofu)         , new FoodStat( 2, 1.400F,   0, C+37,  0.10F,   0,   0,   4,   4,   0, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)35);
		IL.Food_SoylentGreen                   .set(addItem(32103, "Изумрудно-зеленый батончик"               , "Изумрудно-зеленый - это жители деревни!"         , OP.ingot.dat(MT.SoylentGreen) , new FoodStat( 3, 1.400F,   0, C+37,  0.10F,   0,   0,   4,   0,  12, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.CORPUS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)34);
		IL.Food_Meat_Raw                       .set(addItem(32105, "Батончик из сырого мяса"                  , "Не ешьте сырой мясной фарш"            , OP.ingot.dat(MT.MeatRaw)      , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   4,   0,  12, EnumAction.eat, null                         , F, T, F, T, Potion.hunger.id, 300, 0, 50), TC.stack(TC.CORPUS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Meat                           .set(addItem(32107, "Вареный мясной батончик"                  , "Сжатое мясо"                     , OP.ingot.dat(MT.MeatCooked)   , new FoodStat( 2, 1.600F,   0, C+38,  0.50F,   0,   0,   4,   0,   4, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.CORPUS, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		IL.Food_Chocolate                      .set(addItem(32109, "Шоколадный батончик"                      , "Не для собак и кошек!"         , OP.ingot.dat(MT.Chocolate)    , new FoodStat( 4, 0.800F,   0, C+37,  0.10F,   0,   0,   0,  40,   0, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.MOTUS, 1), TC.stack(TC.FAMES, 1), Behavior_FeedChocolate.INSTANCE)); OreDictManager.INSTANCE.setTarget_(OP.ingot, MT.Chocolate, last()); Sandwiches.INGREDIENTS.put(last(), (byte)21);
		IL.Food_Cheese_Bar                     .set(addItem(32111, "Сырный батончик"                          , "Компактный сыр"                      , OP.ingot.dat(MT.Cheese)       , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   8,   0,   8, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.FAMES, 2)));
		IL.Food_Fish_Raw                       .set(addItem(32113, "Сырой рыбный батончик"                    , "Компактная рыба"                     , OP.ingot.dat(MT.FishRaw)      , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   3,   0,   8, EnumAction.eat, null                         , F, T, F, T, Potion.hunger.id, 300, 0, 50), TC.stack(TC.CORPUS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)24);
		IL.Food_Fish                           .set(addItem(32115, "Вареный рыбный батончик"                  , "Любите рыбные палочки? Ты рыба-гей" , OP.ingot.dat(MT.FishCooked)   , new FoodStat( 2, 1.600F,   0, C+38,  0.50F,   0,   0,   3,   0,   8, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.CORPUS, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)25);
		IL.Food_Butter                         .set(addItem(32117, "Масло сливочное"                          , "Кусок чистого жира"                 , OP.ingot.dat(MT.Butter)       , new FoodStat( 1, 4.000F,   0, C+37,  0.10F,   0,   0,   0,   0,  80, EnumAction.eat, null                         , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 1, 90), TC.stack(TC.FAMES, 3))); OreDictManager.INSTANCE.setTarget_(OP.ingot, MT.Butter      , last()); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Yellow);
		IL.Food_Butter_Salted                  .set(addItem(32119, "Соленое масло"                            , "Как будто это уже не было нездоровым"   , OP.ingot.dat(MT.ButterSalted) , new FoodStat( 1, 4.000F,   0, C+37,  0.10F,   0,   0,  40,   0,  80, EnumAction.eat, null                         , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 1, 90), TC.stack(TC.FAMES, 3))); OreDictManager.INSTANCE.setTarget_(OP.ingot, MT.ButterSalted, last()); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Yellow);
		RM.replicateOrganic( 7,  8, IL.Food_Tofu.get(1));
		RM.replicateOrganic( 7,  9, IL.Food_SoylentGreen.get(1));
		RM.replicateOrganic( 7, 10, IL.Food_Meat_Raw.get(1));
		RM.replicateOrganic( 7, 11, IL.Food_Chocolate.get(1));
		RM.replicateOrganic( 7, 12, IL.Food_Cheese_Bar.get(1));
		RM.replicateOrganic( 7, 13, IL.Food_Fish_Raw.get(1));
		RM.replicateOrganic( 7, 14, IL.Food_Butter.get(1));
		
		RM.replicateOrganic( 8,  9, ST.make(Blocks.brown_mushroom_block, 1, 0));
		RM.replicateOrganic( 8, 10, ST.make(Blocks.red_mushroom_block, 1, 0));
		RM.replicateOrganic( 8, 11, ST.make(Items.nether_wart, 1, 0));
		RM.replicateOrganic( 8, 12, OP.plantGtWart.mat(MT.Milk, 1));
		RM.replicateOrganic( 8, 13, OP.plantGtWart.mat(MT.Glowstone, 1));
		
		RM.replicateOrganic( 9, 10, OP.plantGtBlossom.mat(MT.Tea, 1));
		RM.replicateOrganic( 9, 11, OP.plantGtBlossom.mat(MT.Mint, 1));
		RM.replicateOrganic( 9, 12, OP.plantGtBlossom.mat(MT.Indigo, 1));
		
		RM.replicateOrganic(10, 11, IL.Food_White_Egg.get(1));
		RM.replicateOrganic(10, 12, ST.make(Items.feather, 1, 0));
		RM.replicateOrganic(10, 13, ST.make(Items.leather, 1, 0));
		RM.replicateOrganic(10, 14, ST.make(Blocks.web, 1, 0));
		RM.replicateOrganic(10, 15, ST.make(Items.bone, 1, 0));
		RM.replicateOrganic(10, 16, ST.make(Items.slime_ball, 1, 0));
		RM.replicateOrganic(10, 17, ST.make(Items.spider_eye, 1, 0));
		RM.replicateOrganic(10, 18, ST.make(Items.ghast_tear, 1, 0));
		RM.replicateOrganic(10, 19, ST.make(Items.reeds, 1, 0));
		RM.replicateOrganic(10, 20, ST.make(Blocks.cactus, 1, 0));
		RM.replicateOrganic(10, 21, ST.make(Blocks.vine, 1, 0));
		RM.replicateOrganic(10, 22, ST.make(Blocks.waterlily, 1, 0));
		RM.replicateOrganic(10, 23, IL.Dye_SquidInk.get(1));
		RM.replicateOrganic(10, 24, IL.Dye_Cocoa.get(1));
	}
	
	@Override
	public ItemStack getRotten(ItemStack aStack) {
		short aMeta = ST.meta_(aStack);
		if (UT.Code.inside(    0,   999, aMeta)) return (IL.ENVM_Rotten_Food.exists()?IL.ENVM_Rotten_Food:IL.Remains_Plant).get(aStack.stackSize);
		if (UT.Code.inside( 1100,  1999, aMeta)) return ST.make(Items.rotten_flesh, aStack.stackSize, 0);
		if (UT.Code.inside(13000, 13999, aMeta)) return null;
		if (UT.Code.inside(31000, 31999, aMeta)) return aStack;
		
		switch(aMeta) {
		case 12000: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 12001: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 12002: return ST.make(this, aStack.stackSize, 12003, aStack.getTagCompound());
		case 12004: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 12005: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 12006: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 12007: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 32700: return (IL.ENVM_Rotten_Food.exists()?IL.ENVM_Rotten_Food:IL.Remains_Plant).get(aStack.stackSize);
		case 32701: return (IL.ENVM_Rotten_Food.exists()?IL.ENVM_Rotten_Food:IL.Remains_Plant).get(aStack.stackSize);
		case 32105: case 32107: return OP.ingot.mat(MT.MeatRotten, aStack.stackSize);
		case 32113: case 32115: return OP.ingot.mat(MT.FishRotten, aStack.stackSize);
		
		default: return ST.food(aStack) > 0 ? IL.ENVM_Rotten_Food.exists() ? IL.ENVM_Rotten_Food.get(aStack.stackSize) : null : aStack;
		}
	}
	
	@Override public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {return getRotten(aStack);}
}
