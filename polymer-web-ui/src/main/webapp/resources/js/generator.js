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
    var config = {
        min : 0,
        max : 20,
        sum2 : true,
        sum3 : true
    };

    var selectDigit = function() {
        return random.intBetween(config.min, config.max);
    };

    var selectOperator = function() {
        return random.boolean() ? "+" : "-";
    }

    var isInBounds = function(value) {
        return value >= config.min && value <= config.max;
    };

    var compute = function(t1, op, t2) {
        if (op === "+") {
            return t1 + t2;
        } else if (op === "-") {
            return t1 - t2;
        }
        throw "Unsupported operator " + op;
    }

    var createSum2 = function() {
        // Sum 2
        var t1 = selectDigit();
        var t2 = selectDigit();
        var o1 = selectOperator();
        var result = compute(t1, o1, t2);

        return {
            exercise : [ t1, o1, t2],
            solution : result
        };
    };

    var createSum3 = function() {
        // Sum 3
        var t1 = selectDigit();
        var t2 = selectDigit();
        var t3 = selectDigit();
        var result = compute(compute(t1, "+", t2), "+", t3);

        return {
            exercise : [ t1, "+", t2, "+", t3],
            solution : result
        };
    };

    var selectSumType = function() {
        if (config.sum2 && !config.sum3) {
            return createSum2;
        } else if (!config.sum2 && config.sum3) {
            return createSum3;
        } else {
            // All other permutations are considered a random mix
            return random.boolean() ? createSum2 : createSum3;
        }
    };

    var generateExercise = function() {
        return selectSumType()(); // Invoke the function result of the selectSumType
    };

    var generateExerciseWithinBounds = function() {
        var exercise = generateExercise();
        var retryCounter = 0;
        while (!isInBounds(exercise.solution)) {
            exercise = generateExercise();
            retryCounter++;

            if (retryCounter >= 100) {
                // We apparently fail to generate an exercise that meets the criteria...
                // Let us reset the configuration to something safe.
                // NOTE: We do not replace the object as we have exposed it via the return object
                config.min = 0;
                config.max = 20;
                config.sum2 = true;
                config.sum3 = true;

                console.log("[WARNING] Automatically reset configuration, since the current configuration could not yield a valid result");
            }
        }
        return exercise;
    }

    return {
        next : generateExerciseWithinBounds,
        config: config
    };
}();

