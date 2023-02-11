package qp.scs.repository;

import org.hibernate.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import qp.scs.model.User;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
@Repository
public class UserRepository extends BaseRepository {

	public User getUserByUserName(String username) {
		String hql = "from User u where lower(u.username) = lower(:username)";

		Query query = createQuery(hql);
		query.setParameter("username", username);

		return (User) query.uniqueResult();
	}
	/**
	 * Returns a session token object based on the token
	 * 
	 * @param token
	 * @return
	 */

}