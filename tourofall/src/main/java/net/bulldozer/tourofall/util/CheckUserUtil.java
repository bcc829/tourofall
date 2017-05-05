package net.bulldozer.tourofall.util;

import net.bulldozer.tourofall.user.model.User;

public class CheckUserUtil {
	public static boolean sameAsFormer(User nowUser, User newUser){
		return nowUser==null? newUser == null : nowUser.equals(newUser);
	}
}
