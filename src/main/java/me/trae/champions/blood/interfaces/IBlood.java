package me.trae.champions.blood.interfaces;

import me.trae.framework.shared.utility.interfaces.duration.IExpire;
import me.trae.framework.shared.utility.interfaces.duration.IGetDuration;
import me.trae.framework.shared.utility.interfaces.duration.IGetSystemTime;
import me.trae.framework.shared.utility.interfaces.duration.IRemaining;
import org.bukkit.entity.Item;

import java.util.UUID;

public interface IBlood extends IGetSystemTime, IGetDuration, IRemaining, IExpire {

    UUID getUUID();

    Item getItem();
}