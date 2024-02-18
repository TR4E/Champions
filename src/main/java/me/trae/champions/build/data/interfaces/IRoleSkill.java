package me.trae.champions.build.data.interfaces;

public interface IRoleSkill {

    String getType();

    String getName();

    int getLevel();

    void setLevel(final int level);

    String getDisplayName();
}