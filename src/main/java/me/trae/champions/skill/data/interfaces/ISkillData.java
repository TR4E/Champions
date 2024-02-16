package me.trae.champions.skill.data.interfaces;

import me.trae.framework.shared.utility.interfaces.*;

import java.util.UUID;

public interface ISkillData extends IGetSystemTime, IGetDuration, ISetDuration, IRemaining, IExpire {

    UUID getUUID();
}