package qp.cps.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.google.common.base.Strings;

import qp.cps.util.DateUtil;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	RequestMappingHandlerAdapter requestMappingHandlerAdapter;

	protected SessionFactory sessionFactory() {
		return entityManager.getEntityManagerFactory().unwrap(SessionFactoryImplementor.class);
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory());
		return txManager;
	}

	@PostConstruct
	public void addConversionConfig() {
		ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter
				.getWebBindingInitializer();
		if (initializer.getConversionService() != null) {
			GenericConversionService genericConversionService = (GenericConversionService) initializer
					.getConversionService();
			genericConversionService.addConverter(new StringToDateTimeConverter());
			genericConversionService.addConverter(new StringToDateConverter());
		}
	}

	public class StringToDateTimeConverter implements Converter<String, LocalDateTime> {

		@Override
		public LocalDateTime convert(String dateString) {
			return Strings.isNullOrEmpty(dateString) ? null : DateUtil.parseDateTime(dateString);
		}
	}

	public class StringToDateConverter implements Converter<String, LocalDate> {

		@Override
		public LocalDate convert(String dateString) {
			return Strings.isNullOrEmpty(dateString) ? null : DateUtil.parseDate(dateString);
		}
	}
}
