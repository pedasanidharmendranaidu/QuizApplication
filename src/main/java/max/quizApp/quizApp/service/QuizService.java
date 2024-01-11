package max.quizApp.quizApp.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import max.quizApp.quizApp.dao.QuestionDao;
import max.quizApp.quizApp.dao.QuizDao;
import max.quizApp.quizApp.model.QuestionWrapper;
import max.quizApp.quizApp.model.Questions;
import max.quizApp.quizApp.model.Quiz;
import max.quizApp.quizApp.model.Responce;

public class QuizService {
	@Autowired
	QuestionDao questionDao;
	@Autowired
	QuizDao quizDao; 
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Questions> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
         java.util.Optional<Quiz> quiz = quizDao.findById(id);
        List<Questions> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Questions q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }
	public ResponseEntity<Integer> calculateResult(Integer id, List<Responce> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Questions> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Responce response : responses){
            if(response.getResponce().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
