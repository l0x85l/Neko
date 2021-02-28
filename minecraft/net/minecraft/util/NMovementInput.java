package net.minecraft.util;


import neko.mod.Mod;
import neko.mod.ModManager;
import neko.mod.impl.ToggleSprint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.potion.Potion;

import java.text.DecimalFormat;

public class NMovementInput extends MovementInput {

    private boolean sprint = false;
    private GameSettings gameSettings;
    private int sneakWasPressed = 0;
    private int sprintWasPressed = 0;
    private EntityPlayerSP player;
    private float originalFlySpeed = -1.0F;
    private float boostedFlySpeed = 1;
    private Minecraft mc = Minecraft.getMinecraft();

    public NMovementInput(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    @Override
    public void updatePlayerMoveState() {

        player = mc.thePlayer;
        moveStrafe = 0.0F;
        moveForward = 0.0F;

        if(gameSettings.keyBindForward.isKeyDown()) {
            moveForward++;
        }

        if(gameSettings.keyBindBack.isKeyDown()) {
            moveForward--;
        }

        if(gameSettings.keyBindLeft.isKeyDown()) {
            moveStrafe++;
        }

        if(gameSettings.keyBindRight.isKeyDown()) {
            moveStrafe--;
        }

        jump = gameSettings.keyBindJump.isKeyDown();

        sneak = gameSettings.keyBindSneak.isKeyDown();


        if(sneak) {
            moveStrafe *= 0.3F;
            moveForward *= 0.3F;
        }

        ToggleSprint mod = (ToggleSprint) ModManager.getMod(ToggleSprint.class);
        if(mod.getState()) {

            if(gameSettings.keyBindSprint.isKeyDown()) {
                if(sprintWasPressed == 0) {

                    if(sprint) {
                        sprintWasPressed = -1;
                    }
                    else if(player.capabilities.isFlying) {
                        sprintWasPressed = mod.keyHoldTicks + 1;
                    }
                    else {
                        sprintWasPressed = 1;
                    }
                    sprint = !sprint;
                }
                else if(sprintWasPressed > 0) {
                    sprintWasPressed++;
                }
            }
            else {
                if((mod.keyHoldTicks > 0) && (sprintWasPressed > mod.keyHoldTicks)) {
                    sprint = false;
                }
                sprintWasPressed = 0;
            }

        }
        else {
            sprint = false;
        }

        if(sprint && moveForward == 1.0F && player.onGround && !player.isUsingItem() && !player.isPotionActive(Potion.blindness)) {
            player.setSprinting(true);
        }

        if(mod.flyBoost && player.capabilities.isCreativeMode && player.capabilities.isFlying && (Minecraft.getMinecraft().getRenderViewEntity() == player) && sprint) {

            if(originalFlySpeed < 0.0F || this.player.capabilities.getFlySpeed() != boostedFlySpeed) {
                originalFlySpeed = this.player.capabilities.getFlySpeed();
            }

            boostedFlySpeed = originalFlySpeed * mod.flyBoostFactor;
            player.capabilities.setFlySpeed(boostedFlySpeed);

            if(sneak) {
                player.motionY -= 0.15D * (double)(mod.flyBoostFactor - 1.0F);
            }

            if(jump) {
                player.motionY += 0.15D * (double)(mod.flyBoostFactor - 1.0F);
            }

        }
        else {
            if(player.capabilities.getFlySpeed() == boostedFlySpeed) {
                this.player.capabilities.setFlySpeed(originalFlySpeed);
            }
            originalFlySpeed = -1.0F;
        }

    }

    private final DecimalFormat df = new DecimalFormat("#.0");
    public String getDisplayText() {

        String displayText = "";

        boolean isFlying = mc.thePlayer.capabilities.isFlying;
        boolean isRiding = mc.thePlayer.isRiding();
        boolean isHoldingSprint = gameSettings.keyBindSprint.isKeyDown();

        if(isFlying) {
            if(originalFlySpeed > 0.0F) {
                displayText += "[Flying (" + df.format(boostedFlySpeed / originalFlySpeed) + "x Boost)]  ";
            } else {
                displayText += "[Flying]  ";
            }
        }

        if(isRiding) {
            displayText += "[Riding]  ";
        }


        else if(sprint && !isFlying && !isRiding ) {
            if(isHoldingSprint) {
                displayText += "[Sprinting (Key Held)]  ";
            } else {
                displayText += "[Sprinting (Key Toggled)]  ";
            }
        }


        return displayText.trim();

    }

}