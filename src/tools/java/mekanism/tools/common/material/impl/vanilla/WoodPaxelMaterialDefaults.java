package mekanism.tools.common.material.impl.vanilla;

import mekanism.tools.common.material.VanillaPaxelMaterial;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.Tiers;

@MethodsReturnNonnullByDefault
public class WoodPaxelMaterialDefaults extends VanillaPaxelMaterial {

    @Override
    public Tiers getVanillaTier() {
        return Tiers.WOOD;
    }

    @Override
    public float getPaxelDamage() {
        return 7;
    }
}