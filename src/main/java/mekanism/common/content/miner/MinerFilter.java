package mekanism.common.content.miner;

import javax.annotation.Nonnull;
import mekanism.api.NBTConstants;
import mekanism.common.content.filter.BaseFilter;
import mekanism.common.util.NBTUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class MinerFilter<FILTER extends MinerFilter<FILTER>> extends BaseFilter<FILTER> {

    public Item replaceTarget = Items.AIR;
    public boolean requiresReplacement;

    protected MinerFilter() {
    }

    protected MinerFilter(FILTER filter) {
        replaceTarget = filter.replaceTarget;
        requiresReplacement = filter.requiresReplacement;
    }

    public boolean replaceTargetMatches(@Nonnull Item target) {
        return replaceTarget != Items.AIR && replaceTarget == target;
    }

    public abstract boolean canFilter(BlockState state);

    public abstract boolean hasBlacklistedElement();

    @Override
    public CompoundTag write(CompoundTag nbtTags) {
        super.write(nbtTags);
        nbtTags.putBoolean(NBTConstants.REQUIRE_STACK, requiresReplacement);
        if (replaceTarget != Items.AIR) {
            nbtTags.putString(NBTConstants.REPLACE_STACK, replaceTarget.getRegistryName().toString());
        }
        return nbtTags;
    }

    @Override
    public void read(CompoundTag nbtTags) {
        requiresReplacement = nbtTags.getBoolean(NBTConstants.REQUIRE_STACK);
        replaceTarget = NBTUtils.readRegistryEntry(nbtTags, NBTConstants.REPLACE_STACK, ForgeRegistries.ITEMS, Items.AIR);
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        super.write(buffer);
        buffer.writeBoolean(requiresReplacement);
        buffer.writeRegistryId(replaceTarget);
    }

    @Override
    public void read(FriendlyByteBuf dataStream) {
        requiresReplacement = dataStream.readBoolean();
        replaceTarget = dataStream.readRegistryId();
    }

    @Override
    public int hashCode() {
        int code = 1;
        code = 31 * code + replaceTarget.hashCode();
        code = 31 * code + (requiresReplacement ? 1 : 0);
        return code;
    }

    @Override
    public boolean equals(Object filter) {
        return filter instanceof MinerFilter && ((MinerFilter<?>) filter).requiresReplacement == requiresReplacement &&
               ((MinerFilter<?>) filter).replaceTarget == replaceTarget;
    }
}