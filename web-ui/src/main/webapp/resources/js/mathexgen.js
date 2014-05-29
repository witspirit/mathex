var random = function() {
    /**
     * Returns a random number between min and max
     */
    function getRandomArbitary (min, max) {
        return Math.random() * (max - min) + min;
    }

    /**
     * Returns a random integer between min and max
     * Using Math.round() will give you a non-uniform distribution!
     */
    function getRandomInt (min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    function getRandomBoolean() {
        return Math.random() > 0.5 ? true : false;
    }

    return {
        between : getRandomArbitary,
        intBetween : getRandomInt,
        boolean : getRandomBoolean
    };
}();

var mathexgen = function() {
    var min = 0;
    var max = 20;

    var selectDigit = function() {
        return random.intBetween(min, max);
    };

    var selectOperator = function() {
        return random.boolean() ? "+" : "-";
    }

    var isInBounds = function(value) {
        return value >= min && value <= max;
    };

    var compute = function(t1, op, t2) {
        if (op === "+") {
            return t1 + t2;
        } else if (op === "-") {
            return t1 - t2;
        }
        throw "Unsupported operator " + op;
    }

    var generateExercise = function() {
        if (random.boolean()) {
            // Sum 2
            var t1 = selectDigit();
            var t2 = selectDigit();
            var o1 = selectOperator();
            var result = compute(t1, o1, t2);

            return {
                exercise : [ t1, o1, t2],
                solution : result
            };
        } else {
            // Sum 3
            var t1 = selectDigit();
            var t2 = selectDigit();
            var t3 = selectDigit();
            var result = compute(compute(t1, "+", t2), "+", t3);

            return {
                exercise : [ t1, "+", t2, "+", t3],
                solution : result
            };
        }
    };

    var generateExerciseWithinBounds = function() {
        var exercise = generateExercise();
        while (!isInBounds(exercise.solution)) {
            exercise = generateExercise();
        }
        return exercise;
    }

    var sampleData = [
        {
            exercise : ["1", "+", "1"],
            solution : 2
        },
        {
            exercise : ["3", "-", "2"],
            solution : 1
        },
        {
            exercise : ["6", "+", "1", "+", "2"],
            solution : 9
        }
    ];

    var currentIndex = 0;
    var maxIndex = sampleData.length;

    var nextExercise = function() {
        currentIndex = (currentIndex + 1) % maxIndex;
        return sampleData[currentIndex];
    };

    return {
        next : generateExerciseWithinBounds
    };
}();

