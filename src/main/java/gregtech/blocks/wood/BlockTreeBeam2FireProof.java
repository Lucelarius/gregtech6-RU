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

package gregtech.blocks.wood;

import gregapi.block.tree.BlockBaseBeam;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeam2FireProof extends BlockBaseBeam {
	public BlockTreeBeam2FireProof(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 4, Textures.BlockIcons.BEAMS_2);
		
		LH.add(getUnlocalizedName()+ ".0", "Брус из акации (Огнеупорный)");
		LH.add(getUnlocalizedName()+ ".4", "Брус из акации (Огнеупорный)");
		LH.add(getUnlocalizedName()+ ".8", "Брус из акации (Огнеупорный)");
		LH.add(getUnlocalizedName()+".12", "Брус из акации (Огнеупорный)");
		
		LH.add(getUnlocalizedName()+ ".1", "Брус из темного дуба (Огнеупорный)");
		LH.add(getUnlocalizedName()+ ".5", "Брус из темного дуба (Огнеупорный)");
		LH.add(getUnlocalizedName()+ ".9", "Брус из темного дуба (Огнеупорный)");
		LH.add(getUnlocalizedName()+".13", "Брус из темного дуба (Огнеупорный)");
		
		LH.add(getUnlocalizedName()+ ".2", "Брус из каучукого дерева (Огнеупорный)");
		LH.add(getUnlocalizedName()+ ".6", "Брус из каучукого дерева (Огнеупорный)");
		LH.add(getUnlocalizedName()+".10", "Брус из каучукого дерева (Огнеупорный)");
		LH.add(getUnlocalizedName()+".14", "Брус из каучукого дерева (Огнеупорный)");
		
		LH.add(getUnlocalizedName()+ ".3", "Брус из дерева (Огнеупорный)");
		LH.add(getUnlocalizedName()+ ".7", "Брус из дерева (Огнеупорный)");
		LH.add(getUnlocalizedName()+".11", "Брус из дерева (Огнеупорный)");
		LH.add(getUnlocalizedName()+".15", "Брус из дерева (Огнеупорный)");
	}
}
