package de.th3ph4nt0m.tdbot.interfaces;

import de.th3ph4nt0m.tdbot.permission.DiscordRank;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
	/**
	 * @return returns the name of the command
	 */
	String name();

	/**
	 * @return returns the command invokes
	 */
	String[] invokes();

	/**
	 * @return returns the minimum access Rank (defaults to THE_NATION)
	 */
	DiscordRank accessRank() default DiscordRank.THE_NATION;

	/**
	 * @return returns the command description for the help command
	 */
	String description() default "";

	/**
	 * @return returns the required arguments for the command to run (defaults to none)
	 */
	String[] args() default {};
}