package zawkin.asuna.kunuz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.dto.SmsAuthResponseDTO;
import zawkin.asuna.kunuz.entity.SmsHistoryEntity;
import zawkin.asuna.kunuz.enums.ProfileStatus;
import zawkin.asuna.kunuz.repository.SmsHistoryRepository;
import zawkin.asuna.kunuz.util.RandomUtil;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class SmsService {
    @Autowired
    private SmsHistoryRepository repository;

    public void sendRegistrationSms(String phoneNumber) {
        int code = RandomUtil.getRandomInt();
        String message = "<#>kitabu.uz partali. Ro'yxatdan o'tish uchun tasdiqlash kodi: " + code;
        sendSms(phoneNumber, message);
    }

    private void sendSms(String phone, String message) {
        try {
            // TODO limit 3
            // TODO save
            RequestBody formBody = new FormBody.Builder().add("mobile_phone", phone).add("message", message).add("from", "4546").build();

            Request request = new Request.Builder().url("https://notify.eskiz.uz/api/message/sms/send").addHeader("Content-Type", "application/json").addHeader("Authorization", "Bearer " + getToken()).post(formBody).build();

            OkHttpClient client = new OkHttpClient();
            Call call = client.newCall(request);

            Response response = call.execute();
            System.out.println(response.body().string());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String getToken() {
        // repository
        // TODO
        return "eyVCJ9.aass.a2QB";
    }

    private String getNewToken() {
        try {
            RequestBody formBody = new FormBody.Builder().add("email", "emailfromsdasdasdasd ").add("password", "13123123123").add("from", "4546").build();

            Request request = new Request.Builder().url("https://notify.eskiz.uz/api/auth/login").addHeader("Content-Type", "application/json").post(formBody).build();

            OkHttpClient client = new OkHttpClient();
            Call call = client.newCall(request);

            Response response = call.execute();
            String json = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            SmsAuthResponseDTO result = mapper.readValue(json, SmsAuthResponseDTO.class);
            return result.getData().getToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public boolean check(String phoneNumber, String code) {
        // 3. check code is correct
        // 4. sms expiredTime
        // 5. attempt count
        SmsHistoryEntity entity = repository.findByPhone(phoneNumber);

        LocalDateTime now = LocalDateTime.now();
        Duration threshold = Duration.ofMinutes(1);
        Duration duration = Duration.between(now, entity.getCreatedDate());

        return Objects.equals(code, entity.getCode());
    }
}
