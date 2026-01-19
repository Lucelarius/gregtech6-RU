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

import gregapi.code.TagData;
import gregapi.lang.LanguageHandler;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Converter;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Stats;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 * 
 * Contains common translatable Strings.
 */
public class LH {
	public static final String
	  EFFICIENCY = "gt.lang.efficiency"
	, RECIPE = "gt.lang.recipe"
	, RECIPES = "gt.lang.recipes"
	, RECIPES_MOLD = "gt.lang.recipes.mold"
	, RECIPES_MOLD_SELECT = "gt.lang.recipes.mold.select"
	, RECIPES_MOLD_COINAGE = "gt.lang.recipes.mold.coinage"
	, RECIPES_ANVIL_USAGE = "gt.lang.recipes.anvil.usage"
	, RECIPES_SIFTER_USAGE = "gt.lang.recipes.sifter.usage"
	, RECIPES_JUICER_USAGE = "gt.lang.recipes.juicer.usage"
	, RECIPES_MORTAR_USAGE = "gt.lang.recipes.mortar.usage"
	, RECIPES_MIXINGBOWL_USAGE = "gt.lang.recipes.mixingbowl.usage"
	, RECIPES_BATHINGPOT_USAGE = "gt.lang.recipes.bathingsink.usage"
	, RECIPES_GRINDSTONE_USAGE = "gt.lang.recipes.grindstone.usage"
	, RECIPES_GRINDSTONE_INIT = "gt.lang.recipes.grindstone.init"
	, RECIPES_DUSTFUNNEL = "gt.lang.recipes.dustfunnel"
	, RECIPES_AUTOHAMMER = "gt.lang.recipes.autohammer"
	, RECIPES_IGNITER = "gt.lang.recipes.igniter"
	, RECIPES_QUALITY = "gt.lang.recipes.quality"
	, STRUCTURE = "gt.lang.structure"
	, ENERGY_CONTAINED = "gt.lang.energy.contained"
	, ENERGY_CAPACITY = "gt.lang.energy.capacity"
	, ENERGY_OUTPUT = "gt.lang.energy.output"
	, ENERGY_INPUT = "gt.lang.energy.input"
	, ITEM_OUTPUT = "gt.lang.item.output"
	, ITEM_INPUT = "gt.lang.item.input"
	, FLUID_OUTPUT = "gt.lang.fluid.output"
	, FLUID_INPUT = "gt.lang.fluid.input"
	, CONVERTS_FROM_X = "gt.lang.energy.convert.from"
	, CONVERTS_TO_Y = "gt.lang.energy.convert.to"
	, CONVERTS_USING_Z = "gt.lang.energy.convert.using"
	, CONVERTS_PER_Z = "gt.lang.energy.convert.per"
	, FACE_ANY = "gt.lang.face.any"
	, FACE_BOTTOM = "gt.lang.face.bottom"
	, FACE_TOP = "gt.lang.face.top"
	, FACE_LEFT = "gt.lang.face.left"
	, FACE_FRONT = "gt.lang.face.front"
	, FACE_RIGHT = "gt.lang.face.right"
	, FACE_BACK = "gt.lang.face.back"
	, FACE_NONE = "gt.lang.face.none"
	, FACES[] = {FACE_BOTTOM, FACE_TOP, FACE_LEFT, FACE_FRONT, FACE_RIGHT, FACE_BACK, FACE_NONE}
	, FACE_SIDES = "gt.lang.face.sides"
	, FACE_FRONT_BACK = "gt.lang.face.front.back"
	, FACE_ANYBUT_FRONT_BACK = "gt.lang.face.any.but.front.back"
	, FACE_ANYBUT_BOTTOM = "gt.lang.face.any.but.bottom"
	, FACE_ANYBUT_TOP = "gt.lang.face.any.but.top"
	, FACE_ANYBUT_LEFT = "gt.lang.face.any.but.left"
	, FACE_ANYBUT_FRONT = "gt.lang.face.any.but.front"
	, FACE_ANYBUT_RIGHT = "gt.lang.face.any.but.right"
	, FACE_ANYBUT_BACK = "gt.lang.face.any.but.back"
	, FACE_ANYBUT_SIDES = "gt.lang.face.any.but.sides"
	, REQUIREMENT_AIR_IN_FRONT = "gt.lang.requirement.air.front"
	, REQUIREMENT_EMPTY_ASHES = "gt.lang.requirement.empty.ashes"
	, REQUIREMENT_IGNITE_FIRE = "gt.lang.requirement.ignite.fire"
	, REQUIREMENT_MOLTEN_CALCITE = "gt.lang.requirement.molten.calcite"
	, REQUIREMENT_WATER = "gt.lang.requirement.water"
	, REQUIREMENT_WATER_ANY = "gt.lang.requirement.water.any"
	, REQUIREMENT_WATER_PURE = "gt.lang.requirement.water.pure"
	, REQUIREMENT_DISTILLED_WATER = "gt.lang.requirement.water.distilled"
	, REQUIREMENT_CHUNKLOADER = "gt.lang.requirement.chunk.loader"
	, REQUIREMENT_UNSTACKED = "gt.lang.requirement.unstacked"
	, EMITS_USED_STEAM = "gt.lang.emits.used.steam"
	, EMITS_REDSTONE_FLUX = "gt.lang.emits.redstoneflux.lossless"
	, EMITS_REDSTONE_FLUX_LOSS = "gt.lang.emits.redstoneflux.lossy"
	, ACCEPTS_REDSTONE_FLUX = "gt.lang.accepts.redstoneflux.lossless"
	, ACCEPTS_REDSTONE_FLUX_LOSS = "gt.lang.accepts.redstoneflux.lossy"
	, NO_GUI_CLICK_TO_LIMIT = "gt.lang.nogui.rightclick.tank.limit"
	, NO_GUI_CLICK_TO_INTERACT = "gt.lang.nogui.rightclick.interact"
	, NO_GUI_CLICK_TO_INVENTORY = "gt.lang.nogui.rightclick.inventory"
	, NO_GUI_CLICK_TO_TANK = "gt.lang.nogui.rightclick.tank"
	, NO_GUI_FUNNEL_TAP_TO_TANK = "gt.lang.nogui.funnel.tap.tank"
	, NO_GUI_FUNNEL_TO_TANK = "gt.lang.nogui.funnel.tank"
	, NO_GUI_TAP_TO_TANK = "gt.lang.nogui.tap.tank"
	, NO_POWER_CONDUCTING_FLUIDS = "gt.lang.no.powerconducting.fluids"
	, OWNER_CONTROLLED = "gt.lang.owner.controlled"
	, CHEAP_OVERCLOCKING = "gt.lang.cheap.overclocking"
	, KEY_CONTROLLED = "gt.lang.key.controlled"
	, COVER_TOOLTIP = "gt.lang.cover.tooltip"
	, TOOL_TO_SET_FACING_PRE = "gt.lang.use.x.to.toggle.facing.pre"
	, TOOL_TO_SET_FACING_POST = "gt.lang.use.x.to.toggle.facing.post"
	, TOOL_TO_SET_FACING2_PRE = "gt.lang.use.x.to.toggle.facing2.pre"
	, TOOL_TO_SET_FACING2_POST = "gt.lang.use.x.to.toggle.facing2.post"
	, TOOL_TO_SET_CONNECTIONS_PRE = "gt.lang.use.x.to.toggle.connection.pre"
	, TOOL_TO_SET_CONNECTIONS_POST = "gt.lang.use.x.to.toggle.connection.post"
	, TOOL_TO_SET_DIRECTION_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle.direction"
	, TOOL_TO_SET_INPUT_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.set.input.side"
	, TOOL_TO_SET_OUTPUT_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.set.output.side"
	, TOOL_TO_TOGGLE_INPUTS_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle.inputs"
	, TOOL_TO_TOGGLE_OUTPUTS_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle.outputs"
	, TOOL_TO_TOGGLE_AUTO_INPUTS_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle.auto.inputs"
	, TOOL_TO_TOGGLE_AUTO_OUTPUTS_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle.auto.outputs"
	, TOOL_TO_TOGGLE_CONTROLLER_COVER = "gt.lang.use.controlcover.to.toggle"
	, TOOL_TO_TOGGLE_SCREWDRIVER = "gt.lang.use.screwdriver.to.toggle"
	, TOOL_TO_TOGGLE_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle"
	, TOOL_TO_TOGGLE_CUTTER = "gt.lang.use.cutter.to.toggle"
	, TOOL_TO_TOGGLE_SOFT_HAMMER = "gt.lang.use.soft.hammer.to.toggle"
	, TOOL_TO_RESET_SOFT_HAMMER = "gt.lang.use.soft.hammer.to.reset"
	, TOOL_TO_TAPE = "gt.lang.use.tape"
	, TOOL_TO_UNTAPE = "gt.lang.use.untape"
	, TOOL_TO_OPEN_CROWBAR = "gt.lang.use.crowbar.to.open"
	, TOOL_TO_UNCOVER_CROWBAR = "gt.lang.use.crowbar.to.uncover"
	, TOOL_TO_DECALCIFY_CHISEL = "gt.lang.use.chisel.to.decalcify"
	, TOOL_TO_DETAIL_MAGNIFYINGGLASS = "gt.lang.use.magnifyingglass.to.detail"
	, TOOL_TO_MEASURE_GEIGER_COUNTER = "gt.lang.use.geigercoutner.to.measure"
	, TOOL_TO_MEASURE_THERMOMETER = "gt.lang.use.thermometer.to.measure"
	, TOOL_TO_ACCESS_SCOOP = "gt.lang.use.scoop.to.access"
	, TOOL_TO_REMOVE_SHOVEL = "gt.lang.use.shovel.to.empty"
	, TOOL_TO_CHANGE_DESIGN_CHISEL = "gt.lang.use.chisel.to.switch.design"
	, TOOL_TO_HARVEST = "gt.lang.tool.to.harvest"
	, TOOL_TO_TAKE_PINCERS = "gt.lang.use.pincers.to.take"
	, TOOL_HINT_USE_SNEAK = "gt.lang.tool.hint.use.sneak"
	, WEAPON_SNEAK_RIGHTCLICK_TO_RELOAD = "gt.weapon.sneak.rightclick.reload"
	, WIRE_STATS_LOSSLESS = "gt.lang.wire.stats.lossless"
	, WIRE_STATS_LOSS = "gt.lang.wire.stats.loss"
	, WIRE_STATS_VOLTAGE = "gt.lang.wire.stats.voltage"
	, WIRE_STATS_AMPERAGE = "gt.lang.wire.stats.amperage"
	, PIPE_STATS_LOSS = "gt.lang.pipe.stats.loss"
	, PIPE_STATS_RANGE = "gt.lang.pipe.stats.range"
	, PIPE_STATS_STEPSIZE = "gt.lang.pipe.stats.stepsize"
	, PIPE_STATS_BANDWIDTH = "gt.lang.pipe.stats.bandwidth"
	, PIPE_STATS_CAPACITY = "gt.lang.pipe.stats.capacity"
	, PIPE_STATS_AMOUNT = "gt.lang.pipe.stats.amount"
	, AXLE_STATS_SPEED = "gt.lang.axle.stats.speed"
	, AXLE_STATS_POWER = "gt.lang.axle.stats.power"
	, HAZARD_FIRE = "gt.lang.hazard.fire"
	, HAZARD_EXPLOSION_STEAM = "gt.lang.hazard.explosion.steam"
	, HAZARD_MELTDOWN = "gt.lang.hazard.meltdown"
	, HAZARD_CONTACT = "gt.lang.hazard.contact"
	, HAZARD_LEAKING_GAS = "gt.lang.hazard.leak.gas"
	, TOOLTIP_GASPROOF = "gt.lang.proof.gas"
	, TOOLTIP_ACIDPROOF = "gt.lang.proof.acid"
	, TOOLTIP_MAGICPROOF = "gt.lang.proof.magic"
	, TOOLTIP_LIQUIDPROOF = "gt.lang.proof.liquid"
	, TOOLTIP_PLASMAPROOF = "gt.lang.proof.plasma"
	, TOOLTIP_HEATPROOF = "gt.lang.proof.heat"
	, TOOLTIP_THERMALMASS = "gt.lang.thermal.mass"
	, TOOLTIP_ONLY_SIMPLE = "gt.lang.only.simple"
	, TOOLTIP_REMINDER_EXTENDERS = "gt.lang.reminder.extenders"
	, TOOLTIP_SEALABLE_ANY = "gt.lang.sealable.any"
	, TOOLTIP_SEALABLE_SOME = "gt.lang.sealable.some"
	, TOOLTIP_SEALABLE_BUGGED = "gt.lang.sealable.bug"
	, TOOLTIP_PISTONPUSHABLE = "gt.lang.pistonpush"
	, TOOLTIP_SPAWNPROOF = "gt.lang.spawnproof"
	, TOOLTIP_SPAWNPROOF_OPTIFINE = "gt.lang.spawnproof.optifine"
	, TOOLTIP_SPAWNPROOF_MP_BUG = "gt.lang.spawnproof.mp.bug"
	, TOOLTIP_SPAWNPROOF_MP_BROKEN = "gt.lang.spawnproof.mp.broken"
	, TOOLTIP_SPAWNPROOF_SP_BUG = "gt.lang.spawnproof.sp.bug"
	, TOOLTIP_SPAWNPROOF_SP_BROKEN = "gt.lang.spawnproof.sp.broken"
	, TOOLTIP_BLASTPOWER = "gt.lang.blastpower"
	, TOOLTIP_BLASTRANGE = "gt.lang.blastrange"
	, TOOLTIP_BLASTFORTUNE = "gt.lang.blastfortune"
	, TOOLTIP_BLASTRESISTANCE = "gt.lang.blastresistance"
	, TOOLTIP_RAILSPEED = "gt.lang.railspeed"
	, TOOLTIP_WALKSPEED = "gt.lang.walkspeed"
	, TOOLTIP_GRAVITY = "gt.lang.gravity"
	, TOOLTIP_NEEDS_HANDLE = "gt.lang.needs.handle"
	, TOOLTIP_NEEDS_SHARPENING = "gt.lang.needs.sharpening"
	, TOOLTIP_SHAPELESS_CRAFT = "gt.lang.has.shapeless"
	, TOOLTIP_AUTOCOLLECT = "gt.lang.autocollect"
	, TOOLTIP_ARMOR_PENETRATING = "gt.lang.armorpenetrating"
	, TOOLTIP_BEACON_PAYMENT = "gt.lang.beacon.payment"
	, TOOLTIP_SHELFABLE = "gt.lang.shelfable"
	, TOOLTIP_SANDWICHABLE = "gt.lang.sandwichable"
	, TOOLTIP_POSSIBLE_ENCHANTS = "gt.lang.tool.possible.enchants"
	, TOOLTIP_POSSIBLE_TOOL_ENCHANTS = "gt.lang.tool.enchants"
	, TOOLTIP_POSSIBLE_WEAPON_ENCHANTS = "gt.lang.weapon.enchants"
	, TOOLTIP_POSSIBLE_AMMO_ENCHANTS = "gt.lang.ammo.enchants"
	, TOOLTIP_POSSIBLE_RANGED_ENCHANTS = "gt.lang.ranged.enchants"
	, TOOLTIP_POSSIBLE_FISHING_ENCHANTS = "gt.lang.fishing.enchants"
	, TOOLTIP_POSSIBLE_ARMOR_ENCHANTS = "gt.lang.armor.enchants"
	, TOOLTIP_TOO_MANY_TOOL_ENCHANTS = "gt.lang.tool.enchants.too.many"
	, TOOLTIP_TOO_MANY_ARMOR_ENCHANTS = "gt.lang.armor.enchants.too.many"
	, TOOLTIP_CONTAINED_MATERIALS = "gt.lang.contained.materials"
	, TOOLTIP_FLAMMABLE_AND_EXPLOSIVE = "gt.lang.flammable.explosive"
	, TOOLTIP_FLAMMABLE = "gt.lang.flammable"
	, TOOLTIP_EXPLOSIVE = "gt.lang.explosive"
	, TOOLTIP_UNBURNABLE = "gt.lang.unburnable"
	, TOOLTIP_BLAST_RESISTANCE_TERRIBLE = "gt.lang.blast.resist.terrible"
	, TOOLTIP_BLAST_RESISTANCE_GHAST = "gt.lang.blast.resist.ghast.proof"
	, TOOLTIP_BLAST_RESISTANCE_CREEPER = "gt.lang.blast.resist.creeper.proof"
	, TOOLTIP_BLAST_RESISTANCE_TNT = "gt.lang.blast.resist.tnt.proof"
	, TOOLTIP_BLAST_RESISTANCE_DYNAMITE = "gt.lang.blast.resist.dynamite.proof"
	, TOOLTIP_BLAST_RESISTANCE_NOT_NUKE = "gt.lang.blast.resist.nuke.not"
	, TOOLTIP_ENCHANT_BONUS = "gt.lang.enchantment.bonus"
	, TOOLTIP_THAUMCRAFT_WARP = "gt.lang.thaumcraft.warp"
	, TOOLTIP_BETWEENLANDS_RESISTANCE = "gt.lang.betweenlands.resist"
	, TOOLTIP_TWILIGHT_MAZE_BREAKING = "gt.lang.twilightforest.mazebreaking"
	, TOOLTIP_TWILIGHT_MAZE_HEDGE_BREAKING = "gt.lang.twilightforest.mazehedgebreaking"
	, TOOLTIP_TWILIGHT_MAZE_STONE_BREAKING = "gt.lang.twilightforest.mazestonebreaking"
	, TOOLTIP_TWILIGHT_TOWER_WOOD_BREAKING = "gt.lang.twilightforest.towerwoodbreaking"
	, PROSPECTING_LAVA = "gt.lang.prospecting.lava"
	, PROSPECTING_LIQUID = "gt.lang.prospecting.liquid"
	, PROSPECTING_AIR = "gt.lang.prospecting.air"
	, PROSPECTING_CHANGE = "gt.lang.prospecting.change"
	, PROSPECTING_TRACES = "gt.lang.prospecting.traces"
	, PROSPECTING_NOTHING = "gt.lang.prospecting.nothing"
	, AUTOCRAFTING_INSERT_BLUEPRINT = "gt.autocrafting.insert.blueprint"
	, ADVCRAFTING_INSERT_BLUEPRINT = "gt.advcrafting.insert.blueprint"
	, ADVCRAFTING_PUT_TO_STORAGE = "gt.advcrafting.put.to.storage"
	, ADVCRAFTING_AUTOMATION_ACCESS = "gt.advcrafting.automation.access"
	, ADVCRAFTING_NEUTRAL_SLOT = "gt.advcrafting.neutral.slot"
	, ADVCRAFTING_DROP_SLOT = "gt.advcrafting.drop.slot"
	, TIME_TICK = "gt.lang.time.tick"
	, TIME_SEC = "gt.lang.time.second"
	, TIME_MIN = "gt.lang.time.minute"
	, TIME_HOUR = "gt.lang.time.hour"
	, TIME_DAY = "gt.lang.time.day"
	, TIME_WEEK = "gt.lang.time.week"
	, TIME_TICKS = "gt.lang.time.ticks"
	, TIME_SECS = "gt.lang.time.seconds"
	, TIME_MINS = "gt.lang.time.minutes"
	, TIME_HOURS = "gt.lang.time.hours"
	, TIME_DAYS = "gt.lang.time.days"
	, TIME_WEEKS = "gt.lang.time.weeks"
	, ADMIN_ONLY_CREATION = "gt.lang.admin.only.creation"
	, WIP = "gt.lang.work.in.progress"
	;
	
