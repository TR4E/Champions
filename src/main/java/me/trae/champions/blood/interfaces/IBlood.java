package me.trae.champions.blood.interfaces;

import me.trae.framework.shared.utility.interfaces.IExpire;
import me.trae.framework.shared.utility.interfaces.IGetDuration;
import me.trae.framework.shared.utility.interfaces.IGetSystemTime;
import me.trae.framework.shared.utility.interfaces.IRemaining;
import org.bukkit.entity.Item;

import java.util.UUID;

public interface IBlood extends IGetSystemTime, IGetDuration, IRemaining, IExpire {

    UUID getUUID();

    Item getItem();
}