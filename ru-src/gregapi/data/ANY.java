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

package gregapi.data;

import gregapi.oredict.OreDictMaterial;

import static gregapi.data.CS.*;
import static gregapi.data.TD.Processing.*;
import static gregapi.data.TD.Properties.*;

/**
 * @author Gregorius Techneticies
 */
public class ANY {
	private static OreDictMaterial any(String aNameOreDict) {return OreDictMaterial.createMaterial(-1, aNameOreDict, aNameOreDict).put(UNUSED_MATERIAL, INVALID_MATERIAL, IGNORE_IN_COLOR_LOG);}
	private static boolean INITIALIZED = F;
	
	/** Technical Materials, which are only there for Recipes and such. */
	public static final OreDictMaterial
	Glowstone       = any("Any Glowstone"       ),
	Diamond         = any("Any Diamond"         ),
	Sapphire        = any("Any Sapphire"        ),
	Emerald         = any("Any Emerald"         ),
	Amethyst        = any("Any Аметист"         ),
	Garnet          = any("Any Garnet"          ),
	Jasper          = any("Any Jasper"          ),
	TigerEye        = any("Any Tiger Eye"       ),
	Aventurine      = any("Any Aventurine"      ),
	Fluorite        = any("Any Fluorite"        ), CaF2 = Fluorite,
	Phosphorus      = any("Any Phosphorus"      ),
	Blaze           = any("Any Blaze"           ),
	Prismarine      = any("Any Prismarine"      ),
	Grains          = any("Any Grains"          ),
	Flour           = any("Any Flour"           ),
	FlourGrains     = any("Any Flour Or Grains" ),
	Wax             = any("Any Wax"             ),
	Stone           = any("Any Stone"           ),
	Calcite         = any("Any Calcite"         ),
	Clay            = any("Any Clay"            ),
	Salt            = any("Any Salt"            ),
	Fe              = any("Any Iron"            ),
	Iron            = any("Any Iron Or Steel"   ),
	Steel           = any("Any Iron-Steel"      ),
	BlackSteel      = any("Any Black Steel"     ),
	BlueSteel       = any("Any Blue Steel"      ),
	RedSteel        = any("Any Red Steel"       ),
	MagicIron       = any("Any Magic Iron"      ),
	Cu              = any("Any Copper"          ),
	Ash             = any("Any Ashes"           ),
	C               = any("Any Carbon"          ),
	Coal            = any("Any Coal/Carbon"     ),
	Si              = any("Any Silicon"         ),
	SiO2            = any("Any Silicon Dioxide" ),
	Quartz          = any("Quartz"              ),
	Sand            = any("Any Sand"            ),
	W               = any("Any Tungsten"        ),
	ThaumCrystal    = any("Any Thaumic Crystal" ),
	Hexorium        = any("Hexorium"            ),
	Wood            = any("Any Wood"            ),
	WoodDefault     = any("Any Default Wood"    ),
	WoodNormal      = any("Any Normal Wood"     ),
	WoodMagical     = any("Any Magical Wood"    ),
	WoodTreated     = any("Any Treated Wood"    ),
	WoodUntreated   = any("Any Untreated Wood"  ),
	WoodPlastic     = any("Any Wood Or Plastic" ),
	Rubber          = any("Any Rubber"          ),
	Plastic         = any("Any Plastic"         ),
	PlasticHard     = any("Any Hard Plastic"    ),
	
	_Steel          = any("Any Steel"           ),
	_Bronze         = any("Any Bronze"          ),
	_Metal          = any("Any Metal"           );
	
