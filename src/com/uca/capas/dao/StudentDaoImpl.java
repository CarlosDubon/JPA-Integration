package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Student;


@Repository
public class StudentDaoImpl implements StudentDAO{
	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;

	@Override
	public Student findOne(Integer code) throws DataAccessException {
		Student student = entityManager.find(Student.class, code);
		return student;
	}

	@Override
	@Transactional
	public int save(Student s, Integer newRow) throws DataAccessException {
		try {
			if(newRow == 1) entityManager.persist(s);
			else entityManager.merge(s);
			entityManager.flush();
			return 1;
		}catch(DataAccessException ex) {
			ex.printStackTrace();
			return 1;
		}
	}

	@Override
	@Transactional
	public int delete(Student s) throws DataAccessException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("DELETE FROM student WHERE id_student = ?");
			Query query = entityManager.createNativeQuery(sb.toString(),Student.class);
			query.setParameter(1, s.getcStudent());
			query.executeUpdate();
		}catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<Student> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.student");
		Query query = entityManager.createNativeQuery(sb.toString(),Student.class);
		List<Student> resulset= query.getResultList();
		
		return resulset;
	}
}
