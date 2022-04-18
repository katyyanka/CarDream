package org.bsuir.coursework.helpers;

public class parserStringToRadian {
    public static double toRadian(String angle) {
        try {
            String s[] = angle.split("°");
            int angles = Integer.parseInt(s[0]);
            Double minutes = Double.parseDouble(s[1].substring(0, s[1].length()-1));
            double result = (angles * 60 + minutes)/60 * Math.PI / 180;
            return result;
        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(toRadian("23°41′"));
        System.out.println(toRadian("52°05′"));
        System.out.println(toRadian("27°33′"));
        System.out.println(toRadian("53°55′"));
    }
}
