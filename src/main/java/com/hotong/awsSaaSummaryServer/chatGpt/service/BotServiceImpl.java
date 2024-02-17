package com.hotong.awsSaaSummaryServer.chatGpt.service;

import com.hotong.awsSaaSummaryServer.chatGpt.config.ChatGptConfig;
import com.hotong.awsSaaSummaryServer.chatGpt.entity.Exam;
import com.hotong.awsSaaSummaryServer.chatGpt.model.request.BotRequest;
import com.hotong.awsSaaSummaryServer.chatGpt.model.request.ChatGptRequest;
import com.hotong.awsSaaSummaryServer.chatGpt.model.response.ChatGptResponse;
import com.hotong.awsSaaSummaryServer.chatGpt.repository.ExamRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BotServiceImpl implements BotService {

    private static RestTemplate restTemplate = new RestTemplate();
    private final static Logger LOG = Logger.getGlobal();

    @Autowired
    private ChatGptConfig chatGptConfig;

    //Build headers
    public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(chatGptConfig.getMEDIA_TYPE()));
        headers.add(chatGptConfig.getAUTHORIZATION(), chatGptConfig.getBEARER() + chatGptConfig.getAPI_KEY());
        return new HttpEntity<>(chatRequest, headers);
    }

    //Generate response
    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatRequestHttpEntity) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                chatGptConfig.getURL(),
                chatRequestHttpEntity,
                ChatGptResponse.class);
        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestion(BotRequest botRequest) {

        ArrayList<JSONObject> arrayJson = new ArrayList<JSONObject>();
        JSONArray jsonArr = new JSONArray();

        for (int i = 0; i < 3; i++) {
            JSONObject jSONObject = new JSONObject();
            if (i==0) {
                jSONObject.put("role","system");
                jSONObject.put("content","안녕 너는 세계적인 AWS 아키텍처 전문가야. 너는 AWS 서비스 설명만 해주는 역할이고 그 외에 말은 하지마.");
            } else if (i==1) {
                jSONObject.put("role","user");
                jSONObject.put("content","안녕 너는 세계적인 AWS 아키텍처 전문가야. 나에게 AWS 서비스에 관해서 자세히 알려줘. 너는 AWS 서비스 설명만 해주는 역할이고 그 외에 말은 하지마.");
            } else {
                jSONObject.put("role","assistant");
                jSONObject.put("content","감사합니다. AWS 서비스에 대해 궁금하신 사항이 있으신가요? 어떤 AWS 서비스를 알려드릴까요? 네. AWS 서비스 설명만 해드리고 , 그 이외의 말은 하지 않겠습니다.");
            }
            jsonArr.put(jSONObject);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("role","user");
        jSONObject.put("content",botRequest.getMessage());
        jsonArr.put(jSONObject);

        for (int k = 0; k < jsonArr.length(); k++) {
            JSONObject tempJson = jsonArr.getJSONObject(k);
            arrayJson.add(tempJson);
        }

        int intMaxToken = 500;
        chatGptConfig.setMAX_TOKEN(intMaxToken);

        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequest(
                                chatGptConfig.getMODEL(),
                                //botRequest.getMessage(),
                                arrayJson.toString(),
                                chatGptConfig.getTEMPERATURE(),
                                chatGptConfig.getMAX_TOKEN(),
                                chatGptConfig.getTOP_P())));
    }

    public ChatGptResponse askQuestionTest(BotRequest botRequest) {

        ArrayList<JSONObject> arrayJson = new ArrayList<JSONObject>();
        JSONArray jsonArr = new JSONArray();

        for (int i = 0; i < 3; i++) {
            JSONObject jSONObject = new JSONObject();
            if (i==0) {
                jSONObject.put("role","system");
                jSONObject.put("content","안녕 너는 세계적인 AWS 아키텍처 전문가야. 너는 AWS 서비스 문제만 알려주는 역할이고 그 외에 말은 하지마.");
            } else if (i==1) {
                jSONObject.put("role","user");
                jSONObject.put("content","안녕 너는 세계적인 AWS 아키텍처 전문가야. 나에게 AWS SAA 관련 문제를 내주라, 단 정답은 지금 알려주지말고 알려달라고 요청하면 그때알려줘. 너는 AWS 서비스 문제만 알려주는 역할이고 그 외에 말은 하지마.");
            } else {
                jSONObject.put("role","assistant");
                jSONObject.put("content","감사합니다. AWS SAA 문제에 대해 궁금하신 사항이 있으신가요? 어떤 AWS 문제를 알려드릴까요?, 네 정답은 알려달라고 하실때 알려드리겠습니다. 지금은 문제만 내겠습니다. 네. AWS 서비스 문제만 알려드리겠습니다. 그 외에 말은 하지않겠습니다.");
            }
            jsonArr.put(jSONObject);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("role","user");
        jSONObject.put("content",botRequest.getMessage());
        jsonArr.put(jSONObject);

        for (int k = 0; k < jsonArr.length(); k++) {
            JSONObject tempJson = jsonArr.getJSONObject(k);
            arrayJson.add(tempJson);
        }

        int intMaxToken = 500;
        chatGptConfig.setMAX_TOKEN(intMaxToken);

        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequest(
                                chatGptConfig.getMODEL(),
                                arrayJson.toString(),
                                chatGptConfig.getTEMPERATURE(),
                                chatGptConfig.getMAX_TOKEN(),
                                chatGptConfig.getTOP_P())));
    }

    public ChatGptResponse askQuestionAnswer(BotRequest botRequest) {

        ArrayList<JSONObject> arrayJson = new ArrayList<JSONObject>();
        JSONArray jsonArr = new JSONArray();

        for (int i = 0; i < 3; i++) {
            JSONObject jSONObject = new JSONObject();
            if (i==0) {
                jSONObject.put("role","system");
                jSONObject.put("content","안녕 너는 세계적인 AWS 아키텍처 전문가야. 너는 AWS 서비스 문제에 대한 답만 알려주는 역할이고 그 외에 말은 하지말아야해.");
            } else if (i==1) {
                jSONObject.put("role","user");
                jSONObject.put("content","안녕 너는 세계적인 AWS 아키텍처 전문가야. 너는 방금전에 나한테 AWS SAA 관련문제를 내줬어. 내가 그 문제를 보내줄테니까 나한테 그 문제에 대한 답이랑 설명을 해주면 돼. 너는 AWS 서비스 문제에 대한 답만 알려주는 역할이고 그 외에 말은 하지말아야해.");
            } else {
                jSONObject.put("role","assistant");
                jSONObject.put("content","감사합니다. AWS SAA 문제에 대한 정답이 궁금하신가요? 어떤 AWS 문제에 대한 답을 알려드릴까요? . AWS 문제에 대한 답만 알려드리고 그외에 말은 하지 않겠습니다.");
            }
            jsonArr.put(jSONObject);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("role","user");
        jSONObject.put("content",botRequest.getMessage());
        jsonArr.put(jSONObject);

        for (int k = 0; k < jsonArr.length(); k++) {
            JSONObject tempJson = jsonArr.getJSONObject(k);
            arrayJson.add(tempJson);
        }

        int intMaxToken = 500;
        chatGptConfig.setMAX_TOKEN(intMaxToken);

        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequest(
                                chatGptConfig.getMODEL(),
                                arrayJson.toString(),
                                chatGptConfig.getTEMPERATURE(),
                                chatGptConfig.getMAX_TOKEN(),
                                chatGptConfig.getTOP_P())));
    }


    public ChatGptResponse askQuestionOriginal(BotRequest botRequest) {

        int intMaxToken = 500;
        chatGptConfig.setMAX_TOKEN(intMaxToken);

        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequest(
                                chatGptConfig.getMODEL(),
                                botRequest.getMessage(),
                                chatGptConfig.getTEMPERATURE(),
                                chatGptConfig.getMAX_TOKEN(),
                                chatGptConfig.getTOP_P())));
    }

    public ChatGptResponse askQuestionOriginal2(BotRequest botRequest) {

        int intMaxToken = 1500;
        chatGptConfig.setMAX_TOKEN(intMaxToken);

        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequest(
                                chatGptConfig.getMODEL(),
                                botRequest.getMessage(),
                                chatGptConfig.getTEMPERATURE(),
                                chatGptConfig.getMAX_TOKEN(),
                                chatGptConfig.getTOP_P())));
    }

}






