/**
 * Created by Camilla Velvin on 19.09.2017.
 */
var idCounter = 2;
$(document).ready(function () {
    $("#addQuestion").click(function () {
        $("#questions").append(`      
            <hr>
            <div class="row">
            <div class="col-sm-8">
                <h3>Question ${idCounter}: <input type="text" id="question-${idCounter}"> </h3>
                <label>(Check of the correct answers)</label>
            </div>
            <div class="col-sm-4">
                <br>
                <label>Time (seconds): </label>
            <select id="timeQuestion-${idCounter}">
            <option value="10">10 sec</option>
            <option value="20">20 sec</option>
            <option value="30">30 sec</option>
            <option value="40">40 sec</option>
            <option value="50">50 sec</option>
            <option value="60">60 sec</option>
            </select>
            </div>
            </div>
            <br>
    
    
            <div class="row">
                <div class="col-sm-6">
                <label>Answer 1: </label>
                <input name="correctAnswer" type="text" id="answer1-${idCounter}">
                <input name="correctAnswer" type="checkbox">
                </div>
                <div class="col-sm-6">
                <label>Answer 2: </label>
                <input name="correctAnswer" type="text" id="answer2-${idCounter}">
                <input name="correctAnswer" type="checkbox">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                <label>Answer 3: </label>
            <input name="correctAnswer" type="text" id="answer3-${idCounter}">
                <input name="correctAnswer" type="checkbox">
                </div>
                <div class="col-sm-6">
                <label>Answer 4: </label>
            <input name="correctAnswer" type="text" id="answer4-${idCounter}">
                <input name="correctAnswer" type="checkbox">
                </div>
            </div>
              `);
        idCounter++;
    });
    
    $("#addQuiz").click(function () {
        var params = [];
        for(var i=1; i<idCounter; i++) {
            params.push({"question" : $("#question-"+i).val(),
                    "answers" : [$("#answer1-"+i).val(), $("#answer2-"+i).val(), $("#answer3-"+i).val(), $("#answer4-"+i).val()],
                    "correct" : [1], url : "hei", seconds : $("#timeQuestion-"+i).val()});
        }

        var quiz = {"quiz":{"quizName":$("#QuizName").val(),"questions":params,"tid":$("#timepicker").val()}};


       // $.post("/rest/quiz", JSON.stringify(quiz));

        console.log(quiz);

        $.ajax({
            url: 'rest/quiz',
            type: 'POST',
            data: JSON.stringify(quiz),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(result) {
                console.log(result);
            },
            error: function (e) {
                console.log(e);
            }
        });
    })
});
