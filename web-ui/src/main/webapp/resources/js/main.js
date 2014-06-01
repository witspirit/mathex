var stats = function() {
    var total = 0;
    var correct = 0;
    var mistakes = 0;

    var reset = function() {
        total = 0;
        correct = 0;
        mistakes = 0;
    };

    var trackCorrect = function() {
        total++;
        correct++;
    };

    var trackMistake = function() {
        total++;
        mistakes++;
    };

    var computeScore = function() {
        return (correct / total) * 100;
    };

    return {
        getTotal : function() { return total; },
        getCorrect : function() { return correct; },
        getMistakes : function() { return mistakes; },
        getScore : computeScore,
        trackCorrect : trackCorrect,
        trackMistake : trackMistake,
        reset : reset
    };
}();

var main = function() {

    var exercise = mathexgen.next();
    visuals.updateExercise(exercise.exercise);

    var showConfig = function() {
        $("#min").val(mathexgen.config.min);
        $("#max").val(mathexgen.config.max);
        $("#sum2").prop('checked', mathexgen.config.sum2);
        $("#sum3").prop('checked', mathexgen.config.sum3);
    };

    var showStats = function() {
        $("#total").text(stats.getTotal());
        $("#correct").text(stats.getCorrect());
        $("#mistakes").text(stats.getMistakes());
        $("#score").text(stats.getScore());
    };

    var updateConfig = function() {
        var min = $("#min").val()-0;
        var max = $("#max").val()-0;
        var sum2 = $("#sum2").prop("checked");
        var sum3 = $("#sum3").prop("checked");

        mathexgen.config.min = min;
        mathexgen.config.max = max;
        mathexgen.config.sum2 = sum2;
        mathexgen.config.sum3 = sum3;
    };


    $(document).ready(function() {
        showConfig();

        $("#responseForm").submit(function(event) {
            updateConfig();

            var response = $("#response").val()-0; // Convert to number

            if (response === exercise.solution) {
                stats.trackCorrect();
                exercise = mathexgen.next();
                visuals.updateExercise(exercise.exercise);
            } else {
                // There was a mistake...
                stats.trackMistake();
                visuals.markError();
            }

            showConfig();
            showStats();
            $("#response").val("");
        });
    });
}();


