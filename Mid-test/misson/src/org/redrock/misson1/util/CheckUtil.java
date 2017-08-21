package org.redrock.misson1.util;


public final class CheckUtil {
    public static boolean CheckLength(String text) {
        String regex = "^.{6,18}$";
        return text.matches(regex);
    }


    public static boolean CheckText(String text) {
        String regex = "^.*[\\s]+.*$";
        String regex2= "^.*[/^'@#&/*/|/?/+/(/)/[/]/{/}/-]+.*";
        return !text.matches(regex) && !text.matches(regex2);
    }
}
