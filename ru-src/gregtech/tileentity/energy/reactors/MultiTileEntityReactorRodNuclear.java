/**
 * Copyright (c) 2022 GregTech-6 Team
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

package gregtech.tileentity.energy.reactors;

import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodNuclear extends MultiTileEntityReactorRodBase {
	public long mDurability = 0;
	public int mNeutronSelf = 128, mNeutronOther = 128, mNeutronDiv = 8, mNeutronMax = 128;
	public short mDepleted = -1;
	public boolean mModerated = F, oModerated = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mDurability = aNBT.getLong(aNBT.hasKey(NBT_DURABILITY) ? NBT_DURABILITY : NBT_MAXDURABILITY);
		if (aNBT.hasKey(NBT_NUCLEAR_SELF    )) mNeutronSelf  = aNBT.getInteger(NBT_NUCLEAR_SELF );
		if (aNBT.hasKey(NBT_NUCLEAR_OTHER   )) mNeutronOther = aNBT.getInteger(NBT_NUCLEAR_OTHER);
		if (aNBT.hasKey(NBT_NUCLEAR_DIV     )) mNeutronDiv   = aNBT.getInteger(NBT_NUCLEAR_DIV  );
		if (aNBT.hasKey(NBT_NUCLEAR_MAX     )) mNeutronMax   = aNBT.getInteger(NBT_NUCLEAR_MAX);
		if (aNBT.hasKey(NBT_NUCLEAR_MOD     )) mModerated    = aNBT.getBoolean(NBT_NUCLEAR_MOD);
		if (aNBT.hasKey(NBT_NUCLEAR_MOD+".o")) oModerated    = aNBT.getBoolean(NBT_NUCLEAR_MOD+".o");
		if (aNBT.hasKey(NBT_VALUE           )) mDepleted     = aNBT.getShort(NBT_VALUE);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_DURABILITY, mDurability);
		UT.NBT.setBoolean(aNBT, NBT_NUCLEAR_MOD, mModerated);
		UT.NBT.setBoolean(aNBT, NBT_NUCLEAR_MOD+".o", oModerated);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_DURABILITY, mDurability);
		UT.NBT.setBoolean(aNBT, NBT_NUCLEAR_MOD, mModerated);
		UT.NBT.setBoolean(aNBT, NBT_NUCLEAR_MOD+".o", oModerated);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.DGRAY + "Используется в активной зоне ядерного реактора.");
		aList.add(LH.Chat.GREEN + "Эмиссия -" + LH.Chat.CYAN + " описывает сколько нейтронов излучается соседним стержням.");
		aList.add(LH.Chat.GREEN + "ПН -" + LH.Chat.CYAN + " описывается сколько нейтронов присутсвует на самом стержне.");
		aList.add(LH.Chat.GREEN + "Максимум -" + LH.Chat.CYAN + " описывает сколько нейтронов может находиться на этом стержне в течение заявленного времени.");
		aList.add(LH.Chat.CYAN + "Больший " + LH.Chat.YELLOW + "фактор" + LH.Chat.CYAN + " означает, что стержень излучает больше дополнительных нейтронов на кол-во нейтронов на нем.");
		if (mModerated || oModerated) aList.add(LH.Chat.DBLUE + "Это топливо " + LH.Chat.WHITE + "замедляется.");
		aList.add(LH.Chat.CYAN + "Осталось: " + LH.Chat.WHITE + (mDurability / 120000) + LH.Chat.CYAN + " Минут");
		switch ((int) ((CLIENT_TIME / 100) % 10)) {
			case 0:
				aList.add(LH.Chat.CYAN + "При использовании дистилированной или полутяжелой воды:");
				aList.add(LH.Chat.GREEN + "Эмиссия: " + LH.Chat.WHITE + mNeutronOther + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "ПН: " + LH.Chat.WHITE + mNeutronSelf + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "Максимум: " + LH.Chat.WHITE + mNeutronMax + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.YELLOW + "Фактор: " + LH.Chat.WHITE + "1/" + mNeutronDiv);
				aList.add(LH.Chat.GREEN + "Эти стержни " + LH.Chat.WHITE + "замедляются");
				if (mNeutronDiv <= 4) aList.add(LH.Chat.RED + "Это топливо имеет" + LH.Chat.BLINKING_RED + " решающее значение");
				break;
			case 1:
				aList.add(LH.Chat.CYAN + "При использовании тяжелой воды:");
				aList.add(LH.Chat.GREEN + "Эмиссия: " + LH.Chat.WHITE + mNeutronOther + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "ПН: " + LH.Chat.WHITE + mNeutronSelf + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "Максимум: " + LH.Chat.WHITE + UT.Code.divup(mNeutronMax, 8) + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.YELLOW + "Фактор: " + LH.Chat.WHITE + "1/" + mNeutronDiv);
				aList.add(LH.Chat.GREEN + "Эти стержни " + LH.Chat.WHITE + "замедляются");
				if (mNeutronDiv <= 4) aList.add(LH.Chat.RED + "Это топливо имеет" + LH.Chat.BLINKING_RED + " решающее значение");
				break;
			case 2:
				aList.add(LH.Chat.CYAN + "При использовании тритиевой воды:");
				aList.add(LH.Chat.GREEN + "Эмиссия: " + LH.Chat.WHITE + mNeutronOther + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "ПН: " + LH.Chat.WHITE + mNeutronSelf + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "Максимум: " + LH.Chat.WHITE + UT.Code.divup(mNeutronMax, 16) + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.YELLOW + "Фактор: " + LH.Chat.WHITE + "1/" + mNeutronDiv);
				aList.add(LH.Chat.GREEN + "Эти стержни " + LH.Chat.WHITE + "замедляются");
				if (mNeutronDiv <= 4) aList.add(LH.Chat.RED + "Это топливо имеет" + LH.Chat.BLINKING_RED + " решающее значение");
				break;
			case 3:
				aList.add(LH.Chat.CYAN + "При использовании расплавленного олова:");
				aList.add(LH.Chat.GREEN + "Эмиссия: " + LH.Chat.WHITE + mNeutronOther + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "ПН: " + LH.Chat.WHITE + mNeutronSelf + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "Максимум: " + LH.Chat.WHITE + mNeutronMax + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.YELLOW + "Фактор: " + LH.Chat.WHITE + "1/" + (mNeutronDiv - 1));
				aList.add(LH.Chat.GREEN + "1/3 тепла на нейтрон");
				if (mNeutronDiv <= 5) aList.add(LH.Chat.RED + "Это топливо имеет" + LH.Chat.BLINKING_RED + " решающее значение");
				break;
			case 4:
				aList.add(LH.Chat.CYAN + "При использовании расплавленного натрия:");
				aList.add(LH.Chat.GREEN + "Эмиссия: " + LH.Chat.WHITE + mNeutronOther + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "ПН: " + LH.Chat.WHITE + mNeutronSelf + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "Максимум: " + LH.Chat.WHITE + mNeutronMax + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.YELLOW + "Фактор: " + LH.Chat.WHITE + "1/" + (mNeutronDiv - 1));
				aList.add(LH.Chat.GREEN + "1/6 тепла на нейтрон");
				if (mNeutronDiv <= 5) aList.add(LH.Chat.RED + "Это топливо имеет" + LH.Chat.BLINKING_RED + " решающее значение");
				break;
			case 5:
				aList.add(LH.Chat.CYAN + "При использовании хладагента:");
				aList.add(LH.Chat.GREEN + "Эмиссия: " + LH.Chat.WHITE + mNeutronOther * 4 + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "ПН: " + LH.Chat.WHITE + mNeutronSelf * 4 + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "Максимум: " + LH.Chat.WHITE + mNeutronMax + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.YELLOW + "Фактор: " + LH.Chat.WHITE + "1/" + mNeutronDiv * 2);
				if (mNeutronDiv <= 2) aList.add(LH.Chat.RED + "Это топливо имеет" + LH.Chat.BLINKING_RED + " решающее значение");
				break;
			case 6:
				aList.add(LH.Chat.CYAN + "При использовании расплавленного хлорида лития:");
				aList.add(LH.Chat.GREEN + "Эмиссия: " + LH.Chat.WHITE + (mNeutronOther - UT.Code.divup(mNeutronOther, 2)) + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "ПН: " + LH.Chat.WHITE + (mNeutronSelf * 5) + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "Максимум: " + LH.Chat.WHITE + (mNeutronMax + UT.Code.divup(mNeutronMax, 4)) + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.YELLOW + "Фактор: " + LH.Chat.WHITE + "1/" + mNeutronDiv);
				if (mNeutronDiv <= 4) aList.add(LH.Chat.RED + "Это топливо имеет" + LH.Chat.BLINKING_RED + " решающее значение");
				break;
			case 7:
				aList.add(LH.Chat.CYAN + "При использовании расплавленного ториевой соли:");
				aList.add(LH.Chat.GREEN + "Эмиссия: " + LH.Chat.WHITE + (mNeutronOther - UT.Code.divup(mNeutronOther, 2)) + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "ПН: " + LH.Chat.WHITE + (mNeutronSelf * 0) + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "Максимум: " + LH.Chat.WHITE + (mNeutronMax * 4) + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.YELLOW + "Фактор: " + LH.Chat.WHITE + "1/" + (mNeutronDiv - 1));
				if (mNeutronDiv <= 5) aList.add(LH.Chat.RED + "Это топливо имеет" + LH.Chat.BLINKING_RED + " решающее значение");
				break;
			case 8:
				aList.add(LH.Chat.CYAN + "При использовании диоксида углерода:");
				aList.add(LH.Chat.GREEN + "Эмиссия: " + LH.Chat.WHITE + mNeutronOther + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "ПН: " + LH.Chat.WHITE + mNeutronSelf * 3 + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "Максимум: " + LH.Chat.WHITE + mNeutronMax + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.YELLOW + "Фактор: " + LH.Chat.WHITE + "1/" + mNeutronDiv);
				if (mNeutronDiv <= 4) aList.add(LH.Chat.RED + "Это топливо имеет" + LH.Chat.BLINKING_RED + " решающее значение");
				break;
			case 9:
				aList.add(LH.Chat.CYAN + "При использовании гелия:");
				aList.add(LH.Chat.GREEN + "Эмиссия: " + LH.Chat.WHITE + (mNeutronOther - UT.Code.divup(mNeutronOther, 2)) + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "ПН: " + LH.Chat.WHITE + mNeutronSelf + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.GREEN + "Максимум: " + LH.Chat.WHITE + mNeutronMax + LH.Chat.PURPLE + " Нейтронов/t");
				aList.add(LH.Chat.YELLOW + "Фактор: " + LH.Chat.WHITE + "1/" + mNeutronDiv);
				if (mNeutronDiv <= 4) aList.add(LH.Chat.RED + "Это топливо имеет" + LH.Chat.BLINKING_RED + " решающее значение");
				break;
		}
	}
	
	@Override
	// Gets called every 20 Ticks.
	public int getReactorRodNeutronEmission(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		int tNeutronOther = mNeutronOther;
		int tNeutronSelf = mNeutronSelf;
		int tNeutronDiv = mNeutronDiv;
		if (FL.Coolant_IC2.is(aReactor.mTanks[0])) {
			tNeutronOther *= 4;
			tNeutronSelf *= 4;
			tNeutronDiv *= 2;
		} else if (MT.CO2.mGas.isFluidEqual(aReactor.mTanks[0].getFluid())) {
			tNeutronSelf *= 3;
		} else if (MT.He.mGas.isFluidEqual(aReactor.mTanks[0].getFluid())) {
			tNeutronOther -= UT.Code.divup(mNeutronOther, 2);
		} else if (MT.LiCl.mLiquid.isFluidEqual(aReactor.mTanks[0].getFluid())) {
			tNeutronOther -= UT.Code.divup(mNeutronOther, 2);
			tNeutronSelf *= 5;
		} else if (FL.Thorium_Salt.is(aReactor.mTanks[0])) {
			tNeutronOther -= UT.Code.divup(mNeutronOther, 2);
			tNeutronSelf = 0;
			tNeutronDiv -= 1;
		} else if (MT.Sn.mLiquid.isFluidEqual(aReactor.mTanks[0].getFluid()) || MT.Na.mLiquid.isFluidEqual(aReactor.mTanks[0].getFluid())) {
			tNeutronDiv -= 1;
		}
		aReactor.mNeutronCounts[aSlot] += tNeutronSelf;
		long tEmission = tNeutronOther + UT.Code.divup(Math.max(aReactor.oNeutronCounts[aSlot]-tNeutronSelf, 0), tNeutronDiv);
		return UT.Code.bindInt(tEmission);
	}
	
	@Override
	// Gets called every Tick.
	public boolean getReactorRodNeutronReaction(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		aReactor.mEnergy += aReactor.oNeutronCounts[aSlot];
		int tNeutronMax = getReactorRodNeutronMaximum(aReactor, aSlot, aStack);

		if (FL.distw(aReactor.mTanks[0]) ||
			MT.HDO.mLiquid.isFluidEqual(aReactor.mTanks[0].getFluid()) ||
			MT.D2O.mLiquid.isFluidEqual(aReactor.mTanks[0].getFluid()) ||
			MT.T2O.mLiquid.isFluidEqual(aReactor.mTanks[0].getFluid()))
		{
			mModerated = oModerated = T;
		}
		long tDurabilityLoss = aReactor.oNeutronCounts[aSlot] <= tNeutronMax ? 100 : UT.Code.divup(400 * aReactor.oNeutronCounts[aSlot], tNeutronMax);
		if (oModerated) tDurabilityLoss *= 4;
		mDurability = tDurabilityLoss > mDurability ? -1 : mDurability - tDurabilityLoss;
		UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));

		if (mDurability <= 0) {
			ST.meta(aStack, mDepleted);
			ST.nbt(aStack, null);
			aReactor.updateClientData();
		}
		return T;
	}
	
	@Override
	// Gets called every 20 Ticks.
	public int getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons, boolean aModerated) {
		if (aModerated) {
			mModerated = T;
			UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		}
		aReactor.mNeutronCounts[aSlot] += aNeutrons;
		return 0;
	}

	@Override
	public int getReactorRodNeutronMaximum(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		if (MT.LiCl.mLiquid.isFluidEqual(aReactor.mTanks[0].getFluid())) {
			return mNeutronMax + (int) UT.Code.divup(mNeutronMax, 4);
		} else if (FL.Thorium_Salt.is(aReactor.mTanks[0])) {
			return mNeutronMax * 4;
		} else if (MT.D2O.mLiquid.isFluidEqual(aReactor.mTanks[0].getFluid())) {
			return (int) UT.Code.divup(mNeutronMax, 8);
		} else if (MT.T2O.mLiquid.isFluidEqual(aReactor.mTanks[0].getFluid())) {
			return (int) UT.Code.divup(mNeutronMax, 16);
		} else {
			return mNeutronMax;
		}
	}

	@Override
	public boolean isModerated(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		return oModerated;
	}

	@Override
	public void updateModeration(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		oModerated = mModerated;
		mModerated = F;
		UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
	}

	@Override public ITexture getReactorRodTextureSides(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa, T), BlockTextureDefault.get(sOverlays[1], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}
	@Override public ITexture getReactorRodTextureTop  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[0], mRGBa, T), BlockTextureDefault.get(sOverlays[0], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.rods.nuclear";}
}
