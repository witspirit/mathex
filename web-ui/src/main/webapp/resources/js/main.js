var main = function() {

    var exercise = mathexgen.next();
    visuals.updateExercise(exercise.exercise);

    var showConfig = function() {
        $("#min").val(mathexgen.config.min);
        $("#max").val(mathexgen.config.max);
        $("#sum2").prop('checked', mathexgen.config.sum2);
        $("#sum3").prop('checked', mathexgen.config.sum3);
    }

    var updateConfig = function() {
        var min = $("#min").val()-0;
        var max = $("#max").val()-0;
        var sum2 = $("#sum2").prop("checked");
        var sum3 = $("#sum3").prop("checked");

//        console.log("min = "+min+" ("+typeof min+")");
//        console.log("max = "+max+" ("+typeof max+")");
//        console.log("sum2 = "+sum2+" ("+typeof sum2+")");
//        console.log("sum3 = "+sum3+" ("+typeof sum3+")");

        mathexgen.config.min = min;
        mathexgen.config.max = max;
        mathexgen.config.sum2 = sum2;
        mathexgen.config.sum3 = sum3;
    }

    $(document).ready(function() {
        showConfig();

        $("#responseForm").submit(function(event) {
            updateConfig();

            var response = $("#response").val()-0; // Convert to number

            if (response === exercise.solution) {
                exercise = mathexgen.next();
                visuals.updateExercise(exercise.exercise);
            } else {
                // There was a mistake...
                visuals.markError();
            }

            showConfig();
            $("#response").val("");
        });
    });
}();


