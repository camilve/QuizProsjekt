package services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created by Camilla Velvin on 20.09.2017.
 */
@Path("/quiz")
public class QuizService {

    private static QuizController quizController = new QuizController();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActiveQuiz> getAllQuizs() {
        return quizController.getActiveQuizs();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/futureQuizes")
    public List<ActiveQuiz> getFutureQuiz() {
        return quizController.getFutureQuizs();
    }


    @POST
    public String addActiveQuiz(ActiveQuiz activeQuiz) {
        quizController.addActiveQuiz(activeQuiz);
        return activeQuiz.getId();
    }

    @PUT
    @Path("/join")
    @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
    @Produces (MediaType.APPLICATION_JSON)
    public boolean addParticipants(@FormParam("nickname") String nickname, @FormParam("id") String id) {
        for (ActiveQuiz quiz : quizController.getActiveQuizs()) {
            if(quiz.getId().equals(id)) {
                quiz.addParticipant(nickname);
                return true;
            }
        }
        return false;
    }

}
