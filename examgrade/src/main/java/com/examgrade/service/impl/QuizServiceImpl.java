package com.examgrade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examgrade.dto.OptionRequest;
import com.examgrade.dto.QuestionRequest;
import com.examgrade.dto.QuizRequest;
import com.examgrade.entity.Option;
import com.examgrade.entity.Question;
import com.examgrade.entity.Quiz;
import com.examgrade.entity.User;
import com.examgrade.repository.QuizRepository;
import com.examgrade.repository.UserRepository;
import com.examgrade.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Quiz createQuiz(QuizRequest quizRequest, String teacherEmail) {

        // Find the currently logged-in teacher
        User teacher = userRepository.findByEmail(teacherEmail)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // Create Quiz object
        Quiz quiz = new Quiz();
        quiz.setTitle(quizRequest.getTitle());
        quiz.setSubject(quizRequest.getSubject());
        quiz.setClassGroup(quizRequest.getClassGroup());
        quiz.setDescription(quizRequest.getDescription());
        quiz.setTotalMarks(quizRequest.getTotalMarks());
        quiz.setDurationMinutes(quizRequest.getDurationMinutes());
        quiz.setPassingScore(quizRequest.getPassingScore());
        quiz.setCreatedBy(teacher);
        // Prepare questions list
        List<Question> questionList = new ArrayList<>();

        if (quizRequest.getQuestions() != null) {
            for (QuestionRequest questionRequest : quizRequest.getQuestions()) {

                Question question = new Question();
                question.setQuestionText(questionRequest.getQuestionText());
                question.setQuiz(quiz);

                // Prepare options list
                List<Option> optionList = new ArrayList<>();

                if (questionRequest.getOptions() != null) {
                    for (OptionRequest optionRequest : questionRequest.getOptions()) {

                        Option option = new Option();
                        option.setOptionText(optionRequest.getOptionText());
                        option.setCorrect(optionRequest.isCorrect());
                        option.setQuestion(question);

                        optionList.add(option);
                    }
                }

                question.setOptions(optionList);
                questionList.add(question);
            }
        }

        quiz.setQuestions(questionList);

        // Save quiz, questions, and options (CascadeType.ALL handles everything)
        return quizRepository.save(quiz);
    }
}
