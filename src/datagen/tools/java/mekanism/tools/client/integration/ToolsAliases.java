package mekanism.tools.client.integration;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.client.integration.IAliasedTranslation;
import mekanism.tools.common.MekanismTools;
import net.minecraft.Util;

@NothingNullByDefault
public enum ToolsAliases implements IAliasedTranslation {
    ;

    private final String key;
    private final String alias;

    ToolsAliases(String path, String alias) {
        this.key = Util.makeDescriptionId("alias", MekanismTools.rl(path));
        this.alias = alias;
    }

    @Override
    public String getTranslationKey() {
        return key;
    }

    @Override
    public String getAlias() {
        return alias;
    }
}