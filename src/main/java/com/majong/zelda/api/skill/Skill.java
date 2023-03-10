package com.majong.zelda.api.skill;

import net.minecraft.world.entity.ai.goal.Goal;

public abstract class Skill extends Goal{
	private ISkillable skillable;
	public Skill(ISkillable skillable) {
		this.skillable=skillable;
	}
	@Override
	public boolean canUse() {
		if(skillable.getskill()!=getID())
			return false;
		else
			return additionalCanUse();
	}
	public abstract byte getID();
	public abstract boolean additionalCanUse();
	public void finish() {
		skillable.setskill(skillable.nextskill(getID()));
	}
}
