package com.atc.assessment.repository.impl;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atc.assessment.common.constants.CommonConstant;
import com.atc.assessment.repository.FindingNearbyUsersRepository;
import com.atc.assessment.repository.entity.LocationEntity;
import com.atc.assessment.repository.entity.UserEntity;

/**
 * @author Marappan Sampath
 *
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class FindingNearbyUsersRepositoryImpl implements FindingNearbyUsersRepository {

	@Value("${hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Value("${proximity.radius.km}")
	private Double radius;

	@Autowired
	private SessionFactory sessionFactory;
	
	private Transaction txn;
	private Session session;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public int createUsers(List<UserEntity> userList) {
		session = sessionFactory.openSession();
		txn = session.beginTransaction();
		int res = saveAsBatch(userList);
		System.out.println("txn ==============>: " + txn.isActive());
		txn.commit();
		return res;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserEntity> getAllMatchingUsers(String latitude, String longitude) {
		session = sessionFactory.openSession();
		txn = session.beginTransaction();
		String fetchMatchningUsersQuery = MessageFormat.format(CommonConstant.FETCH_MATCHNING_USERS_QUERY, latitude, longitude, radius);
		System.out.println("fetchMatchningUsersQuery: " + fetchMatchningUsersQuery);
		List<UserEntity> userResponseData = session.createNativeQuery(fetchMatchningUsersQuery, UserEntity.class).getResultList();
		System.out.println("userResponseData: " + userResponseData);
		return userResponseData;
	}
	

	/**
	 * used to save collections data
	 * @param entities
	 * @return collection of saved data
	 */
	@Transactional
	private <T extends UserEntity> int saveAsBatch(Collection<T> entities) {
		int i = 0;
		int recordCount = 0;
		txn = session.getTransaction();
		try {
		for (T t : entities) {
			if( !session.createQuery("from UserEntity where id="+t.getId()).list().isEmpty()) {
				for( LocationEntity loc : t.getLocations() ) {
					UserEntity userEntity = new UserEntity();
					userEntity.setId(t.getId());
					userEntity.setName(t.getName());
					LocationEntity locationEntity = new LocationEntity();
					locationEntity.setUserId(loc.getUserId());
					locationEntity.setPoint(loc.getPoint());
					userEntity.addToLocation(locationEntity);
					persistOrMerge(userEntity);
				}
				persistOrMerge(t);
				i++;
				if (i % batchSize == 0) {
					session.flush();
					session.clear();
				}
				recordCount++;
			}else {
				persistOrMerge(t);
				i++;
				if (i % batchSize == 0) {
					session.flush();
					session.clear();
				}
				recordCount++;
			}
		}
		}finally {
			//TODO
		}
		return recordCount;
	}
	

	/**
	 * @param gets user data to persist
	 * @return data
	 */
	@SuppressWarnings("unchecked")
	private <T extends UserEntity> T persistOrMerge(T t) {
		if (t.getId() == null) {
			session.persist(t);
			return t;
		} else {
			T tt = (T) session.merge(t);
			return tt;
		}
	}
}
