package services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Camilla Velvin on 20.09.2017.
 */
@Path("/quiz")
public class QuizService {

    private static QuizController quizController = new QuizController();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActiveQuiz> getAllQuizzes() {
        return quizController.getActiveQuizs();
    }


    @GET
    @Path("/previous")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActiveQuiz> getPreviousQuizzes() {
        return quizController.getPreviousActiveQuizs();
    }

    @GET
    @Path("/ongoing")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActiveQuiz> getOngoingQuizs() {
        return quizController.getOngoingQuizzes();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/futureQuizes")
    public List<ActiveQuiz> getFutureQuiz() {
        return quizController.getFutureQuizs();
    }


    @GET
    @Path("/specificQuiz/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ActiveQuiz findActiveQuiz(@PathParam("id") String id) {
        return quizController.findActiveQuiz(id);
    }

    @POST
    public String addActiveQuiz(ActiveQuiz activeQuiz) {
        activeQuiz.getQuiz().addEndTime();
        quizController.addActiveQuiz(activeQuiz);
        return activeQuiz.getId();
    }

    @POST
    @Path("/join/{id}/{nickname}")
    @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
    public void addParticipants(@PathParam("nickname") String nickname, @PathParam("id") String id) {
        for (ActiveQuiz quiz : quizController.getActiveQuizs()) {
            if(quiz.getId().equals(id)) {
                quiz.addParticipant(nickname);
            }
        }
    }
    @POST
    @Path("/join/point")
    public void addPoints(HelperClass helperClass) {
        for (ActiveQuiz quiz : quizController.getActiveQuizs()) {
            if(quiz.getId().equals(helperClass.getId())) {
                quiz.givePoints(helperClass.getNickname(), helperClass.getAnswer(), helperClass.getQuestionNumber());
            }
        }
    }

    @GET
    @Path("/scoreboard/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public List<Participant> getScoreboard(@PathParam("id") String id) {
        for (ActiveQuiz quiz : quizController.getActiveQuizs()) {
            if(quiz.getId().equals(id)) {
                return quiz.scoreboard();
            }
        }
        return null;
    }

}