	public static final String add(String aKey, String aEnglish) {LanguageHandler.add(aKey, aEnglish); return aKey;}
	public static final String get(String aKey) {return LanguageHandler.translate(aKey);}
	public static final String get(String aKey, String aDefault) {return LanguageHandler.translate(aKey, aDefault);}
	
	public static final String percent(long aNumber) {return (aNumber/100) + ((aNumber%100)>9?"."+aNumber%100:".0"+(aNumber%100));}
	
	public static final String getToolTipBlastResistance(Block aBlock, double aResistance) {return Chat.WHITE + get(LH.TOOLTIP_BLASTRESISTANCE) + Chat.ORANGE + ((int)aResistance) + "." + (((int)(aResistance * 10)) % 10) + (aResistance < 4 ? Chat.BLINKING_RED + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_TERRIBLE) : aResistance < 12 ? Chat.RED + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_GHAST) : aResistance < 16 ? Chat.YELLOW + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_CREEPER) : aResistance <= 40 ? Chat.GREEN + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_TNT) : aResistance < 3330 || COMPAT_IC2 == null || COMPAT_IC2.isExplosionWhitelisted(aBlock) ? Chat.GREEN + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_DYNAMITE) : Chat.BLINKING_CYAN + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_NOT_NUKE));}
	
	public static final String getToolTipHarvest(Material aMaterial, String aHarvestTool, int aHarvestLevel) {
		if (aMaterial.isAdventureModeExempt()) {
			if (UT.Code.stringValid(aHarvestTool))
			return LH.Chat.DGRAY + "Собирается руками. Быстрее, если используется " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + LH.Chat.DGRAY;
			return LH.Chat.DGRAY + "Собирается руками";
		}
		if (UT.Code.stringValid(aHarvestTool)) {
			if (aHarvestLevel > 1) switch (aHarvestLevel) {
			case  1: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Stone.getLocal()+")";
			case  2: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Fe.getLocal()+")";
			case  3: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Diamond.getLocal()+")";
			case  4: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Netherite.getLocal()+")";
			case  5: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Ad.getLocal()+")";
			case  6: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case  7: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case  8: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case  9: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case 10: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case 11: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case 12: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case 13: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case 14: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			default: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Infinity.getLocal()+")";
			}
			return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool));
		}
		if (aMaterial == Material.rock || aMaterial == Material.iron || aMaterial == Material.anvil || aMaterial == Material.glass)
		return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + "pickaxe") + "?";
		if (aMaterial == Material.craftedSnow || aMaterial == Material.snow || aMaterial == Material.sand || aMaterial == Material.grass || aMaterial == Material.ground || aMaterial == Material.clay)
		return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + "shovel") + "?";
		if (aMaterial == Material.wood || aMaterial == Material.plants || aMaterial == Material.vine || aMaterial == Material.gourd || aMaterial == Material.cactus)
		return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + "axe") + "?";
		if (aMaterial == Material.leaves || aMaterial == Material.cloth || aMaterial == Material.carpet || aMaterial == Material.web)
		return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + "sword") + "?";
		return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + "Unknown";
	}
	
	public static final String getToolTipEfficiency(long aEfficiency) {aEfficiency = Math.abs(aEfficiency); return Chat.YELLOW + get(EFFICIENCY) + ": " + Chat.WHITE + percent(aEfficiency) + "%";}
	
	public static final void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H, TE_Behavior_Energy_Converter aConverter) {
		addToolTipsEfficiency(aList, aStack, aF3_H, aConverter.mEnergyIN, aConverter.mEnergyOUT, aConverter.mMultiplier);
	}
	public static final void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H, TE_Behavior_Energy_Stats aEnergyIN, TE_Behavior_Energy_Stats aEnergyOUT, long aMultiplier) {
		if (TD.Energy.ALL_EU.contains(aEnergyIN.mType)) {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT.mType)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*aMultiplier, F)));
			} else {
				if (aEnergyOUT.mType == TD.Energy.RF  ) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec*RF_PER_EU, aEnergyOUT.mRec*aMultiplier, F)));
			}
		} else {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT.mType)) {
				if (aEnergyIN.mType == TD.Energy.RF   ) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*aMultiplier*RF_PER_EU, F)));
				if (aEnergyIN.mType == TD.Energy.STEAM) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*aMultiplier*STEAM_PER_EU, F)));
			}
		}
	}
	
	public static final void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H, TE_Behavior_Energy_Stats aEnergyIN, TE_Behavior_Energy_Stats aEnergyOUT, TE_Behavior_Energy_Stats aEnergyOUT2, long aMultiplier) {
		if (TD.Energy.ALL_EU.contains(aEnergyIN.mType)) {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT.mType)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec, F)));
			} else {
				if (aEnergyOUT.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec*RF_PER_EU, aEnergyOUT.mRec, F)));
			}
			if (TD.Energy.ALL_EU.contains(aEnergyOUT2.mType)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec, F)));
			} else {
				if (aEnergyOUT2.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec*RF_PER_EU, aEnergyOUT.mRec, F)));
			}
		} else {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT .mType) && aEnergyIN.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*8, F)));
			if (TD.Energy.ALL_EU.contains(aEnergyOUT2.mType) && aEnergyIN.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*8, F)));
		}
	}
	
	public static final void addEnergyToolTips(ITileEntityEnergy aTileEntity, List<String> aToolTips, TagData aEnergyTypeIN, TagData aEnergyTypeOUT, String aSidesIN, String aSidesOUT) {
		if (aEnergyTypeIN != null) {
			long tMin = aTileEntity.getEnergySizeInputMin(aEnergyTypeOUT, SIDE_ANY), tRec = aTileEntity.getEnergySizeInputRecommended(aEnergyTypeOUT, SIDE_ANY), tMax = aTileEntity.getEnergySizeInputMax(aEnergyTypeOUT, SIDE_ANY);
			aToolTips.add(Chat.GREEN + LH.get(LH.ENERGY_INPUT ) + ": " + Chat.WHITE + tRec + " " + aEnergyTypeIN .getLocalisedChatNameShort() + Chat.WHITE + (tRec == tMin && tRec == tMax ? "/t" : (tMin <= 1 ? "/t (до " : "/t ("+tMin+" - ")+tMax+(UT.Code.stringInvalid(aSidesIN )?"":", "+aSidesIN )+")"));
			aToolTips.add(getToolTipRedstoneFluxAccept(aEnergyTypeIN));
		}
		if (aEnergyTypeOUT != null) {
			long tMin = aTileEntity.getEnergySizeOutputMin(aEnergyTypeOUT, SIDE_ANY), tRec = aTileEntity.getEnergySizeOutputRecommended(aEnergyTypeOUT, SIDE_ANY), tMax = aTileEntity.getEnergySizeOutputMax(aEnergyTypeOUT, SIDE_ANY);
			aToolTips.add(Chat.RED   + LH.get(LH.ENERGY_OUTPUT) + ": " + Chat.WHITE + tRec + " " + aEnergyTypeOUT.getLocalisedChatNameShort() + Chat.WHITE + (tRec == tMin && tRec == tMax ? "/t" : (tMin <= 1 ? "/t (до " : "/t ("+tMin+" - ")+tMax+(UT.Code.stringInvalid(aSidesOUT)?"":", "+aSidesOUT)+")"));
			aToolTips.add(getToolTipRedstoneFluxEmit(aEnergyTypeOUT));
		}
	}
	
	public static final String getToolTipRedstoneFluxEmit(TagData aEnergyType) {
		if (aEnergyType == TD.Energy.KU) return Chat.ORANGE + LH.get(LH.EMITS_REDSTONE_FLUX_LOSS)+" 50%";
		if (aEnergyType == TD.Energy.RF) return Chat.ORANGE + LH.get(LH.EMITS_REDSTONE_FLUX);
		if (aEnergyType == TD.Energy.MJ) return Chat.ORANGE + LH.get(LH.EMITS_REDSTONE_FLUX);
		return null;
	}
	
	public static final String getToolTipRedstoneFluxAccept(TagData aEnergyType) {
//      if (aEnergyType == TD.Energy.KU) return Chat.ORANGE + LH.get(LH.ACCEPTS_REDSTONE_FLUX_LOSS)+" 50%";
		if (aEnergyType == TD.Energy.RF) return Chat.ORANGE + LH.get(LH.ACCEPTS_REDSTONE_FLUX);
		if (aEnergyType == TD.Energy.MJ) return Chat.ORANGE + LH.get(LH.ACCEPTS_REDSTONE_FLUX);
		return null;
	}
	
	static {
		add("enchantment.level.11"                      , "XI");
		add("enchantment.level.12"                      , "XII");
		add("enchantment.level.13"                      , "XIII");
		add("enchantment.level.14"                      , "XIV");
		add("enchantment.level.15"                      , "XV");
		add("enchantment.level.16"                      , "XVI");
		add("enchantment.level.17"                      , "XVII");
		add("enchantment.level.18"                      , "XVIII");
		add("enchantment.level.19"                      , "XIX");
		add("enchantment.level.20"                      , "XX");
		add("enchantment.level.21"                      , "XXI");
		add("enchantment.level.22"                      , "XXII");
		add("enchantment.level.23"                      , "XXIII");
		add("enchantment.level.24"                      , "XXIV");
		add("enchantment.level.25"                      , "XXV");
		add("enchantment.level.26"                      , "XXVI");
		add("enchantment.level.27"                      , "XXVII");
		add("enchantment.level.28"                      , "XXVIII");
		add("enchantment.level.29"                      , "XXIX");
		add("enchantment.level.30"                      , "XXX");
		
		add("loot.mineshaftCorridor"                    , "+Mineshaft+");
		add("loot.pyramidDesertyChest"                  , "*Desert Pyramid*");
		add("loot.pyramidJungleChest"                   , "*Jungle Temple*");
		add("loot.pyramidJungleDispenser"               , "-Dispenser-");
		add("loot.strongholdCorridor"                   , "-Corridor-");
		add("loot.strongholdLibrary"                    , "+Library+");
		add("loot.strongholdCrossing"                   , "+Storage+");
		add("loot.villageBlacksmith"                    , "*Blacksmith*");
		add("loot.bonusChest"                           , "-Bonus Chest-");
		add("loot.dungeonChest"                         , "*Dungeon*");
		add("loot.twilightforest:hill1"                 , "-Hollow Hill-");
		add("loot.twilightforest:hill2"                 , "+Hollow Hill+");
		add("loot.twilightforest:hill3"                 , "*Hollow Hill*");
		add("loot.twilightforest:hedgemaze"             , "-Hedge Maze-");
		add("loot.twilightforest:tree_cache"            , "-Tree Cache-");
		add("loot.twilightforest:basement"              , "-Basement Cache-");
		add("loot.twilightforest:labyrinth_room"        , "-Labyrinth-");
		add("loot.twilightforest:labyrinth_deadend"     , "+Labyrinth+");
		add("loot.twilightforest:labyrinth_vault"       , "*Labyrinth*");
		add("loot.twilightforest:tower_room"            , "-Magic Tower-");
		add("loot.twilightforest:tower_library"         , "+Magic Tower+");
		add("loot.twilightforest:stronghold_cache"      , "-Stronghold-");
		add("loot.twilightforest:stronghold_room"       , "+Stronghold+");
		add("loot.twilightforest:stronghold_boss"       , "*Stronghold*");
		add("loot.twilightforest:darktower_cache"       , "-Dark Tower-");
		add("loot.twilightforest:darktower_key"         , "+Dark Tower+");
		add("loot.twilightforest:darktower_boss"        , "*Dark Tower*");
		add("loot.twilightforest:aurora_cache"          , "-Aurora Tower-");
		add("loot.twilightforest:aurora_room"           , "+Aurora Tower+");
		add("loot.twilightforest:aurora_boss"           , "*Aurora Tower*");
		add("loot.twilightforest:troll_garden"          , "-Troll Cave-");
		add("loot.twilightforest:troll_vault"           , "+Troll Cave+");
		
		add(EFFICIENCY                                  , "Эффективность (КПД)");
		add(RECIPE                                      , "Рецепт");
		add(RECIPES                                     , "Рецепты");
		add(RECIPES_MOLD                                , "Эта форма производит");
		add(RECIPES_MOLD_SELECT                         , "Используйте долото, чтобы выбрать форму");
		add(RECIPES_MOLD_COINAGE                        , "Поместите крошечную металлическую пластину сверху, забейте ее и возьмите монету");
		add(RECIPES_ANVIL_USAGE                         , "Разместите материал сверху и используйте молот сверху или сбоку");
		add(RECIPES_MORTAR_USAGE                        , "Нажмите ПКМ предметом, из которого хотите получить пыль");
		add(RECIPES_JUICER_USAGE                        , "Нажмите ПКМ предметом, из которого хотите получить сок");
		add(RECIPES_SIFTER_USAGE                        , "Поместите материал сверху и нажмите на него ПКМ");
		add(RECIPES_MIXINGBOWL_USAGE                    , "Поместите ввод в центр, заполните жидкостью ободок, затем щелкните его ПКМ");
		add(RECIPES_BATHINGPOT_USAGE                    , "Поместите ввод в центр, заполните жидкостью ободок, затем щелкните его ПКМ");
		add(RECIPES_GRINDSTONE_USAGE                    , "Нажмите несколько раз предметом ПКМ, который хотите заточить");
		add(RECIPES_GRINDSTONE_INIT                     , "Добавьте блок песчаника перед использованием");
		add(RECIPES_DUSTFUNNEL                          , "Превращает все пыли разного размера в указанный размер");
		add(RECIPES_AUTOHAMMER                          , "Дробит установленный перед ним блок");
		add(RECIPES_IGNITER                             , "Воспламеняет блок, находящийся у лицевой стороны машины");
		add(RECIPES_QUALITY                             , "Имеет качество инструмента");
		add(STRUCTURE                                   , "Структура");
		add(ENERGY_CONTAINED                            , "Хранится энергии");
		add(ENERGY_CAPACITY                             , "Вместимость");
		add(ENERGY_OUTPUT                               , "Отдает энергию");
		add(ENERGY_INPUT                                , "Принимает энергию");
		add(ITEM_OUTPUT                                 , "Принимает предметы");
		add(ITEM_INPUT                                  , "Отдает предметы");
		add(FLUID_OUTPUT                                , "Отдает жидкости");
		add(FLUID_INPUT                                 , "Принимает жидкости");
		add(CONVERTS_FROM_X                             , "Конвертирует");
		add(CONVERTS_TO_Y                               , "в");
		add(CONVERTS_USING_Z                            , "используя");
		add(CONVERTS_PER_Z                              , "из");
		add(FACE_ANY                                    , "Любая сторона");
		add(FACE_BOTTOM                                 , "Нижняя");
		add(FACE_TOP                                    , "Верхняя");
		add(FACE_LEFT                                   , "Левая");
		add(FACE_FRONT                                  , "Фронтальная");
		add(FACE_RIGHT                                  , "Правая");
		add(FACE_BACK                                   , "Задняя");
		add(FACE_NONE                                   , "Отсутствует");
		add(FACE_SIDES                                  , "Боковая");
		add(FACE_FRONT_BACK                             , "Фронтальная и задняя");
		add(FACE_ANYBUT_FRONT_BACK                      , "Любая, кроме фронтальной и задней");
		add(FACE_ANYBUT_BOTTOM                          , "Любая, кроме нижней");
		add(FACE_ANYBUT_TOP                             , "Любая, кроме верхней");
		add(FACE_ANYBUT_LEFT                            , "Любая, кроме левой");
		add(FACE_ANYBUT_FRONT                           , "Любая, кроме фронтальной");
		add(FACE_ANYBUT_RIGHT                           , "Любая, кроме правой");
		add(FACE_ANYBUT_BACK                            , "Любая, кроме задней");
		add(FACE_ANYBUT_SIDES                           , "Любая, кроме боковых");
		add(REQUIREMENT_AIR_IN_FRONT                    , "Требуется воздух c фронтальной стороны для работы!");
		add(REQUIREMENT_EMPTY_ASHES                     , "Требуется регулярно извлекать пепел!");
		add(REQUIREMENT_IGNITE_FIRE                     , "Требует поджигания от огнива или аналога!");
		add(REQUIREMENT_MOLTEN_CALCITE                  , "Требуется заполнить расплавленным кальцитом!");
		add(REQUIREMENT_WATER                           , "Требуется подача воды!");
		add(REQUIREMENT_WATER_ANY                       , "Требуется подача любой воды!");
		add(REQUIREMENT_WATER_PURE                      , "Требуется любая вода. Используйте дистиллированную воду для лучшей эффективности!");
		add(REQUIREMENT_DISTILLED_WATER                 , "Требуется подача дистиллированной воды!");
		add(REQUIREMENT_CHUNKLOADER                     , "Должен быть в загруженном чанке для правильной работы!");
		add(REQUIREMENT_UNSTACKED                       , "Не работает когда сложено!");
		add(EMITS_USED_STEAM                            , "Выпускает использованный пар");
		add(EMITS_REDSTONE_FLUX                         , "Может излучать RF без потерь");
		add(EMITS_REDSTONE_FLUX_LOSS                    , "Может излучать RF с потерями");
		add(ACCEPTS_REDSTONE_FLUX                       , "Может принимать RF без потерь");
		add(ACCEPTS_REDSTONE_FLUX_LOSS                  , "Может принимать RF с потерями");
		add(NO_GUI_CLICK_TO_LIMIT                       , "Нажмите по стороне для выставления лимита (При приседании чувствительность выше)");
		add(NO_GUI_CLICK_TO_INTERACT                    , "Нет ГИ. Нажмите для взаимодействия!");
		add(NO_GUI_CLICK_TO_INVENTORY                   , "Нет ГИ. Нажмите для размещения/извлечения предметов!");
		add(NO_GUI_CLICK_TO_TANK                        , "Нет ГИ. Нажмите по жидкостному контейнеру для взаимодействия!");
		add(NO_GUI_FUNNEL_TAP_TO_TANK                   , "Нет ГИ. Используйте миниворонки и краники для взаимодействия!");
		add(NO_GUI_FUNNEL_TO_TANK                       , "Нет ГИ. Используйте миниворонки для взаимодействия!");
		add(NO_GUI_TAP_TO_TANK                          , "Нет ГИ. Используйте краник для взаимодействия!");
		add(NO_POWER_CONDUCTING_FLUIDS                  , "Все жидкостные проводники будут опустошены!");
		add(OWNER_CONTROLLED                            , "С этим блоком может взаимодействовать только его владелец!");
		add(CHEAP_OVERCLOCKING                          , "Может быть разогнан без дополнительной потери энергии");
		add(KEY_CONTROLLED                              , "Этот блок можно открыть только с помощью ключа!");
		add(COVER_TOOLTIP                               , "Этот предмет можно использовать как покрытие");
		add(TOOL_TO_REMOVE_SHOVEL                       , "Используйте лопату для очистки");
		add(TOOL_TO_CHANGE_DESIGN_CHISEL                , "Используйте долото, чтобы изменить дизайн");
		add(TOOL_TO_TOGGLE_CONTROLLER_COVER             , "Используйте покрытие контроллер для вкл/выкл");
		add(TOOL_TO_TOGGLE_SCREWDRIVER                  , "Используйте отвертку для переключения режимов");
		add(TOOL_TO_TOGGLE_MONKEY_WRENCH                , "Используйте разводной ключ для смены режима");
		add(TOOL_TO_TOGGLE_CUTTER                       , "Используйте кусачки для смены режима");
		add(TOOL_TO_OPEN_CROWBAR                        , "Используйте монтировку, чтобы открыть это");
		add(TOOL_TO_UNCOVER_CROWBAR                     , "Используйте монтировку, чтобы снять покрытие");
		add(TOOL_TO_DECALCIFY_CHISEL                    , "Используйте долото для удаления накипи");
		add(TOOL_TO_DETAIL_MAGNIFYINGGLASS              , "Используйте лупу для просмотра деталей");
		add(TOOL_TO_MEASURE_GEIGER_COUNTER              , "Используйте счетчик Гейгера для измерения");
		add(TOOL_TO_MEASURE_THERMOMETER                 , "Используйте термометр для измерения");
		add(TOOL_TO_ACCESS_SCOOP                        , "Используйте сачек для извлечения шмелей");
		add(TOOL_TO_TOGGLE_INPUTS_MONKEY_WRENCH         , "Используйте разводной ключ для переключения входов");
		add(TOOL_TO_TOGGLE_OUTPUTS_MONKEY_WRENCH        , "Используйте разводной ключ для переключения выходов");
		add(TOOL_TO_TOGGLE_AUTO_INPUTS_MONKEY_WRENCH    , "Используйте разводной ключ для переключения автоматических входов");
		add(TOOL_TO_TOGGLE_AUTO_OUTPUTS_MONKEY_WRENCH   , "Используйте разводной ключ для переключения автоматических выходов");
		add(TOOL_TO_TOGGLE_SOFT_HAMMER                  , "Используйте киянку для переключения состояний");
		add(TOOL_TO_RESET_SOFT_HAMMER                   , "Используйте киянку для сброса");
		add(TOOL_TO_TAPE                                , "Используйте клейкую ленту, чтобы делать все, что может делать клейкая лента!");
		add(TOOL_TO_UNTAPE                              , "Используйте ножницы или ножи, чтобы удалить ленту");
		add(TOOL_TO_SET_INPUT_MONKEY_WRENCH             , "Используйте разводной ключ, чтобы установить входную сторону");
		add(TOOL_TO_SET_OUTPUT_MONKEY_WRENCH            , "Используйте разводной ключ, чтобы установить выходную сторону");
		add(TOOL_TO_SET_DIRECTION_MONKEY_WRENCH         , "Используйте разводной ключ, чтобы установить направление");
		add(TOOL_TO_SET_FACING_PRE                      , "Используйте ");
		add(TOOL_TO_SET_FACING_POST                     , " для настройки ориентации");
		add(TOOL_TO_SET_FACING2_PRE                     , "Используйте ");
		add(TOOL_TO_SET_FACING2_POST                    , " для настройки вторичной ориентации");
		add(TOOL_TO_SET_CONNECTIONS_PRE                 , "Используйте ");
		add(TOOL_TO_SET_CONNECTIONS_POST                , " для настройки соединения");
		add(TOOL_TO_HARVEST                             , "Инструмент для добычи");
		add(TOOL_TO_TAKE_PINCERS                        , "Используйте клещи для извлечения предметов");
		add(TOOL_HINT_USE_SNEAK                         , "Используйте инструмент и приседания для дополнительных опций");
		add(WEAPON_SNEAK_RIGHTCLICK_TO_RELOAD           , "Приседание и ПКМ для перезарядки");
		add(WIRE_STATS_LOSSLESS                         , "Передает энергию без потерь");
		add(WIRE_STATS_LOSS                             , "Потери: ");
		add(WIRE_STATS_VOLTAGE                          , "Напряжение: ");
		add(WIRE_STATS_AMPERAGE                         , "Сила тока: ");
		add(PIPE_STATS_LOSS                             , "Потери: ");
		add(PIPE_STATS_RANGE                            , "Дистанция: ");
		add(PIPE_STATS_STEPSIZE                         , "Размер шага: ");
		add(PIPE_STATS_BANDWIDTH                        , "Пропускная способность: ");
		add(PIPE_STATS_CAPACITY                         , "Вместимость: ");
		add(PIPE_STATS_AMOUNT                           , "Количество труб: ");
		add(AXLE_STATS_SPEED                            , "Скорость: ");
		add(AXLE_STATS_POWER                            , "Сила: ");
		add(HAZARD_FIRE                                 , "Может поджечь блоки находящиеся рядом!");
		add(HAZARD_EXPLOSION_STEAM                      , "Взрывается, когда давление пара слишком велико!");
		add(HAZARD_MELTDOWN                             , "Плавится при перегреве!");
		add(HAZARD_CONTACT                              , "Наносит урон при прикосновении во время работы!");
		add(HAZARD_LEAKING_GAS                          , "Утечки при использовании с газами!");
		add(TOOLTIP_GASPROOF                            , "Может работать с газами");
		add(TOOLTIP_ACIDPROOF                           , "Может работать с ксилотами");
		add(TOOLTIP_MAGICPROOF                          , "Может рабобтать с магией");
		add(TOOLTIP_LIQUIDPROOF                         , "Может работать с жидкостями");
		add(TOOLTIP_PLASMAPROOF                         , "Может рабобтать с плазмой");
		add(TOOLTIP_HEATPROOF                           , "Может работать с температурами до: ");
		add(TOOLTIP_THERMALMASS                         , "Тепловая масса: ");
		add(TOOLTIP_ONLY_SIMPLE                         , "Принимает только простые жидкости!");
		add(TOOLTIP_REMINDER_EXTENDERS                  , "Не забудьте использовать универсальные расширители, если вам нужно буквально обрезать углы");
		add(TOOLTIP_SEALABLE_ANY                        , "Этот блок может запечатать воздух на любой стороне");
		add(TOOLTIP_SEALABLE_SOME                       , "Этот блок может запечатать воздух на некоторых сторонах");
		add(TOOLTIP_SEALABLE_BUGGED                     , "Не должен запечатывать воздух, но иногда делает, потому что непрозрачный");
		add(TOOLTIP_PISTONPUSHABLE                      , "Поршни могут двигать этот блок");
		add(TOOLTIP_SPAWNPROOF                          , "Мобы не могут появиться на этом блоке");
		add(TOOLTIP_SPAWNPROOF_OPTIFINE                 , "Защита от мобов сломана, потому что Optifine удалил поддержку метаданных из Vanilla.");
		add(TOOLTIP_SPAWNPROOF_MP_BUG                   , "Мобы должны появляться в этом блоке, даже если в NEI указано обратное.");
		add(TOOLTIP_SPAWNPROOF_MP_BROKEN                , "Мобы не могут появиться на этом блоке, даже если NEI говорит обратное.");
		add(TOOLTIP_SPAWNPROOF_SP_BUG                   , "Мобы не могут, но должны появляться на этом блоке.");
		add(TOOLTIP_SPAWNPROOF_SP_BROKEN                , "Мобы могут, но не должны появляться на этом блоке.");
		add(TOOLTIP_BLASTPOWER                          , "Сила взрыва: ");
		add(TOOLTIP_BLASTRANGE                          , "Радиус взрыва: ");
		add(TOOLTIP_BLASTFORTUNE                        , "Уровень удачи взрыва: ");
		add(TOOLTIP_BLASTRESISTANCE                     , "Взрывоустойчивость: ");
		add(TOOLTIP_RAILSPEED                           , "Скорость путей: ");
		add(TOOLTIP_WALKSPEED                           , "Этот блок изменяет скорость ходьбы");
		add(TOOLTIP_GRAVITY                             , "На этот блок влияет гравитация");
		add(TOOLTIP_NEEDS_HANDLE                        , "Необходима рукоять: ");
		add(TOOLTIP_NEEDS_SHARPENING                    , "Перед использованием необходимо заточить");
		add(TOOLTIP_SHAPELESS_CRAFT                     , "Имеет бесполезные рецепты в колличестве: ");
		add(TOOLTIP_AUTOCOLLECT                         , "Может автоматически собирать предметы при добыче");
		add(TOOLTIP_ARMOR_PENETRATING                   , "Может пробить броню");
		add(TOOLTIP_BEACON_PAYMENT                      , "Может использоваться в качестве оплаты за маяк");
		add(TOOLTIP_SHELFABLE                           , "Может быть помещен в книжную полку GT");
		add(TOOLTIP_SANDWICHABLE                        , "Является допустимым ингредиентом для сэндвичей");
		add(TOOLTIP_POSSIBLE_ENCHANTS                   , "Возможные чары: ");
		add(TOOLTIP_POSSIBLE_TOOL_ENCHANTS              , "Инструмент: ");
		add(TOOLTIP_POSSIBLE_WEAPON_ENCHANTS            , "Оружие: ");
		add(TOOLTIP_POSSIBLE_AMMO_ENCHANTS              , "Патроны: ");
		add(TOOLTIP_POSSIBLE_RANGED_ENCHANTS            , "Дальнобойное: ");
		add(TOOLTIP_POSSIBLE_FISHING_ENCHANTS           , "Рыбалка: ");
		add(TOOLTIP_POSSIBLE_ARMOR_ENCHANTS             , "Броня: ");
		add(TOOLTIP_TOO_MANY_TOOL_ENCHANTS              , "Слишком много чар для инструментов в списке");
		add(TOOLTIP_TOO_MANY_ARMOR_ENCHANTS             , "Слишком много чар для брони в списке");
		add(TOOLTIP_CONTAINED_MATERIALS                 , "Содержит материалы:");
		add(TOOLTIP_FLAMMABLE_AND_EXPLOSIVE             , "Взрыво- и огнеопасно!");
		add(TOOLTIP_FLAMMABLE                           , "Огнеопасно!");
		add(TOOLTIP_EXPLOSIVE                           , "Взрывоопасно!");
		add(TOOLTIP_UNBURNABLE                          , "Несгораемый!");
		add(TOOLTIP_BLAST_RESISTANCE_TERRIBLE           , "(Ужасная)");
		add(TOOLTIP_BLAST_RESISTANCE_GHAST              , "(Гастоустойчивый)");
		add(TOOLTIP_BLAST_RESISTANCE_CREEPER            , "(Крипероустойчивый)");
		add(TOOLTIP_BLAST_RESISTANCE_TNT                , "(Динамитоустойчивый)");
		add(TOOLTIP_BLAST_RESISTANCE_DYNAMITE           , "(Устойчив к сильному динамиту)");
		add(TOOLTIP_BLAST_RESISTANCE_NOT_NUKE           , "(Ядерное оружие IC2 все еще может пробить!)");
		add(TOOLTIP_ENCHANT_BONUS                       , "Может влиять на стол зачарования, как книжная полка");
		add(TOOLTIP_THAUMCRAFT_WARP                     , "Инструменты, сделанные из этого материала, вызывают телесное искажение владельца");
		add(TOOLTIP_BETWEENLANDS_RESISTANCE             , "Устойчив к воздействию эффектов из Betweenlands");
		add(TOOLTIP_TWILIGHT_MAZE_BREAKING              , "Инструменты, сделанные из этого материала, могут сломать лабиринты Twilight Twilight");
		add(TOOLTIP_TWILIGHT_MAZE_HEDGE_BREAKING        , "Может легко сломать живую изгородь лабиринта Twilight Twilight");
		add(TOOLTIP_TWILIGHT_MAZE_STONE_BREAKING        , "Можно легко сломать Twilight Forest Mazestone");
		add(TOOLTIP_TWILIGHT_TOWER_WOOD_BREAKING        , "Можно легко сломать Twilight Forest Towerwood");
		add(PROSPECTING_LAVA                            , "За этим камнем есть лава");
		add(PROSPECTING_LIQUID                          , "За этим камнем есть жидкость");
		add(PROSPECTING_AIR                             , "За этим камнем есть воздушный карман");
		add(PROSPECTING_CHANGE                          , "Материал меняется за этим камнем");
		add(PROSPECTING_TRACES                          , "Найдены следы от ");
		add(PROSPECTING_NOTHING                         , "Не найдено следов руды");
		add(AUTOCRAFTING_INSERT_BLUEPRINT               , "Установите сюда чертёж автокрафта");
		add(ADVCRAFTING_INSERT_BLUEPRINT                , "Установите чертеж или настройщик в режиме от 2 до 9");
		add(ADVCRAFTING_PUT_TO_STORAGE                  , "Перемещение содержимого сетки крафта в слоты хранения");
		add(ADVCRAFTING_AUTOMATION_ACCESS               , "Разрешить автоматическое извлечение из сетки крафта");
		add(ADVCRAFTING_NEUTRAL_SLOT                    , "Этот слот не делает АБСОЛЮТНО НИЧЕГО с его содержимым!");
		add(ADVCRAFTING_DROP_SLOT                       , "Может автоматически извлекать предметы из этого слота");
		add(TIME_TICK                                   , "Тик");
		add(TIME_SEC                                    , "Секунда");
		add(TIME_MIN                                    , "Минута");
		add(TIME_HOUR                                   , "Час");
		add(TIME_DAY                                    , "День");
		add(TIME_WEEK                                   , "Неделя");
		add(TIME_TICKS                                  , "Тика(ов)");
		add(TIME_SECS                                   , "Секунд(ы)");
		add(TIME_MINS                                   , "Минут(ы)");
		add(TIME_HOURS                                  , "Часа(ов)");
		add(TIME_DAYS                                   , "Дня(ей)");
		add(TIME_WEEKS                                  , "Недели(ь)");
		add(ADMIN_ONLY_CREATION                         , "Только администраторы могут это создать. (или используйте мод MineTweaker для добавления рецепта)");
		add(WIP                                         , Chat.RESET + Chat.WHITE + Chat.BOLD + "WIP" + Chat.RESET_TOOLTIP + ", Это может быть не так функционально, как вы ожидаете!");
	}
	
	public static class Chat {
		public static final String
		   BLACK          = EnumChatFormatting.BLACK.toString()
		,  DBLUE          = EnumChatFormatting.DARK_BLUE.toString()
		,  DGREEN         = EnumChatFormatting.DARK_GREEN.toString()
		,  DCYAN          = EnumChatFormatting.DARK_AQUA.toString()
		,  DRED           = EnumChatFormatting.DARK_RED.toString()
		,  PURPLE         = EnumChatFormatting.DARK_PURPLE.toString()
		,  ORANGE         = EnumChatFormatting.GOLD.toString()
		,  GOLD           = EnumChatFormatting.GOLD.toString()
		,  GRAY           = EnumChatFormatting.GRAY.toString()
		,  DGRAY          = EnumChatFormatting.DARK_GRAY.toString()
		,  BLUE           = EnumChatFormatting.BLUE.toString()
		,  GREEN          = EnumChatFormatting.GREEN.toString()
		,  CYAN           = EnumChatFormatting.AQUA.toString()
		,  RED            = EnumChatFormatting.RED.toString()
		,  PINK           = EnumChatFormatting.LIGHT_PURPLE.toString()
		,  YELLOW         = EnumChatFormatting.YELLOW.toString()
		,  WHITE          = EnumChatFormatting.WHITE.toString()
		,  OBFUSCATED     = EnumChatFormatting.OBFUSCATED.toString()
		,  BOLD           = EnumChatFormatting.BOLD.toString()
		,  STRIKETHROUGH  = EnumChatFormatting.STRIKETHROUGH.toString()
		,  UNDERLINE      = EnumChatFormatting.UNDERLINE.toString()
		,  ITALIC         = EnumChatFormatting.ITALIC.toString()
		,  RESET          = EnumChatFormatting.RESET.toString()
		,  RESET_TOOLTIP  = RESET + GRAY
		;
		
		public static final String
		  _BLACK          = " " + BLACK
		, _DBLUE          = " " + DBLUE
		, _DGREEN         = " " + DGREEN
		, _DCYAN          = " " + DCYAN
		, _DRED           = " " + DRED
		, _PURPLE         = " " + PURPLE
		, _ORANGE         = " " + ORANGE
		, _GOLD           = " " + GOLD
		, _GRAY           = " " + GRAY
		, _DGRAY          = " " + DGRAY
		, _BLUE           = " " + BLUE
		, _GREEN          = " " + GREEN
		, _CYAN           = " " + CYAN
		, _RED            = " " + RED
		, _PINK           = " " + PINK
		, _YELLOW         = " " + YELLOW
		, _WHITE          = " " + WHITE
		, _OBFUSCATED     = " " + OBFUSCATED
		, _BOLD           = " " + BOLD
		, _STRIKETHROUGH  = " " + STRIKETHROUGH
		, _UNDERLINE      = " " + UNDERLINE
		, _ITALIC         = " " + ITALIC
		, _RESET          = " " + RESET
		, _RESET_TOOLTIP  = " " + RESET_TOOLTIP
		;
		
		public static String
		  RAINBOW_FAST = BLACK
		, RAINBOW = BLACK
		, RAINBOW_SLOW = BLACK
		, BLINKING_CYAN = CYAN
		, BLINKING_RED = RED
		, BLINKING_ORANGE = ORANGE
		, BLINKING_GRAY = GRAY
		;
	}
}
