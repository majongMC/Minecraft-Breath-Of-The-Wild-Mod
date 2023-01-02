package com.majong.zelda.api.skill;

public interface ISkillable {
	byte getskill();
	byte nextskill(byte currentskill);
	void setskill(byte skill);
}
