package com.examgrade.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examgrade.dto.OptionAttemptDTO;
import com.examgrade.dto.QuestionAttemptDTO;
import com.examgrade.dto.QuizAttemptDTO;
import com.examgrade.dto.QuizResponseDTO;
import com.examgrade.dto.QuizResultDTO;
import com.examgrade.dto.QuizSubmissionDTO;
import com.examgrade.entity.Option;
import com.examgrade.entity.Question;
import com.examgrade.entity.Quiz;
import com.examgrade.repository.QuizRepository;
import com.examgrade.service.StudentQuizService;

@Service
public class StudentQuizServiceImpl implements StudentQuizService {

    @Autowired
    private QuizRepository quizRepository;

    /**
     * Used by availableQuizzes.html
     * Returns quiz summary cards.
     */
    @Override
    public List<QuizResponseDTO> getAllAvailableQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();

        List<QuizResponseDTO> response = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            QuizResponseDTO dto = new QuizResponseDTO();

            dto.setId(quiz.getId());
            dto.setTitle(quiz.getTitle());
            dto.setSubject(quiz.getSubject());
            dto.setDescription(quiz.getDescription());
            dto.setDurationMinutes(quiz.getDurationMinutes());

            if (quiz.getQuestions() != null) {
                dto.setQuestionCount(quiz.getQuestions().size());
            } else {
                dto.setQuestionCount(0);
            }

            response.add(dto);
        }

        return response;
    }

    /**
     * Used by studentattempt.html
     * Returns full quiz with questions and options.
     * IMPORTANT: correct answers are NOT included.
     */
    @Override
    public QuizAttemptDTO getQuizForAttempt(Long quizId) {

        // Find quiz by ID
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        // Create main DTO
        QuizAttemptDTO quizDTO = new QuizAttemptDTO();
        quizDTO.setId(quiz.getId());
        quizDTO.setTitle(quiz.getTitle());
        quizDTO.setSubject(quiz.getSubject());
        quizDTO.setDescription(quiz.getDescription());
        quizDTO.setDurationMinutes(quiz.getDurationMinutes());

        // Prepare question DTO list
        List<QuestionAttemptDTO> questionDTOList = new ArrayList<>();

        // Convert each Question entity to QuestionAttemptDTO
        if (quiz.getQuestions() != null) {
            for (Question question : quiz.getQuestions()) {

                QuestionAttemptDTO questionDTO = new QuestionAttemptDTO();
                questionDTO.setId(question.getId());
                questionDTO.setQuestionText(question.getQuestionText());

                // Prepare option DTO list
                List<OptionAttemptDTO> optionDTOList = new ArrayList<>();

                // Convert each Option entity to OptionAttemptDTO
                if (question.getOptions() != null) {
                    for (Option option : question.getOptions()) {

                        OptionAttemptDTO optionDTO = new OptionAttemptDTO();
                        optionDTO.setId(option.getId());
                        optionDTO.setOptionText(option.getOptionText());

                        // Do NOT expose option.isCorrect()

                        optionDTOList.add(optionDTO);
                    }
                }

                // Set options on question DTO
                questionDTO.setOptions(optionDTOList);

                // Add question DTO to list
                questionDTOList.add(questionDTO);
            }
        }

        // Set all questions on quiz DTO
        quizDTO.setQuestions(questionDTOList);

        return quizDTO;
    }
    @Override
public QuizResultDTO submitQuiz(Long quizId, QuizSubmissionDTO submission) {

    // Load quiz
    Quiz quiz = quizRepository.findById(quizId)
            .orElseThrow(() -> new RuntimeException("Quiz not found"));

    int totalQuestions = quiz.getQuestions().size();
    int correctAnswers = 0;

    // Student answers: questionId -> selectedOptionId
    Map<Long, Long> submittedAnswers = submission.getAnswers();

    // Check each question
    for (Question question : quiz.getQuestions()) {

        Long selectedOptionId = submittedAnswers.get(question.getId());

        // If no answer selected, skip
        if (selectedOptionId == null) {
            continue;
        }

        // Find the correct option
        for (Option option : question.getOptions()) {
            if (option.isCorrect()
                    && option.getId().equals(selectedOptionId)) {
                correctAnswers++;
                break;
            }
        }
    }

    int incorrectAnswers = totalQuestions - correctAnswers;

    // Calculate marks
    int totalMarks = quiz.getTotalMarks();

    // Protect against division by zero
    int obtainedMarks = 0;
    if (totalQuestions > 0) {
        obtainedMarks = (correctAnswers * totalMarks) / totalQuestions;
    }

    double percentage = 0;
    if (totalMarks > 0) {
        percentage = (obtainedMarks * 100.0) / totalMarks;
    }

    boolean passed = percentage >= quiz.getPassingScore();

    // Build result DTO
    QuizResultDTO result = new QuizResultDTO();
    result.setTotalQuestions(totalQuestions);
    result.setCorrectAnswers(correctAnswers);
    result.setIncorrectAnswers(incorrectAnswers);
    result.setObtainedMarks(obtainedMarks);
    result.setTotalMarks(totalMarks);
    result.setPercentage(percentage);
    result.setPassed(passed);

    return result;
}
}