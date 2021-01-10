package de.th3ph4nt0m.tdbot.interfaces;

import de.th3ph4nt0m.tdbot.permission.DiscordRank;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
	String name();
	String[] invokes();
	DiscordRank accessRank();
	String description()default "";
	int args() default 0;
}