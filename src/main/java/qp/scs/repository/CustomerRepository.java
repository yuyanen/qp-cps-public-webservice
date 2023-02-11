package qp.scs.repository;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import qp.scs.model.Customer;
import qp.scs.model.User;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
public interface CustomerRepository extends CrudRepository<Customer, String> {

//	public List<Customer> getCustomers() {
//		String hql = "from Customer";
//
//		Query query = createQuery(hql);
//
//		List<Customer> resultList = query.list();
//
//		return resultList;
//	}
//	
//	public Customer getCustomerById(String customerId) {
//		String hql = "from Customer c where lower(c.id) = lower(:id)";
//
//		Query query = createQuery(hql);
//		query.setParameter("id", customerId);
//
//		return (Customer) query.uniqueResult();
//	}

}