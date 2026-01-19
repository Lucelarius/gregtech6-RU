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

package gregapi.lang;

import cpw.mods.fml.common.registry.LanguageRegistry;
import gregapi.data.ANY;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.util.HashMap;
import java.util.Map.Entry;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class LanguageHandler {
	public static Configuration sLangFile;
	public static boolean sUseFile = F;
	
	private static final HashMap<String, String> TEMPMAP = new HashMap<>(), BUFFERMAP = new HashMap<>(), BACKUPMAP = new HashMap<>();
	private static boolean mWritingEnabled = F;
	
	public static void save() {
		if (sLangFile != null) {
			mWritingEnabled = T;
			sLangFile.save();
		}
	}
	
	public static synchronized void set(String aKey, String aEnglish) {
		BACKUPMAP.put(aKey, aEnglish);
		TEMPMAP.put(aKey        , aEnglish);
		TEMPMAP.put(aKey+".name", aEnglish);
		LanguageRegistry.instance().injectLanguage("en_US", TEMPMAP);
		TEMPMAP.clear();
	}
	
	public static synchronized void add(String aKey, String aEnglish) {
		if (aKey == null) return;
		aKey = aKey.trim();
		if (aKey.length() <= 0) return;
		boolean tSave = F;
		BACKUPMAP.put(aKey, aEnglish);
		if (sLangFile == null) {
			BUFFERMAP.put(aKey, aEnglish);
		} else {
			if (!BUFFERMAP.isEmpty()) {
				tSave = T;
				for (Entry<String, String> tEntry : BUFFERMAP.entrySet()) {
					Property tProperty = sLangFile.get("LanguageFile", tEntry.getKey(), tEntry.getValue());
					TEMPMAP.put(tEntry.getKey()        , sUseFile?tProperty.getString():tEntry.getValue());
					TEMPMAP.put(tEntry.getKey()+".name", sUseFile?tProperty.getString():tEntry.getValue());
					LanguageRegistry.instance().injectLanguage("en_US", TEMPMAP);
					TEMPMAP.clear();
				}
				BUFFERMAP.clear();
			}
			Property tProperty = sLangFile.get("LanguageFile", aKey, aEnglish);
			tSave |= tProperty.wasRead();
			TEMPMAP.put(aKey        , sUseFile?tProperty.getString():aEnglish);
			TEMPMAP.put(aKey+".name", sUseFile?tProperty.getString():aEnglish);
			LanguageRegistry.instance().injectLanguage("en_US", TEMPMAP);
			TEMPMAP.clear();
		}
		if (tSave && mWritingEnabled) sLangFile.save();
	}
	
	public static String get(String aKey, String aDefault) {
		add(aKey, aDefault);
		return translate(aKey, aDefault);
	}
	
	public static String langfile(String aKey, String aEnglish) {
		if (sLangFile == null) return aEnglish;
		Property tProperty = sLangFile.get("LanguageFile", aKey, aEnglish);
		if (tProperty.wasRead() && mWritingEnabled) sLangFile.save();
		return sUseFile?tProperty.getString():aEnglish;
	}
	
	public static String translate(String aKey) {
		return translate(aKey, aKey);
	}
	
	public static String translate(String aKey, String aDefault) {
		if (aKey == null || aKey.length() < 2) return "";
		aKey = aKey.trim();
		if (aKey.length() < 2) return "";
		String
		rTranslation = LanguageRegistry.instance().getStringLocalization(aKey);
		if (UT.Code.stringValid(rTranslation) && !aKey.equals(rTranslation)) return rTranslation;
		rTranslation = StatCollector.translateToLocal(aKey);
		if (UT.Code.stringValid(rTranslation) && aKey != rTranslation) return rTranslation;
		rTranslation = BACKUPMAP.get(aKey);
		if (UT.Code.stringValid(rTranslation)) return rTranslation;
		
		aKey = (aKey.endsWith(".name") ? aKey.substring(0, aKey.length() - 5) : aKey + ".name");
		
		rTranslation = LanguageRegistry.instance().getStringLocalization(aKey);
		if (UT.Code.stringValid(rTranslation) && !aKey.equals(rTranslation)) return rTranslation;
		rTranslation = StatCollector.translateToLocal(aKey);
		if (UT.Code.stringValid(rTranslation) && aKey != rTranslation) return rTranslation;
		rTranslation = BACKUPMAP.get(aKey);
		if (UT.Code.stringValid(rTranslation)) return rTranslation;
		
		return aDefault;
	}
	
	public static String separate(String aKey, String aSeparator) {
		if (aKey == null) return "";
		String rTranslation = "";
		for (String tString : aKey.split(aSeparator)) {
			rTranslation += get(tString, tString);
		}
		return rTranslation;
	}
	
	public static String getTranslateableItemStackName(ItemStack aStack) {
		if (ST.invalid(aStack)) return "null";
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT != null && tNBT.hasKey("display")) {
			String tName = tNBT.getCompoundTag("display").getString("Name");
			if (UT.Code.stringValid(tName)) {
				return tName;
			}
		}
		return aStack.getUnlocalizedName() + ".name";
	}
	
	public static String getLocalName(OreDictPrefix aPrefix, OreDictMaterial aMaterial) {
		// Certain Materials have slightly different default Localisations.
		//if (aPrefix == OP.crateGtDust     || aPrefix == OP.crateGt64Dust     || aPrefix == OP.blockDust    ) return aPrefix.mMaterialPre + getLocalName(OP.dust    , aMaterial);
		//if (aPrefix == OP.crateGtGem      || aPrefix == OP.crateGt64Gem      || aPrefix == OP.blockGem     ) return aPrefix.mMaterialPre + getLocalName(OP.gem     , aMaterial);
		//if (aPrefix == OP.crateGtIngot    || aPrefix == OP.crateGt64Ingot    || aPrefix == OP.blockIngot   ) return aPrefix.mMaterialPre + getLocalName(OP.ingot   , aMaterial);
		//if (aPrefix == OP.crateGtPlate    || aPrefix == OP.crateGt64Plate    || aPrefix == OP.blockPlate   ) return aPrefix.mMaterialPre + getLocalName(OP.plate   , aMaterial);
		//if (aPrefix == OP.crateGtPlateGem || aPrefix == OP.crateGt64PlateGem || aPrefix == OP.blockPlateGem) return aPrefix.mMaterialPre + getLocalName(OP.plateGem, aMaterial);
		//if (aPrefix == OP.crateGtRaw      || aPrefix == OP.crateGt64Raw      || aPrefix == OP.blockRaw     ) return aPrefix.mMaterialPre + getLocalName(OP.ore     , aMaterial);


		if (aPrefix == OP.dustDiv72 || aPrefix == OP.crateGtDust || aPrefix == OP.crateGt64Dust || aPrefix == OP.blockDust) {
			if (aMaterial == MT.WoodTreated || ANY.Plastic.mToThis.contains(aMaterial) || ANY.Rubber.mToThis.contains(aMaterial) || aMaterial.contains(TD.Properties.WOOD))
				return aPrefix.mMaterialPre + "целлюлозы (" + aMaterial.mNameLocal + ")";
			if (aMaterial == MT.Red || aMaterial == MT.Black || aMaterial == MT.Green || aMaterial == MT.Brown || aMaterial == MT.Blue || aMaterial == MT.Purple || aMaterial == MT.Cyan || aMaterial == MT.LightGray || aMaterial == MT.Gray || aMaterial == MT.Pink || aMaterial == MT.Lime || aMaterial == MT.Yellow || aMaterial == MT.LightBlue || aMaterial == MT.Magenta || aMaterial == MT.Orange || aMaterial == MT.White)
				return aPrefix.mMaterialPre + "красителя (" + aMaterial.mNameLocal + ")";
			if (aMaterial == MT.Wheat || aMaterial == MT.Rye || aMaterial == MT.Barley || aMaterial == MT.Oat || aMaterial == MT.OatAbyssal || aMaterial == MT.Corn || aMaterial == MT.Bone || aMaterial == MT.FishRaw || aMaterial == MT.FishCooked || aMaterial == MT.FishRotten)
				return aPrefix.mMaterialPre + "муки (" + aMaterial.mNameLocal + ")";
			if (aMaterial == MT.AncientDebris || aMaterial == MT.InfusedDull || aMaterial == MT.InfusedBalance || aMaterial == MT.InfusedVis || aMaterial == MT.InfusedAir || aMaterial == MT.InfusedWater || aMaterial == MT.InfusedEarth || aMaterial == MT.InfusedFire || aMaterial == MT.InfusedOrder || aMaterial == MT.InfusedEntropy || aMaterial == MT.Ceramic)
				return aPrefix.mMaterialPre + "порошка (" + aMaterial.mNameLocal + ")";
			if (aMaterial == MT.STONES.MoonTurf || aMaterial == MT.STONES.MarsSand)
				return aPrefix.mMaterialPre + "торфа (" + aMaterial.mNameLocal + ")";
			if (aMaterial == MT.MeatRaw || aMaterial == MT.MeatCooked || aMaterial == MT.MeatRotten)
				return aPrefix.mMaterialPre + "мясного фарша (" + aMaterial.mNameLocal + ")";
			if (aMaterial == MT.Rice)
				return aPrefix.mMaterialPre + "риса";
			return aPrefix.mMaterialPre + "пыли (" + aMaterial.mNameLocal + ")";
		}

		if (aPrefix == OP.crateGtGem      || aPrefix == OP.crateGt64Gem      || aPrefix == OP.blockGem     ) {
			if (aMaterial == MT.Ice)
				return aPrefix.mMaterialPre + "ледяных кубов";
			if (aMaterial == MT.InfusedDull || aMaterial == MT.InfusedBalance || aMaterial == MT.InfusedVis || aMaterial == MT.InfusedAir || aMaterial == MT.InfusedWater || aMaterial == MT.InfusedEarth || aMaterial == MT.InfusedFire || aMaterial == MT.InfusedOrder || aMaterial == MT.InfusedEntropy)
				return aPrefix.mMaterialPre + "осколков (" + aMaterial.mNameLocal + ")";
			return aPrefix.mMaterialPre + "самоцветов (" + aMaterial.mNameLocal + ")";
		}

		if (aPrefix == OP.crateGtIngot    || aPrefix == OP.crateGt64Ingot    || aPrefix == OP.blockIngot   ) {
			if (aMaterial == MT.Steeleaf)  return aPrefix.mMaterialPre + "сталелиста";
			if (aMaterial == MT.Fireleaf)  return aPrefix.mMaterialPre + "огнелиста";
			if (aMaterial == MT.Chocolate || aMaterial == MT.Cheese || aMaterial == MT.Butter || aMaterial == MT.ButterSalted || aMaterial == MT.SoylentGreen || aMaterial == MT.Tofu)
				return aPrefix.mMaterialPre + "батончиков (" + aMaterial.mNameLocal + ")";
			if (aMaterial == MT.Peat || aMaterial == MT.PeatBituminous || aMaterial == MT.Lignite || aMaterial == MT.LigniteCoke || aMaterial == MT.Charcoal || aMaterial == MT.Coal || aMaterial == MT.CoalCoke || aMaterial == MT.Anthracite || aMaterial == MT.Prismane || aMaterial == MT.Lonsdaleite || aMaterial == MT.PetCoke || aMaterial == MT.HydratedCoal)
				return aPrefix.mMaterialPre + "брикетов (" + aMaterial.mNameLocal + ")";
			if (ANY.Plastic.mToThis.contains(aMaterial) || ANY.Rubber.mToThis.contains(aMaterial))
				return aPrefix.mMaterialPre + "брусков (" +  aMaterial.mNameLocal + ")";
			return aPrefix.mMaterialPre + "слитков (" + aMaterial.mNameLocal + ")";
		}

		if (aPrefix == OP.crateGtPlate    || aPrefix == OP.crateGt64Plate    || aPrefix == OP.blockPlate   ) {
			if (aMaterial == MT.WoodTreated || aMaterial.contains(TD.Properties.WOOD))
				return aPrefix.mMaterialPre + "планок (" + aMaterial.mNameLocal + ")";
			if (ANY.Plastic.mToThis.contains(aMaterial) || ANY.Rubber.mToThis.contains(aMaterial))
				return aPrefix.mMaterialPre + "листов (" + aMaterial.mNameLocal + ")";
			return aPrefix.mMaterialPre + "пластин (" + aMaterial.mNameLocal + ")";
		}

		if (aPrefix == OP.crateGtPlateGem || aPrefix == OP.crateGt64PlateGem || aPrefix == OP.blockPlateGem) return aPrefix.mMaterialPre + "пластин из сам-ов (" + aMaterial.mNameLocal + ")";

		if (aPrefix == OP.crateGtRaw      || aPrefix == OP.crateGt64Raw      || aPrefix == OP.blockRaw     ) {
			if (aMaterial.mID > 0 && aMaterial.mID <= 830 && aMaterial.mID % 10 == 0 && aMaterial.mMeltingPoint > C && aMaterial.mTargetCrushing.mMaterial == aMaterial && aMaterial.contains(TD.Processing.SMITHABLE)) {
				if (aPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED))
					return aPrefix.mMaterialPre + "руды (" + aMaterial.mNameLocal + ", Природная)";
			}
			return aPrefix.mMaterialPre + "руды (" + aMaterial.mNameLocal + ")";
		}
		
		if (APRIL_FOOLS) {
			if (aMaterial == MT.Empty) {
			if (aPrefix == OP.bulletGtSmall)                                        return aPrefix.mMaterialPre + "Bolt Shaft";
			if (aPrefix == OP.bulletGtMedium)                                       return aPrefix.mMaterialPre + "Bolt Shaft";
			if (aPrefix == OP.bulletGtLarge)                                        return aPrefix.mMaterialPre + "Bolt Shaft";
			} else {
			if (aPrefix == OP.bulletGtSmall)                                        return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Bolt";
			if (aPrefix == OP.bulletGtMedium)                                       return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Bolt";
			if (aPrefix == OP.bulletGtLarge)                                        return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Bolt";
			}
		}
		
		if (aMaterial == MT.Empty) {
			if (aPrefix == OP.chemtube)                                             return "Колба (Пусто)";
			if (aPrefix == OP.arrowGtWood)                                          return "Стрела без наконечника";
			if (aPrefix == OP.arrowGtPlastic)                                       return "Легкая стрела без наконечника";
			if (aPrefix == OP.bulletGtSmall)                                        return "Малая  гильза для пули";
			if (aPrefix == OP.bulletGtMedium)                                       return "Средняя гильза для пули";
			if (aPrefix == OP.bulletGtLarge)                                        return "Большая гильза для пули";
			if (aPrefix.contains(TD.Prefix.ORE))                                    return "Неизвестная руда";
			if (aPrefix.contains(TD.Prefix.ORE_PROCESSING_BASED))                   return "Обработанная неизвестная руда";
		} else
		if (aMaterial == MT.Stone) {
			if (aPrefix == OP.rockGt)                                               return "Камешек";
		} else
		if (aMaterial == MT.AncientDebris) {
			if (aPrefix == OP.rockGt)                                               return aMaterial.mNameLocal;
			if (aPrefix == OP.oreRaw)                                               return "Большой кусок древних обломков";
			//if (aPrefix == OP.crushed)                                              return "Дробленые древние обломки";
			//if (aPrefix == OP.crushedTiny)                                          return "Tiny Recycled " + aMaterial.mNameLocal;
			if (aPrefix == OP.nugget)                                               return "Крошечный кусок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chunkGt)                                              return "Малый кусок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.billet)                                               return "Заготовка (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.scrap)                                                return "Scrap Piece of Netherite Scrap";
			if (aPrefix.mNameInternal.startsWith("ore"))                            return aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("ingot"))                          return "Слиток (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Sand || aMaterial == MT.RedSand || aMaterial == MT.EndSandWhite || aMaterial == MT.EndSandBlack) {
			if (aPrefix == OP.crushed)                                              return "Ground " + aMaterial.mNameLocal;
			if (aPrefix == OP.crushedTiny)                                          return "Tiny Ground " + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("ore"))                            return aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("dust"))                           return aPrefix.mMaterialPre + aMaterial.mNameLocal + ")";
		}
		if (aMaterial == MT.SoulSand) {
			if (aPrefix == OP.crushed)                                              return "Молотый песок душ";
			if (aPrefix == OP.crushedTiny)                                          return "Крошечный молотый песок душ";
			if (aPrefix.mNameInternal.startsWith("ore"))                            return aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("dust"))                           return aPrefix.mMaterialPre + aMaterial.mNameLocal + ")";
		}
		else
		if (aMaterial == MT.Netherrack) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Endstone) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.MeteoricIron || aMaterial == MT.Meteorite) {
			if (aPrefix == OP.rockGt)                                               return "Метеорит";
			if (aPrefix == OP.oreRaw)                                               return "Большой метеорит";
		} else
		if (aMaterial == MT.STONES.SpaceRock) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.STONES.MoonRock) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.STONES.MarsRock) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.STONES.MoonTurf) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Торф (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Moon Turf";
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.STONES.MarsSand) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Торф (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Mars Turf";
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.STONES.Holystone) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.STONES.Umber) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.STONES.Betweenstone) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.STONES.Pitstone) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.STONES.Gneiss) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Glass) {
			if (aPrefix == OP.scrapGt)                                              return "Осколки (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.round)                                                return "Шарик (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateGem)                                             return "стеклянных панелей";
			if (aPrefix == OP.plateGemTiny)                                         return "Малая стеклянная панель";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Кристалл стекла";
			if (aPrefix.mNameInternal.startsWith("plate"))                          return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Pane";
		} else
		if (aMaterial == MT.PrismarineLight) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.scrapGt)                                              return "Осколки (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.round)                                                return "Шарик (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Кристалл светлого призмарина";
		} else
		if (aMaterial == MT.PrismarineDark) {
			if (aPrefix == OP.rockGt)                                               return "Камешек (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.scrapGt)                                              return "Осколки (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.round)                                                return "Шарик (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Кристалл темного призмарина";
		} else
		if (aMaterial == MT.Frezarite) {
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Кристалл фрезарита";
		} else
		if (aMaterial == MT.Fluix) {
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Изменчивый кристалл";
		} else
		if (aMaterial == MT.Palis) {
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Кристалл палиса";
		} else
		if (aMaterial == MT.Diamantine) {
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Кристалл диамантина";
		} else
		if (aMaterial == MT.VoidCrystal) {
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Пустотный кристалл";
		} else
		if (aMaterial == MT.Emeradic) {
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Кристалл изумрудника";
		} else
		if (aMaterial == MT.Enori) {
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Кристалл энори";
		} else
		if (aMaterial == MT.Redstonia) {
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Кристалл редстония";
		} else
		if (aMaterial == MT.InfusedDull) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return "Тусклые кристаллы";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return "Осколок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crystal"))                        return aPrefix.mMaterialPre + "Dull Shard";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Тусклые осколки)";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chemtube)                                             return aPrefix.mMaterialPre + "Тусклый кристаллический порошок";
			return aPrefix.mMaterialPre + "Тусклый" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.InfusedBalance) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return "Балансирующий камень";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return "Осколок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crystal"))                        return aPrefix.mMaterialPre + "Balance Shard";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Балансирующие осколки)";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chemtube)                                             return aPrefix.mMaterialPre + "Балансирующий порошок";
			return aPrefix.mMaterialPre + "Наполненный балансом" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.InfusedVis) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return "Магический камень";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return "Осколок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crystal"))                        return aPrefix.mMaterialPre + "Magic Shard";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Магические осколки)";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chemtube)                                             return aPrefix.mMaterialPre + "Магический порошок";
			return aPrefix.mMaterialPre + "Наполненный магией" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.InfusedAir) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return "Воздушный камень";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return "Осколок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crystal"))                        return aPrefix.mMaterialPre + "Air Shard";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Воздушные осколки)";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chemtube)                                             return aPrefix.mMaterialPre + "Воздушный порошок";
			return aPrefix.mMaterialPre + "Наполненный воздухом" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.InfusedWater) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return "Водяной камень";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return "Осколок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crystal"))                        return aPrefix.mMaterialPre + "Water Shard";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Водяные осколки)";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chemtube)                                             return aPrefix.mMaterialPre + "Водяной порошок";
			return aPrefix.mMaterialPre + "Наполненный водой" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.InfusedEarth) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return "Земляной камень";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return "Осколок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crystal"))                        return aPrefix.mMaterialPre + "Earth Shard";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Земляные осколки)";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chemtube)                                             return aPrefix.mMaterialPre + "Земляной попрошок";
			return aPrefix.mMaterialPre + "Наполненный землей" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.InfusedFire) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return "Пропитанный огнем камень";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return "Осколок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crystal"))                        return aPrefix.mMaterialPre + "Fire Shard";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Пропитанные огнем осколки)";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chemtube)                                             return aPrefix.mMaterialPre + "Пропитанный огнем порошок";
			return aPrefix.mMaterialPre + "Наполненный огнем" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.InfusedOrder) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return "Порядковый камень";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return "Осколок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crystal"))                        return aPrefix.mMaterialPre + "Order Shard";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Порядковые осколки)";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chemtube)                                             return aPrefix.mMaterialPre + "Порядковый попрошок";
			return aPrefix.mMaterialPre + "Наполненный порядком" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.InfusedEntropy) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return "Энтропический камень";
			if (aPrefix.mNameInternal.startsWith("gem"))                            return "Осколок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crystal"))                        return aPrefix.mMaterialPre + "Entropy Shard";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + "Энтропические осколки)";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chemtube)                                             return aPrefix.mMaterialPre + "Энтропический попрошок";
			return aPrefix.mMaterialPre + "Наполненный энтропией" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.Craponite) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return aPrefix.mMaterialPre + "Крапонит)";
		} else
		if (aMaterial == MT.Wheat) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мука (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Oat) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мука (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.OatAbyssal) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мука (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Rye) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мука (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Barley) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мука (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Corn) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мука (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Rice) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Рис";
		} else
		if (aMaterial == MT.Ice) {
			if (aPrefix == OP.gemChipped)                                           return "Ледяной куб";
			if (aPrefix == OP.gemFlawed)                                            return "Средний ледяной куб";
			if (aPrefix == OP.gem)                                                  return "Большой ледяной куб";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return aPrefix.mMaterialPre + "Дробленый лед)";
		} else
		if (aMaterial == MT.WoodTreated) {
			if (aPrefix == OP.rockGt)                                               return aMaterial.mNameLocal;
			if (aPrefix == OP.scrapGt)                                              return "Щепки (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("bolt"))                           return "Короткая палочка (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.stick)                                                return "Палка (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.stickLong)                                            return "Длинная палка (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Целлюлоза (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.nugget)                                               return "Щепка (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plate)                                                return "Планка (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateTiny)                                            return "Малая планка (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateCurved)                                          return "Гнутая планка (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.FierySteel) {
			if (aPrefix.contains(TD.Prefix.IS_CONTAINER))                           return aPrefix.mMaterialPre + "Fiery Blood" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.Steeleaf || aMaterial == MT.Fireleaf) {
			if (aPrefix == OP.plantGtBlossom)                                       return "Лист (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingot)                                                return aMaterial.mNameLocal;
			if (aPrefix == OP.ingotDouble)                                          return aMaterial.mNameLocal + " х2";
			if (aPrefix == OP.ingotTriple)                                          return aMaterial.mNameLocal + " х3";
			if (aPrefix == OP.ingotQuadruple)                                       return aMaterial.mNameLocal + " х4";
			if (aPrefix == OP.ingotQuintuple)                                       return aMaterial.mNameLocal + " х5";
		} else
		if (aMaterial == MT.Bark) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return aPrefix.mMaterialPre + "Кора)";
		} else
		if (aMaterial == MT.Tea || aMaterial == MT.Mint) {
			if (aPrefix == OP.plantGtBlossom)                                       return "Лист (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Bone) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return aPrefix.mMaterialPre + "Окаменелость)";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Костная мука";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return "Дробленая кость";
			if (aPrefix == OP.crushedTiny)                                          return "Крошечная дробленая кость";
		} else
		if (aMaterial == MT.Flint) {
			if (aPrefix == OP.rockGt)                                               return "Рудный камень (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Pyrite) {
			if (aPrefix.contains(TD.Prefix.ORE))                                    return aPrefix.mMaterialPre + MT.Au.mNameLocal + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.MgCO3) {
			if (aPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED)) return aPrefix.mMaterialPre + aMaterial.mNameLocal + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.Asbestos) {
			if (aPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED)) return aPrefix.mMaterialPre + aMaterial.mNameLocal + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.Talc) {
			if (aPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED)) return aPrefix.mMaterialPre + aMaterial.mNameLocal + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.AlO3H3) {
			if (aPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED)) return aPrefix.mMaterialPre + aMaterial.mNameLocal + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.Au) {
			if (aPrefix == OP.plantGtBlossom)                                       return "Лист (Золто)";
		} else
		if (aMaterial == MT.Fe) {
			if (aPrefix == OP.plantGtBlossom)                                       return "Лист (Железо)";
		} else
		if (aMaterial == MT.Pb) {
			if (aPrefix == OP.plantGtBlossom)                                       return "Лист (Свинец)";
		} else
		if (aMaterial == MT.Ag) {
			if (aPrefix == OP.plantGtBlossom)                                       return "Лист (Серебро)";
		} else
		if (aMaterial == MT.Sn) {
			if (aPrefix == OP.plantGtTwig)                                          return "Веточка (Олово)";
		} else
		if (aMaterial == MT.Cu) {
			if (aPrefix == OP.plantGtFiber)                                         return "Волокно (Медь)";
		} else
		if (aMaterial == MT.Emerald) {
			if (aPrefix == OP.plantGtBerry)                                         return "Ягода (Bobs-Yer-Uncle-Berry)";
		} else
		if (aMaterial == MT.Milk) {
			if (aPrefix == OP.plantGtWart)                                          return "Молочный нарост";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Chocolate || aMaterial == MT.Cheese) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("ingot"))                          return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Bar";
		} else
		if (aMaterial == MT.Butter || aMaterial == MT.ButterSalted) {
			if (aPrefix.mNameInternal.startsWith("ingot"))                          return aPrefix.mMaterialPre + aMaterial.mNameLocal;
		} else
		if (aMaterial == MT.Indigo || aMaterial == MT.ConstructionFoam || aMaterial == MT.Cocoa || aMaterial == MT.Curry || aMaterial == MT.Chocolate || aMaterial == MT.Coffee || aMaterial == MT.Chili || aMaterial == MT.Cheese || aMaterial == MT.Snow) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
		} else
		if (aPrefix.mNameInternal.startsWith("dust")) {
			if (aMaterial == MT.Potato) return aPrefix.mMaterialPre + "Молотый картофель)";
			if (aMaterial == MT.Hazelnut) return aPrefix.mMaterialPre + "Молотый фундук)";
			if (aMaterial == MT.Pistachio) return aPrefix.mMaterialPre + "Молотая фисташка)";
			if (aMaterial == MT.Almond) return aPrefix.mMaterialPre + "Молотый миндаль)";
			if (aMaterial == MT.Peanut) return aPrefix.mMaterialPre + "Молотый арахис)";
			if (aMaterial == MT.Nutmeg) return aPrefix.mMaterialPre + "Молотый мускатный орех)";
			if (aMaterial == MT.Cinnamon) return aPrefix.mMaterialPre + "Молотая корица)";
			if (aMaterial == MT.Vanilla) return aPrefix.mMaterialPre + "Молотая ваниль)";
			if (aMaterial == MT.PepperBlack) return aPrefix.mMaterialPre + "Молтый черный перец)";
		} else
		if (aMaterial == MT.Paper) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return aPrefix.mMaterialPre + "Chad";
			if (aPrefix == OP.scrapGt)                                              return "Измельченная бумага";
			if (aPrefix == OP.plateTiny)                                            return "Малый лист бумаги";
			if (aPrefix == OP.plate)                                                return "Лист бумаги";
			if (aPrefix == OP.plateDouble)                                          return "Плотный лист бумаги";
			if (aPrefix == OP.plateTriple)                                          return "Картон";
			if (aPrefix == OP.plateQuadruple)                                       return "Плотный картон";
			if (aPrefix == OP.plateQuintuple)                                       return "Толстый картон";
			if (aPrefix == OP.plateDense)                                           return "Прочный картон";
		} else
		if (aMaterial == MT.FishRaw) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мука (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.FishCooked) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мука (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.FishRotten) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мука (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.MeatRaw) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мясной фарш (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.MeatCooked) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мясной фарш (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.MeatRotten) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Мясной фарш (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.SoylentGreen || aMaterial == MT.Tofu) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return aPrefix.mMaterialPre + "Silken " + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("ingot"))                          return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Bar";
		} else
		if (aMaterial == MT.Peat || aMaterial == MT.PeatBituminous) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return aPrefix.mMaterialPre + aMaterial.mNameLocal;
			if (aPrefix == OP.ingot)                                                return "Брикет (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotDouble)                                          return "Брикет х2 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotTriple)                                          return "Брикет х3 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotQuadruple)                                       return "Брикет х4 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotQuintuple)                                       return "Брикет х5 (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Lignite || aMaterial == MT.LigniteCoke || aMaterial == MT.Charcoal || aMaterial == MT.Coal || aMaterial == MT.CoalCoke || aMaterial == MT.Anthracite || aMaterial == MT.Prismane || aMaterial == MT.Lonsdaleite || aMaterial == MT.PetCoke || aMaterial == MT.HydratedCoal) {
			if (aPrefix == OP.ingot)                                                return "Брикет (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotDouble)                                          return "Брикет х2 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotTriple)                                          return "Брикет х3 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotQuadruple)                                       return "Брикет х4 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotQuintuple)                                       return "Брикет х5 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.chunkGt)                                              return aPrefix.mMaterialPre + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.nugget)                                               return aPrefix.mMaterialPre + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Sugar) {
			if (aPrefix == OP.gemChipped)                                           return "Кубики сахара";
		} else
		if (aMaterial == MT.Ceramic) {
			if (aPrefix == OP.scrapGt)                                              return "Хрупкие керамические отходы";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
		} else
		if (ANY.Blaze.mToThis.contains(aMaterial)) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("stick"))                          return aPrefix.mMaterialPre + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("ingot"))                          return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Bar";
		} else
		if (ANY.Clay.mToThis.contains(aMaterial)) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Порошок (" + aMaterial.mNameLocal + ")";
		} else
		if (ANY.Plastic.mToThis.contains(aMaterial) || ANY.Rubber.mToThis.contains(aMaterial)) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Целлюлоза (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plate)                                                return "Лист (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateDouble)                                          return "Лист х2 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateTriple)                                          return "Лист х3 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateQuadruple)                                       return "Лист х4 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateQuintuple)                                       return "Лист х5 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateDense)                                           return "Плотный лист (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateTiny)                                            return "Малый лист (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateCurved)                                          return "Гнутый лист (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingot)                                                return "Брусок (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotDouble)                                          return "Брусок х2 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotTriple)                                          return "Брусок х3 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotQuadruple)                                       return "Брусок х4 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.ingotQuintuple)                                       return "Брусок х5 (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.nugget)                                               return "Щепка (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("foil"))                           return "Тонкий лист (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Dilithium) {
			if (aPrefix.mNameInternal.startsWith("gem"))                            return aPrefix.mMaterialPre + "Кристалл дилития";
		} else
		if (aMaterial == MT.Ectoplasm || aMaterial == MT.Tallow || aMaterial == MT.Gunpowder || aMaterial == MT.NaCl || aMaterial == MT.KCl || aMaterial == MT.KIO3 || aMaterial == MT.Asphalt) {
			if (aPrefix.mNameInternal.startsWith("dust"))                           return aPrefix.mMaterialPre + aMaterial.mNameLocal;
		} else
		if (aMaterial == MT.Black || aMaterial == MT.Red || aMaterial == MT.Green || aMaterial == MT.Brown || aMaterial == MT.Blue || aMaterial == MT.Purple || aMaterial == MT.Cyan || aMaterial == MT.LightGray || aMaterial == MT.Gray || aMaterial == MT.Pink || aMaterial == MT.Lime || aMaterial == MT.Yellow || aMaterial == MT.LightBlue || aMaterial == MT.Magenta || aMaterial == MT.Orange || aMaterial == MT.White) {
			if (aPrefix == OP.plantGtFiber)                                         return "Нить (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Краситель (" + aMaterial.mNameLocal + ")";
		} else
		if (aMaterial == MT.Wax || aMaterial == MT.WaxMagic || aMaterial == MT.WaxAmnesic || aMaterial == MT.WaxSoulful || aMaterial == MT.WaxBee || aMaterial == MT.WaxRefractory || aMaterial == MT.WaxPlant || aMaterial == MT.WaxParaffin || aMaterial == MT.Ash || aMaterial == MT.DarkAsh || aMaterial == MT.VolcanicAsh || aMaterial == MT.ArcaneAsh || aMaterial == MT.ArcaneCompound || aMaterial == MT.OREMATS.Vermiculite || aMaterial == MT.Talc || aMaterial == MT.OREMATS.Magnetite || aMaterial == MT.OREMATS.BasalticMineralSand || aMaterial == MT.OREMATS.GraniticMineralSand || aMaterial == MT.OREMATS.GarnetSand || aMaterial == MT.SluiceSand || aMaterial == MT.OREMATS.QuartzSand || aMaterial == MT.OREMATS.Pitchblende || aMaterial == MT.Bentonite || aMaterial == MT.Palygorskite || aMaterial == MT.RareEarth || aMaterial == MT.Oilsands) {
			if (aPrefix.mNameInternal.startsWith("ore"))                            return aPrefix.mMaterialPre + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("dust"))                           return aPrefix.mMaterialPre + aMaterial.mNameLocal;
			if (aPrefix == OP.crushed)                                              return "Дробленая руда (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.crushedTiny)                                          return "Крошечная дробленая руда (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("crushed"))                        return aPrefix.mMaterialPre + aMaterial.mNameLocal + ")";
		}
		
		if (aMaterial.contains(TD.Properties.WOOD)) {
			if (aPrefix == OP.rockGt)                                               return aMaterial.mNameLocal;
			if (aPrefix == OP.scrapGt)                                              return "Щепки (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("bolt"))                           return "Короткая палочка (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.stick)                                                return "Палка (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.stickLong)                                            return "Длинная палка (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("dust"))                           return "Целлюлоза (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.nugget)                                               return "Щепка (" + aMaterial.mNameLocal + ")";
			if (aPrefix.mNameInternal.startsWith("plate"))                          return "Планка (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateTiny)                                            return "Малая планка (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.plateCurved)                                          return "Гнутая планка (" + aMaterial.mNameLocal + ")";
		}
		if (aMaterial.contains(TD.Properties.STONE)) {
			if (aPrefix == OP.rockGt)                                               return aMaterial.mNameLocal.endsWith("rock") ? (aMaterial.mNameLocal+" §§§").replaceFirst("rock §§§", " Rock") : "Камешек (" + aMaterial.mNameLocal + ")";
			if (aPrefix == OP.scrapGt)                                              return "Галька (" + aMaterial.mNameLocal + ")";
		}
		if (aMaterial.mID > 0 && aMaterial.mID <= 830 && aMaterial.mID % 10 == 0 && aMaterial.mMeltingPoint > C && aMaterial.mTargetCrushing.mMaterial == aMaterial && aMaterial.contains(TD.Processing.SMITHABLE)) {
			if (aPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED)) return aPrefix.mMaterialPre + aMaterial.mNameLocal + ", Природная" + aPrefix.mMaterialPost;
		}
		
		
		
		// Use Standard Localisation
		return aPrefix.mMaterialPre + aMaterial.mNameLocal + aPrefix.mMaterialPost;
	}
}
