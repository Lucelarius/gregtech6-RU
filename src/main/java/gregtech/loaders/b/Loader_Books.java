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

package gregtech.loaders.b;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.configurations.IOreDictConfigurationComponent;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.List;

import static gregapi.data.CS.*;

@SuppressWarnings("ALL")
public class Loader_Books implements Runnable {
	@Override
	public void run() {
		String tPage = "";
		List<String> tBook = new ArrayListNoNulls<>();

		UT.Books.createWrittenBook("Manual_Punch_Cards", "Руководство по перфокартам V0.0", "Gregorius Techneticies", ST.make(ItemsGT.BOOKS, 1, 1), new String[] {
		  "В этом руководстве будут объяснены функциональные возможности перфокарт после их полного внедрения. И нет, они не будут похожи на перфокарты IRL. Это всего лишь текущая коллекция идей."
		, "(i1&&i2)?o1=15:o1=0;=10"
		, "Чтобы игнорировать все пробельные символы, используйте 'Long' для сохранения чисел."
		, "&& || ^^ & | ^ ! ++ -- + - % / // * ** << >> >>> < > <= >= == !=  ~ ( ) ?: , ; ;= ;=X; = i0 i1 i2 i3 i4 i5 o0 o1 o2 o3 o4 o5 v0 v1 v2 v3 v4 v5 v6 v7 v8 v9 m0 m1 m2 m3 m4 m5 m6 m7 m8 m9 A B C D E F"
		, "'0' = ложь, 'все, кроме 0' = правда, '!' превращает «0» в «1», а все остальное в «0»."
		, "',' — это просто разделитель нескольких выполняемых подряд кодов."
		, "';' означает, что программа ждет следующего тика, прежде чем продолжить. ';=10' и ';=10;' оба означают, что он будет ждать 10 тиков вместо 1. И ';=0' или что-нибудь <0 по умолчанию будет равно 0."
		, "Если оператор '=' используется в скобках, он возвращает значение, присвоенное переменной."
		, "Программа сохраняет индекс символов текущей задачи, 10 переменных (которые сбрасываются в 0, как только цикл программы останавливается), 10 переменных-членов и оставшееся время ожидания в своем NBT."
		, "A = 10, B = 11, C = 12, D = 13, E = 14, F = 15, просто для экономии шестнадцатеричного пространства, поскольку в 'Красной пыли' всего 4 бита."
		, "Для реализации циклов вам нужна всего лишь 1 перфокарта на цикл. Эти карты могут перезапуститься после завершения, в зависимости от того, сколько других карт находится в программном цикле, в который вы вставили свою карту, поскольку он будет обрабатывать их процедурно."
		, "Процессор перфокарт может параллельно запускать до четырех циклов, каждый длиной в семь перфокарт."
		, "Вы спросите, почему для изготовления перфокарты нужны чернила? Потому что на пустом необходимо иметь несколько строк, а на перфорированном он печатает код для выполнения в 'человеческом' читаемом формате на карте."
		});

		UT.Books.createWrittenBook("Manual_Microwave", "Руководство по эксплуатации микроволновой печи", "Kitchen Industries", ST.make(ItemsGT.BOOKS, 1, 2), new String[] {
		  "Поздравляем, вы вставили в микроволновую печь случайную, казалось бы, пустую книгу, и эти буквы появились из ниоткуда."
		, "Вы только что купили микроволновую печь и спросили себя: 'Зачем она мне вообще нужна?'. Все просто: микроволновая печь может готовить всего за 128 EU и с безумной скоростью. Даже обычная электронная печь не сможет сделать это так быстро и дешево!"
		, "Это самый дешевый и быстрый способ приготовить для вас. Именно поэтому микроволновую печь можно найти практически на каждой кухне (см. www.youwannabuyakitchen.ly)."
		, "Длительное воздействие микроволн может вызвать рак, но мы сомневаемся, что Стив проживет достаточно долго, чтобы умереть из-за этого."
		, "Не помещайте металлы. Это может привести к взрыву."
		, "Не сушите ей животных. В результате получится хот-дог, независимо от того, какое животное вы в нее положите."
		, "Не помещайте легковоспламеняющиеся предметы. Печь загорится."
		, "Не помещайте взрывчатые вещества, например яйца. Просто не надо."
		});

		UT.Books.createWrittenBook("Manual_Printer", "Руководство по сканерам и принтерам", "GregTech Electrics", ST.make(ItemsGT.BOOKS, 1, 32000), new String[] {
		  "Поздравляем с приобретением нового сканера и/или принтера. Данное руководство, полученное при покупке, окажется для вас очень полезным. Просто помните, что если вы потеряете его, вы можете просто позволить принтеру создать новое."
		, "Прежде всего, вам необходимо снабдить сканер и принтер электричеством на задней стороне. Просто подключите их к розеткам соответствующего напряжения. Далее для их подключения вам понадобится USB-кабель или USB-накопитель."
		, "Подойдет любой USB-накопитель, изображения и текстовые файлы не такие большие и не требуют более USB 1.0, хотя вы, конечно, можете использовать и более высокие уровни USB благодаря совместимости с нисходящими версиями."
		, "Теперь вам нужно пополнить запасы чернил для принтера, в зависимости от того, какую работу вы хотите с ними выполнить. Для этой цели он принимает только химические красители. Черный = текст, CMYK для изображений, синий или белый для чертежей."
		, "Сделав все это, пришло время сканировать то, что вам нужно, внутри сканера. Просто подключите USB-накопитель и вставьте книгу/холст/чертеж и т. д., которые хотите отсканировать. Если вы вставите блоки, он также просканирует их поверхность."
		, "После успешного сканирования он извлечет USB-накопитель и отсканированный объект. Теперь вы можете вставить данные и нужный тип бумаги/холста в принтер, чтобы он мог печатать ваши продукты."
		, "Принтер может выполнять несколько больше заданий на печать, чем просто холст и книги. NEI поможет вам со всеми возможными рецептами."
		});

		UT.Books.createWrittenBook("Manual_Steam", "Паровое руководство", "GregTech Thermodynamics", ST.make(ItemsGT.BOOKS, 1, 32000), new String[] {
		  "Данное руководство посвящено правильной эксплуатации котла, чтобы не взорвать себя. И да, есть много людей, которые взрываются из-за неправильной эксплуатации котла."
		, "Прежде всего вам нужно убедиться, что используемый вами котел подходит к камере скорания, которую вы размещаете ниже. Подсказки подскажут вам правильные значения, которые вы можете использовать. Сам котел принимает тепло со всех сторон, а не только снизу."
		, "Затем вам нужно заполнить котел водой по сторонам, чтобы с его помощью можно было генерировать пар. Разместив на нем воронку и щелкнув ПКМ по этой воронке с ведром для воды, вы также сможете заполнить его."
		, "Если вы используете обычную воду вместо дистиллированной, то в конечном итоге вы получите кальцификацию и постоянное снижение эффективности парообразования. Однако из-за этого нет риска взрыва, поскольку он просто производит меньше пара на воду."
		, "Поместив воду в пустой горячий котел, давление быстро увеличится, что может привести к взрыву, в зависимости от того, сколько тепла и воды вы поместите одновременно."
		, "Если котел накапливает слишком много тепла, не имея возможности преобразовывать воду в пар, он просто расплавится и сломается, оставив вас в большом беспорядке."
		, "Паровые турбины и двигатели выбрасывают дистиллированную воду в свои стороны, что позволяет просто возвращать дистиллированную воду обратно в котел, чтобы уменьшить кальцификацию."
		, "Котлы имеют барометр на передней стороне, их идеальное состояние - это если линия находится внутри темно-зеленой области, то есть в середине самого барометра. Чем ближе он к красной зоне, тем ближе он к взрыву."
		, "Паровые двигатели работают лучше всего, если они находятся в зеленом режиме, если они слишком синие, значит, в них недостаточно пара (ищите кальцификацию котла, если это происходит через некоторое время нормальной работы)."
		, "Если двигатели слишком красные, они выпустят пар и перестанут работать (взрывов не будет, просто пустая трата пара и шипящий шум)."
		});

		UT.Books.createWrittenBook("Manual_Random", "Советы и подсказки в GregTech-6", "Gregorius Techneticies", ST.make(ItemsGT.BOOKS, 1, 32000), new String[] {
		"Эта книга содержит случайную информацию, которая может когда-нибудь оказаться полезной. Прочтите его всякий раз, когда у вас возникнут проблемы. Возможно у них есть решение."
		, "Первый намек очевиден: ВСЕГДА ВНАЧАЛЕ ЧИТАТЬ ОПИСАНИЕ! Подсказки часто содержат необходимую информацию, но не многие люди задумываются о ее прочтении. Обычно они содержат все характеристики машины."
		, "Если машина не обрабатывает определенный рецепт (но выполняет другие), значит, вы подаете недостаточно энергии. Это ВСЕГДА проблема. Уровень машины определяет, насколько больше энергии она потребляет, чем обычный рецепт."
		, "Уровни машин следующие: все, что находится между 16 и 64 универсальными единицами за тик, соответствует уровню ОДИН¶Уровень 2: 65-256¶Уровень 3: 257-1024¶Уровень 4: 1025-4096¶Уровень 5: 4097-16192"
		, "Если уровень используемой вами машины находится в более высоком диапазоне, чем уровень рецепта (см. его GU/t), это умножит необходимое входное GU/t на 4 и сократит время обработки вдвое, что сделает рецепт вдвое дороже."
		, "И да, 64 GU/t - это ЕЩЕ ПЕРВЫЙ УРОВЕНЬ, в машину 1-го уровня серьезно придется вставить столько! Часто можно использовать двигатель/нагреватель/и т. д. более высокого уровня при минимальной потребляемой мощности, чтобы получить GU/t."
		, "Электрическая энергия GT6 широко совместима со всеми модами, использующими IC² API, такими как:¶IC², IC²-Classic, IC²-Addons, Railcraft, Forestry и другими!"
		, "Вещи, излучающие напряжение менее 1024 EU/t, способны даже питать машины без IC² следующих модов:¶Galacticraft и его дополнения, Applied Energistics, Immersive Engineering, OpenModularTurrets, TechGuns и даже GT5U!"
		, "Камеры сгорания любого типа всегда представляют собой опасность пожара, поэтому убедитесь, что рядом с ними лежат только огнеупорные материалы! Единственный способ выключить эти камеры — поставить перед ними твердый блок или огнетушители."
		, "Вы хотите переместить воду или получить молоко, но у вас нет железного ведра? Попробуйте сделать для этой задачи деревянное ведро, это дешевле, даже если оно подходит только для небольшого количества жидкостей."
		, "Если вам нужно руководство по принтеру, то просто вставьте в принтер химический черный краситель и пустую ванильную книгу и подайте энергию. Это руководство будет немедленно распечатано."
		, "В миске для купания и в ванне можно покрасить кожаную броню и блоки GT6 с помощью жидких красителей.¶На раннем этапе вы можете промыть руду, бросив в наполненный ванильный котел. Также трубы GT могут наполнять котлы водой."
		, "Немного разнесите машины, чтобы иметь возможность автоматизировать их позже, не вызывая огромного кластерного траха."
		, "Всегда имейте достаточно места для двух блоков между каждой машиной, если не для трех! Не обращайте внимания на возможную потерю мощности, оно того не стоит."
		});

		UT.Books.createWrittenBook("Manual_Portal_TF", "The Twilight Forest Guide", "Gregorius Techneticies", ST.make(ItemsGT.BOOKS, 1, 1005), new String[] {
		"If you found this Book next to a Twilight Forest Portal, but you don't know how to light the Portal to go on the Adventure, don't worry, in here are some instructions. If found inside the Twilight Forest, you can probably skip 3 Pages."
		, "Step 1: You see the 4x4 of some type of Dirt, Grass or Mud with 12 Flowers growing on it and a 2x2 Pool of Water in the middle? That is going to be the Twilight Forest Portal."
		, "Step 2: All you need to do to activate that Portal, is throwing a Diamond into that Water Pool, and you are ready to go. Yep, it is that simple. If the Water is purple and swirly, the Portal is already active."
		, "Step 3: So, now that you have a Portal to the Twilight Forest, all you need to do is jump in. You don't even need Materials to prepare or anything."
		, "Once you are inside of the Twilight Forest you can gather up Materials from the ground as usual. A good recommendation would be to find a Firefly Forest and grab a bunch of the Firefly Jars there, because they are blocklike Torches."
		, "The Obsidian Pillars you see spread all over the place have a Block of Lapis Lazuli at their Top, it is sometimes easier to get that then to go mining. These Pillars also have Ravens around them, if you need a Feather for a Magic Map Focus."
		, "Lakes are just boring Bodies of Water, not even Squids spawn inside of them, due to the Twilight Forest being of a too low Y Level to do so. I guess the Lake is a good place to go fishing or build some IC2 Water Power Generator."
		, "The Mushroom Fortress is currently not in use. No Loot or anything in there, not even Staircases or Ladders, you could make a Base inside of it though."
		, "The Questing Ram is an Animal you can find in the middle of a Biome full of Rainbow Trees. If you click it with one of each type of Wool it will reward you with the Crumble Horn, a somewhat useful Mining Tool, and some Blocks of Resources."
		, "Druid Huts contain a Skeleton Druid Spawner, which is a renewable source of Torchberries, and a Netherrack Block that is lit on Fire. The Roof is made of Petrified Wood and therefore not flammable. There is no other Loot in there."
		, "In general all Loot Chests in Twilight Forest can be picked up wholesale as long as you have never opened them before. That way you can bring them home without cluttering your Inventory and then open them there safely."
		, "If you find small ruined Buildings, it is well advised to break their wooden Floor and dig a three block deep hole to see if there is a hidden Chest in the Basement. It is a 50:50 chance for it to exist."
		, "Water Wells can be found all over the place, it is important to note that some Wells have a Chest hidden at their Bottom. Only the Wells that have a 1-by-1 of Water can randomly have such a Chest, the ones that are 2-by-2 will never have it."
		, "Hollow Trees can contain multiple Chests accompanied by Spider Spawners. The tiny Spiders and their noise make it easy to find, and even early Tools kill them in one hit. Bigger Hollow Trees tend to have more Chests in their Foliage."
		, "Hedge Mazes are the next logical Step to find some Loot. First look from above where all the Rooms are, then rush for each of them, axe all the Chests, and run. Most of the Mobs in there are not hard to beat."
		, "Ironwood is easy as you only need some Hematite/Magnetite, Gold, and some Liveroot you get from picking up Sticks. A Mortar, a Mixing Bowl, a Furnace and Stone Anvils with Stone Hammers are all you need to make basic Equipment of it."
		, "Hollow Hills are something you can try once you are all armored up, nab some Chests from one of those. Beware it is full of very deadly and explosive Mobs, so you need to be constantly on the run and hide behind the many Stalagmites."
		, "Steeleaf may have low durability, but it is one of the Materials you can easily get good Fortune and Looting Enchanted Tools and Arrows from, and has the Item-Auto-Collecting Ability for many GT Tools. But it will turn into regular Steel if melted."
		, "Naga Arena¶===================¶This is the first Boss you need to defeat. All you need is 8 solid Blocks, a Bow and a few Stacks of Arrows. Run into the Arena and let the Naga spawn, then immediately pillar up 8 Blocks and attack with the Bow."
		, "Naga Arena¶===================¶The Naga will almost definitely kill you in Melee Combat so using ranged Weapons is the best way. And those Pillars that are already in the Arena are a good hint that you should get up and out of Melee Range."
		, "Naga Arena¶===================¶If against better judgement you wish to engage the Naga in Melee Combat, you better get well enchanted Armor. Also walk around the Naga Arena once to make sure all Chunks are generated and Worldgen Lag wont kill you."
		, "Naga Arena¶===================¶The Naga Trophy you get can be used to produce KU through a Magic Field Absorber. Naga Scales can be used to make Chestplates and Leggings, but are worthless otherwise. You are able to get into Lich Towers now."
		, "Lich Tower¶===================¶In order to enter this place you might need to kill a Naga first and touch its Trophy. The Lich Tower has one big Double Helix Central Staircase with Bridges inbetween that contain typical Dungeon Mob Spawners."
		, "Lich Tower¶===================¶Those Mob Spawners can be lit up by putting two Firefly Jars or similar Light Sources 1 horizontal Block away from them on opposite ends. These Spawners can create Zombies, Skeletons or various Spiders."
		, "Lich Tower¶===================¶There will not only be Spawner Mobs but also special Mobs that spawn normally. The special one in this case being a flying Tome, that drops Paper for each hit. Have fun punching it for maximized Paper drops."
		, "Lich Tower¶===================¶At the Ground Level of the Tower is four Side Buildings, which likely contain quite a bit of Loot Chests, you should try getting into each of those by breaking into the Walls from the Outside."
		, "Lich Tower¶===================¶The Lich is located at the top of the Staircase, and has FIVE Shields that need to be destroyed before taking Damage. To destroy a Shield you need reflect their Projectiles at them (nearly impossible in Multiplayer!)."
		, "Lich Tower¶===================¶An alternative Method for destroying a Shield is hitting it with 1.5+ Hearts of any Armor penetrating Damage. For example Smite Bullets, Pickaxes, Falling Damage and Splash Potions of Healing (but NOT Regeneration!)."
		, "Lich Tower¶===================¶Using things like Withering Damage to attack the Lich works only when all Shields are down. Damage Immunities include: Fire, Poison, Drowning, Harming Potions and anything else Undead are immune to."
		, "Lich Tower¶===================¶Zombies and Shadowclones of the Lich will be summoned occassionally to distract you from the real Target. Avoid hitting the Shadowclones, but do kill the Zombies as they will swarm you otherwise."
		, "Lich Tower¶===================¶The Lich Trophy you get can be used to produce QU through a Magic Field Absorber. The Scepter will either allow you to drain Life or to summon helpful Zombies. They can be reloaded in the crafting Grid."
		, "Minoshroom Maze¶===================¶Each Fire Swamp is surrounded by four regular Swamps, which each contain a Maze full of Minotaurs, Slimes, annoying Insects, Loot and sometimes flooded with Swamp Water. Kill a Lich first before going here."
		, "Minoshroom Maze¶===================¶Minotaurs will charge at you very quickly and from any angle. To easily get rid of the. use a Weapon with the Werebane Enchantment, which works on them and weakens them drastically."
		, "Minoshroom Maze¶===================¶Maze Slimes behave like the normal ones and are just as vulnerable to the Dissolving Enchantment. If you have Thaumcraft, use the Fire Wand Focus for the tiny leftover Slimes, to save on your Weapons durability."
		, "Minoshroom Maze¶===================¶Around the Entrance are a lot of Vines, harvest them with a Knife and craft Ropes out of them, which you can then use to get down the Hole. Bring extra Rope for going from the Upper Level to the Basement Level!"
		, "Minoshroom Maze¶===================¶Try to follow the universal Rules of Maze traversal, which you could train over at the Hedge Maze. Placing Torches always on the righthand side Walls helps you find the way back easily."
		, "Minoshroom Maze¶===================¶You may be able to find a Maze Map Focus in one of the Loot Chests. Use it to craft a Maze Map and rightclick it while you stand on the ground of the Basement Level. Dont waste it on the Upper Level!"
		, "Minoshroom Maze¶===================¶The Maze Map works everywhere, even your Base should you choose so. You can also upgrade an empty Maze Map into an Ore Map and go mining with it. Though if you got Ore Magnets you wont really need that."
		, "Minoshroom Maze¶===================¶There is a few Rooms with Mob Spawners and Loot Chests inside the Pillars. Other Loot Chests can be found in Dead Ends, just beware that those are likely TNT trapped with more than one trigger!"
		// Attacking the Minoshroom will result in breaking all Fences around it, making the Bow Method completely useless, unless you replace the Fences with actual Blocks. The Cheese would still work if it wasn't for GT6. This Line will stay to troll people.
		, "Minoshroom Maze¶===================¶The Room with the Minoshroom is at the Basement Level and blocked off by Fences. Just break a Fence out of the middle and shoot the Minoshroom with a Bow and a bunch of Arrows. Four Loot Chests await you inside."
		, "Minoshroom Maze¶===================¶Among the drops of the Minoshroom are Meef Stroganoff, a useless Food Item, and a +1 Diamond Axe that supposedly deals +3.5 Hearts of Extra Damage when you charge at Enemies (may be bugged, test it yourself)."
		, "Minoshroom Vault¶===================¶There is a secret Vault full of high value Loot Chests hidden in the Maze. It occupies 3 by 3 grid spaces worth of Basement Level Maze with very thick Walls. With the Maze Map you can easily find it."
		, "Minoshroom Vault¶===================¶The Vault Room on the Basement Level is trapped, try digging at Floor Height and with an Autocollecting Pickaxe to avoid the Wooden Pressure Plates triggering the TNT next to the Chests."
		, "Hydra Cave¶===================¶Once you have killed the Minoshroom, you can go into the Fire Swamp in the center of the Swamplands to find the Hydra Cave, the home of the aforementioned Hydra. Though there is only Ores to be found there, no Loot."
		, "Hydra Cave¶===================¶Trying to attack the Hydra can be tricky, because if you are too far away from it, all ranged Damage you deal will be nullified, and when you are close enough it will spew explosives at you."
		, "Hydra Cave¶===================¶In order to deal damage to it, you need to hit the Heads while their Mouth happens to be open. It will always rotate towards you, so try to avoid having the Hydra kite you away, while all other Heads attack!"
		, "Hydra Cave¶===================¶It has a high damage Bite Attack instant killing you if you dont wear Protection enchanted Armor, and a Fire Breathing Attack for which you should drink a Fire Resistance Potion ahead of time."
		, "Hydra Cave¶===================¶To engage in Melee, you should wear strong Armor with at least one high Level regular Protection Enchantment, drink a Fire Resistance Potion and a Regeneration Potion, and get a Sword or Axe with Sharpness."
		, "Hydra Cave¶===================¶The Hydra Trophy you get can be used to produce HU through a Magic Field Absorber. Its Blood can be used to imbue Steels including Steeleaf with a Fiery Aura, enabling Auto-Smelt on Tools and incinerating opponents!"
		, "Knight Stronghold¶===================¶Time for the Dark Forest, there is four Knightly Strongholds in there. They are underground, but you need to find the Entrance in the Forest and put one of your Trophies onto its pedestal to open it up."
		, "Knight Stronghold¶===================¶You should bring Ropes to the Stronghold as not all Areas inside of it are connected with Stairs. You will also need something that can get rid of Obsidian easily as the Tomb is encased in it."
		, "Knight Stronghold¶===================¶The Mobs include: Helmet Crabs, Two Babies in a Trenchcoat wielding a Spear can be split in two by attacking from the back, The Sapper throwing TNT at you, And little Kids spinning Spikeballs around."
		, "Knight Stronghold¶===================¶Speaking of Spikeball, you can craft one of those too from a lot of Knightmetal Ingots. Later there is even an upgrade to it to 'annihilate' your Enemies and the Terrain even more!"
		, "Knight Stronghold¶===================¶There is a few 3 by 3 structures made of Stone Brick Stair Blocks. Remove one of those Stairs to reveal a trapped Loot Chest hidden inside of it. They are often hidden behind actual Staircases too."
		, "Knight Stronghold¶===================¶Search the Stronghold until you find an Obsidian Wall visible behind some Iron Bars. Break through the Obsidian Wall into the Tomb behind it. The Phantoms will spawn once you enter."
		, "Knight Stronghold¶===================¶A lot of times the Tombs are completely disconnected from the Entrance too, because the whole Stronghold does not generate well. Just dig around a little to see if something is behind a Wall."
		, "Knight Stronghold¶===================¶The Phantoms will only track you down a limited distance and then go back to the Tomb, so you can bait them out and kill them one by one. They look more scary than they actually are."
		, "Knight Stronghold¶===================¶They throw a LOT of Weapons at you, but they only really distract from their melee attacks. Phantoms do NOT count as Undead for the sake of the Smite Enchantment and Splash Potions of Harming."
		, "Knight Stronghold¶===================¶Make sure to NOT place anything in the middle of the Tomb, such as Torches, because if the spot where the Loot Chest is supposed to be is blocked, it will not spawn!"
		, "Knight Stronghold¶===================¶It is possible for there to be more than one Tomb, and every Tomb can be connected on four Sides, so breaking through the other Walls from the inside of the Tomb may unearth more of the Stronghold!"
		, "Knight Stronghold¶===================¶Killing the Phantoms will cause Mobs to no longer spawn inside the Stronghold, giving you an easier time to find more Tombs in there. However sometimes listening for hostile Mobs can point you places."
		, "Knight Stronghold¶===================¶Weapons and Armor of the Phantoms are not special or worthwhile, so dont bother. The Knightmetal Weapons supposedly deal +1 Heart of Extra Damage to the opponent (may be bugged, test it yourself)."
		, "Dark Tower¶===================¶Found in the center of the Dark Forest, the Dark Tower is the most complex Dungeon of the Twilight Forest in this Minecraft Version. You better bring a few Enderpearls and Ropes with you, just in case."
		, "Dark Tower¶===================¶Getting to the Tower is honestly easiest if you just walk ontop of the Dark Forest itself, way less Bushes and Trees blocking your way. Not to mention you can easily see the Dark Tower from a distance."
		, "Dark Tower¶===================¶If you just wanna cheat and pillar up to the Roof of the Tower, beware of the big Rooftop Ghasts that will likely knock you off the Pillar and to your Death. So maybe you should try take the normal Entrance."
		, "Dark Tower¶===================¶To enter the Tower go to one of the Red marked Doors and rightclick it. These Doors are nice and you could harvest some of them for your Base if you would like. There is also crafting Recipes for them."
		, "Dark Tower¶===================¶Many of the Segments of this Tower have Anti-Builders in them, which prevent you from placing Blocks and will replace any broken Blocks. But they have a slight delay, enough to get into Fences for example."
		, "Dark Tower¶===================¶There is a lot of burning Holes in the Tower, for which you will have to place Blocks or Ropes, or throw Enderpearls to get past those. Destroying any Anti-Builders you find is highly recommended."
		, "Dark Tower¶===================¶Some Segments have Carminite Builders with Levers. Flip the Lever and look into the direction you want to go, and it will slowly place a Bridge of Blocks going there. You could harvest them for use with your Base."
		, "Dark Tower¶===================¶Blaze Spawners can be spawnblocked by just putting eight Firefly Jars on their exact Y-Level in a Ring one Block away from their Pillar. So two Blocks above ground, barely enough space to walk under the Jars."
		, "Dark Tower¶===================¶Locked Doors are found between Major Tower Segments. In order to open them you need to find four Tower Keys and rightclick each Corner with one of them. Or just break the Doorframe or something if you wanna cheat."
		, "Dark Tower¶===================¶Tower Keys are found in the Knightmetal Chests of the smaller Side Towers. You can just harvest the whole Chest and the Key will drop out of it anyways, even if the Chest Loot has not been generated yet."
		, "Dark Tower¶===================¶Golem and Spider Spawners are often found inside the Side Towers. Trapdoor Setups and partially broken Redstone Contraptions can be found too, as well as many Wooden and Metal Loot Chests."
		, "Dark Tower¶===================¶Ghastlings can be found in various places around the Tower, usually they will not shoot you until you stare at them. Other than that they are basically Ghasts."
		, "Dark Tower¶===================¶Towerwood Borers infest the entire Towers Walls, their Borer Essence can be used in a few Crafting Recipes related to the Devices you can find around the Dark Tower."
		// My Book isn't gonna spoil what this fun Device will do to you. XD
		, "Dark Tower¶===================¶At the Top Segment of the Tower you will see Boxes with Pistons for pushing Redstone Blocks into them. When you push all four Redstone Blocks, the Box will turn into Gold and Diamonds ready for you to harvest."
		, "Dark Tower¶===================¶To fight the Ur-Ghast I recommend using a Stack of high quality Arrows and an Enchanted Bow. If you have access to the Implosion Enchantment by using things like Gem Arrows, it works great on any type of Ghasts."
		, "Dark Tower¶===================¶There is a 'special' other way to beat the Ur-Ghast using one of the four Ghost Busters References, whenever they have been charged enough by killing the small Ghasts around, and the Ur-Ghast is directly above it."
		, "Dark Tower¶===================¶The Ur-Ghast Trophy you get can be used to produce LU through a Magic Field Absorber. Make sure you got the Achievement for collecting the Trophy! The Fiery Tears can be used on Steels just like Fiery Blood."
		, "Yeti Cave¶===================¶Deep in the Wintery Forest you will find the Yeti Cave, complete with iced Floors and a herd of Yetis at each of the four Entrances. In the center the Alpha Yeti will wait for you to get close or attack him."
		, "Yeti Cave¶===================¶The Alpha Yeti himself is quite the Cry-Baby, you can kill him with a Flame Bow very easily, even when he is immune to Arrows, and whenever you hit him he will throw a tantrum and make Ice fall from the ceiling."
		, "Yeti Cave¶===================¶Should he grapple you like a Pinchbeetle, just hit him until he gives you up and lets you down, so you can run around and desert him."// never gonna give a fuck, never gonna care about...
		, "Yeti Cave¶===================¶The Yetis drop Fur which can be used for Armors, or you just take a Knife to the Crafting Grid and remove the Fur to make Leather out of it instead. The Ice Bombs can be fun to throw at unsuspecting Yetis too."
		, "Aurora Tower¶===================¶A Blue-Green Tower is located ontop of the Glacier. It is a straight forward Dungeon with a little bit of Parkour. Look at the ceiling of each Room for 'hidden' Chests."
		, "Aurora Tower¶===================¶Three kinds of Mobs spawn inside of it, frosty Armored Ghosts, 'Ice Blazes' which drop Blizz Rods, and corrupt 'Ice Blazes' which explode after you kill them changing the Terrain in a weird way."
		, "Aurora Tower¶===================¶While climbing the insides of the Tower you will eventually end up finding a Room with an Ice ceiling, where you have to break through the ceiling multiple times to get up to the Snow Queen."
		, "Aurora Tower¶===================¶On the topmost Ice Layer you will find the Snow Queen. She is floating ontop of a platform of Ice. Hitting that floating Platform will do no damage to her at all. You need to hit her from the top."
		, "Aurora Tower¶===================¶There will be one or two 'Ice Blazes' she summons every now and then. They do mostly melee damage. She will move up and down a lot in attempt to dodge attacks, and with the Ice Platform block attacks too."
		, "Aurora Tower¶===================¶She has a Frost Breath that deals only Damage without any Side Effects. While breathing at you, you can hit her to make her stop the attack, though it's not really all that easy to do before she stops on her own."
		, "Aurora Tower¶===================¶The Snow Queen Trophy you get can be used to produce CU through a Magic Field Absorber. She drops a Tri-Bow or a Seeker-Bow, both of which are fun Weapons. The Ender-Bow wont teleport you when using Looting Arrows."
		, "Troll Caves¶===================¶Up in the Highlands are the Troll Caves, a place full of various vanilla Mobs and the namesake of the Caves, Trolls. The Walls are lined with 'Troll Steinn', an ugly Block that indicates presence or lack of Light."
		, "Troll Caves¶===================¶Many Plants unique to these Caves are located within, such as huge Mushglooms and Troll Berries, the latter of which will randomly ripen when in contact with Light, resulting in Torchberries and therefore more Light."
		, "Troll Caves¶===================¶The Trolls themselves hit like Golems, they also throw things at you. On death they cause Troll Berries to ripen, lighting up the Area. Also 2.5% to drop Magical Beans. They avoid Sunlight but are not hurt by it."
		, "Cloud Giants¶===================¶Far above the Highlands floats a Cloud. Use the Magical Beans from the Troll Caves on the Uberous Soil beneath that Cloud to spawn a huge Bean Stalk to get up there comfortably. Could also just pillar up though."
		, "Cloud Giants¶===================¶Up on the big walkable Cloud you will find a giant Oak Tree and a giant small Hut, where at least two Giants live, who will respawn in that Hut every once in a while. Ranged Weapons are advisable against them."
		, "Cloud Giants¶===================¶The entire Area may seem like everything is moving slower or laggier, but this is just an optical Illusion, everything still moves at normal speed. The Giants only seem slow because their movement didn't scale up."
		, "Cloud Giants¶===================¶You can get a Giant Sword or a Giant Pickaxe from defeating a Giant. The Pickaxe can be used to mine any of the giant Blocks, such as the giant Leaves, giant Logs, giant Obsidian and ofcourse giant Cobblestone."
		, "Cloud Giants¶===================¶It is also possible to use those giant Blocks to craft more Giant Pickaxes and Giant Swords. Also the Boxinator is capable of merging 64 Blocks into one of the giant Blocks using the Giant Pickaxe as free catalyst."
		, "Troll Vault¶===================¶The giant Cube of Obsidian contains the Lamp of Cinders and vanilla Endgame Loot such as Ancient Debris and Wither Skeleton Skulls. At this Version of Minecraft it's the final worthwhile Loot of Twilight Forest."
		, "Thorn Castle¶===================¶With the Lamp of Cinders you can either burn the Thorns or use it as an infinite Lighter. The Castle itself is unfinished, so there is not much to do there. There might be Ancient Debris below the Castle."
		, "If you combine various Trophies you got on your Journey, you will receive the Orb of Annihilation. It will grant any Holder all Progression Achievements of Twilight Forest. Just make sure that when you use the thing that you are NOT in your Base."
		, "Placing a Snow Queen Trophy (or any of the earlier placeable Trophies) somewhere, will let anyone who rightclicks the Trophy receive all related Progression Achievements as if they collected it, so that they can enter the various Biomes."
		, "This should conclude all the Stuff about Twilight Forest that seems noteworthy, aswell as most of the changes I made to improve the gameplay and add variety.¶-------------------¶Have Fun! ^^"
		});

		UT.Books.createWrittenBook("Manual_Enchantments", "Ужасающий мир волшебства", "Evoker Number 42", ST.make(ItemsGT.BOOKS, 1, 10), new String[] {
		  "Я не могу поверить, как трудно найти информацию об этих древних чарах! Но вот я наконец-то собрал все, что нужно знать об этой Магии!"
		, "Стол зачарований¶===================¶Вам понадобится один из этих столов, обычно состоящий из 2 алмазов, 4 обсидиана и книги. Затем вы сможете использовать свою магическую силу, а иногда и лазурит, чтобы накладывать случайные чары на инструменты и книги!"
		, "Книжные полки¶===================¶Чтобы увеличить силу этих ранее упомянутых случайных чар, вам просто нужны любые книжные полки, заполненные книгами. Это могут быть даже необычные металлические полки! Книги могут быть даже на обратной стороне!"
		, "Наковальня¶===================¶Но случайные чары нельзя просто удалить из инструментов. В этом поможет наковальня, поскольку она позволяет накладывать на инструменты чары из книг и даже давать им имена!"
		, "Точильный камень¶===================¶Предположительно, существуют точильные камни, которые могут снимать чары, но я никогда не видел ни одного из них! Должно быть, это похоже на те 'Мозги в банках', о которых я постоянно слышу!"
		, "Магическая энергия¶===================¶Вы можете получить это, убивая множество существ в этом мире, готовя еду или возясь с рудой! Это настоящий опыт, поэтому многие люди называют его XP или EXP!"
		, "Список¶===================¶Далее следует список известных мне чар и их эффектов."
		, "Острота¶===================¶Уровни с 1 по 5+¶Наносит 0,625 сердец дополнительного урона за уровень и действует практически на все!"
		, "Разрушение¶===================¶Уровни с 1 по 5+¶IНаносит 0,75 сердец дополнительного урона за уровень и действует практически на все! Обычно может применяться только к железнодорожным монтировкам."
		, "Небесная кара¶===================¶Уровни с 1 по 5+¶Наносит 1,25 сердец дополнительного урона за уровень и действует на нежить, такую как: скелеты, зомби, утопленники, призраки, мумии и личи! Карательные пули могут пробить даже барьер Лича!"
		, "Гибель насекомых¶===================¶Уровни с 1 по 5+¶Наносит 1,25 сердец дополнительного урона за уровень и действует на существ, у которых 6 или более ног, таких как надоедливые пауки, клещи, насекомые или чешуйницы!"
		, "Имплозия¶===================¶Уровни с 1 по 5+¶Наносит 1,5 сердца доп.урона за уровень и работает на любых криперах! Обычно может применяться только к монтировкам. Это также часто наносит вред другим взрывоопасным мобам, таким как гасты!"
		, "Расстворение¶===================¶Уровни от 1 до 5+¶Причиняет слабость и яд любому склизкому существу, по которому попадает!"
		, "Разделение¶===================¶Уровни от 1 до 5+¶Наносит слабость и яд всем существам Края, по которым попадает! Ослабленные жители Края больше не могут телепортироваться, поэтому снаряды с этим зачарованием неоценимы для их свержения!"
		, "Проклятие¶===================¶Уровни от 1 до 5+¶Наносит увядание и яд всем существам-оборотням и подобным им существам, которых поражает! Работает и на Минотаврах!"
		, "Отдача¶===================¶Уровни от 1 до 2+¶Этот удар поразит любого, кого вы ударите, уменьшив при этом вашу гориз-ую скорость на 40%, что заметно только в воздухе. Его уровень увеличивается на единицу во время спринта."
		, "Заговор огня¶===================¶Уровни от 1 до 2+¶Это зачарование не только поджигает врага на 4 сек. за уровень, иногда даже готовя его дроп, а на уровне 3 оно также может расплавить любой блок, который собирает, невероятно быстро!"
		, "Добыча¶===================¶Уровни с 1 по 3+¶Увеличивает кол-во дропа, которое вы получаете с мобов. Это может увеличить общее кол-во дропа предметов или вероятность того, что вы вообще получите редкий предмет."
		, "Удача¶===================¶Уровни с 1 по 3+¶Увеличивает кол-во дропа из блоков. Это может увеличить общее кол-во дропа или вероятность того, что вы получите редкий предмет. Работает с рудами, посевами, гравием и светящимся камнем."
		, "Шёлковое касание¶===================¶Только 1 уровень¶Убедитесь, что блок, который вы собираете подходящим инструментом, выпадет сам, а не так, как обычно. Маленькие руды имеют больше шансов получить более крупные драгоценные камни."
		, "Бесконечность¶===================¶Уровни 1+¶Луки смогут стрелять бесконечными стрелами, пока у вас есть одна стрела. Оружие будет иметь 50%, 66%, 75%, 80%, 84% или выше шанс не израсходовать боеприпасы в зависимости от уровня."
		, "Сила¶===================¶Уровни с 1 по 5+¶Стрелы нанесут на 25% больше урона за уровень. Выпущенные пули летят быстрее и, следовательно, имеют более высокую пробивную способность и больший урон на большем расстоянии."
		, "Горящая стрела¶===================¶Уровни 1+¶Ваше оружие дальнего боя подожжет свой снаряд, нанося урон от огня, аналогичный или в сочетании с аспектом огня. С его помощью вы также можете поджечь ТНТ и некоторые другие вещи."
		, "Откидывание¶===================¶Уровни от 1 до 2+¶Ваше оружие дальнего боя нанесет каждому, кого вы ударите своим снарядом, примерно +3 дополнительных блока за уровень."
		, "Морская удача¶===================¶Уровни от 1 до 3+¶Увеличивает шанс выловить сокровище, уменьшает шанс выловить мусор, немного снижает шанс выловить настоящую рыбу."
		, "Приманка¶===================¶Уровни с 1 по 3+¶Сокращает время ожидания поклевки на 5 сек. за уровень до уровня 5, уровня 6 или выше, вместо этого делая удочку бесполезной для рыбалки."
		, "Защита¶===================¶Уровни с 1 по 4+¶Уменьшает общее кол-во получаемого урона на 4% за уровень за каждую часть брони, максимальное общее снижение составляет 80%, включая все остальные типы защитных чар!"
		, "Огнеупорность¶===================¶Уровни с 1 по 4+¶Уменьшает кол-во получаемого урона от огня на 8% за уровень за каждую часть брони, огр-ся защитой. Это сократит время горения на 15% за каждый лучший некомбинированный уровень."
		, "Взрывоустойчивость¶===================¶Уровни с 1 по 4+¶Уменьшает кол-во получаемого урона от взрыва на 8% за уровень за каждую часть брони, огр-ся защитой. Это снизит отбрасывание взрывом на 15% за каждый лучший некомбинированный уровень."
		, "Защита от снарядов¶===================¶Уровни с 1 по 4+¶Уменьшает кол-во получаемого дальнего урона на 8% за уровень за каждую часть брони, огр-ся защитой. Имейте в виду, что кол-во взрывных снарядов может быть уменьшено лишь частично!"
		, "Невесомость¶===================¶Уровни с 1 по 4+¶Уменьшает кол-во получаемого жемчуга Эндера и урона от падения на 12% за уровень за каждую часть брони, ограничивается защитой. Однако не работает, если вы врезаетесь в стену."
		, "Шипы¶===================¶Уровни с 1 по 3+¶Каждая отдельная часть брони имеет шанс в 15% за уровень нанести от 0,5 до 2 сердец урона любому атакующему в ближнем или дальнем бою. Однако часть брони потеряет еще немного прочности."
		, "Подводное дыхание¶===================¶Уровни с 1 по 3+¶Каждый уровень добавляет 15 сек. кислорода под водой, что увеличивает время в 2-4 раза больше обычного времени. Снижает урон от утопления с вероятностью 50%, 66% или 75% соответственно."
		, "Подводник¶===================¶Всего 1 уровень¶Позволяет вам добывать добычу на нормальной скорости, стоя под водой, а не в пять раз медленнее. Однако вам все равно придется стоять на земле, иначе вы замедлитесь в пять раз."
		, "*Эффективность¶===================¶Уровни с 1 по 5+. Инструменты с этим зачарованием получают фиксированный бонус к скорости добычи в квадрате уровня плюс 1, то есть +2, +5, +10, +17 или +26."
		, "*Прочность¶===================¶Уровни с 1 по 3+¶Инструменты с этим зачарованием служат в среднем дольше на +100% за уровень. Броня прослужит только на +25%, +34% или +43% дольше."
		, "*¶Эффективность и Прочность¶===================¶Инструментальные материалы никогда не имеют этих зачарований, потому что это было бы бессмысленно."
		});


		String tAlexGryllsIntro = "Hi, I'm Alex Grylls, Adventurer and experienced Hunter of the several Mobs you encounter in our World. In this Guide I will explain you easy Methods to hunt for wild Mobs in their natural Habitats.";

		UT.Books.createWrittenBook("Manual_Hunting_Creeper", "Hunting Guide for Creepers", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Creepers¶===================¶Creepers are sneaky, suicidal, leafy sticks on 4 short Legs, which will blow up when they are close by. They drop Gunpowder and will not deal any damage aside from the explosion when you let them come close."
		, "Creepers¶===================¶If a Creeper is struck by Lightning it will become supercharged and will explode with a much larger force. Creepers in general when shot by a Skeleton, will drop Music Discs, which are usable for the Jukebox."
		, "Creepers¶===================¶In order to detonate, a Creeper needs to see the Player, due to this it is always recommended to take the high Ground as you can hide behind the Cliff you are standing on more easily."
		, "Creepers¶===================¶Water is also a good place to melee a Creeper, as knockback will get it out of explosion Range, and even if it explodes it will not cause Damage to the ground, due to the Water absorbing the shock."
		, "Creepers¶===================¶For Melee Combat in general it is adviced to have Weapons with a strong Knockback so you don't need to sprint to knock the Creeper out of the Detonation Zone. You can also just shoot an Arrow first and then melee."
		, "Creepers¶===================¶The Implosion Enchantment does a great deal of Damage to Creepers. It can however only be gotten by enchanting Crowbars, or by using Tool Materials that have the Enchantment on them normally."
		, "Creepers¶===================¶You can manually ignite Creepers with Flint and Steel or a Lighter, and Ammunition with the Fire Aspect Enchantment will ignite Creepers at a distance if Implosion is not also on the same Arrow or Bullet."
		, "Creepers¶===================¶Last but not least, you can always just drop Water between you and the Creeper, it will slow it down considerably and if its about to explode anyways, then it won't damage the Terrain."
		});

