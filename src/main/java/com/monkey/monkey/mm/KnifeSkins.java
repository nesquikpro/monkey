package com.monkey.monkey.mm;

import com.google.common.collect.Sets;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Set;

public class KnifeSkins {
    private static final Set<Item> kfSk = Sets.newHashSet(
            Items.iron_sword, Items.stone_sword, Items.wooden_sword, Items.golden_sword, Items.diamond_sword,
            Items.iron_shovel, Items.stone_shovel, Items.diamond_shovel, Items.golden_shovel,
            Items.stick, Items.blaze_rod, Items.bone, Items.wooden_axe, Items.golden_axe, Items.diamond_axe,
            Items.golden_pickaxe, Items.diamond_pickaxe, Items.diamond_hoe, Items.golden_hoe,
            Items.quartz, Items.pumpkin_pie, Items.carrot, Items.golden_carrot, Items.cookie,
            Items.prismarine_shard, Items.cooked_beef, Items.beef, Items.book, Items.boat,
            Items.speckled_melon, Items.shears, Items.cooked_chicken, Items.name_tag, Items.netherbrick,
            Items.bread, Items.apple, Items.carrot_on_a_stick, Items.dye, Items.leather,
            Items.cooked_fish, Items.fish, Items.flint, Items.coal,
            Items.record_blocks, Items.record_cat, Items.record_far, Items.record_ward,
            Items.record_11, Items.record_13, Items.record_mall, Items.record_chirp,
            Items.record_mellohi, Items.record_stal, Items.record_wait, Items.record_strad,
            Item.getItemFromBlock(Blocks.deadbush), Item.getItemFromBlock(Blocks.sapling),
            Item.getItemFromBlock(Blocks.double_plant), Item.getItemFromBlock(Blocks.sponge)
    );

    public static boolean isKfSk(final Item itIn) {
        return itIn != null && kfSk.contains(itIn);
    }
}

//        addItemName(Items.diamond_hoe, 0, "The Scythe");
//        addItemName(Items.golden_hoe, 0, "Farming Implement");
//        addItemName(Items.iron_sword, 0, "Default Iron Sword");
//        addItemName(Items.wooden_sword, 0, "Stake");
//        addItemName(Items.stone_sword, 0, "Cheapo Sword");
//        addItemName(Items.diamond_sword, 0, "MVP Diamond Sword");
//        addItemName(Items.golden_sword, 0, "VIP Golden Sword");
//        addItemName(Items.golden_shovel, 0, "Gold Spray Painted Shovel");
//        addItemName(Items.diamond_shovel, 0, "Only the Best");
//        addItemName(Items.iron_shovel, 0, "Shovel");
//        addItemName(Items.stone_shovel, 0, "Stick with a Hat");
//        addItemName(Items.golden_pickaxe, 0, "Gold Digger");
//        addItemName(Items.diamond_pickaxe, 0, "Double Death Scythe");
//        addItemName(Items.golden_axe, 0, "Shred");
//        addItemName(Items.diamond_axe, 0, "Timber");
//        addItemName(Items.wooden_axe, 0, "Wooden Axe");
//        addItemName(Item.getItemFromBlock(Blocks.deadbush), 0, "Earthen Dagger");
//        addItemName(Item.getItemFromBlock(Blocks.deadbush), 1, "Chewed Up Bush");
//        addItemName(Items.book, 0, "Grimoire");
//        addItemName(Items.speckled_melon, 0, "Glistering Melon");
//        addItemName(Items.boat, 0, "Easter Basket");
//        addItemName(Items.bread, 0, "Freshly Frozen Baguette");
//        addItemName(Items.cooked_fish, 0, "Salmon");
//        addItemName(Items.shears, 0, "Shears");
//        addItemName(Items.record_blocks, 0, "Frisbee");
//        addItemName(Items.cooked_chicken, 0, "Basted Turkey");
//        addItemName(Items.netherbrick, 0, "Bloody Brick");
//        addItemName(Items.cooked_beef, 0, "Grilled Steak");
//        addItemName(Items.prismarine_shard, 0, "Ice Shard");
//        addItemName(Items.cookie, 0, "Sweet Treat");
//        addItemName(Items.golden_carrot, 0, "Sparkly Snack");
//        addItemName(Item.getItemFromBlock(Blocks.double_plant), 0, "Prickly");
//        addItemName(Items.carrot, 0, "Rudolph's Favorite Snack");
//        addItemName(Items.bone, 0, "Big Bone");
//        addItemName(Items.flint, 0, "Somewhat Sharp Rock");
//        addItemName(Items.coal, 0, "Campfire Leftovers");
//        addItemName(Items.name_tag, 0, "Mouse Trap");
//        addItemName(Items.leather, 0, "Unfortunately Colored Jacket");
//        addItemName(Items.pumpkin_pie, 0, "Pumpkin Pie");
//        addItemName(Items.quartz, 0, "Jugged Knife");
//        addItemName(Items.blaze_rod, 0, "Blaze Rod");
//        addItemName(Items.reeds, 0, "Fragile Plant");
//        addItemName(Items.stick, 0, "Stick");
//        addItemName(Items.dye, 1, "Rudolph's Nose");
//        addItemName(Items.dye, 4, "10000 Spoons");

//    private static void addItemName(Item item, int meta, String name) {
//        itemNames.computeIfAbsent(item, k -> new HashMap<>()).put(meta, name);
//    }
//
//    public static String getCustomName(ItemStack stack) {
//        if (stack == null) return "unknown item";
//
//        Item item = stack.getItem();
//        int meta = stack.getMetadata();
//
//        if (itemNames.containsKey(item) && itemNames.get(item).containsKey(meta)) {
//            return itemNames.get(item).get(meta);
//        }
//        return item.getUnlocalizedName();
//    }