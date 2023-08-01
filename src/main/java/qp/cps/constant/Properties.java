package qp.cps.constant;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.GitProperties;
import org.springframework.stereotype.Component;

import qp.cps.util.DateUtil;

@Component
public class Properties  {

	private transient Logger logger = LoggerFactory.getLogger(getClass());


	@Value("${aws.api.myInfo.url.base}")
	public String myInfoBaseApiUrl;

	@Value("${aws.api.myInfo.url.personBasic}")
	public String myInfoApiPersonBasic;



	
}
