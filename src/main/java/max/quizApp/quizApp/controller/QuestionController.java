package max.quizApp.quizApp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import max.quizApp.quizApp.model.Questions;
import max.quizApp.quizApp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	 @PostMapping("/add")
	    public ResponseEntity<String> addQuestion(@RequestBody Questions question){
	        return  questionService.addQuestion(question);
	    }
	
	 @GetMapping("/allQuestions")
	    public ResponseEntity<List<Questions>> getAllQuestions(){
	        return questionService.getAllQuestions();
	    }
	 @GetMapping("/category/{category}")
	    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
	        return questionService.getQuestionsByCategory(category);
	    }

}
