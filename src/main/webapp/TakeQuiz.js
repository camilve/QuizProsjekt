/**
 * Created by Camilla Velvin on 21.09.2017.
 */
$(document).ready(function () {
    var searchParams = new URLSearchParams(window.location.search);
    var id = searchParams.get("id");
    var nickname = searchParams.get("nickname");

    $.get("rest/quiz/specificQuiz/"+id, function (data) {
        setTimer(data);
    });

    function setTimer(data) {
        var timeInterval = setInterval(function () {
            var today = new Date();
            var quizTime = new Date(data.quiz.tid);
            var timeToQuiz = quizTime - today;
            var days = Math.floor(timeToQuiz / (1000 * 60 * 60 * 24));
            var hours = Math.floor((timeToQuiz % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            var minutes = Math.floor((timeToQuiz % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((timeToQuiz % (1000 * 60)) / 1000);
            $("#showQuestion").html(`<h2>Time left to quiz: ${days} days, ${hours} hours, ${minutes} minutes and ${seconds} seconds</h2>`);
            if(quizTime-today <=0) {
                clearInterval(timeInterval);
                setQuestions(data);
            }
        }, 1000);

    }

    var round = 0;
    function setQuestions(data) {
        var correctAnswer = [];
        var numberOfQuestions = data.quiz.questions.length;
        $("#showQuestion").html("");
        $("#showPicture").html("");


        if(round < numberOfQuestions) {
            var time = data.quiz.questions[round].seconds;
            for (var j = 0; j < data.quiz.questions[round].correct.length; j++) {
                correctAnswer.push(data.quiz.questions[round].correct[j]);
            }
            var interval = setInterval(function () {
                if (time == 0) {
                    clearInterval(interval);
                    round++;
                    setQuestions(data);
                }
                $("#timeCounter").html("");
                $("#timeCounter").append(`${time}`);
                time--;
            }, 1000);

            $("#showQuestion").append(`<h3>${data.quiz.questions[round].question}</h3>
                <label>Time left: <label id="timeCounter"></label></label>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <input type="button" class="answerButtons btn btn-success" id="svar1" value="${data.quiz.questions[round].answers[0]}">
                    </div>
                    <div class="col-sm-6">
                        <input type="button" class="answerButtons btn btn-info" id="svar2" value="${data.quiz.questions[round].answers[1]}">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <input type="button" class="answerButtons btn btn-warning" id="svar3" value="${data.quiz.questions[round].answers[2]}">
                    </div>
                    <div class="col-sm-6">
                        <input type="button" class="answerButtons btn btn-danger" id="svar4" value="${data.quiz.questions[round].answers[3]}">
                    </div>
                </div>`);

            $("#showPicture").append(`<br>
        <img src="${data.quiz.questions[round].url}" 
        style="height: 100%; margin-left: auto; margin-right: auto; display: block">`);
            $("#svar1").off('click').on('click', function () {
                var correct = false;
                for (var i = 0; i < correctAnswer.length; i++) {
                    if (correctAnswer[i] == 1) {
                        updateScore(1, round);
                        correct = true;
                        alert("Correct answer!");
                    }
                }
                if(!Boolean(correct)) {
                    alert("Wrong answer!");
                }
                clearInterval(interval);
                round++;
                setQuestions(data);
            });
            $("#svar2").click(function () {
                var correct = false;
                for (var j = 0; j < correctAnswer.length; j++) {
                    if (correctAnswer[j] == 2) {
                        updateScore(2, round);
                        correct = true;
                        alert("Correct answer!");
                    }
                }
                if(!Boolean(correct)) {
                    alert("Wrong answer!");
                }
                clearInterval(interval);
                round++;
                setQuestions(data);
            });
            $("#svar3").click(function () {
                var correct = false;
                for (var i = 0; i < correctAnswer.length; i++) {
                    if (correctAnswer[i] == 3) {
                        updateScore(3, round);
                        correct = true;
                        alert("Correct answer!");
                    }
                }
                if(!Boolean(correct)) {
                    alert("Wrong answer!");
                }
                clearInterval(interval);
                round++;
                setQuestions(data);
            });
            $("#svar4").click(function () {
                var correct = false;
                for (var j = 0; j < correctAnswer.length; j++) {
                    if (correctAnswer[j] == 4) {
                        updateScore(4, round);
                        correct = true;
                        alert("Correct answer!");
                    }
                }
                if(!Boolean(correct)) {
                    alert("Wrong answer!");
                }
                clearInterval(interval);
                round++;
                setQuestions(data);
            });
        } else {
            $("#showQuestion").append(``);
            $("#showPicture").append(``);
            window.location.href = "JoinQuiz.html";
        }
    };

    function updateScore(answer, questionNumber) {
        var data = {"nickname": nickname, "id" : id, "answer": answer, "questionNumber":questionNumber};
        console.log(JSON.stringify(data));
        $.ajax({
            url: 'rest/quiz/join/point',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function () {

            },
            error: function (e) {
                console.log(e);
            }
        });
    }
});