package com.wojciechowski.project.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wojciechowski.project.entity.CodeSnippet;

@Repository
public class CodeSnippetDAOImpl implements CodeSnippetDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CodeSnippet> getCodeSnippets(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		String hql = "from CodeSnippet where username = :username";
		List<CodeSnippet> codeSnippets = currentSession.createQuery(hql).
				setParameter("username", username).getResultList();
		return codeSnippets;
	}

	@Override
	public void saveCodeSnippet(CodeSnippet theCodeSnippet) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theCodeSnippet);
	}

	@Override
	public CodeSnippet getCodeSnippet(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		// retrieve/read from database using the primary key
		CodeSnippet theCodeSnippet = currentSession.get(CodeSnippet.class, theId);
		return theCodeSnippet;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery("delete from CodeSnippet where id=:snippetId");
				theQuery.setParameter("snippetId", theId);
		theQuery.executeUpdate();	
	}

}