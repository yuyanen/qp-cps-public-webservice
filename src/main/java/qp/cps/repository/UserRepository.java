package qp.cps.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



import qp.cps.model.User;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
public interface UserRepository extends CrudRepository<User, Long> {

//	public User getUserByUserName(String username) {
//		String hql = "from User u where lower(u.username) = lower(:username)";
//
//		Query query = createQuery(hql);
//		query.setParameter("username", username);
//
//		return (User) query.uniqueResult();
//	}
	/**
	 * Returns a session token object based on the token
	 * 
	 * @param token
	 * @return
	 */
	@Query("select u from User u where u.name = :username")
	public User getUserByUserName(@Param("username") String username);

}