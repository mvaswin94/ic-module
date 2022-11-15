package mipl.icmodule.token;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import mipl.icmodule.appsetting.AppSetting;
import mipl.icmodule.appsetting.AppSettingRepository;
import mipl.icmodule.bill.Bill;
import mipl.icmodule.bill.BillRepository;
import mipl.icmodule.bill.Investigations;
import mipl.icmodule.client.Client;
import mipl.icmodule.client.ClientRepository;
import mipl.icmodule.dto.PrepareIc;

/**
 * IntegrationService
 */
@Service
public class IntegrationService {
	
	@Autowired 
	AppSettingRepository AppSettingRepo;
	
    private String username = "freedom";

    private String password = "@nders0n";

    // Contains Security token freedomRisApi
    private String authToken;

    // Contains TimeString of last Sync in FreedomRis Format YYYYMMDDHHMMSS
    private String lastSyncTimeString;

    // URLs
    private String INTEGRATION_URL = "http://freedomris.ddns.net:8001/FreedomRisNotificationApp";
    private String INTEGRATION_TOKEN_URL = INTEGRATION_URL + "/token";
    private String INTEGRATION_CLIENT_URL = INTEGRATION_URL + "/api/v1/GetClient";
    private String INTEGRATION_BILL_URL = INTEGRATION_URL + "/api/v1/GetBill";

    // Autowirings
    @Autowired
    ClientRepository ClientRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppSettingRepository appSetting;

    @Autowired
    BillRepository BillRepo;

    public String refreshAuthToken() {
        // TODO Get Authtoken from Server
        ResponseEntity<Token> response = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        try {
            MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
            parametersMap.add("grant_type", "password");
            parametersMap.add("username", this.username);
            parametersMap.add("password", this.password);

            HttpEntity<Object> postData = new HttpEntity<Object>(parametersMap, headers);
            response = restTemplate.exchange(INTEGRATION_TOKEN_URL, HttpMethod.POST, postData, Token.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Token token = response.getBody();
        // AppSetting setting = new AppSetting("auth_token", token.getAccessToken());
        this.authToken = token.getAccessToken();
        // appSetting.save(setting);
        return this.authToken;
    }

    public Boolean syncBills() {

        // TODO Get Auth Token

        refreshAuthToken();

        // TODO Send POST Request To the Server
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "bearer " + authToken);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(this.INTEGRATION_BILL_URL)
                .queryParam("FromDate", "20190401000000").queryParam("ToDate", "20190430115959")
                .queryParam("InvestigationList", "true").queryParam("Mode", "5").build();

        HttpEntity<String> getData = new HttpEntity<String>(headers);

        ResponseEntity<List<Bill>> response1 = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, getData,
                new ParameterizedTypeReference<List<Bill>>() {
                });

        List<Bill> bill = response1.getBody();
        BillRepo.saveAll(bill);

        // TODO Save the Bills to repository

        return true;
    }

    public void syncClients() {

        refreshAuthToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "bearer " + authToken);

        HttpEntity<String> getData = new HttpEntity<String>(headers);

        ResponseEntity<List<Client>> response = restTemplate.exchange(INTEGRATION_CLIENT_URL, HttpMethod.GET, getData,
                new ParameterizedTypeReference<List<Client>>() {
                });

        // FIXME For Every Sync client Master should be deleted
        List<Client> client = response.getBody();
        
        for (int i = 0; i < client.size(); i++) {
			Client clientObj = client.get(i);
			clientObj.setPercentage(0);
			clientObj.setStatus((byte) 0);
        }
        ClientRepo.saveAll(client);
    }
    
/*-----------------------------------------Sync Bills AutoTrigger-----------------------------------------------*/ 
    //@Scheduled(fixedRate=60000) // 1 Minute
    @Scheduled(fixedRate=3600000)  // 1 Hour
    public String syncBillsAutoTrigger() {

        // TODO Get Auth Token

        refreshAuthToken();

        // TODO Send POST Request To the Server
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "bearer " + authToken);

/* -----------------------------------------Current Date and Time---------------------------------------------- */        
      
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String currentDateTime = dateFormat.format(date); 
		
/* --------------------------------------------------------------------------------------------------------------- */        
		
		String lastSyncTimeString = AppSettingRepo.findKey("last_sync_time");
		
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(this.INTEGRATION_BILL_URL)
                .queryParam("FromDate", lastSyncTimeString).queryParam("ToDate", currentDateTime)
                .queryParam("InvestigationList", "true").queryParam("Mode", "5").build();

        HttpEntity<String> getData = new HttpEntity<String>(headers);

        ResponseEntity<List<Bill>> response1 = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, getData,
                new ParameterizedTypeReference<List<Bill>>() {
                });

        List<Bill> bill = response1.getBody();
        
        for (int i = 0; i < bill.size(); i++) {
        	
			Bill billObj = bill.get(i);
			billObj.setIcstatus((byte) 0);
			
        
        for(int j = 0;j < billObj.Investigations.size(); j++) {
        	
        	Investigations invest = billObj.Investigations.get(j);
        	invest.setIcStatus((byte) 0);
        	invest.setHold((byte) 0);
        	invest.setIseligibleIc((byte) 0);
        }
        }
        
        BillRepo.saveAll(bill);
        
        String key1 = "last_sync_time";
        AppSetting obj = AppSettingRepo.findBykey1(key1);
        obj.setValue(currentDateTime);
        AppSettingRepo.save(obj);
        
        this.lastSyncTimeString = currentDateTime;
        return this.lastSyncTimeString;  
		}
    
/*----------------------------------------------------------------------------------------------------------------*/   
}

/*@Scheduled(fixedRate=1000)
public void helloworld() {
	
	System.out.print("Hello World");
}*/