		UT.Books.createWrittenBook("Manual_Hunting_Skeleton", "Hunting Guide for Skeletons", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Skeletons¶===================¶Skeletons, usually named after Fonts and armed with Bow and Arrows. They are good at aiming, some of them are even capable of picking up dropped Weapons and Armor."
		, "Skeletons¶===================¶If they somehow kill Creepers, those Creepers will drop Music Discs. Killing a Skeleton on long Range will yield the Sniper Duel Achievement aswell."
		, "Skeletons¶===================¶In order to not have a bad time with Skeletons, you need to either keep a Safe distance from them and shoot them from far away, or to quickly engage them with Melee Attacks, Speed is useful for this purpose."
		, "Skeletons¶===================¶When you charge at a Skeleton, strave slightly to the right or left in order to have less Arrows hit you, any way to instantly kill them with one blow, will greatly reduce the chance of more Arrows flying at you."
		, "Skeletons¶===================¶If you have a Sword then blocking with it will reduce the Damage of Arrows. This can easily be used even during Melee to not get that large amounts of Damage, even if you get hit."
		, "Skeletons¶===================¶Backing a Skeleton into a Corner, by pushing it or knocking it back for example, will result in the Arrow getting Stuck in the Wall in a glitchy fashion and therefore not flying at you or damaging you."
		, "Skeletons¶===================¶Walls of any kind are useful, but what really helps (if you are prepared) is Sugar Canes / Reeds as they can block their view on you, while letting you shoot through."
		, "Skeletons¶===================¶You can also use Saplings and (sadistically) Bonemeal to instantly create a small Forest as a Shield in the Grasslands."
		, "Skeletons¶===================¶Being in Water makes you an easy Target for the Skeleton, since you are slow and constantly get knocked back. But the Skeleton being in Water can be worse as it bounces up and down without losing accuracy!"
		, "Skeletons¶===================¶But if both you and the Skeleton are in deep water then diving below the surface and attacking the Skeleton from below is the best advise I can give, as the Skeletons often miss at steep angles."
		, "Skeletons¶===================¶In some cases it is possible to just drop a Stone or a Wooden Sword for the Skeleton to pick up, they will drop their Bow in this case. However, this does not work on every Skeleton!"
		, "Skeletons¶===================¶Skeletons are grouped amoung the Undead Mobs, meaning they can be damaged by Sunlight, since it sets them on Fire. The Smite Enchant will also help a lot."
		, "Skeletons¶===================¶Due to being undead, Healing Splash Potions deal damage to them, while poisoning ones heal them. Since Skeletons shoot Arrows, it is a good Idea to wear Armor with Projectile Protection on it."
		, "Skeletons¶===================¶It is always a good Idea to just make Skeletons attack each other or other Mobs in order to make the attacked Mob angry at them (instead of you), causing them to kill each other."
		, "Spider Jockeys¶===================¶Ever saw a Skeleton Ride a Spider? Yeah its a terrifying picture. The speed and mobility of a Spider and a 'Turret' on its back."
		, "Spider Jockeys¶===================¶To fight them you can use easy tricks, like making the Spider crawl up a wall into the Roof, crushing the Skeleton that rides it in the process."
		, "Spider Jockeys¶===================¶In general you can apply the Strategies for both the Skeletons and the Spiders, to this combo. It's always the best solution to just shoot them from far away though."
		, "Spider Jockeys¶===================¶If you have a Bow then go for the Skeleton first, if you have to Melee then go for the Spider. Alternatively you can wait for the Sun to burn the Skeleton before engaging."
		, "Wither Skeletons¶===================¶Wither Skeletons are just like normal Skeletons with a Stone Sword, that instead of Arrows will drop regular Coal. They also are 3 Blocks tall and can absorb your Health."
		, "Wither Skeletons¶===================¶While Witherskeletons behave like any other humanoid Melee Mob, they are relatively fast and less stupid than Zombie Pigmen. If you want to know if you can handle one, practise on Zombie Pigmen first."
		, "Wither Skeletons¶===================¶Due to being about 3m tall, just like Endermen, you can essentially hide behind a 2m tall Roof and grind them into Ashes, uhh I mean Coal."
		, "Wither Skeletons¶===================¶In Melee Situations, walking backwards and swinging is the best way to fight them without getting yourself harmed, but watch out for Fire and Lava behind you."
		, "Wither Skeletons¶===================¶An Iron Golem is a good Idea to have with you, just take Iron Blocks and a Pumpkin with you. The Iron Golem will turn every hostile Mob in the area into scraps."
		, "Wither Skeletons¶===================¶Those black Skeletons spawn alongside regular Skeletons with Bow & Arrow inside a Nether Fortress, and they are the only really aggressive Mob there, aside from Blazes."
		, "Wither Skeletons¶===================¶If you end up fighting both a Wither Skeleton and a Blaze, the best is to hide outside the Range of the Blaze and trying to fight the Skeleton first, or to bring the Skeleton between the Blaze and yourself."
		, "Wither Skeletons¶===================¶Since most of the times you are in the Nether with these Mobs, placing a Nether Portal close by to lure Mobs inside it, by hiding behind it, might be a good Idea (if you don't plan to use that Portal later)."
		, "Wither Skeletons¶===================¶The Heads, which are dropped by every 40th Wither Skeleton, can be used to summon the Wither, but the Wither is being talked about in another Book."
		});

		UT.Books.createWrittenBook("Manual_Hunting_Zombie", "Hunting Guide for Zombies", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Zombies¶===================¶Zombies, the most common Mob in Minecraft. Zombies always attack with Melee Damage and they can wield Weapons and wear Armor."
		, "Zombies¶===================¶Zombies are grouped amoung the Undead Mobs, meaning they can be damaged by Sunlight, since it sets them on Fire. However, this does not apply to Baby Zombies!"
		, "Zombies¶===================¶Due to being undead, Healing Splash Potions deal damage to them, while poisoning ones heal them. The Smite Enchant is a good way to cause a lot of damage on them."
		, "Zombies¶===================¶Do not underestimate their Melee Damage and try to keep a safe distance while attacking them, make sure they don't hit you when you Melee them yourself."
		, "Zombies¶===================¶While wearing a Helmet they are immune to the Sun, that is until the Helmet loses all of its durability and breaks as it gets damaged when worn by a Zombie in Sunlight."
		, "Zombies¶===================¶Zombies are attracted by Villagers, you can use them to distract them. If you want them to murder that Village, that is."
		, "Zombies¶===================¶They can detect you from very far distances and will therefore end up swarming you from all directions, if you are not careful."
		, "Zombies¶===================¶Build yourself a small fortification if you are in the wild. Zombies luckily cannot break Blocks so they won't be able to attack you if you build it right."
		, "Zombies¶===================¶Since Zombies normally group up on you due to coincedential pathfinding, it is well advised to just throw Splash Potions of Regeneration or Healing at them, since they are damaged by those."
		, "Zombies¶===================¶As always, Bow and Arrows can help you killing Zombies, it is a good Idea to use golden Arrows or other Smite Arrows if possible. But usually there are too many of them to kill all."
		, "Zombies¶===================¶Use Knockback on Zombies even if not needed, otherwise there might be too many Zombies grouping up at you if you are surrounded, so keep them at distance."
		, "Zombies¶===================¶Keep Zombies in the Water, they are almost helpless there and can't shoot you like Skeletons would. They are swimming much slower than you do."
		, "Zombies¶===================¶Iron Golems are made for killing Zombies, they might be a viable option for your purposes."
		, "Zombies¶===================¶The official Mojang term for Zombies wearing golden Armor is 'Zom-Bling'."
		, "Baby Zombies¶===================¶Annoying little Bastards would be a better Name for them, not only that they are immune to Sunlight, they are also very fast and hard to hit!"
		, "Baby Zombies¶===================¶If you didn't need the Anti Zombie Strategies before, then now you probably need to use them, since those little things can ruin your day."
		, "Baby Zombies¶===================¶If you have the option, go for the Water, they aren't fast there. Other than that, mow your lawn and then aim low and hit them as fast as you can!"
		, "Chicken Jockeys¶===================¶Thought it couldn't get any worse? Chickens are faster than those Baby Zombies and now they actually ride the Chickens!"
		, "Chicken Jockeys¶===================¶Shoot the Chicken, yes get rid of it as fast as possible, you won't be able to Melee it as the Zombie is in the way, but you can try to shoot it!"
		, "Chicken Jockeys¶===================¶Other than that? Run for your life and try to either find a pond of water or use a Water Bucket on them, maybe you get it to dismount by doing so!"
		, "Zombie Pigmen¶===================¶Zombie Pigmen, usually wielding a Golden Sword and dropping Gold Nuggets. The only pieceful Zombie, well peaceful until a Player harms it."
		, "Zombie Pigmen¶===================¶If a Player does any damage to a Zombie Pigman, all the other Pegmen get angry and try to kill you, not only are they pretty fast, but they also do lots of Damage!"
		, "Zombie Pigmen¶===================¶Zombie Pigmen are immune to Fire and the Sun, if you happen to find one in the Overworld (Pig struck by Lightning, or fresh from a Portal) it is usually safe to kill."
		, "Zombie Pigmen¶===================¶The easiest Solution is not to harm them, but it always can happen that you accidentially do harm to them, or you mine the Ores of that Nether Ores Mod."
		, "Zombie Pigmen¶===================¶In that case, pillar up or dig yourself in, as fast as possible! You don't want to die instantly and drop all your Inventory into the Nether."
		, "Zombie Pigmen¶===================¶Once you have done that, make yourself a comfortable spot to attack the Pigmen from a space where they cant attack you."
		, "Zombie Pigmen¶===================¶Since most of the times you are in the Nether with these Mobs, placing a Nether Portal close by to lure Mobs inside it, by hiding behind it, might be a good Idea (if you don't plan to use that Portal later)."
		, "Zombie Pigmen¶===================¶As they are undead like normal Zombies aswell, Splash Potions of Regeneration and Healing can deal great damage to them, while leaving yourself unharmed."
		, "Zombie Villagers¶===================¶They behave just like normal Zombies, even though they look much worse, the only difference is that you can cure them to get Villagers, what will be explained in the following Pages."
		, "Zombie Villagers¶===================¶First of all, you need a good place to actually cure that Zombie without other Zombies turning the Villager right back into the undead Green."
		, "Zombie Villagers¶===================¶For that you need to know, that the Transformation back into a Villager for some reason goes by faster if you use vanilla Iron Bars for the Cage."
		, "Zombie Villagers¶===================¶Once you got the Cage, there are multiple Options on the Item you use to cure the Villager Zombie. There are multiple options."
		, "Zombie Villagers¶===================¶Splash Potions of Weakness + Golden Apples are one way. Glass Bottles of Holy Water can replace that Golden Apple. The rainbow colored Cure-All Pill is an alternative that does not need Weakness."
		, "Zombie Villagers¶===================¶Now you need to somehow lure the Zombie Villager into your Cage. Don't worry, the Villager won't remember that you punched them while they were a Zombie."
		, "Zombie Villagers¶===================¶A fishing Rod can also work wonders on moving the Zombie into the right directions. Punching them into a pit and closing it off, will work aswell."
		, "Zombie Villagers¶===================¶If your Cage is outside (or improvised), then make sure the Sun won't burn the Villager before you can heal them. Add a Roof or something to your Cage, once you caught them."
		, "Zombie Villagers¶===================¶So, now that you got the Villager into a Cage, you can heal them. Once you have done that, transport the Villager somewhere else, maybe with Rails and Minecarts."
		});

		UT.Books.createWrittenBook("Manual_Hunting_Spider", "Hunting Guide for Spiders", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Spiders¶===================¶Spiders, octo-legged, climbing, pastry selling Arthropods, that can jump at you. Often accompanied by a random Potion Effect, which doesn't ever run out, making them invisible, resistant, regenerating or faster."
		, "Spiders¶===================¶They are fast, usually faster than you unless you run. They can also climb to a certain extend, so Walls won't help much, however Spiders can easily get stuck on ceilings, where they could fall down on you!"
		, "Spiders¶===================¶The normal way to beat them is already very effective, just melee or shoot them. They have less Health than other Mobs. The Bane of Arthropods Enchant is very effective on them."
		, "Spiders¶===================¶Spiders will jump/pounce at you when they are close, if you time it right then hitting them will knock them back farther than usual. Also always have the high Ground when fighting Spiders as otherwise they'll jump you."
		, "Spiders¶===================¶If you are in a Desert, then look out for some tsundere Plants called 'Cactus', plural being 'Cactii', Spiders apparently love climbing them, damaging themselves in the process."
		, "Spiders¶===================¶At daytime, Spiders are tired and usually not aggressive. If you attack them then and with critical hits, then they will die by the surprise and won't damage you."
		, "Spiders¶===================¶Running is the best thing to do when fighting Spiders, not running away, I mean running at them or keeping a safe distance during combat. Spiders are immune to Poison, so splashing them with it isn't effective at all."
		, "Spiders¶===================¶They get stuck at overhangs, meaning if you build a pillar with 4 overhang blocks (one at each side) Spiders won't get up and you can damage them from ontop of that pillar."
		, "Spiders¶===================¶due to being 2m in diameter, hiding in a 1m tight spot is a good idea when fighting regular Spiders. This doesn't work on the poinsonous Cave Spiders though."
		, "Cave Spiders¶===================¶Cave Spiders are just 1m in diameter and they inject poison when they melee you. They normally only spawn inside Mineshafts, since there are Spawners for them over there."
		, "Cave Spiders¶===================¶The Spawners are surrounded by lots and lots of Webs, those webs will slow you down, so take your Sword or Silk Touch Shears and slice those Webs away, also useful if you need String."
		, "Cave Spiders¶===================¶Cave Spiders often get Stuck at the wooden Pillars or hide in the ceiling at those, so be careful where you walk as they will fall down at you very surprisingly despite making lots of noise."
		, "Cave Spiders¶===================¶Due to them being poisonous, Milk or any sort of Antidote Pill are useful Items to have on your Hotbar. Also hiding from them by closing off Paths is very needed sometimes."
		});

		UT.Books.createWrittenBook("Manual_Hunting_End", "Hunting Guide for the End", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Endermen¶===================¶Endermen, 3m tall, black, purple eyed Teleporters with a weakness for Water and Silver (Disjunction Enchant), that can take some Blocks away from the World."
		, "Endermen¶===================¶Under normal circumstances Endermen just mind their Business and don't care what you are doing, but they do not like getting stared at, to the point that they will attack you if you do so."
		, "Endermen¶===================¶If you still want to look at them without them attacking you, then wear a carved Pumpkin as Helmet. But you are hunting them, probably for the Pearls, so turn to the next Page."
		, "Endermen¶===================¶Since Endermen don't usually attack you, I presume you just want their Pearls, and for that an Enderman Trap is a good Idea."
		, "Endermen¶===================¶The simplest Method is just standing below a 2m tall ceiling, so they cannot get to you. And then stare at every Enderman around to let them rage at you and attack you."
		, "Endermen¶===================¶Another way would be the use of Water Buckets to shield yourself off. Also they can only teleport distances of at least 16m btw, no shorter teleports are possible for them."
		, "Endermen¶===================¶As you may know, normal Arrows are pretty much worthless against them, that is why you should use Arrows with Disjunction on it (Silver), in order to prevent them from teleporting."
		, "Endermen¶===================¶If you encounter an angry Enderman, then try to run into the closest Forest, the low Trees are perfect hiding Spots, and they cant teleport to you if they are too close to you already."
		, "Endermen¶===================¶Melee attacks only work out if you look at their feet while swinging, as they will teleport away when you stare at them, since staring at them would freeze them into place."
		, "Endermen¶===================¶The Weakness Potion Effect is capable of preventing an Enderman from Teleporting, make use of that and splash them. (Note, that it is GregTech adding that Feature!)"
		, "Ender Dragon¶===================¶The Ender Dragon is essentially the Queen of the Endermen. She flies around between Obsidian Pillars with Magical Ender Crystals, that will regenerate her Health. Her attack pattern is Kamikaze Melee."
		, "Ender Dragon¶===================¶She will obliterate every normal Block (except Endstone, Bedrock and Obsidian) when flying through it. She is immune to Knockback and Fire, and weak to the Disjunction Enchant found in Silver."
		, "Ender Dragon¶===================¶The first thing you have to do is making sure you don't get knocked into the Void. Building Walls will only help if you have Obsidian with you, since she can't break it, unlike everything else you can bring."
		, "Ender Dragon¶===================¶Once you are safe on the Main Island, you have to go and get rid of the Ender Crystals. They can easily be destroyed by Snowballs and Arrows. Do not melee those Crystals as they deal a huge explosion damage."
		, "Ender Dragon¶===================¶Due to the Kamikaze Ram Attack, the Ender Dragon can be fought in Melee aswell as with Bow and Arrow. Use Weapons with the Disjunction Enchant and get yourself some Strength Buff in order to deal more melee."
		, "Ender Dragon¶===================¶During the Fight it can happen that you stare at Endermen by accident, in order to prevent that you could either wear a Pumpkin as Hat and press F1, or just try to only look at a slight upwards angle."
		});

		UT.Books.createWrittenBook("Manual_Hunting_Blaze", "Hunting Guide for Blazes and Ghasts", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Blazes¶===================¶Blazes are floating, fireball shooting, yellow Heads with valuable fiery Rods rotating around them. Those Rods only drop if they are killed by a living thing, making them rare. They also deal Melee Fire Damage too."
		, "Blazes¶===================¶As they shoot Fireballs, they can be hard to get close to. My advice is either just shooting them with a Bow or being very creative and using a Fishing Rod to reel them in for Melee Combat."
		, "Blazes¶===================¶You can also just hide behind a Wall and wait for them to come close, and then strike and hide again, so they don't get a chance to fire back. Building such a Wall between you and the Blazes is also a good Idea."
		, "Blazes¶===================¶Fire Resistance is a nice Effect you should somehow have on yourself when you fight them, it makes you nearly immune to Blazes overall. Also possible is the use of Iron Golems, what not many think about doing."
		, "Blazes¶===================¶Snowballs are normally useless against Mobs but in case of Blazes they do cause some severe Damage and are easier throw than you can shoot a bow. Snow Golems wouldn't survive in the Nether but they would work too."
		, "Blazes¶===================¶Using Wolves or Golems can lead to them knowing Blazes down the Platforms, meaning you might not get drops, or they will now attack you from a distance, only do this for Areas that are surrounded by Netherrack."
		, "Blazes¶===================¶Blazes can also be damaged by Water Buckets, sure they won't drop anything, and Water Buckets don't work in the Nether, but this Information might be useful if you got some way to get Water over there."
		, "Ghasts¶===================¶Ghasts, flying, ghost alike, crying Tentacle Monsters with explosive Projectiles. Their Tears are hard to get due to them being airborne and the Nether being all like 'The Floor is Lava!'."
		, "Ghasts¶===================¶Their Projectiles can literally be punched back at them kinda like a Baseball, what will yield the 'Return to Sender' Achievement. What is a Baseball, you ask? I don't know either."
		, "Ghasts¶===================¶Punching their Fireballs back at them can be hard to do, especially with Lag, but Arrows can deflect the fireballs aswell, meaning you can shoot Arrows at the Fireballs as a defense."
		, "Ghasts¶===================¶In general any form of ranged Attack is advised, because it happens very rarely, that a Ghast is close enough to the ground to actually melee it. The Implosion Enchantment works well on them too."
		, "Ghasts¶===================¶It can happen very often that you are unprepared and a Ghast shows up trying to blow you into smithereens. In that case, run away and hide, first."
		, "Ghasts¶===================¶After you are done finding a hiding Spot, look at where exactly the Ghast is right now, so you can stay out of its Range. Then either continue your business or shoot the Ghast."
		, "Ghasts¶===================¶If you are going to deflect a Ghast Blast the melee way, then use a Sword for that, as it gives you a slightly increased Range. This is especially useful if you cant use a Bow."
		, "Ghasts¶===================¶Ghasts are the normal kind of Mob, they are neither Undead nor Spiders, there is no special Enchant against them, and Potions work normally on them."
		});

		UT.Books.createWrittenBook("Manual_Hunting_Witch", "Hunting Guide for Witches", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Witches¶===================¶Witches are uglier Villagers with Potion Skills. When damaged they will drink a Potion of Regeneration or Fire Resistance depending on the source of the Damage. They sometimes drop the **most recently used** Potion."
		, "Witches¶===================¶Their attacks are Splash Potions of Poison and Harming. Since they use Fire Resistance Potions 'BURN THE WITCH!!!' is not an attack option for you. Drowning doesn't work either, as they use Water Breathing Potions."
		, "Witches¶===================¶Bow and Arrow are ofcourse a good option, but if you have to melee then do it fast and run straight at the Witch to hit them. This will cause the Witch to try healing themself instead of attacking you."
		, "Witches¶===================¶A Bucket of Water can give you the needed distance from the Witch in order to attack them with ranged Weapons. But watch out, if you are too far away, the Witch may use Speed Potions to be able to catch up with you."
		, "Witches¶===================¶In swampy Areas there is the possibility to find Witch Huts, those Huts may be very small but only Witches can spawn in there, meaning a Mob Spawning Area built around it will only spawn Witches!"
		, "Witches¶===================¶Witches drop Empty Bottles, Sticks, Redstone, Glowstone, Sugar, Spider Eyes, Gunpowder and Potions. This makes them a valuable Mob to farm and also a way to get Glowstone and Potions without the Nether."
		});

