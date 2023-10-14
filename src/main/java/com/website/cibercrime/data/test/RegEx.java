package com.website.cibercrime.data.test;

import com.website.cibercrime.data.entity.IP;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    public static List<IP> ipList = new ArrayList<>();
    private final static String value = "172.16.25.11 27.05.2023 12:05:48 272.16.25.11 28.05.2023 10:10:48";
    private final static String ipRegex = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b";
    private final static Pattern ipPattern = Pattern.compile(ipRegex, Pattern.MULTILINE);
    private final static String ipDateRegex = "\\b\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\b";
    private final static Pattern ipDatePattern = Pattern.compile(ipDateRegex, Pattern.MULTILINE);
    private final static String ipTimeRegex = "\\b\\d{1,2}:\\d{1,2}:\\d{1,2}\\b";
    private final static Pattern ipTimePattern = Pattern.compile(ipTimeRegex, Pattern.MULTILINE);

    public static void main(String[] args) {

        String[] ipLineArray = value.split(" ");
        IP ip = new IP();
        int countField = getCountFieldsOfObject(ip);
        int counter = 0;

        for (String ipComponent : ipLineArray) {


            System.out.println("ipLine in convertToModel -> " + ipComponent);
            Matcher ipRegex = ipPattern.matcher(ipComponent);
            Matcher ipDateRegex = ipDatePattern.matcher(ipComponent);
            Matcher ipTimeRegex = ipTimePattern.matcher(ipComponent);

            if (ipRegex.find()) {
                ip.setIp(ipRegex.group());
            } else if (ipDateRegex.find()) {
                ip.setIpDate(ipDateRegex.group());
            } else if (ipTimeRegex.find()) {
                ip.setTime(ipTimeRegex.group());
            }

            if (counter == countField && ip.getIp() != null && ip.getIpDate() != null && ip.getTime() != null) {
                ipList.add(ip);
                ip = new IP();
            }

        }

        for (IP ip1 : ipList) {
            System.out.println(ip1.getIp() + " " + ip1.getIpDate() + " " + ip1.getTime());
        }

        System.out.println(countField);

    }

    public static boolean isFullIP(IP ip, int counter) {
        int countField = getCountFieldsOfObject(ip);
        return counter == countField && ip.getIp() != null && ip.getIpDate() != null && ip.getTime() != null;
    }

    private static int getCountFieldsOfObject(Object object) {
        return object.getClass().getDeclaredFields().length - 1;
    }
}

//            while (matcher.find()) {
//                System.out.println("Full match: " + matcher.group(0));
//
//                for (int i = 1; i <= matcher.groupCount(); i++) {
//                    System.out.println("Group " + i + ": " + matcher.group(i));
//                }
//    }
//}
