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

import gregapi.cover.covers.*;
import gregapi.data.*;
import gregapi.item.CreativeTab;
import gregapi.item.multiitem.MultiItemRandomWithCompat;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregtech.items.behaviors.Behavior_DataStorage;
import gregtech.items.behaviors.Behavior_DataStorage16;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

import static gregapi.data.CS.*;

public class MultiItemTechnological extends MultiItemRandomWithCompat {
	public MultiItemTechnological(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized);
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Технологии", this, (short)30501));
	}
	
	@Override
	public void addItems() {
		int tLastID = 0;
		
		for (int i = 0; i < 10; i++) {
		IL.MOTORS[i]          .set(addItem(12000+i, "Компактный электромотор ("+VN[i]+")"            , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.MOTUS, 1+i)));
		IL.PUMPS[i]           .set(addItem(12020+i, "Компактный электрический насос ("+VN[i]+")"     , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.ITER, 1+i), TC.stack(TC.AQUA, 1+i), new CoverPump(250<<(2*i))));
		IL.CONVEYERS[i]       .set(addItem(12040+i, "Компактный электрический конвейер ("+VN[i]+")"  , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.ITER, 1+i), new CoverConveyor(512>>i)));
		IL.PISTONS[i]         .set(addItem(12060+i, "Компактный электрический поршень ("+VN[i]+")"   , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.MOTUS, 1+i)));
		IL.ROBOT_ARMS[i]      .set(addItem(12080+i, "Компактная рука робота ("+VN[i]+")"             , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.HUMANUS, 1+i), new CoverRobotArm(512>>i)));
		IL.FIELD_GENERATORS[i].set(addItem(12100+i, "Компактный излучатель силового поля ("+VN[i]+")", "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.TUTAMEN, 1+i)));
		IL.EMITTERS[i]        .set(addItem(12120+i, "Компактный излучатель сигналов ("+VN[i]+")"     , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.LUX, 1+i)));
		IL.SENSORS[i]         .set(addItem(12140+i, "Компактный датчик ("+VN[i]+")"                  , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.SENSUS, 1+i)));
		}
		
		IL.Cover_Blank                           .set(addItem(tLastID =  1000, "Пустое покрытие"                                   , "*ПУСТО*"                                                                 , new CoverTextureMulti(T, T, "machines/covers/blank/", 6)   , TC.stack(TC.MACHINA, 2)));
		IL.Cover_Crafting                        .set(addItem(tLastID =  1001, "Покрытие с верстаком"                              , "Обычный верстак в виде прикрытия"                                        , new CoverCrafting("machines/covers/crafting/", 6)          , TC.stack(TC.MACHINA, 1), TC.stack(TC.FABRICO, 3)));
		IL.Cover_Machine_Display                 .set(addItem(tLastID =  1002, "Индикатор состояния машины"                        , "Показывает состояние машины и имеет переключатель ВКЛ/ВЫКЛ"              , new CoverControllerDisplay()                               , TC.stack(TC.MACHINA, 1), TC.stack(TC.SENSUS, 3)));
		IL.Cover_Auto_Switch                     .set(addItem(tLastID =  1003, "Автоматический выключатель"                        , "Автоматически ВКЛ/ВЫКЛ машины при необходимости"                         , new CoverControllerAuto()                                  , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 3)));
		IL.Cover_Energy_Display                  .set(addItem(tLastID =  1004, "Индикатор энергии"                                 , "Отображает хранящуюся энергию"                                           , new CoverDisplayEnergy()                                   , TC.stack(TC.MACHINA, 1), TC.stack(TC.SENSUS, 2), TC.stack(TC.POTENTIA, 1)));
		IL.Cover_Redstone_Switch                 .set(addItem(tLastID =  1005, "Ручной выключатель"                                , "ВКЛ/ВЫКЛ машины с помощью сигнала красного камня"                        , new CoverControllerRedstone()                              , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 2)));
		IL.Cover_Auto_Switch_Redstone            .set(addItem(tLastID =  1006, "Полуавтоматический выключатель"                    , "ВКЛ/ВЫКЛ машины с помощью сигнала красного камня, но позволяет закончить", new CoverControllerAutoRedstone()                          , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 3)));
		IL.Cover_Redstone_Selector               .set(addItem(tLastID =  1007, "Красный переключатель"                             , "Управляется сигналом красного камня"                                     , new CoverSelectorRedstone()                                , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 2)));
		IL.Cover_Manual_Selector                 .set(addItem(tLastID =  1008, "Ручной переключатель"                              , "Управляется кнопками"                                                    , new CoverSelectorManual()                                  , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 2)));
		IL.Cover_Auto_Switch_01_Minute           .set(addItem(tLastID =  1009, "Переключатель автоматической перезагрузки (1 мин)" , "Перезапускает устройство каждую минуту"                                  , new CoverControllerAutoTimer( 1200)                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Auto_Switch_05_Minute           .set(addItem(tLastID =  1010, "Переключатель автоматической перезагрузки (5 мин)" , "Перезапускает устройство каждые 5 минут"                                 , new CoverControllerAutoTimer( 6000)                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Auto_Switch_10_Minute           .set(addItem(tLastID =  1011, "Переключатель автоматической перезагрузки (10 мин)", "Перезапускает устройство каждые 10 минут"                                , new CoverControllerAutoTimer(12000)                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Auto_Switch_20_Minute           .set(addItem(tLastID =  1012, "Переключатель автоматической перезагрузки (20 мин)", "Перезапускает устройство каждые 20 минут"                                , new CoverControllerAutoTimer(24000)                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Auto_Switch_30_Minute           .set(addItem(tLastID =  1013, "Переключатель автоматической перезагрузки (30 мин)", "Перезапускает устройство каждые 30 минут"                                , new CoverControllerAutoTimer(36000)                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Scale_Energy                    .set(addItem(tLastID =  1014, "Датчик энергии"                                    , "Излучение зависит от колличества энергии"                                , new CoverScaleEnergy()                                     , TC.stack(TC.MACHINA, 2), TC.stack(TC.POTENTIA, 1)));
		IL.Cover_Detector_Possible               .set(addItem(tLastID =  1015, "Датчик активности (Ожидание)"                      , "Выдает сигнал при готовности"                                            , new CoverDetectorRunningPossible()                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Detector_Passively              .set(addItem(tLastID =  1016, "Датчик активности (Активно)"                       , "Выдает сигнал когда запущен"                                             , new CoverDetectorRunningPassively()                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Detector_Actively               .set(addItem(tLastID =  1017, "Датчик активности (Обработка)"                     , "Выдает сигнал при обработке"                                             , new CoverDetectorRunningActively()                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Scale_Progress                  .set(addItem(tLastID =  1018, "Датчик прогресса"                                  , "Излучает сигнал в зависимости от прогресса"                              , new CoverScaleProgress()                                   , TC.stack(TC.MACHINA, 2), TC.stack(TC.FABRICO, 1)));
		IL.Cover_Detector_Success                .set(addItem(tLastID =  1019, "Датчик активности (Успех)"                         , "Выдает сигнал при оканчании процесса"                                    , new CoverDetectorRunningSuccessfully()                     , TC.stack(TC.MACHINA, 2), TC.stack(TC.FABRICO, 1)));
		IL.Cover_Drain                           .set(addItem(tLastID =  1020, "Дренаж"                                            , ""                                                                        , new CoverDrain()                                           , TC.stack(TC.MACHINA, 1), TC.stack(TC.AQUA, 1), TC.stack(TC.VACUOS, 1)));
		IL.Cover_Redstone_Emitter                .set(addItem(tLastID =  1021, "Излучатель сигнала красного сигнала"               , "Постоянно излучает сигнал красного камня"                                , new CoverRedstoneEmitter()                                 , TC.stack(TC.MACHINA, 1), TC.stack(TC.POTENTIA, 1)));
		IL.Cover_Vent                            .set(addItem(tLastID =  1022, "Вентиляционное отверстие"                          , "Вентилирует воздух в резервуары и трубы"                                 , new CoverVent()                                            , TC.stack(TC.MACHINA, 1), TC.stack(TC.AER, 1), TC.stack(TC.VACUOS, 1)));
		IL.Cover_Filter_Item                     .set(addItem(tLastID =  1023, "Фильтр предметов"                                  , "Фильтр для предметов"                                                    , new CoverFilterItem()                                      , TC.stack(TC.MACHINA, 1), TC.stack(TC.ORDO, 1), TC.stack(TC.ITER, 1)));
		IL.Cover_Filter_Fluid                    .set(addItem(tLastID =  1024, "Фильтр жидкостей"                                  , "Фильтр для жидкостей"                                                    , new CoverFilterFluid()                                     , TC.stack(TC.MACHINA, 1), TC.stack(TC.ORDO, 1), TC.stack(TC.AQUA, 1)));
		IL.Cover_Controller                      .set(addItem(tLastID =  1025, "Контроллер покрытий"                               , "ВКЛ/ВЫКЛ сигнал красного камня на покрытия"                              , new CoverControllerCovers()                                , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 2)));
		IL.Cover_Shutter                         .set(addItem(tLastID =  1026, "Заглушка"                                          , "Соединяет и разделяет трубы и провода"                                   , new CoverShutter()                                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Button_Selector                 .set(addItem(tLastID =  1027, "Кнопочная панель"                                  , "Крышка настройщика, управляемая кнопками"                                , new CoverSelectorButtonPanel()                             , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Warning                         .set(addItem(tLastID =  1028, "Предупреждающее покрытие"                          , "Предупреждает об определенных типах опасности"                           , new CoverTextureMulti(T, T, "machines/covers/warning/", 20), TC.stack(TC.MACHINA, 1), TC.stack(TC.SENSUS, 1), TC.stack(TC.VINCULUM, 1)));
		IL.Cover_Redstone_Conductor_IN           .set(addItem(tLastID =  1029, "Крышка проводника сигнала кр.камня (Прием)"        , "Сигнал кр.камня перейдет к излучающим проводникам"                       , new CoverRedstoneConductorIN()                             , TC.stack(TC.MACHINA, 2), TC.stack(TC.SENSUS, 1)));
		IL.Cover_Redstone_Conductor_OUT          .set(addItem(tLastID =  1030, "Крышка проводника сигнала кр.камня (Излучение)"    , "Сигнал кр.камня предеается на принимающий проводник"                     , new CoverRedstoneConductorOUT()                            , TC.stack(TC.MACHINA, 2), TC.stack(TC.VINCULUM, 1)));
		IL.Cover_Retriever_Item                  .set(addItem(tLastID =  1031, "Извлекатель предметов"                             , "Извлекает предметы из трубопровода"                                      , new CoverRetrieverItem()                                   , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.ITER, 1)));
		
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(IL.Cover_Vent .get(1)), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.Air.make(0), FL.Air_Nether.make(0), FL.Air_End.make(0)), 0, 0, 0);
		RM.DidYouKnow.addFakeRecipe(F, ST.array(IL.Cover_Drain.get(1)), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.Water.make(0), FL.Ocean.make(0), FL.Dirty_Water.make(0)), 0, 0, 0);
		if (FL.XP.exists() || FL.Mob.exists())
		RM.DidYouKnow.addFakeRecipe(F, ST.array(IL.Cover_Drain.get(1)), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.XP.make(20), FL.Mob.make(66)), 0, 0, 0);
		if (FL.Sewage.exists())
		RM.DidYouKnow.addFakeRecipe(F, ST.array(IL.Cover_Drain.get(1)), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.Sewage.make(0)), 0, 0, 0);
		
		
		IL.Cover_Logistics_Display_CPU_Logic     .set(addItem(tLastID =  1086, "Дисплей логистики (ЦП Логика)"             , "Для использования с ядрами логистики и проводкой"         , CoverLogisticsDisplayCPULogic.INSTANCE                     , TC.stack(TC.MACHINA, 2), TC.stack(TC.SENSUS, 2), TC.stack(TC.ITER, 1)));
		IL.Cover_Logistics_Display_CPU_Control   .set(addItem(tLastID =  1087, "Дисплей логистики (ЦП Управление)"         , "Для использования с ядрами логистики и проводкой"         , CoverLogisticsDisplayCPUControl.INSTANCE                   , TC.stack(TC.MACHINA, 2), TC.stack(TC.SENSUS, 2), TC.stack(TC.ITER, 1)));
		IL.Cover_Logistics_Display_CPU_Storage   .set(addItem(tLastID =  1088, "Дисплей логистики (ЦП память)"             , "Для использования с ядрами логистики и проводкой"         , CoverLogisticsDisplayCPUStorage.INSTANCE                   , TC.stack(TC.MACHINA, 2), TC.stack(TC.SENSUS, 2), TC.stack(TC.ITER, 1)));
		IL.Cover_Logistics_Display_CPU_Conversion.set(addItem(tLastID =  1089, "Дисплей логистики (ЦП Конвертация)"        , "Для использования с ядрами логистики и проводкой"         , CoverLogisticsDisplayCPUConversion.INSTANCE                , TC.stack(TC.MACHINA, 2), TC.stack(TC.SENSUS, 2), TC.stack(TC.ITER, 1)));
		IL.Cover_Logistics_Fluid_Export          .set(addItem(tLastID =  1090, "Логический фильтр шины экспорта (Жидкость)", "Для использования с ядрами логистики и проводкой"         , CoverLogisticsFluidExport.INSTANCE                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Fluid_Import          .set(addItem(tLastID =  1091, "Логический фильтр шины импорта (Жидкость)" , "Для использования с ядрами логистики и проводкой"         , CoverLogisticsFluidImport.INSTANCE                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Fluid_Storage         .set(addItem(tLastID =  1092, "Логический фильтр шины хранения (Жидкость)", "Для использования с ядрами логистики и проводкой"         , CoverLogisticsFluidStorage.INSTANCE                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Item_Export           .set(addItem(tLastID =  1093, "Логический фильтр шины экспорта (Предметы)", "Для использования с ядрами логистики и проводкой"         , CoverLogisticsItemExport.INSTANCE                          , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Item_Import           .set(addItem(tLastID =  1094, "Логический фильтр шины импорта (Предметы)" , "Для использования с ядрами логистики и проводкой"         , CoverLogisticsItemImport.INSTANCE                          , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Item_Storage          .set(addItem(tLastID =  1095, "Логический фильтр шины хранения (Предметы)", "Для использования с ядрами логистики и проводкой"         , CoverLogisticsItemStorage.INSTANCE                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Generic_Export        .set(addItem(tLastID =  1096, "Общая логическая шина экспорта"            , "Для использования с ядрами логистики и проводкой"         , CoverLogisticsGenericExport.INSTANCE                       , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Generic_Import        .set(addItem(tLastID =  1097, "Общая логическая шина импорта"             , "Для использования с ядрами логистики и проводкой"         , CoverLogisticsGenericImport.INSTANCE                       , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Generic_Storage       .set(addItem(tLastID =  1098, "Общая логическая шина хранения"            , "Для использования с ядрами логистики и проводкой"         , CoverLogisticsGenericStorage.INSTANCE                      , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Dump                  .set(addItem(tLastID =  1099, "Логическая шина утилизации (Предметы)"     , "Для использования с ядрами логистики и проводкой"         , CoverLogisticsGenericDump.INSTANCE                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		
		// Cycle Logistics Displays
		CR.shapeless(IL.Cover_Logistics_Display_CPU_Logic     .get(1), new Object[] {IL.Cover_Logistics_Display_CPU_Conversion});
		CR.shapeless(IL.Cover_Logistics_Display_CPU_Control   .get(1), new Object[] {IL.Cover_Logistics_Display_CPU_Logic     });
		CR.shapeless(IL.Cover_Logistics_Display_CPU_Storage   .get(1), new Object[] {IL.Cover_Logistics_Display_CPU_Control   });
		CR.shapeless(IL.Cover_Logistics_Display_CPU_Conversion.get(1), new Object[] {IL.Cover_Logistics_Display_CPU_Storage   });
		// Cycle Logistics IO
		CR.shapeless(IL.Cover_Logistics_Fluid_Export          .get(1), new Object[] {IL.Cover_Logistics_Dump                  });
		CR.shapeless(IL.Cover_Logistics_Fluid_Import          .get(1), new Object[] {IL.Cover_Logistics_Fluid_Export          });
		CR.shapeless(IL.Cover_Logistics_Fluid_Storage         .get(1), new Object[] {IL.Cover_Logistics_Fluid_Import          });
		CR.shapeless(IL.Cover_Logistics_Item_Export           .get(1), new Object[] {IL.Cover_Logistics_Fluid_Storage         });
		CR.shapeless(IL.Cover_Logistics_Item_Import           .get(1), new Object[] {IL.Cover_Logistics_Item_Export           });
		CR.shapeless(IL.Cover_Logistics_Item_Storage          .get(1), new Object[] {IL.Cover_Logistics_Item_Import           });
		CR.shapeless(IL.Cover_Logistics_Generic_Export        .get(1), new Object[] {IL.Cover_Logistics_Item_Storage          });
		CR.shapeless(IL.Cover_Logistics_Generic_Import        .get(1), new Object[] {IL.Cover_Logistics_Generic_Export        });
		CR.shapeless(IL.Cover_Logistics_Generic_Storage       .get(1), new Object[] {IL.Cover_Logistics_Generic_Import        });
		CR.shapeless(IL.Cover_Logistics_Dump                  .get(1), new Object[] {IL.Cover_Logistics_Generic_Storage       });
		// Reset Covers
		CR.shapeless(IL.Cover_Filter_Item                     .get(1), new Object[] {IL.Cover_Filter_Item                     });
		CR.shapeless(IL.Cover_Filter_Fluid                    .get(1), new Object[] {IL.Cover_Filter_Fluid                    });
		CR.shapeless(IL.Cover_Retriever_Item                  .get(1), new Object[] {IL.Cover_Retriever_Item                  });
		
		
		
		CR.shaped(IL.Cover_Blank                    .get(1), CR.DEF_REV, "Sh" , "Pd"        , 'P', OP.plate.dat(MT.Al), 'S', OP.screw.dat(MT.Al));
		CR.shaped(IL.Cover_Crafting                 .get(1), CR.DEF_REV,  "C" ,  "Q"        , 'Q', IL.Cover_Blank, 'C', OD.craftingWorkBench);
		CR.shaped(IL.Cover_Machine_Display          .get(1), CR.DEF_REV, "LLB", "CQW"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'B', OD.lever, 'L', OP.wireGt01.dat(MT.Lumium));
		CR.shaped(IL.Cover_Auto_Switch              .get(1), CR.DEF_REV, "BW" , "CQ"        , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'B', OD.lever);
		CR.shaped(IL.Cover_Energy_Display           .get(1), CR.DEF_REV, "CLB", "WQW"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'B', MT.DATA.WIRES_01[1], 'L', OP.wireGt01.dat(MT.Lumium));
		CR.shaped(IL.Cover_Redstone_Switch          .get(1), CR.DEF_REV, "BW" , "CQ"        , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'B', OD.craftingRedstoneTorch);
		CR.shaped(IL.Cover_Auto_Switch_Redstone     .get(1), CR.DEF_REV, "BW" , "CQ"        , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[2], 'B', OD.lever);
		CR.shaped(IL.Cover_Redstone_Selector        .get(1), CR.DEF_REV, " C ", "WQX", " B ", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[2], 'B', Items.comparator, 'X', IL.Circuit_Selector.wild(1));
		CR.shaped(IL.Cover_Manual_Selector          .get(1), CR.DEF_REV, " C ", "WQX", " B ", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[2], 'B', OD.button, 'X', IL.Circuit_Selector.wild(1));
		CR.shaped(IL.Cover_Auto_Switch_01_Minute    .get(1), CR.DEF_REV, "BWd", "CQ "       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[5], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Auto_Switch_05_Minute    .get(1), CR.DEF_REV, "BW ", "CQd"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[4], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Auto_Switch_10_Minute    .get(1), CR.DEF_REV, "BW ", "CQ ", "  d", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[3], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Auto_Switch_20_Minute    .get(1), CR.DEF_REV, "BW" , "CQ" , " d" , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Auto_Switch_30_Minute    .get(1), CR.DEF_REV, "BW" , "CQ" , "d " , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Scale_Energy             .get(1), CR.DEF_REV, "WQW", "BCB"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', MT.DATA.WIRES_01[1]);
		CR.shaped(IL.Cover_Detector_Possible        .get(1), CR.DEF_REV, "WQW", "BCB"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', Items.comparator);
		CR.shaped(IL.Cover_Detector_Passively       .get(1), CR.DEF_REV, "WQW", "BCB"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Detector_Actively        .get(1), CR.DEF_REV, "WQW", "BCX"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', Items.comparator, 'X', Items.repeater);
		CR.shaped(IL.Cover_Scale_Progress           .get(1), CR.DEF_REV, "WQW", "BCB"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', OP.gearGtSmall.dat(MT.Brass));
		CR.shaped(IL.Cover_Detector_Success         .get(1), CR.DEF_REV, "WQW", "BCX"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', OD.button, 'X', OD.craftingRedstoneTorch);
		CR.shaped(IL.Cover_Drain                    .get(1), CR.DEF_REV, "RRR", "RwR", "RRR", 'R', OP.stick.dat(ANY.Iron));
		CR.shaped(IL.Cover_Redstone_Emitter         .get(1), CR.DEF_REV, "BQB", "WXW"       , 'Q', IL.Cover_Blank, 'W', MT.DATA.CABLES_01[1], 'B', OD.button, 'X', Items.comparator);
		CR.shaped(IL.Cover_Vent                     .get(1), CR.DEF_REV, "RRR", "RXR", "RRR", 'R', OP.stick.dat(ANY.Iron), 'X', OP.rotor.dat(ANY.Iron));
		CR.shaped(IL.Cover_Filter_Item              .get(1), CR.DEF_REV, " Z ", "ZQZ", " Z ", 'Q', IL.Cover_Blank, 'Z', OP.foil.dat(MT.Zn));
		CR.shaped(IL.Cover_Filter_Fluid             .get(1), CR.DEF_REV, "Z Z", " Q ", "Z Z", 'Q', IL.Cover_Blank, 'Z', OP.foil.dat(MT.Zn));
		CR.shaped(IL.Cover_Controller               .get(1), CR.DEF_REV, "BW" , "CQ"        , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', Items.comparator);
		CR.shaped(IL.Cover_Shutter                  .get(1), CR.DEF_REV, "TwT", "PQP", "TdT", 'Q', IL.Cover_Blank, 'P', OP.plate.dat(MT.StainlessSteel), 'T', OP.screw.dat(MT.StainlessSteel));
		CR.shaped(IL.Cover_Button_Selector          .get(1), CR.DEF_REV, "BXB", "BQB", "BCB", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'B', OD.button, 'X', IL.Circuit_Selector.wild(1));
		CR.shaped(IL.Cover_Warning                  .get(1), CR.DEF_REV, "GB" , "YQ"        , 'Q', IL.Cover_Blank, 'Y', DYE_OREDICTS[DYE_INDEX_Yellow], 'G', OD.itemGlue);
		CR.shaped(IL.Cover_Redstone_Conductor_IN    .get(1), CR.DEF_REV,  "R" ,  "Q"        , 'Q', IL.Cover_Blank, 'R', OP.wireGt01.dat(MT.RedAlloy));
		CR.shaped(IL.Cover_Redstone_Conductor_OUT   .get(1), CR.DEF_REV,  "Q" ,  "R"        , 'Q', IL.Cover_Blank, 'R', OP.wireGt01.dat(MT.RedAlloy));
		CR.shaped(IL.Cover_Retriever_Item           .get(1), CR.DEF_REV, "RPR", "CQC"       , 'Q', IL.Cover_Filter_Item, 'P', IL.PISTONS[1], 'C', OD_CIRCUITS[3], 'R', OP.plateCurved.dat(MT.Electrum));
		
		CR.shapeless(IL.Cover_Redstone_Conductor_IN .get(1), new Object[] {IL.Cover_Redstone_Conductor_OUT.get(1)});
		CR.shapeless(IL.Cover_Redstone_Conductor_OUT.get(1), new Object[] {IL.Cover_Redstone_Conductor_IN .get(1)});
		
		
		IL.Cover_Pressure_Valve            .set(addItem(tLastID =  2000, "Клапан давления"                   , ""                                                , new CoverPressureValve()                              , TC.stack(TC.MACHINA, 1), TC.stack(TC.AER, 1), TC.stack(TC.VACUOS, 1)));
		
		CR.shaped(IL.Cover_Pressure_Valve           .get(1), CR.DEF_REV, "TCT", "wPd"       , 'C', OP.plateCurved.dat(MT.Brass), 'P', OP.plate.dat(MT.Brass), 'T', OP.screw.dat(MT.Brass));
		
		
		
		IL.Shape_Extruder_Empty            .set(addItem(tLastID = 10000, "Пустая форма экструдера"                  , "Необработанная пластина для создания форм экструдера", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		
		CR.shaped(IL.Shape_Extruder_Empty.get(1), CR.DEF_REV, "hf" , "xP", 'P', OP.plateDouble.dat(MT.TungstenCarbide));
		
		IL.Shape_Extruder_Plate            .set(addItem(tLastID = 10001, "Форма экструдера (Пластина)"            , "Форма экструдера для изготовления пластин"                       , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Rod_Long         .set(addItem(tLastID = 10002, "Форма экструдера (Длинный стержень)"    , "Форма экструдера для изготовления длинных стержней"              , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Bolt             .set(addItem(tLastID = 10003, "Форма экструдера (Болт)"                , "Форма экструдера для изготовления болтов"                        , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Ring             .set(addItem(tLastID = 10004, "Форма экструдера (Кольцо)"              , "Форма экструдера для изготовления колец"                         , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Cell             .set(addItem(tLastID = 10005, "Форма экструдера (Капсула)"             , "Форма экструдера для изготовления капсул"                        , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Ingot            .set(addItem(tLastID = 10006, "Форма экструдера (Слиток)"              , "Форма экструдера для изготовления слитков"                       , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Wire             .set(addItem(tLastID = 10007, "Форма экструдера (Провод)"              , "Форма экструдера для изготовления проводов"                      , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Casing           .set(addItem(tLastID = 10008, "Форма экструдера (Оболочка)"            , "Форма экструдера для изготовления оболочек"                      , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pipe_Tiny        .set(addItem(tLastID = 10009, "Форма экструдера (Крохотная труба)"     , "Форма экструдера для изготовления крохотных труб"                , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pipe_Small       .set(addItem(tLastID = 10010, "Форма экструдера (Малая труба)"         , "Форма экструдера для изготовления малых труб"                    , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pipe_Medium      .set(addItem(tLastID = 10011, "Форма экструдера (Труба)"               , "Форма экструдера для изготовления труб"                          , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pipe_Large       .set(addItem(tLastID = 10012, "Форма экструдера (Большая труба)"       , "Форма экструдера для изготовления больших труб"                  , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pipe_Huge        .set(addItem(tLastID = 10013, "Форма экструдера (Огромная труба)"      , "Форма экструдера для изготовления огромных труб"                 , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Block            .set(addItem(tLastID = 10014, "Форма экструдера (Блок)"                , "Форма экструдера для изготовления блоков"                        , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Sword            .set(addItem(tLastID = 10015, "Форма экструдера (Лезвие меча)"         , "Форма экструдера для изготовления мечей"                         , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pickaxe          .set(addItem(tLastID = 10016, "Форма экструдера (Основа кирки)"        , "Форма экструдера для изготовления кирок"                         , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Shovel           .set(addItem(tLastID = 10017, "Форма экструдера (Основа лопаты)"       , "Форма экструдера для изготовления лопат"                         , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Axe              .set(addItem(tLastID = 10018, "Форма экструдера (Основа топора)"       , "Форма экструдера для изготовления топоров"                       , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Hoe              .set(addItem(tLastID = 10019, "Форма экструдера (Основа мотыги)"       , "Форма экструдера для изготовления мотыг"                         , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Hammer           .set(addItem(tLastID = 10020, "Форма экструдера (Основа молота)"       , "Форма экструдера для изготовления молотов"                       , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_File             .set(addItem(tLastID = 10021, "Форма экструдера (Основа напильника)"   , "Форма экструдера для изготовления напильников"                   , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Saw              .set(addItem(tLastID = 10022, "Форма экструдера (Лезвие пилы)"         , "Форма экструдера для изготовления пил"                           , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Gear             .set(addItem(tLastID = 10023, "Форма экструдера (Шестерня)"            , "Форма экструдера для изготовления шестерней"                     , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Bottle           .set(addItem(tLastID = 10024, "Форма экструдера (Бутылка)"             , "Форма экструдера для изготовления бутылок"                       , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Plate_Curved     .set(addItem(tLastID = 10025, "Форма экструдера (Гнутая пластина)"     , "Форма экструдера для изготовления гнутых пластин"                , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Gear_Small       .set(addItem(tLastID = 10026, "Форма экструдера (Малая шестерня)"      , "Форма экструдера для изготовления малых шестерней"               , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Rod              .set(addItem(tLastID = 10027, "Форма экструдера (Стержень)"            , "Форма экструдера для изготовления стержней"                      , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_CCC              .set(addItem(tLastID = 10028, "Форма экструдера (Капсульный контейнер)", "Форма экструдера для изготовления капсульных контейнеров"        , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Foil             .set(addItem(tLastID = 10029, "Форма экструдера (Фольга)"              , "Форма экструдера для изготовления фольги из неметаллов"          , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Plate_Tiny       .set(addItem(tLastID = 10030, "Форма экструдера (Малая пластина)"      , "Форма экструдера для изготовления малых пластин"                 , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Wire_Fine        .set(addItem(tLastID = 10031, "Форма экструдера (Проволока)"           , "Форма экструдера для изготовления тонкой проволоки из неметаллов", TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		
		CR.shaped(IL.Shape_Extruder_Ingot           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Empty);
		CR.shaped(IL.Shape_Extruder_Plate_Tiny      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Empty);
		CR.shaped(IL.Shape_Extruder_Plate_Curved    .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Empty);
		CR.shaped(IL.Shape_Extruder_Rod             .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Empty);
		CR.shaped(IL.Shape_Extruder_Foil            .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_Extruder_Empty);
		CR.shaped(IL.Shape_Extruder_Ring            .get(1), CR.DEF_REV, "   ", " P ", " x ", 'P', IL.Shape_Extruder_Empty);
		
		CR.shaped(IL.Shape_Extruder_Bolt            .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Rod);
		CR.shaped(IL.Shape_Extruder_Wire            .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Rod);
		CR.shaped(IL.Shape_Extruder_Rod_Long        .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Rod);
		CR.shaped(IL.Shape_Extruder_Wire_Fine       .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Rod);
		
		CR.shaped(IL.Shape_Extruder_Block           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Ingot);
		CR.shaped(IL.Shape_Extruder_Pickaxe         .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Ingot);
		CR.shaped(IL.Shape_Extruder_Hammer          .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Ingot);
		CR.shaped(IL.Shape_Extruder_Hoe             .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Ingot);
		
		CR.shaped(IL.Shape_Extruder_Gear            .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Ring);
		CR.shaped(IL.Shape_Extruder_Gear_Small      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Ring);
		CR.shaped(IL.Shape_Extruder_Bottle          .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Ring);
		CR.shaped(IL.Shape_Extruder_Cell            .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Ring);
		CR.shaped(IL.Shape_Extruder_CCC             .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_Extruder_Ring);
		
		CR.shaped(IL.Shape_Extruder_Axe             .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Tiny);
		CR.shaped(IL.Shape_Extruder_Shovel          .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Tiny);
		CR.shaped(IL.Shape_Extruder_File            .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Tiny);
		CR.shaped(IL.Shape_Extruder_Sword           .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Plate_Tiny);
		CR.shaped(IL.Shape_Extruder_Saw             .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_Extruder_Plate_Tiny);
		
		CR.shaped(IL.Shape_Extruder_Plate           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Foil);
		CR.shaped(IL.Shape_Extruder_Casing          .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Foil);
		
		CR.shaped(IL.Shape_Extruder_Pipe_Tiny       .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Curved);
		CR.shaped(IL.Shape_Extruder_Pipe_Small      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Curved);
		CR.shaped(IL.Shape_Extruder_Pipe_Medium     .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Curved);
		CR.shaped(IL.Shape_Extruder_Pipe_Large      .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Plate_Curved);
		CR.shaped(IL.Shape_Extruder_Pipe_Huge       .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_Extruder_Plate_Curved);
		
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Empty, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Plate, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Rod_Long, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Bolt, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Ring, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Cell, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Ingot, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Wire, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Casing, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Pipe_Tiny, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Pipe_Small, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Pipe_Medium, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Pipe_Large, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Pipe_Huge, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Block, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Sword, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Pickaxe, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Shovel, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Axe, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Hoe, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Hammer, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_File, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Saw, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Gear, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Bottle, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Plate_Curved, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Gear_Small, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Rod, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_CCC, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Foil, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Plate_Tiny, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Extruder_Wire_Fine, (byte)45);
		
		
		
		IL.Shape_SimpleEx_Empty            .set(addItem(tLastID = 10200, "Пустая форма экструдера"                                    , "Необработанная пластина для создания форм экструдера", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		
		CR.shaped(IL.Shape_SimpleEx_Empty.get(1), CR.DEF_REV, "hf" , "xP", 'P', OP.plateDouble.dat(ANY.Steel));
		
		IL.Shape_SimpleEx_Plate            .set(addItem(tLastID = 10201, "Форма низкотемпературного экструдера (Пластина)"            , "Форма экструдера для изготовления пластин"                       , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Rod_Long         .set(addItem(tLastID = 10202, "Форма низкотемпературного экструдера (Длинный стержень)"    , "Форма экструдера для изготовления длинных стержней"              , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Bolt             .set(addItem(tLastID = 10203, "Форма низкотемпературного экструдера (Болт)"                , "Форма экструдера для изготовления болтов"                        , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Ring             .set(addItem(tLastID = 10204, "Форма низкотемпературного экструдера (Кольцо)"              , "Форма экструдера для изготовления колец"                         , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Cell             .set(addItem(tLastID = 10205, "Форма низкотемпературного экструдера (Капсула)"             , "Форма экструдера для изготовления капсул"                        , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Ingot            .set(addItem(tLastID = 10206, "Форма низкотемпературного экструдера (Слиток)"              , "Форма экструдера для изготовления слитков"                       , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Wire             .set(addItem(tLastID = 10207, "Форма низкотемпературного экструдера (Провод)"              , "Форма экструдера для изготовления проводов"                      , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Casing           .set(addItem(tLastID = 10208, "Форма низкотемпературного экструдера (Оболочка)"            , "Форма экструдера для изготовления оболочек"                      , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pipe_Tiny        .set(addItem(tLastID = 10209, "Форма низкотемпературного экструдера (Крохотная труба)"     , "Форма экструдера для изготовления крохотных труб"                , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pipe_Small       .set(addItem(tLastID = 10210, "Форма низкотемпературного экструдера (Малая труба)"         , "Форма экструдера для изготовления малых труб"                    , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pipe_Medium      .set(addItem(tLastID = 10211, "Форма низкотемпературного экструдера (Труба)"               , "Форма экструдера для изготовления труб"                          , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pipe_Large       .set(addItem(tLastID = 10212, "Форма низкотемпературного экструдера (Большая труба)"       , "Форма экструдера для изготовления больших труб"                  , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pipe_Huge        .set(addItem(tLastID = 10213, "Форма низкотемпературного экструдера (Огромная труба)"      , "Форма экструдера для изготовления огромных труб"                 , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Block            .set(addItem(tLastID = 10214, "Форма низкотемпературного экструдера (Блок)"                , "Форма экструдера для изготовления блоков"                        , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Sword            .set(addItem(tLastID = 10215, "Форма низкотемпературного экструдера (Лезвие меча)"         , "Форма экструдера для изготовления мечей"                         , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pickaxe          .set(addItem(tLastID = 10216, "Форма низкотемпературного экструдера (Основа кирки)"        , "Форма экструдера для изготовления кирок"                         , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Shovel           .set(addItem(tLastID = 10217, "Форма низкотемпературного экструдера (Основа лопаты)"       , "Форма экструдера для изготовления лопат"                         , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Axe              .set(addItem(tLastID = 10218, "Форма низкотемпературного экструдера (Основа топора)"       , "Форма экструдера для изготовления топоров"                       , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Hoe              .set(addItem(tLastID = 10219, "Форма низкотемпературного экструдера (Основа мотыги)"       , "Форма экструдера для изготовления мотыг"                         , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Hammer           .set(addItem(tLastID = 10220, "Форма низкотемпературного экструдера (Основа молота)"       , "Форма экструдера для изготовления молотов"                       , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_File             .set(addItem(tLastID = 10221, "Форма низкотемпературного экструдера (Основа напильника)"   , "Форма экструдера для изготовления напильников"                   , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Saw              .set(addItem(tLastID = 10222, "Форма низкотемпературного экструдера (Лезвие пилы)"         , "Форма экструдера для изготовления пил"                           , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Gear             .set(addItem(tLastID = 10223, "Форма низкотемпературного экструдера (Шестерня)"            , "Форма экструдера для изготовления шестерней"                     , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Bottle           .set(addItem(tLastID = 10224, "Форма низкотемпературного экструдера (Бутылка)"             , "Форма экструдера для изготовления бутылок"                       , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Plate_Curved     .set(addItem(tLastID = 10225, "Форма низкотемпературного экструдера (Гнутая пластина)"     , "Форма экструдера для изготовления гнутых пластин"                , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Gear_Small       .set(addItem(tLastID = 10226, "Форма низкотемпературного экструдера (Малая шестерня)"      , "Форма экструдера для изготовления малых шестерней"               , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Rod              .set(addItem(tLastID = 10227, "Форма низкотемпературного экструдера (Стержень)"            , "Форма экструдера для изготовления стержней"                      , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_CCC              .set(addItem(tLastID = 10228, "Форма низкотемпературного экструдера (Капсульный контейнер)", "Форма экструдера для изготовления капсульных контейнеров"        , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Foil             .set(addItem(tLastID = 10229, "Форма низкотемпературного экструдера (Фольга)"              , "Форма экструдера для изготовления фольги из неметаллов"          , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Plate_Tiny       .set(addItem(tLastID = 10230, "Форма низкотемпературного экструдера (Малая пластина)"      , "Форма экструдера для изготовления малых пластин"                 , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Wire_Fine        .set(addItem(tLastID = 10231, "Форма низкотемпературного экструдера (Проволока)"           , "Форма экструдера для изготовления тонкой проволоки из неметаллов", TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		
		CR.shaped(IL.Shape_SimpleEx_Ingot           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Empty);
		CR.shaped(IL.Shape_SimpleEx_Plate_Tiny      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Empty);
		CR.shaped(IL.Shape_SimpleEx_Plate_Curved    .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Empty);
		CR.shaped(IL.Shape_SimpleEx_Rod             .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Empty);
		CR.shaped(IL.Shape_SimpleEx_Foil            .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_SimpleEx_Empty);
		CR.shaped(IL.Shape_SimpleEx_Ring            .get(1), CR.DEF_REV, "   ", " P ", " x ", 'P', IL.Shape_SimpleEx_Empty);
		
		CR.shaped(IL.Shape_SimpleEx_Bolt            .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Rod);
		CR.shaped(IL.Shape_SimpleEx_Wire            .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Rod);
		CR.shaped(IL.Shape_SimpleEx_Rod_Long        .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Rod);
		CR.shaped(IL.Shape_SimpleEx_Wire_Fine       .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Rod);
		
		CR.shaped(IL.Shape_SimpleEx_Block           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Ingot);
		CR.shaped(IL.Shape_SimpleEx_Pickaxe         .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Ingot);
		CR.shaped(IL.Shape_SimpleEx_Hammer          .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Ingot);
		CR.shaped(IL.Shape_SimpleEx_Hoe             .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Ingot);
		
		CR.shaped(IL.Shape_SimpleEx_Gear            .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Ring);
		CR.shaped(IL.Shape_SimpleEx_Gear_Small      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Ring);
		CR.shaped(IL.Shape_SimpleEx_Bottle          .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Ring);
		CR.shaped(IL.Shape_SimpleEx_Cell            .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Ring);
		CR.shaped(IL.Shape_SimpleEx_CCC             .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_SimpleEx_Ring);
		
		CR.shaped(IL.Shape_SimpleEx_Axe             .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Tiny);
		CR.shaped(IL.Shape_SimpleEx_Shovel          .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Tiny);
		CR.shaped(IL.Shape_SimpleEx_File            .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Tiny);
		CR.shaped(IL.Shape_SimpleEx_Sword           .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Plate_Tiny);
		CR.shaped(IL.Shape_SimpleEx_Saw             .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_SimpleEx_Plate_Tiny);
		
		CR.shaped(IL.Shape_SimpleEx_Plate           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Foil);
		CR.shaped(IL.Shape_SimpleEx_Casing          .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Foil);
		
		CR.shaped(IL.Shape_SimpleEx_Pipe_Tiny       .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Curved);
		CR.shaped(IL.Shape_SimpleEx_Pipe_Small      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Curved);
		CR.shaped(IL.Shape_SimpleEx_Pipe_Medium     .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Curved);
		CR.shaped(IL.Shape_SimpleEx_Pipe_Large      .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Plate_Curved);
		CR.shaped(IL.Shape_SimpleEx_Pipe_Huge       .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_SimpleEx_Plate_Curved);
		
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Empty, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Plate, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Rod_Long, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Bolt, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Ring, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Cell, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Ingot, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Wire, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Casing, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Pipe_Tiny, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Pipe_Small, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Pipe_Medium, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Pipe_Large, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Pipe_Huge, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Block, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Sword, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Pickaxe, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Shovel, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Axe, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Hoe, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Hammer, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_File, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Saw, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Gear, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Bottle, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Plate_Curved, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Gear_Small, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Rod, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_CCC, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Foil, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Plate_Tiny, (byte)55);
		BooksGT.BOOK_REGISTER.put(IL.Shape_SimpleEx_Wire_Fine, (byte)55);
		
		
		IL.Shape_Foodmold_Empty            .set(addItem(tLastID = 10800, "Пустая пищевая форма"           , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		
		CR.shaped(IL.Shape_Foodmold_Empty.get(1), CR.DEF_REV, "hf" , "xP", 'P', OP.plateDouble.dat(MT.StainlessSteel));
		
		IL.Shape_Foodmold_Bun              .set(addItem(tLastID = 10801, "Пищевая форма (Булочка)"        , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		IL.Shape_Foodmold_Bread            .set(addItem(tLastID = 10802, "Пищевая форма (Хлеб)"           , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		IL.Shape_Foodmold_Baguette         .set(addItem(tLastID = 10803, "Пищевая форма (Багет)"          , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		IL.Shape_Foodmold_Cylinder         .set(addItem(tLastID = 10804, "Пищевая форма (Цилиндр)"        , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		IL.Shape_Foodmold_Toast            .set(addItem(tLastID = 10805, "Пищевая форма (Тосты)"          , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		
		CR.shaped(IL.Shape_Foodmold_Bun             .get(1), CR.DEF_REV, "h  ", " P ", "   ", 'P', IL.Shape_Foodmold_Empty);
		CR.shaped(IL.Shape_Foodmold_Bread           .get(1), CR.DEF_REV, " h ", " P ", "   ", 'P', IL.Shape_Foodmold_Empty);
		CR.shaped(IL.Shape_Foodmold_Baguette        .get(1), CR.DEF_REV, "  h", " P ", "   ", 'P', IL.Shape_Foodmold_Empty);
		CR.shaped(IL.Shape_Foodmold_Cylinder        .get(1), CR.DEF_REV, "   ", " Ph", "   ", 'P', IL.Shape_Foodmold_Empty);
		CR.shaped(IL.Shape_Foodmold_Toast           .get(1), CR.DEF_REV, "   ", " P ", "  h", 'P', IL.Shape_Foodmold_Empty);
		
		BooksGT.BOOK_REGISTER.put(IL.Shape_Foodmold_Empty, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Foodmold_Bun, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Foodmold_Bread, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Foodmold_Baguette, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Foodmold_Cylinder, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Foodmold_Toast, (byte)45);
		
		
		
		
		IL.Shape_Press_Bullet_Casing_Small .set(addItem(tLastID = 10896, "Форма для гильзы (Малый калибр)"  , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.TELUM, 2)));
		IL.Shape_Press_Bullet_Casing_Medium.set(addItem(tLastID = 10897, "Форма для гильзы (Средний калибр)", "", TC.stack(TC.FABRICO, 2), TC.stack(TC.TELUM, 3)));
		IL.Shape_Press_Bullet_Casing_Large .set(addItem(tLastID = 10898, "Форма для гильзы (Крупный калибр)", "", TC.stack(TC.FABRICO, 2), TC.stack(TC.TELUM, 4)));
		
		CR.shaped(IL.Shape_Press_Bullet_Casing_Small .get(1), CR.DEF_REV, "TPT", "dyh", "SPS", 'S', OP.stick.dat(ANY.Steel), 'T', OP.screw.dat(ANY.Steel), 'P', OP.plateDouble.dat(ANY.Steel));
		CR.shaped(IL.Shape_Press_Bullet_Casing_Medium.get(1), CR.DEF_REV, "TPT", "dyh", "SPS", 'S', OP.stick.dat(ANY.Steel), 'T', OP.screw.dat(ANY.Steel), 'P', OP.plateTriple.dat(ANY.Steel));
		CR.shaped(IL.Shape_Press_Bullet_Casing_Large .get(1), CR.DEF_REV, "TPT", "dyh", "SPS", 'S', OP.stick.dat(ANY.Steel), 'T', OP.screw.dat(ANY.Steel), 'P', OP.plateQuadruple.dat(ANY.Steel));
		
		BooksGT.BOOK_REGISTER.put(IL.Shape_Press_Bullet_Casing_Small , (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Press_Bullet_Casing_Medium, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Press_Bullet_Casing_Large , (byte)45);
		
		
		
		IL.Shape_Slicer_Empty              .set(addItem(tLastID = 10900, "Рамка лезвия ломтерезки"           , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		
		CR.shaped(IL.Shape_Slicer_Empty.get(1), CR.DEF_REV, " R ", "RhR", " R ", 'R', OP.stick.dat(MT.StainlessSteel));
		
		IL.Shape_Slicer_Flat               .set(addItem(tLastID = 10901, "Лезвие ломтерезки (Плоское)"       , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Grid               .set(addItem(tLastID = 10902, "Лезвие ломтерезки (Решетка)"       , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Eigths             .set(addItem(tLastID = 10903, "Лезвие ломтерезки (Восмерка)"      , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Eigths_Hollow      .set(addItem(tLastID = 10904, "Лезвие ломтерезки (Полая восмерка)", "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Split              .set(addItem(tLastID = 10905, "Лезвие ломтерезки (Половинка)"     , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Quarters           .set(addItem(tLastID = 10906, "Лезвие ломтерезки (Четверть)"      , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Quarters_Hollow    .set(addItem(tLastID = 10907, "Лезвие ломтерезки (Полая четверть)", "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		
		CR.shaped(IL.Shape_Slicer_Flat              .get(1), CR.DEF_REV, "B f", "BO ", "B s", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Grid              .get(1), CR.DEF_REV, " Bf", "BOB", " Bs", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Eigths            .get(1), CR.DEF_REV, "B B", "s f", "BOB", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Eigths_Hollow     .get(1), CR.DEF_REV, "B B", "sRf", "BOB", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel), 'R', OP.ring.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Split             .get(1), CR.DEF_REV, " Of", "BBB", "  s", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Quarters          .get(1), CR.DEF_REV, "fB ", "B s", " O ", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Quarters_Hollow   .get(1), CR.DEF_REV, "fB ", "BRs", " O ", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel), 'R', OP.ring.dat(MT.StainlessSteel));
		
		BooksGT.BOOK_REGISTER.put(IL.Shape_Slicer_Empty, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Slicer_Flat, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Slicer_Grid, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Slicer_Eigths, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Slicer_Eigths_Hollow, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Slicer_Split, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Slicer_Quarters, (byte)45);
		BooksGT.BOOK_REGISTER.put(IL.Shape_Slicer_Quarters_Hollow, (byte)45);
		
		
		IL.Comp_Laser_Gas_Empty            .set(addItem(tLastID = 11000, "Пустой газовый лазерный излучатель"        , "Для электрических лазеров"                        , TC.stack(TC.LUX, 1), TC.stack(TC.VACUOS, 2)));
		CR.shaped(IL.Comp_Laser_Gas_Empty.get(1), CR.DEF_REV_NCC, "CWM", "WGx", "MTd", 'W', MT.DATA.CABLES_01[2], 'C', OD_CIRCUITS[2], 'M', OP.plate.dat(MT.Ag), 'T', OP.screw.dat(MT.StainlessSteel), 'G', OD.blockGlassColorless);
		
		IL.Comp_Laser_Gas_He               .set(addItem(tLastID = 11001, "Гелиевый лазерный излучатель"              , "Назначение: слабые оптические приборы"            , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_Ne               .set(addItem(tLastID = 11002, "Неоновый лазерный излучатель"              , "Назначение: слабые оптические приборы"            , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_Ar               .set(addItem(tLastID = 11003, "Аргоновый лазерный излучатель"             , "Назначение: сильные оптические приборы"           , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_Kr               .set(addItem(tLastID = 11004, "Криптонитовый лазерный излучатель"         , "Назначение: Наука"                                , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_Xe               .set(addItem(tLastID = 11005, "Ксеноновый лазерный излучатель"            , "Назначение: Наука"                                , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_HeNe             .set(addItem(tLastID = 11006, "Гелий-неоновый лазерный излучатель"        , "Назначение: Слабые оптические приборы"            , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_CO               .set(addItem(tLastID = 11007, "Лазерный излучатель на монооксиде углерода", "Назначение: Обработка слабых материалов"          , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_CO2              .set(addItem(tLastID = 11008, "Лазерный излучатель на диоксиде углерода"  , "Назначение: Обработка сильных материалов"         , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.He      .gas(U, T), NF, IL.Comp_Laser_Gas_He.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.Ne      .gas(U, T), NF, IL.Comp_Laser_Gas_Ne.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.Ar      .gas(U, T), NF, IL.Comp_Laser_Gas_Ar.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.Kr      .gas(U, T), NF, IL.Comp_Laser_Gas_Kr.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.Xe      .gas(U, T), NF, IL.Comp_Laser_Gas_Xe.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.HeNe    .gas(U, T), NF, IL.Comp_Laser_Gas_HeNe.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.CO      .gas(U, T), NF, IL.Comp_Laser_Gas_CO.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.CO2     .gas(U, T), NF, IL.Comp_Laser_Gas_CO2.get(1));
		
		CR.shaped(IL.MOTORS[0].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.bolt     .dat(MT.IronMagnetic)       , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[0]), 'R', OP.stick.dat(MT.DATA.Electric_T[0]), 'W', OP.wireFine.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[0]);
		CR.shaped(IL.MOTORS[0].get(1), CR.DEF       , "CWR", "WIW", "PWC", 'I', OP.bolt     .dat(MT.SteelMagnetic)      , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[0]), 'R', OP.stick.dat(MT.DATA.Electric_T[0]), 'W', OP.wireFine.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[0]);
		CR.shaped(IL.MOTORS[1].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.IronMagnetic)       , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[1]), 'R', OP.stick.dat(MT.DATA.Electric_T[1]), 'W', OP.wireGt01.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[1]);
		CR.shaped(IL.MOTORS[1].get(1), CR.DEF       , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.SteelMagnetic)      , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[1]), 'R', OP.stick.dat(MT.DATA.Electric_T[1]), 'W', OP.wireGt01.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[1]);
		CR.shaped(IL.MOTORS[2].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.SteelMagnetic)      , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[2]), 'R', OP.stick.dat(MT.DATA.Electric_T[2]), 'W', OP.wireGt02.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[2]);
		CR.shaped(IL.MOTORS[3].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.SteelMagnetic)      , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[3]), 'R', OP.stick.dat(MT.DATA.Electric_T[3]), 'W', OP.wireGt03.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[3]);
		CR.shaped(IL.MOTORS[4].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[4]), 'R', OP.stick.dat(MT.DATA.Electric_T[4]), 'W', OP.wireGt04.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[4]);
		CR.shaped(IL.MOTORS[5].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[5]), 'R', OP.stick.dat(MT.DATA.Electric_T[5]), 'W', OP.wireGt05.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[5]);
		CR.shaped(IL.MOTORS[6].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stickLong.dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[6]), 'R', OP.stick.dat(MT.DATA.Electric_T[6]), 'W', OP.wireGt06.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[6]);
		CR.shaped(IL.MOTORS[7].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stickLong.dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[7]), 'R', OP.stick.dat(MT.DATA.Electric_T[7]), 'W', OP.wireGt07.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[7]);
		CR.shaped(IL.MOTORS[8].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stickLong.dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[8]), 'R', OP.stick.dat(MT.DATA.Electric_T[8]), 'W', OP.wireGt08.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[8]);
		CR.shaped(IL.MOTORS[9].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stickLong.dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[9]), 'R', OP.stick.dat(MT.DATA.Electric_T[9]), 'W', OP.wireGt09.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[9]);
		
		for (int i = 0; i < 10; i++) {
		CR.shaped(IL.PUMPS      [i].get(1), CR.DEF_REV, "TXO", "dPw", "OMT", 'M', IL.MOTORS[i], 'O', OP.ring.dat(ANY.Rubber), 'X', OP.rotor.dat(MT.DATA.Electric_T[i]), 'T', OP.screw.dat(MT.DATA.Electric_T[i]), 'P', OP.plateCurved.dat(MT.DATA.Electric_T[i]));
		CR.shaped(IL.CONVEYERS  [i].get(1), CR.DEF_REV, "RRR", "MCM", "RRR", 'M', IL.MOTORS[i], 'C', MT.DATA.CABLES_01[i], 'R', OP.plate.dat(ANY.Rubber));
		CR.shaped(IL.PISTONS    [i].get(1), CR.DEF_REV, "TPP", "dSS", "TMG", 'M', IL.MOTORS[i], 'P', OP.plate.dat(MT.DATA.Electric_T[i]), 'S', OP.stick.dat(MT.DATA.Electric_T[i]), 'G', OP.gearGtSmall.dat(MT.DATA.Electric_T[i]), 'T', OP.screw.dat(MT.DATA.Electric_T[i]));
		CR.shaped(IL.ROBOT_ARMS [i].get(1), CR.DEF_REV, "CCC", "MSM", "PES", 'M', IL.MOTORS[i], 'C', MT.DATA.CABLES_01[i], 'E', OD_CIRCUITS[i], 'S', OP.stick.dat(MT.DATA.Electric_T[i]), 'P', IL.PISTONS[i]);
		}
		
		CR.shaped(IL.FIELD_GENERATORS[0].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.EnderPearl ), 'C', OD_CIRCUITS[0], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[0]), 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[1].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.EnderPearl ), 'C', OD_CIRCUITS[1], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[1]), 'W', OP.wireGt01.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[2].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.EnderEye   ), 'C', OD_CIRCUITS[2], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[2]), 'W', OP.wireGt02.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[3].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.EnderEye   ), 'C', OD_CIRCUITS[3], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[3]), 'W', OP.wireGt04.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[4].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[4], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[4]), 'W', OP.wireGt06.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[5].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[5], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[5]), 'W', OP.wireGt08.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[6].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[6], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[6]), 'W', OP.wireGt10.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[7].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[7], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[7]), 'W', OP.wireGt12.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[8].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[8], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[8]), 'W', OP.wireGt14.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[9].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[9], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[9]), 'W', OP.wireGt16.dat(MT.Os));
		
		CR.shaped(IL.EMITTERS[0].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(ANY.SiO2)         , 'S', MT.DATA.WIRES_04[0], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[0]), 'C', OD_CIRCUITS[0], 'W', MT.DATA.CABLES_01[0]);
		CR.shaped(IL.EMITTERS[1].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(ANY.SiO2)         , 'S', MT.DATA.WIRES_04[1], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[1]), 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1]);
		CR.shaped(IL.EMITTERS[2].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(ANY.SiO2)         , 'S', MT.DATA.WIRES_04[2], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[2]), 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[2]);
		CR.shaped(IL.EMITTERS[3].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(ANY.Emerald)      , 'S', MT.DATA.WIRES_04[3], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[3]), 'C', OD_CIRCUITS[3], 'W', MT.DATA.CABLES_01[3]);
		CR.shaped(IL.EMITTERS[4].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.EnderPearl)    , 'S', MT.DATA.WIRES_04[4], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[4]), 'C', OD_CIRCUITS[4], 'W', MT.DATA.CABLES_01[4]);
		CR.shaped(IL.EMITTERS[5].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.EnderEye)      , 'S', MT.DATA.WIRES_04[5], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[5]), 'C', OD_CIRCUITS[5], 'W', MT.DATA.CABLES_01[5]);
		CR.shaped(IL.EMITTERS[6].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.NetherStar)    , 'S', MT.DATA.WIRES_04[6], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[6]), 'C', OD_CIRCUITS[6], 'W', MT.DATA.CABLES_01[6]);
		CR.shaped(IL.EMITTERS[7].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.NetherStar)    , 'S', MT.DATA.WIRES_04[7], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[7]), 'C', OD_CIRCUITS[7], 'W', MT.DATA.CABLES_01[7]);
		CR.shaped(IL.EMITTERS[8].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.NetherStar)    , 'S', MT.DATA.WIRES_04[8], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[8]), 'C', OD_CIRCUITS[8], 'W', MT.DATA.CABLES_01[8]);
		CR.shaped(IL.EMITTERS[9].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.NetherStar)    , 'S', MT.DATA.WIRES_04[9], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[9]), 'C', OD_CIRCUITS[9], 'W', MT.DATA.CABLES_01[9]);
		
		CR.shaped(IL.SENSORS[0].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(ANY.SiO2)          , 'S', MT.DATA.WIRES_01[0], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[0]), 'C', OD_CIRCUITS[0]);
		CR.shaped(IL.SENSORS[1].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(ANY.SiO2)          , 'S', MT.DATA.WIRES_01[1], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[1]), 'C', OD_CIRCUITS[1]);
		CR.shaped(IL.SENSORS[2].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(ANY.SiO2)          , 'S', MT.DATA.WIRES_01[2], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[2]), 'C', OD_CIRCUITS[2]);
		CR.shaped(IL.SENSORS[3].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(ANY.Emerald)       , 'S', MT.DATA.WIRES_01[3], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[3]), 'C', OD_CIRCUITS[3]);
		CR.shaped(IL.SENSORS[4].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.EnderPearl)     , 'S', MT.DATA.WIRES_01[4], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[4]), 'C', OD_CIRCUITS[4]);
		CR.shaped(IL.SENSORS[5].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.EnderEye)       , 'S', MT.DATA.WIRES_01[5], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[5]), 'C', OD_CIRCUITS[5]);
		CR.shaped(IL.SENSORS[6].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.NetherStar)     , 'S', MT.DATA.WIRES_01[6], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[6]), 'C', OD_CIRCUITS[6]);
		CR.shaped(IL.SENSORS[7].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.NetherStar)     , 'S', MT.DATA.WIRES_01[7], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[7]), 'C', OD_CIRCUITS[7]);
		CR.shaped(IL.SENSORS[8].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.NetherStar)     , 'S', MT.DATA.WIRES_01[8], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[8]), 'C', OD_CIRCUITS[8]);
		CR.shaped(IL.SENSORS[9].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.NetherStar)     , 'S', MT.DATA.WIRES_01[9], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[9]), 'C', OD_CIRCUITS[9]);
		
		
		
		
		IL.Battery_Lead_Acid_Cell_Empty    .set(addItem(tLastID = 20000, "Свинцово-кислотная капсула (Пусто)"    , "Часть аккумулятора (Наполнитель не требуется!)" , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.METALLUM, 2), TC.stack(TC.VACUOS , 2), new OreDictItemData(MT.Pb, U, MT.BatteryAlloy, U)));
		IL.Battery_Lead_Acid_Cell_Filled   .set(addItem(tLastID = 20001, "Свинцово-кислотная капсула (Заполнено)", "Часть аккумулятора"                             , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.ELECTRUM, 2), TC.stack(TC.VENEMUM, 2), new OreDictItemData(MT.Pb, U, MT.BatteryAlloy, U, MT.H2SO4, 2*U), new FluidContainerData(MT.H2SO4.liquid(2*U, T), ST.make(this, 1, 20001), ST.make(this, 1, 20000), F)));
		ItemsGT.addNEIRedirects(IL.Battery_Lead_Acid_Cell_Empty.get(1), IL.Battery_Lead_Acid_Cell_Filled.get(1));
		CR.shaped(IL.Battery_Lead_Acid_Cell_Empty.get(1), CR.DEF_NCC, " Fh", "FPF", "xF ", 'P', OP.plateCurved.dat(MT.BatteryAlloy), 'F', OP.foil.dat(MT.Pb));
		
		IL.Battery_Alkaline_Cell_Empty     .set(addItem(tLastID = 20002, "Щелочная кнопочная капсула (Пусто)"    , "Часть аккумулятора (Наполнитель не требуется!)" , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.METALLUM, 2), TC.stack(TC.VACUOS , 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Al, U4), OM.stack(MT.StainlessSteel, U), OM.stack(ANY.Plastic, U4), OM.stack(ANY.Iron, U2), OM.stack(ANY.C, U), OM.stack(MT.KOH, U), OM.stack(MT.Zn, U), OM.stack(MT.MnO2, U))));
		IL.Battery_Alkaline_Cell_Filled    .set(addItem(tLastID = 20003, "Щелочная кнопочная капсула (Заполнено)", "Часть аккумулятора"                             , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.ELECTRUM, 2), TC.stack(TC.VENEMUM, 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Al, U4), OM.stack(MT.StainlessSteel, U), OM.stack(ANY.Plastic, U4), OM.stack(ANY.Iron, U2), OM.stack(ANY.C, U), OM.stack(MT.KOH, U), OM.stack(MT.Zn, U), OM.stack(MT.MnO2, U), OM.stack(MT.H2O, U)), new FluidContainerData(FL.DistW.make(1000), ST.make(this, 1, tLastID), ST.make(this, 1, tLastID-1), F)));
		ItemsGT.addNEIRedirects(IL.Battery_Alkaline_Cell_Empty.get(1), IL.Battery_Alkaline_Cell_Filled.get(1));
		CR.shaped(IL.Battery_Alkaline_Cell_Empty.get(1), CR.DEF_NCC, "KSM", "OPF", "CWZ", 'P', OP.plateCurved.dat(MT.BatteryAlloy), 'F', OP.foil.dat(MT.Al), 'S', OP.plateCurved.dat(MT.StainlessSteel), 'O', OP.ring.dat(ANY.Plastic), 'W', OP.wireGt01.dat(ANY.Iron), 'C', OP.dust.dat(ANY.C), 'K', OP.dust.dat(MT.KOH), 'Z', OP.dust.dat(MT.Zn), 'M', OP.dust.dat(MT.MnO2));
		
		IL.Battery_NiCd_Cell_Empty         .set(addItem(tLastID = 20004, "Никель-кадмиевая капсула (Пусто)"      , "Часть аккумулятора (Наполнитель не требуется!)" , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.METALLUM, 2), TC.stack(TC.VACUOS , 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Al, U4), OM.stack(MT.StainlessSteel, U), OM.stack(ANY.Plastic, U4), OM.stack(ANY.Iron, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.KOH, U), OM.stack(MT.Ni, U), OM.stack(MT.Cd, U))));
		IL.Battery_NiCd_Cell_Filled        .set(addItem(tLastID = 20005, "Никель-кадмиевая капсула (Заполнено)"  , "Часть аккумулятора"                             , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.ELECTRUM, 2), TC.stack(TC.VENEMUM, 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Al, U4), OM.stack(MT.StainlessSteel, U), OM.stack(ANY.Plastic, U4), OM.stack(ANY.Iron, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.KOH, U), OM.stack(MT.Ni, U), OM.stack(MT.Cd, U), OM.stack(MT.H2O, U)), new FluidContainerData(FL.DistW.make(1000), ST.make(this, 1, tLastID), ST.make(this, 1, tLastID-1), F)));
		ItemsGT.addNEIRedirects(IL.Battery_NiCd_Cell_Empty.get(1), IL.Battery_NiCd_Cell_Filled.get(1));
		CR.shaped(IL.Battery_NiCd_Cell_Empty.get(1), CR.DEF_NCC, "KSM", "OPF", "CWZ", 'P', OP.plateCurved.dat(MT.BatteryAlloy), 'F', OP.foil.dat(MT.Al), 'S', OP.plateCurved.dat(MT.StainlessSteel), 'O', OP.ring.dat(ANY.Plastic), 'W', OP.wireGt01.dat(ANY.Iron), 'C', OP.stick.dat(MT.Graphite), 'K', OP.dust.dat(MT.KOH), 'Z', OP.plateCurved.dat(MT.Ni), 'M', OP.plateCurved.dat(MT.Cd));
		
		IL.Battery_LiCoO2_Cell_Empty       .set(addItem(tLastID = 20006, "Литий-кобольтовая капсула (Пусто)"     , "Часть аккумулятора (Наполнитель не требуется!)" , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.METALLUM, 2), TC.stack(TC.VACUOS , 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Co, U2), OM.stack(MT.Cr, U), OM.stack(ANY.Plastic, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.LiClO4, U*2))));
		IL.Battery_LiCoO2_Cell_Filled      .set(addItem(tLastID = 20007, "Литий-кобольтовая капсула (Заполнено)" , "Часть аккумулятора"                             , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.ELECTRUM, 2), TC.stack(TC.VENEMUM, 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Co, U2), OM.stack(MT.Cr, U), OM.stack(ANY.Plastic, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.LiClO4, U*2), OM.stack(MT.HCl, U*2)), new FluidContainerData(MT.HCl.gas(U*2, T), ST.make(this, 1, tLastID), ST.make(this, 1, tLastID-1), F)));
		ItemsGT.addNEIRedirects(IL.Battery_LiCoO2_Cell_Empty.get(1), IL.Battery_LiCoO2_Cell_Filled.get(1));
		CR.shaped(IL.Battery_LiCoO2_Cell_Empty.get(1), CR.DEF_NCC, "CLF", "XSG", "FLP", 'P', OP.plateCurved.dat(MT.BatteryAlloy), 'X', OP.stick.dat(MT.Co), 'G', OP.stick.dat(MT.Graphite), 'L', OP.dust.dat(MT.LiClO4), 'S', OP.plateCurved.dat(MT.Cr), 'F', OP.foil.dat(ANY.Plastic), 'C', OD_CIRCUITS[4]);
		
		IL.Battery_LiMn_Cell_Empty         .set(addItem(tLastID = 20008, "Литий-марганцевая капсула (Пусто)"     , "Часть аккумулятора (Наполнитель не требуется!)" , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.METALLUM, 2), TC.stack(TC.VACUOS , 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Mn, U2), OM.stack(MT.Cr, U), OM.stack(ANY.Plastic, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.LiClO4, U*2))));
		IL.Battery_LiMn_Cell_Filled        .set(addItem(tLastID = 20009, "Литий-марганцевая капсула (Заполнено)" , "Часть аккумулятора"                             , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.ELECTRUM, 2), TC.stack(TC.VENEMUM, 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Mn, U2), OM.stack(MT.Cr, U), OM.stack(ANY.Plastic, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.LiClO4, U*2), OM.stack(MT.HF, U*2)), new FluidContainerData(MT.HF.gas(U*2, T), ST.make(this, 1, tLastID), ST.make(this, 1, tLastID-1), F)));
		ItemsGT.addNEIRedirects(IL.Battery_LiMn_Cell_Empty.get(1), IL.Battery_LiMn_Cell_Filled.get(1));
		CR.shaped(IL.Battery_LiMn_Cell_Empty.get(1), CR.DEF_NCC, "CLF", "XSG", "FLP", 'P', OP.plateCurved.dat(MT.BatteryAlloy), 'X', OP.stick.dat(MT.Mn), 'G', OP.stick.dat(MT.Graphite), 'L', OP.dust.dat(MT.LiClO4), 'S', OP.plateCurved.dat(MT.Cr), 'F', OP.foil.dat(ANY.Plastic), 'C', OD_CIRCUITS[6]);
		
		
		
		IL.Electrode_FR_Copper              .set(addItem(tLastID = 29987, "Электрод (Медь)"                , "Требуется пробирка"                                , new OreDictItemData(ANY.Cu            , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Tin                 .set(addItem(tLastID = 29988, "Электрод (Олово)"               , "Требуется пробирка"                                , new OreDictItemData(MT.Sn             , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Bronze              .set(addItem(tLastID = 29989, "Электрод (Бронза)"              , "Требуется пробирка"                                , new OreDictItemData(MT.Bronze         , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Iron                .set(addItem(tLastID = 29990, "Электрод (Железо)"              , "Требуется пробирка"                                , new OreDictItemData(ANY.Fe            , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Gold                .set(addItem(tLastID = 29991, "Электрод (Золото)"              , "Требуется пробирка"                                , new OreDictItemData(MT.Au             , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Diamond             .set(addItem(tLastID = 29992, "Электрод (Алмаз)"               , "Требуется пробирка"                                , new OreDictItemData(ANY.Diamond       , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Obsidian            .set(addItem(tLastID = 29993, "Электрод (Обсидиан)"            , "Требуется пробирка"                                , new OreDictItemData(MT.Obsidian       , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Blaze               .set(addItem(tLastID = 29994, "Электрод (Огненный порошок)"    , "Требуется пробирка"                                , new OreDictItemData(MT.Blaze          , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Rubber              .set(addItem(tLastID = 29995, "Электрод (Резина)"              , "Требуется пробирка"                                , new OreDictItemData(ANY.Rubber        , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Emerald             .set(addItem(tLastID = 29996, "Электрод (Изумруд)"             , "Требуется пробирка"                                , new OreDictItemData(ANY.Emerald       , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Apatite             .set(addItem(tLastID = 29997, "Электрод (Апатит)"              , "Требуется пробирка"                                , new OreDictItemData(MT.Apatite        , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Lapis               .set(addItem(tLastID = 29998, "Электрод (Лазурит)"             , "Требуется пробирка"                                , new OreDictItemData(MT.Lapis          , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Ender               .set(addItem(tLastID = 29999, "Электрод (Ender)"               , "Требуется пробирка"                                , new OreDictItemData(MT.Endstone       , 5*U4, MT.EnderEye, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		
		for (OreDictMaterial tMat : ANY.Cu.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 2), OP.bolt.mat(tMat       , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Copper     .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Sn          , 2), OP.bolt.mat(MT.Sn      , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Tin        .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Bronze      , 2), OP.bolt.mat(MT.Bronze  , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Bronze     .get(1));
		for (OreDictMaterial tMat : ANY.Iron.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 2), OP.bolt.mat(tMat       , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Iron       .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Au          , 2), OP.bolt.mat(MT.Au      , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Gold       .get(1));
		for (OreDictMaterial tMat : ANY.Diamond.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 2), OP.bolt.mat(tMat       , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Diamond    .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Obsidian    , 2), OP.bolt.mat(MT.Obsidian, 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Obsidian   .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Blaze       , 2), OP.bolt.mat(MT.Blaze   , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Blaze      .get(1));
		for (OreDictMaterial tMat : ANY.Rubber.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 2), OP.bolt.mat(tMat       , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Rubber     .get(1));
		for (OreDictMaterial tMat : ANY.Emerald.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 2), OP.bolt.mat(tMat       , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Emerald    .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Apatite     , 2), OP.bolt.mat(MT.Apatite , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Apatite    .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Lapis       , 2), OP.bolt.mat(MT.Lapis   , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Lapis      .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.dustSmall.mat(MT.Endstone, 5)                             , OP.dustSmall.mat(MT.EnderEye, 2)), IL.Electrode_FR_Ender      .get(1));
		
		for (OreDictMaterial tMat : ANY.Cu.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 4), OP.bolt.mat(tMat       , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Copper      .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Sn          , 4), OP.bolt.mat(MT.Sn      , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Tin         .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Bronze      , 4), OP.bolt.mat(MT.Bronze  , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Bronze      .get(2));
		for (OreDictMaterial tMat : ANY.Iron.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 4), OP.bolt.mat(tMat       , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Iron        .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Au          , 4), OP.bolt.mat(MT.Au      , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Gold        .get(2));
		for (OreDictMaterial tMat : ANY.Diamond.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 4), OP.bolt.mat(tMat       , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Diamond     .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Obsidian    , 4), OP.bolt.mat(MT.Obsidian, 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Obsidian    .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Blaze       , 4), OP.bolt.mat(MT.Blaze   , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Blaze       .get(2));
		for (OreDictMaterial tMat : ANY.Rubber.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 4), OP.bolt.mat(tMat       , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Rubber      .get(2));
		for (OreDictMaterial tMat : ANY.Emerald.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 4), OP.bolt.mat(tMat       , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Emerald     .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Apatite     , 4), OP.bolt.mat(MT.Apatite , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Apatite     .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Lapis       , 4), OP.bolt.mat(MT.Lapis   , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Lapis       .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.dustSmall.mat(MT.Endstone,10)                             , OP.dust     .mat(MT.EnderEye, 1)), IL.Electrode_FR_Ender       .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.dustSmall.mat(MT.Endstone,10)                             , OP.gem      .mat(MT.EnderEye, 1)), IL.Electrode_FR_Ender       .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.dust.mat(MT.Endstone , 5)                                 , OP.dust     .mat(MT.EnderEye, 2)), IL.Electrode_FR_Ender       .get(4));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.dust.mat(MT.Endstone , 5)                                 , OP.gem      .mat(MT.EnderEye, 2)), IL.Electrode_FR_Ender       .get(4));
		RM.Press.addRecipeX(T, 16, 64, ST.array(ST.make(Blocks.end_stone, 5, W)                              , OP.dust     .mat(MT.EnderEye, 2)), IL.Electrode_FR_Ender       .get(4));
		RM.Press.addRecipeX(T, 16, 64, ST.array(ST.make(Blocks.end_stone, 5, W)                              , OP.gem      .mat(MT.EnderEye, 2)), IL.Electrode_FR_Ender       .get(4));
		
		
		IL.Circuit_Plate_Empty             .set(addItem(tLastID = 30000, "Текстолит"                          , "Требуется контурная проводка"                    , new OreDictItemData(ANY.SiO2, U, ANY.Plastic, U), TC.stack(TC.FABRICO, 1)));
		
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
			ItemStack tDust = OP.dust.mat(tMat, 1);
			if (ST.valid(tDust)) for (OreDictMaterial tMt2 : ANY.Plastic.mToThis) {
				RM.Press.addRecipe2(T, F, F, F, T, 16, 64, OP.plate.mat(tMt2, 1), tDust, IL.Circuit_Plate_Empty.get(1));
			}
		}
		
		IL.Circuit_Wire_Copper             .set(addItem(tLastID = 30001, "Контурная проводка (Медь)"          , "Необходимо разместить на пустом текстолите"      , new OreDictItemData(MT.Cu, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.PERMUTATIO, 1)));
		IL.Circuit_Plate_Copper            .set(addItem(tLastID = 30002, "Текстолит (Медь)"                   , "Требуется контурный компонент"                   , new OreDictItemData(MT.Cu, U, ANY.SiO2, U, ANY.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.PERMUTATIO, 1)));
		IL.Circuit_Wire_Gold               .set(addItem(tLastID = 30003, "Контурная проводка (Золото)"        , "Необходимо разместить на пустом текстолите"      , new OreDictItemData(MT.Au, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.LUCRUM, 1)));
		IL.Circuit_Plate_Gold              .set(addItem(tLastID = 30004, "Текстолит (Золото)"                 , "Требуется контурный компонент"                   , new OreDictItemData(MT.Au, U, ANY.SiO2, U, ANY.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.LUCRUM, 1), "oc:materialCircuitBoardPrinted"));
		IL.Circuit_Wire_Platinum           .set(addItem(tLastID = 30005, "Контурная проводка (Платина)"       , "Необходимо разместить на пустом текстолите"      , new OreDictItemData(MT.Pt, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.NEBRISUM, 1)));
		IL.Circuit_Plate_Platinum          .set(addItem(tLastID = 30006, "Текстолит (Платина)"                , "Требуется контурный компонент"                   , new OreDictItemData(MT.Pt, U, ANY.SiO2, U, ANY.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.NEBRISUM, 1)));
		
		IL.Circuit_Wire_Magic              .set(addItem(tLastID = 30011, "Контурная проводка (Магия)"         , "Необходимо разместить на пустом текстолите"      , TC.stack(TC.FABRICO, 1), TC.stack(TC.PRAECANTIO, 1)));
		IL.Circuit_Plate_Magic             .set(addItem(tLastID = 30012, "Текстолит (Магия)"                  , "Требуется контурный компонент"                   , new OreDictItemData(ANY.SiO2, U, ANY.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.PRAECANTIO, 1)));
		IL.Circuit_Wire_Enderium           .set(addItem(tLastID = 30013, "Контурная проводка (Эндерий)"       , "Необходимо разместить на пустом текстолите"      , new OreDictItemData(MT.Enderium, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.ALIENIS, 1)));
		IL.Circuit_Plate_Enderium          .set(addItem(tLastID = 30014, "Текстолит (Эндерий)"                , "Требуется контурный компонент"                   , new OreDictItemData(MT.Enderium, U, ANY.SiO2, U, ANY.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.ALIENIS, 1)));
		IL.Circuit_Wire_Signalum           .set(addItem(tLastID = 30015, "Контурная проводка (Сигналий)"      , "Необходимо разместить на пустом текстолите"      , new OreDictItemData(MT.Signalum, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.POTENTIA, 1)));
		IL.Circuit_Plate_Signalum          .set(addItem(tLastID = 30016, "Текстолит (Сигналий)"               , "Требуется контурный компонент"                   , new OreDictItemData(MT.Signalum, U, ANY.SiO2, U, ANY.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.POTENTIA, 1)));
		
		IL.Circuit_Plate_HSLA              .set(addItem(tLastID = 30099, "Текстолит (HSLA)"                   , "Требуется контурный компонент"                   , new OreDictItemData(MT.HSLA, U, MT.Au, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.MACHINA, 1), MD.RoC.mLoaded ? null : TD.Creative.HIDDEN));
		
		CR.shaped(IL.Circuit_Wire_Copper        .get(1), CR.DEF, "WWW", "WxW", "WWW", 'W', OP.wireFine.dat(ANY.Cu));
		
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Copper    .get(1), IL.Circuit_Plate_Copper    .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Gold      .get(1), IL.Circuit_Plate_Gold      .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Platinum  .get(1), IL.Circuit_Plate_Platinum  .get(1));

		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Magic     .get(1), IL.Circuit_Plate_Magic     .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Enderium  .get(1), IL.Circuit_Plate_Enderium  .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Signalum  .get(1), IL.Circuit_Plate_Signalum  .get(1));
		
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, OP.plate.mat(MT.HSLA, 1), IL.Circuit_Wire_Gold.get(1), IL.Circuit_Plate_HSLA.get(1));
		
		IL.Circuit_Part_Basic              .set(addItem(tLastID = 30101, "Контурный компнент (Базовая)"          , "Необходимо разместить на медном текстолите"     , TC.stack(TC.COGNITIO, 1)));
		IL.Circuit_Part_Good               .set(addItem(tLastID = 30102, "Контурный компнент (Хорошая)"          , "Необходимо разместить на медном текстолите"     , TC.stack(TC.COGNITIO, 1)));
		IL.Circuit_Part_Advanced           .set(addItem(tLastID = 30103, "Контурный компнент (Продвинутая)"      , "Необходимо разместить на золотом текстолите"    , TC.stack(TC.COGNITIO, 1)));
		IL.Circuit_Part_Elite              .set(addItem(tLastID = 30104, "Контурный компнент (Элитная)"          , "Необходимо разместить на золотом текстолите"    , TC.stack(TC.COGNITIO, 1)));
		IL.Circuit_Part_Master             .set(addItem(tLastID = 30105, "Контурный компнент (Мастер)"           , "Необходимо разместить на платиновом текстолите" , TC.stack(TC.COGNITIO, 1)));
		IL.Circuit_Part_Ultimate           .set(addItem(tLastID = 30106, "Контурный компнент (Максимальная)"     , "Необходимо разместить на платиновом текстолите" , TC.stack(TC.COGNITIO, 1)));
		
		IL.Circuit_Part_Magic              .set(addItem(tLastID = 30111, "Контурный компнент (Магическая)"       , "Необходимо разместить на магическом текстолите" , TC.stack(TC.PRAECANTIO, 1)));
		IL.Circuit_Part_Enderium           .set(addItem(tLastID = 30113, "Контурный компнент (Эндерий)"          , "Необходимо разместить на эндериевом текстолите" , TC.stack(TC.ALIENIS, 1)));
		IL.Circuit_Part_Signalum           .set(addItem(tLastID = 30115, "Контурный компнент (Сигналий)"         , "Необходимо разместить на сигналиевом текстолите", TC.stack(TC.POTENTIA, 1)));
		
		IL.Circuit_Part_EnderPearl         .set(addItem(tLastID = 30198, "Контурный компнент (Жемчуг Края)"      , "Необходимо разместить на текстолите"            , TC.stack(TC.ALIENIS, 1)));
		IL.Circuit_Part_EnderEye           .set(addItem(tLastID = 30199, "Контурный компнент (Око Края)"         , "Необходимо разместить на текстолите"            , TC.stack(TC.ALIENIS, 1)));
		
		for (OreDictMaterial tMat : ANY.Cu.mToThis) {
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 9)), IL.Circuit_Part_Basic        .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 9)), IL.Circuit_Part_Basic        .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 9)), IL.Circuit_Part_Basic        .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Basic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Basic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Basic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 1), OP.wireFine.mat(MT.RedAlloy, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 1), OP.wireFine.mat(MT.RedAlloy, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(tMat             , 1), OP.wireFine.mat(MT.RedAlloy, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Good         .get(1));
		}
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Au            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Advanced     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Au            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Advanced     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Au            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Elite        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Pt            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Master       .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Pt            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Master       .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Pt            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Ultimate     .get(1));
		
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Thaumium      , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Thaumium      , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Thaumium      , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Manasteel     , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Manasteel     , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Manasteel     , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Mithril       , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Mithril       , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Mithril       , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Netherite     , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Netherite     , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Netherite     , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Enderium      , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Enderium     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 9)), IL.Circuit_Part_Signalum     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 9)), IL.Circuit_Part_Signalum     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 9)), IL.Circuit_Part_Signalum     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Signalum     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Signalum     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Signalum     .get(1));
		
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 9)), IL.Circuit_Part_EnderPearl   .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 9)), IL.Circuit_Part_EnderPearl   .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 9)), IL.Circuit_Part_EnderPearl   .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 9)), IL.Circuit_Part_EnderEye     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 9)), IL.Circuit_Part_EnderEye     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 9)), IL.Circuit_Part_EnderEye     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_EnderPearl   .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_EnderPearl   .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_EnderPearl   .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_EnderEye     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_EnderEye     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_EnderEye     .get(1));
		
		IL.Circuit_Board_Basic             .set(addItem(tLastID = 30201, "Печатная плата (Базовая)"          , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Good              .set(addItem(tLastID = 30202, "Печатная плата (Хорошая)"          , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Advanced          .set(addItem(tLastID = 30203, "Печатная плата (Продвинутая)"      , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Elite             .set(addItem(tLastID = 30204, "Печатная плата (Элитная)"          , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Master            .set(addItem(tLastID = 30205, "Печатная плата (Мастер)"           , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Ultimate          .set(addItem(tLastID = 30206, "Печатная плата (Максимальная)"     , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1)));
		
		IL.Circuit_Board_Magic             .set(addItem(tLastID = 30211, "Печатная плата (Магическая)"       , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Enderium          .set(addItem(tLastID = 30213, "Печатная плата (Эндерий)"          , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Signalum          .set(addItem(tLastID = 30215, "Печатная плата (Сигналий)"         , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1)));
		
		IL.Circuit_Board_BC_Redstone       .set(addItem(tLastID = 30280, "Печатная плата (BC Кр.камень)"     , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U))));
		IL.Circuit_Board_BC_Iron           .set(addItem(tLastID = 30281, "Печатная плата (BC железо)"        , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Fe, 4*U))));
		IL.Circuit_Board_BC_Gold           .set(addItem(tLastID = 30282, "Печатная плата (BC золото)"        , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(MT.Au, 4*U))));
		IL.Circuit_Board_BC_Diamond        .set(addItem(tLastID = 30283, "Печатная плата (BC алмаз)"         , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Diamond, 4*U))));
		IL.Circuit_Board_BC_Ender          .set(addItem(tLastID = 30284, "Печатная плата (BC Ender)"         , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(MT.EnderPearl, 4*U))));
		IL.Circuit_Board_BC_Quartz         .set(addItem(tLastID = 30285, "Печатная плата (BC кварц)"         , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,5*U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U))));
		IL.Circuit_Board_BC_Comparator     .set(addItem(tLastID = 30286, "Печатная плата (BC компаратор)"    , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone,16*U))));
		IL.Circuit_Board_BC_Emerald        .set(addItem(tLastID = 30287, "Печатная плата (BC изумруд)"       , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Emerald, 4*U))));
		
		IL.Circuit_Board_HSLA_Circuit      .set(addItem(tLastID = 30298, "Печатная плата (HSLA схема)"       , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1), MD.RoC.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Circuit_Board_Power_Module      .set(addItem(tLastID = 30299, "Печатная плата (Силовой модуль)"   , "Необходимо правильно припаять"                   , TC.stack(TC.FABRICO, 1), MD.RoC.mLoaded ? null : TD.Creative.HIDDEN));
		
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Basic      .get(4), IL.Circuit_Board_Basic         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Good       .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Advanced   .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Elite      .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Master     .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Ultimate   .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Basic      .get(4), IL.Circuit_Board_Basic         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Good       .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Advanced   .get(4), IL.Circuit_Board_Advanced      .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Elite      .get(4), IL.Circuit_Board_Elite         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Master     .get(4), IL.Circuit_Board_Elite         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Ultimate   .get(4), IL.Circuit_Board_Elite         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Basic      .get(4), IL.Circuit_Board_Basic         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Good       .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Advanced   .get(4), IL.Circuit_Board_Advanced      .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Elite      .get(4), IL.Circuit_Board_Elite         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Master     .get(4), IL.Circuit_Board_Master        .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Ultimate   .get(4), IL.Circuit_Board_Ultimate      .get(1));
		
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Magic       .get(1), IL.Circuit_Part_Magic      .get(4), IL.Circuit_Board_Magic         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Enderium    .get(1), IL.Circuit_Part_Enderium   .get(4), IL.Circuit_Board_Enderium      .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Signalum    .get(1), IL.Circuit_Part_Signalum   .get(4), IL.Circuit_Board_Signalum      .get(1));
		
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_HSLA        .get(1), IL.Circuit_Part_EnderPearl .get(4), IL.Circuit_Board_HSLA_Circuit  .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_HSLA        .get(1), IL.Circuit_Part_EnderEye   .get(4), IL.Circuit_Board_Power_Module  .get(1));
		
		IL.Circuit_Basic                   .set(addItem(tLastID = 30301, "Электросхема T1 (Базовая)"     , "Очень медленно вычисляет простые данные"        , MT.DATA.CIRCUITS[1], OD_CIRCUITS[1], TC.stack(TC.COGNITIO, 2)));
		IL.Circuit_Good                    .set(addItem(tLastID = 30302, "Электросхема T2 (Хорошая)"     , "Медленно вычисляет простые данные"              , MT.DATA.CIRCUITS[2], OD_CIRCUITS[2], TC.stack(TC.COGNITIO, 3)));
		IL.Circuit_Advanced                .set(addItem(tLastID = 30303, "Электросхема T3 (Продвинутая)" , "Вычисляет простые данные со средней скоростью"  , MT.DATA.CIRCUITS[3], OD_CIRCUITS[3], TC.stack(TC.COGNITIO, 4)));
		IL.Circuit_Elite                   .set(addItem(tLastID = 30304, "Электросхема T4 (Элитная)"     , "Вычисляет простые данные с повышенной скоростью", MT.DATA.CIRCUITS[4], OD_CIRCUITS[4], TC.stack(TC.COGNITIO, 5)));
		IL.Circuit_Master                  .set(addItem(tLastID = 30305, "Электросхема T5 (Мастер)"      , "Эффективно вычисляет простые данные"            , MT.DATA.CIRCUITS[5], OD_CIRCUITS[5], TC.stack(TC.COGNITIO, 6)));
		IL.Circuit_Ultimate                .set(addItem(tLastID = 30306, "Электросхема T6 (Максимальная)", "Очень эффективно вычисляет простые данные"      , MT.DATA.CIRCUITS[6], OD_CIRCUITS[6], TC.stack(TC.COGNITIO, 7)));
		
		IL.Circuit_Magic                   .set(addItem(tLastID = 30311, "Электросхема (Магическая)"     , "Вычисляет простые магические данные"            , OP.circuit.dat(MT.Magic)));
		IL.Circuit_Enderium                .set(addItem(tLastID = 30313, "Электросхема (Эндерий)"        , "Вычисляет простые данные где-то еще"            , OP.circuit.dat(MT.Enderium)));
		IL.Circuit_Signalum                .set(addItem(tLastID = 30315, "Электросхема (Сигналий)"       , "Вычисляет простую логику"                       , OP.circuit.dat(MT.Signalum)));
		
		IL.Circuit_BC_Redstone             .set(addItem(tLastID = 30380, "Электросхема (BC Кр.камень)"   , "Создан для настройки рецептов"                  , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 1), TC.stack(TC.POTENTIA, 3), TC.stack(TC.MACHINA, 1)     , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U))));
		IL.Circuit_BC_Iron                 .set(addItem(tLastID = 30381, "Электросхема (BC железо)"      , "Создан для настройки рецептов"                  , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 2), TC.stack(TC.METALLUM, 4)                              , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Fe, 4*U))));
		IL.Circuit_BC_Gold                 .set(addItem(tLastID = 30382, "Электросхема (BC золото)"      , "Создан для настройки рецептов"                  , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 3), TC.stack(TC.LUCRUM, 2), TC.stack(TC.METALLUM, 2)      , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(MT.Au, 4*U))));
		IL.Circuit_BC_Diamond              .set(addItem(tLastID = 30383, "Электросхема (BC алмаз)"       , "Создан для настройки рецептов"                  , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 4), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2)       , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Diamond, 4*U))));
		IL.Circuit_BC_Ender                .set(addItem(tLastID = 30384, "Электросхема (BC Ender)"       , "Создан для настройки рецептов"                  , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 3), TC.stack(TC.ALIENIS, 4)                               , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(MT.EnderPearl, 4*U))));
		IL.Circuit_BC_Quartz               .set(addItem(tLastID = 30385, "Электросхема (BC кварц)"       , "Создан для настройки рецептов"                  , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 2), TC.stack(TC.POTENTIA, 2), TC.stack(TC.VITREUS, 2)     , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,5*U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U))));
		IL.Circuit_BC_Comparator           .set(addItem(tLastID = 30386, "Электросхема (BC компаратор)"  , "Создан для настройки рецептов"                  , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 2), TC.stack(TC.MACHINA, 4)                               , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone,16*U))));
		IL.Circuit_BC_Emerald              .set(addItem(tLastID = 30387, "Электросхема (BC изумруд)"     , "Создан для настройки рецептов"                  , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 4), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2)       , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(ANY.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Emerald, 4*U))));
		
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Basic         .get(1), MT.Pb              .liquid(U2, T), NF, IL.Circuit_Basic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Basic         .get(1), MT.Sn              .liquid(U2, T), NF, IL.Circuit_Basic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Basic         .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_Basic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Good          .get(1), MT.Pb              .liquid(U2, T), NF, IL.Circuit_Basic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Good          .get(1), MT.Sn              .liquid(U2, T), NF, IL.Circuit_Good          .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Good          .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_Good          .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Advanced      .get(1), MT.Pb              .liquid(U2, T), NF, IL.Circuit_Basic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Advanced      .get(1), MT.Sn              .liquid(U2, T), NF, IL.Circuit_Good          .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Advanced      .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_Advanced      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Elite         .get(1), MT.Pb              .liquid(U2, T), NF, IL.Circuit_Good          .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Elite         .get(1), MT.Sn              .liquid(U2, T), NF, IL.Circuit_Advanced      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Elite         .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_Elite         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Master        .get(1), MT.Pb              .liquid(U2, T), NF, IL.Circuit_Advanced      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Master        .get(1), MT.Sn              .liquid(U2, T), NF, IL.Circuit_Elite         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Master        .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_Master        .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Ultimate      .get(1), MT.Pb              .liquid(U2, T), NF, IL.Circuit_Elite         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Ultimate      .get(1), MT.Sn              .liquid(U2, T), NF, IL.Circuit_Master        .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Ultimate      .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_Ultimate      .get(1));
		
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Magic         .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_Magic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Enderium      .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_Enderium      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Signalum      .get(1), MT.Pb              .liquid(U2, T), NF, IL.Circuit_Signalum      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Signalum      .get(1), MT.Sn              .liquid(U2, T), NF, IL.Circuit_Signalum      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Signalum      .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_Signalum      .get(1));
		
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Redstone   .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_BC_Redstone   .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Iron       .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_BC_Iron       .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Gold       .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_BC_Gold       .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Diamond    .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_BC_Diamond    .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Ender      .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_BC_Ender      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Quartz     .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_BC_Quartz     .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Comparator .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_BC_Comparator .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Emerald    .get(1), MT.SolderingAlloy  .liquid(U2, T), NF, IL.Circuit_BC_Emerald    .get(1));
		
		IL.Circuit_Crystal_Diamond         .set(addItem(tLastID = 30401, "Кристаллическая схема (Алмаз)"       , "Логический алмаз"                     , TC.stack(TC.COGNITIO, 3), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(OM.stack(ANY.Diamond, U))));
		IL.Circuit_Crystal_Ruby            .set(addItem(tLastID = 30402, "Кристаллическая схема (Рубин)"       , "Управляющий рубин"                    , TC.stack(TC.COGNITIO, 3), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(OM.stack(MT.Ruby, U))));
		IL.Circuit_Crystal_Emerald         .set(addItem(tLastID = 30403, "Кристаллическая схема (Изумруд)"     , "Изумрудная память"                    , TC.stack(TC.COGNITIO, 3), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(OM.stack(ANY.Emerald, U))));
		IL.Circuit_Crystal_Sapphire        .set(addItem(tLastID = 30404, "Кристаллическая схема (Сапфир)"      , "Конвертирующий сапфир"                , TC.stack(TC.COGNITIO, 3), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(OM.stack(ANY.Sapphire, U))));
		
		IL.Processor_Crystal_Empty         .set(addItem(tLastID = 30500, "Сокет кристаллического процессора"   , "Основа для кристаллической схемы"     , TC.stack(TC.COGNITIO, 5), TC.stack(TC.VACUOS, 2)));
		IL.Processor_Crystal_Diamond       .set(addItem(tLastID = 30501, "Кристаллический процессор (Алмаз)"   , "Схема логического процессора"         , TC.stack(TC.COGNITIO, 5), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(MT.Pt, U, ANY.Diamond, U)));
		IL.Processor_Crystal_Ruby          .set(addItem(tLastID = 30502, "Кристаллический процессор (Рубин)"   , "Схема управляющего процессора"        , TC.stack(TC.COGNITIO, 5), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(MT.Pt, U, MT.Ruby, U)));
		IL.Processor_Crystal_Emerald       .set(addItem(tLastID = 30503, "Кристаллический процессор (Изумруд)" , "Схема запоминающего процессора"       , TC.stack(TC.COGNITIO, 5), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(MT.Pt, U, ANY.Emerald, U)));
		IL.Processor_Crystal_Sapphire      .set(addItem(tLastID = 30504, "Кристаллический процессор (Сапфир)"  , "Схема конвертирующего процессора"     , TC.stack(TC.COGNITIO, 5), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(MT.Pt, U, ANY.Sapphire, U)));
		
		CR.shaped(IL.Processor_Crystal_Empty.get(1), CR.DEF_REV, "CLC", "LBL", "CLC", 'C', OD_CIRCUITS[6], 'B', IL.Circuit_Plate_Platinum, 'L', IL.Comp_Laser_Gas_HeNe);
		
		RM.Press.addRecipe2(T, F, F, F, T, 16, 16, IL.Processor_Crystal_Empty.get(1), IL.Circuit_Crystal_Diamond .get(1), IL.Processor_Crystal_Diamond .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 16, IL.Processor_Crystal_Empty.get(1), IL.Circuit_Crystal_Ruby    .get(1), IL.Processor_Crystal_Ruby    .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 16, IL.Processor_Crystal_Empty.get(1), IL.Circuit_Crystal_Emerald .get(1), IL.Processor_Crystal_Emerald .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 16, IL.Processor_Crystal_Empty.get(1), IL.Circuit_Crystal_Sapphire.get(1), IL.Processor_Crystal_Sapphire.get(1));
		
		
		CR.shaped(IL.Cover_Logistics_Display_CPU_Logic     .get(1), CR.DEF_REV, "dL ", " Q ", " C ", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'L', OP.wireGt01.dat(MT.Lumium));
		CR.shaped(IL.Cover_Logistics_Display_CPU_Control   .get(1), CR.DEF_REV, " Ld", " Q ", " C ", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'L', OP.wireGt01.dat(MT.Lumium));
		CR.shaped(IL.Cover_Logistics_Display_CPU_Storage   .get(1), CR.DEF_REV, " L ", " Q ", "dC ", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'L', OP.wireGt01.dat(MT.Lumium));
		CR.shaped(IL.Cover_Logistics_Display_CPU_Conversion.get(1), CR.DEF_REV, " L ", " Q ", " Cd", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'L', OP.wireGt01.dat(MT.Lumium));
		
		CR.shaped(IL.Cover_Logistics_Fluid_Export          .get(1), CR.DEF_REV, "  w", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Fluid_Import          .get(1), CR.DEF_REV, " w ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Fluid_Storage         .get(1), CR.DEF_REV, "w  ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Item_Export           .get(1), CR.DEF_REV, "  r", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Item_Import           .get(1), CR.DEF_REV, " r ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Item_Storage          .get(1), CR.DEF_REV, "r  ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Generic_Export        .get(1), CR.DEF_REV, "  d", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Generic_Import        .get(1), CR.DEF_REV, " d ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Generic_Storage       .get(1), CR.DEF_REV, "d  ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Dump                  .get(1), CR.DEF_REV, "   ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		
		
		
		IL.USB_Stick_1                     .set(addItem(tLastID = 32001, "USB-накопитель 1.0"                    , "Хранит данные"                                     , OD_USB_STICKS[1], Behavior_DataStorage.INSTANCE, TC.stack(TC.COGNITIO, 3), TC.stack(TC.ELECTRUM, 1)));
		IL.USB_Stick_2                     .set(addItem(tLastID = 32002, "USB-накопитель 2.0"                    , "Хранит данные"                                     , OD_USB_STICKS[2], Behavior_DataStorage.INSTANCE, TC.stack(TC.COGNITIO, 4), TC.stack(TC.ELECTRUM, 2), TC.stack(TC.MOTUS, 1)));
		IL.USB_Stick_3                     .set(addItem(tLastID = 32003, "USB-накопитель 3.0"                    , "Хранит данные"                                     , OD_USB_STICKS[3], Behavior_DataStorage.INSTANCE, TC.stack(TC.COGNITIO, 5), TC.stack(TC.ELECTRUM, 3), TC.stack(TC.MOTUS, 2)));
		IL.USB_Stick_4                     .set(addItem(tLastID = 32004, "USB-накопитель 4.0"                    , "Хранит данные"                                     , OD_USB_STICKS[4], Behavior_DataStorage.INSTANCE, TC.stack(TC.COGNITIO, 6), TC.stack(TC.ELECTRUM, 4), TC.stack(TC.MOTUS, 3)));
		
		CR.shaped(IL.USB_Stick_1.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', OD_CIRCUITS[3], 'W', MT.DATA.WIRES_01[3], 'P', OP.plate.dat(MT.Al            ), 'T', OP.screw.dat(MT.Al            ));
		CR.shaped(IL.USB_Stick_2.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', OD_CIRCUITS[4], 'W', MT.DATA.WIRES_01[4], 'P', OP.plate.dat(MT.StainlessSteel), 'T', OP.screw.dat(MT.StainlessSteel));
		CR.shaped(IL.USB_Stick_3.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', OD_CIRCUITS[5], 'W', MT.DATA.WIRES_01[5], 'P', OP.plate.dat(MT.Cr            ), 'T', OP.screw.dat(MT.Cr            ));
		CR.shaped(IL.USB_Stick_4.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', OD_CIRCUITS[6], 'W', MT.DATA.WIRES_01[6], 'P', OP.plate.dat(MT.Ti            ), 'T', OP.screw.dat(MT.Ti            ));
		
		BooksGT.BOOK_REGISTER.put(IL.USB_Stick_1, (byte)54);
		BooksGT.BOOK_REGISTER.put(IL.USB_Stick_2, (byte)54);
		BooksGT.BOOK_REGISTER.put(IL.USB_Stick_3, (byte)54);
		BooksGT.BOOK_REGISTER.put(IL.USB_Stick_4, (byte)54);
		
		
		IL.USB_Cable_1                     .set(addItem(tLastID = 32011, "USB-кабель 1.0"                    , "Заменяет USB-накопители при подключении к USB-портам" , OD_USB_CABLES[1], TC.stack(TC.COGNITIO, 2), TC.stack(TC.ELECTRUM, 1)));
		IL.USB_Cable_2                     .set(addItem(tLastID = 32012, "USB-кабель 2.0"                    , "Заменяет USB-накопители при подключении к USB-портам" , OD_USB_CABLES[2], TC.stack(TC.COGNITIO, 3), TC.stack(TC.ELECTRUM, 2), TC.stack(TC.ITER, 1)));
		IL.USB_Cable_3                     .set(addItem(tLastID = 32013, "USB-кабель 3.0"                    , "Заменяет USB-накопители при подключении к USB-портам" , OD_USB_CABLES[3], TC.stack(TC.COGNITIO, 4), TC.stack(TC.ELECTRUM, 3), TC.stack(TC.ITER, 2)));
		IL.USB_Cable_4                     .set(addItem(tLastID = 32014, "USB-кабель 4.0"                    , "Заменяет USB-накопители при подключении к USB-портам" , OD_USB_CABLES[4], TC.stack(TC.COGNITIO, 5), TC.stack(TC.ELECTRUM, 4), TC.stack(TC.ITER, 3)));
		
		CR.shaped(IL.USB_Cable_1.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', MT.DATA.CABLES_01[3], 'W', MT.DATA.WIRES_01[3], 'P', OP.plate.dat(MT.Al            ), 'T', OP.screw.dat(MT.Al            ));
		CR.shaped(IL.USB_Cable_2.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', MT.DATA.CABLES_01[4], 'W', MT.DATA.WIRES_01[4], 'P', OP.plate.dat(MT.StainlessSteel), 'T', OP.screw.dat(MT.StainlessSteel));
		CR.shaped(IL.USB_Cable_3.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', MT.DATA.CABLES_01[5], 'W', MT.DATA.WIRES_01[5], 'P', OP.plate.dat(MT.Cr            ), 'T', OP.screw.dat(MT.Cr            ));
		CR.shaped(IL.USB_Cable_4.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', MT.DATA.CABLES_01[6], 'W', MT.DATA.WIRES_01[6], 'P', OP.plate.dat(MT.Ti            ), 'T', OP.screw.dat(MT.Ti            ));
		
		
		IL.USB_HDD_1                       .set(addItem(tLastID = 32021, "USB 1.0 HDD"                      , "Хранит до 16 файлов одновременно"                   , OD_USB_DRIVES[1], Behavior_DataStorage16.INSTANCE, TC.stack(TC.COGNITIO, 3), TC.stack(TC.ELECTRUM, 2), TC.stack(TC.MOTUS, 1)));
		IL.USB_HDD_2                       .set(addItem(tLastID = 32022, "USB 2.0 HDD"                      , "Хранит до 16 файлов одновременно"                   , OD_USB_DRIVES[2], Behavior_DataStorage16.INSTANCE, TC.stack(TC.COGNITIO, 4), TC.stack(TC.ELECTRUM, 3), TC.stack(TC.MOTUS, 2)));
		IL.USB_HDD_3                       .set(addItem(tLastID = 32023, "USB 3.0 HDD"                      , "Хранит до 16 файлов одновременно"                   , OD_USB_DRIVES[3], Behavior_DataStorage16.INSTANCE, TC.stack(TC.COGNITIO, 5), TC.stack(TC.ELECTRUM, 4), TC.stack(TC.MOTUS, 3)));
		IL.USB_HDD_4                       .set(addItem(tLastID = 32024, "USB 4.0 HDD"                      , "Хранит до 16 файлов одновременно"                   , OD_USB_DRIVES[4], Behavior_DataStorage16.INSTANCE, TC.stack(TC.COGNITIO, 6), TC.stack(TC.ELECTRUM, 5), TC.stack(TC.MOTUS, 4)));
		
		CR.shaped(IL.USB_HDD_1.get(1), CR.DEF_REV, "PLT", "dRW", "TCP", 'C', OD_CIRCUITS[3], 'W', IL.USB_Cable_1, 'L', IL.Comp_Laser_Gas_He, 'R', OD.record, 'P', OP.plate.dat(MT.Al            ), 'T', OP.screw.dat(MT.Al            )); // TODO: Replace record with a CD (made of aluminium foils and plastic plates in a Press)
		CR.shaped(IL.USB_HDD_2.get(1), CR.DEF_REV, "PLT", "dRW", "TCP", 'C', OD_CIRCUITS[4], 'W', IL.USB_Cable_2, 'L', IL.Comp_Laser_Gas_He, 'R', OD.record, 'P', OP.plate.dat(MT.StainlessSteel), 'T', OP.screw.dat(MT.StainlessSteel));
		CR.shaped(IL.USB_HDD_3.get(1), CR.DEF_REV, "PLT", "dRW", "TCP", 'C', OD_CIRCUITS[5], 'W', IL.USB_Cable_3, 'L', IL.Comp_Laser_Gas_He, 'R', OD.record, 'P', OP.plate.dat(MT.Cr            ), 'T', OP.screw.dat(MT.Cr            ));
		CR.shaped(IL.USB_HDD_4.get(1), CR.DEF_REV, "PLT", "dRW", "TCP", 'C', OD_CIRCUITS[6], 'W', IL.USB_Cable_4, 'L', IL.Comp_Laser_Gas_He, 'R', OD.record, 'P', OP.plate.dat(MT.Ti            ), 'T', OP.screw.dat(MT.Ti            ));
		
		BooksGT.BOOK_REGISTER.put(IL.USB_HDD_1, (byte)54);
		BooksGT.BOOK_REGISTER.put(IL.USB_HDD_2, (byte)54);
		BooksGT.BOOK_REGISTER.put(IL.USB_HDD_3, (byte)54);
		BooksGT.BOOK_REGISTER.put(IL.USB_HDD_4, (byte)54);
	}
}