//      The Wither is a tough mob to fight.
//      Having more health than the Ender Dragon or iron golems, the Wither can be quite hard to fight if one is not prepared and taken the necessary precautions.
//      First of all, Withers do not naturally spawn.
//      You must get 4 soul sand from a Nether fortress and 3 wither skeleton skulls.
//      Wither skeleton skulls are dropped by wither skeletons when killed, however, they have only a 2.5% chance of dropping it.
//      You should use a looting enchantment sword if possible.
//      Once you get your three skulls and four soul sand, find a place to spawn the wither.
//      To spawn the wither, one needs to place the soul sand in a t-shaped arrangement (exactly like an iron golem's iron block arrangement).
//      Then, the three wither skulls are placed on the top of each of the three soul sand blocks that are on the top layer.
//      Beware however, once you place the last wither skeleton skull to complete the wither spawning arrangement, the wither will start to spawn.
//      We will come back to this spawning process later. 
//      Supplies needed to fight the wither depend on strategy.
//      The most strategic way requires these items:
//      A full set of diamond armor (with a high protection enchantment, thorns may be useful but is not necessary),
//      A sword with either sharpness or smite (fire aspect does not work on the wither),
//      A bow with Power IV or V, punch, and infinity, Obviously an arrow (or many if you don't have the infinity enchantment),
//      some instant health II splash potions, some strength II potions, and one or two enchanted golden apples.
//      You will see how all of these will help in the following combat strategy.
//      Again, this is one of the infinitely possible combat strategies there are for fighting the wither.
//      Make sure you are an ample distance from your house before spawning the wither.
//      This mob has the potential to be incredibly destructive.
//      When you make the arrangement for spawning the wither correctly as said above, you should see a mob appear that is black and has three heads.
//      Also you will notice a boss health bar much similar to the Ender Dragon's health bar.
//      You will also see the bar gradually filling up.
//      At this time, get your sword, bow, apples, and potions in hand and equip your armor.
//      You don't have much time.
//      Run away as fast as you can and be a good distance away from the wither as the health bar fills up.
//      When the bar is full, the wither lets out an explosion that destroys some of the terrain around it.
//      You are now ready to start fighting. The wither attacks any mob that is not undead.
//      When the wither starts to search for prey, it will look for any mob that meets its criteria for killing.
//      Your strategy right now should be to use your bow and shoot arrows at him.
//      If you hit him, he might see you and charge at you.
//      He will shoot blue and black colored skulls that look like one of his many heads.
//      If these projectiles hit you, they will deal a good amount of damage.
//      Damage done is dependent on the difficulty.
//      On normal, if one is hit with a wither skull, the wither effect may be inflicted.
//      Wither is basically the same as poison except you can be killed if your health bar is low and turns your health bar black for its duration so it is slightly hard to see how much health you have.
//      These skulls will deal terrain damage if they hit the ground.
//      The rare, blue skull is prone to destroying blocks with a much higher block resistance, including obsidian.
//      Continue to follow the pattern of shoot, flee, and repeat.
//      Keep doing this to decrease his health.
//      Use the health potions when necessary.
//      When the health reaches half, you will notice a somewhat of a force field around it.
//      The force field causes the wither to take less damage and makes it immune to arrows.
//      At this point, your bow is rendered useless.
//      Consume a golden apple and a strength potion and you have no choice but to charge in.
//      That way, the Wither is within melee range.
//      The wither will do no damage to you while you have regen from the apple.
//      You will also do much more damage because of the strength potion.
//      Hopefully, You can kill the wither in one go with the apple and potion.
//      It should be enough.
//      If you are successful in killing the wither, the wither will explode.
//      The wither drops a small amount of experience and a single Nether star.
//      This Netherstar can be used to make a most desired beacon block.

		//-----

		tBook.clear();
		tBook.add("В этом руководстве рассказывается о распространенных и необычных инструментах и о том, как их использовать. Он содержит список всех обычных инструментов, истинное назначение которых, вы возможно не знаете.");

		tBook.add("Меч"                 +"¶===================¶Это меч, он наносит урон и режет предметы. Он может собирать листья, паутину и блоки шерсти.");
		tBook.add("Нож"                 +"¶===================¶Он разрезает еду, палки, резину, кору дерева и тому подобные вещи и является довольно полезным ранним оружием. Он может собрать все, что может собрать меч.");
		tBook.add("Тесак"               +"¶===================¶По умолчанию имеет модификатор грабежа, но имеет медленный размах и низкий урон.");

		tBook.add("Дубина"              +"¶===================¶Иногда довольно дорого, но она работает как молот и наносит много урона, но имеет большее время восстановления после удара. Она может сломать каменные блоки, но разрушит их.");

		tBook.add("Топор"               +"¶===================¶Хорошо рубит деревья, плохо рубит доски или другие деревянные предметы.");
		tBook.add("Секира"              +"¶===================¶По-прежнему хороша для рубки деревьев, но также очень эффективна в качестве оружия и имеет на 50% большую прочность, чем обычный топор, при этом для её создания требуется на 66,666% больше материала.");
		tBook.add("Пила"                +"¶===================¶Плохо подходит для рубки деревьев, но может легко разрезать деревянные предметы, например доски.");
		tBook.add("Электропила"         +"¶===================¶Подходит практически для всех целей, может рубить деревья, пилить доски и является очень хорошим оружием. Её можно специально использовать для спам-кликов по криперу до его смерти.");
		tBook.add("Циркулярная пила"    +"¶===================¶Этот тип пилы предназначен для ручного изготовления и не может собирать блоки. Дешевле использовать её для крафта, чем бензопилу.");

		tBook.add("Кирка"               +"¶===================¶Капает блоки, как это делает обычная кирка.");
		tBook.add("Строительная кирка"  +"¶===================¶Она добывает искусственные блоки намного быстрее, но медленнее на природных камнях.");
		tBook.add("Кирка с напылением"  +"¶===================¶В обмен на большую прочность вы можете поместить испорченные самоцветы на основу кирки из необработанной стали, чтобы придать ей качество инструмента из самоцветов. Лучше всего для этого подходят янтарь, сапфиры и алмазы.");
		tBook.add("Отбойный молоток"    +"¶===================¶Эта штука добывает блоки и крушит их, как молот, с безумной скоростью. Не так уж много об этом можно сказать.");
		tBook.add("Шахтерский бур"      +"¶===================¶Он может одновременно добывать и перелопачивать предметы и является идеальным универсальным инструментом для шахтерских работ.");

		tBook.add("Лопата"              +"¶===================¶Капает блоки, как это делает обычная лопата.");
		tBook.add("Совковая лопата"     +"¶===================¶Лопата имеет условное шелковое касание для определенных блоков, таких как грязь, трава, мицелий, глина, грязь, снег и подзол, но она медленнее, чем обычная лопата, и не работает на песке.");
		tBook.add("Снегоуборочная лопата"+"¶===================¶Одновременно убирает слои снега и подобные блоки на площади 3x3x3.");
		tBook.add("Универсальная лопата"+"¶===================¶Лопата, пила, меч и монтировка одновременно. Очень универсальна, но это происходит за счет прочности и скорости.");

		tBook.add("Молот"               +"¶===================¶Разбивание блоков и кузнечное дело — наиболее распространенные способы использования этого инструмента. Больше он ничего не делает, но его можно использовать в бою.");
		tBook.add("Киянка"              +"¶===================¶Эти молотки в основном используются для включения и выключения машин. Они могут даже включать лампы красной пыли и некоторые рельсы без необходимости иметь рядом с ними сигнал красной пыли.");
		tBook.add("Гаечный ключ"        +"¶===================¶Регулирует основную облицовку большинства машин, иногда его также можно использовать для переключения режимов.");
		tBook.add("Разводной ключ"      +"¶===================¶Регулирует вторичную облицовку большинства машин, часто его также можно использовать для переключения режимов.");
		tBook.add("Электроключ"         +"¶===================¶Он представляет собой комбинацию обычного гаечного ключа и разводного ключа, и между ними можно легко переключаться с помощью режимов. Нажмите ПКМ на неразборный блок, чтобы переключиться.");

		tBook.add("Напильник"           +"¶===================¶Затачивает предметы и может, как ни странно, собирать железные прутья гораздо быстрее, чем что-либо еще.");
		tBook.add("Долото"              +"¶===================¶Может придать форму литейным формам и изменить внешний вид некоторых камней.");
		tBook.add("Гибочный цилиндр"    +"¶===================¶Превращает пластины в изогнутые пластины и используется для аналогичных процедур обработки металлов.");

		tBook.add("Монтировка"          +"¶===================¶Открывает ящики и может использоваться на рельсах и вагонетках.");
		tBook.add("Клещи"               +"¶===================¶Используется для сбора горячих предметов из форм и тазов, поэтому вам не придется ждать, пока они остынут, прежде чем брать их.");
		tBook.add("Вантуз"              +"¶===================¶Опорожняет трубы и резервуары с жидкостью, опустошая жидкость или выбрасывая застрявшие в них пачки предметов. Также работает с Таумическими проводниками.");
		tBook.add("Сачок"               +"¶===================¶Ловит бабочек, пчел и шмелей, также необходим для сбора ульев шмелей и пчелиных ульев.");

		tBook.add("Мотыга"              +"¶===================¶Они пашут землю, чего вы ожидаете? Я не буду подробно объяснять ванильные инструменты.");
		tBook.add("Секатор"             +"¶===================¶Вырезает саженцы из листьев с высокой точностью, как прививка.");
