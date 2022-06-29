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
        Device dev1 = new Device();
        dev1.setId(1L);
        dev1.setObjectId(1L);
        dev1.setDeviceType("LIGHT");

        Device dev2 = new Device();
        dev2.setId(2L);
        dev2.setObjectId(1L);
        dev2.setDeviceType("DOOR");

        List<String> alarmTypes = new ArrayList<>();
        alarmTypes.add("WARNING");
        alarmTypes.add("MESSAGE");
        alarmTypes.add("ALARM");


        List<Device> devices = new ArrayList<>();
        devices.add(dev1);
        devices.add(dev2);

        Timer timer = new Timer();
        int begin = 1000; //timer starts after 1 second.
        int timeinterval = 10 * 1000; //timer executes every 10 seconds.
        timer.scheduleAtFixedRate(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                LOG.info(":)");
                int randomIndex = (int)(Math.random()*(devices.size()));
                Device selectedDevice = devices.get(randomIndex);
                Alarm alarm = new Alarm();
                alarm.setDate(LocalDate.now().toString());
                alarm.setTime(LocalTime.now().toString());
                alarm.setDevice(new DeviceDTO(selectedDevice));
                alarm.setObjectId(selectedDevice.getObjectId());

                randomIndex = (int)(Math.random()*(3));
                alarm.setMessageType(alarmTypes.get(randomIndex));

                if (alarm.getMessageType().equals("MESSAGE")) alarm.setMessage("Message: .... :)");
                if (alarm.getMessageType().equals("WARNING")) alarm.setMessage("Warning: .... :|");
                if (alarm.getMessageType().equals("ALARM")) alarm.setMessage("Alarm!!: .... :(");

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
        LOG.info("Finished comunicating with MyHouse.");
    }
}
