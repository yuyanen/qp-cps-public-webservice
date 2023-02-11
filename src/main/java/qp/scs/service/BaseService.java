package qp.scs.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import qp.scs.model.api.Entity;
import qp.scs.repository.BaseRepository;

public abstract class BaseService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("baseRepository")
	protected BaseRepository baseRepo;

	/**
	 * Retrieves the object with the specified id
	 * 
	 * @param cls
	 * @param id
	 * @return
	 */
	public <T extends Entity> T get(Class<T> cls, Serializable id) {
		return baseRepo.get(cls, id);
	}
	
	public <T extends Entity> void save(T t) {
		baseRepo.save(t);
	}
}
