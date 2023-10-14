package com.website.cibercrime.data.converter;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import com.website.cibercrime.data.entity.IP;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class IPConverter implements Converter<String, List<IP>> {
    private final static String ipRegex = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b";
    private final static Pattern ipPattern = Pattern.compile(ipRegex, Pattern.MULTILINE);
    private final static String ipDateRegex = "\\b\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\b";
    private final static Pattern ipDatePattern = Pattern.compile(ipDateRegex, Pattern.MULTILINE);
    private final static String ipTimeRegex = "\\b\\d{1,2}:\\d{1,2}:\\d{1,2}\\b";
    private final static Pattern ipTimePattern = Pattern.compile(ipTimeRegex, Pattern.MULTILINE);

    @Override
    public Result<List<IP>> convertToModel(String value, ValueContext context) {
        log.info("Convert To Model");
        List<IP> ipList = new ArrayList<>();
        String[] ipLineArray = value.split("\n");
        log.info("ipLineArray -> {} ", Arrays.stream(ipLineArray).toList());

        IP ip = new IP();
        int countField = getCountFieldsOfObject(ip);
        int counter = 0;

        for (String ipComponent : ipLineArray) {
//            counter++;
            Matcher ipMatcher = ipPattern.matcher(ipComponent);
            Matcher ipDateMatcher = ipDatePattern.matcher(ipComponent);
            Matcher ipTimeMatcher = ipTimePattern.matcher(ipComponent);

            if (ipMatcher.find()) {
                ip.setIp(ipMatcher.group().trim());
                log.info("ipMatcher -> {} ", ipMatcher.group());
                counter++;
            }
            if (ipDateMatcher.find()) {
                ip.setIpDate(ipDateMatcher.group().trim());
                log.info("ipDateMatcher -> {} ", ipDateMatcher.group());
                counter++;
            }
            if (ipTimeMatcher.find()) {
                ip.setTime(ipTimeMatcher.group().trim());
                log.info("ipTimeMatcher -> {} ", ipTimeMatcher.group());
                counter++;
            }
            if (counter == countField && ip.getId() == null && ip.getIp() != null && ip.getIpDate() != null && ip.getTime() != null) {
                ipList.add(ip);
                log.info("ip -> {} ", ip);
                ip = new IP();
                counter = 0;
            }
        }
        log.info("ipList -> {} ", ipList);
        return Result.ok(ipList);
    }

    @Override
    public String convertToPresentation(List<IP> value, ValueContext context) {
        log.info("Convert To Presentation");
        return String.join("\n",
                value.stream()
                        .map(ip -> {
                            String iP = ip.getIp() + " " + ip.getIpDate() + " " + ip.getTime();
                            log.info("ip -> {} ", iP);
                            return iP;
                        })
                        .toList()
        );
    }

    private int getCountFieldsOfObject(IP ip) {
        return ip.getClass().getDeclaredFields().length;
    }


}
