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

import gregapi.data.CS.*;
import gregapi.data.IL;
import gregapi.data.TC;
import gregapi.data.TD;
import gregapi.item.CreativeTab;
import gregapi.item.IItemRottable;
import gregapi.item.multiitem.MultiItemRandomWithCompat;
import gregapi.item.multiitem.behaviors.Behavior_FeedCat;
import gregapi.item.multiitem.behaviors.Behavior_FeedDog;
import gregapi.item.multiitem.food.FoodStat;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

import static gregapi.data.CS.*;

public class MultiItemCans extends MultiItemRandomWithCompat implements IItemRottable {
	public MultiItemCans(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized);
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Консервы", this, (short)74));
	}
	
	@Override
	public void addItems() {
		@SuppressWarnings("unused")
		int tLastID = 0;
		
		IL.Food_Can_Undefined_1.set(addItem(tLastID =     1, "Крошечные консервы (Неизвестно)", "", new FoodStat( 2, 0.1F,  5, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		IL.Food_Can_Undefined_2.set(addItem(tLastID =     2, "Маленькие консервы (Неизвестно)", "", new FoodStat( 4, 0.2F, 10, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		IL.Food_Can_Undefined_3.set(addItem(tLastID =     3, "Высокие консервы (Неизвестно)"  , "", new FoodStat( 6, 0.3F, 15, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.FAMES, 2), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		IL.Food_Can_Undefined_4.set(addItem(tLastID =     4, "Широкие консервы (Неизвестно)"  , "", new FoodStat( 8, 0.4F, 20, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.FAMES, 2), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		IL.Food_Can_Undefined_5.set(addItem(tLastID =     5, "Большие консервы (Неизвестно)"  , "", new FoodStat(10, 0.5F, 25, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.FAMES, 3), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		IL.Food_Can_Undefined_6.set(addItem(tLastID =     6, "Огромные консервы (Неизвестно)" , "", new FoodStat(12, 0.6F, 30, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.FAMES, 3), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		
		IL.Food_Can_Rotten_1   .set(addItem(tLastID =    11, "Крошечные консервы (Испорчено)" , "", new FoodStat( 2, 0.1F,  5, C+37, 0.10F,  4,  0,  4,  4,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 300, 0, 40), TC.stack(TC.FAMES, 1), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Can_Rotten_2   .set(addItem(tLastID =    12, "Маленькие консервы (Испорчено)" , "", new FoodStat( 4, 0.2F, 10, C+37, 0.10F,  8,  0,  8,  8,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 300, 0, 50), TC.stack(TC.FAMES, 1), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Can_Rotten_3   .set(addItem(tLastID =    13, "Высокие консервы (Испорчено)"   , "", new FoodStat( 6, 0.3F, 15, C+37, 0.10F, 12,  0, 12, 12,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 300, 0, 60), TC.stack(TC.FAMES, 2), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Can_Rotten_4   .set(addItem(tLastID =    14, "Широкие консервы (Испорчено)"   , "", new FoodStat( 8, 0.4F, 20, C+37, 0.10F, 16,  0, 16, 16,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 300, 0, 70), TC.stack(TC.FAMES, 2), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Can_Rotten_5   .set(addItem(tLastID =    15, "Большие консервы (Испорчено)"   , "", new FoodStat(10, 0.5F, 25, C+37, 0.10F, 20,  0, 20, 20,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 300, 0, 80), TC.stack(TC.FAMES, 3), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Can_Rotten_6   .set(addItem(tLastID =    16, "Огромные консервы (Испорчено)"  , "", new FoodStat(12, 0.6F, 30, C+37, 0.10F, 24,  0, 24, 24,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 300, 0, 90), TC.stack(TC.FAMES, 3), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		
		IL.Food_Can_Veggie_1   .set(addItem(tLastID =    21, "Крошечные консервы (Овощи)"     , "", new FoodStat( 2, 0.1F, 10, C+37, 0.10F,  0,  0,  0,  5,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FABRICO, 1), "listAllveggie")); CR.remove(last());
		IL.Food_Can_Veggie_2   .set(addItem(tLastID =    22, "Маленькие консервы (Овощи)"     , "", new FoodStat( 4, 0.2F, 20, C+37, 0.10F,  0,  0,  0, 10,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FABRICO, 1), "listAllveggie")); CR.remove(last());
		IL.Food_Can_Veggie_3   .set(addItem(tLastID =    23, "Высокие консервы (Овощи)"       , "", new FoodStat( 6, 0.3F, 30, C+37, 0.10F,  0,  0,  0, 15,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FABRICO, 1), "listAllveggie")); CR.remove(last());
		IL.Food_Can_Veggie_4   .set(addItem(tLastID =    24, "Широкие консервы (Овощи)"       , "", new FoodStat( 8, 0.4F, 40, C+37, 0.10F,  0,  0,  0, 20,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FABRICO, 1), "listAllveggie")); CR.remove(last());
		IL.Food_Can_Veggie_5   .set(addItem(tLastID =    25, "Большие консервы (Овощи)"       , "", new FoodStat(10, 0.5F, 50, C+37, 0.10F,  0,  0,  0, 25,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 3), TC.stack(TC.FABRICO, 1), "listAllveggie")); CR.remove(last());
		IL.Food_Can_Veggie_6   .set(addItem(tLastID =    26, "Огромные консервы (Овощи)"      , "", new FoodStat(12, 0.6F, 60, C+37, 0.10F,  0,  0,  0, 30,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 3), TC.stack(TC.FABRICO, 1), "listAllveggie")); CR.remove(last());
		
		IL.Food_Can_Fruit_1    .set(addItem(tLastID =    31, "Крошечные консервы (Фрукты)"    , "", new FoodStat( 2, 0.1F, 15, C+37, 0.30F,  0,  0,  0, 10,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FABRICO, 1), "listAllfruit")); CR.remove(last());
		IL.Food_Can_Fruit_2    .set(addItem(tLastID =    32, "Маленькие консервы (Фрукты)"    , "", new FoodStat( 4, 0.2F, 30, C+37, 0.30F,  0,  0,  0, 20,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FABRICO, 1), "listAllfruit")); CR.remove(last());
		IL.Food_Can_Fruit_3    .set(addItem(tLastID =    33, "Высокие консервы (Фрукты)"      , "", new FoodStat( 6, 0.3F, 45, C+37, 0.30F,  0,  0,  0, 30,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FABRICO, 1), "listAllfruit")); CR.remove(last());
		IL.Food_Can_Fruit_4    .set(addItem(tLastID =    34, "Широкие консервы (Фрукты)"      , "", new FoodStat( 8, 0.4F, 60, C+37, 0.30F,  0,  0,  0, 40,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FABRICO, 1), "listAllfruit")); CR.remove(last());
		IL.Food_Can_Fruit_5    .set(addItem(tLastID =    35, "Большие консервы (Фрукты)"      , "", new FoodStat(10, 0.5F, 75, C+37, 0.30F,  0,  0,  0, 50,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 3), TC.stack(TC.FABRICO, 1), "listAllfruit")); CR.remove(last());
		IL.Food_Can_Fruit_6    .set(addItem(tLastID =    36, "Огромные консервы (Фрукты)"     , "", new FoodStat(12, 0.6F, 90, C+37, 0.30F,  0,  0,  0, 60,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 3), TC.stack(TC.FABRICO, 1), "listAllfruit")); CR.remove(last());
		
		IL.Food_Can_Bread_1    .set(addItem(tLastID =    41, "Крошечные консервы (Хлеб)"      , "", new FoodStat( 2, 0.1F,  2, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		IL.Food_Can_Bread_2    .set(addItem(tLastID =    42, "Маленькие консервы (Хлеб)"      , "", new FoodStat( 4, 0.2F,  4, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		IL.Food_Can_Bread_3    .set(addItem(tLastID =    43, "Высокие консервы (Хлеб)"        , "", new FoodStat( 6, 0.3F,  6, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		IL.Food_Can_Bread_4    .set(addItem(tLastID =    44, "Широкие консервы (Хлеб)"        , "", new FoodStat( 8, 0.4F,  8, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		IL.Food_Can_Bread_5    .set(addItem(tLastID =    45, "Большие консервы (Хлеб)"        , "", new FoodStat(10, 0.5F, 10, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 3), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		IL.Food_Can_Bread_6    .set(addItem(tLastID =    46, "Огромные консервы (Хлеб)"       , "", new FoodStat(12, 0.6F, 12, C+37, 0.10F,  0,  0,  0,  0,  0, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.HERBA, 3), TC.stack(TC.FABRICO, 1))); CR.remove(last());
		
		IL.Food_Can_Meat_1     .set(addItem(tLastID =    51, "Крошечные консервы (Мясо)"      , "", new FoodStat( 2, 0.1F,  5, C+37, 0.10F,  0,  0,  4,  0,  4, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 1), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, Behavior_FeedDog.INSTANCE, "listAllmeatraw", "listAllmeatcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Can_Meat_2     .set(addItem(tLastID =    52, "Маленькие консервы (Мясо)"      , "", new FoodStat( 4, 0.2F, 10, C+37, 0.10F,  0,  0,  8,  0,  8, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 1), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, Behavior_FeedDog.INSTANCE, "listAllmeatraw", "listAllmeatcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Can_Meat_3     .set(addItem(tLastID =    53, "Высокие консервы (Мясо)"        , "", new FoodStat( 6, 0.3F, 15, C+37, 0.10F,  0,  0, 12,  0, 12, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 2), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, Behavior_FeedDog.INSTANCE, "listAllmeatraw", "listAllmeatcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Can_Meat_4     .set(addItem(tLastID =    54, "Широкие консервы (Мясо)"        , "", new FoodStat( 8, 0.4F, 20, C+37, 0.10F,  0,  0, 16,  0, 16, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 2), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, Behavior_FeedDog.INSTANCE, "listAllmeatraw", "listAllmeatcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Can_Meat_5     .set(addItem(tLastID =    55, "Большие консервы (Мясо)"        , "", new FoodStat(10, 0.5F, 25, C+37, 0.10F,  0,  0, 20,  0, 20, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 3), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, Behavior_FeedDog.INSTANCE, "listAllmeatraw", "listAllmeatcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Can_Meat_6     .set(addItem(tLastID =    56, "Огромные консервы (Мясо)"       , "", new FoodStat(12, 0.6F, 30, C+37, 0.10F,  0,  0, 24,  0, 24, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 3), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, Behavior_FeedDog.INSTANCE, "listAllmeatraw", "listAllmeatcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		
		IL.Food_Can_Fish_1     .set(addItem(tLastID =    61, "Крошечные консервы (Рыба)"      , "", new FoodStat( 2, 0.1F,  5, C+37, 0.10F,  0,  0,  2,  0,  2, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 1), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, "listAllfishraw", "listAllfishcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)25);
		IL.Food_Can_Fish_2     .set(addItem(tLastID =    62, "Маленькие консервы (Рыба)"      , "", new FoodStat( 4, 0.2F, 10, C+37, 0.10F,  0,  0,  4,  0,  4, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 1), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, "listAllfishraw", "listAllfishcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)25);
		IL.Food_Can_Fish_3     .set(addItem(tLastID =    63, "Высокие консервы (Рыба)"        , "", new FoodStat( 6, 0.3F, 15, C+37, 0.10F,  0,  0,  8,  0,  8, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 2), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, "listAllfishraw", "listAllfishcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)25);
		IL.Food_Can_Fish_4     .set(addItem(tLastID =    64, "Широкие консервы (Рыба)"        , "", new FoodStat( 8, 0.4F, 20, C+37, 0.10F,  0,  0, 10,  0, 10, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 2), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, "listAllfishraw", "listAllfishcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)25);
		IL.Food_Can_Fish_5     .set(addItem(tLastID =    65, "Большие консервы (Рыба)"        , "", new FoodStat(10, 0.5F, 25, C+37, 0.10F,  0,  0, 12,  0, 12, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 3), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, "listAllfishraw", "listAllfishcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)25);
		IL.Food_Can_Fish_6     .set(addItem(tLastID =    66, "Огромные консервы (Рыба)"       , "", new FoodStat(12, 0.6F, 30, C+37, 0.10F,  0,  0, 14,  0, 14, EnumAction.eat, NI, F, F, F, T), TC.stack(TC.CORPUS, 3), TC.stack(TC.FABRICO, 1), Behavior_FeedCat.INSTANCE, "listAllfishraw", "listAllfishcooked")); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)25);
		
		IL.Food_Can_Chum_1     .set(addItem(tLastID =    71, "Крошечные консервы (ЧУМ)"       , "", new FoodStat( 2, 0.1F,  5, C+37, 0.10F,  0,  0, 10,  0,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.CORPUS, 1), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)39);
		IL.Food_Can_Chum_2     .set(addItem(tLastID =    72, "Маленькие консервы (ЧУМ)"       , "", new FoodStat( 4, 0.2F, 10, C+37, 0.10F,  0,  0, 20,  0,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.CORPUS, 1), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)39);
		IL.Food_Can_Chum_3     .set(addItem(tLastID =    73, "Высокие консервы (ЧУМ)"         , "", new FoodStat( 6, 0.3F, 15, C+37, 0.10F,  0,  0, 30,  0,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.CORPUS, 2), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)39);
		IL.Food_Can_Chum_4     .set(addItem(tLastID =    74, "Широкие консервы (ЧУМ)"         , "", new FoodStat( 8, 0.4F, 20, C+37, 0.10F,  0,  0, 40,  0,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.CORPUS, 2), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)39);
		IL.Food_Can_Chum_5     .set(addItem(tLastID =    75, "Большие консервы (ЧУМ)"         , "", new FoodStat(10, 0.5F, 25, C+37, 0.10F,  0,  0, 50,  0,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.CORPUS, 3), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)39);
		IL.Food_Can_Chum_6     .set(addItem(tLastID =    76, "Огромные консервы (ЧУМ)"        , "", new FoodStat(12, 0.6F, 30, C+37, 0.10F,  0,  0, 60,  0,  0, EnumAction.eat, NI, F, F, T, T, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.CORPUS, 3), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN)); CR.remove(last()); Sandwiches.INGREDIENTS.put(last(), (byte)39);
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack aStack) {
		return IL.Food_Can_Empty.get(1);
	}
	
	@Override
	public ItemStack getRotten(ItemStack aStack) {
		short tMeta = ST.meta_(aStack);
		return tMeta < 20 ? aStack : ST.make(this, aStack.stackSize, 10+(tMeta%10), aStack.getTagCompound());
	}
	
	@Override public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {return getRotten(aStack);}
}
