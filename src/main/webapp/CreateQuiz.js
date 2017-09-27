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
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3">Time (seconds):</label>
                            <div class="col-sm-8">
                                <select id="timeQuestion-${idCounter}" class="form-control">
                                    <option value="10">10 sec</option>
                                    <option value="20">20 sec</option>
                                    <option value="30">30 sec</option>
                                    <option value="40">40 sec</option>
                                    <option value="50">50 sec</option>
                                    <option value="60">60 sec</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <br>


            <div class="row">
                <div class="col-sm-6">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3">Answer 1:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="answer1-${idCounter}" />
                            </div>
                            <div class="col-sm-1">
                                <input name="correctAnswer1-1" type="checkbox" id="correct1-${idCounter}" checked>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-sm-6">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3">Answer 2:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="answer2-${idCounter}" />
                            </div>
                            <div class="col-sm-1">
                                <input name="correctAnswer1-1" type="checkbox" id="correct2-${idCounter}">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3">Answer 3:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="answer3-${idCounter}" />
                            </div>
                            <div class="col-sm-1">
                                <input name="correctAnswer1-1" type="checkbox" id="correct3-${idCounter}">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-sm-6">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3">Answer 4:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="answer4-${idCounter}" />
                            </div>
                            <div class="col-sm-1">
                                <input name="correctAnswer1-1" type="checkbox" id="correct4-${idCounter}">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <br>
            <br>
            <div class="row">
                <div class="col-sm-6">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-4">URL for picture: </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="url-${idCounter}" />
                            </div>

                        </div>
                    </form>
                    <br>
                </div>
            </div>
              `);
        idCounter++;
    });
    var allCorrect = true;
    $("#addQuiz").click(function () {
        var params = [];
        for(var i=1; i<idCounter; i++) {
            var correct = [];
            if ($("#correct1-" + i).is(":checked")) {
                correct.push(1);
            }
            if ($("#correct2-" + i).is(":checked")) {
                correct.push(2);
            }
            if ($("#correct3-" + i).is(":checked")) {
                correct.push(3);
            }
            if ($("#correct4-" + i).is(":checked")) {
                correct.push(4);
            }

            if (correct.length == 0) {
                alert("Please add a correct answer");
                allCorrect = false;
            }
            if ($("#question-" + i).val() == "" || $("#QuizName").val() == "" || $("#timepicker").val() == "" ||
                $("#answer1-" + i).val() == "" || $("#answer2-" + i).val() == "") {
                alert("Type in all the information! :-)");
                allCorrect = false;
            } else {
                allCorrect = true;
                params.push({
                    "question": $("#question-" + i).val(),
                    "answers": [$("#answer1-" + i).val(), $("#answer2-" + i).val(), $("#answer3-" + i).val(), $("#answer4-" + i).val()],
                    "correct": correct, url: $("#url-" + i).val(), seconds: $("#timeQuestion-" + i).val()
                });
            }
        }
        if(allCorrect) {
            var quiz = {"quiz": {"quizName": $("#QuizName").val(), "questions": params, "tid": $("#timepicker").val()}};


            $.ajax({
                url: 'rest/quiz',
                type: 'POST',
                data: JSON.stringify(quiz),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                },
                error: function (e) {
                    console.log(e);
                }
            });
            window.location.href = "CreateQuiz.html";
            alert("The quiz is created");
        }
    })
});
