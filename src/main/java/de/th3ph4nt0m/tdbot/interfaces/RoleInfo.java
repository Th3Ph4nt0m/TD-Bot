package de.th3ph4nt0m.tdbot.interfaces;

import java.awt.*;

public class RoleInfo {
  public final String name;
  public final Color color;
  public final int userCount;

  public RoleInfo(String name, Color color, int userCount) {
    this.name = name;
    this.color = color;
    this.userCount = userCount;
  }
}
