package qp.cps.controller;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import qp.cps.constant.Codes;
import qp.cps.constant.Codes.Trades;
import qp.cps.constant.Messages;
//import qp.cps.constant.Properties;
import qp.cps.dto.ListableDto;
import qp.cps.dto.publicsite.PublicContentDto;
import qp.cps.dto.publicsite.PublicGuidesDto;
import qp.cps.dto.publicsite.PublicTousDto;
import qp.cps.exception.ValidationException;
//import qp.cps.helper.FileHelper;
import qp.cps.model.PublicContent;
import qp.cps.model.PublicGuide;
import qp.cps.model.PublicTou;
import qp.cps.model.Trade;
import qp.cps.model.Type;
//import qp.cps.repository.CommonRepository;
//import qp.cps.repository.SystemRepository;
import qp.cps.util.DateUtil;
import qp.cps.constant.Properties;

@RestController
@RequestMapping(path = "/api/common/public")
@Transactional
public class CommonController extends BaseController {

	protected transient Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	Properties properties;
	
//	
//	@Autowired
//	FileHelper fileHelper;


	@RequestMapping(path = "/max-file-size", method = RequestMethod.GET)
	public int getMaxFileSize() {
		logger.debug(cache.getSystemParameterAsString(Codes.SystemParameters.MAX_FILE_SIZE));
		int sizebyte = 0;
		try {
			int sizekb = Integer.parseInt(cache.getSystemParameterAsString(Codes.SystemParameters.MAX_FILE_SIZE));
			sizebyte = sizekb * 1024;
		} catch (Exception e) {
			logger.error("Error in max-file-size defined by web administrator.");
			return 0;
		}
		return sizebyte;
	}




//	@RequestMapping(path = "/privacy-statement", method = RequestMethod.GET)
//	public PublicContentDto getPrivacyStatement() {
//		PublicContent pc = systemRepository.get(PublicContent.class, Codes.PublicContent.TOU_PRIVACY_STATEMENT);
//		return PublicContentDto.buildDto(pc);
//
//	}

	private RestTemplate getRestTemplate() {
		String PROXY_SERVER_HOST = "internal-p2l-prod-elasticl-op5sfi8thy7j-885561264.ap-southeast-1.elb.amazonaws.com";
		int PROXY_SERVER_PORT = 8080;
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_SERVER_HOST, PROXY_SERVER_PORT));
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setProxy(proxy);
		return new RestTemplate(requestFactory);
	}

	private RestTemplate getRestTemplate(String proxyServer, int proxyPort) {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy())
				.setProxy(new HttpHost(proxyServer, proxyPort)).build();
		requestFactory.setHttpClient(httpClient);
		return new RestTemplate(requestFactory);

	}

	

}
