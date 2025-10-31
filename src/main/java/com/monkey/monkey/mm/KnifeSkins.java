package com.monkey.monkey.mm;

import com.google.common.collect.ImmutableSet;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Set;

public final class KnifeSkins {
    private static final Set<Item> KNIFE_SKINS = ImmutableSet.of(
            Items.iron_sword, Items.stone_sword, Items.wooden_sword,
            Items.golden_sword, Items.diamond_sword,
            Items.iron_shovel, Items.stone_shovel, Items.diamond_shovel,
            Items.golden_shovel, Items.stick,
            Items.blaze_rod, Items.bone,
            Items.wooden_axe, Items.golden_axe, Items.diamond_axe,
            Items.golden_pickaxe, Items.diamond_pickaxe,
            Items.diamond_hoe, Items.golden_hoe,
            Items.quartz, Items.pumpkin_pie, Items.carrot,
            Items.golden_carrot, Items.cookie,
            Items.prismarine_shard, Items.cooked_beef,
            Items.beef, Items.book, Items.boat,
            Items.speckled_melon, Items.shears, Items.cooked_chicken,
            Items.name_tag, Items.netherbrick,
            Items.bread, Items.apple, Items.carrot_on_a_stick,
            Items.dye, Items.leather,
            Items.cooked_fish, Items.fish, Items.flint, Items.coal,
            Items.record_blocks, Items.record_cat, Items.record_far,
            Items.record_ward,
            Items.record_11, Items.record_13, Items.record_mall,
            Items.record_chirp,
            Items.record_mellohi, Items.record_stal, Items.record_wait,
            Items.record_strad,
            Item.getItemFromBlock(Blocks.deadbush),
            Item.getItemFromBlock(Blocks.sapling),
            Item.getItemFromBlock(Blocks.double_plant),
            Item.getItemFromBlock(Blocks.sponge)
    );

    private KnifeSkins() {

    }
    
    public static boolean isKnifeSkin(final Item item) {
        return item != null && KNIFE_SKINS.contains(item);
    }
}