	protected static void init() {
		if (INITIALIZED) return;
		INITIALIZED = T;
		
		Glowstone       .stealLooks(MT.Glowstone      ).steal(MT.Glowstone      ).setLocal("Светопыль"          ).setAllToTheOutputOf(MT.Glowstone      ).put(CRYSTAL, GLOWING, LIGHTING                             );
		Diamond         .stealLooks(MT.Diamond        ).steal(MT.Diamond        ).setLocal("Алмаз"              ).setAllToTheOutputOf(MT.Diamond        ).put(CRYSTAL, VALUABLE                                      ).addReRegistrationToThis(MT.Diamantine);
		Sapphire        .stealLooks(MT.BlueSapphire   ).steal(MT.Sapphire       ).setLocal("Сапфир"             ).setAllToTheOutputOf(MT.Sapphire       ).put(CRYSTAL, VALUABLE                                      );
		Emerald         .stealLooks(MT.Emerald        ).steal(MT.Emerald        ).setLocal("Изумруд"            ).setAllToTheOutputOf(MT.Emerald        ).put(CRYSTAL, VALUABLE                                      ).addReRegistrationToThis(MT.Emeradic);
		Amethyst        .stealLooks(MT.Amethyst       ).steal(MT.Amethyst       ).setLocal("Аметист"            ).setAllToTheOutputOf(MT.Amethyst       ).put(CRYSTAL, VALUABLE                                      ).addReRegistrationToThis(MT.Amethyst, MT.EnderAmethyst);
		Garnet          .stealLooks(MT.Spessartine    ).steal(MT.Spessartine    ).setLocal("Гранат"                                                     ).put(CRYSTAL, VALUABLE                                      );
		Jasper          .stealLooks(MT.Jasper         ).steal(MT.Jasper         ).setLocal("Яшма"                                                       ).put(CRYSTAL, VALUABLE                                      );
		TigerEye        .stealLooks(MT.TigerEyeYellow ).steal(MT.TigerEyeYellow ).setLocal("Тигриный глаз"                                              ).put(CRYSTAL, VALUABLE                                      );
		Aventurine      .stealLooks(MT.AventurineGreen).steal(MT.AventurineGreen).setLocal("Авантюрин"                                                  ).put(CRYSTAL, VALUABLE                                      );
		Fluorite        .stealLooks(MT.CaF2           ).steal(MT.CaF2           ).setLocal("Флюорит"            ).setAllToTheOutputOf(MT.CaF2           ).put(CRYSTAL, MORTAR, MELTING, BRITTLE, ACID                );
		Phosphorus      .stealLooks(MT.Phosphorus     ).steal(MT.Phosphorus     ).setLocal("Фосфор"             ).setAllToTheOutputOf(MT.Phosphorus     ).put(CRYSTAL, MORTAR, MELTING, BRITTLE, FLAMMABLE, EXPLOSIVE).addReRegistrationToThis(MT.Phosphorus, MT.PhosphorusBlue, MT.PhosphorusRed, MT.PhosphorusWhite);
		Blaze           .stealLooks(MT.Blaze          ).steal(MT.Blaze          ).setLocal("Огненный порошок"                                           ).put(GLOWING, MAGICAL, BRITTLE, MORTAR                      );
		Prismarine      .stealLooks(MT.PrismarineLight).steal(MT.PrismarineLight).setLocal("Светлый призмарин"  ).setAllToTheOutputOf(MT.PrismarineLight).put(CRYSTAL                                                ).addReRegistrationToThis(MT.PrismarineLight, MT.PrismarineDark);
		Grains          .stealLooks(MT.Wheat          ).steal(MT.Wheat          ).setLocal("Зерна"              ).setAllToTheOutputOf(MT.Wheat          ).put(FOOD, MORTAR, FLAMMABLE                                );
		Flour           .stealLooks(MT.Wheat          ).steal(MT.Wheat          ).setLocal("Мука"               ).setAllToTheOutputOf(MT.Wheat          ).put(FOOD, MORTAR, FLAMMABLE                                ).addReRegistrationToThis(MT.Wheat, MT.Rye, MT.Oat, MT.OatAbyssal, MT.Barley, MT.Potato, MT.Corn);
		FlourGrains     .stealLooks(MT.Wheat          ).steal(MT.Wheat          ).setLocal("Мука или зерна"     ).setAllToTheOutputOf(MT.Wheat          ).put(FOOD, MORTAR, FLAMMABLE                                ).addReRegistrationToThis(MT.Potato);
		Wax             .stealLooks(MT.Wax            ).steal(MT.Wax            ).setLocal("Воск"               ).setAllToTheOutputOf(MT.Wax            ).put(                                                       );
		Stone           .stealLooks(MT.Stone          ).steal(MT.Stone          ).setLocal("Камень"             ).setAllToTheOutputOf(MT.Stone          ).put(STONE, BRITTLE, UNRECYCLABLE                           );
		Calcite         .stealLooks(MT.CaCO3          ).steal(MT.CaCO3          ).setLocal("Кальцит"            ).setAllToTheOutputOf(MT.CaCO3          ).put(STONE, BRITTLE, UNRECYCLABLE                           ).addReRegistrationToThis(MT.CaCO3, MT.STONES.Marble, MT.Chalk, MT.STONES.Limestone, MT.Dolomite);
		Clay            .stealLooks(MT.ClayBrown      ).steal(MT.Clay           ).setLocal("Глина"              ).setAllToTheOutputOf(MT.Clay           ).put(MORTAR                                                 ).addReRegistrationToThis(MT.Clay);
		Salt            .stealLooks(MT.NaCl           ).steal(MT.NaCl           ).setLocal("Соль"               ).setAllToTheOutputOf(MT.NaCl           ).put(BRITTLE                                                ).addReRegistrationToThis(MT.NaCl, MT.KCl, MT.LiCl, MT.MgCl2, MT.CaCl2);
		Fe              .stealLooks(MT.Fe             ).steal(MT.Fe             ).setLocal("Железо"             ).setAllToTheOutputOf(MT.Fe             ).put(SMITHABLE, MELTING                                     ).addReRegistrationToThis(MT.Fe, MT.WroughtIron, MT.IronCast, MT.IronCompressed, MT.PigIron, MT.MeteoricIron, MT.Meteorite, MT.Enori);
		Iron            .stealLooks(MT.Fe             ).steal(MT.Fe             ).setLocal("Железо"             ).setAllToTheOutputOf(MT.Fe             ).put(SMITHABLE, MELTING                                     ).addReRegistrationToThis(MT.Fe, MT.WroughtIron, MT.IronCast, MT.IronCompressed, MT.PigIron, MT.MeteoricIron, MT.Meteorite, MT.Enori, MT.Steel, MT.Knightmetal, MT.MeteoricSteel);
		Steel           .stealLooks(MT.Steel          ).steal(MT.Steel          ).setLocal("Сталь"              ).setAllToTheOutputOf(MT.Steel          ).put(SMITHABLE, MELTING                                     ).addReRegistrationToThis(MT.Steel, MT.Knightmetal, MT.MeteoricSteel);
		BlackSteel      .stealLooks(MT.BlackSteel     ).steal(MT.BlackSteel     ).setLocal("Черная сталь"       ).setAllToTheOutputOf(MT.BlackSteel     ).put(SMITHABLE, MELTING                                     ).addReRegistrationToThis(MT.BlackSteel, MT.MeteoricBlackSteel, MT.MeteoflameBlackSteel);
		BlueSteel       .stealLooks(MT.BlueSteel      ).steal(MT.BlueSteel      ).setLocal("Синяя сталь"        ).setAllToTheOutputOf(MT.BlueSteel      ).put(SMITHABLE, MELTING                                     ).addReRegistrationToThis(MT.BlueSteel, MT.MeteoricBlueSteel, MT.MeteoflameBlueSteel);
		RedSteel        .stealLooks(MT.RedSteel       ).steal(MT.RedSteel       ).setLocal("Красная сталь"      ).setAllToTheOutputOf(MT.RedSteel       ).put(SMITHABLE, MELTING                                     ).addReRegistrationToThis(MT.RedSteel, MT.MeteoricRedSteel, MT.MeteoflameRedSteel);
		MagicIron       .stealLooks(MT.Manasteel      ).steal(MT.Manasteel      ).setLocal("Магическая сталь"   ).setAllToTheOutputOf(MT.Fe             ).put(SMITHABLE, MELTING, MAGICAL                            ).addReRegistrationToThis(MT.Manasteel, MT.Thaumium, MT.DarkThaumium, MT.SpectreIron, MT.FierySteel);
		Cu              .stealLooks(MT.Cu             ).steal(MT.Cu             ).setLocal("Медь"               ).setAllToTheOutputOf(MT.Cu             ).put(SMITHABLE, MELTING                                     ).addReRegistrationToThis(MT.Cu, MT.AnnealedCopper);
		Ash             .stealLooks(MT.Ash            ).steal(MT.Ash            ).setLocal("Зола"               ).setAllToTheOutputOf(MT.Ash            ).put(BRITTLE                                                ).addReRegistrationToThis(MT.Ash, MT.DarkAsh, MT.VolcanicAsh);
		C               .stealLooks(MT.C              ).steal(MT.C              ).setLocal("Углерод"            ).setAllToTheOutputOf(MT.C              ).put(                                                       ).addReRegistrationToThis(MT.C, MT.Graphite, MT.Graphene);
		Coal            .stealLooks(MT.C              ).steal(MT.C              ).setLocal("Углерод"            ).setAllToTheOutputOf(MT.C              ).put(                                                       ).addReRegistrationToThis(MT.C, MT.Graphite, MT.Graphene, MT.CoalCoke, MT.Coal, MT.Charcoal);
		Si              .stealLooks(MT.Si             ).steal(MT.Si             ).setLocal("Кремний"            ).setAllToTheOutputOf(MT.Si             ).put(SMITHABLE, MELTING                                     ).addReRegistrationToThis(MT.Si);
		SiO2            .stealLooks(MT.SiO2           ).steal(MT.SiO2           ).setLocal("Диоксид кремния"    ).setAllToTheOutputOf(MT.SiO2           ).put(BRITTLE, MELTING                                       ).addReRegistrationToThis(MT.STONES.Quartzite, MT.SiO2, MT.Sand, MT.RedSand, MT.EndSandWhite, MT.EndSandBlack, MT.Glass, MT.Flint);
		Quartz          .stealLooks(MT.MilkyQuartz    ).steal(MT.MilkyQuartz    ).setLocal("Кварц"              ).setAllToTheOutputOf(MT.SiO2           ).put(BRITTLE, MELTING, QUARTZ                               ).addReRegistrationToThis(MT.STONES.Quartzite);
		Sand            .stealLooks(MT.Sand           ).steal(MT.Sand           ).setLocal("Песок"              ).setAllToTheOutputOf(MT.Sand           ).put(BRITTLE, MELTING                                       ).addReRegistrationToThis(MT.Sand, MT.RedSand);
		W               .stealLooks(MT.W              ).steal(MT.W              ).setLocal("Вольфрам"           ).setAllToTheOutputOf(MT.W              ).put(SMITHABLE, MELTING, UNBURNABLE                         ).addReRegistrationToThis(MT.W, MT.TungstenSintered);
		ThaumCrystal    .stealLooks(MT.InfusedBalance ).steal(MT.InfusedDull                                                                            ).put(DONT_SHOW_THIS_COMPONENT                               );
		Hexorium        .stealLooks(MT.HexoriumWhite  ).steal(MT.HexoriumWhite                                                                          ).put(DONT_SHOW_THIS_COMPONENT                               );
		WoodDefault     .stealLooks(MT.WOODS.Spruce   ).steal(MT.Wood           ).setLocal("Обычное дерево"     ).setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE                                        ).addReRegistrationToThis(MT.Wood, MT.Peanutwood).setFurnaceBurnTime(TICKS_PER_SMELT/2);
		WoodNormal      .stealLooks(MT.WOODS.Spruce   ).steal(MT.Wood           ).setLocal("Обычное дерево"     ).setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE                                        ).addReRegistrationToThis(ANY.WoodDefault.mToThis.toArray(ZL_MT)).addReRegistrationToThis(MT.WoodRubber, MT.Weedwood, MT.Skyroot, MT.Bamboo).setFurnaceBurnTime(TICKS_PER_SMELT/2);
		WoodMagical     .stealLooks(MT.Greatwood      ).steal(MT.Wood           ).setLocal("Магическое дерево"  ).setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE, MAGICAL                               ).addReRegistrationToThis(MT.Greatwood, MT.Silverwood, MT.Livingwood, MT.Dreamwood, MT.Shimmerwood, MT.WOODS.Magic, MT.WOODS.Tainted, MT.WOODS.Rainbowood).setFurnaceBurnTime(TICKS_PER_SMELT*2L);
		WoodTreated     .stealLooks(MT.WoodTreated    ).steal(MT.Wood           ).setLocal("Обработанное дерево").setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE                                        ).addReRegistrationToThis(MT.WoodTreated, MT.WoodPolished).setFurnaceBurnTime(TICKS_PER_SMELT/2);
		WoodUntreated   .stealLooks(MT.WOODS.Spruce   ).steal(MT.Wood           ).setLocal("Необработанное дерево").setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE                                        ).addReRegistrationToThis(ANY.WoodMagical.mToThis.toArray(ZL_MT)).addReRegistrationToThis(ANY.WoodNormal.mToThis.toArray(ZL_MT)).setFurnaceBurnTime(TICKS_PER_SMELT/2);
		Wood            .stealLooks(MT.WOODS.Spruce   ).steal(MT.Wood           ).setLocal("Дерево"             ).setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE                                        ).setFurnaceBurnTime(TICKS_PER_SMELT/2);
		PlasticHard     .stealLooks(MT.Polycarbonate  ).steal(MT.Polycarbonate  ).setLocal("Жесткий пластик"    ).setAllToTheOutputOf(MT.Plastic        ).put(                                                       ).addReRegistrationToThis(MT.Polycarbonate, MT.PVC);
		Plastic         .stealLooks(MT.Plastic        ).steal(MT.Plastic        ).setLocal("Пластик"            ).setAllToTheOutputOf(MT.Plastic        ).put(                                                       ).addReRegistrationToThis(MT.Polycarbonate, MT.PVC, MT.Teflon, MT.Bakelite, MT.Plastic);
		Rubber          .stealLooks(MT.Rubber         ).steal(MT.Rubber         ).setLocal("Резина"             ).setAllToTheOutputOf(MT.Rubber         ).put(                                                       ).addReRegistrationToThis(MT.Rubber);
		WoodPlastic     .stealLooks(MT.WOODS.Spruce   ).steal(MT.Wood                                                                                   ).put(DONT_SHOW_THIS_COMPONENT                               ).addReRegistrationToThis(ANY.Plastic, MT.PetrifiedWood);
		
		_Steel          .stealLooks(MT.Steel          ).put(DONT_SHOW_THIS_COMPONENT);
		_Bronze         .stealLooks(MT.Bronze         ).put(DONT_SHOW_THIS_COMPONENT);
		_Metal          .stealLooks(MT.Fe             ).put(DONT_SHOW_THIS_COMPONENT);
	}
}