//      tBook.add("Sickles"             +"¶===================¶");
		tBook.add("Коса"                +"¶===================¶Собирает урожай, цветы и высокую траву на площади 3x3x3.");

		tBook.add("Скалка"              +"¶===================¶Раскатывает тесто и глину для приготовления пищи и поделок.");
		tBook.add("Ножницы"             +"¶===================¶Можно перерезать растянутые провода при щелчке ПКМ, стричь овец и собирать шерстяные/тканевые блоки.");
		tBook.add("Отвертка"            +"¶===================¶Приводы, винты и тому подобное. Чаще всего используется для настройки режимов блоков и облицовок. Может вращать компараторы и повторители.");
		tBook.add("Кусачки"             +"¶===================¶Они собирают провода. Другое использование — настройка проводов, а в некоторых случаях и облицовок.");
		tBook.add("Миксер"              +"¶===================¶Это сделано для того, чтобы вы не израсходовали батончик голода при работе с чашей для смешивания.");
		tBook.add("Ручная дрель"        +"¶===================¶Это отверстия в поверхностях. Если у вас есть динамитные палки или железные/стальные стержни, вы можете воткнуть динамит в стены или укрепить кирпичи и бетон.");
		tBook.add("Огниво"              +"¶===================¶Поджигает вещи и может зажечь все, что требует зажигания. Но для этого может потребоваться несколько попыток.");
		tBook.add("Лупа"                +"¶===================¶С её помощью вы можете просмотреть детали МНОЖЕСТВА вещей в GregTech, просто наденьте любую линзу на палку, и все будет готово к работе в течение длительного времени.");

		tBook.add("Карманный мультиинструмент"+"¶===================¶Мультиинструмент, который можно использовать для самых распространенных целей. Воспринимайте это как швейцарский армейский нож.");

		UT.Books.createWrittenBook("Manual_Tools", "Каталог инструментов", "GITF (Gregorius Industrial Tool Factory)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));


		tBook.clear();
		tBook.add("Это руководство по плавильному тиглю и связанным с ним объектам. В нем объясняется, как правильно использовать плавильный тигель и формы, чтобы вы могли начать плавить вещи, которые невозможно выплавить в обычной печи.");
		tBook.add("Если вам нужно знать, насколько тяжел объект или из каких материалов он состоит, просто используйте старый добрый ванильный метод F3+H, чтобы включить расширенные подсказки, чтобы вы могли видеть наиболее важные данные об объекте.");
		tBook.add("Шаг 1: Источник тепла"         +"¶===================¶Вам нужен источник тепла, чтобы нагреть тигель до желаемой температуры. Возможные источники тепла могут включать камеры сгорания, электрические нагреватели, лазерные нагреватели и дуговые нагреватели.");
		tBook.add("Опционально: Источник воздуха" +"¶===================¶Если нужно подать воздух для тигля (для стали), нужно направить в него двигатель, который будет действовать как вентилятор. Для мультиблочных тиглей, двигатель должен находиться в нижнем ряду.");
		tBook.add("Шаг 2: Тигель"                 +"¶===================¶Выбор материала тигля очень важен. Тигель также может плавиться при определенной температуре, которая обычно выше фактической температуры плавления самого тигля.");
		tBook.add("Шаг 2: Тигель"                 +"¶===================¶Теперь поместите тигель на выходную сторону источника тепла по вашему выбору. Например камера сгорания излучает тепло только сверху, поэтому вам нужно разместить тигель над ним.");
		tBook.add("Шаг 3: Литейные формы"         +"¶===================¶Вероятно, вы хотите отлить расплавленный металл в форму, поэтому вам нужно разместить форму горизонтально рядом с тиглем. Как вы могли заметить, саму литейную форму нельзя отлить.");
		tBook.add("Шаг 3: Литейные формы"         +"¶===================¶Вот почему вам нужно долото, чтобы вырезать правильную форму в литейной форме, используя ее в мире. Наиболее важные формы перечислены на последних страницах этой книги.");
		tBook.add("Шаг 3: Литейные формы"         +"¶===================¶Если вам по какой-то причине не удастся придать правильную форму, конечным результатом будет столько самородков, сколько вы вырезали мест.");
		tBook.add("Шаг 4: Плавление"              +"¶===================¶Теперь приступим к плавке. Просто бросьте предметы, которые хотите переплавить в тигель, или используйте над ним воронку, чтобы заполнить его. Увеличивайте температуру, пока не достигнете точки плавления.");
		tBook.add("Шаг 4: Плавление"              +"¶===================¶Если ваш источник тепла не имеет какого-либо регулирования температуры, вам, возможно, придется отключить его как только температура достигнет точки плавления, иначе вы испарите металлы или что-то еще хуже.");
		tBook.add("Шаг 5: Формирование"           +"¶===================¶Теперь просто щелкните ту сторону верхней части литейной формы, рядом с которой находится тигель, чтобы заполнить ее металлами. Предупреждение: сама форма также может расплавиться, если она изготовлена из неправильного материала.");
		tBook.add("Шаг 5: Формирование"           +"¶===================¶Подождите, пока металл остынет, но учтите, что вам не следует прикасаться к металлу или вынимать его, пока он горячий, иначе вы обожжетесь, если только вы не наденете защитный костюм или что-то в этом роде.");
		tBook.add("Литейные формы"                +"¶===================¶Ниже приводится список фигур, которые можно вырезать в форме. Положение фигуры на форме НЕ важно, вы также можете вращать и отражать ее: ¶X = Выточено¶O = Не выточено");
		tBook.add("Слиток"                        +"¶===================¶XXXOO¶XXXOO¶XXXOO¶XXXOO¶XXXOO¶===================¶Требуется материала: 1.000");
		tBook.add("Кусок"                         +"¶===================¶XXOOO¶XXOOO¶OOOOO¶OOOOO¶OOOOO¶===================¶Требуется материала: 0.250");
		tBook.add("Пластина"                      +"¶===================¶XXXXX¶XXXXX¶XXXXX¶XXXXX¶XXXXX¶===================¶Требуется материала: 1.000");
		tBook.add("Малая пластина"                +"¶===================¶OOOOO¶OXXXO¶OXXXO¶OXXXO¶OOOOO¶===================¶Требуется материала: 0.111");
		tBook.add("Болт"                          +"¶===================¶XXOOO¶OOOOO¶OOOOO¶OOOOO¶OOOOO¶===================¶Требуется материала: 0.125");
		tBook.add("Стержень"                      +"¶===================¶OOOOO¶OOOOO¶XXXXX¶OOOOO¶OOOOO¶===================¶Требуется материала: 0.500");
		tBook.add("Длинный стержень"              +"¶===================¶XOOOO¶OXOOO¶OOXOO¶OOOXO¶OOOOX¶===================¶Требуется материала: 1.000");
		tBook.add("Оболочка"                      +"¶===================¶XXXOX¶XXXOX¶XXXOX¶OOOOX¶XXXOO¶===================¶Требуется материала: 0.500");
		tBook.add("Кольцо"                        +"¶===================¶OOOOO¶OXXXO¶OXOXO¶OXXXO¶OOOOO¶===================¶Требуется материала: 0.250");
		tBook.add("Шестерня"                      +"¶===================¶XOXOX¶OXXXO¶XXOXX¶OXXXO¶XOXOX¶===================¶Требуется материала: 4.000");
		tBook.add("Малая шестерня"                +"¶===================¶OXOXO¶XXXXX¶OXOXO¶XXXXX¶OXOXO¶===================¶Требуется материала: 1.000");
		tBook.add("Мечь"                          +"¶===================¶OOXOO¶OXXXO¶OXXXO¶OXXXO¶OXXXO¶===================¶Требуется материала: 2.000");
		tBook.add("Кирка"                         +"¶===================¶OOOOO¶OXXXO¶XOOOX¶OOOOO¶OOOOO¶===================¶Требуется материала: 3.000");
		tBook.add("Совковая лопата"               +"¶===================¶OXXXO¶OXXXO¶OXXXO¶OXOXO¶OOOOO¶===================¶Требуется материала: 1.000");
		tBook.add("Лопата"                        +"¶===================¶OOXOO¶OXXXO¶OXXXO¶OXXXO¶OOOOO¶===================¶Требуется материала: 1.000");
		tBook.add("Универсальная лопата"          +"¶===================¶OOXOO¶OXXXO¶OXXOO¶OXXXO¶OOOOO¶===================¶Требуется материала: 1.000");
		tBook.add("Топор"                         +"¶===================¶OOOOO¶OXXXO¶OXXXO¶OXOOO¶OOOOO¶===================¶Требуется материала: 3.000");
		tBook.add("Секира"                        +"¶===================¶OOOOO¶XXXXX¶XXXXX¶XOOOX¶OOOOO¶===================¶Требуется материала: 5.000");
		tBook.add("Пила"                          +"¶===================¶OOOOO¶XXXXX¶XXXXX¶OOOOO¶OOOOO¶===================¶Требуется материала: 2.000");
		tBook.add("Молот"                         +"¶===================¶XXXOO¶XXXOO¶XOXOO¶XXXOO¶XXXOO¶===================¶Требуется материала: 6.000");
		tBook.add("Напильник"                     +"¶===================¶OXXXO¶OXXXO¶OXXXO¶OOXOO¶OOXOO¶===================¶Требуется материала: 1.500");
		tBook.add("Отвертка"                      +"¶===================¶OOOOO¶OOXOO¶OOXOO¶OOXOO¶OOXOO¶===================¶Требуется материала: 1.000");
		tBook.add("Долото"                        +"¶===================¶OXXXO¶OOXOO¶OOXOO¶OOXOO¶OOXOO¶===================¶Требуется материала: 1.500");
		tBook.add("Наконечник стрелы"             +"¶===================¶OOOOO¶OOXOO¶OOXOO¶OXXXO¶OOOOO¶===================¶Требуется материала: 0.125");
		tBook.add("Мотыга"                        +"¶===================¶OOOOO¶OOXXO¶OXXXO¶OOOOO¶OOOOO¶===================¶Требуется материала: 2.000");
		tBook.add("Коса"                          +"¶===================¶OOOOO¶OXXXX¶XXXXX¶OOOOO¶OOOOO¶===================¶Требуется материала: 3.000");
		tBook.add("Снегоуборочная лопата"         +"¶===================¶XXXXX¶XXXXX¶XXXXX¶XXXXX¶OOXOO¶===================¶Требуется материала: 4.000");
		tBook.add("Наконечник строительного жезла"+"¶===================¶OOXOO¶XXXXX¶OXXXO¶OXOXO¶OOOOO¶===================¶Требуется материала: 1.000");

		UT.Books.createWrittenBook("Manual_Smeltery", "Руководство по плавильным тиглям", "GMWI (Gregorius Metal Working Industries)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));

		//-----

		tBook.clear();
		tBook.add("Эта книга содержит информацию о каждом сплаве, который можно создать с помощью плавильного тигля.¶===================¶Чтобы создать сплав, вам нужно достичь температуры плавления самого сплава.");
		tBook.add("И вам нужно достичь точки плавления всех его компонентов, кроме одного. Вы, конечно, также можете расплавить все Компоненты, но вы можете 'не плавить' один из компонентов.");
		tBook.add("Если нужно подать воздух для тигля (для стали), нужно направить в него двигатель, который будет действовать как вентилятор. Для мультиблочных тиглей, двигатель должен находиться в нижнем ряду.");

		for (OreDictMaterial tMat : OreDictMaterial.ALLOYS) {
			for (IOreDictConfigurationComponent tComponents : tMat.mAlloyCreationRecipes) {
				tPage="Сплав:¶"+tMat.getLocal()+"¶===================¶Плавление: "+tMat.mMeltingPoint+" K¶Кипение: "+tMat.mBoilingPoint+" K¶===================¶Компоненты для "+tComponents.getCommonDivider() + ":¶";
				for (OreDictMaterialStack tMaterial : tComponents.getUndividedComponents()) tPage += (tMaterial.mAmount / U)+" "+tMaterial.mMaterial.getLocal()+"¶";
				tBook.add(tPage);
			}
		}

		UT.Books.createWrittenBook("Manual_Alloys", "Книга сплавов, издание для плавильного завода ("+(tBook.size()-2)+")", "GMWI (Gregorius Metal Working Industries)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));

		//-----

		tBook.clear();

		for (OreDictMaterial tMat : OreDictMaterial.MATERIAL_ARRAY) {
			if (tMat != null && !tMat.mHidden && tMat.contains(TD.Atomic.ELEMENT) && !tMat.contains(TD.Atomic.ANTIMATTER)) {
				tBook.add(tMat.getLocal()+"¶"+tMat.mProtons+"/"+tMat.mNeutrons+"¶===================¶ID: "+(tMat.mID<0?"NONE":tMat.mID)
				+"¶Плавление: "+tMat.mMeltingPoint+" K¶Кипение: "+tMat.mBoilingPoint+" K¶Плазма: "+tMat.mPlasmaPoint
				+" K¶===================¶Плотность:¶"+(tMat.mGramPerCubicCentimeter == 0 ? "???" : tMat.mGramPerCubicCentimeter)+" g/cm3¶"+tMat.getWeight(U)+" kg/unit¶===================¶");
			}
		}

		UT.Books.createWrittenBook("Manual_Elements", "Периодическая книга ("+tBook.size()+")", "GCC (Gregorius Chemical Consortium)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));

		//-----

		UT.Books.createWrittenBook("Manual_Extenders", "Расширители и фильтры", "GAD (Gregorius Automation Distributions)", ST.make(ItemsGT.BOOKS, 1, 32000), new String[] {
		  "В этом руководстве рассматриваются как расширители, так и фильтры. Оно подробно объяснит использование этих устройств, чтобы вы могли легко их использовать."
		, "Сначала необходимо объявить стороны, которые есть у любого фильтра и расширителя. Основная сторона — это сторона, которую вы можете установить с помощью гаечного ключа и которая имеет 4 точки вокруг центральной трубы."
		, "Вторичная сторона — это сторона со стрелками, направленными наружу, которая устанавливается с помощью гаечного ключа. А другие стороны расширителя/фильтра являются нейтральными."
		, "Теперь, когда это ясно, мы можем перейти к функциональности. По сути, ВСЕ, что 'взаимодействует' (* объяснено позже) с расширителем через нейтральную или вторичную сторону, будет перенаправлено на TileEntity на основной стороне."
		, "Это означает, что основная сторона обычно (но не всегда!) является выходной стороной, если вы хотите вставить материал через нейтральную или вторичную сторону. Поэтому прикрепите основную сторону к TileEntity, который вы хотите расширить или применить к нему фильтрацию."
		, "Теперь о вторичной стороне: как вы знаете, 5 сторон перенаправляют свой доступ к TileEntity на основной стороне, но сама основная сторона может перенаправлять все доступы к TileEntity на своей стороне к TileEntity на вторичной стороне."
		, "Это означает, что если у вас есть вход и выход на той же стороне TileEntity, к которой вы его прикрепили, вы можете перенаправить вывод в одном направлении, используя вторичную сторону, в то время как нейтральные стороны заполняют TileEntity на основной стороне."
		, "Перенаправление с основной на вторичную НЕ фильтруется, фильтруются только нейтральные и вторичная на основную! Я надеюсь, что этого объяснения достаточно, чтобы правильно использовать фильтры."
		, "Расширители и фильтры не могут использоваться рядом друг с другом, в смысле связанные вместе. Это означает, что у вас должно быть что-то вроде канала между расширителем/фильтром и другим расширителем/фильтром, если они, конечно, не параллельны."
		, "Теперь объясним, что на самом деле означает 'взаимодействовать'. Воронка извлекает элементы из всего, с чем она 'взаимодействует' выше, и помещает их во все, с чем 'взаимодействует' внизу. Это означает, что направление движения предмета не имеет значения для расширителя."
		, "Таким образом, если вы поместите djhjyre под расширителем, воронка будет извлекать элементы из любого объекта TileEntity, к которому подключен расширитель. А если вы поместите воронку над расширителем, она заполнит TileEntity, к которому прикреплен расширитель."
		, "Расширители, по сути, представляют собой двусторонний знак обхода для всего, что пытается взаимодействовать с ними тем или иным образом, как если бы TileEntity, к которому вы их прикрепили, теперь имеет размер двух блоков вместо одного."
		, "Они вообще ничего не делают сами по себе, они просто сидят там, притворяясь тем TileEntity, к которому вы их прикрепили. Так что не ожидайте, что они будут тянуть или толкать предметы или делать что-то в этом роде."
		});

		//-----

		UT.Books.createWrittenBook("Manual_Reactors", "Руководство по реактору деления", "Apature Atomics", ST.make(ItemsGT.BOOKS, 1, 32005), new String[] {
		"=================== =================== =================== =================== =================== =================== =================== =================== =================== =================== =================== =================== =================== ===================",
		"¶¶===================¶Введение и инструкции по безопасности¶===================¶",
		"[Стенограмма]¶Поздравляем [Вставьте имя сотрудника] с дипломом по ядерной физике!¶Теперь вы подходите для работы в Apature Atomics в качестве оператора реактора.",
		"Сокращения¶===================¶АЗР - активная зона ядерного реактора.¶СР - стержень реактора.¶ОЖ - теплоноситель/ охлажд. жидкость.¶ХОЖ - холодная ОЖ.¶ГОЖ - горячаяя ОЖ.¶",
		"Сначала я познакомлю вас с тем, чем вы будете управлять, надеюсь, без незапланированных взрывов:¶Ядро любого реактора деления - АЗР 2х2.",
		"Некоторые из наших инженеров утв-ют, что это просто металл-ая коробка с четырьмя поршнями, которые толкают стержни внутрь или вверх, что мы переборщили с вычислительной мощностью этих малышей, что 4-х чертовых проводов было бы достаточно.",
		"Как будто кто-то поверил бы четырем проклятым проводам, чтобы предотвратить полное ядерное разрушение, вероятно, в любом случае это даже не в его интересах. Вот почему в АЗР каждый стержень-контроллер разумен.",
		"Поэтому в их интересах не взорваться, так как тогда я бы очень разозлился на них и отправил бы их 'контролер СР' — в ад!",
		"Во всяком случае, мы им так сказали, такого места явно не существует, у нас нет денег, чтобы финансировать всякую глупость. Вместо этого они отправятся в ад роботов, чтобы их вечно сжигали и перестраивали, пока мы собираем полезные данные.",
		"Оказывается, они вообще никогда не вставляли СР, что делало реакторы сов-но безопасными и бесполезными, поэтому мы перезаписали их контроль над стержнями, чтобы вместо этого они управлялись 4-мя проводами.",
		"Любые слухи, которые вы, возможно, слышали об АЗР 1х1 — это всего лишь слухи, мы, конечно, никогда бы не построили такую штуку, потому что очевидно, что стержень-контроллер сошел бы с ума без трех других, которые бы его развлекали.",
		"В маловероятном случае вы можете наткнуться на такое ядро, чего не произойдет, если бы они существовали, мы бы уже отправили их в ад роботов, где бы они находились в постоянной агонии, чего они не делают, потому что их не существует.",
		"В любом случае, безопасность, я обязан предоставить вам эту информацию, поэтому я быстро пройдусь по ней: Всегда носите костюм радиационной защиты. Не взрывайте АЗР. Не взрывайтесь. Не спровоцируйте ядерный взрыв.",
		"Не отрицайте причину ядерного взрыва в случае ядерного взрыва. Не прикасайтесь к работающему реактору. Не ломайте работающий реактор. Не облизывайте работающий реактор. Не позволяйте фламинго приближаться к реактору.",
		"Не ешьте ядерное топливо. Не готовьте на ядерном реакторе. В случае опухолей ешьте ядерное топливо. Это должно быть все, что вам нужно знать, а теперь приступайте к делу и займитесь наукой!",
		"¶¶===================¶Основы работы реактора¶===================¶",
		"В АЗР (2x2) есть слоты для 4-х СР.¶СР, вставленные в реактор, будут напрямую взаимодействовать только с непосредственно соседними СР, включая соседние пазы на разных, но соседних блоках АЗР.",
		"СР можно вставить в слот вручную, щелкнув по нему ПКМ. Щелчок ПКМ по слоту с помощью клещей вытащит СР внутри слота.",
		"СР также могут автоматически вставляться сверху, когда реакторный блок отключен. Также возможно автоматическое извлечение их с нижней стороны при выключенном блоке.",
		"Реактор можно включать и выключать вручную с помощью киянки. Переключатель машины из красного камня позволит управлять этим с помощью красного камня. Пока реактор выключен, он будет вести себя так, как будто в нем нет СР.",
		"Отдельные СР также можно включать и выключать, чтобы они вели себя так, как будто слот пуст. Это можно сделать либо с помощью красного камня, либо с помощью ручного переключателя.",
		"АЗР имеет два внутренних бака для хранения 64 000 литров ХОЖ и ГОЖ. Разная ОЖ может по-разному воздействовать на вставленные СР. ХОЖ можно заливать с любой стороны. ГОЖ автоматически выйдет на красную сторону.",
		"Если бак ХОЖ заполнен более чем на половину, ХОЖ будет автоматически выливаться на синюю сторону. Красную сторону можно переместить с помощью гаечного ключа, а синюю — с помощью разводного ключа.",
		"Пока реактор включен, нейтроны могут присутствовать на активных СР. Нейтроны могут взаимодействовать со СР по-разному.",
		"Как правило, количество нейтронов напрямую выводится в реактор в виде HU каждый тик. Нейтроны существуют только одну секунду и поэтому обновляются только каждую секунду.",
		"Кол-во нейтронов можно измерить вручную, щелкнув ПКМ по АЗР с помощью счетчика Гейгера. Датчик счетчика Гейгера также может быть прикреплен к АЗР для измерения суммы всех нейтронов на СР.",
		"100% режим датчика относится к сумме нейтронных максимумов всех ТВЭЛов внутри АЗР. Реактор также будет излучать излучение с диапазоном, основанным на наибольшем количестве нейтронов на любом СР.",
		"Настоятельно рекомендуется строить реактор вдали от населенных пунктов и носить костюм радиационной защиты. Любая АЗР взорвется, если добавить тепло без присутствия ОЖ.",
		"Если добавить новую тепловую энергию от нейтронов, когда в реакторе нет ОЖ, реактор резко разрушится. Если в реакторе не останется места для оставшейся ГОЖ, реактор будет подвергнут быстрой внеплановой разборке.",
		"¶¶===================¶Руководство по стержням реакторов¶===================¶",
		"Сокращения¶===================¶ПСР - пустой СР.¶ТВЭЛ - топливный СР.¶ПС - поглащающий СР.¶ОС - отражающий СР.¶ЗС - замедляющий СР.¶СС- селекционный СР.",
		"Пустой стержень реактора (ПСР)¶===================¶Ничего не делает, находясь внутри реактора. Не принимает нейтроны.",
		"Топливный стержень (ТВЭЛ)¶===================¶ТВЭЛ излучают нейтроны на себя и соседние СР. У них есть несколько характеристик, которые различаются в зависимости от материала СР и ОЖ, используемого в реакторе.",
		"Параметр 'ПН' (Персональных нейтронов) описывает сколько нейтронов СР выводит на свой собственный слот. Параметр 'Выброс' описывает сколько нейтронов он выводит на каждый соседний СР.",
		"Параметр 'Фактор' описывает сколько дополнительных нейтронов он выпустит на соседние СР в зависимости от того, сколько нейтронов находится на нем самом.",
		"Больший коэффициент (1/3 > 1/32) означает, что на один нейтрон на ТВЭЛ выводится больше допол-ых нейтронов. ¶Показатель 'Максимум' описывает сколько нейтронов может быть на этом ТВЭЛ, прежде чем он начнет терять продолжительность быстрее.",
		"При превышении максимального значения, ТВЭЛ будет терять продолжительность жизни как минимум в четыре раза быстрее, линейно масштабируясь в зависимости от количества нейтронов, превышающего максимальное.",
		"ЗС сделают также замедленными соседние активные ТВЭЛ. Замедленные ТВЭЛ разряжаются в четыре раза быстрее и не могут передать нейтроны на СС. Когда оставшийся срок службы ТВЭЛ истечет, он превратится в истощенный ТВЭЛ.",
		"Истощенный ТВЭЛ¶===================¶Ничего не делает, находясь внутри реактора. Не принимает нейтроны. Можно использовать в центрифуге, чтобы получить обратно немного топлива и немного топлива деления более высокого уровня.",
		"Поглощающий стержень (ПС)¶===================¶ПС принимают нейтроны от соседних ТВЭЛов. Каждый нейтрон на ПС преобразуется в две HU вместо одной",
		"Отражающий стержень (ОС)¶===================¶Возвращают все нейтроны, попавшие на них, прямо к их источнику. Поэтому на них никогда не будет нейтронов.",
		"Замедляющий стержень (ЗС)¶===================¶Подобно ОС, они возвращают нейтроны непосредственно обратно к источнику, но количество возвращаемых ими нейтронов умножается на количество ТВЭЛ, излучающих на них нейтроны.",
		"Поэтому они могут отражать обратно в четыре раза больше нейтронов и никогда не иметь на себе ни одного нейтрона.",
		"ЗС также замедляют соседние ТВЭЛ, в результате чего они истощаются в четыре раза быстрее и не могут передать нейтроны на СС. Замедленные ТВЭЛ также сделают замедленными соседние активные ТВЭЛ.",
		"Селекционный стержень (СС)¶===================¶Каждый тик кол-во нейтронов на этом стержне вычитается из необходимого ко-ва нейтронов. Если необходимое кол-во нейтронов достигнет нуля, стержень превратится в обогащенный ТВЭЛ.",
		"СС не принимают нейтроны от замедленных ТВЭЛ. Показатель потерь вычитается из каждого количества нейтронов, испускаемых этим стержнем,",
		"то есть потери применяются к нейтронам, поступающим от любого ТВЭЛ, излучающего на него, а не только один раз для СС.",
		"¶¶===================¶Руководство по теплоносителям реактора¶===================¶",
		"Дистилированная вода¶===================¶Превращается в пар, не требует теплообменника.¶80 HU превращает¶1л дист. воды в¶160л пара.¶Замедляет любые ТВЭЛ внутри. Статус ТВЭЛ не меняется.",
		"Полутяжелая вода¶===================¶40 HU превращает 1л полутяжелой воды в 1л гор. полутяжелой воды.¶Замедляет любые ТВЭЛ внутри.¶Статус ТВЭЛ не меняется.",
		"Тяжелая вода¶===================¶50 HU превращают 1л тяжелой воды в¶1л гор. тяжелой воды.¶Замедляет любые ТВЭЛ внутри.¶Делит хар-ку ТВЭЛ 'Максимум' на 8.",
		"Тритиевая вода¶===================¶60 HU превращают 1л тритиевой воды в¶1л гор. тритиевой воды.¶Замедляет любые ТВЭЛ внутри.¶Делит хар-ку ТВЭЛ 'Максимум' на 16.",
		"Хладагент¶===================¶20 HU превращают¶1л хладагента в¶1л гор. хладагента. Умножает хар-ки ТВЭЛ:¶'ПН' на 4¶'Выброс' на 4¶'Фактор' на 2",
		"Диоксид углерода¶===================¶20 HU превращают 1л диоксида углерода в 1л гор. диоксида углерода.¶Умножает хар-ку ТВЭЛ 'ПН' на 3.",
		"Гелий¶===================¶30 HU превращают¶1л гелия в¶1л гор. гелия.¶Делит хар-ку ТВЭЛ 'Выброс' на 2.",
		"Расплавленный хлорид лития¶===================¶15 HU превращает 1л расплавленного хлорида лития в 1л гор. расплавленного хлорида лития.¶Меняет хар-ки ТВЭЛ:¶'ПН'х5¶'Выброс'/2¶'Максимум'+25%",
		"Расплавленное олово¶===================¶40 HU превращают 1л расплавленного олова в 1л гор. расплавленного олова.¶Нейтроны преобр-ся только в треть HU. Уменьшает делитель 'Фактор' ТВЭЛ на 1.",
		"Расплавленный натрий¶===================¶30 HU превращает 1л расплавленного натрия в 1л гор. расплавленного натрия. Нейтроны преобр-ся только в шестую часть HU.¶Уменьшает делитель 'Фактор' ТВЭЛ на 1.",
		"Расплавленная ториевая соль¶===================¶2_560_000 HU превращает 1л расплавленной соли тория в 1л расплавленного хлорида лития.¶Меняет хар-ки ТВЭЛ:¶'ПН'=0¶'Выброс'/2¶'Максимум'х4",
		"¶¶===================¶Руководство по проектированию стабильного реактора¶===================¶",
		"Стабильные ядерные реакторы деления, представляют собой конструкцию реактора деления, в которой нет критических ТВЭЛ. Это означает, что кол-во нейтронов и, следовательно, тепловая мощность не увеличиваются постоянно во время работы реактора.",
		"Преимущества стабильных реакторов:¶- Не требуется внешний контроль¶- Стабильный выход нейтронов/HU¶- Повышенная безопасность¶- Легко проектировать ¶- Работает с любым ядерным топливом",
		"Недостатками стабильных реакторов являются:¶- Не очень экономичен по топливу¶- Действительно можно использовать только в качестве энергетических реакторов¶- Обычно больше, чем сопоставимые критические ре-ры",
		"Стержень реактора можно считать критическим, когда  кол-во нейтронов, отраженных обратно на стержень, умноженное на коэффициент стержня, больше или равно параметру 'Выброс' топливного стержня.",
		"Это означает, что стержень является критическим, если кол-во отражателей вокруг него, умноженное на коэффициент, больше или равно 1. Таким образом, ТВЭЛ с коэффициентом 1/3 требует как минимум 3 соседних ОС, чтобы быть критическим.",
		"Поэтому реком-ся использовать хладагент в кач-ве теплоносителя для стабильных реакторов. Хладагент снижает коэффициент ТВЭЛ, делая невозможным переход любого ТВЭЛ в критическое состояние без ЗС.",
		"Это также увеличивает характеристики 'ПН' и 'Выброс', что действительно выгодно для стабильных реакторов, поскольку означает больше нейтронов и, следовательно, большую эффективность.",
		"Обычно вы можете придерживаться этих четырех правил, чтобы добиться максимальной эффективности вашего стабильного реактора:",
		"Правло 1:¶Если используемое топливо имеет коэффициент менее 1/8, окружение ТВЭЛ ПС дает наибольшую мощность HU.",
		"Правло 2:¶Если используемое топливо имеет коэффициент 1/8, окружение ТВЭЛ ПС, ОС или другими ТВЭЛ дает точно такую же мощность HU.",
		"Правло 3:¶Если коэффициент используемого ТВЭЛ превышает 1/8,окружение ТВЭЛ ОС или другими ТВЭЛ дает максимальную мощность HU.",
		"Правло 4:¶Не используйте теплоноситель на водной основе или ЗС в стабильных реакторах, так как они значительно снижаю срок службы и эффективность ТВЭЛ за счет их замедления, без какой-либо реальной выгоды в стабильных реакторах.",
		"¶¶===================¶Руководство по проектированию критических реакторов¶===================¶",
		"Критические ядерные реакторы деления, представляют собой конструкцию реактора деления с критическими ТВЭЛ. Это означает, что кол-во нейтронов и тепловая мощность постоянно увеличиваются во время работы реактора.",
		"Преимущества крит. реакторов:¶- Большая эффективность¶- Полностью настраиваемый нейтронный выход ¶- Обычно меньше, чем сопоставимые стабильные реакторы¶- Способны работать в качестве реакторов-горелок или обогащающих",
		"Недостатки крит. реакторов:¶- Нужен внешний контроль¶- Постоянное колебание мощности HU¶- Сложнее спроектировать¶- Снижение безопасности¶- Необходимы ТВЭЛ с более высоким коэффициентом",
		"Стержень реактора можно считать критическим, когда кол-во нейтронов, отраженных обратно на стержень, умноженное на коэффициент стержня, больше или равно параметру 'Выброс' топливного стержня.",
		"Это означает, что стержень является критическим, если кол-во отражателей вокруг него, умноженное на коэффициент, больше или равно 1. Таким образом, ТВЭЛ с коэффициентом 1/3 требует как минимум 3 соседних ОС, чтобы быть критическим.",
		"Поскольку кол-во нейтронов на ТВЭЛ будет бесконечно увеличиваться, вам необходимо контролировать реакцию, чтобы не выдавать больше энергии, чем вы можете выдержать.",
		"Счетчик Гейгера позволяет осуществлять такое управление автоматически в сочетании с машинными переключателями или селекторами из красного камня.",
		"Поэтому, когда кол-во нейтронов становится слишком большим, вы хотите выключить стержень реактора, чтобы снова сделать ТВЭЛ подкритическим, что приводит к уменьшению кол-ва нейтронов.",
		"Для достижения максимальной эффективности вам нужно, чтобы кол-во нейтронов на ТВЭЛ всегда оставалось ниже максимального значения этого ТВЭЛ, но при этом приближалось к нему как можно ближе.",
		"Вы также хотели бы, чтобы кол-во нейтронов уменьшалось как можно меньше при управлении реактором, чтобы ваша средняя мощность HU оставалась как можно выше.",
		"Поэтому вам нужно топливо с коэффициентом не менее 1/4, чтобы можно было построить критический реактор без использования ЗС.",
		"Проще всего исп-ть топливо, отвечающее этому условию — Уран-235. Его можно получить, перерабатывая Настуран. При использовании ЗС коэффициент топлива может быть уменьшен до 1/16, поэтому возможно исп-ие Урана-238.",
		"¶¶===================¶Руководство по проектированию реактора-горелки¶===================¶",
		"Реакторы-горелки представляют собой тип конструкции ядерного деления, целью которой яв-ся максимально быстрое сжигание ТВЭЛ, что позволяет перерабатывать истощенные ТВЭЛ для получения более качественных материалов ядерного топлива.",
		"Есть два способа значительно ускорить истощение ТВЭЛ:¶- Замедление ТВЭЛ¶- Превышение нейтронного максимума",
		"ТВЭЛ можно замедлить поместив его в реактор, охлаждаемый водным теплоносителем, либо поместив его рядом с ЗС или замедленным ТВЭЛ. При замедлении ТВЭЛ будет истощаться в четыре раза быстрее.",
		"Если количество нейтронов превышает максимальное значение ТВЭЛ на нем, он также будет истощаться в четыре раза быстрее, суммируясь с замедлением до 16 раз быстрее.¶Чем больше вы превысите максимум, тем быстрее будет истощаться ТВЭЛ.",
		"Если количество нейтронов в 2 раза превышает максимум на стержне, это снова заставит его истощаться в 2 раза быстрее, что будет суммироваться с замедлением до 32-кратного ускорения истощения.",
		"Число нейтронов в 17 раз превышает максимальное, поэтому ТВЭЛ истощается в 17 раз быстрее, в 4 раза из-за превышения максимума, то есть в 68 раз быстрее, в 4 раза, если стержень замедлился, то есть в 272 раза быстрее.",
		"Вместо значительного увеличения кол-ва нейтронов на ТВЭЛ можно также снизить нейтронный максимум, чтобы воспользоваться этим эффектом.",
		"Это можно легко сделать, используя тяжелую или тритиевую воду, которые снижают максимальную характеристику в 8 и 16 раз, а также замедляют все ТВЭЛ, поскольку они основаны на воде.",
		"Для достижения действительно высокого числа нейтронов желательно использовать критическую конструкцию реактора, хотя также возможно использовать реактор-горелку в качестве стабильной конструкции.",
		"Кобальт-60, хотя сам по себе почти бесполезен в качестве топлива для реактора, на самом деле легко сгорает до тория, который гораздо полезнее.",
		"¶¶===================¶Руководство по проектированию обогощающего реактора¶===================¶",
		"Целью обогощающих реакторов является превращение СС в обогащенные ТВЭЛ. Они позволяют получать хорошее ядерное топливо из распространенных видов топлива, таких как Торий и Уран-238.¶Хотя они и похожи на реакторы-горелки, их горасздо сложнее",
		"построить, так как для них требуется незамедлительная и крит. конструкция реактора. У СС есть показатель, называемый потерей. Он описывает, сколько нейтронов вычитается из любого количества помещенных в него нейтронов.",
		"Таким образом, когда у вас есть СС с потерей 500 нейтронов и вы пытаетесь испустить на него 300 нейтронов из одного соседнего ТВЭЛ, СС не получает нейтронов, а это означает, что процесс обогащения не будет продвигаться вперед.",
		"При излучении 300 нейтронов из другого ТВЭЛ на тот же СС, то есть испуская на него 300 нейтронов дважды, на СС по-прежнему не будет нейтронов, поскольку потери применяются к каждому количеству нейтронов.",
		"Это означает, что требуются высокие нейтронные выходы от одного ТВЭЛ, что делает невозможным использование некритической конструкции реактора для обогащения.",
		"Испускание 900 нейтронов на один и тот же СС с потерей 500 нейтронов будет означать, что 400 нейтронов окажутся на СС, а это значит, что они будут добавляться к процессу превращения в обогащенный ТВЭЛ каждую секунду.",
		"Другой ТВЭЛ, испускающий 900 нейтронов на тот же СС, то есть, по сути, 900 нейтронов дважды, будет означать 800 нейтронов на СС.",
		"Поскольку СС по существу должен находиться рядом с критическим ТВЭЛ, у ТВЭЛ остаются только три стороны для ОС, а это означает, что он может стать крит. только в том случае, если его коэффициент составляет 1/3 или больше.",
		"ЗС здесь явно непригодны,так как СС не могут принимать нейтроны от замедлительных ТВЭЛ. Это означает, что либо требуется топливо с более высоким коэф-ом, такое как плутоний, либо необходимо использовать ОЖ, которая повышает коэффициент.",
		"Расплавленные металлические ОЖ, такие как расплавленные олово или натрий, лучше всего подходят для обогощающих реакторов, поскольку они повышают коэф-нт наличия ТВЭЛ внутри, ",
		"делая возможным обогащение с более распространенными топливными материалами, такими как Уран-235, или позволяя лучшим топливным материалам одновременно обогощать больше СС.",
		"У них также есть преимущество в снижении выхода HU на нейтрон, что означает, что возможно большее количество нейтронов при меньшем охлаждении.",
		"Выброс большего кол-ва нейтронов на СС приводит к более быстрому и эффективному процессу обогащения, поскольку потеря нейтронов будет происходить за более короткое время, что приведет к общей потере меньшего количества нейтронов.",
		"¶¶===================¶Руководство по проектированию реактора на основе ториевой соли¶===================¶",
		"Ториевая соль – это действительно особый вид охлаждающей жидкости. Фактически, его даже нельзя считать теплоносителем, поскольку он не превращается в энергонесущую жидкость.",
		"Вместо этого его можно рассматривать скорее как топливо, поскольку он превращается в расплавленный хлорид лития, а это означает, что, по сути, торий внутри ториевой соли используется в качестве топлива.",
		"Ториевая соль также очень медленно распадается на расплавленный хлорид лития, только на каждые 2_560_000 нейтронов один литр ториевой соли превращается в расплавленный хлорид лития.",
		"Основное преимущество использования ториевой соли заключается в том, как она влияет на характеристики любого топливного стержня внутри, значительно повышая их.",
		"Однако, поскольку из ториевой соли невозможно извлечь энергию, поскольку она не превращается в энергонесущую жидкость, вы не можете просто использовать ториевую соль отдельно в любой конструкции реактора.",
		"В конструкции реактора, использующей ториевую соль, вместо нее должен использоваться хотя бы еще один дополнительный теплоноситель.",
		"АЗР, заполненная ториевой солью, будет содержать ТВЭЛ, а соседние АЗР, заполненные другим теплоносителем, будут улавливать нейтроны, испускаемые из этого реактора, с помощью ПС.",
		"Поскольку основным преимуществом ториевой соли является то, что она значительно повышает максимальную мощность ТВЭЛ внутри, обычно её имеет смысл применять только в критических реакторах.",
		"Критические реакторы с ториевой солью являются наиболее эффективными энергогенерирующими реакторами, но также и одними из самых сложных в строительстве.",
		"¶¶===================¶Усовершенствованные реакторы деления¶===================¶",
		"В этой главе вы найдете несколько советов и математические формулы для создания самых совершенных и эффективных реакторов.",
		"Расчет выброса нейтронов¶===================¶Топливный стержень будет излучать больше нейтронов на своих соседей, если на нем будет больше нейтронов.",
		"Точное количество нейтронов, которое любой ТВЭЛ испустит на соседа, можно рассчитать следующим образом:",
		"e_n=e+((n–s)*f)¶¶e_n: Излучаемые на соседа нейтроны¶¶e: 'Выброс' ТВЭЛ¶¶n: Количество нейтронов на ТВЭЛ¶¶s: 'ПН' ТВЭЛ¶¶f: 'Фактор' ТВЭЛ",
		"Расчет скорости истощения ТВЭЛ¶===================¶ТВЭЛ обычно имеет скорость истощения, равную единице, и поэтому прослужит столько, сколько указано в подсказке.",
		"Однако при наличии большего количества нейтронов на ТВЭЛ, чем нейтронный максимум, скорость истощения умножается на этот коэффициент:",
		"f=4*n/m¶¶f: Множитель скорости истощения¶¶n: Количество нейтронов на ТВЭЛ¶¶m: 'Максимум' ТВЭЛ",
		"Кроме того, замедление ТВЭЛ дополнительно умножит скорость его истощения в 4 раза. Разделите оставшееся время состояния ТВЭЛ на рассчитанную скорость истощения, чтобы получить реальное время до истощения.",
		"Секреты и лайфхаки¶===================¶Можно создавать реакторы с использованием нескольких ОЖ. Для крит. реакторов с ЗС, разумнее контролировать один соседний ТВЭЛ, чем сам ЗС, чтобы контролировать количество нейтронов.",
		"Роботы-манипуляторы позволяют точно автоматизировать работу активных зон реакторов.¶Фильтры можно использовать для упрощения автоматизации реакторов-горелок и обогащения.",
		"Более высокий показатель 'ПН' плох в крит. реакторах, так как способствует достижению максимума, не влияя на выброс нейтронов.¶Показатель выбросов в большинстве случаев не имеет значения для эффективности в крит. реакторах.",
		"Установка счетчика Гейгера в шестнадцатеричный режим полезна при попытке контролировать количество нейтронов выше 9_999. В шестнадцатеричном режиме вы можете сравнить до 65_535 нейтронов."
		});

		//-----

		for (int i = 1; i < OreDictMaterial.MATERIAL_ARRAY.length; i++) {
			if (OreDictMaterial.MATERIAL_ARRAY[i] != null && OreDictMaterial.MATERIAL_ARRAY[i].mID == i && (!OreDictMaterial.MATERIAL_ARRAY[i].mHidden || OreDictMaterial.MATERIAL_ARRAY[i].mDescription != null)) {
				ItemStack tStack = UT.Books.addMaterialDictionary(OreDictMaterial.MATERIAL_ARRAY[i]);

				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.gem               .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.dust              .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.ingot             .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.plate             .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.plateGem          .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.crushedCentrifuged.dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.bucket            .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.chemtube          .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
			}
		}
	}
}
