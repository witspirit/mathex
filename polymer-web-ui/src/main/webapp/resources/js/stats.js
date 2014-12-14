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
