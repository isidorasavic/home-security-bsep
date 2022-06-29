package com.bsep.device.service;

import com.bsep.device.dto.Alarm;
import com.bsep.device.dto.DeviceDTO;
import com.bsep.device.model.Device;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class AlarmService {

    public static String BASE_URL = "http://localhost:8081/api";

    public static String URL_ENCODING = "UTF-8";

    private static final Logger LOG = LoggerFactory.getLogger(AlarmService.class);

    public static void generateAlarms(){
        Device dev1 = new Device(1L, 1L,  "LIGHT");
        Device dev2 = new Device(2L, 1L, "DOOT");
        DeviceDTO dev1DTO = new DeviceDTO(dev1);
        DeviceDTO dev2DTO = new DeviceDTO(dev2);

        Alarm a1 = new Alarm("MESSAGE", 1L, dev2DTO, "Door opened", null, null);
        Alarm a2 = new Alarm("ALARM", 1L, dev2DTO, "Door opened for more than 10 minutes", null, null);
        Alarm a3 = new Alarm("WARNING", 1L, dev2DTO, "Door opened 3 times in a row", null, null);
        Alarm a4 = new Alarm("MESSAGE", 1L, dev1DTO, "Light turned off", null, null);
        Alarm a5 = new Alarm("ALARM", 1L, dev1DTO, "Light on for more than 24 hours", null, null);
        Alarm a6 = new Alarm("WARNING", 1L, dev1DTO, "Light turned on/off three times in a minute", null, null);

        List<Alarm> alarms = new ArrayList<>();
        alarms.add(a1);
        alarms.add(a2);
        alarms.add(a3);
        alarms.add(a4);
        alarms.add(a5);
        alarms.add(a6);


        Timer timer = new Timer();
        int begin = 1000; //timer starts after 1 second.
        int timeinterval = 10 * 1000; //timer executes every 10 seconds.
        timer.scheduleAtFixedRate(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                LOG.info(":)");
                int randomIndex = (int)(Math.random()*(alarms.size()));
                Alarm alarm = alarms.get(randomIndex);
                alarm.setDate(LocalDate.now().toString());
                alarm.setTime(LocalTime.now().toString());
                sendMessage(alarm);

            }
        },begin, timeinterval);
    }

    public static void sendMessage(Alarm alarm) throws URISyntaxException {
        String url = BASE_URL + "/addMessage";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        URI uri = new URI(url);

        HttpEntity<Alarm> httpEntity = new HttpEntity<>(alarm, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, Alarm.class);
        LOG.info("Sent message to MyHouse.");
    }
}
