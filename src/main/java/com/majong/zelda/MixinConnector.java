package com.majong.zelda;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinConnector implements IMixinConnector{

	@Override
	public void connect() {
		// TODO �Զ����ɵķ������
		Mixins.addConfigurations("zelda.mixin.json");
	}

}
