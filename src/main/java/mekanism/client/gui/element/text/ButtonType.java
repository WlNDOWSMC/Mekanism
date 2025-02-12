package mekanism.client.gui.element.text;

import java.util.function.BiFunction;
import mekanism.client.gui.element.GuiElement.ButtonBackground;
import mekanism.client.gui.element.GuiElement.IClickable;
import mekanism.client.gui.element.button.MekanismImageButton;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;

public enum ButtonType {
    NORMAL((field, callback) -> new MekanismImageButton(field.gui(), field.getRelativeRight() - field.getHeight(), field.getRelativeY(),
          field.getHeight(), 12, MekanismUtils.getResource(ResourceType.GUI_BUTTON, "checkmark.png"), callback)),
    DIGITAL((field, callback) -> {
        MekanismImageButton ret = new MekanismImageButton(field.gui(), field.getRelativeRight() - field.getHeight(), field.getRelativeY(),
              field.getHeight(), 12, MekanismUtils.getResource(ResourceType.GUI_BUTTON, "checkmark_digital.png"), callback);
        ret.setButtonBackground(ButtonBackground.DIGITAL);
        return ret;
    });

    private final BiFunction<GuiTextField, IClickable, MekanismImageButton> buttonCreator;

    ButtonType(BiFunction<GuiTextField, IClickable, MekanismImageButton> buttonCreator) {
        this.buttonCreator = buttonCreator;
    }

    public MekanismImageButton getButton(GuiTextField field, IClickable callback) {
        return buttonCreator.apply(field, callback);
    }
}