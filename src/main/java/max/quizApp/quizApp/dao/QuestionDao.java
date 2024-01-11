package max.quizApp.quizApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import max.quizApp.quizApp.model.Questions;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer>{

	 List<Questions> findByCategory(String category);
	 
	 

	    @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
	    List<Questions> findRandomQuestionsByCategory(String category, int numQ);

}
